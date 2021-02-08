
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

INSERT INTO public.workbook_dataviews
(id, workbook_id, "name", status, "version", created_at, updated_at)
VALUES('bd11bd92-3eac-4991-9ffc-ecd0353caf79', '7b7b6daa-e106-4c23-86b7-306307c2c52b', 'Krankenhaus-Liste-View', 1, 0, '2021-02-03 09:04:12.432', '2021-02-03 09:04:12.432');
INSERT INTO public.workbook_dataviews
(id, workbook_id, "name", status, "version", created_at, updated_at)
VALUES('8d580281-050a-4896-a25d-f0c395d2734f', '7b7b6daa-e106-4c23-86b7-306307c2c52b', 'Krankenhaus-Erkrankung-View', 1, 0, '2021-02-03 09:04:12.432', '2021-02-03 09:04:12.432');
INSERT INTO public.workbook_dataviews
(id, workbook_id, "name", status, "version", created_at, updated_at)
VALUES('d6f767c3-d1dc-4aa9-84aa-055d9873496d', '7b7b6daa-e106-4c23-86b7-306307c2c52b', 'Krankenhaus-Behandlung-View', 1, 0, '2021-02-03 09:11:10.943', '2021-02-03 09:11:10.943');

