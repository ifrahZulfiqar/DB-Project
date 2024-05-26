create database ISMS
use ISMS

create table PRODUCT(
P_NAME varchar(50),
P_ID varchar(10) PRIMARY KEY,
P_PRICE int,
P_QUANTITY INT,
P_PURCHASE INT,
S_ID VARCHAR(10) FOREIGN KEY REFERENCES SUPPLIER(S_ID),
P_TYPE VARCHAR(10),
P_WEIGHT INT
)

CREATE TABLE SUPPLIER(
S_ID VARCHAR(10) PRIMARY KEY,
S_NAME VARCHAR(30),
S_PHONE BIGINT
)

CREATE TABLE CUSTOMER(
C_ID VARCHAR(10) PRIMARY KEY,
C_NAME VARCHAR(20),
C_PHONE BIGINT,
C_EMAIL VARCHAR(50)
)

CREATE TABLE [ORDER](
O_ID VARCHAR(10) PRIMARY KEY,
C_ID VARCHAR(10),
O_TIME TIMESTAMP,
O_METHOD VARCHAR(5),
O_DISCOUNT INT,
E_ID VARCHAR(10) FOREIGN KEY REFERENCES CUSTOMER(C_ID),
O_STATUS VARCHAR(10)
)

CREATE TABLE PRO_ORD(
O_ID VARCHAR(10) FOREIGN KEY REFERENCES [ORDER](O_ID) ,
P_ID VARCHAR(10) FOREIGN KEY REFERENCES PRODUCT(P_ID),
PO_QUANTITY INT,
PO_PRICE INT,
PRIMARY KEY(O_ID, P_ID)
)



CREATE TABLE PURCHASE(
PUR_ID VARCHAR(10) PRIMARY KEY,
S_ID VARCHAR(10) FOREIGN KEY REFERENCES SUPPLIER(S_ID),
PUR_TIME DATETIME,
PUR_METHOD VARCHAR(5),
PUR_DISCOUNT INT,
E_ID VARCHAR(10) FOREIGN KEY REFERENCES EMPLOYEE(E_ID),
)

CREATE TABLE PRO_PUR(
PUR_ID VARCHAR(10) FOREIGN KEY REFERENCES PURCHASE(PUR_ID),
P_ID VARCHAR(10) FOREIGN KEY REFERENCES PRODUCT(P_ID),
PUR_QUANTITY INT,
PUR_PRICE INT,
PRIMARY KEY(PUR_ID, P_ID)
)

CREATE TABLE EMPLOYEE(
E_ID VARCHAR(10) PRIMARY KEY,
E_NAME VARCHAR(20),
E_PHONE BIGINT,
E_DESIGNATION VARCHAR(8),
E_SALARY INT,
E_PASS VARCHAR(50),
E_USERNAME VARCHAR(30)
)
/* 
CREATE TABLE [CHANGES] (
P_ID VARCHAR(10) FOREIGN KEY REFERENCES PRODUCT(P_ID),
[C_TIME] TIMESTAMP,
)

CREATE TABLE PRODUCT_CHANGES (

PREV_NAME VARCHAR(50),
NEW_NAME VARCHAR(50),
PREV_PURCHASE INT,
NEW_PURCHASE INT,
PREV_PRICE INT,
NEW_PRICE INT,
PREV_WEIGHT FLOAT,
NEW_WEIGHT FLOAT,
PREV_TYPE VARCHAR(1),
NEW_TYPE VARCHAR(1),
PREV_PRESET_VALUE FLOAT,
NEW_PRESET_VALUE FLOAT,
)
*/

ALTER TABLE PRODUCT
ALTER COLUMN P_NAME VARCHAR(50)

select * from EMPLOYEE

alter table EMPLOYEE
add E_QUESTION varchar(30)

update EMPLOYEE
set E_QUESTION='cats'
where E_NAME='John Smith'

update EMPLOYEE
set E_QUESTION='dogs'
where E_NAME='Jane Doe'

select * from EMPLOYEE

-- INSERTING DATA INTO TABLES
INSERT INTO SUPPLIER (S_ID, S_NAME, S_PHONE)
VALUES ('S000000001', 'Acme Inc.', 923001234567),
       ('S000000002', 'Beta Industries', 923001234568),
       ('S000000003', 'Gamma Corp', 923001234569),
       ('S000000004', 'Delta Co', 923001234570),
       ('S000000005', 'Epsilon LLC', 923001234571),
       ('S000000006', 'Zeta Enterprises', 923001234572),
       ('S000000007', 'Eta Corp', 923001234573);

