package com.springboot.repository;

import com.springboot.model.DBPDFInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBPDFInvoiceRepository extends JpaRepository<DBPDFInvoice, String> {
}
