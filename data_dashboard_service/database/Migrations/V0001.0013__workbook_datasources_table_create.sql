
CREATE TABLE public.workbook_datasources (
	id uuid NOT NULL,
	workbook_id uuid NOT NULL,
	connection_id uuid NOT NULL,
	data_source_type varchar(200) NOT NULL,
	data_source varchar(4000) NOT NULL,
	status int2 NOT NULL DEFAULT 1,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT workbook_datasources_pkey PRIMARY KEY (id)
);

ALTER TABLE public.workbook_datasources ADD CONSTRAINT fk_workbook_datasources_workbooks FOREIGN KEY (workbook_id) REFERENCES workbooks(id) ON UPDATE CASCADE ON DELETE CASCADE;
