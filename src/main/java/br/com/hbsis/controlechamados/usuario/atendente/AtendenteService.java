package br.com.hbsis.controlechamados.usuario.atendente;

import br.com.hbsis.controlechamados.atendenteproduto.AtendenteProduto;
import br.com.hbsis.controlechamados.atendenteproduto.AtendenteProdutoService;
import br.com.hbsis.controlechamados.produtos.Produto;
import br.com.hbsis.controlechamados.produtos.ProdutoService;
import br.com.hbsis.controlechamados.storage.Disco;
import br.com.hbsis.controlechamados.utils.email.ValidatorEmail;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
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

    /**
     * MENSAGEM PADRÃO DE CAMPO EM BRANCO
     */
    private final String msgVazio = " não pode estar vazio!";

    @Autowired
    /** CONSTRUTOR */
    public AtendenteService(IAtendenteRepository iAtendenteRepository, AtendenteProdutoService atendenteProdutoService, ProdutoService produtoService, Disco disco) {
        this.iAtendenteRepository = iAtendenteRepository;
        this.atendenteProdutoService = atendenteProdutoService;
        this.produtoService = produtoService;
        this.disco = disco;
    }

    /**
     * MÉTODOS DE CRUD
     */
    public AtendenteDTO save(AtendenteDTO atendenteDTO) {

        this.validate(atendenteDTO);

        LOGGER.info("Salvando atendente");
        LOGGER.debug("Atendente -> [{}]", atendenteDTO);

        Atendente atendente = new Atendente();
        atendente.setNome(atendenteDTO.getNome());
        atendente.setEmail(atendenteDTO.getEmail());
        atendente.setSenha(atendenteDTO.getSenha());
        atendente.setFoto(atendenteDTO.getFoto());
        atendente.setProdutoList(atendenteDTO.getProdutoList());

        LOGGER.info("Executando save do atendente!" + atendente);
        atendente = this.iAtendenteRepository.save(atendente);

        LOGGER.info("Finalizando save do atendente!");
        return AtendenteDTO.of(atendente);
    }

    public AtendenteDTO validate(AtendenteDTO atendenteDTO) {

        LOGGER.info("Validando atendente...");

        /** MENSAGENS DE RETORNO AO USUÁRIO */
        if (StringUtils.isBlank(atendenteDTO.getNome())) {
            throw new IllegalArgumentException("Nome" + msgVazio);
        }

        if (atendenteDTO.getNome().length() > 100) {
            throw new IllegalArgumentException("Nome deve conter no máximo 100 digitos!");
        }

        if (StringUtils.isBlank(atendenteDTO.getEmail())) {
            throw new IllegalArgumentException("E-mail" + msgVazio);
        }

        if (!ValidatorEmail.isValidEmail(atendenteDTO.getEmail())) {
            throw new IllegalArgumentException("Padrão de e-mail inválido!");
        }

        if (atendenteDTO.getEmail().length() > 100) {
            throw new IllegalArgumentException("E-mail deve conter no máximo 50 digitos!");
        }

        if (StringUtils.isBlank(atendenteDTO.getSenha())) {
            throw new IllegalArgumentException("Senha" + msgVazio);
        }

        if (atendenteDTO.getSenha().length() > 100) {
            throw new IllegalArgumentException("Senha deve conter no máximo 30 digitos!");
        }
        if (atendenteDTO.getProdutoList() == null) {
            throw new IllegalArgumentException("Favor selecionar no mínimo um produto!");
        }

        if (StringUtils.isBlank(atendenteDTO.getFoto())) {
            atendenteDTO.setFoto("default-person.png");
        }

        if (!(atendenteDTO.getSenha().equals(atendenteDTO.getSenha()))) {
            throw new IllegalArgumentException("As senhas não correspondem!");
        }

           return atendenteDTO;
    }
    /** MÉTODO DE FORMATAÇÃO GERAL */
    private Atendente converterObjeto(AtendenteDTO atendenteDTO) {

        Atendente atendente = new Atendente();
        atendente.setId(atendenteDTO.getId());
        return atendente;
    }

    public AtendenteDTO findById(Long id){

        Optional<Atendente> atendenteOptional = this.iAtendenteRepository.findById(id);

        if (atendenteOptional.isPresent()){

            Atendente atendente = atendenteOptional.get();
            return AtendenteDTO.of(atendente);
        }
        throw new IllegalArgumentException(String.format("Id %s de atendente não existe", id));
    }

    /** EXECUTAR FILE-UPLOAD NA CLASSE DISCO */
    public void salvarFoto(MultipartFile file) {
        disco.salvarFoto(file);
    }
}
