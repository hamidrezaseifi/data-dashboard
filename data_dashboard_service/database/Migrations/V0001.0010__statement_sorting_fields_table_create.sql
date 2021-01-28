
CREATE TABLE public.statement_sorting_fields (
	id uuid NOT NULL,
	statement_id uuid NOT NULL,
	field_name varchar(400) NOT NULL,
	sort_type int4 NOT NULL,
	status int2 NOT NULL DEFAULT 1,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT statement_sorting_fields_pkey PRIMARY KEY (id)
);


ALTER TABLE public.statement_sorting_fields ADD CONSTRAINT statement_sorting_fields_fk FOREIGN KEY (statement_id) REFERENCES statements(id) ON UPDATE CASCADE ON DELETE CASCADE;



