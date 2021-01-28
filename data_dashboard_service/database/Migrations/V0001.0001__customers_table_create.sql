CREATE TABLE public.customers (
	id uuid PRIMARY KEY,
	"name" varchar(200) NULL,
	customer_key varchar(100) NULL,
	status int4 NULL,
	"version" int4 NULL,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now())
);

INSERT INTO public.customers
(id, "name", customer_key, status, "version", created_at, updated_at)
VALUES('f6142b1a-9bb2-4119-9ae9-864afa5d0c65', 'MEDIQON-TEST', 'mq', 1, 1, '2021-01-27 14:12:28.000', '2021-01-27 14:12:28.000');


