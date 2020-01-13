package br.com.hbsis.controlechamados.modulo;

import br.com.hbsis.controlechamados.produtos.ProdutoRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/modulos")
public class ModuloRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoRest.class);

    private final ModuloService moduloService;

    public ModuloRest(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @PostMapping("/import/{nome}")
    public void save(@PathVariable("nome") String nomeProduto, @RequestParam("file")MultipartFile multipartFile) throws Exception {
        moduloService.saveImports(multipartFile, nomeProduto);
    }
}