package br.com.acme.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * Created by eric-nasc on 25/02/17.
 */
@Value.Immutable
@Value.Style(typeAbstract = {"*Template"}, typeImmutable = "*")
@JsonSerialize(as = InvoiceRequest.class)
@JsonDeserialize(as = InvoiceRequest.class)
public interface InvoiceRequestTemplate {

    Long customerId();

    String addressId();

    String type();

    String typeLocalized();

}
