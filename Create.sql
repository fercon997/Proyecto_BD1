CREATE DATABASE Proyecto;
\connect Proyecto

CREATE TABLE Lugar(
  Codigo number(10),
  Nombre varchar(50) NOT NULL,
  cod_superior number(10),
  Constraint cod_dir_pk Primary Key(Codigo),
  Constraint cod_dir_fk Foreign Key(Codigo) references Lugar(Codigo),
);

CREATE TABLE Guarderia(
  RIF varchar(10),
  Costo_mensualidad number(15) NOT NULL,
  Costo_multa number(10),
  Costo_transporte number(10) NOT NULL,
  Costo_agodic number(10) NOT NULL,
  Costo_hora_extra number(10) NOT NULL,
  Horario_entrada char(5) NOT NULL,
  Horario_salida  char(5) NOT NULL,
  Cod_direccion number(10),
  Constraint rif_guard_pk Primary Key(Rif),
  Constraint cod_dir_guard_fk Foreign Key(Cod_direccion) references Lugar (Codigo),
  Constraint hora_entry_valid Check (Horario_entrada LIKE ('__:__')),
  Constraint hora_sal_valid Check (Horario_salida LIKE ('__:__')),
);

CREATE TABLE Personal(
  CI varchar(10),
  Nombre varchar(10) NOT NULL,
  Apellido varchar(10) NOT NULL,
  Celular number(10) NOT NULL,
  Nivel_estudio varchar(15) NOT NULL,
  Sueldo number(10) NOT NULL,
  SeñalEncargada number(1) NOT NULL Default 0,
  Fecha_responsable Date Default NULL,
  Cod_direccion number(10),
  RIF_guarderia varchar(10),
  Constraint CI_per_pk Primary Key(CI),
  Constraint RIF_guard_per_fk Foreign Key(Rif_guarderia) references Guarderia (Rif),
  Constraint Cod_dir_per_fk Foreign Key(Cod_direccion) references Lugar (Codigo),
  Constraint Encargada_bool Check(SeñalEncargada IN (0,1)),
  Constrain Tipos_estudio Check(Nivel_estudio IN ('Bachillerato', 'TSU', 'Universitario', 'Postgrado')),
);

CREATE TABLE Experiencia(
  CI_personal varchar(10),
  Nombre varchar(50),
  Constraint CI_per_exp_pk Primary Key(CI_personal),
  Constraint CI_per_exp_fk Foreign Key(CI_personal) references Personal(CI),
  Constraint Nom_exp_pk Primary Key(Nombre),
);

CREATE TABLE Representante(
  CI varchar(10),
  Nombre varchar(10) NOT NULL,
  Apellido varchar(10) NOT NULL,
  Celular number(10) NOT NULL,
  Tlf_casa number(10) NOT NULL,
  Tlf_oficina number(10) NOT NULL,
  Email varchar(20) NOT NULL,
  Profesion varchar(20) NOT NULL,
  Estado_civil char(1) NOT NULL,
  Principal number(1) NOT NULL,
  Cod_direccion number(10),
  Constraint CI_rep_pk Primary Key(CI),
  Constraint Cod_dir_rep_fk Foreign Key(Cod_direccion) references(Lugar),
  Constraint edo_civil_valid Check(Estado_civil IN ('C', 'S')),
  Constraint ppal_bool Check(Principal IN (0,1)),
  Constraint email_valid Check(Email LIKE ('%_@%_._&')),
);

CREATE TABLE Autorizado(
  CI varchar(10),
  Nombre varchar(10) NOT NULL,
  Apellido varchar(10) NOT NULL,
  Celular number(10) NOT NULL,
  Constraint CI_auth_pk Primary Key(CI),
);

CREATE TABLE Actividad(
  Codigo number(10),
  Nombre varchar(15) NOT NULL,
  Descripcion varchar(50) NOT NULL,
  EdadMinima number(1) NOT NULL,
  Transporte number(1) NOT NULL,
  Constraint Cod_act_pk Primary Key(Codigo),
  Constraint edad_min_act_valid Check(EdadMinima IN (1,2,3,4,5,6)),
  Constraint transpor_bool Check(Transporte IN (0,1)),
);

