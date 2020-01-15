package br.com.hbsis.controlechamados.utils.exportAndImport;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Import {

    public static List<String[]> importWithoutDependencyCSV(MultipartFile multipartfile) throws IOException {

        InputStream inputStream = multipartfile.getInputStream();
        Scanner scanner = new Scanner(inputStream);

        List<String[]> readAll = new ArrayList<>();

        String fileLine;

        scanner.nextLine();

        while (scanner.hasNext()) {

            fileLine = scanner.nextLine();

            String[] data = fileLine.replaceAll("\"", "").split(";");


            readAll.add(data);
        }
        return readAll;
    }
}
