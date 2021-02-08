
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

INSERT INTO public.workbook_dataview_columns
(id, dataview_id, column_id, "label", user_as_select, select_type, "version", created_at)
VALUES('a3d757fc-476e-42ef-857e-e241a9590702', 'bd11bd92-3eac-4991-9ffc-ecd0353caf79', '88516907-1ed5-491a-806f-b246f8b978e8', 'kh_key', true, 'NORMAL', 0, '2021-02-03 09:04:12.432');
INSERT INTO public.workbook_dataview_columns
(id, dataview_id, column_id, "label", user_as_select, select_type, "version", created_at)
VALUES('eb129bd1-76fc-416a-b17f-d8299efb0ce6', 'bd11bd92-3eac-4991-9ffc-ecd0353caf79', 'eb1e4232-54a3-47a2-be15-1cd7a404eb4f', 'Krankenhaus Name', true, 'NORMAL', 0, '2021-02-03 09:04:12.432');
INSERT INTO public.workbook_dataview_columns
(id, dataview_id, column_id, "label", user_as_select, select_type, "version", created_at)
VALUES('39641a9f-fc3f-42ac-bd5b-6886ca229091', '8d580281-050a-4896-a25d-f0c395d2734f', '3199aeac-facd-4e8d-88c2-a6eac9e22812', 'kh_key', true, 'DISTINCT', 0, '2021-02-03 09:04:12.432');
INSERT INTO public.workbook_dataview_columns
(id, dataview_id, column_id, "label", user_as_select, select_type, "version", created_at)
VALUES('d9f850c0-768a-4a73-8b36-d7d749da5947', '8d580281-050a-4896-a25d-f0c395d2734f', '2f3378f6-522a-4967-9946-f199f1d3cc43', 'Erkrankung Kapitel', true, 'NORMAL', 0, '2021-02-03 09:04:12.432');
INSERT INTO public.workbook_dataview_columns
(id, dataview_id, column_id, "label", user_as_select, select_type, "version", created_at)
VALUES('c79689f1-5888-4007-954a-a4c17a5d32b7', '8d580281-050a-4896-a25d-f0c395d2734f', 'c8c8f7c6-5fc4-48a0-ab02-39f8b70d9048', 'Erkrankung Obergruppe', true, 'NORMAL', 0, '2021-02-03 09:04:12.432');
INSERT INTO public.workbook_dataview_columns
(id, dataview_id, column_id, "label", user_as_select, select_type, "version", created_at)
VALUES('6061d94a-663a-4cfe-ad32-7a1294506e62', '8d580281-050a-4896-a25d-f0c395d2734f', '129ecd43-a57a-491a-a280-6042864f03f9', 'Fallzahl', true, 'COUNT', 0, '2021-02-03 09:04:12.432');
INSERT INTO public.workbook_dataview_columns
(id, dataview_id, column_id, "label", user_as_select, select_type, "version", created_at)
VALUES('6816e4c9-7f79-4c19-b1fa-2c6bd1a7d851', '8d580281-050a-4896-a25d-f0c395d2734f', '24400e88-8fc8-4d4a-8738-495e10984066', 'Fachabteilung Name', true, 'NORMAL', 0, '2021-02-03 09:04:12.432');
INSERT INTO public.workbook_dataview_columns
(id, dataview_id, column_id, "label", user_as_select, select_type, "version", created_at)
VALUES('268004d4-55ef-4fc6-93a7-3e3b5edaa3b7', 'd6f767c3-d1dc-4aa9-84aa-055d9873496d', '8797b1a3-f2b9-484d-834c-040b1d2e6c9f', 'kh_key', true, 'COUNT', 0, '2021-02-03 09:11:10.943');
INSERT INTO public.workbook_dataview_columns
(id, dataview_id, column_id, "label", user_as_select, select_type, "version", created_at)
VALUES('c3f1b829-c4ec-44f7-b9b5-e580016dece6', 'd6f767c3-d1dc-4aa9-84aa-055d9873496d', '13cc29ac-d23a-4836-b8f3-81fd8f0904fe', 'Behandlung Kapitel', true, 'NORMAL', 0, '2021-02-03 09:11:10.943');
INSERT INTO public.workbook_dataview_columns
(id, dataview_id, column_id, "label", user_as_select, select_type, "version", created_at)
VALUES('b9eedd15-5eaa-4ad3-bfa7-9d8760909b87', 'd6f767c3-d1dc-4aa9-84aa-055d9873496d', '63254762-2bc7-4c29-b114-436191fbadc0', 'Behandlung Obergruppe', true, 'NORMAL', 0, '2021-02-03 09:11:10.943');
INSERT INTO public.workbook_dataview_columns
(id, dataview_id, column_id, "label", user_as_select, select_type, "version", created_at)
VALUES('48ff915a-0953-49fd-9f21-81694683c5bb', 'd6f767c3-d1dc-4aa9-84aa-055d9873496d', '314c6f5d-2b57-42c7-b1a6-556e663bf9a1', 'Anzahl', true, 'COUNT', 0, '2021-02-03 09:11:10.943');
INSERT INTO public.workbook_dataview_columns
(id, dataview_id, column_id, "label", user_as_select, select_type, "version", created_at)
VALUES('f535200f-9248-4604-8294-4da513524201', 'd6f767c3-d1dc-4aa9-84aa-055d9873496d', '5ad2e01f-107f-4ac2-8445-5e62b33da7b9', 'Fachabteilung Name', true, 'NORMAL', 0, '2021-02-03 09:11:10.943');

