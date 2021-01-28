CREATE TABLE public.connection_properties (
	id uuid NOT NULL,
	name varchar(200) NOT NULL,
	server varchar(200) NOT NULL,
	port int4 NOT NULL,
	database_name varchar(200) NOT NULL,
	database_type int4 NOT NULL,
	username varchar(50) NOT NULL,
	password varchar(50) NOT NULL,
	status int2 NOT NULL DEFAULT 1,
	version int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT connection_properties_pkey PRIMARY KEY (id)
);

INSERT INTO public.connection_properties
(id, "server", port, database_name, database_type, username, "password", status, "version", created_at, updated_at, "name")
VALUES('60196060-e19f-49b1-ad0a-814ee786a326', 'mq-db02.mediqon.local', 5433, 'mq_pg_krankenhaus_norm', 3, 'tableau_read', 'tableau_read', 1, 0, '2021-01-28 18:00:22.227', '2021-01-28 18:00:22.227', 'Krankenhaus Norm');
INSERT INTO public.connection_properties
(id, "server", port, database_name, database_type, username, "password", status, "version", created_at, updated_at, "name")
VALUES('9ee8c001-e9b9-49d7-92d4-7e4392e630f0', 'mq-db02.mediqon.local', 5433, 'mq_pg_business_development_tableau', 3, 'tableau_read', 'tableau_read', 1, 0, '2021-01-28 20:42:14.160', '2021-01-28 20:42:14.160', 'Business Development Tableau');
INSERT INTO public.connection_properties
(id, "server", port, database_name, database_type, username, "password", status, "version", created_at, updated_at, "name")
VALUES('864ea91d-868c-4b09-af58-e8a83a0d2935', 'mq-db02.mediqon.local', 5433, 'mq_pg_kodierschluessel_norm', 3, 'tableau_read', 'tableau_read', 1, 0, '2021-01-28 20:42:52.060', '2021-01-28 20:42:52.060', 'Business Kdierschluessel Tableau Norm');
INSERT INTO public.connection_properties
(id, "server", port, database_name, database_type, username, "password", status, "version", created_at, updated_at, "name")
VALUES('9362838e-a12a-4059-8749-e51c3eb38857', 'mq-db02.mediqon.local', 5433, 'mq_pg_p21_norm', 3, 'tableau_read', 'tableau_read', 1, 0, '2021-01-28 20:43:34.406', '2021-01-28 20:43:34.406', 'P21 Norm');

