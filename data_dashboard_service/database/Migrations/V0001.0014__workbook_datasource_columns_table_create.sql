
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

INSERT INTO public.workbook_datasource_columns
(id, datasource_id, column_name, column_type, "version", created_at)
VALUES('88516907-1ed5-491a-806f-b246f8b978e8', '6f02ccd9-44b8-45c7-86f5-f778815e625f', 'kh_key', 'varchar', 0, '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasource_columns
(id, datasource_id, column_name, column_type, "version", created_at)
VALUES('eb1e4232-54a3-47a2-be15-1cd7a404eb4f', '6f02ccd9-44b8-45c7-86f5-f778815e625f', 'krankenhaus_name', 'varchar', 0, '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasource_columns
(id, datasource_id, column_name, column_type, "version", created_at)
VALUES('3199aeac-facd-4e8d-88c2-a6eac9e22812', '44e92606-d733-4a60-98fd-9fd713f884f3', 'kh_key', 'varchar', 0, '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasource_columns
(id, datasource_id, column_name, column_type, "version", created_at)
VALUES('2f3378f6-522a-4967-9946-f199f1d3cc43', '44e92606-d733-4a60-98fd-9fd713f884f3', 'erkrankung_kapitel', 'varchar', 0, '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasource_columns
(id, datasource_id, column_name, column_type, "version", created_at)
VALUES('c8c8f7c6-5fc4-48a0-ab02-39f8b70d9048', '44e92606-d733-4a60-98fd-9fd713f884f3', 'erkrankung_obergruppe', 'varchar', 0, '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasource_columns
(id, datasource_id, column_name, column_type, "version", created_at)
VALUES('129ecd43-a57a-491a-a280-6042864f03f9', '44e92606-d733-4a60-98fd-9fd713f884f3', 'fallzahl', 'int4', 0, '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasource_columns
(id, datasource_id, column_name, column_type, "version", created_at)
VALUES('24400e88-8fc8-4d4a-8738-495e10984066', '44e92606-d733-4a60-98fd-9fd713f884f3', 'fachabteilung_name', 'varchar', 0, '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasource_columns
(id, datasource_id, column_name, column_type, "version", created_at)
VALUES('8797b1a3-f2b9-484d-834c-040b1d2e6c9f', 'b0f14fd9-eae0-48eb-8e76-8fc1d48b3680', 'kh_key', 'varchar', 0, '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasource_columns
(id, datasource_id, column_name, column_type, "version", created_at)
VALUES('13cc29ac-d23a-4836-b8f3-81fd8f0904fe', 'b0f14fd9-eae0-48eb-8e76-8fc1d48b3680', 'behandlung_kapitel', 'varchar', 0, '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasource_columns
(id, datasource_id, column_name, column_type, "version", created_at)
VALUES('63254762-2bc7-4c29-b114-436191fbadc0', 'b0f14fd9-eae0-48eb-8e76-8fc1d48b3680', 'behandlung_obergruppe', 'varchar', 0, '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasource_columns
(id, datasource_id, column_name, column_type, "version", created_at)
VALUES('314c6f5d-2b57-42c7-b1a6-556e663bf9a1', 'b0f14fd9-eae0-48eb-8e76-8fc1d48b3680', 'anzahl', 'int4', 0, '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasource_columns
(id, datasource_id, column_name, column_type, "version", created_at)
VALUES('5ad2e01f-107f-4ac2-8445-5e62b33da7b9', 'b0f14fd9-eae0-48eb-8e76-8fc1d48b3680', 'fachabteilung_name', 'varchar', 0, '2021-02-03 08:31:57.516');
