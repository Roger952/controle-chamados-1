package br.com.hbsis.controlechamados.usuario.atendente;

import br.com.hbsis.controlechamados.atendenteproduto.AtendenteProduto;
import br.com.hbsis.controlechamados.atendenteproduto.AtendenteProdutoDTO;
import br.com.hbsis.controlechamados.atendenteproduto.AtendenteProdutoService;
import br.com.hbsis.controlechamados.produtos.Produto;
import br.com.hbsis.controlechamados.produtos.ProdutoDTO;
import br.com.hbsis.controlechamados.produtos.ProdutoService;
import br.com.hbsis.controlechamados.storage.Disco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AtendenteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtendenteService.class);
    private final IAtendenteRepository iAtendenteRepository;
    private final AtendenteProdutoService atendenteProdutoService;
    private final ProdutoService produtoService;
    private final Disco disco;

    @Autowired /** CONSTRUTOR */
    public AtendenteService(IAtendenteRepository iAtendenteRepository, AtendenteProdutoService atendenteProdutoService, ProdutoService produtoService, Disco disco) {
        this.iAtendenteRepository = iAtendenteRepository;
        this.atendenteProdutoService = atendenteProdutoService;
        this.produtoService = produtoService;
        this.disco = disco;
    }

    /** MÉTODOS DE CRUD */
    public AtendenteDTO save(MultipartFile file, AtendenteDTO atendenteDTO) {

        LOGGER.info("Salvando atendente");
        LOGGER.debug("Atendente... nome: {} e email: {}", atendenteDTO.getNome(), atendenteDTO.getEmail());

        Atendente atendente = new Atendente();
        atendente.setNome(atendenteDTO.getNome());
        atendente.setEmail(atendenteDTO.getEmail());
        atendente.setSenha(atendenteDTO.getSenha());
        atendente.setFoto(file.getOriginalFilename());

        /** LISTA DE ITENS */
        atendente.setAtendenteProdutoList(saveAtendenteProduto(atendenteDTO.getAtendenteProdutoDTOList(), atendente));

        atendente.setFoto(file.getOriginalFilename());

        atendente = this.iAtendenteRepository.save(atendente);

        /** EXECUTANDO UPLOAD DE IMAGEM */
        disco.salvarFoto(file);

        LOGGER.info("Finalizando save do atendente! DEU BOM");
        return AtendenteDTO.of(atendente);
    }

    public List<AtendenteProduto> saveAtendenteProduto(List<AtendenteProdutoDTO> atendenteProdutoDTOList, Atendente atendente){

        LOGGER.info("Salvando Atendente Produto");
        List<AtendenteProduto> atendenteProdutosList = new ArrayList<>();

        for (AtendenteProdutoDTO atendenteProdutoDTO : atendenteProdutoDTOList){

            ProdutoDTO produtoDTO = produtoService.findById(atendenteProdutoDTO.getIdProduto());
            Produto produto = produtoService.converterObjeto(produtoDTO);

            AtendenteDTO atendenteDTO = findById(atendenteProdutoDTO.getIdAtendente());
            Atendente atendente1 = converterObjeto(atendenteDTO);

            AtendenteProduto atendenteProduto = new AtendenteProduto(atendente1, produto);
            atendenteProdutosList.add(atendenteProduto);
        }
        return this.atendenteProdutoService.saveAllNaRepository(atendenteProdutosList);
    }

    private Atendente converterObjeto(AtendenteDTO atendenteDTO) {

        Atendente atendente = new Atendente();
        atendente.setId(atendenteDTO.getId());
        return atendente;
    }

    public AtendenteDTO findById(Long id){

        Optional<Atendente> atendenteOptional = this.iAtendenteRepository.findById(id);

        if (atendenteOptional.isPresent()){

            Atendente atendente = atendenteOptional.get();
            AtendenteDTO atendenteDTO = AtendenteDTO.of(atendente);
            return atendenteDTO;
        }
        throw new IllegalArgumentException(String.format("Id %s de atendente não existe", id));
    }

}
