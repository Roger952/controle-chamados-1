package br.com.hbsis.controlechamados.modulo;

import br.com.hbsis.controlechamados.produtos.ProdutoService;
import br.com.hbsis.controlechamados.usuario.atendente.AtendenteService;
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

    public void saveImports(MultipartFile multipartFile, String nomeProduto) throws IOException {

        for (ModuloDTO moduloDTO : importFormated(multipartFile, nomeProduto)) {

            if (String.valueOf(moduloDTO.getNomeProduto()).isEmpty()) {
                LOGGER.info("O id do produto não pode ser vazio");
            } else if (!produtoService.existsByNome(moduloDTO.getNomeProduto())) {
                LOGGER.info("Não existe um produto com  este Id");
            }
            if (moduloDTO.getNomeModulo().isEmpty()) {
                LOGGER.info("O nome do Modulo não pode estar vazio");
            } else if (noEqualsNameAndProduto(moduloDTO)) {
                LOGGER.info("Este produto já tem um modulo com o nome de " + moduloDTO.getNomeModulo());
            } else if (moduloDTO.getNomeModulo().length() > 50) {
                LOGGER.info("O numeros de caracteres permitidos foi exedido");
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

    public List<ModuloDTO> importFormated(MultipartFile multipartFile, String nomeProduto) throws IOException {

        List<ModuloDTO> moduloDTOS = new ArrayList<>();
        Integer contador = 1;

        List<String[]> importCSV = Import.importWithoutDependencyCSV(multipartFile);
        for (String[] linhaImportada : importCSV){

            ModuloDTO moduloDTO = new ModuloDTO();

            if (linhaImportada.length > 1) {
                LOGGER.info("O CSV só pode conter o nome do Modulo \r\n" + "Inconsistência encontrada na linha: " + contador);
            } else if (linhaImportada[0].length() > 50) {
                LOGGER.info("O nome inserido não pode ter mais de 50 caracteres \r\n" + "Inconsistência encontrada na linha: " + contador);
            } else if (linhaImportada[0].isEmpty()) {
                LOGGER.info("O nome do modulo Não pode ser vazio \r\n" + "Inconsistência encontrada na linha: " + contador);
            } else {
                moduloDTO.setNomeModulo(linhaImportada[0]);
                moduloDTO.setNomeProduto(nomeProduto);

                moduloDTOS.add(moduloDTO);
            }
            contador++;
        }
        return moduloDTOS;
    }
}
