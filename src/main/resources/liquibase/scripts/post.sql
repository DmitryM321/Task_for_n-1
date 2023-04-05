--liquibase formatted sql

--changeset dmatveev:1

CREATE TABLE post(
                     id      BIGSERIAL PRIMARY KEY,
                     title   VARCHAR(60) NOT NULL,
                     body    VARCHAR(255) NOT NULL,
                     user_id BIGINT REFERENCES users(id)
);