package br.com.acme.service;

import br.com.acme.Invoice;
import br.com.acme.exception.AcmeServiceException;
import br.com.acme.request.InvoiceRequest;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by eric-nasc on 26/02/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceService invoiceService;

    @Test
    public void listInvoicesWithSuccess() throws Exception {

        Long customerId = 1L;
        Integer month = 12;
        String addressId = "A1B2";
        String filter = "shop";
        Mockito.when(invoiceService.list(customerId, month, addressId, filter)).thenReturn(Collections.singletonList(createMockInvoice(customerId, addressId, "ShopPurchase", "Winkel aankoop")));
        List<Invoice> invoices = invoiceService.list(customerId, month, addressId, filter);
        Assert.assertThat(invoices, is(notNullValue()));
        Assert.assertThat(invoices, is(hasSize(1)));

    }

    @Test(expected = AcmeServiceException.class)
    public void listInvoicesWithErrorInvalidMonth() {

        Long customerId = 1L;
        Integer month = 13;
        String addressId = "A1B2";
        String filter = "shop";
        Mockito.when(invoiceService.list(customerId, month, addressId, filter)).thenThrow(new AcmeServiceException("Invalid Month, month must be between 1 and 12"));
        invoiceService.list(customerId, month, addressId, filter);

    }


    @Test
    public void saveWithSuccess() throws Exception {

        InvoiceRequest invoiceRequest = createMockInvoiceRequest();
        Mockito.when(invoiceService.save(invoiceRequest)).thenReturn(createMockInvoice(invoiceRequest.customerId(), invoiceRequest.addressId(), invoiceRequest.type(), invoiceRequest.typeLocalized()));
        Invoice invoice = invoiceService.save(invoiceRequest);
        Assert.assertThat(invoice, is(notNullValue()));
        Assert.assertThat(invoice.id(), is(notNullValue()));
        Assert.assertThat(invoice.date(), is(notNullValue()));

    }

    private Invoice createMockInvoice(Long customerId, String addressId, String type, String typeLocalized) {
        return Invoice.builder()
                .id(UUID.randomUUID())
                .number(1L)
                .addressId(addressId)
                .customerId(customerId)
                .type(type)
                .typeLocalized(typeLocalized)
                .periodDescription("Fake")
                .date(Instant.now())
                .startDate(Instant.now())
                .endDate(Instant.now())
                .paymentDueDate(Instant.now())
                .amount(Money.of(CurrencyUnit.EUR, BigDecimal.TEN))
                .vatAmount(Money.of(CurrencyUnit.EUR, BigDecimal.TEN))
                .totalAmount(Money.of(CurrencyUnit.EUR, BigDecimal.TEN))
                .build();
    }

    private InvoiceRequest createMockInvoiceRequest() {
        return InvoiceRequest.builder().customerId(1L).addressId("1AB2").type("ShopPurchase").typeLocalized("Winkel aankoop").build();
    }

}