package br.com.hbsis.controlechamados.modulo;

import br.com.hbsis.controlechamados.produtos.ProdutoService;
import br.com.hbsis.controlechamados.usuario.atendente.AtendenteService;
import br.com.hbsis.controlechamados.utils.exportAndImport.Import;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ModuloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtendenteService.class);

    private final IModuloRepositoty iModuloRepositoty;
    private final ProdutoService produtoService;
    public String messageCaseErr = "";

    @Autowired
    public ModuloService(IModuloRepositoty iModuloRepositoty, ProdutoService produtoService) {
        this.iModuloRepositoty = iModuloRepositoty;
        this.produtoService = produtoService;
    }

    private boolean noEqualsNameAndProduto(ModuloDTO moduloDTO) {
        return iModuloRepositoty.existsByNomeModuloAndProduto(moduloDTO.getNomeModulo(),
                produtoService.findByNome(moduloDTO.getNomeProduto()));
    }

    public String saveImports(MultipartFile multipartFile) throws IOException {


        messageCaseErr = "";

        Integer contador = 2;

        for (ModuloDTO moduloDTO : importFormatted(multipartFile)) {

            if (String.valueOf(moduloDTO.getNomeProduto()).isEmpty()) {
                messageCaseErr += "O id do produto não pode ser vazio" + "\r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n";
            } else if (!produtoService.existsByNome(moduloDTO.getNomeProduto())) {
                messageCaseErr += "Não existe um produto com  este Nome" + "\r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n";

            }
            if (moduloDTO.getNomeModulo().isEmpty()) {
                messageCaseErr += "O nome do Modulo não pode estar vazio" + "\r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n";

            } else if (noEqualsNameAndProduto(moduloDTO)) {
                messageCaseErr += "Este produto já tem um modulo com o  nome de " + moduloDTO.getNomeModulo() + "\r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n";

            } else if (moduloDTO.getNomeModulo().length() > 50) {
                messageCaseErr += "O numeros de caracteres permitidos foi exedido" + "\r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n";

            } else {
                save(moduloDTO);
            }
            contador++;
            LOGGER.info(messageCaseErr);
        }
        return messageCaseErr;
    }

    private void save(ModuloDTO moduloDTO) {
        Modulo modulo = new Modulo();

        modulo.setNomeModulo(moduloDTO.getNomeModulo());
        modulo.setProduto(produtoService.findByNome(moduloDTO.getNomeProduto()));

        iModuloRepositoty.save(modulo);
    }

    public List<ModuloDTO> importFormatted(MultipartFile multipartFile) throws IOException {

        messageCaseErr = "";

        List<ModuloDTO> moduloDTOS = new ArrayList<>();
        Integer contador = 2;

        List<String[]> importCSV = Import.importWithoutDependencyCSV(multipartFile);
        for (String[] lineImported : importCSV) {

            ModuloDTO moduloDTO = new ModuloDTO();

            if (lineImported.length > 2) {
                messageCaseErr += "O CSV só pode conter o nome do Modulo e o Produto no qual este pertence \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n";
            } else if (lineImported[0].length() > 50) {
                messageCaseErr += "O nome inserido não pode ter mais de 50 caracteres \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n";
            } else if (lineImported[0].isEmpty()) {
                messageCaseErr += "O nome do modulo Não pode ser vazio \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n";
            } else if (lineImported[1].isEmpty()) {
                messageCaseErr += "O nome do produto Não pode estar vazio \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n";
            } else if (!produtoService.existsByNome(lineImported[1])) {
                messageCaseErr += "O nome deste produto não existe ou ainda não foi cadastrado \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n";
            } else {
                moduloDTO.setNomeModulo(lineImported[0]);
                moduloDTO.setNomeProduto(lineImported[1]);

                moduloDTOS.add(moduloDTO);
            }
            contador++;
        }
        return moduloDTOS;
    }
}
