package br.com.hbsis.controlechamados.empresa;

import br.com.hbsis.controlechamados.utils.cnpj.ValidatorCNPJ;
import br.com.hbsis.controlechamados.utils.email.ValidatorEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

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
            throw new IllegalArgumentException("EmpresaDTO não pode estar nulo.");
        }

        validateCnpj(empresaDTO);

        validateEmail(empresaDTO);
    }

    private void validateEmail(EmpresaDTO empresaDTO) {
        if(StringUtils.isBlank(empresaDTO.getEmail())){
            throw new IllegalArgumentException("E-mail não pode estar vazio.");
        }

        if(!ValidatorEmail.isValidEmail(empresaDTO.getEmail())){
            throw new IllegalArgumentException("E-mail inválido.");
        }

        if(empresaDTO.getEmail().length() > 50){
            throw new IllegalArgumentException("E-mail com tamanho inválido.");
        }

        if(this.iEmpresaRepository.existsByEmail(empresaDTO.getEmail())){
            throw new IllegalArgumentException("E-mail já existente.");
        }
    }

    private void validateCnpj(EmpresaDTO empresaDTO) {
        if(StringUtils.isBlank(empresaDTO.getCnpj())){
            throw new IllegalArgumentException("CNPJ não pode estar vazio.");
        }

        if(empresaDTO.getCnpj().length() != 14){
            throw new IllegalArgumentException("CNPJ com tamanho inválido.");
        }

        if(this.iEmpresaRepository.existsByCnpj(empresaDTO.getCnpj())){
            throw new IllegalArgumentException("CNPJ já existente.");
        }

        if(!ValidatorCNPJ.isValidCNPJ(empresaDTO.getCnpj())){
            throw new IllegalArgumentException("CNPJ inválido.");
        }
    }

    public EmpresaDTO save(EmpresaDTO empresaDTO) {
        return EmpresaDTO.of(this.saveEntity(empresaDTO));
    }

    Empresa saveEntity(EmpresaDTO empresaDTO) {

        this.validate(empresaDTO);

        LOGGER.info("Salvando Empresa");
        LOGGER.debug("Payload: {}", empresaDTO);

        Empresa empresa = this.fromDto(empresaDTO, new Empresa());

        empresa = this.iEmpresaRepository.save(empresa);

        LOGGER.trace("Fornecedor Salvo {}", empresa);

        return empresa;
    }

    private Empresa fromDto(EmpresaDTO empresaDTO, Empresa empresa) {

        empresa.setRazaoSocial(empresaDTO.getRazaoSocial());
        empresa.setNomeFantasia(empresaDTO.getNomeFantasia());
        empresa.setCnpj(empresaDTO.getCnpj());
        empresa.setIe(empresaDTO.getIe());
        empresa.setEmail(empresaDTO.getEmail());

        return empresa;
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para empresa de ID: [{}]", id);
        this.iEmpresaRepository.deleteById(id);
    }
}
