
CREATE TABLE public.workbook_presentations (
	id uuid NOT NULL,
	workbook_id uuid NOT NULL,
	dataview_id uuid NOT NULL,
	name varchar(200) NOT NULL,
	presentation_type varchar(20) NOT NULL,
	presentation_style varchar(200) NOT NULL,
	status int2 NOT NULL DEFAULT 1,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT workbook_presentations_pkey PRIMARY KEY (id)
);

ALTER TABLE public.workbook_presentations ADD CONSTRAINT fk_workbook_presentations_workbook_datasources FOREIGN KEY (dataview_id) REFERENCES workbook_dataviews(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE public.workbook_presentations ADD CONSTRAINT fk_workbook_presentations_workbooks FOREIGN KEY (workbook_id) REFERENCES workbooks(id) ON UPDATE CASCADE ON DELETE CASCADE;
