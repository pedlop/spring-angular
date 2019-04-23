SELECT * FROM SpringAngular.users;

SHOW INDEXES FROM SpringAngular.users;

INSERT INTO SpringAngular.users (email, password, username) VALUES ("ped@test.com", "12345", "ped");

UPDATE SpringAngular.users SET username = "test" WHERE id = 1;

ALTER TABLE SpringAngular.users AUTO_INCREMENT = 1;

TRUNCATE TABLE SpringAngular.users;
