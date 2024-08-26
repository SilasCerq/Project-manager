Banco de dados

-- -----------------------------------------------------
-- Table Pessoa
-- -----------------------------------------------------
CREATE TABLE pessoa
(
  id bigserial NOT NULL,
  nome character varying(100) NOT NULL,
  datanascimento date,
  cpf character varying(14),
  funcionario boolean,
  gerente boolean,
  CONSTRAINT pk_pessoa PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table Projeto
-- -----------------------------------------------------
CREATE TABLE projeto (
  id bigserial NOT NULL,
  nome VARCHAR(200) NOT NULL,
  data_inicio DATE,
  data_previsao_fim DATE,
  data_fim DATE,
  descricao VARCHAR(5000),
  status VARCHAR(45),
  orcamento FLOAT,
  risco VARCHAR(45),
  idgerente bigint NOT NULL,
  CONSTRAINT pk_projeto PRIMARY KEY (id),
  CONSTRAINT fk_gerente FOREIGN KEY (idgerente)
    REFERENCES pessoa (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- -----------------------------------------------------
-- Table Membros
-- -----------------------------------------------------
CREATE TABLE membros (
  id_pessoa bigint NOT NULL,
  id_projeto bigint NOT NULL,
  CONSTRAINT membros_pkey PRIMARY KEY (id_pessoa, id_projeto),
  CONSTRAINT membros_id_pessoa_fkey FOREIGN KEY (id_pessoa) REFERENCES pessoa(id),
  CONSTRAINT membros_id_projeto_fkey FOREIGN KEY (id_projeto) REFERENCES projeto(id)
);
