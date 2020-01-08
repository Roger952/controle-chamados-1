package br.com.hbsis.controlechamados.empresa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private final IEmpresaRepository iEmpresaRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaService.class);

    @Autowired
    public EmpresaService(IEmpresaRepository iEmpresaRepository) {
        this.iEmpresaRepository = iEmpresaRepository;
    }

    private void validate(EmpresaDTO empresaDTO) {
        LOGGER.info("Validando Empresa {}", empresaDTO);

        if (empresaDTO == null) {
            throw new IllegalArgumentException("Categoria ProdutoDTO não deve ser nulo");
        }
    }

    public EmpresaDTO save(EmpresaDTO empresaDTO) {

        this.validate(empresaDTO);

        LOGGER.info("Salvando Empresa");
        LOGGER.debug("Empresa: {}", empresaDTO);

        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(empresaDTO.getRazaoSocial());
        empresa.setNomeFantasia(empresaDTO.getNomeFantasia());
        empresa.setCnpj(empresaDTO.getCnpj());
        empresa.setIe(empresaDTO.getIe());
        empresa.setEmail(empresaDTO.getEmail());

        empresa = this.iEmpresaRepository.save(empresa);

        return EmpresaDTO.of(empresa);

    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para empresa de ID: [{}]", id);
        this.iEmpresaRepository.deleteById(id);
    }
}
