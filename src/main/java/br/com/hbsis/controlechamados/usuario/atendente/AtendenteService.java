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
    public AtendenteDTO save(MultipartFile file, AtendenteDTO atendenteDTO) {

        LOGGER.info("Salvando atendente");
        LOGGER.debug("Atendente: {}", atendenteDTO);

        Atendente atendente = new Atendente();
        atendente.setNome(atendenteDTO.getNome());
        atendente.setEmail(atendenteDTO.getEmail());
        atendente.setSenha(atendenteDTO.getSenha());

        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(atendenteDTO.getFoto() + ".png"));
            atendente.setFoto(atendenteDTO.getFoto());
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro no upload de imagem");
        }

        atendente = this.iAtendenteRepository.save(atendente);
        return atendenteDTO.of(atendente);
    }
}
