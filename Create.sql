CREATE DATABASE Proyecto;
\connect proyecto

CREATE TABLE Lugar_4(
  Codigo numeric(10),
  Nombre varchar(50) NOT NULL,
  cod_superior numeric(10),
  Constraint cod_dir_pk Primary Key(Codigo),
  Constraint cod_dir_fk Foreign Key(Codigo) references Lugar_4(Codigo)
);

CREATE TABLE Guarderia_4(
  RIF varchar(10),
  Costo_mensualidad numeric(15) NOT NULL,
  Costo_multa numeric(10) NOT NULL,
  Costo_transporte numeric(10) NOT NULL,
  Costo_agodic numeric(10) NOT NULL,
  Costo_hora_extra numeric(10) NOT NULL,
  Horario_entrada char(5) NOT NULL,
  Horario_salida  char(5) NOT NULL,
  Cod_direccion numeric(10),
  Constraint rif_guard_pk Primary Key(Rif),
  Constraint cod_dir_guard_fk Foreign Key(Cod_direccion) references Lugar_4 (Codigo),
  Constraint hora_entry_valid Check (Horario_entrada LIKE ('__:__')),
  Constraint hora_sal_valid Check (Horario_salida LIKE ('__:__'))
);

CREATE TABLE Personal_4(
  CI varchar(10),
  Nombre numeric(10) NOT NULL,
  Apellido varchar(10) NOT NULL,
  Celular numeric(10) NOT NULL,
  Nivel_estudio varchar(15) NOT NULL,
  Sueldo numeric(10) NOT NULL,
  SeñalEncargada numeric(1) NOT NULL Default 0,
  Fecha_responsable Date Default NULL,
  Cod_direccion numeric(10),
  RIF_guarderia varchar(10),
  Constraint CI_per_pk Primary Key(CI),
  Constraint RIF_guard_per_fk Foreign Key(Rif_guarderia) references Guarderia_4 (Rif),
  Constraint Cod_dir_per_fk Foreign Key(Cod_direccion) references Lugar_4 (Codigo),
  Constraint encargada_bool Check(SeñalEncargada IN (0,1)),
  Constraint tipos_estudio Check(Nivel_estudio IN ('Bachillerato', 'TSU', 'Universitario', 'Postgrado'))
);

CREATE TABLE Experiencia_4(
  CI_personal varchar(10),
  Nombre varchar(50),
  Constraint CINom_per_exp_pk Primary Key(CI_personal, Nombre),
  Constraint CI_per_exp_fk Foreign Key(CI_personal) references Personal_4(CI)
);

CREATE TABLE Representante_4(
  CI varchar(10),
  Nombre varchar(10) NOT NULL,
  Apellido varchar(10) NOT NULL,
  Celular numeric(10) NOT NULL,
  Tlf_casa numeric(10) NOT NULL,
  Tlf_oficina numeric(10) NOT NULL,
  Email varchar(20) NOT NULL,
  Profesion varchar(20) NOT NULL,
  Estado_civil char(1) NOT NULL,
  Principal numeric(1) NOT NULL,
  Cod_direccion numeric(10),
  Constraint CI_rep_pk Primary Key(CI),
  Constraint Cod_dir_rep_fk Foreign Key(Cod_direccion) references Lugar_4(Codigo),
  Constraint edo_civil_valid Check(Estado_civil IN ('C', 'S')),
  Constraint ppal_bool Check(Principal IN (0,1)),
  Constraint email_valid Check(Email LIKE ('%_@%_._&'))
);

CREATE TABLE Autorizado_4(
  CI varchar(10),
  Nombre varchar(10) NOT NULL,
  Apellido varchar(10) NOT NULL,
  Celular numeric(10) NOT NULL,
  Constraint CI_auth_pk Primary Key(CI)
);

CREATE TABLE Actividad_4(
  Codigo numeric(10),
  Nombre varchar(15) NOT NULL,
  Descripcion varchar(50) NOT NULL,
  EdadMinima numeric(1) NOT NULL,
  Transporte numeric(1) NOT NULL,
  Constraint Cod_act_pk Primary Key(Codigo),
  Constraint edad_min_act_valid Check(EdadMinima IN (1,2,3,4,5,6)),
  Constraint transpor_bool Check(Transporte IN (0,1))
);

