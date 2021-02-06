
CREATE TABLE public.workbook_presentation_filters (
	presentation_id uuid NOT NULL,
	filter_id uuid NOT NULL,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT workbook_presentation_filters_pkey PRIMARY KEY (presentation_id, filter_id)
);

ALTER TABLE public.workbook_presentation_filters ADD CONSTRAINT fk_workbook_presentation_filters_workbook_presentations FOREIGN KEY (presentation_id) REFERENCES workbook_presentations(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE public.workbook_presentation_filters ADD CONSTRAINT fk_workbook_presentation_filters_workbook_filters FOREIGN KEY (filter_id) REFERENCES workbook_filters(id) ON UPDATE CASCADE ON DELETE CASCADE;

