--liquibase formatted sql

--changeset dmatveev:1

CREATE TABLE users(
                      id         BIGSERIAL PRIMARY KEY,
                      username   VARCHAR(60)
);