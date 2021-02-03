
CREATE TABLE public.workbook_dataview_columns (
	id uuid NOT NULL,
	dataview_id uuid NOT NULL,
	column_id uuid NOT NULL,
	label varchar(200) NOT NULL,
	user_as_select boolean NOT NULL,
	select_type varchar(20) NOT NULL,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT workbook_dataview_columns_pkey PRIMARY KEY (id)
);

ALTER TABLE public.workbook_dataview_columns ADD CONSTRAINT fk_workbook_dataview_columns_workbook_datasources FOREIGN KEY (dataview_id) REFERENCES workbook_dataviews(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE public.workbook_dataview_columns ADD CONSTRAINT fk_workbook_dataview_columns_workbook_datasource_columns FOREIGN KEY (column_id) REFERENCES workbook_datasource_columns(id) ON UPDATE CASCADE ON DELETE CASCADE;

