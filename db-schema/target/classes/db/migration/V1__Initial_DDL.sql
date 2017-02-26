CREATE TABLE import (
	id bigint NOT NULL,
	dt_begin timestamp NULL,
	info varchar(255) NULL,
	imported int NULL,
	CONSTRAINT import_pkey PRIMARY KEY (id)
);

CREATE TABLE discoveries (
	discovery_id uuid PRIMARY KEY NOT NULL,
	name VARCHAR(40) NOT NULL,
    description VARCHAR(250),
    created_at timestamptz NOT NULL,
    img_url VARCHAR(250),
    channels text,
    category_type smallint,
    priority_type smallint,
    min integer,
    max integer,
    start_date timestamptz,
    end_date timestamptz,
    content_type smallint NOT NULL,
    enabled boolean NOT NULL,
    hide_name boolean NOT NULL,
    preview_count integer
);

CREATE TABLE contents (
	content_id uuid PRIMARY KEY NOT NULL,
	external_id bigint NOT NULL,
	external_content_id uuid,
	additional_code VARCHAR(10),
	content_type smallint NOT NULL,
	discovery_id uuid NOT NULL,
	CONSTRAINT content_discovery_fk FOREIGN KEY (discovery_id) REFERENCES "discoveries" (discovery_id)
);

CREATE TABLE criteria (
	criteria_id uuid PRIMARY KEY NOT NULL,
	field VARCHAR(20)  NOT NULL,
	value text NOT NULL,
	field_type smallint NOT NULL,
	operator_type smallint,
	discovery_id uuid NOT NULL,
    CONSTRAINT content_discovery_fk FOREIGN KEY (discovery_id) REFERENCES "discoveries" (discovery_id)
);

CREATE TABLE sorts (
	sort_id uuid PRIMARY KEY NOT NULL,
	field VARCHAR(20)  NOT NULL,
	field_type smallint NOT NULL,
	order_type smallint,
	priority smallint,
	discovery_id uuid NOT NULL,
    CONSTRAINT content_discovery_fk FOREIGN KEY (discovery_id) REFERENCES "discoveries" (discovery_id)
);