INSERT INTO CUSTOMER (C_ID, C_NAME, C_PHONE, C_EMAIL)
VALUES 
('C000000001', 'John Smith', 923001234567, 'john.smith@example.com'),
('C000000002', 'Jane Doe', 923001234568, 'jane.doe@example.com'),
('C000000003', 'Bob Johnson', 923001234569, 'bob.johnson@example.com'),
('C000000004', 'Alice Wong', 923001234570, 'alice.wong@example.com'),
('C000000005', 'Tom Wilson', 923001234571, 'tom.wilson@example.com'),
('C000000006', 'Sara Lee', 923001234572, 'sara.lee@example.com'),
('C000000007', 'Alex Kim', 923001234573, 'alex.kim@example.com'),
('C000000008', 'Emily Davis', 923001234574, 'emily.davis@example.com'),
('C000000009', 'Daniel Park', 923001234575, 'daniel.park@example.com'),
('C000000010', 'Kelly Brown', 923001234576, 'kelly.brown@example.com'),
('C000000011', 'George Taylor', 923001234577, 'george.taylor@example.com'),
('C000000012', 'Jenny Lee', 923001234578, 'jenny.lee@example.com'),
('C000000013', 'Mark Johnson', 923001234579, 'mark.johnson@example.com'),
('C000000014', 'Linda Wilson', 923001234580, 'linda.wilson@example.com'),
('C000000015', 'David Jones', 923001234581, 'david.jones@example.com'),
('C000000016', 'Cathy Kim', 923001234582, 'cathy.kim@example.com'),
('C000000017', 'Andrew Lee', 923001234583, 'andrew.lee@example.com'),
('C000000018', 'Grace Kim', 923001234584, 'grace.kim@example.com'),
('C000000019', 'Timothy Davis', 923001234585, 'timothy.davis@example.com'),
('C000000020', 'Ella Wilson', 923001234586, 'ella.wilson@example.com'),
('C000000021', 'Steven Lee', 923001234587, 'steven.lee@example.com'),
('C000000022', 'Amy Smith', 923001234588, 'amy.smith@example.com'),
('C000000023', 'Kevin Johnson', 923001234589, 'kevin.johnson@example.com'),
('C000000024', 'Sophie Brown', 923001234590, 'sophie.brown@example.com'),
('C000000025', 'Jacob Taylor', 923001234591, 'jacob.taylor@example.com'),
('C000000026', 'Olivia Lee', 923001234592, 'olivia.lee@example.com'),
('C000000027', 'Ryan Kim', 923001234593, 'ryan.kim@example.com'),
('C000000028', 'Isabella Davis', 923001234594, 'isabella.davis@example.com'),
('C000000029', 'William Wilson', 923001234595, 'william.wilson@example.com');

INSERT INTO EMPLOYEE(E_ID, E_NAME, E_PHONE, E_DESIGNATION, E_SALARY)
VALUES 
('E000000001', 'John Smith', 923334567890, 'manager', 100000),
('E000000002', 'Jane Doe', 923219876543, 'cashier', 50000),
('E000000003', 'Bob Johnson', 923456789123, 'worker', 25000),
('E000000004', 'Alice Wong', 923123456789, 'worker', 25000),
('E000000005', 'Tom Wilson', 923001234567, 'worker', 25000);
	
