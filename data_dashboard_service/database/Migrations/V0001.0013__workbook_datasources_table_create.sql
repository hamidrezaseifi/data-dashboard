
CREATE TABLE public.workbook_datasources (
	id uuid NOT NULL,
	workbook_id uuid NOT NULL,
	connection_id uuid NOT NULL,
	name varchar(200) NOT NULL,
	data_source_type varchar(200) NOT NULL,
	table_name varchar(200),
	query_script text,
	status int2 NOT NULL DEFAULT 1,
	"version" int4 NOT NULL DEFAULT 1,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	updated_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT workbook_datasources_pkey PRIMARY KEY (id)
);

ALTER TABLE public.workbook_datasources ADD CONSTRAINT fk_workbook_datasources_workbooks FOREIGN KEY (workbook_id) REFERENCES workbooks(id) ON UPDATE CASCADE ON DELETE CASCADE;

INSERT INTO public.workbook_datasources
(id, workbook_id, connection_id, "name", data_source_type, table_name, query_script, status, "version", created_at, updated_at)
VALUES('6f02ccd9-44b8-45c7-86f5-f778815e625f', '7b7b6daa-e106-4c23-86b7-306307c2c52b', '7cdc3f4f-9263-4ef3-9a1a-00c517ebfe22', 'Krankenhaus-Liste', 'TABLE', 'qb_kh_stamm', '', 1, 0, '2021-02-03 08:31:57.516', '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasources
(id, workbook_id, connection_id, "name", data_source_type, table_name, query_script, status, "version", created_at, updated_at)
VALUES('44e92606-d733-4a60-98fd-9fd713f884f3', '7b7b6daa-e106-4c23-86b7-306307c2c52b', '7cdc3f4f-9263-4ef3-9a1a-00c517ebfe22', 'Krankenhaus-Erkrankung', 'TABLE', 'fact_qb_icd', '', 1, 0, '2021-02-03 08:31:57.516', '2021-02-03 08:31:57.516');
INSERT INTO public.workbook_datasources
(id, workbook_id, connection_id, "name", data_source_type, table_name, query_script, status, "version", created_at, updated_at)
VALUES('b0f14fd9-eae0-48eb-8e76-8fc1d48b3680', '7b7b6daa-e106-4c23-86b7-306307c2c52b', '7cdc3f4f-9263-4ef3-9a1a-00c517ebfe22', 'Krankenhaus-Behandlung', 'TABLE', 'fact_qb_ops', '', 1, 0, '2021-02-03 08:31:57.516', '2021-02-03 08:31:57.516');

