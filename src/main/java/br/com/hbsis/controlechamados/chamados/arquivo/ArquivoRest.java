package br.com.hbsis.controlechamados.chamados.arquivo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/arquivos")
public class ArquivoRest {

    private final ArquivoService arquivoService;

    public ArquivoRest(ArquivoService arquivoService) {
        this.arquivoService = arquivoService;
    }

    @PostMapping("/save/{id}")
    @PreAuthorize("hasRole('ROLE_CADASTRAR_ARQUIVOS')")
    public List<Arquivo> save(@PathVariable("id") Long id,@RequestParam("file") List<MultipartFile> multipartFile) throws IOException {
        return arquivoService.formattedMultipartFile(multipartFile, id);
    }
}
