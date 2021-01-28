
CREATE TABLE public.statements (
	id uuid NOT NULL,
	connection_id uuid NOT NULL,
	is_distinct boolean NOT NULL,
	table_name varchar(100) NOT NULL,
	name varchar(400) NOT NULL,
	result_limit int NOT NULL DEFAULT -1,
	status int2 NOT NULL DEFAULT 1,
	version int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),

	CONSTRAINT statements_pkey PRIMARY KEY (id)
);

ALTER TABLE public.statements ADD CONSTRAINT statements_fk FOREIGN KEY (connection_id) REFERENCES connection_properties(id) ON UPDATE CASCADE ON DELETE CASCADE;
