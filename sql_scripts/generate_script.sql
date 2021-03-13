-- todo

TRUNCATE TABLE currencies, currency_rates, customers, accounts, transaction_statuses, transactions, payment_cards,
    activation_changes, account_statements RESTART IDENTITY CASCADE;

INSERT INTO currencies (name)
VALUES ('USD'), ('EURO'), ('JPY'), ('GPB'), ('AUD'), ('CAD'), ('CHF');

-- INSERT INTO transaction_statuses (name)--todo nebude tu, budeme mat len status o tom ci uz prebehla boolean
-- VALUES (''), (''), ('');

--random val tables
DROP TABLE IF EXISTS first_names;
CREATE TABLE first_names (
    first_name VARCHAR(20)
);

INSERT INTO first_names (first_name)
VALUES ('Ramiro'), ('Cory'), ('Dale'), ('Michael'), ('Kevin'), ('Tressie'), ('Cassidy'), ('Colby'), ('Sarah'),
       ('Katherine'), ('Julissa'), ('Linda'), ('Troy'), ('Mary'), ('Robert'), ('Cynthia'), ('Mamie'), ('Douglas'),
       ('Shannon'), ('Rhonda'), ('Matthew'), ('Bruce'), ('Alfredo'), ('Svetlana'), ('Frankie'), ('Henry'), ('Mark'),
       ('Steven'), ('Archie'), ('Jessica'), ('Carl'), ('Lucille'), ('Sylvia'), ('Todd'), ('Juanita'), ('Wayne'),
       ('Sandra'), ('Chelsey'), ('Richard'), ('Crystal'), ('Patricia'), ('Nicholas'), ('Ronnie'), ('Eileen'),
       ('Kathleen'), ('Alfred'), ('Diana'), ('Rocky'), ('Robin'), ('William'), ('Arthur'), ('Roxanne'), ('Chas'),
       ('Anissa'), ('Karen'), ('Jane'), ('Damion'), ('Alberto'), ('Rachael'), ('Margaret'), ('Jacquelyn'), ('Maria'),
       ('Flora'), ('Cassandra'), ('Francis'), ('Aaron'), ('Pedro'), ('Sally'), ('Ruby'), ('Patty'), ('Dorothy'),
       ('David'), ('Sue'), ('Barbara'), ('Bonnie'), ('Louann'), ('Luisa'), ('Ben'), ('Peter'), ('Joe'), ('Brian'),
       ('Johnny'), ('Gene'), ('Anna'), ('Emory'), ('Loretta'), ('Huey'), ('Roger'), ('Nina'), ('Susan'), ('Carma'),
       ('Cindy'), ('Maryanne'), ('Burton'), ('Tina'), ('Bonita'), ('Kenneth'), ('James'), ('Leroy'), ('Janice');

DROP TABLE IF EXISTS last_names;
CREATE TABLE last_names (
    last_name VARCHAR(30)
);

INSERT INTO last_names (last_name)
VALUES ('Jackson'), ('Margerum'), ('Stella'), ('Lomanto'), ('Tackitt'), ('Hirsh'), ('Behr'), ('Weaver'), ('Ibarra'),
       ('Coffey'), ('Johnson'), ('Greenbaum'), ('Spencer'), ('Carter'), ('Carroll'), ('Pearce'), ('Auguste'),
       ('Fitch'), ('Lawson'), ('Layman'), ('Krueger'), ('Gracey'), ('Barker'), ('Holliday'), ('Hall'), ('Barnes'),
       ('Mccabe'), ('Sanders'), ('Lackey'), ('Leskovec'), ('Eddington'), ('Torres'), ('Pulsifer'), ('Barrows'),
       ('Smith'), ('Morin'), ('Contreras'), ('Burke'), ('Wilson'), ('Davey'), ('Arnold'), ('Phillips'), ('Pratt'),
       ('Jennings'), ('Zwagerman'), ('Burel'), ('Wibbens'), ('Curtis'), ('Myers'), ('Gionet'), ('Souder'), ('Warren'),
       ('Brinton'), ('Tate'), ('Morris'), ('Self'), ('Hissem'), ('Eyler'), ('Avella'), ('Kemp'), ('Law'), ('Haynes'),
       ('Reynolds'), ('Foster'), ('Nunez'), ('Schlegel'), ('Black'), ('Arrington'), ('Ballance'), ('Carr'), ('Ryan'),
       ('Sizemore'), ('Dao'), ('Parks'), ('Coy'), ('Stevens'), ('Perez'), ('Livingston'), ('May'), ('Caravati'),
       ('Pavich'), ('Garcia'), ('Gross'), ('Brewer'), ('Hipsher'), ('Parker'), ('Keithly'), ('Lach'), ('Healey'),
       ('Clements'), ('Mcroberts'), ('Guadian'), ('Crosier'), ('Lewis'), ('Ferranti'), ('Nettles'), ('Nestor'),
       ('Zhong'), ('Frantz'), ('Logan');

