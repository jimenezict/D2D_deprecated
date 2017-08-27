CREATE TABLE FOUNTAINS (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
xcordenate VARCHAR(30) NOT NULL,
ycordenate VARCHAR(30) NOT NULL,
origineCodeId VARCHAR(30),
origine varchar(50),
reg_date TIMESTAMP
);

alter table fountains add comments TEXT;