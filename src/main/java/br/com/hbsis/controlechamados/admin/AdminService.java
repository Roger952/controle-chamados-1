
package br.com.hbsis.controlechamados.admin;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
    private final IAdminRepository iAdminRepository;

    public AdminService(IAdminRepository iAdminRepository) {
        this.iAdminRepository = iAdminRepository;

    }

    public AdminDTO update(AdminDTO adminDTO, Long id) {
        Optional<Admin> adminOptional = this.iAdminRepository.findById(id);

        if (adminOptional.isPresent()) {
            Admin adminExistente = adminOptional.get();

            LOGGER.info("Atualizando a linha... id:{}", adminExistente.getId());
            LOGGER.debug("Payload: {}", adminDTO);
            LOGGER.debug("Linha existente: {}", adminExistente);

            adminExistente.setSenha(adminDTO.getSenha());
            adminExistente.setLogin(adminDTO.getLogin());
            adminExistente.setRoles(adminDTO.getRole());

            return adminDTO.of(adminExistente);
        }
        throw new IllegalArgumentException(String.format("ID %S NAO EXISTE ", id));
    }

    public AdminDTO save(AdminDTO adminDTO){

        Admin admin = new Admin();
        admin.setLogin(adminDTO.getLogin());
        admin.setSenha(adminDTO.getSenha());
        admin.setRoles(adminDTO.getRole());

        admin = this.iAdminRepository.save(admin);

        return AdminDTO.of(admin);
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para adim de ID [{}]", id);
        this.iAdminRepository.deleteById(id);
    }
}
