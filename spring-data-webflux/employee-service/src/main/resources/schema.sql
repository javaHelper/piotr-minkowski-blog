CREATE TABLE employee (
    name character varying NOT NULL,
    salary integer NOT NULL,
    id serial PRIMARY KEY,
    organization_id integer
);

CREATE TABLE organization (
    name character varying NOT NULL,
    id serial PRIMARY KEY
);


INSERT INTO public.employee (id, "name", salary, organization_id) VALUES(1, 'John Smith', 1000, 1);
INSERT INTO public.employee (id, "name", salary, organization_id) VALUES(2, 'Darren Hamilton', 2000, 1);
INSERT INTO public.employee (id, "name", salary, organization_id) VALUES(3, 'Tom Scott', 2500, 1);
INSERT INTO public.employee (id, "name", salary, organization_id) VALUES(4, 'Steve Franklin', 3000, 2);
INSERT INTO public.employee (id, "name", salary, organization_id) VALUES(5, 'Andrew Campton', 3000, 2);
INSERT INTO public.employee (id, "name", salary, organization_id) VALUES(6, 'Ian Scott', 2000, 2);


INSERT INTO public.organization (id, "name") VALUES(1, 'Mastercard');
INSERT INTO public.organization (id, "name") VALUES(2, 'VISA');
INSERT INTO public.organization (id, "name") VALUES(3, 'Oracle');

