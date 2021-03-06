package br.com.hbsis.controlechamados.produtos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/produtos")
public class ProdutoRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoRest.class);

    private final ProdutoService produtoService;

    public ProdutoRest(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(produtoService.findAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/save")
    public ProdutoDTO save(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.save(produtoDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        produtoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ProdutoDTO produtoDTO, @PathVariable Long id) {
        produtoService.update(produtoDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(produtoService.findById(id), HttpStatus.OK);
    }
}
