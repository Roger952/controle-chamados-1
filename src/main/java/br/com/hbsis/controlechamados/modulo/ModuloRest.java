package br.com.hbsis.controlechamados.modulo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:3200")
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

        moduloService.saveImports(multipartFile);

        message = moduloService.messageCaseErr;
    }

    @GetMapping("/export")
    @PreAuthorize("hasRole('ROLE_EXPORT_MODULO')")
    public String exportMessage() {
        if (!message.isEmpty()) {
            return message;
        }
        throw new IllegalArgumentException("Está enviando uma mensagem vazia!");
    }
}
