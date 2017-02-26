CREATE TABLE invoices (

    invoice_id VARCHAR(200) PRIMARY KEY NOT NULL,
	customer_id bigint NOT NULL,
	address_id VARCHAR(200) NOT NULL,
    invoice_type VARCHAR(100) NOT NULL,
    invoice_type_localized VARCHAR(100),
    invoice_date timestamptz NOT NULL,
    payment_due_date timestamptz NOT NULL,
    invoice_number bigint NOT NULL,
    start_date timestamptz NOT NULL,
    end_date timestamptz NOT NULL,
    period_description VARCHAR(200)  NOT NULL,
    currency text NOT NULL,
    amount numeric(12, 2) NOT NULL,
    vat_amount numeric(12, 2) NOT NULL,
    total_amount numeric(12, 2) NOT NULL

);


    INSERT INTO public.invoices (invoice_id, customer_id, address_id, invoice_type, invoice_type_localized, invoice_date, payment_due_date, invoice_number, start_date, end_date, period_description, currency, amount, vat_amount, total_amount) VALUES ('70ec3a54a43d014aa9e8', 1, '8212BJ154', 'AdvancePayment', 'Voorschot', '2015-02-13 00:00:00.000000', '2015-02-20 00:00:00.000000', 157005888, '2015-03-01 00:00:00.000000', '2015-04-01 00:00:00.000000', 'Maart 2015', 'EUR', 165.29, 34.71, 200.00);
    INSERT INTO public.invoices (invoice_id, customer_id, address_id, invoice_type, invoice_type_localized, invoice_date, payment_due_date, invoice_number, start_date, end_date, period_description, currency, amount, vat_amount, total_amount) VALUES ('ef7bf8999a', 1, 'ef7bf8999a', 'AdvancePayment', 'Voorschot', '2014-11-13 00:00:00.000000', '2014-11-20 00:00:00.000000', 1429564, '2014-12-01 00:00:00.000000', '2015-01-01 00:00:00.000000', 'Maart 2015', 'EUR', 165.29, 34.71, 200.00);