CREATE TABLE Act_Guarderia_4(
  RIF_guarderia varchar(10),
  Cod_actividad numeric(10),
  Costo_actividad numeric(10) NOT NULL,
  CupoMinimo numeric(3) NOT NULL,
  CupoMax numeric(3) NOT NULL,
  CI_encargada varchar(10),
  Constraint RIF_guard_Cod_act_pk Primary Key(RIF_guarderia, Cod_actividad),
  Constraint RIF_guard_act_fk Foreign Key(RIF_guarderia) references Guarderia_4(RIF),
  Constraint Cod_act_guard_fk Foreign Key(Cod_actividad) references Actividad_4(Codigo),
  Constraint CI_enc_act_fk Foreign Key(CI_encargada) references Personal_4(CI)
);

CREATE TABLE Horario_Act_Guarderia_4(
  RIF_guarderia varchar(10),
  Cod_actividad numeric(10),
  Fecha Date,
  Hora_inicio char(5),
  Hora_fin char(5) NOT NULL,
  Constraint RIF_guard_Cod_act_hora_Fecha_Hora_init_pk Primary Key(RIF_guarderia, Cod_actividad, Fecha, Hora_inicio),
  Constraint RIF_guard_Cod_act_hora_fk Foreign Key(RIF_guarderia, Cod_actividad) references Act_Guarderia_4(RIF_guarderia, Cod_actividad),
  Constraint Hora_init_valid Check(Hora_inicio LIKE ('__:__')),
  Constraint Hora_fin_valid Check(Hora_inicio LIKE ('__:__'))
);

CREATE TABLE Nino_4(
  CI_representante varchar(10),
  Letra char(1),
  Nombre varchar(15) NOT NULL,
  Apellido varchar (15) NOT NULL,
  Fecha_nacimiento Date NOT NULL,
  Sexo char(1) NOT NULL,
  Constraint CI_rep_Letra_nino_pk Primary Key(CI_representante, Letra),
  Constraint CI_rep_nino_fk Foreign Key(CI_representante) references Representante_4(CI),
  Constraint letra_nino_valid Check (Letra IN ('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')),
  Constraint sexo_valid Check(Sexo IN ('M', 'F'))
);

CREATE TABLE Parentesco_Padre_4(
  Letra_nino char(1),
  CI_principal varchar(10),
  CI_representante varchar(10),
  Parentesco varchar(10),
  Constraint Letra_nino_CI_ppal_CI_rep_Parentesco_pk Primary Key(Letra_nino,CI_principal,CI_representante,Parentesco),
  Constraint Letra_nino_CI_ppal_parent_fk Foreign Key(Letra_nino, CI_principal) references Nino_4(Letra, CI_representante),
  Constraint CI_rep_parent_fk Foreign Key (CI_representante) references Representante_4(CI)
);

CREATE TABLE Parentesco_nino_4(
  Letra_nino char(1),
  CI_principal varchar(10),
  Letra_pariente char(1),
  CI_pariente_principal varchar(10),
  Parentesco varchar(5) NOT NULL,
  Constraint Letra_nino_CI_ppal_parent_nino1_y_nino2_pk Primary Key(Letra_nino, CI_principal, Letra_pariente, CI_pariente_principal),
  Constraint Letra_nino_CI_ppal_parent_nino1_fk Foreign Key(Letra_nino, CI_principal) references Nino_4(Letra, CI_representante),
  Constraint Letra_nino_CI_ppal_parent_nino2_fk Foreign Key(Letra_pariente, CI_pariente_principal) references Nino_4(Letra, CI_representante)
);

CREATE TABLE Asistencia_4(
  Fecha date,
  Consecutivo_Ins varchar(6),
  Ano_inscripcion char(4),
  CI_representante varchar(10),
  Letra_nino char(1),
  CI_Padre_busco varchar(6),
  CI_auth_busco varchar(6),
  Hora_entrada char(5) NOT NULL,
  Hora_salida char (5) NOT NULL,
  Comio char(2) NOT NULL,
  Constraint Asistencia_pk Primary Key(Fecha,Consecutivo_Ins,Ano_inscripcion,CI_representante,Letra_nino),
  Constraint Inscripcion_asistencia_fk Foreign Key(Consecutivo_Ins,Ano_inscripcion,CI_representante) references Inscripcion_4(Consecutivo,Ano,CI_representante),
  Constraint Representante_asistencia_fk Foreign Key(CI_Padre_busco) references Representante_4(CI),
  Constraint Autorizado_asistencia_fk Foreign Key(CI_auth_busco) references Autorizado_4(CI),
  Constraint Hora_entrada_valid Check(Hora_entrada LIKE ('__:__')),
  Constraint Hora_salida_valid Check(Hora_salida LIKE ('__:__')),
  Constraint Comio_valid Check(Comio IN ('SI', 'NO'))
);

