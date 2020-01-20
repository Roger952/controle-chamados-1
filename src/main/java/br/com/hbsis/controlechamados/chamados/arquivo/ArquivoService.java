package br.com.hbsis.controlechamados.chamados.arquivo;


import br.com.hbsis.controlechamados.chamados.ChamadosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArquivoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArquivoService.class);
    private final IArquivoRepository iArquivoRepository;
    private final ChamadosService chamadosService;

    public ArquivoService(IArquivoRepository iArquivoRepository, ChamadosService chamadosService)  {
        this.iArquivoRepository = iArquivoRepository;
        this.chamadosService = chamadosService;
    }

    public ArquivoDTO save(ArquivoDTO arquivoDTO) {
        LOGGER.info("Cadastrando novo arquivo '{}'...", arquivoDTO.getArquivo());

        Arquivo arquivo = new Arquivo();

        arquivo.setNomeArquivo(arquivoDTO.getNomeArquivo());
        arquivo.setArquivo(arquivoDTO.getArquivo());
        arquivo.setIdChamados(chamadosService.findById(arquivoDTO.getIdChamados()));

        arquivo = iArquivoRepository.save(arquivo);
        return ArquivoDTO.of(arquivo);
    }

    public Arquivo findById(Long id){
        Optional<Arquivo> optionalArquivo = iArquivoRepository.findById(id);
        if (optionalArquivo.isPresent()){
            return optionalArquivo.get();
        }
        throw new IllegalArgumentException("Não foi possivel fazer a ");
    }

}