INSERT INTO PRODUCT(P_ID, P_NAME, P_PRICE, P_QUANTITY, P_PURCHASE, S_ID, P_WEIGHT, P_TYPE)
VALUES
('P000000001', 'Apple', 50, 100, 40, 'S000000001', 1, 'W'),
('P000000002', 'Banana', 30, 200, 25, 'S000000003', 12, 'Q'),
('P000000003', 'Orange', 60, 150, 50, 'S000000002', 1, 'W'),
('P000000004', 'Grapes', 80, 100, 70, 'S000000002', 1, 'W'),
('P000000005', 'Mango', 100, 50, 80, 'S000000003', 1, 'W'),
('P000000006', 'Pineapple', 120, 70, 100, 'S000000003', 1, 'W'),
('P000000007', 'Watermelon', 80, 30, 60, 'S000000004', 1, 'W'),
('P000000008', 'Strawberry', 150, 80, 120, 'S000000004', 1, 'W'),
('P000000009', 'Milk', 80, 150, 70, 'S000000005', 1, 'Q'),
('P000000010', 'Butter', 200, 50, 150, 'S000000005', 1, 'W'),
('P000000011', 'Cheese', 300, 30, 250, 'S000000005', 1, 'W'),
('P000000012', 'Bread Loaf', 40, 200, 30, 'S000000006', 1, 'Q'),
('P000000013', 'Eggs', 100, 100, 80, 'S000000006', 12, 'Q'),
('P000000014', 'Chicken', 300, 50, 250, 'S000000007', 1, 'W'),
('P000000015', 'Beef', 400, 40, 300, 'S000000007', 1, 'W'),
('P000000016', 'Mutton', 500, 30, 400, 'S000000007', 1, 'W'),
('P000000017', 'Rice', 60, 300, 40, 'S000000002', 1, 'W'),
('P000000018', 'Flour', 40, 250, 30, 'S000000004', 1, 'W'),
('P000000019', 'Sugar', 50, 200, 35, 'S000000003', 1, 'W'),
('P000000020', 'Salt', 20, 300, 10, 'S000000006', 1, 'W'),
('P000000021', 'Tomato Ketchup', 150, 50, 100, 'S000000007', 1, 'Q'),
('P000000022', 'Mayonnaise', 200, 35, 150, 'S000000004', 1, 'Q'),
('P000000023', 'Burger Bun', 10, 200, 7, 'S000000007', 1, 'Q'),
('P000000024', 'Cheese Slice', 50, 100, 40, 'S000000002', 25, 'Q'),
('P000000025', 'Cheddar Cheese', 600, 20, 550, 'S000000001', 0.5, 'W'),
('P000000026', 'Mozzarella Cheese', 700, 20, 600, 'S000000002', 0.5, 'W'),
('P000000027', 'Olives', 80, 50, 70, 'S000000005', 0.5, 'W'),
('P000000028', 'Pickles', 100, 40, 80, 'S000000003', 0.5, 'W'),
('P000000029', 'Chicken Tikka', 400, 10, 350, 'S000000004', 1, 'W'),
('P000000030', 'Chicken Seekh Kabab', 450, 10, 400, 'S000000007', 6, 'Q'),
('P000000031', 'Beef Burger Patty', 150, 50, 120, 'S000000006', 4, 'Q'),
('P000000032', 'Beef Kofta', 300, 10, 250, 'S000000001', 1, 'W'),
('P000000033', 'Beef Chapli Kabab', 400, 10, 350, 'S000000001', 1, 'W'),
('P000000034', 'Chicken Wings', 200, 30, 170, 'S000000004', 0.5, 'W'),
('P000000035', 'Chicken Drumsticks', 250, 25, 200, 'S000000002', 0.5, 'W'),
('P000000036', 'Mutton Chops', 700, 15, 600, 'S000000001', 0.5, 'W'),
('P000000037', 'Mutton Karahi', 1000, 10, 850, 'S000000002', 1, 'W'),
('P000000038', 'Beef Karahi', 800, 10, 700, 'S000000005', 1, 'W'),
('P000000039', 'Chicken Biryani', 350, 20, 300, 'S000000001', 1, 'Q'),
('P000000040', 'Beef Biryani', 500, 15, 450, 'S000000006', 1, 'Q');

INSERT INTO PURCHASE (PUR_ID, PUR_METHOD, PUR_TIME, PUR_DISCOUNT, E_ID, S_ID)
VALUES 
('PUR0000001', 'cash', '2022-05-11 14:30:00', 5, 'E000000002', 'S000000001'),
('PUR0000002', 'card', '2022-06-11 12:00:00',7, 'E000000002', 'S000000002'),
('PUR0000003', 'cash', '2022-07-02 17:0:00', 2, 'E000000001', 'S000000003'),
('PUR0000004', 'cash', '2022-08-06 11:30:00', 0, 'E000000002', 'S000000004'),
('PUR0000005', 'card', '2022-09-08 12:30:00', 8, 'E000000001', 'S000000005'),
('PUR0000006', 'cash', '2022-10-01 19:15:00', 10, 'E000000002', 'S000000006'),
('PUR0000007', 'card', '2022-11-02 14:30:00', 2, 'E000000002', 'S000000007');


