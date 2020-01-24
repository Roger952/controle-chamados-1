package br.com.hbsis.controlechamados.produtos;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoService.class);
    private final IProdutoRepository iProdutoRepository;

    private final String msgVazio = " não pode ser vazio!";

    @Autowired
    public ProdutoService(IProdutoRepository iProdutoRepository) {
        this.iProdutoRepository = iProdutoRepository;
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {

        Produto produto = new Produto();

        LOGGER.info("Salvando o Produto");

        this.validate(produtoDTO);

        produto.setNome(produtoDTO.getNome());

        produto = iProdutoRepository.save(produto);

        return ProdutoDTO.of(produto);
    }

    private void validate(ProdutoDTO produtoDTO) {
        LOGGER.info("Validando os Produtos");

        /** MENSAGENS DE RETORNO AO USUÁRIO */
        if (StringUtils.isBlank(produtoDTO.getNome())) {
            throw new IllegalArgumentException("Nome"+msgVazio);
        }

        if(produtoDTO.getNome().length() > 100){
            throw new IllegalArgumentException("Nome deve conter no máximo 100 digitos!");
        }

        if (iProdutoRepository.existsByNome(produtoDTO.getNome())) {
            throw new IllegalArgumentException("Produto já cadastrado!");
        }
    }

    public List<Produto> findAll() {
        LOGGER.info("Listando os Produtos");

        return iProdutoRepository.findAll();
    }

    public void deleteById(Long id) {
        LOGGER.info("Deletando o produto ... " + id);

        iProdutoRepository.deleteById(id);
    }

    public ProdutoDTO update(ProdutoDTO produtoDTO, Long id) {
        LOGGER.info("Atualizando os dados do produto ..." + id);

        Optional<Produto> optionalProduto = iProdutoRepository.findById(id);

        this.validate(produtoDTO);

        if (optionalProduto.isPresent()) {

            Produto produto = optionalProduto.get();

            produto.setNome(produtoDTO.getNome());

            produto = iProdutoRepository.save(produto);

            return ProdutoDTO.of(produto);
        }
        throw new IllegalArgumentException("O id deste Produto não existe");
    }

    public ProdutoDTO findById (Long id){
        LOGGER.info("Procurando o Produto..." +  id);

        Optional<Produto> optionalProduto = iProdutoRepository.findById(id);

        if (optionalProduto.isPresent()){
            return ProdutoDTO.of(optionalProduto.get());
        }

        throw new IllegalArgumentException("Não existe um produto com este id");
    }

    public Produto converterObjeto(ProdutoDTO produtoDTO) {

        Produto produto = new Produto();
        produto.setId(produtoDTO.getId());
        return produto;
    }

    public boolean existsByNome(String nome){
        return iProdutoRepository.existsByNome(nome);
    }

    public Produto findByNome(String nome) {
        if (iProdutoRepository.existsByNome(nome)) {
            return iProdutoRepository.findByNome(nome);
        }
        throw new IllegalArgumentException("Este nome não esta cadatrado no Banco de Dados");
    }
}
