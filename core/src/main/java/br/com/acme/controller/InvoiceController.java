package br.com.acme.controller;

import br.com.acme.Invoice;
import br.com.acme.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by eric-nasc on 25/02/17.
 */
@RestController
@RequestMapping("/v1.0/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> list(@RequestParam Long customerId, @RequestParam Integer month) {
        return invoiceService.listInvoices(customerId, month);
    }

}