INSERT INTO PRO_PUR (PUR_ID, P_ID, PUR_PRICE, PUR_QUANTITY)
VALUES
('PUR0000001', 'P000000014', 40, 100),
('PUR0000001', 'P000000025', 550, 20),
('PUR0000001', 'P000000032', 250, 10),
('PUR0000001', 'P000000033', 350, 10),
('PUR0000001', 'P000000036', 600, 15),
('PUR0000001', 'P000000039', 300, 20),
('PUR0000002', 'P000000003', 50, 150),
('PUR0000002', 'P000000004', 70, 100),
('PUR0000002', 'P000000017', 40, 300),
('PUR0000002', 'P000000024', 40, 100),
('PUR0000002', 'P000000026', 600, 20),
('PUR0000002', 'P000000035', 200, 25),
('PUR0000002', 'P000000037', 850, 10),
('PUR0000003', 'P000000002', 25, 200),
('PUR0000003', 'P000000005', 80, 50),
('PUR0000003', 'P000000006', 100, 70),
('PUR0000003', 'P000000019', 35, 200),
('PUR0000003', 'P000000028', 80, 40),
('PUR0000004', 'P000000007', 60, 30),
('PUR0000004', 'P000000008', 120, 80),
('PUR0000004', 'P000000018', 30, 250),
('PUR0000004', 'P000000022', 150, 35),
('PUR0000004', 'P000000029', 350, 10),
('PUR0000004', 'P000000034', 170, 30),
('PUR0000005', 'P000000009', 70, 150),
('PUR0000005', 'P000000010', 150, 50),
('PUR0000005', 'P000000011', 250, 30),
('PUR0000005', 'P000000027', 70, 50),
('PUR0000005', 'P000000038', 700, 10),
('PUR0000005', 'P000000024', 40, 100),
('PUR0000006', 'P000000012', 30, 200),
('PUR0000006', 'P000000013', 80, 100),
('PUR0000006', 'P000000020', 10, 300),
('PUR0000006', 'P000000031', 120, 50),
('PUR0000006', 'P000000040', 450, 15),
('PUR0000007', 'P000000014', 250, 50),
('PUR0000007', 'P000000015', 300, 40),
('PUR0000007', 'P000000016', 400, 30),
('PUR0000007', 'P000000021', 100, 50),
('PUR0000007', 'P000000023', 7, 200),
('PUR0000007', 'P000000030', 400, 10);


select p.P_ID, count(p.P_ID) as [Number of purchases], p.PUR_ID from PRO_PUR p
inner join PRODUCT on PRODUCT.P_ID = p.P_ID
group by p.P_ID, p.PUR_ID having p.P_ID = 'P000000024'

update PRO_PUR
set P_ID = 'P000000001'
where PRO_PUR.P_ID = 'P000000014' and PRO_PUR.PUR_ID = 'PUR0000001'

select * from EMPLOYEE

update EMPLOYEE
set E_USERNAME = 'tom.wilson', E_PASS='TOM123' where E_ID='E000000005'

update PRODUCT
set P_WEIGHT=0.5 where P_WEIGHT=0

alter table PRODUCT
alter column P_WEIGHT FLOAT

alter table PRODUCT
alter column P_QUANTITY FLOAT

alter table PRODUCT
add P_AVAILABILITY varchar(10)

select * from PRODUCT

update PRODUCT
set P_AVAILABILITY='available'

create table [EMPLOYEE DETAILS] (
E_ID varchar(10),
E_PASS varchar(20),
E_QUESTION varchar(20),
E_USERNAME varchar(30),
CONSTRAINT ED_PK PRIMARY KEY (E_ID),
CONSTRAINT ED_FK FOREIGN KEY (E_ID) REFERENCES EMPLOYEE(E_ID)
)

select * from EMPLOYEE
select * from [EMPLOYEE DETAILS]

INSERT INTO [EMPLOYEE DETAILS] (E_ID, E_PASS, E_QUESTION, E_USERNAME)
values
('E000000001', 'a1s2d3f4', 'john.smith', 'cats'),
('E000000002', 'JANE123', 'jane.doe', 'dogs'),
('E000000003', 'BOB123', 'bob.johnson', 'sheeps'),
('E000000004', 'ALICE123', 'alice.wong', 'parrots'),
('E000000005', 'TOM123', 'tom.wilson', 'lions')


