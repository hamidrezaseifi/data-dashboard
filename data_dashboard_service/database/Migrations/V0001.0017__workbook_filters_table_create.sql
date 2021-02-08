
CREATE TABLE public.workbook_filters (
	id uuid NOT NULL,
	workbook_id uuid NOT NULL,
	dataview_id uuid NOT NULL,
	name varchar(200) NOT NULL,
	filter_key varchar(200) NOT NULL,
	filter_type varchar(20) NOT NULL,
	column_index int NOT NULL,
	row_index int NOT NULL,
	status int2 NOT NULL DEFAULT 1,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT workbook_filters_pkey PRIMARY KEY (id)
);

ALTER TABLE public.workbook_filters ADD CONSTRAINT fk_workbook_filters_workbook_datasources FOREIGN KEY (dataview_id) REFERENCES workbook_dataviews(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE public.workbook_filters ADD CONSTRAINT fk_workbook_filters_workbooks FOREIGN KEY (workbook_id) REFERENCES workbooks(id) ON UPDATE CASCADE ON DELETE CASCADE;

INSERT INTO public.workbook_filters
(id, workbook_id, dataview_id, "name", filter_key, filter_type, column_index, row_index, status, "version", created_at, updated_at)
VALUES('a1db6e75-3064-4ad9-aecf-92e032f10422', '7b7b6daa-e106-4c23-86b7-306307c2c52b', 'bd11bd92-3eac-4991-9ffc-ecd0353caf79', 'Krankenhaus Filter', 'kh_key', 'TABEL', 2, 0, 1, 0, '2021-02-03 15:13:35.095', '2021-02-03 15:13:35.095');

