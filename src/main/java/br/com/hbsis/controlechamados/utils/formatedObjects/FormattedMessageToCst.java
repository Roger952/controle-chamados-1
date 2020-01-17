package br.com.hbsis.controlechamados.utils.formatedObjects;

public class FormattedMessageToCst {

    public String formattedModuloCST(String nomeModulo, String nomeProduto, String message){
        return nomeModulo + " --- " + nomeProduto + " --- " + message +"\r\n";
    }
}
