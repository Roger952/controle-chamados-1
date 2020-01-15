package br.com.hbsis.controlechamados.usuario.atendente;

import br.com.hbsis.controlechamados.empresa.Empresa;
import br.com.hbsis.controlechamados.storage.Disco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/atendente")
public class AtendenteRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtendenteRest.class);
    private final AtendenteService  atendenteService;
    private final IAtendenteRepository iAtendenteRepository;

    @Autowired /** CONSTRUTOR */
    public AtendenteRest(AtendenteService atendenteService, IAtendenteRepository iAtendenteRepository) {
        this.atendenteService = atendenteService;
        this.iAtendenteRepository = iAtendenteRepository;
    }

    /** MÉTODOS */
    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_CADASTRAR_ATENDENTE')")
    public AtendenteDTO save(@RequestBody AtendenteDTO atendenteDTO){
        LOGGER.info("Recebendo save de atendente...");
        return this.atendenteService.save(atendenteDTO);
    }

    @PostMapping("/saveImagem")
    public void saveImagem(@Valid @RequestParam MultipartFile file){
        LOGGER.info("Recebendo save de imagem...");
        this.atendenteService.salvarFoto(file);
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_LISTAR_ATENDENTE')")
    public List<Atendente> findAll() {
        return this.iAtendenteRepository.findAll();
    }
}
