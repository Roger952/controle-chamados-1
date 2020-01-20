package br.com.hbsis.controlechamados.modulo;

import br.com.hbsis.controlechamados.utils.exportAndImport.Export;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/modulos")
public class ModuloRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModuloRest.class);

    private final ModuloService moduloService;
    private String message = "";

    public ModuloRest(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @PostMapping("/import")
    public void save(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        LOGGER.info("Importando os modulos....");

        moduloService.saveAndImports(multipartFile);

        message = moduloService.messageCaseErr;
    }

    @GetMapping("/export")
    @PreAuthorize("hasRole('ROLE_EXPORT_MODULO')")
    public String exportMessage() {
        if (!message.isEmpty()) {
            LOGGER.info("Enviando a mensagem contendo as inconsistências");
            return message;
        }
        LOGGER.error("Nenhuma Inconsistência em");
        throw new IllegalArgumentException("Esta enviando uma mensagem vazia");
    }

    @GetMapping("/export-formatted")
    @PreAuthorize("hasRole('ROLE_EXPORT_FORMATTED_CSV_MODULO')")
    public String exportModule(){
        return Export.exportModuloFormattedInCsv();
    }
}