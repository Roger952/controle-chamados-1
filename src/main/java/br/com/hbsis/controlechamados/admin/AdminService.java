package br.com.hbsis.controlechamados.admin;

import br.com.hbsis.controlechamados.colaborador.Colaborador;
import br.com.hbsis.controlechamados.colaborador.ColaboradorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
    private final IAdminRepository iAdminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(IAdminRepository iAdminRepository, PasswordEncoder passwordEncoder) {
        this.iAdminRepository = iAdminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveNaRepositoryAdmin(Admin admin){
        this.iAdminRepository.save(admin);
    }

    public AdminDTO update(String login, Colaborador colaborador){

        this.validateLogin(colaborador.getEmail());

        LOGGER.info("Atualizando usuário do tipo colaborador! Login: [{}]", login);

        Admin adminExistente = iAdminRepository.findByLogin(login).get();

        adminExistente.setLogin(colaborador.getEmail());
        adminExistente.setSenha(colaborador.getSenha());

        adminExistente = this.iAdminRepository.save(adminExistente);
        return AdminDTO.of(adminExistente);
    }

    public boolean validateLogin(String email) {

        if(iAdminRepository.existsByLogin(email)){
            return true;
        }
        return false;
    }
}
