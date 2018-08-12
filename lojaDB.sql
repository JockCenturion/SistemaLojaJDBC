CREATE DATABASE lojaDB;

USE lojaDB;

CREATE TABLE IF NOT EXISTS tbl_cliente (
	codigo_cliente INT NOT NULL AUTO_INCREMENT,
    nome_cliente VARCHAR(30),
    endereco VARCHAR(30),
    telefone VARCHAR(30),
    PRIMARY KEY(codigo_cliente)
) ENGINE = InnoDB;

CREATE TABLE tbl_cidade (
	codigo_cidade INT NOT NULL AUTO_INCREMENT,
    nome_cidade VARCHAR(30),
    UF VARCHAR(30),
    taxa FLOAT(4),
    PRIMARY KEY(codigo_cidade)
) ENGINE = InnoDB;

CREATE TABLE tbl_frete (
	codigo_frete INT NOT NULL AUTO_INCREMENT,
    codigo_cidade INT NOT NULL,
    codigo_cliente INT NOT NULL,
    descricao VARCHAR(30),
    peso FLOAT(4),
    valor FLOAT(4),
    PRIMARY KEY (codigo_frete),
	FOREIGN KEY (codigo_cidade) REFERENCES tbl_cidade(codigo_cidade),
    FOREIGN KEY (codigo_cliente) REFERENCES tbl_cliente(codigo_cliente)
) ENGINE = InnoDB;


-- CADASTRANDO OS CLIENTES
INSERT INTO tbl_cliente VALUES (NULL, 'Jock', 'Rua 10', '88888888'), (NULL, 'Lucas', 'Rua 11', '888888888'), (NULL, 'Carlos', 'Rua 12', '88888888');

-- CADASTRANDO AS CIDADES 
INSERT INTO tbl_cidade VALUES (NULL, 'São Luis', 'MA', 20.5), (NULL, 'Raposa', 'MA', 20.5), (NULL, 'Paço do Lumiar', 'MA', 20.5),
							  (NULL, 'Ribeirão Preto', 'SP', 15.3), (NULL, 'Cubatão', 'SP', 15.3), (NULL, 'Campinas', 'SP', 15.5);
                              
-- CADASTRANDO OS FRETES
INSERT INTO tbl_frete VALUES (NULL, 1, 1, 'Frete do Jock', 52.9, 250), (NULL, 3, 2, 'Frete do Lucas', 100, 263.7), (NULL, 4, 3, 'Frete do Carlos', 182, 525.5), 
							 (NULL, 1, 1, 'Frete do Jock', 90, 1522.5), (NULL, 1, 1, 'Frete do Jock', 900, 9500);

-- CONSULTAS SQL
/*VIEW*/
CREATE VIEW valorFrete AS
SELECT codigo_frete, tbl_cidade.codigo_cidade, ROUND((peso * 10 + taxa), 2) AS ValorFrete 
FROM tbl_frete JOIN tbl_cidade
WHERE tbl_cidade.codigo_cidade = tbl_frete.codigo_cidade;


/*1 Consulta*/
SELECT * FROM valorFrete WHERE codigo_frete = 2;

/*2 Consulta*/
SELECT * FROM tbl_frete WHERE codigo_cliente = 1;

/*3 Consulta*/ #AQUI
SELECT F.codigo_frete, F.codigo_cidade, F.codigo_cliente, F.descricao, F.peso, F.valor
FROM tbl_frete F JOIN tbl_cidade C
WHERE F.codigo_cidade = C.codigo_cidade AND ROUND((peso * 10 + taxa), 2) = (SELECT MAX(ValorFrete) FROM valorFrete);

/*VIEW*/
CREATE VIEW quantidadeFrete AS
SELECT C.nome_cidade, C.codigo_cidade, COUNT(*) as Quantidade
FROM tbl_cidade C JOIN tbl_frete F ON C.codigo_cidade = F.codigo_cidade
GROUP BY C.codigo_cidade;

/*4 Consulta*/
SELECT C.codigo_cidade, C.nome_cidade, C.UF, C.taxa
FROM tbl_cidade C JOIN quantidadeFrete Q 
WHERE C.codigo_cidade = Q.codigo_cidade AND Quantidade = (SELECT MAX(Quantidade) FROM quantidadeFrete);




