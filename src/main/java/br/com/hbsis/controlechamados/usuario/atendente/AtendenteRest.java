package br.com.hbsis.controlechamados.usuario.atendente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/atendente")
public class AtendenteRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtendenteRest.class);
    private final AtendenteService  atendenteService;

    @Autowired /** CONSTRUTOR */
    public AtendenteRest(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    /** MÉTODOS */
    @PostMapping
    public AtendenteDTO save(@RequestParam("file") MultipartFile file, @RequestBody AtendenteDTO atendenteDTO){

        LOGGER.info("Recebendo save para atendente...");
        LOGGER.debug("Payload: {}", atendenteDTO);

        //files.add(file.getOriginalFilename());
        return this.atendenteService.save(file, atendenteDTO);
    }
}
