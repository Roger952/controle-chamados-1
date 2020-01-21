package br.com.hbsis.controlechamados.chamados.arquivo;


import br.com.hbsis.controlechamados.chamados.ChamadosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ArquivoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArquivoService.class);
    private final IArquivoRepository iArquivoRepository;
    private final ChamadosService chamadosService;

    public ArquivoService(IArquivoRepository iArquivoRepository, ChamadosService chamadosService) {
        this.iArquivoRepository = iArquivoRepository;
        this.chamadosService = chamadosService;
    }

    public ArquivoDTO save(ArquivoDTO arquivoDTO, MultipartFile multipartFile) throws IOException {
        LOGGER.info("Cadastrando novos arquivos...");
        this.validarArquivos(multipartFile);

        Arquivo arquivo = new Arquivo();

        arquivo.setNomeArquivo(multipartFile.getOriginalFilename());
        arquivo.setArquivo(multipartFile.getBytes());


        arquivo = iArquivoRepository.save(arquivo);
        return ArquivoDTO.of(arquivo);
    }


    private void validarArquivos(MultipartFile multipartFile) {
        if (multipartFile.getSize() > 1000 * 1000 * 2) {
            throw new IllegalArgumentException("Arquivo excedeu o limite de 2MB");
        }

    }
}
