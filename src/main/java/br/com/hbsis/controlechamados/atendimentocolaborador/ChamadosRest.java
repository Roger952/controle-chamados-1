package br.com.hbsis.controlechamados.atendimentocolaborador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/chamado")
public class ChamadosRest {
    private final ChamadosService chamadosService;

    @Autowired
    public ChamadosRest(ChamadosService chamadosService) { this.chamadosService = chamadosService; }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_CADASTRAR_COLABORADOR')")
    public ChamadosDTO save(@RequestBody ChamadosDTO chamadosDTO){
        return this.chamadosService.save(chamadosDTO);
    }



}
