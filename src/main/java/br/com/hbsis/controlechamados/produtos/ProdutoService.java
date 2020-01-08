package br.com.hbsis.controlechamados.produtos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoService.class);
    private final IProdutoRepository iProdutoRepository;

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

        if (StringUtils.isEmpty(produtoDTO.getNome())) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio");
        }
        if (iProdutoRepository.existsByNome(produtoDTO.getNome())) {
            throw new IllegalArgumentException("Já existe um produto com este mesmo nome");
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
}
