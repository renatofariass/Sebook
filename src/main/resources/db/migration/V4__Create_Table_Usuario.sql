CREATE TABLE IF NOT EXISTS tb_usuario (
    id bigserial not null,
    contato varchar(255),
    email varchar(255),
    nome varchar(255),
    senha varchar(100),
    primary key (id)
);