
CREATE DATABASE bdloyola;

USE bdloyola;

CREATE TABLE TBL_PRODUCTOT1 (
    IDPRODUCTOT1 INT AUTO_INCREMENT PRIMARY KEY,
    NOMBRET1 VARCHAR(255),
    PRECIOT1 DOUBLE,
    DESCRIPCIONT1 VARCHAR(255),
    ESTADOT1 VARCHAR(255),
    FECHAVENCIMT1 DATE,
    FECHAFABRIT1 DATE
);

insert into TBL_PRODUCTOT1 (NOMBRET1,PRECIOT1,DESCRIPCIONT1,ESTADOT1,FECHAVENCIMT1,FECHAFABRIT1) 
	values
	("Cemento Cran", 150.0, "Cementos de larga durabilidad", "Stock", '2025-10-25', '2024-10-25'),
    ("Tubos pip", 112.3, "Tuberias de plastico 2cm diametro", "Stock", '2025-09-13', '2023-09-13'),
    ("Calaminas Gup", 109.7, "Calaminas de plastico rojo", "Stock", '2025-08-28', '2022-08-28');
    



