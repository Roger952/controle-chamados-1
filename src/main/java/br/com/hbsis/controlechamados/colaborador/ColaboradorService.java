package br.com.hbsis.controlechamados.colaborador;

import br.com.hbsis.controlechamados.empresa.Empresa;
import br.com.hbsis.controlechamados.empresa.EmpresaService;
import br.com.hbsis.controlechamados.utils.email.ValidatorEmail;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ColaboradorService.class);
    private final IColaboradorRepository iColaboradorRepository;
    private final EmpresaService empresaService;

    public ColaboradorService(IColaboradorRepository iColaboradorRepository, EmpresaService empresaService) {
        this.iColaboradorRepository = iColaboradorRepository;
        this.empresaService = empresaService;
    }

    private void validate(ColaboradorDTO colaboradorDTO){
        LOGGER.info("Validando colaborador...");
        LOGGER.info("colaborador" + colaboradorDTO);
        if(colaboradorDTO == null){
            throw new IllegalArgumentException("Colaborador DTO vazio.");
        }

        validarNome(colaboradorDTO.getNome());

        validarEmail(colaboradorDTO.getEmail());

        validarSenha(colaboradorDTO.getSenha());

        if(colaboradorDTO.getEmpresa() == null){
            throw new IllegalArgumentException("Uma empresa precisa ser selecionada!");
        }

        if(colaboradorDTO.getProdutoList() == null){
            throw new IllegalArgumentException("Favor selecionar no mínimo um produto!");
        }
    }

    private void validarNome(String nome) {
        if(StringUtils.isBlank(nome)){
            throw new IllegalArgumentException("O campo nome não pode estar vazio!");
        }

        if(nome.length() > 100){
            throw new IllegalArgumentException("O nome não pode ser maior que 100 caracteres.");
        }
    }

    private void validarEmail(String email) {
        if(StringUtils.isBlank(email)){
            throw new IllegalArgumentException("O campo e-mail não pode estar vazio!");
        }

        if(!ValidatorEmail.isValidEmail(email)){
            throw new IllegalArgumentException("Padrão de e-mail inválido!");
        }

        if(email.length() > 100){
            throw new IllegalArgumentException("O e-mail não pode ser maior que 100 caracteres.");
        }
    }

    private void validarExistenciaEmail(String email) {
        if(this.iColaboradorRepository.existsByEmail(email)){
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }
    }

    private void validarSenha(String senha) {
        if(StringUtils.isBlank(senha)){
            throw new IllegalArgumentException("O campo senha não pode estar vazio!");
        }

        if(senha.length() < 8){
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.");
        }

        if(senha.length() > 100){
            throw new IllegalArgumentException("A senha não pode ser maior que 100 caracteres.");
        }
    }

    private Empresa findEmpresa(Long id){
        Optional<Empresa> empresaOptional = this.empresaService.findByIdOptional(id);

        return empresaOptional.get();
    }

    private Colaborador findColaboradorExistente(Long id){
        Optional<Colaborador> colaboradorOptional = this.iColaboradorRepository.findById(id);

        if(colaboradorOptional.isPresent()){
            return colaboradorOptional.get();
        }

        throw new IllegalArgumentException("Colaborador não encontrado.");
    }

    public ColaboradorDTO save(ColaboradorDTO colaboradorDTO){

        LOGGER.info("ColaboradorEmpresaID" + colaboradorDTO.getEmpresa());
        LOGGER.info("email" + colaboradorDTO.getEmail());
        LOGGER.info("senha" + colaboradorDTO.getSenha());
        LOGGER.info("nome" + colaboradorDTO.getNome());
        LOGGER.info("list prod" + colaboradorDTO.getProdutoList());

        validate(colaboradorDTO);
        validarExistenciaEmail(colaboradorDTO.getEmail());

        LOGGER.info("Cadastrando novo colaborador '{}'...", colaboradorDTO.getEmail());

        Colaborador colaborador = new Colaborador(
                colaboradorDTO.getNome(),
                colaboradorDTO.getCodigo(),
                colaboradorDTO.getEmail(),
                colaboradorDTO.getSenha(),
                findEmpresa(colaboradorDTO.getEmpresa()),
                colaboradorDTO.getProdutoList()

        );

        colaborador = this.iColaboradorRepository.save(colaborador);

        return ColaboradorDTO.of(colaborador);
    }

    public ColaboradorDTO update(ColaboradorDTO colaboradorDTO){
        validate(colaboradorDTO);

        Colaborador colaboradorAtualizado = this.findColaboradorExistente(colaboradorDTO.getId());

        colaboradorAtualizado.setEmail(colaboradorDTO.getEmail());
        colaboradorAtualizado.setSenha(colaboradorDTO.getSenha());
        colaboradorAtualizado.setEmpresa(this.findEmpresa(colaboradorDTO.getEmpresa()));
        colaboradorAtualizado.setProdutoList(colaboradorDTO.getProdutoList());

        colaboradorAtualizado = this.iColaboradorRepository.save(colaboradorAtualizado);

        return ColaboradorDTO.of(colaboradorAtualizado);
    }

    public List<Colaborador> findAll(){
        return this.iColaboradorRepository.findAll();
    }
}