create table [PRODUCT DETAILS](
P_ID VARCHAR (10),
P_QUANTITY INT CHECK (P_QUANTITY >= 0) ,
P_WEIGHT FLOAT,
P_TYPE VARCHAR (1) CHECK (P_TYPE = 'W' OR P_TYPE = 'Q'),
P_PRESET INT,
P_AVAILABILITY VARCHAR(20) DEFAULT 'available',
CONSTRAINT PD_PK PRIMARY KEY(P_ID),
CONSTRAINT PD_FK FOREIGN KEY (P_ID) REFERENCES PRODUCT(P_ID)
)
INSERT INTO [PRODUCT DETAILS](P_ID, P_QUANTITY, P_WEIGHT, P_TYPE, P_AVAILABILITY)
SELECT P_ID, P_QUANTITY, P_WEIGHT, P_TYPE, P_AVAILABILITY FROM PRODUCT

ALTER TABLE PRODUCT 
DROP COLUMN P_AVAILABILITY,P_TYPE

CREATE TABLE [PRODUCT HISTORY](
P_ID VARCHAR(10),
P_NAME VARCHAR(20),
P_PURCHASE FLOAT,
P_PRICE FLOAT,
P_TIME DATETIME DEFAULT SYSDATETIME(),
CONSTRAINT PH_PK PRIMARY KEY (P_ID,P_TIME),
CONSTRAINT PH_FK FOREIGN KEY (P_ID) REFERENCES PRODUCT(P_ID)
)

INSERT INTO [PRODUCT HISTORY](P_ID,P_NAME,P_PURCHASE,P_PRICE)
SELECT P_ID,P_NAME,P_PURCHASE,P_PRICE FROM PRODUCT

SELECT * FROM PRODUCT

alter table PRODUCT
drop column P_QUANTITY, P_WEIGHT, P_TYPE, P_AVAILABILITY

alter table EMPLOYEE
drop column E_PASS, E_USERNAME, E_QUESTION

--pid ,name,purchase,price,quantity,weight,type
SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available'

SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on pd.P_ID=p.P_ID and pd.P_TYPE='W' and pd.P_AVAILABILITY='available'

SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on pd.P_ID=p.P_ID and pd.P_TYPE='Q' and pd.P_AVAILABILITY='available'

SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and p.P_NAME like 'c%'

SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_QUANTITY < 15

SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and p.P_PRICE <200


SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID
order by
p.P_NAME asc

SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID
order by
p.P_PRICE asc

SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID
order by
pd.P_QUANTITY asc

SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID
order by
pd.P_WEIGHT asc

SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID
order by
p.P_PURCHASE asc

SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID
order by
pd.P_TYPE asc

SELECT p.P_ID,p.P_NAME,p.P_PRICE,p.P_PURCHASE,pd.P_QUANTITY,pd.P_WEIGHT,pd.P_TYPE FROM PRODUCT p
inner join [PRODUCT DETAILS] pd on p.P_ID = pd.P_ID and pd.P_AVAILABILITY='available'
order by
pd.P_ID asc


-- login query
select E_NAME, E_ID, E_DESIGNATION,
(select E_USERNAME from [EMPLOYEE DETAILS] ed where ed.E_ID=e.E_ID) as E_USERNAME,
(select E_PASS from [EMPLOYEE DETAILS] ed where ed.E_ID=e.E_ID) as E_PASS
from EMPLOYEE e where 
e.E_ID=
(select E_ID from [EMPLOYEE DETAILS] ed 
where ed.E_USERNAME = 'john.smith' and ed.E_PASS='a1s2d3f4')

select * from [EMPLOYEE DETAILS]

------------------- fix
alter table [EMPLOYEE DETAILS]
add TEMP varchar(30)

update [EMPLOYEE DETAILS]
set TEMP=E_USERNAME

update [EMPLOYEE DETAILS]
set E_USERNAME=E_QUESTION

update [EMPLOYEE DETAILS]
set E_QUESTION=TEMP

alter table [EMPLOYEE DETAILS]
drop column TEMP
---------------------

-- security question query
select E_USERNAME, E_ID, 
(select E_NAME from EMPLOYEE e where e.E_ID=ed.E_ID) as E_NAME,
(select E_DESIGNATION from EMPLOYEE e where e.E_ID=ed.E_ID) as E_DESIGNATION
from [EMPLOYEE DETAILS] ed where
ed.E_USERNAME='john.smith' and ed.E_QUESTION='cats'

select * from [PURCHASE]
select * from [PRODUCT DETAILS]


begin tran UPDATE_PRODUCT

	update PRODUCT
	set	P_NAME='Banana', P_PURCHASE=40, P_PRICE=60
	where P_NAME='Banana'

	update [PRODUCT DETAILS]
	set P_TYPE='Q', P_WEIGHT=12, P_PRESET=null
	where P_ID='P000000002'

