package br.com.hbsis.controlechamados.chamados;

import br.com.hbsis.controlechamados.chamados.arquivo.Arquivo;
import br.com.hbsis.controlechamados.chamados.arquivo.ArquivoDTO;
import br.com.hbsis.controlechamados.produtos.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        LOGGER.info("Cadastrando novo atendimento '{}'...", chamadosDTO.getTitulo());

        Chamados chamados = new Chamados();

        chamados.setDescricao(chamadosDTO.getDescricao());
        chamados.setDataHoraRegistro(new Date());
        chamados.setStatus(chamadosDTO.getStatus());
        chamados.setTitulo(chamadosDTO.getTitulo());
        chamados.setProduto(produtoService.findByIdProduto(chamadosDTO.getProdutoId()));
        chamados = iChamadosRepository.save(chamados);
        chamados.setArquivoList(parseArquivos(chamadosDTO.getArquivoDTOS(), chamados));

        for (Arquivo arquivo : chamados.getArquivoList()){

        }

        return ChamadosDTO.of(chamados);
    }

    public Chamados findById(Long id) {
        Optional<Chamados> chamadosOptional = iChamadosRepository.findById(id);
        if (chamadosOptional.isPresent()) {
            return chamadosOptional.get();
        }
        throw new IllegalArgumentException("ID não encontrado.");
    }

    public List<Arquivo> parseArquivos(List<ArquivoDTO> arquivoDTOS, Chamados chamados) {
        List<Arquivo> arquivoList = new ArrayList<>();
        for (ArquivoDTO arquivoDTO : arquivoDTOS){
            Arquivo arquivo = new Arquivo();
            arquivo.setArquivo(arquivoDTO.getArquivo());
            arquivo.setNomeArquivo(arquivoDTO.getNomeArquivo());
            arquivo.setIdChamados(chamados);
            arquivoList.add(arquivo);
        }
        return arquivoList;
    }

    public void saveMultipartFiles(List<MultipartFile> multipartFiles){

    }
}
