CREATE TABLE station
(
    id       bigserial primary key,
    date     date not null,
    target   int  not null,
    actual   int  not null,
    variance int  not null
);
