
CREATE TABLE public.workbook_dataviews (
	id uuid NOT NULL,
	workbook_id uuid NOT NULL,
	name varchar(200) NOT NULL,
	status int2 NOT NULL DEFAULT 1,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT workbook_dataviews_pkey PRIMARY KEY (id)
);

ALTER TABLE public.workbook_dataviews ADD CONSTRAINT fk_workbook_dataviews_workbooks FOREIGN KEY (workbook_id) REFERENCES workbooks(id) ON UPDATE CASCADE ON DELETE CASCADE;
