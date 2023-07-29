-- public.group_master_sequence definition

-- DROP SEQUENCE public.group_master_sequence;

CREATE SEQUENCE public.group_master_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
-- public.entity_sequence definition

-- DROP SEQUENCE public.entity_sequence;

CREATE SEQUENCE public.entity_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- public.stock_sequence definition

-- DROP SEQUENCE public.stock_sequence;

CREATE SEQUENCE public.stock_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
-- public.stock_history_sequence definition

-- DROP SEQUENCE public.stock_history_sequence;

CREATE SEQUENCE public.stock_history_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
-- public.emp_seq definition

-- DROP SEQUENCE public.emp_seq;

CREATE SEQUENCE public.emp_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;