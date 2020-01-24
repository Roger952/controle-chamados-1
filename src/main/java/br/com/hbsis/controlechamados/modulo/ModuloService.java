package br.com.hbsis.controlechamados.modulo;

import br.com.hbsis.controlechamados.produtos.ProdutoService;
import br.com.hbsis.controlechamados.usuario.atendente.AtendenteService;
import br.com.hbsis.controlechamados.utils.exportAndImport.Import;
import br.com.hbsis.controlechamados.utils.formatedObjects.FormattedMessageToCst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class ModuloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtendenteService.class);

    private final IModuloRepositoty iModuloRepositoty;
    private final ProdutoService produtoService;
    public String messageCaseErr = "";
    FormattedMessageToCst formattedMessageToCst = new FormattedMessageToCst();

    @Autowired
    public ModuloService(IModuloRepositoty iModuloRepositoty, ProdutoService produtoService) {
        this.iModuloRepositoty = iModuloRepositoty;
        this.produtoService = produtoService;
    }

    private boolean noEqualsNameAndProduto(String nomeModulo, String nomeProduto) {
        return iModuloRepositoty.existsByNomeModuloAndProduto(nomeModulo,
                produtoService.findByNome(nomeProduto));
    }

    private ModuloDTO save(ModuloDTO moduloDTO) {

        LOGGER.info("Salvando Módulo");
        LOGGER.debug("Linha: {}", moduloDTO);

        Modulo modulo = new Modulo();

        modulo.setNomeModulo(moduloDTO.getNomeModulo());
        modulo.setProduto(produtoService.findByNome(moduloDTO.getNomeProduto()));

        modulo = this.iModuloRepositoty.save(modulo);

        LOGGER.info("Modulo salvado com sucesso!");
        return moduloDTO.of(modulo);
    }

    public void saveAndImports(MultipartFile multipartFile) throws IOException {

        int counter = 2;

        messageCaseErr = "";

        List<String[]> importCSV = Import.importWithoutDependencyCSV(multipartFile);
        for (String[] lineImported : importCSV) {

            if (isFormatted(counter, lineImported)) {
                ModuloDTO moduloDTO = new ModuloDTO();
                moduloDTO.setNomeModulo(lineImported[0]);
                moduloDTO.setNomeProduto(lineImported[1]);
                save(moduloDTO);
            }
            counter++;
        }
    }

    private boolean isFormatted(int counter, String[] lineImported) {


        if (lineImported.length != 2) {
            messageCaseErr += formattedMessageToCst.formattedModuloCST(lineImported[0], lineImported[1],
                    "O CSV só pode conter o nome do Modulo, o Produto qual este pertence, e uma descrição opcional" +
                            "Inconsistência encontrada na linha: " + counter + " da importação original");
            return false;
        } else if (lineImported[0].length() > 50) {
            messageCaseErr += formattedMessageToCst.formattedModuloCST(lineImported[0], lineImported[1],
                    "O nome inserido não pode ter mais de 50 caracteres - " +
                            "Inconsistência encontrada na linha: " + counter + " da importação original");
            return false;
        } else if (lineImported[0].isEmpty()) {
            messageCaseErr += formattedMessageToCst.formattedModuloCST(lineImported[0], lineImported[1],
                    "O nome do modulo Não pode ser vazio - " +
                            "Inconsistência encontrada na linha: " + counter + " da importação original");
            return false;
        }
        if (lineImported[1].isEmpty()) {
            messageCaseErr += formattedMessageToCst.formattedModuloCST(lineImported[0], lineImported[1],
                    "O nome do produto Não pode estar vazio - " +
                            "Inconsistência encontrada na linha: " + counter + " da importação original");
            return false;
        } else if (!produtoService.existsByNome(lineImported[1])) {
            messageCaseErr += formattedMessageToCst.formattedModuloCST(lineImported[0], lineImported[1],
                    "O nome deste produto não existe ou ainda não foi cadastrado \r\n" +
                            "Inconsistência encontrada na linha: " + counter + " da importação original");
            return false;
        }
        if (noEqualsNameAndProduto(lineImported[0], lineImported[1])) {
            messageCaseErr += formattedMessageToCst.formattedModuloCST(lineImported[0], lineImported[1],
                    "Este produto já tem um modulo com o  nome de " + lineImported[1] + "\r\n" +
                            "Inconsistência encontrada na linha: " + counter + " da importação original");
            return false;
        }
        return true;
    }
}