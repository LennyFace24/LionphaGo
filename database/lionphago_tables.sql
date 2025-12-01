-- Table: public.user_info

-- DROP TABLE IF EXISTS public.user_info;

CREATE TABLE IF NOT EXISTS public.user_info
(
    user_id bigint NOT NULL,
    username character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    grade integer,
    class_number integer,
    major character varying(100) COLLATE pg_catalog."default",
    school character varying(100) COLLATE pg_catalog."default",
    role_name text[] COLLATE pg_catalog."default",
    CONSTRAINT "user_id 学号" PRIMARY KEY (user_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_info
    OWNER to postgres;