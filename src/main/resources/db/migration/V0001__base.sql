CREATE TABLE station
(
    id       bigint primary key,
    date     date not null,
    target   int  not null,
    actual   int  not null,
    variance int  not null
);
