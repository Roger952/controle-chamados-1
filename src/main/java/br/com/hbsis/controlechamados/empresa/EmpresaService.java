package br.com.hbsis.controlechamados.empresa;

import br.com.hbsis.controlechamados.utils.cnpj.ValidatorCNPJ;
import br.com.hbsis.controlechamados.utils.email.ValidatorEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import java.util.Optional;

@Service
public class EmpresaService {

    private final IEmpresaRepository iEmpresaRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaService.class);

    private final String msgVazio = " não pode ser vazio!";

    @Autowired
    public EmpresaService(IEmpresaRepository iEmpresaRepository) {
        this.iEmpresaRepository = iEmpresaRepository;
    }

    private void validate(EmpresaDTO empresaDTO) {
        LOGGER.info("Validando Empresa {}", empresaDTO);

        /** MENSAGENS DE RETONO AO USUÁRIO */
        if(StringUtils.isBlank(empresaDTO.getRazaoSocial())){
            throw new IllegalArgumentException("Razão social"+msgVazio);
        }

        if(empresaDTO.getRazaoSocial().length() > 50){
            throw new IllegalArgumentException("Razão social deve conter no máximo 50 digitos!");
        }

        if(StringUtils.isBlank(empresaDTO.getNomeFantasia())){
            throw new IllegalArgumentException("Nome fantasia"+msgVazio);
        }

        if(empresaDTO.getNomeFantasia().length() > 50){
            throw new IllegalArgumentException("Nome fantasia deve conter no máximo 50 digitos!");
        }

        validateCnpj(empresaDTO);

        if(StringUtils.isBlank(empresaDTO.getIe())){
            throw new IllegalArgumentException("Incrição estadual"+msgVazio);
        }

        if(empresaDTO.getIe().length() > 10){
            throw new IllegalArgumentException("Inscrição estadual deve conter no máximo 10 digitos");
        }

        validateEmail(empresaDTO);
    }

    private void validateEmail(EmpresaDTO empresaDTO) {

        if(StringUtils.isBlank(empresaDTO.getEmail())){
            throw new IllegalArgumentException("E-mail"+msgVazio);
        }

        if(!ValidatorEmail.isValidEmail(empresaDTO.getEmail())){
            throw new IllegalArgumentException("Padrão de e-mail inválido!");
        }

        if(empresaDTO.getEmail().length() > 50){
            throw new IllegalArgumentException("E-mail deve conter no máximo 50 digitos!");
        }

        if(this.iEmpresaRepository.existsByEmail(empresaDTO.getEmail())){
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }
    }

    private void validateCnpj(EmpresaDTO empresaDTO) {

        if(StringUtils.isBlank(empresaDTO.getCnpj())){
            throw new IllegalArgumentException("CNPJ"+msgVazio);
        }

        if(!ValidatorCNPJ.isValidCNPJ(empresaDTO.getCnpj())){
            throw new IllegalArgumentException("CNPJ inválido.");
        }

        if(this.iEmpresaRepository.existsByCnpj(empresaDTO.getCnpj())){
            throw new IllegalArgumentException("CNPJ já cadastrado!");
        }
    }

    public Optional<Empresa> findByIdOptional(Long id){
        Optional<Empresa> empresaOptional = this.iEmpresaRepository.findById(id);

        if(empresaOptional.isPresent()){
            return empresaOptional;
        }

        throw new IllegalArgumentException(String.format("Empresa de ID: %s, não existe.", id));
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
