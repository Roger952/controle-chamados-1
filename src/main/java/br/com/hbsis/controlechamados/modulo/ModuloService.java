package br.com.hbsis.controlechamados.modulo;

import br.com.hbsis.controlechamados.produtos.ProdutoService;
import br.com.hbsis.controlechamados.usuario.atendente.AtendenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ModuloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtendenteService.class);

    private final IModuloRepositoty iModuloRepositoty;
    private final ProdutoService produtoService;

    public ModuloService(IModuloRepositoty iModuloRepositoty, ProdutoService produtoService) {
        this.iModuloRepositoty = iModuloRepositoty;
        this.produtoService = produtoService;
    }

    public ModuloDTO save(ModuloDTO moduloDTO) {

        LOGGER.info("Salvando os modelos");

        this.validate(moduloDTO);

        Modulo modulo = new Modulo();

        modulo.setNomeModulo(moduloDTO.getNomeModulo());
        modulo.setProduto(produtoService.findByIdProduto(moduloDTO.getIdProduto()));

        modulo = iModuloRepositoty.save(modulo);

        return ModuloDTO.of(modulo);
    }

    public void validate(ModuloDTO moduloDTO) {

        LOGGER.info("Validando os modelos");

        if (moduloDTO == null) {
            throw new IllegalArgumentException("A classe ModuloDTO esta vazia");
        }
        if (String.valueOf(moduloDTO.getIdProduto()).isEmpty()) {
            throw new IllegalArgumentException("O id do produto não pode ser vazio");
        } else if (!produtoService.existsById(moduloDTO.getIdProduto())) {
            throw new IllegalArgumentException("Não existe um produto com este Id");
        }
        if (moduloDTO.getNomeModulo().isEmpty()) {
            throw new IllegalArgumentException("O nome do Modulo não pode estar vazio");
        } else if (noNameAndProdutoEquals(moduloDTO)) {
            throw new IllegalArgumentException("Este produto já tem um modulo com este mesmo nome");
        } else if (moduloDTO.getNomeModulo().length() > 50) {
            throw new IllegalArgumentException("O numeros de caracteres permitidos foi exedido");
        }
    }

    private boolean noNameAndProdutoEquals(ModuloDTO moduloDTO) {
        return iModuloRepositoty.existsByNomeModuloAndProduto(moduloDTO.getNomeModulo(),
                produtoService.findByIdProduto(moduloDTO.getIdProduto()));
    }
}
