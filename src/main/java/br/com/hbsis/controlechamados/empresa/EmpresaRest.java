package br.com.hbsis.controlechamados.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/empresa")
public class EmpresaRest {

    private final EmpresaService empresaService;
    private final IEmpresaRepository iEmpresaRepository;

    @Autowired
    public EmpresaRest(EmpresaService empresaService, IEmpresaRepository iEmpresaRepository) {
        this.empresaService = empresaService;
        this.iEmpresaRepository = iEmpresaRepository;
    }

    @PostMapping("/save")
    public EmpresaDTO save(@Valid @RequestBody EmpresaDTO empresaDTO) {
        return this.empresaService.save(empresaDTO);
    }

    @GetMapping
    public List<Empresa> findAll() {
        return this.iEmpresaRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.empresaService.delete(id);
    }
}

