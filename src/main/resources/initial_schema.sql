CREATE TABLE public.ted_talk (
                                 id serial4 NOT NULL,
                                 title varchar(255) NULL,
                                 author varchar(255) NULL,
                                 display_date varchar(255) NULL,
                                 total_views int4 NULL,
                                 likes int8 NULL,
                                 link varchar(255) NULL,
                                 CONSTRAINT ted_talk_pkey PRIMARY KEY (id)
);

COPY ted_talk(title, author, display_date, total_views, likes, link)
    FROM '/var/lib/sample/data.csv'
    DELIMITER ','
    CSV HEADER;

alter table public.ted_talk add release_date date;

update ted_talk set release_date = to_date(display_date, 'Month YYYY');

alter table public.ted_talk add deleted boolean default false;

