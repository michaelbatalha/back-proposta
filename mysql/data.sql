CREATE TABLE IF NOT EXISTS proposta(
	id integer primary key,
    nome varchar(100),
    cpf varchar(11),
    idade integer,
    sexo char(1),
    estado_civil varchar(2),
    estado char(2),
	dependentes integer,
    renda decimal(18,2),
    resultado varchar(100),
    limite varchar(100)
);
    
insert into proposta(id, nome) values(1, "MICHAEL");

CREATE USER 'cal'@'0.0.0.0' IDENTIFIED BY 'cal';

GRANT ALL PRIVILEGES ON * . * TO 'cal'@'0.0.0.0';