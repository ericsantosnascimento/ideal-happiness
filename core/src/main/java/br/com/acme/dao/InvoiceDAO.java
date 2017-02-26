package br.com.acme.dao;

import br.com.acme.Invoice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eric-nasc on 26/02/17.
 */
public interface InvoiceDAO {

    List<Invoice> findInvoices(Long customerId);
}
