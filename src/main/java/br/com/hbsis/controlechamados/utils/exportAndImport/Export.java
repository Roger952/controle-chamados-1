package br.com.hbsis.controlechamados.utils.exportAndImport;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Export {

    public void exportWithoutDependencyCSV(HttpServletResponse httpServletResponse, String file, String content) throws IOException {
        PrintWriter writer = httpServletResponse.getWriter();
        StringBuilder store = new StringBuilder();


        httpServletResponse.setContentType("text/csv");
        httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName = " + file);

        writer.write(content);
    }

}
