
CREATE TABLE public.statement_connections (
	statement_id uuid NOT NULL,
	connection_id uuid NOT NULL,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT statement_connections_pkey PRIMARY KEY (statement_id, connection_id)
);

