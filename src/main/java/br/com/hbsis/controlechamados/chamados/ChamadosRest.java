package br.com.hbsis.controlechamados.chamados;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/chamado")
public class ChamadosRest {
    private final ChamadosService chamadosService;
    private  final IChamadosRepository iChamadosRepository;

    @Autowired
    public ChamadosRest(IChamadosRepository iChamadosRepository, ChamadosService chamadosService) {
        this.iChamadosRepository = iChamadosRepository;
        this.chamadosService = chamadosService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_CADASTRAR_COLABORADOR')")
    public ChamadosDTO save(@RequestBody ChamadosDTO chamadosDTO){
        return this.chamadosService.save(chamadosDTO);
    }

    @GetMapping("/listar")
    @PreAuthorize("hasRole('ROLE_LISTAR_CHAMADOS')")
    public List<Chamados> listarTudo(){
        return this.iChamadosRepository.findAll();
    }

}
