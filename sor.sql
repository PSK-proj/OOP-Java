CREATE USER 'sor_c'@'%' IDENTIFIED VIA mysql_native_password USING 'WWH8aD1ZaZOfzNXn';GRANT SELECT ON *.* TO 'sor_c'@'%' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;

CREATE USER 'sor_s'@'%' IDENTIFIED VIA mysql_native_password USING '2lbiPkEr3nrWAoww';GRANT SELECT, INSERT, UPDATE, ALTER ON *.* TO 'sor_s'@'%' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;

-----------------

CREATE TABLE `sor`.`stoliki` ( `numer` TINYINT(10) NOT NULL , `mac` VARCHAR(17) NOT NULL , PRIMARY KEY (`numer`)) ENGINE = InnoDB;

CREATE TABLE `sor`.`menu` ( `id` INT NOT NULL AUTO_INCREMENT , `nazwa` VARCHAR(50) NOT NULL , `kategoria` VARCHAR(50) NOT NULL , `cena` DECIMAL(2) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE `sor`.`kelnerzy` ( `id` INT NOT NULL AUTO_INCREMENT , `imie` VARCHAR(30) NOT NULL , `nazwisko` VARCHAR(30) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE `sor`.`zamowienia` ( `id` INT NOT NULL AUTO_INCREMENT , `stolik_id` TINYINT NOT NULL , `kelner_id` INT NOT NULL , `data_zamowienia` TIMESTAMP NOT NULL , `zaplacono` BOOLEAN NOT NULL DEFAULT FALSE , PRIMARY KEY (`id`)) ENGINE = InnoDB;
ALTER TABLE zamowienia ADD FOREIGN KEY (`stolik_id`) REFERENCES stoliki(numer);
ALTER TABLE zamowienia ADD FOREIGN KEY (`kelner_id`) REFERENCES kelnerzy(id);

CREATE TABLE `sor`.`zamowienia_potraw` ( `id` INT NOT NULL AUTO_INCREMENT , `zamowienie_id` INT NOT NULL , `potrawa_id` INT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;
ALTER TABLE zamowienia_potraw ADD FOREIGN KEY (`zamowienie_id`) REFERENCES zamowienia(id);
ALTER TABLE zamowienia_potraw ADD FOREIGN KEY (`potrawa_id`) REFERENCES menu(id);

-----------------

INSERT INTO `kelnerzy` (`id`, `imie`, `nazwisko`) VALUES ('1', 'Pola', 'Michalska'), ('2', 'Cyprian', 'Szczepaniak'), ('3', 'Sylwia', 'Pawłowska');

INSERT INTO `sor`.`menu` (`nazwa`, `kategoria`, `cena`) VALUES
('Aperol Spritz', 'napoje', 18),
('Limoncello', 'napoje', 14),
('Cappuccino', 'napoje', 10),
('San Pellegrino', 'napoje', 8),
('Prosecco', 'napoje', 24),
('Limonka Basil Smash', 'napoje', 20),
('Spaghetti Carbonara', 'dania główne', 36),
('Pizza Margherita', 'dania główne', 32),
('Risotto z grzybami', 'dania główne', 40),
('Lasagne Bolognese', 'dania główne', 42),
('Ravioli z ricottą i szpinakiem w sosie pomidorowym', 'dania główne', 38),
('Tagliatelle z sosem pesto', 'dania główne', 34),
('Calzone z szynką i grzybami', 'dania główne', 38),
('Zupa Minestrone', 'dania główne', 28),
('Tiramisu', 'desery', 18),
('Panna Cotta z sosem truskawkowym', 'desery', 16),
('Cannoli z kremem ricotta i czekoladą', 'desery', 20),
('Gelato', 'desery', 12),
('Sernik włoski z mascarpone', 'desery', 22),
('Ciasto ze świeżymi owocami', 'desery', 24);

