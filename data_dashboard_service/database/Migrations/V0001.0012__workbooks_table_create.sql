
CREATE TABLE public.workbooks (
	id uuid NOT NULL,
	customer_id uuid NOT NULL,
	name varchar(200) NOT NULL,
	columns int NOT NULL DEFAULT 2,
	rows int NOT NULL DEFAULT 2,
	description text NOT NULL,
	status int2 NOT NULL DEFAULT 1,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT workbooks_pkey PRIMARY KEY (id)
);

ALTER TABLE public.workbooks ADD CONSTRAINT fk_workbooks_customers FOREIGN KEY (customer_id) REFERENCES customers(id) ON UPDATE CASCADE ON DELETE CASCADE;

INSERT INTO public.workbooks
(id, customer_id, "name", columns, rows, description, status, "version", created_at, updated_at)
VALUES('7b7b6daa-e106-4c23-86b7-306307c2c52b', 'f6142b1a-9bb2-4119-9ae9-864afa5d0c65', 'test wb', 2, 2, 'desciption for test wb', 1, 0, '2021-01-31 13:00:23.533', '2021-01-31 13:00:23.533');


