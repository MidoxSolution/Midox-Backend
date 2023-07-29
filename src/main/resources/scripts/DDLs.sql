-- DROP TABLE public.group_master;

CREATE TABLE public.group_master (
	master_id int4 NOT NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_by int4 NULL DEFAULT 1,
	updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated_by int4 NULL DEFAULT 1,
	display_value varchar(255) NULL,
	master_cd varchar(255) NULL,
	master_prefix varchar(255) NULL,
	CONSTRAINT group_master_pkey PRIMARY KEY (master_id),
	CONSTRAINT uk_master UNIQUE (master_cd),
	CONSTRAINT uk_display UNIQUE (display_value),
	CONSTRAINT uk_prefix UNIQUE (master_prefix)
);


-- public.group_entity definition

-- Drop table

-- DROP TABLE public.group_entity;

CREATE TABLE public.group_entity (
	entity_id int4 NOT NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_by int4 NULL DEFAULT 1,
	updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated_by int4 NULL DEFAULT 1,
	display_value varchar(255) NULL,
	entity_cd varchar(255) NULL,
	master_cd varchar(255) NULL,
	parent_entity_cd varchar(255) NULL,
	CONSTRAINT group_entity_pkey PRIMARY KEY (entity_id),
	CONSTRAINT uk_entity UNIQUE (entity_cd)
);

-- public.stock definition

-- Drop table

-- DROP TABLE public.stock;

CREATE TABLE public.stock (
	stock_id int4 NOT NULL,
	created_at timestamp NULL,
	created_by int4 NULL,
	updated_at timestamp NULL,
	updated_by int4 NULL,
	color_fabric_cd varchar(255) NULL,
	material_cd varchar(255) NULL,
	subcategory_cd varchar(255) NULL,
	unit varchar(255) NULL,
	available_quantity float8 NULL,
	CONSTRAINT stock_pkey PRIMARY KEY (stock_id),
	CONSTRAINT stock_un UNIQUE (material_cd, color_fabric_cd, subcategory_cd)
);

-- DROP TABLE public.stock_history;

CREATE TABLE public.stock_history (
	stock_history_id int4 NOT NULL,
	created_at timestamp NULL,
	created_by int4 NULL,
	updated_at timestamp NULL,
	updated_by int4 NULL,
	bill_date timestamp(6) NULL,
	bill_no varchar(255) NULL,
	quantity float8 NULL,
	supplier_id int4 NULL,
	stock_id int4 NULL,
	amount float8 NULL,
	CONSTRAINT stock_history_pkey PRIMARY KEY (stock_history_id),
	CONSTRAINT fk_stock FOREIGN KEY (stock_id) REFERENCES public.stock(stock_id)
);

-- DROP TABLE public.employee;

CREATE TABLE public.employee (
	emp_id int4 NOT NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_by int4 NULL DEFAULT 1,
	updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated_by int4 NULL DEFAULT 1,
	emp_name varchar(255) NULL,
	joining_date date NULL DEFAULT CURRENT_DATE,
	emp_dob date NULL,
	status varchar NOT NULL,
	identification_no varchar NULL,
	designation varchar NOT NULL,
	CONSTRAINT employee_pkey PRIMARY KEY (emp_id)
);