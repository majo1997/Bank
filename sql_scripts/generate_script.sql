TRUNCATE TABLE currencies, currency_rates, customers, accounts, transactions, payment_cards,
    activation_changes, account_statements RESTART IDENTITY CASCADE;

INSERT INTO currencies (name)
VALUES ('USD'), ('EURO'), ('JPY'), ('GPB'), ('AUD'), ('CAD'), ('CHF');

INSERT INTO currency_rates(from_id, to_id, rate)
VALUES (1, 4, 5), (1, 2, 2.5), (1, 3, 3), (2, 4, 1.8), (2, 3, 8), (4, 5, 9);

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

CREATE OR REPLACE FUNCTION random_boolean() RETURNS BOOLEAN LANGUAGE sql AS
$$
SELECT random() < 0.5
$$;

CREATE OR REPLACE FUNCTION random_balance() RETURNS NUMERIC LANGUAGE sql AS
$$
SELECT round(random()::NUMERIC * 250000, 3)
$$;

CREATE OR REPLACE FUNCTION random_interest_rate() RETURNS NUMERIC LANGUAGE sql AS
$$
SELECT round(random()::NUMERIC * 10, 3)
$$;

CREATE OR REPLACE FUNCTION random_date() RETURNS DATE LANGUAGE sql AS
$$
select  date(timestamp '2018-01-10' + random() * (timestamp '2021-3-14' - timestamp '2018-01-10'))
$$;

CREATE OR REPLACE FUNCTION random_integer() RETURNS INTEGER LANGUAGE sql AS
$$
SELECT floor((random() * 999) + 1)
$$;

--generates random 30 digit card id
CREATE OR REPLACE FUNCTION random_card_id() RETURNS VARCHAR(30) LANGUAGE sql AS
$$
SELECT array_to_string(ARRAY(SELECT chr((48 + round(random() * 9))::INTEGER) FROM generate_series(1, 30)), '')
$$;

--generates random 30 digit account number
CREATE OR REPLACE FUNCTION random_account_number() RETURNS VARCHAR(30) LANGUAGE sql AS
$$
SELECT concat('SK', array_to_string(ARRAY(SELECT chr((48 + round(random() * 9))::INTEGER) FROM generate_series(1, 28)), ''))
$$;

--generates random 10 digit birth number
CREATE OR REPLACE FUNCTION random_birth_number() RETURNS VARCHAR(10) LANGUAGE sql AS
$$
SELECT lpad(floor((random() * 10000000000))::VARCHAR, 10, '0')
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
SELECT concat(lpad(random_integer()::VARCHAR, 3, '0'), ' ', address) FROM streets_cities TABLESAMPLE BERNOULLI(1) LIMIT 1;

-- SELECT concat(lpad(random_integer()::VARCHAR, 3, '0'), ' ', address) FROM streets_cities tablesample system_rows(10) order by random() limit 1
-- SELECT concat(lpad(random_integer()::VARCHAR, 3, '0'), ' ', address)
-- FROM streets_cities
-- OFFSET floor(random() * (SELECT count(*) FROM streets_cities))
-- LIMIT 1
$$;

--todo insert in loop because can be same birth number and the it prints err
INSERT INTO customers (birth_number, first_name, last_name, address)
SELECT 	random_birth_number(), random_first_name(), random_last_name(), random_address()
FROM generate_series(1, 10000)
ON CONFLICT DO NOTHING;

--todo add active status to all customers first
--todo continue iterating throught date and check if last status was false or tr and then decide

-- do $$
--     begin
--         for r in 1..1000 loop
--                 INSERT INTO accounts (account_number, active, available_balance, current_balance, account_type)
--                 SELECT random_account_number(), random_boolean(), random_balance(), random_balance(), 'current';
--             end loop;
--     end;
-- $$;
--todo mozno negenerovat cisla uctov nahodne ale pridavat o 1 vacsie a vacsie kazdemu dalsiemu, a na current balance pridat ze generovanie cisla iba do velkosti currentbalance... alebo rovnake budu...
INSERT INTO accounts (account_number, active, available_balance, current_balance, account_type, currency_id, customer_id)
SELECT random_account_number(), random_boolean(), 500, 500, 'CURRENT', 1, 1--todo asi nechat rovnake hodnoty current a available balance/ ale generovat nahodne
FROM generate_series(1, 10000)
ON CONFLICT DO NOTHING;

INSERT INTO accounts (account_number, active, available_balance, current_balance, account_type, interest_rate,
                      commitment_till, currency_id, customer_id)
SELECT random_account_number(), random_boolean(), 500, 500, 'TERM', random_interest_rate(),
       random_date(), 1, 1
FROM generate_series(1, 10000);

INSERT INTO accounts (account_number, active, available_balance, current_balance, account_type, interest_rate, currency_id, customer_id)
SELECT random_account_number(), random_boolean(), 500, 500, 'SAVINGS', random_interest_rate(), 1, 1--todo asi nebude random boolean pre active
FROM generate_series(1, 10000);


INSERT INTO activation_changes(active, datetime, customer_id)
VALUES (TRUE, timestamp'2017-05-04', 1), (TRUE, timestamp'2018-05-04', 2), (FALSE, timestamp'2019-06-04', 1), (TRUE, timestamp'2021-06-04', 1);


INSERT INTO payment_cards (card_id)
SELECT random_card_id()
FROM generate_series(1, 10000);
--todo remove random functions
--todo count customers active at the end of quad year session
