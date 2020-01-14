package br.com.hbsis.controlechamados.modulo;

import br.com.hbsis.controlechamados.produtos.ProdutoService;
import br.com.hbsis.controlechamados.usuario.atendente.AtendenteService;
import br.com.hbsis.controlechamados.utils.exportAndImport.Export;
import br.com.hbsis.controlechamados.utils.exportAndImport.Import;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public ModuloService(IModuloRepositoty iModuloRepositoty, ProdutoService produtoService) {
        this.iModuloRepositoty = iModuloRepositoty;
        this.produtoService = produtoService;
    }

    private boolean noEqualsNameAndProduto(ModuloDTO moduloDTO) {
        return iModuloRepositoty.existsByNomeModuloAndProduto(moduloDTO.getNomeModulo(),
                produtoService.findByNome(moduloDTO.getNomeProduto()));
    }

    public void saveImports(MultipartFile multipartFile) throws IOException {

        Export export = new Export();

        for (ModuloDTO moduloDTO : importFormatted(multipartFile)) {

            if (String.valueOf(moduloDTO.getNomeProduto()).isEmpty()) {
                export.exportErrInTxt("O id do produto não pode ser vazio" + "\r\n");
            } else if (!produtoService.existsByNome(moduloDTO.getNomeProduto())) {
                export.exportErrInTxt("Não existe um produto com  este Id" + "\r\n");
            }
            if (moduloDTO.getNomeModulo().isEmpty()) {
                export.exportErrInTxt("O nome do Modulo não pode estar vazio" + "\r\n");
            } else if (noEqualsNameAndProduto(moduloDTO)) {
                export.exportErrInTxt("Este produto já tem um modulo com o nome de " + moduloDTO.getNomeModulo() + "\r\n");
            } else if (moduloDTO.getNomeModulo().length() > 50) {
                export.exportErrInTxt("O numeros de caracteres permitidos foi exedido" + "\r\n");
            } else {
                save(moduloDTO);
            }

        }
    }

    private void save(ModuloDTO moduloDTO) {
        Modulo modulo = new Modulo();

        modulo.setNomeModulo(moduloDTO.getNomeModulo());
        modulo.setProduto(produtoService.findByNome(moduloDTO.getNomeProduto()));

        iModuloRepositoty.save(modulo);
    }

    public List<ModuloDTO> importFormatted(MultipartFile multipartFile) throws IOException {

        List<ModuloDTO> moduloDTOS = new ArrayList<>();
        Integer contador = 2;

        List<String[]> importCSV = Import.importWithoutDependencyCSV(multipartFile);
        for (String[] lineImported : importCSV) {

            ModuloDTO moduloDTO = new ModuloDTO();
            Export export = new Export();

            if (lineImported.length > 2) {
                export.exportErrInTxt("O CSV só pode conter o nome do Modulo e o Produto no qual este pertence \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n");
            } else if (lineImported[0].length() > 50) {
                export.exportErrInTxt("O nome inserido não pode ter mais de 50 caracteres \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n");
            } else if (lineImported[0].isEmpty()) {
                export.exportErrInTxt("O nome do modulo Não pode ser vazio \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n");
            } else if (lineImported[1].isEmpty()) {
                export.exportErrInTxt("O nome do produto Não pode estar vazio \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n");
            } else if (!produtoService.existsByNome(lineImported[1])) {
                export.exportErrInTxt("O nome deste produto não existe ou ainda não foi cadastrado \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n");
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
