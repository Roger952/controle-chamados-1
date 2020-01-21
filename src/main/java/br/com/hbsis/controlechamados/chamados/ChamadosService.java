package br.com.hbsis.controlechamados.chamados;

import br.com.hbsis.controlechamados.produtos.ProdutoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChamadosService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChamadosService.class);
    private final IChamadosRepository iChamadosRepository;
    private final ProdutoService produtoService;

    public ChamadosService(IChamadosRepository iChamadosRepository, ProdutoService produtoService) {
        this.iChamadosRepository = iChamadosRepository;
        this.produtoService = produtoService;
    }


    public ChamadosDTO save(ChamadosDTO chamadosDTO) {
        this.validate(chamadosDTO);

        LOGGER.info("Cadastrando novo atendimento '{}'...", chamadosDTO.getTitulo());

        Chamados chamados = new Chamados();

        chamados.setProduto(this.produtoService.findByIdProduto(chamadosDTO.getProdutoId()).get());
        chamados.setTitulo(chamadosDTO.getTitulo());
        chamados.setDescricao(chamadosDTO.getDescricao());
        chamados.setMultipartFileList(chamadosDTO.getMultipartFileList());
        chamados.setStatus("PENDENTE");
        chamados.setDataHoraRegistro(LocalDateTime.now());

        chamados = iChamadosRepository.save(chamados);

        return ChamadosDTO.of(chamados);
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
