package br.com.acme.service;

import br.com.acme.Invoice;
import br.com.acme.dao.InvoiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by eric-nasc on 25/02/17.
 */
@Service
public class InvoiceService {

    private InvoiceDAO invoiceDAO;

    @Autowired
    public InvoiceService(InvoiceDAO invoiceDAO) {
        this.invoiceDAO = invoiceDAO;
    }

    public List<Invoice> listInvoices(@RequestParam Long customerId, @RequestParam Integer month) {
        return invoiceDAO.findInvoices(customerId);
    }

}
