DROP TABLE ServerLog IF EXISTS;
CREATE TABLE ServerLog (
    Id varchar(10) PRIMARY KEY,
    StartTime bigint  NULL,
    EndTime bigint  NULL,
    Duration bigint  NULL,
    Host varchar(10) NULL,
    Type varchar(14) NULL,
    Alert BOOLEAN DEFAULT FALSE NOT NULL
);