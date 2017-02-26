package br.com.acme.dao.impl;

import br.com.acme.Invoice;
import br.com.acme.dao.AbstractDAO;
import br.com.acme.dao.InvoiceDAO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by eric-nasc on 26/02/17.
 */
@Repository
public class InvoiceDAOImpl extends AbstractDAO implements InvoiceDAO {

    private static final String INSERT_INVOICES = "INSERT INTO invoices (invoice_id, customer_id, address_id, invoice_type, invoice_type_localized, invoice_date, payment_due_date, invoice_number, start_date, end_date, period_description, currency, amount, vat_amount, total_amount) " +
            " VALUES (:id, :customerId, :addressId, :type, :typeLocalized, :date, :paymentDueDate, :number, :startDate, :endDate, :periodDescription, 'EUR', :amount, :vatAmount, :totalAmount)";

    private static class InvoiceRowMapper implements RowMapper<Invoice> {

        public Invoice mapRow(final ResultSet rs, final int line) throws SQLException {

            final CurrencyUnit currency = CurrencyUnit.of(rs.getString("currency"));

            return Invoice.builder()
                    .id(UUID.fromString(rs.getString("invoice_id")))
                    .customerId(rs.getLong("customer_id"))
                    .date((rs.getTimestamp("invoice_date").toInstant()))
                    .number(rs.getLong("invoice_number"))
                    .type(rs.getString("invoice_type"))
                    .typeLocalized(rs.getString("invoice_type_localized"))
                    .addressId(rs.getString("address_id"))
                    .amount(Money.of(currency, rs.getBigDecimal("amount")))
                    .paymentDueDate(rs.getTimestamp("payment_due_date").toInstant())
                    .startDate(rs.getTimestamp("start_date").toInstant())
                    .endDate(rs.getTimestamp("end_date").toInstant())
                    .periodDescription(rs.getString("period_description"))
                    .vatAmount(Money.of(currency, rs.getBigDecimal("vat_amount")))
                    .totalAmount(Money.of(currency, rs.getBigDecimal("total_amount")))
                    .build();
        }
    }

    @Override
    public List<Invoice> findInvoices(Long customerId, Integer month, String addressId, String filter) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT invoice_id, customer_id, address_id, invoice_type, invoice_type_localized,");
        sql.append(" invoice_date, payment_due_date, invoice_number, start_date, end_date, period_description, ");
        sql.append(" currency, amount, vat_amount, total_amount FROM invoices WHERE customer_id = :customerId ");

        if (month != null) {
            sql.append(" AND EXTRACT(MONTH FROM start_date) = :month");
        }

        if (StringUtils.isNotBlank(addressId)) {
            sql.append(" AND address_id = :addressId");
        }

        if (StringUtils.isNotBlank(filter)) {
            sql.append(" AND lower(invoice_type) LIKE lower('%:filter%')");
        }

        final Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);
        params.put("month", month);
        params.put("addressId", addressId);
        params.put("filter", filter);

        final List<Invoice> resultList = new NamedParameterJdbcTemplate(this.getJdbcTemplate())
                .query(sql.toString(), params, new InvoiceRowMapper());

        if (CollectionUtils.isNotEmpty(resultList)) {
            return resultList;
        }

        return Collections.emptyList();

    }

    @Override
    public Invoice save(Invoice invoice) {

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", invoice.id());
        parameters.put("customerId", invoice.customerId());
        parameters.put("addressId", invoice.addressId());
        parameters.put("type", invoice.type());
        parameters.put("typeLocalized", invoice.typeLocalized());
        parameters.put("date", Timestamp.from(invoice.date()));
        parameters.put("paymentDueDate", Timestamp.from(invoice.paymentDueDate()));
        parameters.put("number", invoice.number());
        parameters.put("startDate", Timestamp.from(invoice.startDate()));
        parameters.put("endDate", Timestamp.from(invoice.endDate()));
        parameters.put("periodDescription", invoice.periodDescription());
        parameters.put("amount", invoice.amount().getAmount());
        parameters.put("vatAmount", invoice.vatAmount().getAmount());
        parameters.put("totalAmount", invoice.totalAmount().getAmount());

        new NamedParameterJdbcTemplate(this.getJdbcTemplate()).update(INSERT_INVOICES, parameters);

        return invoice;
    }

}