commit

create function product_id()
returns varchar(10)
as
begin
declare @temp varchar(10);
set @temp = (select 'P'+Cast(count(P_ID)+1 as varchar) from PRODUCT )
return(@temp)
end

create function purchase_id()
returns varchar(10)
as
begin
declare @temp varchar(10);
set @temp = (select 'PUR'+Cast(count(PUR_ID)+1 as varchar) from PURCHASE )
return(@temp)
end

create function supplier_id()
returns varchar(10)
as
begin
declare @temp varchar(10);
set @temp = (select 'S'+Cast(count(S_ID)+1 as varchar) from SUPPLIER )
return(@temp)
end

declare @res varchar(10);
set @res = dbo.product_id();
select @res as PID

declare @res varchar(10);
set @res = dbo.supplier_id();
select @res as PID

---------------------FIXING THE QUANTITY
select * from PRO_PUR where P_ID='P000000024'
select * from [PRODUCT DETAILS] where P_ID='P000000024'

update [PRODUCT DETAILS]
set P_QUANTITY=200 where P_ID='P000000024'

select * from [PRODUCT] where P_ID='P000000024'
select * from PURCHASE
-------------------------------------

------------query for adding product when placing order
select P_ID, P_NAME,P_PRICE, P_PURCHASE,
(select P_AVAILABILITY from [PRODUCT DETAILS] pd
where pd.P_ID=p.P_ID) as P_AVAILABILITY,
(select P_WEIGHT from [PRODUCT DETAILS] pd
where pd.P_ID=p.P_ID) as P_WEIGHT,
(select P_TYPE from [PRODUCT DETAILS] pd
where pd.P_ID=p.P_ID) as P_TYPE
from PRODUCT p where p.P_NAME='Banana' and p.P_ID=
(select P_ID from [PRODUCT DETAILS] pd 
where pd.P_ID=p.P_ID and pd.P_AVAILABILITY='available')

----------purchase time fix
alter table PURCHASE
alter column PUR_TIME datetime

alter table PURCHASE
add constraint pur_time_default
default SYSDATETIME() for PUR_TIME

----------stored procedure for adding products

create procedure add_product
@pname varchar(20),
@pid varchar(10),
@purchase float,
@price float,
@quantity float,
@weight float,
@type varchar(1),
@preset int,
@supplier varchar(10)
as 
begin
	insert into PRODUCT(P_ID, P_NAME, P_PRICE, P_PURCHASE, S_ID)
	values (@pid, @pname, @price, @purchase, @supplier)

	insert into [PRODUCT DETAILS](P_ID, P_QUANTITY, P_WEIGHT, P_TYPE, P_PRESET)
	values (@pid, @quantity, @weight, @type, @preset)

	insert into [PRODUCT HISTORY](P_ID, P_NAME, P_PURCHASE, P_PRICE)
	values (@pid, @pname, @purchase, @price)
end

drop procedure add_product

create procedure add_purchase
@purid varchar(10),
@sid varchar(10),
@method varchar(10),
@discount int, 
@eid varchar(10)
as
begin
	declare @temp varchar(10)
	set @temp = dbo.purchase_id();

	insert into [PURCHASE] (PUR_ID, S_ID, PUR_METHOD, PUR_DISCOUNT, E_ID)
	values (@purid, @sid, @method, @discount, @eid)

end

create procedure add_pro_pur
@purid varchar(10),
@pid varchar(10),
@quantity float,
@price float
as
begin
	insert into PRO_PUR(PUR_ID, P_ID, PUR_QUANTITY, PUR_PRICE)
	values (@purid, @pid, @quantity, @price)
end

select * from [PRO_PUR]

------------------ fix pro pur price data type
alter table [PRO_PUR]
alter column PUR_PRICE float

--------------trigger for 0 quantity
create trigger out_of_stock
on [PRODUCT DETAILS]
after update as
begin
	update [PRODUCT DETAILS]
	set P_AVAILABILITY='out of stock' where P_QUANTITY=0
end

-----------------------
declare @temp2 varchar(10)
set @temp2 = dbo.product_id();
exec dbo.add_product @pname='testing', @pid=@temp2, @purchase=0, @price=0, @quantity=0, @weight=0, @type='W', @preset=0, @supplier='S000000001'


select * from [PRODUCT HISTORY]	

-----------------------adding cascade delete to product details page
alter table [PRODUCT DETAILS]
drop constraint PD_FK

