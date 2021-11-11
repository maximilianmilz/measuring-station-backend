CREATE TABLE station
(
    id       varchar(8) primary key,
    date     date not null,
    target   int  not null,
    actual   int  not null,
    variance int  not null
);