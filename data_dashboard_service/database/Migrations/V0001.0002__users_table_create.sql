CREATE TABLE public.users (
	id uuid NOT NULL,
	customer_id uuid NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(500) NOT NULL,
	password_salt varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	birthdate date NULL,
	firstname varchar(45) NOT NULL,
	lastname varchar(45) NOT NULL,
	permission int2 NOT NULL DEFAULT 1,
	status int2 NOT NULL DEFAULT 1,
	version int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT users_email_key UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);


-- public.users foreign keys

ALTER TABLE public.users ADD CONSTRAINT fk_users_customers FOREIGN KEY (customers_id) REFERENCES customers(id) ON UPDATE CASCADE ON DELETE CASCADE;

INSERT INTO public.users
(id, customer_id, username, password_hash, password_salt, email, birthdate, firstname, lastname, "permission", status, "version", created_at, updated_at)
VALUES('9aaa55ae-32ee-4dee-a467-3088009c8c44', 'f6142b1a-9bb2-4119-9ae9-864afa5d0c65', 'hamidreza.seifi', 'UTxS8xpLdkOcLEy1TrVqQEkhjJur8SdYwKnx6yq51x0e3Lp5e0p1LoNv+mQIQfHR0T+eNx+ZuKLp+8LJHn3GSw==', 'b86c3d85-db11-4d95-ae4f-ee1a0d372081#857949cd-25bf-4b12-b46c-5b3e27ca780d', 'hamidreza.seifi@mediqon.de', '1077-09-11', 'Hamidreza', 'Seifi', 1, 1, 1, '2021-01-27 14:12:28.566', '2021-01-27 14:12:28.566');

