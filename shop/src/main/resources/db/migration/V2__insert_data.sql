INSERT INTO ProductCategory(id, name, description) VALUES
    (1, 'furniture', 'all furniture you need in your home'),
    (2, 'clothes', 'you never have enough clothes'),
    (3, 'shoes', 'also never enough shoes');

INSERT INTO Supplier(id, name) VALUES
    (1,'Ikea'),
    (2, 'Amazon'),
    (3, 'Ecco');

INSERT INTO Product(id, name, description, price, weight, category, supplier, imageURL) VALUES
                (1, 'double bed', '200x200cm', 555.99, 50, 1, 1, 'https://cdn03.plentymarkets.com/ga033kipwuy2/item/images/30810/full/Bett-Kernbuche-massiv-1.jpg'),
                (2, 'Columbia Jacket', 'Lightweight', 149.99, 0.3, 2, 2, 'https://www.bfgcdn.com/1500_1500_90/004-1557-0411/columbia-powder-lite-hooded-jacket-kunstfaserjacke.jpg'),
                (3, 'Ecco Sandals', 'perfect for walking in city break', 53.23, 0.3, 3, 3, 'http://www.zukunft-katastrophenhelfer.at/images/large/ima/ECCO%20SCHUHE%20F%20R%20DAMEN%20-%20KLASSISCHE%20SANDALEN%20BEIGE-KOMBI%20ZRS8212695_LRG.jpg');


INSERT INTO Address(id, addressCountry, addressCity, addressCounty, addressStreet) VALUES
                (1, 'RO', 'Cluj-Napoca', 'CJ', 'Unirii 12'),
                (2, 'DE', 'Passau', 'Bayern', 'Neuburger 14'),
                (3, 'ES', 'Palma', 'Mallorca', 'Playa de Palma 23');

INSERT INTO Location(id, name, address) VALUES
                (1, 'Cluj', 1),
                (2, 'Passau', 2),
                (3, 'Mallorca', 3);

INSERT INTO Stock(id, product, location, quantity) VALUES
                (1, 1, 1, 100),
                (2, 1, 2, 10),
                (3, 2, 3, 120),
                (4, 3, 1, 20),
                (5, 3, 3, 30);

INSERT INTO Customer(id, firstName, lastName, username, password, emailAddress) VALUES
                (1, 'John', 'Doe', 'jonhd', 'password123', 'johndoe@yahoo.com'),
                (2, 'Maria', 'Cristina', 'merry23', 'merry23', 'merry23@yahoo.com');

INSERT INTO Orders(id, shippedFrom, customer, createdAt, address) VALUES
                (1, 1, 1, '2019-06-23 10:23:55', 2),
                (2, 3, 2, '2019-07-23 12:23:55', 1);

INSERT INTO OrderDetail(id, orderId, product, quantity) VALUES
                (1, 1, 1, 1),
                (2, 2, 2, 5);

INSERT INTO Revenue(id, location, date, sum) VALUES
                (1, 1, '2019-07-15', 10.3),
                (2, 2, '2019-07-23', 82.5);
