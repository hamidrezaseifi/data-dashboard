
CREATE TABLE public.workbooks (
	id uuid NOT NULL,
	customer_id uuid NOT NULL,
	name varchar(200) NOT NULL,
	description text NOT NULL,
	status int2 NOT NULL DEFAULT 1,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT workbooks_pkey PRIMARY KEY (id)
);

ALTER TABLE public.workbooks ADD CONSTRAINT fk_workbooks_customers FOREIGN KEY (customer_id) REFERENCES customers(id) ON UPDATE CASCADE ON DELETE CASCADE;

