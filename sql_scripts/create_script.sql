--todo table constraints uniq, not null
DROP TABLE IF EXISTS transactions CASCADE;
-- DROP TABLE IF EXISTS transaction_statuses CASCADE;
DROP TABLE IF EXISTS payment_cards CASCADE;
DROP TABLE IF EXISTS activation_changes CASCADE;
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS account_statements CASCADE;
DROP TABLE IF EXISTS currency_rates CASCADE;
DROP TABLE IF EXISTS currencies CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;

CREATE TABLE currencies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE
);

CREATE TABLE currency_rates (
    id SERIAL PRIMARY KEY,
    from_id INTEGER REFERENCES currencies,
    to_id INTEGER REFERENCES currencies,
    rate NUMERIC,
    UNIQUE (from_id, to_id), --todo always add both ways...
    CHECK (from_id != to_id) --todo in transactions same??
);

CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    birth_number VARCHAR(10) UNIQUE,
    first_name VARCHAR(20),
    last_name VARCHAR(30),
    address VARCHAR(70)
);

CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(30) UNIQUE ,--uniq
    active BOOLEAN,
    available_balance NUMERIC,
    current_balance NUMERIC,
    account_type VARCHAR,--acc type
    interest_rate NUMERIC,
    commitment_till DATE,
    currency_id INTEGER REFERENCES currencies,
    customer_id INTEGER REFERENCES customers,
    current_account_id INTEGER REFERENCES accounts, --todo pre savings accounts
    CHECK (current_balance >= available_balance)
);

--todo remove this
-- CREATE TABLE transaction_statuses (
--     id SERIAL PRIMARY KEY,
--     name VARCHAR(20) UNIQUE
-- );

CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    from_id INTEGER REFERENCES accounts,
    to_id INTEGER REFERENCES accounts,
    from_account VARCHAR(30),
    to_account VARCHAR(30),
    datetime TIMESTAMP,
    completed BOOLEAN,
    type VARCHAR(20), -- todo pripis na ucet alebo normalny presun/vyber z atm/vklad -- definovat si co bude definovane pri kazdom type
    amount NUMERIC,
    currency_id INTEGER REFERENCES currencies
);

CREATE TABLE payment_cards (
    id SERIAL PRIMARY KEY,
    --current_account_id INTEGER REFERENCES accounts
    card_id	VARCHAR(30) UNIQUE
);

CREATE TABLE activation_changes (
    id SERIAL PRIMARY KEY,
    active BOOLEAN,
    datetime TIMESTAMP,
    customer_id INTEGER REFERENCES customers
);--todo pridat active a now ak sa prida novy customer

CREATE TABLE account_statements (
    id SERIAL PRIMARY KEY,
    statement TEXT,--unknown format
    customer_id INTEGER REFERENCES customers
    --todo add timestamp if needed
);

--todo create indexes for columns used for search...