package br.com.hbsis.controlechamados.colaborador;

import br.com.hbsis.controlechamados.admin.Admin;
import br.com.hbsis.controlechamados.admin.AdminService;
import br.com.hbsis.controlechamados.empresa.Empresa;
import br.com.hbsis.controlechamados.empresa.EmpresaService;
import br.com.hbsis.controlechamados.permissao.Permissao;
import br.com.hbsis.controlechamados.utils.email.ValidatorEmail;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ColaboradorService.class);
    private final IColaboradorRepository iColaboradorRepository;
    private final EmpresaService empresaService;
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    private final String msgVazio = " não pode estar vazio!";

    @Autowired
    public ColaboradorService(IColaboradorRepository iColaboradorRepository, EmpresaService empresaService, AdminService adminService, PasswordEncoder bCryptPasswordEncoder, PasswordEncoder passwordEncoder) {
        this.iColaboradorRepository = iColaboradorRepository;
        this.empresaService = empresaService;
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    private void validate(ColaboradorDTO colaboradorDTO) {

        LOGGER.info("Validando colaborador...");

        if (colaboradorDTO == null) {
            throw new IllegalArgumentException("Colaborador DTO vazio.");
        }

        validarNome(colaboradorDTO.getNome());

        validarEmail(colaboradorDTO.getEmail());

        validarSenha(colaboradorDTO);

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

        if(adminService.validateLogin(email)){
            throw new IllegalArgumentException("E-mail já cadastrado!");
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

    private void validarSenha(ColaboradorDTO colaboradorDTO) {

        if (StringUtils.isBlank(colaboradorDTO.getSenha())) {
            throw new IllegalArgumentException("Senha" + msgVazio);
        }

        if(colaboradorDTO.getSenha().length() > 30){
            throw new IllegalArgumentException("Senha deve conter no máximo 30 digitos!");
        }
        if(colaboradorDTO.getSenha().length() < 8){
            throw new IllegalArgumentException("Senha fraca: Possui menos de 8 digitos.");
        }

        if(StringUtils.containsOnly(colaboradorDTO.getSenha().toLowerCase(), "qwertyuiopasdfghjklzxcvbnm")){
            throw new IllegalArgumentException("Senha fraca: Possui apenas letras");
        }

        if(StringUtils.containsOnly(colaboradorDTO.getSenha(), "0123456789")){
            throw new IllegalArgumentException("Senha fraca: Possui apenas numeros.");
        }
        if(!StringUtils.containsAny(colaboradorDTO.getSenha().toLowerCase(), "qwertyuiopasdfghjklzxcvbnm0123456789")){
            throw new IllegalArgumentException("Senha fraca: Precisa conter letras e numeros.");
        }
        if(!StringUtils.containsAny(colaboradorDTO.getSenha(), "qwertyuiopasdfghjklzxcvbnm")){
            throw new IllegalArgumentException("Senha fraca: Precisa conter letras minusculas e maiusculas.");
        }
        if(!StringUtils.containsAny(colaboradorDTO.getSenha(), "QWERTYUIOPASDFGHJKLZXCVBNM")){
            throw new IllegalArgumentException("Senha fraca: Precisa conter letras maiusculas.");
        }
        if(!StringUtils.containsAny(colaboradorDTO.getSenha(), "0123456789")){
            throw new IllegalArgumentException("Senha fraca: Precisa conter numeros.");
        }
        if(!StringUtils.containsAny(colaboradorDTO.getSenha(), "$&+,:;=?@#|'<>.^*()%!-")){
            throw new IllegalArgumentException("Senha fraca: Senha precisa de caracteres especiais");
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

        Colaborador colaborador = new Colaborador();
        colaborador.setNome(colaboradorDTO.getNome());
        colaborador.setEmail(colaboradorDTO.getEmail());
        colaborador.setSenha(passwordEncoder.encode(colaboradorDTO.getSenha()));
        colaborador.setEmpresa(findEmpresa(colaboradorDTO.getEmpresa()));
        colaborador.setProdutoList(colaboradorDTO.getProdutoList());

        LOGGER.info("Executando save do colaborador!");
        colaborador = this.iColaboradorRepository.save(colaborador);

        this.executeListPermissioes(colaborador);

        LOGGER.info("Finalizando save do colaborador!");
        return ColaboradorDTO.of(colaborador);
    }

    public ColaboradorDTO update(ColaboradorDTO colaboradorDTO) {

        this.validate(colaboradorDTO);

        Colaborador colaboradorAtualizado = this.findColaboradorExistente(colaboradorDTO.getId());

        String email = colaboradorAtualizado.getEmail();

        colaboradorAtualizado.setNome(colaboradorDTO.getNome());
        colaboradorAtualizado.setEmail(colaboradorDTO.getEmail());
        colaboradorAtualizado.setSenha(passwordEncoder.encode(colaboradorDTO.getSenha()));
        colaboradorAtualizado.setEmpresa(this.findEmpresa(colaboradorDTO.getEmpresa()));
        colaboradorAtualizado.setProdutoList(colaboradorDTO.getProdutoList());

        colaboradorAtualizado = this.iColaboradorRepository.save(colaboradorAtualizado);

        adminService.update(email, colaboradorAtualizado);

        return ColaboradorDTO.of(colaboradorAtualizado);
    }

    public List<Colaborador> findAll() {
        return this.iColaboradorRepository.findAll();
    }

    public List<Colaborador> LikeAs(String nome) {
        return iColaboradorRepository.findByNomeContaining(nome);
    }

    public ColaboradorDTO findByEmail(String email){
        Optional<Colaborador> colaboradorOptional = iColaboradorRepository.findByEmail(email);

        return ColaboradorDTO.of(colaboradorOptional.get());
    }

    public ColaboradorDTO findById(Long id) {
        Optional<Colaborador> colaboradorOptional = iColaboradorRepository.findById(id);

        if (colaboradorOptional.isPresent()) {
            LOGGER.info("Colaborador Eencontrado");
            return ColaboradorDTO.of(colaboradorOptional.get());
        }
        throw new IllegalArgumentException("Nenhum colaborador encontrado... id: " + id);
    }

    public void executeListPermissioes(Colaborador colaborador){

        List<Permissao> permissaoList = new ArrayList<>();

        Permissao permissao = new Permissao();
        permissao.setId(Long.parseLong("10"));
        permissao.setDescricao("ROLE_LISTAR_COLABORADOR");

        permissaoList.add(permissao);


        Permissao permissao1 = new Permissao();
        permissao1.setId(Long.parseLong("4"));
        permissao1.setDescricao("ROLE_LISTAR_PRODUTO");

        permissaoList.add(permissao1);

        Permissao permissao2 = new Permissao();
        permissao2.setId(Long.parseLong("16"));
        permissao2.setDescricao("ROLE_CADASTRAR_CHAMADOS");

        permissaoList.add(permissao2);

        Permissao permissao3 = new Permissao();
        permissao3.setId(Long.parseLong("18"));
        permissao3.setDescricao("ROLE_LISTAR_CHAMADOS");

        permissaoList.add(permissao3);

        Permissao permissao4 = new Permissao();
        permissao4.setId(Long.parseLong("17"));
        permissao4.setDescricao("ROLE_CADASTER_FILES_CHAMADOS");

        permissaoList.add(permissao4);


        Admin admin = new Admin();
        admin.setLogin(colaborador.getEmail());
        admin.setSenha(colaborador.getSenha());
        admin.setPermissaoList(permissaoList);

        LOGGER.info("Executando save do admin...");
        this.adminService.saveNaRepositoryAdmin(admin);
    }
}