package br.com.hbsis.controlechamados.usuario.atendente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AtendenteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtendenteService.class);
    private final IAtendenteRepository iAtendenteRepository;

    private final Path rootLocation = Paths.get("img");

    @Autowired /** CONSTRUTOR */
    public AtendenteService(IAtendenteRepository iAtendenteRepository) {
        this.iAtendenteRepository = iAtendenteRepository;
    }

    /** MÉTODOS DE CRUD */
    public AtendenteDTO save(MultipartFile file, String foto) {

        LOGGER.info("Salvando atendente");
        LOGGER.debug("Atendente: {}", foto);

        Atendente atendente = new Atendente();
        atendente.setNome("Teste");
        atendente.setEmail("teste@gmail.com");
        atendente.setSenha("teste");

        atendente = this.iAtendenteRepository.save(atendente);
        return AtendenteDTO.of(atendente);
    }
}
