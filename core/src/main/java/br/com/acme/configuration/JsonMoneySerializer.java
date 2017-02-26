package br.com.acme.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.joda.money.Money;

import java.io.IOException;

/**
 * @author Created by Raphael Prado Lisboa on 28/12/15.
 * 
 *         Custom Json Serilizer for Joda Money
 */
public class JsonMoneySerializer extends StdSerializer<Money> {

    public JsonMoneySerializer() {
        super(Money.class);
    }

    /**
     * Serialize Joda Money objects to Json
     * 
     * @param value Money
     * @param jsonGenerator JsonGenerator
     * @param provider SerializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(Money value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("currency", value.getCurrencyUnit().toString());
        jsonGenerator.writeObjectField("value", value.getAmountMinor().toString());
        jsonGenerator.writeEndObject();
    }
}