package br.com.hbsis.controlechamados.usuario.atendente;

import br.com.hbsis.controlechamados.admin.Admin;
import br.com.hbsis.controlechamados.admin.AdminService;
import br.com.hbsis.controlechamados.permissao.Permissao;
import br.com.hbsis.controlechamados.produtos.ProdutoDTO;
import br.com.hbsis.controlechamados.produtos.ProdutoService;
import br.com.hbsis.controlechamados.storage.Disco;
import br.com.hbsis.controlechamados.utils.email.ValidatorEmail;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AtendenteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtendenteService.class);
    private final IAtendenteRepository iAtendenteRepository;
    private final ProdutoService produtoService;
    private final Disco disco;
    private final AdminService adminService;
    private final PasswordEncoder bCryptPasswordEncoder;

    private final String msgVazio = " não pode estar vazio!";

    @Autowired
    public AtendenteService(IAtendenteRepository iAtendenteRepository, ProdutoService produtoService, Disco disco, AdminService adminService, PasswordEncoder bCryptPasswordEncoder) {
        this.iAtendenteRepository = iAtendenteRepository;
        this.produtoService = produtoService;
        this.disco = disco;
        this.adminService = adminService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public AtendenteDTO save(AtendenteDTO atendenteDTO) {

        this.validate(atendenteDTO);

        LOGGER.info("Salvando atendente");
        LOGGER.debug("Atendente -> [{}]", atendenteDTO);

        Atendente atendente = new Atendente();
        atendente.setNome(atendenteDTO.getNome());
        atendente.setEmail(atendenteDTO.getEmail());
        atendente.setSenha(bCryptPasswordEncoder.encode(atendenteDTO.getSenha()));
        atendente.setFoto(atendenteDTO.getFoto());
        atendente.setProdutoList(atendenteDTO.getProdutoList());

        LOGGER.info("Executando save do atendente!");
        atendente = this.iAtendenteRepository.save(atendente);

        this.executeListPermissioes(atendente);

        LOGGER.info("Finalizando save do atendente!");
        return AtendenteDTO.of(atendente);
    }

    private AtendenteDTO validate(AtendenteDTO atendenteDTO){

        LOGGER.info("Validando atendente...");

        if(StringUtils.isBlank(atendenteDTO.getNome())){
            throw new IllegalArgumentException("Nome"+msgVazio);
        }

        if(atendenteDTO.getNome().length() > 100){
            throw new IllegalArgumentException("Nome deve conter no máximo 100 digitos!");
        }

        if(StringUtils.isBlank(atendenteDTO.getEmail())){
            throw new IllegalArgumentException("E-mail"+msgVazio);
        }

        if(adminService.validateLogin(atendenteDTO.getEmail())){
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }

        if(!ValidatorEmail.isValidEmail(atendenteDTO.getEmail())){
            throw new IllegalArgumentException("Padrão de e-mail inválido!");
        }

        if(atendenteDTO.getEmail().length() > 100){
            throw new IllegalArgumentException("E-mail deve conter no máximo 50 digitos!");
        }

        if(iAtendenteRepository.existsByEmail(atendenteDTO.getEmail())){
            throw new IllegalArgumentException("Email já cadastrado!");
        }

        if(StringUtils.isBlank(atendenteDTO.getSenha())){
            throw new IllegalArgumentException("Senha"+msgVazio);
        }

        validateSenha(atendenteDTO);

        if(atendenteDTO.getProdutoList() == null){
            throw new IllegalArgumentException("Favor selecionar no mínimo um produto!");
        }

        if(atendenteDTO.getProdutoList().size() == 0){
            throw new IllegalArgumentException("Favor selecionar no mínimo um produto!");
        }

        if(StringUtils.isBlank(atendenteDTO.getFoto())){
            atendenteDTO.setFoto("default-person.png");
        }

        return atendenteDTO;
    }

    private void validateSenha(AtendenteDTO atendenteDTO) {
        if(atendenteDTO.getSenha().length() > 30){
            throw new IllegalArgumentException("Senha deve conter no máximo 30 digitos!");
        }
        if(atendenteDTO.getSenha().length() < 8){
            throw new IllegalArgumentException("Senha fraca: Possui menos de 8 digitos.");
        }

        if(StringUtils.containsOnly(atendenteDTO.getSenha().toLowerCase(), "qwertyuiopasdfghjklzxcvbnm")){
            throw new IllegalArgumentException("Senha fraca: Possui apenas letras");
        }

        if(StringUtils.containsOnly(atendenteDTO.getSenha(), "0123456789")){
            throw new IllegalArgumentException("Senha fraca: Possui apenas numeros.");
        }
        if(!StringUtils.containsAny(atendenteDTO.getSenha().toLowerCase(), "qwertyuiopasdfghjklzxcvbnm0123456789")){
            throw new IllegalArgumentException("Senha fraca: Precisa conter letras e numeros.");
        }
        if(!StringUtils.containsAny(atendenteDTO.getSenha(), "qwertyuiopasdfghjklzxcvbnm")){
            throw new IllegalArgumentException("Senha fraca: Precisa conter letras minusculas e maiusculas.");
        }
        if(!StringUtils.containsAny(atendenteDTO.getSenha(), "QWERTYUIOPASDFGHJKLZXCVBNM")){
            throw new IllegalArgumentException("Senha fraca: Precisa conter letras maiusculas.");
        }
        if(!StringUtils.containsAny(atendenteDTO.getSenha(), "0123456789")){
            throw new IllegalArgumentException("Senha fraca: Precisa conter numeros.");
        }
        if(!StringUtils.containsAny(atendenteDTO.getSenha(), "$&+,:;=?@#|'<>.^*()%!-")){
            throw new IllegalArgumentException("Senha fraca: Senha precisa de caracteres especiais");
        }
    }

    public AtendenteDTO findById(Long id){

        Optional<Atendente> atendenteOptional = this.iAtendenteRepository.findById(id);

        if (atendenteOptional.isPresent()){
            Atendente atendente = atendenteOptional.get();
            return AtendenteDTO.of(atendente);
        }
        throw new IllegalArgumentException(String.format("Id %s de atendente não existe", id));
    }

    public void salvarFoto(MultipartFile file) {
        disco.salvarFoto(file);
    }

    public void executeListPermissioes(Atendente atendente){

        List<Permissao> permissaoList = new ArrayList<>();

        Permissao permissao1 = new Permissao();
        permissao1.setId(Long.parseLong("6"));
        permissao1.setDescricao("ROLE_LISTAR_ATENDENTE");

        permissaoList.add(permissao1);

        Permissao permissao2 = new Permissao();
        permissao2.setId(Long.parseLong("16"));
        permissao2.setDescricao("ROLE_CADASTRAR_CHAMADOS");

        permissaoList.add(permissao2);

        Permissao permissao3 = new Permissao();
        permissao3.setId(Long.parseLong("17"));
        permissao3.setDescricao("ROLE_CADASTER_FILES_CHAMADOS");

        permissaoList.add(permissao3);

        Admin admin = new Admin();
        admin.setLogin(atendente.getEmail());
        admin.setSenha(atendente.getSenha());
        admin.setPermissaoList(permissaoList);

        LOGGER.info("Executando save do admin...");
        this.adminService.saveNaRepositoryAdmin(admin);
    }

    public AtendenteDTO findByEmail(String email){
        Optional<Atendente> colaboradorOptional = iAtendenteRepository.findByEmail(email);
        if (colaboradorOptional.isPresent()) {
            LOGGER.info("Colaborador encontrado");
            return AtendenteDTO.of(colaboradorOptional.get());
        }
        return AtendenteDTO.of(colaboradorOptional.get());
    }
}