DROP TABLE IF EXISTS cities;
CREATE TABLE cities (
    city VARCHAR(30)
);

INSERT INTO cities (city)
VALUES ('Bergland, RI 89936'), ('FPO AE 23451'), ('Jenniferville, SC 41885'), ('Holmesland, VA 89735'),
       ('Port Nathan, IL 63335'), ('FPO AE 19852'), ('Callahanberg, MS 56011'), ('APO AP 80505'),
       ('North Ericbury, GA 32234'), ('East Troymouth, HI 43321'), ('North Johnton, IA 30020'),
       ('Haydenshire, MO 01810'), ('APO AA 33171'), ('West Kimberly, VT 89577'), ('Larryside, MO 35991'),
       ('East Melindaberg, IL 09149'), ('West Leahberg, OK 29153'), ('Christopherville, KY 47611'),
       ('DPO AA 26406'), ('Dawnville, MD 79524'), ('Andersonmouth, AL 43776'), ('Port Rogerside, UT 16153'),
       ('Parkchester, TX 30245'), ('Port Jason, DC 35334'), ('West Madisonland, MT 62236'), ('Jenniferport, IL 24529'),
       ('South Amy, NY 22920'), ('Mariaburgh, VA 85612'), ('Dorseyport, FL 49690'), ('APO AE 75268'),
       ('West Gregorychester, WV 78826'), ('East Jenniferfurt, MN 34828'), ('DPO AE 57938'), ('South Renee, NY 07347'),
       ('Christychester, NJ 32081'), ('West Dustinshire, OK 51878'), ('New Jeffreyton, DE 76079'),
       ('South Danielle, IL 77957'), ('New Amy, IL 88741'), ('Garcialand, WV 94853'), ('New Lynn, ID 01035'),
       ('South Williamshire, IN 50371'), ('DPO AA 74730'), ('East Patriciamouth, DC 32303'), ('Stevenport, WI 73934'),
       ('Danielmouth, DE 91187'), ('West Katherine, KS 54285'), ('Greenview, TX 76639'), ('Crystalland, NE 73329'),
       ('Cortezfurt, PA 45527'), ('Michaelberg, LA 84106'), ('DPO AA 90841'), ('Port Robert, AZ 12729'),
       ('Robertfurt, NC 65218'), ('West Cynthiastad, GA 12449'), ('West Jessica, RI 34510'),
       ('North Rebecca, FL 63575'), ('Walterton, OR 38037'), ('Lake Jamestown, LA 86415'), ('FPO AE 73194'),
       ('Fergusonfort, DC 57686'), ('West Brenda, WI 05064'), ('North Lisamouth, GA 19258'), ('South Frank, TN 72013'),
       ('Lambfurt, NC 86147'), ('Woodview, NH 40475'), ('Port Steven, OH 23957'), ('South Kristina, CA 06867'),
       ('Rhodesburgh, WA 55789'), ('Castillofort, DC 05936'), ('South Aaron, DC 10598'),
       ('North Cynthiaview, MD 88342'), ('North Scott, NM 90877'), ('FPO AE 46380'), ('Gomezbury, RI 03805'),
       ('East Patrick, KS 06728'), ('Lake Brian, OH 20539'), ('Lake Tylerberg, NJ 20310'), ('FPO AP 25751'),
       ('North Matthewmouth, CO 24499'), ('Jenkinsstad, CO 65767'), ('Phamborough, CO 95084'),
       ('Michelefurt, VA 81278'), ('East Mary, ID 64052'), ('Hayesberg, TN 86043'), ('Nicoleshire, KS 76349'),
       ('APO AP 89180'), ('Youngburgh, DC 24715'), ('Brightmouth, MD 25483'), ('Miguelview, SD 67152'),
       ('New Charles, CO 07429'), ('Travisside, WI 53445'), ('North Cheryl, NC 48024'), ('Danielleside, IN 23553'),
       ('Glennville, DE 28283'), ('North Daniel, SD 32866'), ('Angelafort, OR 82080'), ('Lake Gina, VT 97331'),
       ('Waynefort, FL 86634'), ('Tuckerhaven, DE 23517');


