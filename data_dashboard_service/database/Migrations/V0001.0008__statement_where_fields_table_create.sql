
CREATE TABLE public.statement_where_fields (
	id uuid NOT NULL,
	statement_id uuid NOT NULL,
	field_name varchar(400) NOT NULL,
	field_type int4 NOT NULL,
	field_value varchar(500) NOT NULL,
	status int2 NOT NULL DEFAULT 1,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT statement_where_fields_pkey PRIMARY KEY (id)
);

ALTER TABLE public.statement_where_fields ADD CONSTRAINT statement_where_fields_fk FOREIGN KEY (statement_id) REFERENCES statements(id) ON UPDATE CASCADE ON DELETE CASCADE;


