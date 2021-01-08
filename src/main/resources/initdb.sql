create database mydb;
create user myuser with encrypted password 'mypass';
grant all privileges on database mydb to myuser;

-- List all DBs
\l
-- Connect to mydb DB
\c mydb

CREATE TABLE IF NOT EXISTS users (
    id UUID NOT NULL ,
    firstname VARCHAR(80) NOT NULL,
    lastname VARCHAR(80) NOT NULL,
    email VARCHAR(80) NOT NULL UNIQUE,
    password  VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS stock (
    id UUID NOT NULL,
    ticker VARCHAR(10) NOT NULL UNIQUE,
    company VARCHAR(80) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_stock (
    id INT NOT NULL,
    ticker_id UUID REFERENCES stock(id),
    user_id UUID REFERENCES users(id),
    purchase_timestamp TIMESTAMP NOT NULL,
    purchase_price FLOAT NOT NULL,
    PRIMARY KEY (id)
);

\d;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


