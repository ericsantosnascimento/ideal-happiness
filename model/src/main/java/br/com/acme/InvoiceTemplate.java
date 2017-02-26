package br.com.acme;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.joda.money.Money;

import java.time.Instant;
import java.util.UUID;

/**
 * Created by eric-nasc on 25/02/17.
 */
@Value.Immutable
@Value.Style(typeAbstract = {"*Template"}, typeImmutable = "*")
@JsonSerialize(as = Invoice.class)
@JsonDeserialize(as = Invoice.class)
public interface InvoiceTemplate {

    UUID customerId();

    String addressId();

    String invoiceId();

    String invoiceType();

    String invoiceTypeLocalized();

    Instant invoiceDate();

    Instant paymentDueDate();

    String invoiceNumber();

    Instant startDate();

    Instant endDate();

    String periodDescription();

    Money amount();

    Money vatAmount();

    Money totalAmount();
}
