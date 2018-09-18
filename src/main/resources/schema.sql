CREATE KEYSPACE customer WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

CREATE TABLE clientes (
  id_cliente uuid PRIMARY KEY,
  nome_cliente text,
  email_cliente text
);

INSERT INTO clientes  (id_cliente, nome_cliente, email_cliente)
VALUES (eccdeb00-df41-4513-963c-7720914a29a6, 'Ribeiro', 'rafael.pr10@gmail.com')