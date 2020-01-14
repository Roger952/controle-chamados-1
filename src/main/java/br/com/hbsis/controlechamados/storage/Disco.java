package br.com.hbsis.controlechamados.storage;

import br.com.hbsis.controlechamados.usuario.atendente.AtendenteDTO;
import br.com.hbsis.controlechamados.usuario.atendente.AtendenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class Disco {

    private static final Logger LOGGER = LoggerFactory.getLogger(Disco.class);

//    /** RESOLUÇÃO DAS IMAGENS */
//    private final int MAX_SIZE_IMAGE = 2 * 1024 * 1024;

    @Value("${hbsis.disco.raiz}")
    private String raiz;

    @Value("${hbsis.disco.diretorio-fotos}")
    private String diretorioFotos;

    /** MÉTODOS */
    public void salvarFoto(MultipartFile foto){
        this.salvar(this.diretorioFotos, foto);
    }

    public void salvar(String diretorio, MultipartFile arquivo){

        this.validate(arquivo);

        LOGGER.info("Executando upload de imagem...");

        Path diretorioPath = Paths.get(this.raiz, diretorio);
        Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());

        try{

            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPath.toFile());

            LOGGER.info("Finalizando upload de imagem...");

        }catch (IOException e){
            throw new RuntimeException("Problema na tentativa de criar diretório/transferir arquivo: "+e.getMessage());
        }
    }

    public void validate(MultipartFile file) {

        /** OBTER AS  */
        String nomeArquivo = file.getOriginalFilename();

        /** VALIDAR TIPO DE IMAGEM */
        boolean validacao = isImageInCorrectType(nomeArquivo);

        if(validacao){
            LOGGER.info("Arquivo do tipo correto");
        }else {
            throw new IllegalArgumentException("Tipo de imagem incorreta, selecione tipo png - jpeg - jpg");
        }

//        if (file.getBytes() > MAX_SIZE_IMAGE){
//            throw new IllegalArgumentException("Tamanho de imagem deve ser menor do que 2MB");
//        }
    }

    private boolean isImageInCorrectType(String nomeArquivo) {

        boolean validacao = false;
        String lastLetters = nomeArquivo.substring(nomeArquivo.length() -4);

        if(lastLetters.equalsIgnoreCase(".jpg")){
            validacao = true;
        }

        if(nomeArquivo.substring(nomeArquivo.length() -5).equalsIgnoreCase(".jpeg")){
            validacao = true;
        }

        if(lastLetters.equalsIgnoreCase(".png")){
            validacao = true;
        }
        return validacao;
    }
}
