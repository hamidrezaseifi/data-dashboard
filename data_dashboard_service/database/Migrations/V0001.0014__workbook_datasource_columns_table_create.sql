
CREATE TABLE public.workbook_datasource_columns (
	id uuid NOT NULL,
	datasource_id uuid NOT NULL,
	column_name varchar(200) NOT NULL,
	column_type varchar(200) NOT NULL,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT workbook_datasource_columns_pkey PRIMARY KEY (id)
);

ALTER TABLE public.workbook_datasource_columns ADD CONSTRAINT fk_workbook_datasource_columns_workbook_datasources FOREIGN KEY (datasource_id) REFERENCES workbook_datasources(id) ON UPDATE CASCADE ON DELETE CASCADE;

