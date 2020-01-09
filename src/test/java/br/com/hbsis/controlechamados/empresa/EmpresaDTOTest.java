package br.com.hbsis.controlechamados.empresa;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class EmpresaDTOTest {

    private static Validator validator;
    private EmpresaDTO empresaDTO;


    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void init() {
        this.empresaDTO = new EmpresaDTO(
                null,
                "Razão Social Teste",
                "Nome Fantasia Teste",
                "81875973000176",
                "0123456789",
                "hbsis@hbsis.com.br"
        );
    }

    @Test
    public void shouldHaveNoViolations() {
        Set<ConstraintViolation<EmpresaDTO>> violations = validator.validate(empresaDTO);
        Assertions.assertTrue(violations.isEmpty());

    }

    @Test
    public void razaoSocialNull() {
        this.empresaDTO.setRazaoSocial(null);

        Set<ConstraintViolation<EmpresaDTO>> constraintViolations =
                validator.validate(empresaDTO);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void NomeFantasialNull() {
        this.empresaDTO.setNomeFantasia(null);

        Set<ConstraintViolation<EmpresaDTO>> constraintViolations =
                validator.validate(empresaDTO);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void CnpjNull() {
        this.empresaDTO.setCnpj(null);

        Set<ConstraintViolation<EmpresaDTO>> constraintViolations =
                validator.validate(empresaDTO);

        assertEquals(2, constraintViolations.size());
    }

    @Test
    public void IeNull() {
        this.empresaDTO.setIe(null);

        Set<ConstraintViolation<EmpresaDTO>> constraintViolations =
                validator.validate(empresaDTO);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void EmailNull() {
        this.empresaDTO.setEmail(null);

        Set<ConstraintViolation<EmpresaDTO>> constraintViolations =
                validator.validate(empresaDTO);

        assertEquals(1, constraintViolations.size());
    }

}
