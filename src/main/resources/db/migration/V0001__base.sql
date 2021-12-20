CREATE TABLE station
(
    id       int  not null,
    date     date not null,
    target   int  not null,
    actual   int  not null,
    variance int  not null
);

ALTER TABLE station
    ADD PRIMARY KEY (`id`);

ALTER TABLE station
    MODIFY id int NOT NULL AUTO_INCREMENT;
