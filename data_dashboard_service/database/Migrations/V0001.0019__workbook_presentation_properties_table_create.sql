
CREATE TABLE public.workbook_presentation_properties (
	id uuid NOT NULL,
	presentation_id uuid NOT NULL,
	name varchar(200) NOT NULL,
	value varchar(4000) NOT NULL,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT workbook_presentation_properties_pkey PRIMARY KEY (id)
);

ALTER TABLE public.workbook_presentation_properties ADD CONSTRAINT fk_workbook_presentation_properties_workbook_presentations FOREIGN KEY (presentation_id) REFERENCES workbook_presentations(id) ON UPDATE CASCADE ON DELETE CASCADE;

