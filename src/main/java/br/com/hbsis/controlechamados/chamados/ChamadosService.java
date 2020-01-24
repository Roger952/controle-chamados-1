package br.com.hbsis.controlechamados.chamados;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class ChamadosService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChamadosService.class);
    private final IChamadosRepository iChamadosRepository;


    public ChamadosService(IChamadosRepository iChamadosRepository) {
        this.iChamadosRepository = iChamadosRepository;
    }

    public ChamadosDTO save(ChamadosDTO chamadosDTO, MultipartFile multipartFile) {
        this.validate(chamadosDTO);

        LOGGER.info("Cadastrando novo atendimento '{}'...", chamadosDTO.getTitulo());

        Chamados chamados = new Chamados();
        chamados.setProdutoList(chamadosDTO.getProdutoList());
        chamados.setTitulo(chamadosDTO.getTitulo());
        chamados.setDescricao(chamadosDTO.getDescricao());
        chamados.setStatus("PENDENTE");
        chamados.setDataHoraRegistro(LocalDateTime.now());
        chamados.setMultipartFileList(chamadosDTO.getMultipartFileList());

        LOGGER.info("Executando save Chamados!");
        chamados = this.iChamadosRepository.save(chamados);

        LOGGER.info("Finalizando save do Chamados!");
        return ChamadosDTO.of(chamados);
    }

    private void validate(ChamadosDTO chamadosDTO){
        LOGGER.info("Validando novo chamado...");

        if(StringUtils.isBlank(chamadosDTO.getTitulo())){
            throw new IllegalArgumentException("Título não pode estar vazio.");
        }

        if(chamadosDTO.getTitulo().length() > 200){
            throw new IllegalArgumentException("Título excedeu o limite de 200 caracteres.");
        }

        if(StringUtils.isBlank(chamadosDTO.getDescricao())){
            throw new IllegalArgumentException("Descrição não pode estar vazia.");
        }
    }
}