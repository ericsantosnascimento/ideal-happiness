package br.com.acme;

import org.immutables.value.Value;

/**
 * Created by eric-nasc on 25/02/17.
 */
@Value.Immutable
@Value.Style(typeAbstract = {"*Template"}, typeImmutable = "*")
public interface InvoiceTemplate {
}
