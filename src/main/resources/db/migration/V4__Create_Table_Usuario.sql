CREATE TABLE IF NOT EXISTS tb_usuario (
    id bigserial not null,
    username varchar(30) not null unique,
    telefone varchar(13) not null unique,
    whatsapp varchar(255) not null unique,
    email varchar(255),
    nome varchar(255),
    senha varchar(100),
    primary key (id)
);