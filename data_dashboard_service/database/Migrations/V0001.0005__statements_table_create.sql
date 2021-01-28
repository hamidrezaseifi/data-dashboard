
CREATE TABLE public.statements (
	id uuid NOT NULL,
	name varchar(400) NOT NULL,
	result_limit int NOT NULL DEFAULT -1,
	status int2 NOT NULL DEFAULT 1,
	version int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),

	CONSTRAINT statements_pkey PRIMARY KEY (id)
);

