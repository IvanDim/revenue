--
-- Database schema
--
DROP TABLE IF EXISTS company;
CREATE TABLE company (
                         "name" varchar(200) NOT NULL,
                         "value" INT NOT NULL,
                         PRIMARY KEY ("name")
);
