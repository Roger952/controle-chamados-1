package br.com.hbsis.controlechamados.utils.exportAndImport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Export {

    public void exportErrInTxt(String content) throws IOException {
        Path Location = Paths.get("C:/Users/roger.guillermo/Documents/erro.txt");

        byte[] textInByte = content.getBytes();


        Files.write(Location, textInByte);
    }

}
