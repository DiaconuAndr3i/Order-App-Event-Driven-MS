package com.springboot.service;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.springboot.event.OrderEvent;
import com.springboot.model.PathNameFile;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PDFGenerateService {
    public PathNameFile createInvoice(OrderEvent orderEvent) throws IOException {
        String rootPath = System.getProperty("user.dir");

        Document document = new Document(PageSize.A4);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String currentDateTime = dateFormat.format(new Date());

        String nameFile = orderEvent.getOrder().getNameBuyer() + "_" + currentDateTime + ".pdf";

        String path = rootPath + "\\events-microservices\\src\\main\\resources\\"
                + nameFile;

        PathNameFile pathNameFile = new PathNameFile();
        pathNameFile.setNameFile(nameFile);
        pathNameFile.setPathFile(path);

        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph paragraphTitle = new Paragraph("Invoice", fontTitle);
        paragraphTitle.setAlignment(Paragraph.ALIGN_CENTER);
        paragraphTitle.setSpacingAfter(30);
        document.add(paragraphTitle);

        Font fontParagraph1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontParagraph1.setSize(15);
        Paragraph paragraph1 = new Paragraph("Buyer details:", fontParagraph1);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph1.setSpacingAfter(15);
        document.add(paragraph1);

        Font fontDetailsBuyer = FontFactory.getFont(FontFactory.HELVETICA);
        fontDetailsBuyer.setSize(12);
        Paragraph paragraphName = new Paragraph("Name: " + orderEvent.getOrder().getNameBuyer(), fontDetailsBuyer);
        document.add(paragraphName);
        Paragraph paragraphPhoneNumber = new Paragraph("Phone number: " + orderEvent.getOrder().getPhoneNumber(), fontDetailsBuyer);
        document.add(paragraphPhoneNumber);
        Paragraph paragraphEmail = new Paragraph("Email: " + orderEvent.getOrder().getEmail(), fontDetailsBuyer);
        document.add(paragraphEmail);
        Paragraph paragraphAddress = new Paragraph("Address: " + orderEvent.getOrder().getAddress().getCountry() + ", City "
                + orderEvent.getOrder().getAddress().getCity() + ", Street "
                + orderEvent.getOrder().getAddress().getStreet() + ", Number "
                + orderEvent.getOrder().getAddress().getNumber(), fontDetailsBuyer);
        paragraphAddress.setSpacingAfter(30);
        document.add(paragraphAddress);


        Font fontDetailsProduct = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontDetailsProduct.setSize(15);
        Paragraph paragraphDetailsProduct = new Paragraph("Product details:", fontDetailsProduct);
        paragraphDetailsProduct.setAlignment(Paragraph.ALIGN_LEFT);
        paragraphDetailsProduct.setSpacingAfter(15);
        document.add(paragraphDetailsProduct);

        PdfPTable tableHeader = new PdfPTable(3);
        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontHeader.setSize(12);
        PdfPCell cellHeader1 = new PdfPCell(new Phrase("Product name", fontHeader));
        PdfPCell cellHeader2 = new PdfPCell(new Phrase("Quantity", fontHeader));
        PdfPCell cellHeader3 = new PdfPCell(new Phrase("Price", fontHeader));
        cellHeader1.setBorder(Rectangle.BOTTOM);
        cellHeader2.setBorder(Rectangle.BOTTOM);
        cellHeader3.setBorder(Rectangle.BOTTOM);
        tableHeader.addCell(cellHeader1);
        tableHeader.addCell(cellHeader2);
        tableHeader.addCell(cellHeader3);
        tableHeader.setSpacingAfter(8);
        document.add(tableHeader);



        PdfPTable tableProduct = new PdfPTable(3);
        Font fontProduct = FontFactory.getFont(FontFactory.HELVETICA);
        fontProduct.setSize(12);
        PdfPCell cellProduct1 = new PdfPCell(new Phrase(orderEvent.getOrder().getName(), fontProduct));
        PdfPCell cellProduct2 = new PdfPCell(new Phrase(String.valueOf(orderEvent.getOrder().getQuantity()), fontProduct));
        PdfPCell cellProduct3 = new PdfPCell(new Phrase(String.valueOf(orderEvent.getOrder().getPrice()), fontProduct));
        cellProduct1.setBorder(Rectangle.NO_BORDER);
        cellProduct2.setBorder(Rectangle.NO_BORDER);
        cellProduct3.setBorder(Rectangle.NO_BORDER);
        tableProduct.addCell(cellProduct1);
        tableProduct.addCell(cellProduct2);
        tableProduct.addCell(cellProduct3);
        document.add(tableProduct);


        document.close();

        System.out.println("Document has been created");

        return pathNameFile;

    }
}