CREATE TABLE Act_Guarderia(
  RIF_guarderia varchar(10),
  Cod_actividad number(10),
  Costo_actividad number(10) NOT NULL,
  CupoMinimo number(3) NOT NULL,
  CupoMax number(3) NOT NULL,
  CI_encargada varchar(10),
  Constraint RIF_guard_act_pk Primary Key(RIF_guarderia),
  Constraint RIF_guard_act_fk Foreign Key(RIF_guarderia) references Guarderia(RIF),
  Constraint Cod_act_guard_pk Primary Key(Cod_actividad),
  Constraint Cod_act_guard_fk Foreign Key(Cod_actividad) references Actividad(Codigo),
  Constraint CI_enc_act_fk Foreign Key(CI_encargada) references Personal(CI),
);

CREATE TABLE Horario_Act_Guarderia(
  RIF_guarderia varchar(10),
  Cod_actividad number(10),
  Fecha Date,
  Hora_inicio char(5),
  Hora_fin char(5) NOT NULL,
  Constraint RIF_guard_act_hora_pk Primary Key(RIF_guarderia),
  Constraint RIF_guard_act_hora_fk Foreign Key(RIF_guarderia) references Act_Guarderia(RIF_guarderia),
  Constraint Cod_act_guard_hora_pk Primary Key(Cod_actividad),
  Constraint Cod_act_guard_hora_fk Foreign Key(Cod_actividad) references Act_Guarderia(Cod_actividad),
  Constraint Fecha_pk Primary Key (Fecha),
  Constraint Hora_init_pk Primary Key(Hora_inicio),
  Constraint Hora_init_valid Check(Hora_inicio LIKE ('__:__')),
  Constraint Hora_fin_valid Check(Hora_inicio LIKE ('__:__')),
);

CREATE TABLE Nino(
  CI_representante varchar(10),
  Letra char(1),
  Nombre varchar(15) NOT NULL,
  Apellido varchar (15) NOT NULL,
  Fecha_nacimiento Date NOT NULL,
  Sexo char(1) NOT NULL,
  Constraint CI_rep_nino_pk Primary Key(CI_representante),
  Constraint CI_rep_nino_fk Foreign Key(CI_representante) references Representante(CI),
  Constraint Letra_nino_pk Primary Key(Letra),
  Constraint letra_nino_valid Check (Letra IN ('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')),
  Constraint sexo_valid Check(Sexo IN ('M', 'F')),
);

CREATE TABLE Parentesco_Padre(
  Letra_nino char(1),
  CI_principal varchar(10),
  CI_representante varchar(10),
  Parentesco varchar(10),
  Constraint Letra_nino_parent_pk Primary Key(Letra_nino),
  Constraint Letra_nino_parent_fk Foreign Key(Letra_nino) references Nino(Letra),
  Constraint CI_ppal_parent_pk Primary Key(CI_principal),
  Constraint CI_ppal_parent_fk Foreign Key (CI_principal) references Nino(CI_representante),
  Constraint CI_rep_parent_pk Primary Key (CI_representante),
  Constraint CI_rep_parent_fk Foreign Key (CI_representante) references Representante(CI),
  Constraint Parentesco_pk Primary Key (Parentesco),
);

CREATE TABLE Parentesco_nino(
  Letra_nino char(1),
  CI_principal varchar(10),
  Letra_pariente char(1),
  CI_pariente_principal varchar(10),
  Parentesco varchar(5) NOT NULL,
  Constraint Letra_nino_parent_nino1_pk Primary Key(Letra_nino),
  Constraint Letra_nino_parent_nino1_fk Foreign Key(Letra_nino) references Nino(Letra),
  Constraint CI_ppal_parent__nino1_pk Primary Key(CI_principal),
  Constraint CI_ppal_parent_nino2_fk Foreign Key (CI_principal) references Nino(CI_representante),
  Constraint Letra_nino_parent_nino2_pk Primary Key(Letra_pariente),
  Constraint Letra_nino_parent_nino2_fk Foreign Key(Letra_pariente) references Nino(Letra),
  Constraint CI_ppal_parent__nino2_pk Primary Key(CI_pariente_principal),
  Constraint CI_ppal_parent_nino2_fk Foreign Key (CI_pariente_principal) references Nino(CI_representante),
);
