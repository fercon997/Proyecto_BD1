CREATE DATABASE Proyecto;
\connect Proyecto

CREATE TABLE Lugar(
  Codigo number(10),
  Nombre varchar(50) NOT NULL,
  cod_superior number(10),
  Constraint cod_dir_pk Primary Key(Codigo),
  Constraint cod_dir_fk Foreign Key(Codigo) references Lugar,
);
