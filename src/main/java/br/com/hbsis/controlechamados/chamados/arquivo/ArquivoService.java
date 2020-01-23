package br.com.hbsis.controlechamados.chamados.arquivo;


import br.com.hbsis.controlechamados.chamados.ChamadosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArquivoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArquivoService.class);
    private final IArquivoRepository iArquivoRepository;
    private final ChamadosService chamadosService;

    public ArquivoService(IArquivoRepository iArquivoRepository, ChamadosService chamadosService) {
        this.iArquivoRepository = iArquivoRepository;
        this.chamadosService = chamadosService;
    }

    public ArquivoDTO save(ArquivoDTO arquivoDTO){
        LOGGER.info("Cadastrando novos arquivos...");
        this.validarArquivos(arquivoDTO);

        Arquivo arquivo = new Arquivo();

        arquivo.setNomeArquivo(arquivoDTO.getNomeArquivo());
        arquivo.setArquivo(arquivoDTO.getArquivo());


        arquivo = iArquivoRepository.save(arquivo);
        return ArquivoDTO.of(arquivo);
    }


    private void validarArquivos(ArquivoDTO arquivoDTO) {

        if (arquivoDTO.getArquivo().length > 1000 * 1000 * 2) {
            throw new IllegalArgumentException("Arquivo excedeu o limite de 2MB");
        }

    }

    public List<Arquivo> formattedMultipartFile(List<MultipartFile> multipartFileList) throws IOException {

        List<Arquivo> arquivoList = new ArrayList<>();

        for (MultipartFile multipartFile  : multipartFileList) {
            ArquivoDTO arquivoDTO = new ArquivoDTO();

            arquivoDTO.setArquivo(multipartFile.getBytes());
            arquivoDTO.setNomeArquivo(multipartFile.getOriginalFilename());

            arquivoList.add(parseArquivo(save(arquivoDTO)));

        }

        return arquivoList;
    }

    private Arquivo parseArquivo(ArquivoDTO arquivoDTO) {
        Arquivo arquivo = new Arquivo();
        arquivo.setArquivo(arquivoDTO.getArquivo());
        arquivo.setNomeArquivo(arquivoDTO.getNomeArquivo());
        return arquivo;
    }

}