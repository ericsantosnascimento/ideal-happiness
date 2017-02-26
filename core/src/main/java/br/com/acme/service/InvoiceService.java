package br.com.acme.service;

import br.com.acme.Invoice;
import br.com.acme.dao.InvoiceDAO;
import br.com.acme.request.InvoiceRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

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

    public List<Invoice> listInvoices(Long customerId, Integer month, String addressId, String filter) {
        return invoiceDAO.findInvoices(customerId, month, addressId, filter);
    }

    public Invoice save(InvoiceRequest invoiceRequest) {
        Invoice invoice = completeInvoiceInformation(invoiceRequest);
        return invoiceDAO.save(invoice);
    }

    private Invoice completeInvoiceInformation(InvoiceRequest invoiceRequest) {
        return Invoice.builder()
                .customerId(invoiceRequest.customerId())
                .addressId(invoiceRequest.addressId())
                .type(invoiceRequest.type())
                .typeLocalized(invoiceRequest.typeLocalized())
                .id(UUID.randomUUID()).date(Instant.now())
                .number(Long.valueOf(RandomStringUtils.randomNumeric(8)))
                .periodDescription("AdvancePayment")
                .vatAmount(Money.of(CurrencyUnit.EUR, BigDecimal.TEN))
                .amount(Money.of(CurrencyUnit.EUR, BigDecimal.TEN))
                .totalAmount(Money.of(CurrencyUnit.EUR, BigDecimal.TEN))
                .paymentDueDate(Instant.now().plus(30, ChronoUnit.DAYS))
                .startDate(Instant.now())
                .endDate(Instant.now().plus(30, ChronoUnit.DAYS)).build();
    }

}
