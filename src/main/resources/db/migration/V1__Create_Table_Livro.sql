CREATE TABLE IF NOT EXISTS tb_livros (
  id SERIAL PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL,
  editora VARCHAR(255) NOT NULL,
  autor VARCHAR(255) NOT NULL,
  preco NUMERIC(10, 2) NOT NULL,
  img_url VARCHAR(255),
  nome_categoria VARCHAR(255) NOT NULL,
  categoria_id BIGINT NOT NULL,
  sebo_id BIGINT NOT NULL
);
