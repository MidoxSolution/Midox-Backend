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
	CONSTRAINT uk_entity UNIQUE (entity_cd),
	-- To be amended at server - Done
	CONSTRAINT group_entity_un_dis UNIQUE (display_value, master_cd, parent_entity_cd)
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
	-- To be amended in server DB -- Done
	CONSTRAINT stock_fk FOREIGN KEY (color_fabric_cd) REFERENCES public.group_entity(entity_cd),
    	CONSTRAINT stock_fk_1 FOREIGN KEY (material_cd) REFERENCES public.group_entity(entity_cd),
    	CONSTRAINT stock_fk_2 FOREIGN KEY (subcategory_cd) REFERENCES public.group_entity(entity_cd),
    	CONSTRAINT stock_fk_3 FOREIGN KEY (unit) REFERENCES public.group_entity(entity_cd)
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
-- To be amended in server DB -- Done
ALTER TABLE public.stock_history ALTER COLUMN bill_date TYPE date USING bill_date::date;

------------------------------------------------------- First Draft -----------------------------
-- DROP TABLE public.employee;

CREATE TABLE public.employee (
	emp_id int4 NOT NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_by int4 NULL DEFAULT 1,
	updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated_by int4 NULL DEFAULT 1,
	emp_name varchar(255) NULL,
	contact_no varchar(255) NULL,
	address varchar(255) NULL,
	joining_date date NULL DEFAULT CURRENT_DATE,
	emp_dob date NULL,
	gender varchar NOT NULL,
	status varchar NOT NULL,
	identification_no varchar NULL,
	designation varchar NOT NULL,
	CONSTRAINT employee_pkey PRIMARY KEY (emp_id),
	CONSTRAINT uk_id UNIQUE (identification_no)
);

CREATE TABLE public.brand (
	brand_id int4 NOT NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_by int4 NULL DEFAULT 1,
	updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated_by int4 NULL DEFAULT 1,
	brand_name varchar(255) NULL,
	address varchar(255) NULL,
	brand_uid varchar(255) NULL,
	contact_no varchar(255) null,
	contact_person varchar(255) null,
	status varchar not null,
	CONSTRAINT brand_pkey PRIMARY KEY (brand_id),
	CONSTRAINT uk_uid UNIQUE (brand_uid)
);

-- public.supplier definition

-- Drop table

-- DROP TABLE public.supplier;

CREATE TABLE public.supplier (
	supplier_id int4 NOT NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_by int4 NULL DEFAULT 1,
	updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated_by int4 NULL DEFAULT 1,
	supplier_name varchar(255) not NULL,
	address varchar(255) not NULL,
	supplier_uid varchar(255) not NULL,
	contact_no varchar(255) null,
	contact_person varchar(255) null,
	status varchar not null,
	CONSTRAINT supplier_pkey PRIMARY KEY (supplier_id),
	CONSTRAINT uk_suid UNIQUE (supplier_uid)
);

------------------------------------------------------- First Draft -----------------------------

ALTER TABLE public.supplier ADD email varchar NULL;
ALTER TABLE public.supplier ADD description varchar(255) NULL;


ALTER TABLE public.brand ADD email varchar NULL;
ALTER TABLE public.brand ADD description varchar(255) NULL;

ALTER TABLE public.employee ADD email varchar NULL;
ALTER TABLE public.employee ADD description varchar(255) NULL;