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

    /**
     * MENSAGEM PADRÃO DE CAMPO EM BRANCO
     */
    private final String msgVazio = " não pode estar vazio!";

    public ColaboradorService(IColaboradorRepository iColaboradorRepository, EmpresaService empresaService) {
        this.iColaboradorRepository = iColaboradorRepository;
        this.empresaService = empresaService;
    }

    private void validate(ColaboradorDTO colaboradorDTO) {

        LOGGER.info("Validando colaborador...");

        if (colaboradorDTO == null) {
            throw new IllegalArgumentException("Colaborador DTO vazio.");
        }

        validarNome(colaboradorDTO.getNome());

        validarEmail(colaboradorDTO.getEmail());

        validarSenha(colaboradorDTO.getSenha());

        if (colaboradorDTO.getProdutoList() == null) {
            throw new IllegalArgumentException("Favor selecionar no mínimo um produto!");
        }

        if (colaboradorDTO.getEmpresa() == 0) {
            throw new IllegalArgumentException("Favor selecionar no mínimo uma empresa!");
        }

    }

    private void validarNome(String nome) {

        if (StringUtils.isBlank(nome)) {
            throw new IllegalArgumentException("Nome" + msgVazio);
        }

        if (nome.length() > 100) {
            throw new IllegalArgumentException("Nome deve conter no máximo 100 digitos!");
        }
    }

    private void validarEmail(String email) {

        if (StringUtils.isBlank(email)) {
            throw new IllegalArgumentException("E-mail" + msgVazio);
        }

        if (!ValidatorEmail.isValidEmail(email)) {
            throw new IllegalArgumentException("Padrão de e-mail inválido!");
        }

        if (email.length() > 100) {
            throw new IllegalArgumentException("E-mail deve conter no máximo 100 digitos!");
        }
    }

    private void validarExistenciaEmail(String email) {

        if (this.iColaboradorRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }
    }

    private void validarSenha(String senha) {

        if (StringUtils.isBlank(senha)) {
            throw new IllegalArgumentException("Senha" + msgVazio);
        }

        if (senha.length() < 8) {
            throw new IllegalArgumentException("Senha deve ter no mínimo 8 digitos.");
        }

        if (senha.length() > 100) {
            throw new IllegalArgumentException("Senha deve conter no máximo 100 digitos!");
        }
    }

    private Empresa findEmpresa(Long id) {
        Optional<Empresa> empresaOptional = this.empresaService.findByIdOptional(id);

        return empresaOptional.get();
    }

    private Colaborador findColaboradorExistente(Long id) {
        Optional<Colaborador> colaboradorOptional = this.iColaboradorRepository.findById(id);

        if (colaboradorOptional.isPresent()) {
            return colaboradorOptional.get();
        }

        throw new IllegalArgumentException("Colaborador não encontrado.");
    }

    public ColaboradorDTO save(ColaboradorDTO colaboradorDTO) {

        validate(colaboradorDTO);
        validarExistenciaEmail(colaboradorDTO.getEmail());

        LOGGER.info("Cadastrando novo colaborador '{}'...", colaboradorDTO.getEmail());

        Colaborador colaborador = new Colaborador(
                colaboradorDTO.getNome(),
                colaboradorDTO.getEmail(),
                colaboradorDTO.getSenha(),
                findEmpresa(colaboradorDTO.getEmpresa()),
                colaboradorDTO.getProdutoList()
        );

        colaborador = this.iColaboradorRepository.save(colaborador);

        return ColaboradorDTO.of(colaborador);
    }

    public ColaboradorDTO update(ColaboradorDTO colaboradorDTO) {
        validate(colaboradorDTO);

        Colaborador colaboradorAtualizado = this.findColaboradorExistente(colaboradorDTO.getId());

        colaboradorAtualizado.setEmail(colaboradorDTO.getEmail());
        colaboradorAtualizado.setSenha(colaboradorDTO.getSenha());
        colaboradorAtualizado.setEmpresa(this.findEmpresa(colaboradorDTO.getEmpresa()));
        colaboradorAtualizado.setProdutoList(colaboradorDTO.getProdutoList());

        colaboradorAtualizado = this.iColaboradorRepository.save(colaboradorAtualizado);

        return ColaboradorDTO.of(colaboradorAtualizado);
    }

    public List<Colaborador> findAll() {
        return this.iColaboradorRepository.findAll();
    }

    public List<Colaborador> LikeAs(String nome) {
        return iColaboradorRepository.findByNomeContaining(nome);
    }

    public ColaboradorDTO findById(Long id) {
        Optional<Colaborador> colaboradorOptional = iColaboradorRepository.findById(id);

        if (colaboradorOptional.isPresent()) {
            LOGGER.info("Colaborador Eencontrado");

            return ColaboradorDTO.of(colaboradorOptional.get());
        }

        throw new IllegalArgumentException("Não foi encontrado nenhum colaborador com o Id ..." + id);
    }
}
