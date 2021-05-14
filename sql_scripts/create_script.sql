DROP TABLE IF EXISTS transactions CASCADE;
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
    UNIQUE (from_id, to_id),
    CHECK (from_id != to_id),
    CHECK (rate > 0)
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
    account_number VARCHAR(30) UNIQUE,
    active BOOLEAN,
    available_balance NUMERIC,
    current_balance NUMERIC,
    account_type VARCHAR(10),
    interest_rate NUMERIC,
    commitment_till DATE,
    currency_id INTEGER REFERENCES currencies,
    customer_id INTEGER REFERENCES customers,
    current_account_id INTEGER REFERENCES accounts,
    CHECK (current_balance >= available_balance)
);

CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    from_id INTEGER REFERENCES accounts,
    to_id INTEGER REFERENCES accounts,
    from_account VARCHAR(30),
    to_account VARCHAR(30),
    datetime TIMESTAMP,
    completed BOOLEAN,
    type VARCHAR(20),
    amount NUMERIC,
    currency_id INTEGER REFERENCES currencies
);

CREATE TABLE payment_cards (
    id SERIAL PRIMARY KEY,
    current_account_id INTEGER REFERENCES accounts,
    card_id	VARCHAR(30) UNIQUE
);

CREATE TABLE activation_changes (
    id SERIAL PRIMARY KEY,
    active BOOLEAN,
    datetime TIMESTAMP,
    customer_id INTEGER REFERENCES customers
);

CREATE TABLE account_statements (
    id SERIAL PRIMARY KEY,
    statement TEXT,
    customer_id INTEGER REFERENCES customers,
    datetime TIMESTAMP
);


DROP INDEX IF EXISTS srcAccountTransactionsIndex;
DROP INDEX IF EXISTS destAccountTransactionsIndex;
DROP INDEX IF EXISTS customersIndex;
DROP INDEX IF EXISTS currencyRatesIndex;
DROP INDEX IF EXISTS accountsIdIndex;
DROP INDEX IF EXISTS currentAccountsIndex;
DROP INDEX IF EXISTS accountNumberIndex;
DROP INDEX IF EXISTS accountNumberTypeIndex;
DROP INDEX IF EXISTS customerAccountsTypeIndex;

CREATE INDEX srcAccountTransactionsIndex
ON transactions(to_account);

CREATE INDEX destAccountTransactionsIndex
ON transactions(from_account);

CREATE INDEX customersIndex
ON customers(birth_number);

CREATE INDEX currencyRatesIndex
ON currency_rates(from_id, to_id);

CREATE INDEX accountsIdIndex
ON accounts(id);

CREATE INDEX currentAccountsIndex
ON accounts(current_account_id);

CREATE INDEX accountNumberIndex
ON accounts(account_number);

CREATE INDEX accountNumberTypeIndex
ON accounts(account_number, account_type);

CREATE INDEX customerAccountsTypeIndex
ON accounts(customer_id, account_type);

--needed for customers statistic with larger data
CREATE INDEX activationChangesIndex
ON activation_changes(datetime);
