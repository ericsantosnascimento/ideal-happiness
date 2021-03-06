package br.com.acme.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.IOException;

public class JsonMoneyDeserializer extends StdDeserializer<Money> {

        public JsonMoneyDeserializer() {
            super(Money.class);
        }

        /**
         *   Custom Deserializer for Joda Money.
         *   Using com.ir.commons.base.MoneyHelper.
         * @param jp JsonParser
         * @param ctxt DeserializationContext
         * @return Money
         * @throws IOException
         */
        @Override
        public Money deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);
            String currency = node.get("currency").asText();
            long value = node.get("value").asLong();
            return Money.ofMinor(CurrencyUnit.of(currency), value);
        }

    }