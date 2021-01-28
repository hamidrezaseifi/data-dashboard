CREATE TABLE public.users_roles (
	user_id uuid NOT NULL,
	"role" varchar(200) NOT NULL,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT users_roles_pk PRIMARY KEY (user_id, role)
);

ALTER TABLE public.users_roles ADD CONSTRAINT users_roles_fk FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE;

INSERT INTO public.users_roles
(user_id, "role", created_at)
VALUES('9aaa55ae-32ee-4dee-a467-3088009c8c44', 'ADMIN', '2021-01-27 14:12:28.566');
INSERT INTO public.users_roles
(user_id, "role", created_at)
VALUES('9aaa55ae-32ee-4dee-a467-3088009c8c44', 'USER', '2021-01-27 14:12:28.566');