CREATE TABLE Pediatra_4(
  Codigo numeric(10),
  Nombre varchar(15) NOT NULL,
  Tlf_movil numeric(10) NOT NULL,
  Tlf_oficina numeric(10) NOT NULL,
  Constraint Pediatra_pk Primary Key(Codigo)
);

CREATE TABLE Atencion_4(
  Codigo_pediatra numeric(10),
  Letra_nino char(1),
  CI_ppal varchar(6),
  Constraint Atencion_pk Primary Key(Codigo_pediatra,Letra_nino,CI_ppal),
  Constraint Pediatra_atencion_fk Foreign Key(Codigo_pediatra) references Pediatra_4(Codigo),
  Constraint Nino_atencion_fk Foreign Key(Letra_nino,CI_ppal) references Nino_4(Letra,CI_representante)
);

CREATE TABLE Juego_4(
  Codigo numeric(10),
  Nombre varchar(15) NOT NULL,
  Constraint Juego_pk Primary Key(Codigo)
);

CREATE TABLE Gusto_4(
  Codigo_juego numeric(10),
  Letra_nino char(1),
  CI_representante varchar(6),
  Constraint Gusto_pk Primary Key(Codigo_juego,Letra_nino,CI_representante),
  Constraint Juego_gusto_fk Foreign Key(Codigo_juego) references Juego_4(Codigo),
  Constraint Nino_gusto_fk Foreign Key(Letra_nino,CI_representante) references Nino_4(Letra,CI_representante)
);

CREATE TABLE Sintoma_4(
  Codigo numeric(10),
  Descripcion varchar(40),
  Constraint Sintoma_pk Primary Key(Codigo)
);

CREATE TABLE Medicamento_4(
  Codigo numeric(10),
  Descripcion varchar(40),
  Constraint Medicamento_pk Primary Key(Codigo)
);

CREATE TABLE Tratamiento_4(
  Codigo_sintoma numeric(10),
  Codigo_medicamento numeric(10),
  Letra_nino char(1),
  CI_representante varchar(6),
  Cantidad numeric(4),
  Constraint Tratamiento_pk Primary Key(Codigo_sintoma,Codigo_medicamento,Letra_nino,CI_representante,Cantidad),
  Constraint Sintoma_tratamiento_fk Foreign Key(Codigo_sintoma) references Sintoma_4(Codigo),
  Constraint Medicamento_tratamiento_fk Foreign Key(Codigo_medicamento) references Medicamento_4(Codigo),
  Constraint Nino_tratamiento_fk Foreign Key(Letra_nino,CI_representante) references Nino_4(Letra,CI_representante)
);

CREATE TABLE Alergia_4(
  Codigo numeric(10),
  Descripcion varchar(40),
  Constraint Alergia_pk Primary Key(Codigo)
);

CREATE TABLE Enfermedad_4(
  Codigo numeric(10),
  Descripcion varchar(40),
  Constraint Enfermedad_pk Primary Key(Codigo)
);

CREATE TABLE Padecimiento_enfermedad_4(
  Codigo_enfermedad numeric(10),
  Letra_nino char(1),
  CI_representante varchar(6),
  Fecha date,
  Constraint Padecimiento_enfermedad_pk Primary Key(Codigo_enfermedad,Letra_nino,CI_representante,Fecha),
  Constraint Enfermedad_padecimiento_enfermedad_fk Foreign Key(Codigo_enfermedad) references Enfermedad_4(Codigo),
  Constraint Nino_padecimiento_enfermedad_fk Foreign Key(Letra_nino,CI_representante) references Nino_4(Letra,CI_representante)
);

CREATE TABLE Padecimiento_alergia_4(
  Codigo_alergia numeric(10),
  Letra_nino char(1),
  CI_representante varchar(6),
  Constraint Padecimiento_alergia_pk Primary Key(Codigo_alergia,Letra_nino,CI_representante),
  Constraint Alergia_padecimiento_alergia_fk Foreign Key(Codigo_alergia) references Alergia_4(Codigo),
  Constraint Nino_padecimiento_alergia_fk Foreign Key(Letra_nino,CI_representante) references Nino_4(Letra,CI_representante)
);
