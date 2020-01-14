package br.com.hbsis.controlechamados.atendenteproduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendenteProdutoService {

    private final IAtendenteProdutoRepository iAtendenteProdutoRepository;

    @Autowired /** CONSTRUTOR */
    public AtendenteProdutoService(IAtendenteProdutoRepository iAtendenteProdutoRepository) {
        this.iAtendenteProdutoRepository = iAtendenteProdutoRepository;
    }

    /** MÉTODO AUXILIAR DE ACESSO A REPOSITORY */
    public void saveNaRepository(AtendenteProduto atendenteProduto) {
        this.iAtendenteProdutoRepository.save(atendenteProduto);
    }
}
