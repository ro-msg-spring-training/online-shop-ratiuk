CREATE TABLE ProductCategory(
        id BIGINT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        description VARCHAR(1000));
CREATE UNIQUE INDEX UI_ProdCat ON ProductCategory(name);

CREATE TABLE Supplier(
        id BIGINT PRIMARY KEY,
        name VARCHAR(100) NOT NULL);
CREATE UNIQUE INDEX UI_Supp ON Supplier(Name);

CREATE TABLE Product(
        id BIGINT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        description VARCHAR(1000),
        price DECIMAL NOT NULL,
        weight DOUBLE NOT NULL,
        category INT,
        supplier INT,
        imageURL VARCHAR(200),
        FOREIGN KEY(category) REFERENCES ProductCategory(id),
        FOREIGN KEY(supplier) REFERENCES Supplier(id));
CREATE UNIQUE INDEX UI_Prod ON Product(name);

CREATE TABLE Address(
        id BIGINT PRIMARY KEY,
        addressCountry VARCHAR(100) NOT NULL,
        addressCity VARCHAR(100) NOT NULL,
        addressCounty VARCHAR(100) NOT NULL,
        addressStreet VARCHAR(100) NOT NULL);

CREATE TABLE Location(
        id BIGINT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        address INT,
        FOREIGN KEY(address) REFERENCES Address(id));
CREATE UNIQUE INDEX UI_Loc ON Location(name);

CREATE TABLE Stock(
        id BIGINT PRIMARY KEY,
        product INT,
        location INT,
        quantity INT NOT NULL,
        FOREIGN KEY(product) REFERENCES Product(id),
        FOREIGN KEY(location) REFERENCES Location(id),
        UNIQUE(product, location));

CREATE TABLE Customer(
        id BIGINT PRIMARY KEY,
        firstName VARCHAR(100) NOT NULL,
        lastName VARCHAR(100) NOT NULL,
        username VARCHAR(100) NOT NULL,
        password VARCHAR(100) NOT NULL,
        emailAddress VARCHAR(100) NOT NULL);
CREATE UNIQUE INDEX UI_Cust ON Customer(username);

CREATE TABLE Orders(
        id BIGINT PRIMARY KEY,
        shippedFrom INT,
        customer INT,
        createdAt TIMESTAMP NOT NULL,
        address INT,
        FOREIGN KEY(address) REFERENCES Address(id),
        FOREIGN KEY(shippedFrom) REFERENCES Location(id),
        FOREIGN KEY(customer) REFERENCES Customer(id));

CREATE TABLE OrderDetail(
        id BIGINT PRIMARY KEY,
        orderId INT,
        product INT,
        quantity INT NOT NULL,
        FOREIGN KEY(orderId) REFERENCES Orders(id),
        FOREIGN KEY(product) REFERENCES Product(id));

CREATE TABLE Revenue(
        id BIGINT PRIMARY KEY,
        location INT,
        date DATE NOT NULL,
        sum DECIMAL NOT NULL,
        UNIQUE(location, Date),
        FOREIGN KEY(location) REFERENCES Location(id));

create sequence seq_mytable_sid
minvalue 1 maxvalue 9999999 increment by 1 start with 1
cache 1000
order nocycle;