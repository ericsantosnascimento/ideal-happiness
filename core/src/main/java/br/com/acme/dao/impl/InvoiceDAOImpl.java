package br.com.acme.dao.impl;

import br.com.acme.Invoice;
import br.com.acme.dao.AbstractDAO;
import br.com.acme.dao.InvoiceDAO;
import org.apache.commons.collections.CollectionUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.*;

/**
 *
 * Created by eric-nasc on 26/02/17.
 */
@Repository
public class InvoiceDAOImpl extends AbstractDAO implements InvoiceDAO {

    private static class InvoiceRowMapper implements RowMapper<Invoice> {

        public Invoice mapRow(final ResultSet rs, final int line) throws SQLException {

            return Invoice.builder()
                    .customerId(1L).invoiceDate(Instant.now()).invoiceId("1").invoiceNumber("QBL").invoiceType("type")
                    .invoiceTypeLocalized("type").addressId("123").amount(Money.of(CurrencyUnit.AUD, BigDecimal.ONE))
                    .paymentDueDate(Instant.now()).startDate(Instant.now()).endDate(Instant.now()).periodDescription("AAA")
                    .vatAmount(Money.of(CurrencyUnit.USD, BigDecimal.TEN)).totalAmount(Money.of(CurrencyUnit.AUD, BigDecimal.ZERO))
                    .build();
        }
    }

    @Override
    public List<Invoice> findInvoices(Long customerId) {

        String sql =
                "SELECT content_id, external_id, external_content_id, additional_code, content_type, discovery_id " +
                        "FROM contents WHERE discovery_id = :id";

        final Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);

        final List<Invoice> resultList = new NamedParameterJdbcTemplate(this.getJdbcTemplate())
                .query(sql, params, new InvoiceRowMapper());

        if (CollectionUtils.isNotEmpty(resultList)) {
            return resultList;
        }

        return Collections.emptyList();

    }

}