DROP TABLE IF EXISTS street_names;
CREATE TABLE street_names (
    street_name VARCHAR(30)
);

INSERT INTO street_names (street_name)
VALUES ('Maxwell Road Apt. 253'), ('Kristy Plaza'), ('Hart Tunnel'), ('Amy Cape'), ('Amy Light Suite 689'), ('Lopez'),
       ('Johnson Loop Apt. 168'), ('5563, Box 5050'), ('Melissa Mews'), ('Meredith Plains'), ('Jaime Islands Apt. 650'),
       ('Bender Squares'), ('Moody Branch'), ('Whitaker Corners'), ('Mcpherson Brooks Apt. 916'), ('Garcia Dam'),
       ('Christina Haven'), ('Hale Trace'), ('Daniels Gardens Suite 195'), ('9820 Box 5128'),
       ('Joshua Shores Suite 435'), ('Courtney Crossing Suite 917'), ('Cain Summit'), ('King Ridge'), ('Colon Lights'),
       ('Carr Lake'), ('Long Summit Apt. 299'), ('David Isle'), ('White Camp Apt. 820'), ('Barnett Canyon'),
       ('Hall Wall Suite 434'), ('Timothy Valleys Suite 030'), ('Tracy Stream Suite 696'), ('Daniel Viaduct Apt. 964'),
       ('Morris Points Suite 091'), ('James Mountains Suite 684'), ('Nunez Plains Apt. 753'),
       ('Hammond Squares Apt. 682'), ('Holly Circle'), ('Joseph'), ('3144 Box 8257'), ('Spencer Garden Apt. 204'),
       ('2445, Box 3674'), ('Jeffrey Summit Apt. 585'), ('Karen Fort Apt. 116'), ('1765, Box 9648'), ('Anderson Pike'),
       ('Mark Station'), ('Marissa Stream Apt. 179'), ('Eugene Oval'), ('Rowe Estate'), ('Myers Roads'),
       ('Stewart Shoals'), ('Bryan Shore'), ('Ryan Street Suite 887'), ('Donna Burg Apt. 530'), ('1824, Box 6269'),
       ('Moore Neck'), ('Russell Canyon'), ('4337, Box 8897'), ('Anthony Heights'), ('Krause Oval Apt. 423'),
       ('Lucas Circle Apt. 267'), ('Melanie Street'), ('Timothy Plaza Suite 855'), ('Graham Harbor'),
       ('Paul Circles Suite 727'), ('Larry Squares Suite 597'), ('Hernandez Mountain Suite 392'), ('William Valley'),
       ('James Route'), ('Chambers Parkways Suite 976'), ('0763, Box 4276'), ('Romero Circle Suite 904'),
       ('Steven Meadow Suite 820'), ('James Trafficway Apt. 867'), ('Brown Pines'), ('Brittany Creek'),
       ('Trevor Stravenue'), ('Fuller Throughway Suite 734'), ('Hurst Inlet'), ('Matthew Trail'),
       ('Nguyen Well Suite 087'), ('Bradley Isle'), ('Park Ways Suite 117'), ('Levine Hill'),
       ('Jon Views Apt. 081'), ('Allen'), ('Angela Flats'), ('Shelby Shores Suite 172'), ('French Stravenue Suite 027'),
       ('Steven Key'), ('Powers Court Suite 095'), ('Ashley Forest Suite 911'), ('Allen Junctions'), ('Wells Drives'),
       ('Zachary Turnpike Suite 890'), ('Tammy Roads'), ('Catherine Fields'), ('Martin Groves Apt. 237');


CREATE OR REPLACE FUNCTION random_integer() RETURNS INTEGER LANGUAGE sql AS
$$
SELECT floor((random() * 999) + 1)
$$;

CREATE OR REPLACE FUNCTION random_first_name() RETURNS VARCHAR(20) LANGUAGE sql AS
$$
SELECT first_name FROM first_names ORDER BY random() LIMIT 1
$$;

CREATE OR REPLACE FUNCTION random_last_name() RETURNS VARCHAR(30) LANGUAGE sql AS
$$
SELECT last_name FROM last_names ORDER BY random() LIMIT 1
$$;

