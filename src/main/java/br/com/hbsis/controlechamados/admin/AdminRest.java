/*

package br.com.hbsis.controlechamados.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRest.class);
    private final AdminService adminService;

    @Autowired
    public AdminRest(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/listar")
    public List<Admin> listar() {
        LOGGER.info("Gerando lista de Linhas...");

        return this.adminService.listar();
    }

    @GetMapping("/{id}")
    public AdminDTO find(@PathParam("id") Long idLinha) {

        LOGGER.info("Recebendo find by ID...[{}]", idLinha);

        return this.adminService.findById(idLinha);
    }

    @PutMapping("/{id}")
    public AdminDTO update(@PathVariable("id") Long id, @RequestBody AdminDTO adminDTO) {
        LOGGER.info("Recebendo update para Linha Categoria de ID: {}", id);
        LOGGER.debug("Payload: {}", adminDTO);

        return this.adminService.update(adminDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para Linha Categoria de ID: {}", id);

        this.adminService.delete(id);
    }
}
*/
