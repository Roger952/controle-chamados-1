package br.com.hbsis.controlechamados.utils.formatedObjects;

public class FormatedMessageToCsv {

    public String formatedModuloCSV(String nomeModulo, String nomeProduto, String message){
        return nomeModulo + "; " + nomeProduto + "; " + message +"\r\n";
    }
}
