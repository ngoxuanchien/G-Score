create sequence grades_seq start 1 increment by 1;

create sequence students_seq start 1 increment by 1;

create sequence subjects_seq start 1 increment by 1;

create table students (
    id bigint primary key not null default nextval('students_seq'),
    sbd varchar(10) not null,
    ma_ngoai_ngu varchar(10)
);

create table subjects (
    id bigint primary key not null default nextval('subjects_seq'),
    name varchar(255) not null
);

create table grades (
    id bigint primary key not null default nextval('grades_seq'),
    student_id bigint not null,
    subject_id bigint not null,
    score float,
    foreign key (student_id) references students(id),
    foreign key (subject_id) references subjects(id)
);
