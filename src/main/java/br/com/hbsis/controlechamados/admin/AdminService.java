package br.com.hbsis.controlechamados.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
    private final IAdminRepository iAdminRepository;

    @Autowired
    public AdminService(IAdminRepository iAdminRepository) {
        this.iAdminRepository = iAdminRepository;
    }

    public void saveNaRepositoryAdmin(Admin admin){
        this.iAdminRepository.save(admin);
    }
}
