create table public.books (
                              book_id integer primary key not null,
                              title character varying not null,
                              author character varying,
                              release_year integer,
                              user_id integer,
                              foreign key (user_id) references public.users (user_id)
                                  match simple on update no action on delete set null
);
create unique index books_book_name_key on books using btree (title);

create table public.users (
                              user_id integer primary key not null,
                              full_name character varying not null,
                              year_of_birth integer
);
create unique index users_full_name_key on users using btree (full_name);