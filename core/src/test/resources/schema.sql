CREATE TABLE invoices (

    invoice_id uuid PRIMARY KEY NOT NULL,
	customer_id bigint NOT NULL,
	address_id VARCHAR(200) NOT NULL,
    invoice_type VARCHAR(100) NOT NULL,
    invoice_type_localized VARCHAR(100),
    invoice_date TIMESTAMP NOT NULL,
    payment_due_date TIMESTAMP NOT NULL,
    invoice_number bigint NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    period_description VARCHAR(200)  NOT NULL,
    currency text NOT NULL,
    amount numeric(12, 2) NOT NULL,
    vat_amount numeric(12, 2) NOT NULL,
    total_amount numeric(12, 2) NOT NULL

);