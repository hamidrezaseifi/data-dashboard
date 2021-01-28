CREATE TABLE public.connection_properties (
	id uuid NOT NULL,
	url varchar(200) NOT NULL,
	username varchar(50) NOT NULL,
	password varchar(50) NOT NULL,
	status int2 NOT NULL DEFAULT 1,
	version int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT connection_properties_pkey PRIMARY KEY (id)
);
