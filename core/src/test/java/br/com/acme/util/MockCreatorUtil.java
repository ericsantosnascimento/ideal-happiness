package br.com.acme.util;

import br.com.acme.Invoice;
import br.com.acme.request.InvoiceRequest;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * Created by eric-nasc on 26/02/17.
 */
public class MockCreatorUtil {

    public static Invoice createMockInvoice(Long customerId, String addressId, String type, String typeLocalized) {
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

    public static InvoiceRequest createMockInvoiceRequest() {
        return InvoiceRequest.builder().customerId(1L).addressId("1AB2").type("ShopPurchase").typeLocalized("Winkel aankoop").build();
    }
}
