package resturant.business.api;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import resturant.business.dto.PDFRequest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@CrossOrigin("*")
public class PDFController {

    @PostMapping("/generate-pdf")
    public void generatePDF(@RequestBody PDFRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Menu.pdf");

            String htmlContent = request.getHtmlContent();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ConverterProperties properties = new ConverterProperties();
            HtmlConverter.convertToPdf(new ByteArrayInputStream(htmlContent.getBytes()), outputStream, properties);

            response.setContentLength(outputStream.size());
            response.getOutputStream().write(outputStream.toByteArray());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
