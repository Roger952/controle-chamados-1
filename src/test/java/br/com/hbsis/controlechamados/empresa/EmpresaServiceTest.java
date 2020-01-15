package br.com.hbsis.controlechamados.empresa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceTest {

    @Mock
    private IEmpresaRepository iEmpresaRepository;

    @InjectMocks
    private EmpresaService empresaService;

    @Test
    public void saveEmpresa() {
        EmpresaDTO empresaDTO = new EmpresaDTO(
                null,
                "Razão Social Teste",
                "Nome Fantasia Teste",
                "81875973000176",
                "0123456789",
                "hbsis@hbsis.com.br"
        );
        Empresa empresaEntityMock = mock(Empresa.class);

        when(empresaEntityMock.getRazaoSocial()).thenReturn(empresaDTO.getRazaoSocial());
        when(empresaEntityMock.getNomeFantasia()).thenReturn(empresaDTO.getNomeFantasia());
        when(empresaEntityMock.getCnpj()).thenReturn(empresaDTO.getCnpj());
        when(empresaEntityMock.getIe()).thenReturn(empresaDTO.getIe());
        when(empresaEntityMock.getEmail()).thenReturn(empresaDTO.getEmail());

        when(this.iEmpresaRepository.save(any())).thenReturn(empresaEntityMock);

        Empresa empresa = empresaService.saveEntity(empresaDTO);

        verify(this.iEmpresaRepository, times(1)).save(any());

        assertEquals(empresaDTO.getRazaoSocial(), empresa.getRazaoSocial());
        assertEquals(empresaDTO.getNomeFantasia(), empresa.getNomeFantasia());
        assertEquals(empresaDTO.getCnpj(), empresa.getCnpj());
        assertEquals(empresaDTO.getIe(), empresa.getIe());
        assertEquals(empresaDTO.getEmail(), empresa.getEmail());

        assertEquals(empresaEntityMock, empresa);
    }

    @Test
    public void empresaDTONull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.empresaService.save(null);
        });
    }

    @Test
    public void testFindById() {
    }

    @Test
    public void deleteEmpresa(){
    }

}