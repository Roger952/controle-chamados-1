package br.com.hbsis.controlechamados.chamados;

import br.com.hbsis.controlechamados.chamados.arquivo.Arquivo;
import br.com.hbsis.controlechamados.chamados.arquivo.ArquivoDTO;
import br.com.hbsis.controlechamados.chamados.arquivo.ArquivoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChamadosService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChamadosService.class);
    private final IChamadosRepository iChamadosRepository;
    private final ArquivoService arquivoService;


    public ChamadosService(IChamadosRepository iChamadosRepository, @Lazy ArquivoService arquivoService) {
        this.iChamadosRepository = iChamadosRepository;
        this.arquivoService = arquivoService;
    }

    public ChamadosDTO save(ChamadosDTO chamadosDTO, List<MultipartFile> multipartFile) throws IOException {
        this.validate(chamadosDTO);

        LOGGER.info("Cadastrando novo atendimento '{}'...", chamadosDTO.getTitulo());

        chamadosDTO.setMultipartFileList(formattedMultipartFile(multipartFile));

        Chamados chamados = new Chamados();
        chamados.setProdutoList(chamadosDTO.getProdutoList());
        chamados.setTitulo(chamadosDTO.getTitulo());
        chamados.setDescricao(chamadosDTO.getDescricao());
        chamados.setMultipartFileList(chamadosDTO.getMultipartFileList());
        chamados.setStatus("PENDENTE");
        chamados.setDataHoraRegistro(LocalDateTime.now());


        LOGGER.info("Executando save Chamados!");
        chamados = this.iChamadosRepository.save(chamados);

        LOGGER.info("Finalizando save do Chamados!");
        return ChamadosDTO.of(chamados);
    }

    private List<Arquivo> formattedMultipartFile(List<MultipartFile> multipartFileList) throws IOException {

        List<Arquivo> arquivoList = new ArrayList<>();

        for (MultipartFile multipartFile  : multipartFileList) {
            ArquivoDTO arquivoDTO = new ArquivoDTO();

            arquivoDTO.setArquivo(multipartFile.getBytes());
            arquivoDTO.setNomeArquivo(multipartFile.getOriginalFilename());

            arquivoList.add(parseArquivo(arquivoService.save(arquivoDTO)));

        }

        return arquivoList;
    }

    private Arquivo parseArquivo(ArquivoDTO arquivoDTO) {
        Arquivo arquivo = new Arquivo();
        arquivo.setArquivo(arquivoDTO.getArquivo());
        arquivo.setNomeArquivo(arquivoDTO.getNomeArquivo());
        return arquivo;
    }

    private void validate(ChamadosDTO chamadosDTO){
        LOGGER.info("Validando novo chamado...");

        if(StringUtils.isBlank(chamadosDTO.getTitulo())){
            throw new IllegalArgumentException("Título não pode estar vazio.");
        }

        if(chamadosDTO.getTitulo().length() > 200){
            throw new IllegalArgumentException("Título excedeu o limite de caracteres.");
        }

        if(StringUtils.isBlank(chamadosDTO.getDescricao())){
            throw new IllegalArgumentException("Descrição não pode estar vazia.");
        }
    }


}