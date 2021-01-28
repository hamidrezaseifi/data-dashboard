
CREATE TABLE public.statement_groupby_fields (
	id uuid NOT NULL,
	statement_id uuid NOT NULL,
	field_name varchar(400) NOT NULL,
	status int2 NOT NULL DEFAULT 1,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT statement_groupby_fields_pkey PRIMARY KEY (id)
);


ALTER TABLE public.statement_groupby_fields ADD CONSTRAINT statement_groupby_fields_fk FOREIGN KEY (statement_id) REFERENCES statements(id) ON UPDATE CASCADE ON DELETE CASCADE;



