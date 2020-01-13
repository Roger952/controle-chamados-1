package br.com.hbsis.controlechamados.modulo;

import br.com.hbsis.controlechamados.produtos.ProdutoService;
import br.com.hbsis.controlechamados.usuario.atendente.AtendenteService;
import br.com.hbsis.controlechamados.utils.exportAndImport.Export;
import br.com.hbsis.controlechamados.utils.exportAndImport.Import;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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

    public void saveImports(HttpServletResponse httpServletResponse, MultipartFile multipartFile, String nomeProduto) throws IOException {

        Export export = new Export();

        for (ModuloDTO moduloDTO : importFormatted(httpServletResponse, multipartFile, nomeProduto)) {

            if (String.valueOf(moduloDTO.getNomeProduto()).isEmpty()) {
                export.exportWithoutDependencyCSV(httpServletResponse, "error.csv", "O id do produto não pode ser vazio" + "\r\n");
            } else if (!produtoService.existsByNome(moduloDTO.getNomeProduto())) {
                export.exportWithoutDependencyCSV(httpServletResponse, "error.csv", "Não existe um produto com  este Id" + "\r\n");
            }
            if (moduloDTO.getNomeModulo().isEmpty()) {
                export.exportWithoutDependencyCSV(httpServletResponse, "error.csv", "O nome do Modulo não pode estar vazio" + "\r\n");
            } else if (noEqualsNameAndProduto(moduloDTO)) {
                export.exportWithoutDependencyCSV(httpServletResponse, "error.csv", "Este produto já tem um modulo com o nome de " + moduloDTO.getNomeModulo() + "\r\n");
            } else if (moduloDTO.getNomeModulo().length() > 50) {
                export.exportWithoutDependencyCSV(httpServletResponse, "error.csv", "O numeros de caracteres permitidos foi exedido" + "\r\n");
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

    public List<ModuloDTO> importFormatted(HttpServletResponse httpServletResponse, MultipartFile multipartFile, String nomeProduto) throws IOException {

        List<ModuloDTO> moduloDTOS = new ArrayList<>();
        Integer contador = 2;

        List<String[]> importCSV = Import.importWithoutDependencyCSV(multipartFile);
        for (String[] linhaImportada : importCSV) {

            ModuloDTO moduloDTO = new ModuloDTO();
            Export export = new Export();

            if (linhaImportada.length > 1) {
                export.exportWithoutDependencyCSV(httpServletResponse, "error.csv", "O CSV só pode conter o nome do Modulo \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n");
            } else if (linhaImportada[0].length() > 50) {
                export.exportWithoutDependencyCSV(httpServletResponse, "error.csv", "O nome inserido não pode ter mais de 50 caracteres \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n");
            } else if (linhaImportada[0].isEmpty()) {
                export.exportWithoutDependencyCSV(httpServletResponse, "error.csv", "O nome do modulo Não pode ser vazio \r\n" +
                        "Inconsistência encontrada na linha: " + contador + "\r\n");
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
