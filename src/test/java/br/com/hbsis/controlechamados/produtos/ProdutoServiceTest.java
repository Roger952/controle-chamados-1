package br.com.hbsis.controlechamados.produtos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private IProdutoRepository iProdutoRepository;

    @Captor
    private ArgumentCaptor<Produto> argumentCaptor;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    public void save() {
        ProdutoDTO produtoDTO = new ProdutoDTO("MeuProdutoTest");

        Produto produtomock = Mockito.mock(Produto.class);

        when(produtomock.getNome()).thenReturn(produtoDTO.getNome());

        when(this.iProdutoRepository.save(any())).thenReturn(produtomock);

        this.produtoService.save(produtoDTO);

        verify(this.iProdutoRepository, times(1)).save(this.argumentCaptor.capture());
        Produto createdUsuario = argumentCaptor.getValue();

        assertTrue("O nome do produto não deve ser nulo", !createdUsuario.getNome().isEmpty());
    }
}
