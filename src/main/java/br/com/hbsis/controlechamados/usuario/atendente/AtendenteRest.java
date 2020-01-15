package br.com.hbsis.controlechamados.usuario.atendente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/atendente")
public class AtendenteRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtendenteRest.class);
    private final AtendenteService  atendenteService;

    @Autowired /** CONSTRUTOR */
    public AtendenteRest(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    /** MÉTODOS */
    @PostMapping("/save")
    public AtendenteDTO save(@RequestBody AtendenteDTO atendenteDTO){
        LOGGER.info("Recebendo save de atendente...");
        return this.atendenteService.save(atendenteDTO);
    }

    @PostMapping("/saveImagem")
    public void saveImagem(@Valid @RequestParam MultipartFile file){
        LOGGER.info("Recebendo save de imagem...");
        this.atendenteService.salvarFoto(file);
    }
}