DROP TABLE IF EXISTS streets_cities;
SELECT concat(street_name, ', ', city) AS address INTO streets_cities FROM cities CROSS JOIN street_names;


CREATE OR REPLACE FUNCTION random_address() RETURNS VARCHAR(70) LANGUAGE sql AS
$$
SELECT concat(to_char(random_integer(), 'fm000'), ' ', address) FROM streets_cities OFFSET random() * 10000 LIMIT 1
$$;

INSERT INTO customers (first_name, last_name, address)
SELECT 	random_first_name(), random_last_name(), random_address()
FROM generate_series(1, 1000);

--todo remove random functions

-- CREATE TABLE currency_rates (
--                                 id SERIAL PRIMARY KEY,
--                                 from_id INTEGER REFERENCES currencies, --random id from currencies
--                                 to_id INTEGER REFERENCES currencies,--random id from currencies
--                                 rate NUMERIC--random numeric value?
--                                 --add both sides
-- );
--
-- CREATE TABLE customers (
--                            id SERIAL PRIMARY KEY,
--                            birth_number VARCHAR(10) UNIQUE,--random numbers len 10
--                            first_name VARCHAR(20),--random name from table
--                            last_name VARCHAR(30),--random name from table
--                            address VARCHAR(50)--random addr from table
-- );
--
-- CREATE TABLE accounts (
--                           id SERIAL PRIMARY KEY,
--                           account_number VARCHAR(30) UNIQUE ,--random country 2 + numbers + something
--                           active BOOLEAN,--random
--                           available_balance NUMERIC,--random to 1000000 dollars?
--                           current_balance NUMERIC,--must be >= available
--                           account_type VARCHAR,--one from 3 values so from table
--                           interest_rate NUMERIC,--random number 0-10 depends on acc type if current type then null
--                           commitment_till DATE,--same as above but random date
--                           currency_id INTEGER REFERENCES currencies,--from curr
--                           customer_id INTEGER REFERENCES customers--from curr but must curr1!=curr2
-- );
--
-- --first gen transactions between accs, then trans on self...
-- CREATE TABLE transactions (
--                               id SERIAL PRIMARY KEY,
--                               from_id INTEGER REFERENCES accounts,--rand
--                               to_id INTEGER REFERENCES accounts,--rand depend on type
--                               from_account VARCHAR(30),--
--                               to_account VARCHAR(30),
--                               datetime TIMESTAMP,
--                               status INTEGER REFERENCES transaction_statuses,
--                               type VARCHAR(20), -- todo pripis na ucet alebo normalny presun/vyber z atm/vklad -- definovat si co bude definovane pri kazdom type
--                               amount NUMERIC,
--                               currency_id INTEGER REFERENCES currencies
-- );
--
-- CREATE TABLE payment_cards (
--                                id SERIAL PRIMARY KEY,
--                                card_id	VARCHAR(30) UNIQUE--random 30 len string
-- );
--
-- CREATE TABLE activation_changes (
--                                     id SERIAL PRIMARY KEY,
--                                     active BOOLEAN,--random
--                                     datetime TIMESTAMP,--random
--                                     customer_id INTEGER REFERENCES customers--rand
-- );
--
-- CREATE TABLE account_statements (
--                                     id SERIAL PRIMARY KEY,
--                                     statement TEXT,--something??
--                                     customer_id INTEGER REFERENCES customers--rand id
--     --todo add timestamp if needed
-- );


/***************************************************************************************************************************/
/**
 * Author:  Alexander Šimko
 * Created: Apr 6, 2017
 */

-- Najprv zmazeme vsetky data. Mohli by sme to robit rucne cez DELETE po jednej tabulke.
-- Museli by sme ale brat do uvahy zavislosti medzi tabulkami. Takto to za nas spravi TRUNCATE.