alter table [PRODUCT DETAILS]
add constraint PD_FK FOREIGN KEY (P_ID) REFERENCES PRODUCT(P_ID) ON DELETE CASCADE
------------------------------------------

--------------------- checks if supplier exists or not and then creates if it does not exist
create function doesSupplierExists(@name varchar(30))
returns varchar(10)
as
begin
	declare @supexists varchar(5)
	set @supexists=(select count(*) as [EXISTS] from SUPPLIER s where s.S_NAME=@name)
	
	return @supexists
end

create procedure add_supplier
@id varchar(10),
@name varchar(30),
@phone bigint
as
begin
	if dbo.doesSupplierExists(@name) = 0 
	begin
		insert into SUPPLIER(S_ID, S_NAME, S_PHONE)
		values (@id, @name, @phone)
	end

	select S_ID from SUPPLIER s where s.S_NAME=@name
end

select * from [PURCHASE]
update PURCHASE
set PUR_METHOD='cash' where PUR_METHOD='Cash'
-----------------------------

--------------------query for purchase bill rows
select PUR_ID, PUR_METHOD, PUR_TIME,
(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,
(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,
(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],
(select sum(PUR_PRICE * PUR_QUANTITY) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Amount]
from PURCHASE p

select PUR_ID, PUR_METHOD, PUR_TIME,
(select S_NAME from SUPPLIER s where s.S_ID=p.S_ID) as S_NAME,
(select E_NAME from EMPLOYEE e where e.E_ID=p.E_ID) as E_NAME,
(select count(P_ID) from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID) as [Total Products],
(select sum(PUR_PRICE * PUR_QUANTITY) as [T] from [PRO_PUR] pp where pp.PUR_ID=p.PUR_ID)
as [Total Amount]
from PURCHASE p where 
YEAR(PUR_TIME) >= 2022 and YEAR(PUR_TIME) <= 2023 and
MONTH(PUR_TIME) >= 2 and MONTH(PUR_TIME) <= 6 and
DAY(PUR_TIME) >= 1 and DAY(PUR_TIME) <= 20

select PUR_QUANTITY, PUR_PRICE,  
(select P_NAME from PRODUCT p where p.P_ID=pd.P_ID) as P_NAME
from [PRO_PUR] pd where PUR_ID='PUR8'

----------------------------------------------


-------------------------functions for creating new customer, order, employee keys
create function order_id()
returns varchar(10)
as
begin
declare @temp varchar(10);
set @temp = (select 'O'+Cast(count(O_ID)+1 as varchar) from [ORDER] )
return(@temp)
end

create function customer_id()
returns varchar(10)
as
begin
declare @temp varchar(10);
set @temp = (select 'C'+Cast(count(C_ID)+1 as varchar) from [CUSTOMER] )
return(@temp)
end

create function employee_id()
returns varchar(10)
as
begin
declare @temp varchar(10);
set @temp = (select 'E'+Cast(count(E_ID)+1 as varchar) from [EMPLOYEE] )
return(@temp)
end
------------------------------------------

-----------------procedures to place order

--------------------- checks if supplier exists or not and then creates if it does not exist
create function doesCustomerExists(@email varchar(50))
returns varchar(10)
as
begin
	declare @cusexists varchar(50)
	set @cusexists=(select count(*) as [EXISTS] from CUSTOMER s where s.C_EMAIL=@email)
	
	return @cusexists
end

drop function dbo.doesCustomerExists
drop procedure add_customer

create procedure add_customer
@id varchar(10),
@name varchar(30),
@phone bigint,
@email varchar(50)
as
begin
	if dbo.doesCustomerExists(@email) = 0 
	begin
		insert into CUSTOMER(C_ID, C_NAME, C_PHONE, C_EMAIL)
		values (@id, @name, @phone, @email)
	end

	select C_ID from CUSTOMER c where c.C_EMAIL=@email
end


----------order time fix
alter table [ORDER]
drop column O_TIME

alter table [ORDER]
add O_TIME datetime default SYSDATETIME()
-------------------

---------order discount fix
alter table [ORDER]
alter column O_DISCOUNT float
----------

--------------customer phone number fix
alter table [CUSTOMER]
alter column C_PHONE bigint

------------------create order
create procedure create_order
@oid varchar(10),
@cid varchar(10),
@method varchar(10),
@discount float,
@eid varchar(10),
@status varchar(20)
as 
begin
	
	insert into [ORDER](O_ID, C_ID, O_METHOD, O_DISCOUNT, E_ID, O_STATUS)
	values (@oid, @cid, @method, @discount, @eid, @status);
end

drop procedure create_order
-------------------------subtract items in product details
create procedure subtract_quantity
@pid varchar(10),
@quantity float
as
begin
	update [PRODUCT DETAILS]
	set P_QUANTITY=P_QUANTITY-@quantity where P_ID=@pid
end

drop procedure add_order_items
-------------pro ord price fix
alter table [PRO_ORD]
alter column PO_PRICE float

--------------add items in pro ord table
create procedure add_order_items
@pid varchar(10),
@oid varchar(10),
@quantity float,
@price float
as 
begin
	declare @temp float
	set @temp = (select P_PURCHASE from PRODUCT p  where p.P_ID=@pid)

	insert into [PRO_ORD](O_ID, P_ID, PO_QUANTITY, PO_PRICE, PO_PURCHASE)
	values (@oid, @pid, @quantity, @price, @temp)
end

drop procedure add_order_items

select * from [EMPLOYEE]
select * from [PRO_ORD]

delete from [ORDER]
where O_ID='O1'

declare @temp varchar(10)
set @temp = dbo.order_id()
exec create_order @oid=@temp, @cid='C000000001', @method='cash', @discount=0, @eid='E000000001', @status='paid'

----------------CUSTOMER EMPLOYEE FIX
alter table [ORDER]
drop constraint ORDER_EMPLOYEE_FK

alter table [ORDER]
add constraint ORDER_EMPLOYEE_FK foreign key (E_ID) references EMPLOYEE(E_ID)

---------making everything float
alter table PRO_ORD
alter column PO_QUANTITY float

select * from [PRO_ORD]

delete from [PRO_ORD]
where O_ID='O1'

delete from [ORDER]
where O_ID='O1'

select * from [ORDER]
where O_ID='O1'

select * from [PRODUCT DETAILS]
where P_ID='P000000004'

update [PRODUCT DETAILS]
set P_QUANTITY=148.5 where P_ID='P000000004'

update [PRODUCT DETAILS]
set P_QUANTITY=(select top 1 PUR_QUANTITY from [PRO_PUR] pp where pp.P_ID=[PRODUCT DETAILS].P_ID)

alter table [PRODUCT DETAILS]
add constraint Quantity_Check check (P_QUANTITY >= 0)

select * from [ORDER]

select O_ID, O_DISCOUNT, O_TIME,
(select C_NAME from [CUSTOMER] c where c.C_ID=o.C_ID) as C_NAME,
(select SUM(PO_PRICE) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Price],
(select COUNT(PO_QUANTITY) from [PRO_ORD] po where po.O_ID=o.O_ID) as [Total Products],
(select E_NAME from EMPLOYEE e where e.E_ID=o.E_ID) as [E_NAME]
from [ORDER] o where MONTH(o.O_TIME) = 6 

--------------------------BPP
select PO_QUANTITY, CEILING((PO_PRICE / PO_QUANTITY)) as PO_PRICE,
(select P_NAME from PRODUCT p where p.P_ID=po.P_ID) as P_NAME
from [PRO_ORD] po
where po.O_ID='O1'

select sum(PO_QUANTITY) as PO_QUANTITY, 
(select P_NAME from PRODUCT p where p.P_ID=po.P_ID) as P_NAME
from [PRO_ORD] po group by PO_QUANTITY, (select P_NAME from PRODUCT p where p.P_ID=po.P_ID)

SELECT top 1 sub.PO_PRICE, sub.PO_QUANTITY, sub.PO_PURCHASE, (sub.PO_PRICE - sub.PO_PURCHASE) as PROFIT,
(select P_NAME from PRODUCT p where p.P_ID=sub.P_ID) as P_NAME
FROM (
    SELECT SUM(PO_QUANTITY) AS PO_QUANTITY, sum(PO_PRICE) as PO_PRICE,
	sum(PO_PURCHASE * PO_QUANTITY) as PO_PURCHASE,  P_ID
    FROM [PRO_ORD] po where MONTH(SYSDATETIME()) = 
	(select MONTH(O_TIME) from [ORDER] o where o.O_ID=po.O_ID)
    GROUP BY P_ID
) as sub order by PO_PRICE desc

select * from [ORDER]
select * from PRODUCT

update PRO_ORD
set PO_PURCHASE = 50 where P_ID='P000000003'

select * from EMPLOYEE
-------------------

--------------------------------