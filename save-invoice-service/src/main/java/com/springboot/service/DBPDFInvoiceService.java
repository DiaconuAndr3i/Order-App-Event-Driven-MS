package com.springboot.service;

import com.springboot.event.InvoiceEvent;
import com.springboot.model.DBPDFInvoice;
import com.springboot.repository.DBPDFInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DBPDFInvoiceService {

    @Autowired
    private DBPDFInvoiceRepository dbpdfInvoiceRepository;

    public void savePdf(InvoiceEvent invoiceEvent, byte[] bytesFile) throws IOException {

        String fileName = invoiceEvent.getNameInvoice();

        try{
            if(fileName.contains("..")) {
                throw new IOException("Invalid path sequence");
            }

            DBPDFInvoice dbpdfInvoice = new DBPDFInvoice();

            dbpdfInvoice.setFileName(fileName);
            dbpdfInvoice.setFileType("application/pdf");
            dbpdfInvoice.setDataFile(bytesFile);
            dbpdfInvoice.setIdBuyer(invoiceEvent.getOrderEvent().getOrder().getIdBuyer());

            dbpdfInvoiceRepository.save(dbpdfInvoice);

            String fileDownloadUri = "http://localhost:8086/downloadInvoice/" + dbpdfInvoice.getId();

            System.out.println("File saved. You can download pdf from url bellow:");
            System.out.println(fileDownloadUri);

        }catch (IOException ex){
            throw new IOException("Couldn't store file");
        }

    }

    public DBPDFInvoice getFile(String fileId) throws IOException {
        return dbpdfInvoiceRepository.findById(fileId)
                .orElseThrow(() -> new IOException("File not found with id " + fileId));
    }
}