-- truncate table customers, plans, packages, user_packages, numbers, calls, rates, operations, items, invoices, payments restart identity cascade;
--
--
-- ---- pomocne tabulky na generovanie dat
--
-- -- first names
--
-- drop table if exists first_names cascade;
-- create table first_names
-- (
--     first_name varchar
-- );
--
-- insert into first_names (first_name)
-- values	('James'), ('Willie'), ('Chad'), ('Zachary'), ('Mathew'),
--           ('John'), ('Ralph'), ('Jacob'), ('Corey'), ('Tyrone'),
--           ('Robert'), ('Lawrence'), ('Lee'), ('Herman'), ('Darren'),
--           ('Michael'), ('Nicholas'), ('Melvin'), ('Maurice'), ('Lonnie'),
--           ('William'), ('Roy'), ('Alfred'), ('Vernon'), ('Lance'),
--           ('David'), ('Benjamin'), ('Kyle'), ('Roberto'), ('Cody');
--
-- -- last names
--
-- drop table if exists last_names cascade;
-- create table last_names
-- (
--     last_name varchar
-- );
--
-- insert into last_names (last_name)
-- values	('Smith'), ('Jones'), ('Taylor'), ('Williams'), ('Brown'),
--           ('Davies'), ('Evans'), ('Wilson'), ('Thomas'), ('Roberts'),
--           ('Johnson'), ('Lewis'), ('Walker'), ('Robinson'), ('Wood'),
--           ('Thompson'), ('White'), ('Watson'), ('Jackson'), ('Wright'),
--           ('Green'), ('Harris'), ('Cooper'), ('King'), ('Lee'),
--           ('Martin'), ('Clarke'), ('James'), ('Morgan'), ('Hughes'),
--           ('Edwards'), ('Hill'), ('Moore'), ('Clark'), ('Harrison'),
--           ('Scott'), ('Young'), ('Morris'), ('Hall'), ('Ward'),
--           ('Turner'), ('Carter'), ('Phillips'), ('Mitchell'), ('Patel'),
--           ('Adams'), ('Campbell'), ('Anderson'), ('Allen'), ('Cook');
--
-- -- street names
--
-- drop table if exists street_names cascade;
-- create table street_names
-- (
--     street_name varchar
-- );
--
-- insert into street_names (street_name)
-- values	('main.Main Street'), ('Church Street'), ('main.Main Street North'), ('main.Main Street South'), ('Elm Street'),
--           ('High Street'), ('Washington Street'), ('main.Main Street West'), ('main.Main Street East'), ('Park Avenue'),
--           ('Walnut Street'), ('2nd Street'), ('Chestnut Street'), ('Broad Street'), ('Maple Avenue'),
--           ('Maple Street'), ('Center Street'), ('Oak Street'), ('Pine Street'), ('River Road');
--
-- -- city names
--
-- drop table if exists city_names cascade;
-- create table city_names
-- (
--     city_name varchar
-- );
--
-- insert into city_names (city_name)
-- values	('New York'), ('Los Angeles'), ('Chicago'), ('Houston'), ('Philadelphia'),
--           ('Phoenix'), ('San Antonio'), ('San Diego'), ('Dallas'), ('San Jose'),
--           ('Austin'), ('Jacksonville'), ('San Francisco'), ('Indianapolis'), ('Columbus'),
--           ('Fort Worth'), ('Charlotte'), ('Detroit'), ('El Paso'), ('Seattle');
--
-- ---- hlavne tabulky
--
-- -- customers
--
-- create or replace function random_first_name() returns varchar language sql as
-- $$
-- select first_name from first_names tablesample system_rows(10) order by random() limit 1
-- $$;
--
-- create or replace function random_last_name() returns varchar language sql as
-- $$
-- select last_name from last_names tablesample system_rows(10) order by random() limit 1
-- $$;
--
-- create or replace function random_street_name() returns varchar language sql as
-- $$
-- select street_name from street_names tablesample system_rows(10) order by random() limit 1
-- $$;
--
-- create or replace function random_city_name() returns varchar language sql as
-- $$
-- select city_name from city_names tablesample system_rows(10) order by random() limit 1
-- $$;
--
--
-- insert into customers (first_name, last_name, birth_number, address)
-- select	random_first_name(),
--           random_last_name(),
--           floor(random() * 200) as birth_number, -- TODO: lepsie
--           random_street_name() || ' ' || floor(random() * 100) + 1 || ', ' || random_city_name() as address
-- from generate_series(1, 1000) as seq(i);
--
--
-- -- numbers
--
-- insert into numbers (number, is_emergency_number)
-- select	i, false
-- from generate_series(1, 1000) as seq(i);
--
-- -- rates
--
-- insert into rates (voice_rate, message_rate)
-- select	random() * 10,
--           random() * 3
-- from generate_series(1, 20);
--
-- -- plans
--
-- -- Najprv vlozime iba spoloce data.
-- -- Historicke plany nevytvarame, iba aktualne.
--
-- create or replace function random_customer_id() returns integer language sql as
-- $$
-- select id from customers tablesample system_rows(10) order by random() limit 1
-- $$;
--
-- create or replace function random_rate_id() returns integer language sql as
-- $$
-- select id from rates tablesample system_rows(10) order by random() limit 1
-- $$;
--
--
-- insert into plans (valid_from, valid_till, customer_id, plan_type, number_id, rate_id)
-- select  date '2016-01-01' + floor(random()*300)::integer as valid_from,
--         null::date as valid_till, -- plan je aktualny
--         random_customer_id(),
--         case when random() < 0.5 then 'postpay' else 'prepay' end as plan_type,
--         numbers.id as number_id,
--         random_rate_id()
-- from numbers; -- vytvorime tolko planov kolko cisel mame, kazdemu dame jedno cislo
--
--
-- update plans set
--                  is_dodger = random() < 0.2, -- TODO: nastavit podla realnych platieb
--                  billing_day = floor(random() * 28)::integer
-- where plan_type = 'postpay';
--
-- -- credit sa nastavuje nizsie
--
-- -- calls
--
-- create or replace function random_plan(dummy_in integer) returns table (id integer, number_id integer, rate_id integer, valid_from date) language sql as
-- $$
-- select id, number_id, rate_id, valid_from from plans tablesample system_rows(10) order by random() limit 1
-- $$;
--
-- create or replace function random_number_id_different(in_id integer) returns integer language sql as
-- $$
--     -- najprv nahodne vezme 10 riadkov a potom aplikuje podmienku
-- -- kedze kazdy riadok ma ine id, tak ich urcite 9 ostane a teda vzdy nieco vratime
-- select id from numbers tablesample system_rows(10) WHERE id != in_id order by random() limit 1
-- $$;
--
--
-- insert into calls (plan_id, source_number_id, destination_number_id, rate_id, start_datetime, duration)
-- select	plans.id,
--           plans.number_id,
--           random_number_id_different(plans.number_id),
--           plans.rate_id,
--           plans.valid_from + random()*(current_timestamp - plans.valid_from),
--           floor(random()*300)::integer + 1
-- from generate_series(1,1000) as seq(i)
--          cross join lateral random_plan(i) as plans; -- prenasame id, aby sme zakazdnym mali nove volanie
--
--
-- -- packages
-- -- Balicky su pevne dane dopredu. Pre jednoduchost system rata iba s jednym balickom.
--
-- insert into packages (name, price)
-- values ('Zadarmo na lubovolne cislo', 3);
--
-- -- user packages
-- -- User packages uz negenerujem, a tym padom ani package charges.
--
--
-- -- call charges
--
-- insert into operations (datetime, amount, plan_id, operation_type, call_id)
-- select
--         c.start_datetime + make_interval(0,0,0,0,0,duration,0) as datetime,
--         duration * voice_rate as amount,
--         p.id as plan_id,
--         'call_charge' as operation_type,
--         c.id as call_id
-- from calls as c
--          join plans as p on c.plan_id = p.id
--          join rates as r on c.rate_id = r.id;
--
--
-- -- recharge
-- -- Pre prepay plan nabijeme kredit na zaciatku tak aby stacil na vsetky hovory, ktore boli vykonane
--
-- insert into operations(datetime, amount, plan_id, operation_type)
-- select	min(c.start_datetime) as start_datetime,
--           sum(o.amount) as amount,
--           p.id as plan_id,
--           'recharge'
-- from plans as p
--          join calls as c on c.plan_id = p.id
--          join operations as o on o.call_id = c.id
-- where p.plan_type = 'prepay' and o.operation_type = 'call_charge'
-- group by p.id;
--
-- -- cize po existujucich volaniach by mali mat kredit nulovy
-- -- takze si vygenerujeme nahodny kredit a zaroven vlozite take nabitia
--
-- update plans set
--     credit = floor(random()*300)
-- where plan_type = 'prepay';
--
-- insert into operations(datetime, amount, plan_id, operation_type)
-- select	current_timestamp, credit, id, 'recharge'
-- from plans
-- where plan_type = 'prepay';
--
-- -- invoices
-- -- Invoices uz vynechavam.
--
--
-- ---- nakoniec zmazeme pomocne veci
--
-- drop table first_names, last_names, street_names, city_names cascade;
--
-- drop function random_first_name();
-- drop function random_last_name();
-- drop function random_street_name();
-- drop function random_city_name();
-- drop function random_customer_id();
-- drop function random_rate_id();
-- drop function random_plan(integer);
-- drop function random_number_id_different(integer);