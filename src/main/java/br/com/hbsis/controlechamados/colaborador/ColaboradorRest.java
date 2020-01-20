package br.com.hbsis.controlechamados.colaborador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/colaborador")
public class ColaboradorRest {
    private final ColaboradorService colaboradorService;

    @Autowired
    public ColaboradorRest(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_CADASTRAR_COLABORADOR')")
    public ColaboradorDTO save(@RequestBody ColaboradorDTO colaboradorDTO){
        return this.colaboradorService.save(colaboradorDTO);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_EDITAR_COLABORADOR')")
    public ColaboradorDTO update(@RequestBody ColaboradorDTO colaboradorDTO){
        return this.colaboradorService.update(colaboradorDTO);
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_LISTAR_COLABORADOR')")
    public List<Colaborador> findAll(){
        return this.colaboradorService.findAll();
    }

    @GetMapping("/findByNome/{nome}")
    @PreAuthorize("hasRole('ROLE_LIST_LIKE_NOME_COLABORADOR')")
    public  List<Colaborador> findByLikeNome(@PathVariable("nome") String nome){
        return this.colaboradorService.LikeAs(nome);
    }

}
