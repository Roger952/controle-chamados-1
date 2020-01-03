package br.com.hbsis.controlechamados.usuario.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;

@Service
    public class AdminService {
        private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
        private final IAdminRepository iAdminRepository;

        public AdminService(IAdminRepository iAdminRepository) {
            this.iAdminRepository = iAdminRepository;

        }

        public Admin loginAdmin(AdminDTO adminDTO) {
            LOGGER.info("Logando admin");

            Admin admin = new Admin();
            validate(adminDTO);

            return admin;
        }

        private void validate(AdminDTO adminDTO) {
            LOGGER.info("Validando o Admin");

            if (StringUtils.isEmpty(adminDTO.getLogin())) {
                throw new IllegalArgumentException("O login do Admin  não pode ser vazio/nulo");
            }

            if (StringUtils.isEmpty(adminDTO.getSenha())) {
                throw new IllegalArgumentException("A senha do admin não pode ser vazia/nulo");
            }

        }


    public List<Admin> listar() {

        List<Admin> admin;
        admin = this.iAdminRepository.findAll();

        return admin;
    }


    public AdminDTO findById(Long id) {
        Optional<Admin> adminOptional = this.iAdminRepository.findById(id);
        if (adminOptional.isPresent()) {
            return AdminDTO.of(adminOptional.get());
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public AdminDTO update(AdminDTO adminDTO, Long id) {
        Optional<Admin> adminOptional = this.iAdminRepository.findById(id);

        if (adminOptional.isPresent()) {
            Admin adminExistente = adminOptional.get();

            validate(adminDTO);

            LOGGER.info("Atualizando a linha... id:{}", adminExistente.getId());
            LOGGER.debug("Payload: {}", adminDTO);
            LOGGER.debug("Linha existente: {}", adminExistente);

            adminExistente.setSenha(adminDTO.getSenha());
            adminExistente.setLogin(adminDTO.getLogin());

            return adminDTO.of(adminExistente);
        }
        throw new IllegalArgumentException(String.format("ID %S NAO EXISTE " ,  id));
    }

        public void delete (Long id){
            LOGGER.info("Executando delete para adim de ID [{}]", id);
            this.iAdminRepository.deleteById(id);
        }
    }