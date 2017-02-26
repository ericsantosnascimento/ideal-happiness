package br.com.acme.service;

import br.com.acme.Invoice;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by eric-nasc on 25/02/17.
 */
@Service
public class InvoiceService {

    public List<Invoice> list(@RequestParam Long customerId, @RequestParam Integer month) {
        Invoice invoice = Invoice.builder().customerId(UUID.randomUUID()).invoiceDate(Instant.now()).invoiceId("1").invoiceNumber("QBL").invoiceType("type").invoiceTypeLocalized("type").addressId("123").amount(Money.of(CurrencyUnit.AUD, BigDecimal.ONE)).paymentDueDate(Instant.now()).startDate(Instant.now()).endDate(Instant.now()).periodDescription("AAA").vatAmount(Money.of(CurrencyUnit.USD, BigDecimal.TEN)).totalAmount(Money.of(CurrencyUnit.AUD, BigDecimal.ZERO)).build();
        return Collections.singletonList(invoice);
    }

}
