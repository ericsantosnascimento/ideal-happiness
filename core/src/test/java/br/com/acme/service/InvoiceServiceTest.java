package br.com.acme.service;

import br.com.acme.Invoice;
import br.com.acme.exception.AcmeServiceException;
import br.com.acme.request.InvoiceRequest;
import br.com.acme.util.MockCreatorUtil;
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

import static br.com.acme.util.MockCreatorUtil.createMockInvoice;
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

        Mockito.when(invoiceService.list(1L, 12, "A1B2", "shop")).thenReturn(Collections.singletonList(createMockInvoice(1L, "A1B2", "ShopPurchase", "Winkel aankoop")));
        List<Invoice> invoices = invoiceService.list(1L, 12, "A1B2", "shop");

        Assert.assertThat(invoices, is(notNullValue()));
        Assert.assertThat(invoices, is(hasSize(1)));

    }

    @Test(expected = AcmeServiceException.class)
    public void listInvoicesWithErrorInvalidMonth() {

        Mockito.when(invoiceService.list(1L, 13, "A1B2", "shop")).thenThrow(new AcmeServiceException("Invalid Month, month must be between 1 and 12"));
        invoiceService.list(1L, 13, "A1B2", "shop");

    }


    @Test
    public void saveWithSuccess() throws Exception {

        InvoiceRequest mockedInvoiceRequest = MockCreatorUtil.createMockInvoiceRequest();
        Invoice mockedInvoice = MockCreatorUtil.createMockInvoice(mockedInvoiceRequest.customerId(), mockedInvoiceRequest.addressId(), mockedInvoiceRequest.type(), mockedInvoiceRequest.typeLocalized());

        Mockito.when(invoiceService.save(mockedInvoiceRequest)).thenReturn(mockedInvoice);
        Invoice invoice = invoiceService.save(mockedInvoiceRequest);

        Assert.assertThat(invoice, is(notNullValue()));
        Assert.assertThat(invoice.id(), is(notNullValue()));
        Assert.assertThat(invoice.date(), is(notNullValue()));

    }

}