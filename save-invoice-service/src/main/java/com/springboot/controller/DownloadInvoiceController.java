package com.springboot.controller;

import com.springboot.model.DBPDFInvoice;
import com.springboot.service.DBPDFInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
public class DownloadInvoiceController {

    @Autowired
    private DBPDFInvoiceService dbpdfInvoiceService;

    @GetMapping("/downloadInvoice/{fileId:.+}")
    public ResponseEntity<Resource> downloadPdfFile(@PathVariable String fileId, HttpServletRequest request) throws IOException {
        DBPDFInvoice dbpdfInvoice = dbpdfInvoiceService.getFile(fileId);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbpdfInvoice.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbpdfInvoice.getFileName() + "\"")
                .body(new ByteArrayResource(dbpdfInvoice.getDataFile()));
    }
}
