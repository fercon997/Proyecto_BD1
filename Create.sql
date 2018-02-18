CREATE DATABASE Proyecto;
\connect proyecto

CREATE TABLE Lugar(
  Codigo numeric(10),
  Nombre varchar(50) NOT NULL,
  cod_superior numeric(10),
  Constraint cod_dir_pk Primary Key(Codigo),
  Constraint cod_dir_fk Foreign Key(Codigo) references Lugar(Codigo)
);

CREATE TABLE Guarderia(
  RIF varchar(12),
  Costo_mensualidad numeric(15) NOT NULL,
  Costo_multa numeric(10) NOT NULL,
  Costo_transporte numeric(10) NOT NULL,
  Costo_agodic numeric(10) NOT NULL,
  Costo_hora_extra numeric(10) NOT NULL,
  Horario_entrada char(5) NOT NULL,
  Horario_salida  char(5) NOT NULL,
  Cod_direccion numeric(10),
  Constraint rif_guard_pk Primary Key(Rif),
  Constraint cod_dir_guard_fk Foreign Key(Cod_direccion) references Lugar (Codigo),
  Constraint hora_entry_valid Check (Horario_entrada LIKE ('__:__')),
  Constraint hora_sal_valid Check (Horario_salida LIKE ('__:__'))
);

CREATE TABLE Personal(
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
  Constraint RIF_guard_per_fk Foreign Key(Rif_guarderia) references Guarderia (Rif),
  Constraint Cod_dir_per_fk Foreign Key(Cod_direccion) references Lugar (Codigo),
  Constraint encargada_bool Check(SeñalEncargada IN (0,1)),
  Constraint tipos_estudio Check(Nivel_estudio IN ('Bachillerato', 'TSU', 'Universitario', 'Postgrado'))
);

CREATE TABLE Experiencia(
  CI_personal varchar(10),
  Nombre varchar(50),
  Constraint CINom_per_exp_pk Primary Key(CI_personal, Nombre),
  Constraint CI_per_exp_fk Foreign Key(CI_personal) references Personal(CI)
);

CREATE TABLE Representante(
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
  Constraint Cod_dir_rep_fk Foreign Key(Cod_direccion) references Lugar(Codigo),
  Constraint edo_civil_valid Check(Estado_civil IN ('C', 'S')),
  Constraint ppal_bool Check(Principal IN (0,1)),
  Constraint email_valid Check(Email LIKE ('%_@%_._&'))
);

CREATE TABLE Autorizado(
  CI varchar(10),
  Nombre varchar(10) NOT NULL,
  Apellido varchar(10) NOT NULL,
  Celular numeric(10) NOT NULL,
  Constraint CI_auth_pk Primary Key(CI)
);

CREATE TABLE Actividad(
  Codigo numeric(10),
  Nombre varchar(15) NOT NULL,
  Descripcion varchar(50) NOT NULL,
  EdadMinima numeric(1) NOT NULL,
  Transporte numeric(1) NOT NULL,
  Constraint Cod_act_pk Primary Key(Codigo),
  Constraint edad_min_act_valid Check(EdadMinima IN (1,2,3,4,5,6)),
  Constraint transpor_bool Check(Transporte IN (0,1))
);

CREATE TABLE Act_Guarderia(
  RIF_guarderia varchar(10),
  Cod_actividad numeric(10),
  Costo_actividad numeric(10) NOT NULL,
  CupoMinimo numeric(3) NOT NULL,
  CupoMax numeric(3) NOT NULL,
  CI_encargada varchar(10),
  Constraint RIF_guard_Cod_act_pk Primary Key(RIF_guarderia, Cod_actividad),
  Constraint RIF_guard_act_fk Foreign Key(RIF_guarderia) references Guarderia(RIF),
  Constraint Cod_act_guard_fk Foreign Key(Cod_actividad) references Actividad(Codigo),
  Constraint CI_enc_act_fk Foreign Key(CI_encargada) references Personal(CI)
);

CREATE TABLE Horario_Act_Guarderia(
  RIF_guarderia varchar(12),
  Cod_actividad numeric(10),
  Fecha Date,
  Hora_inicio char(5),
  Hora_fin char(5) NOT NULL,
  Constraint RIF_guard_Cod_act_hora_Fecha_Hora_init_pk Primary Key(RIF_guarderia, Cod_actividad, Fecha, Hora_inicio),
  Constraint RIF_guard_Cod_act_hora_fk Foreign Key(RIF_guarderia, Cod_actividad) references Act_Guarderia(RIF_guarderia, Cod_actividad),
  Constraint Hora_init_valid Check(Hora_inicio LIKE ('__:__')),
  Constraint Hora_fin_valid Check(Hora_inicio LIKE ('__:__'))
);

CREATE TABLE Nino(
  CI_representante numeric(8),
  Letra char(1),
  Nombre varchar(15) NOT NULL,
  Apellido varchar (15) NOT NULL,
  Fecha_nacimiento Date NOT NULL,
  Sexo char(1) NOT NULL,
  Constraint CI_rep_Letra_nino_pk Primary Key(CI_representante, Letra),
  Constraint CI_rep_nino_fk Foreign Key(CI_representante) references Representante(CI),
  Constraint letra_nino_valid Check (Letra IN ('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')),
  Constraint sexo_valid Check(Sexo IN ('M', 'F'))
);

CREATE TABLE Parentesco_Padre(
  Letra_nino char(1),
  CI_principal varchar(10),
  CI_representante varchar(10),
  Parentesco varchar(10),
  Constraint Letra_nino_CI_ppal_CI_rep_Parentesco_pk Primary Key(Letra_nino,CI_principal,CI_representante,Parentesco),
  Constraint Letra_nino_CI_ppal_parent_fk Foreign Key(Letra_nino, CI_principal) references Nino(Letra, CI_representante),
  Constraint CI_rep_parent_fk Foreign Key (CI_representante) references Representante(CI)
);

CREATE TABLE Parentesco_nino(
  Letra_nino char(1),
  CI_principal varchar(10),
  Letra_pariente char(1),
  CI_pariente_principal varchar(10),
  Parentesco varchar(5) NOT NULL,
  Constraint Letra_nino_CI_ppal_parent_nino1_y_nino2_pk Primary Key(Letra_nino, CI_principal, Letra_pariente, CI_pariente_principal),
  Constraint Letra_nino_CI_ppal_parent_nino1_fk Foreign Key(Letra_nino, CI_principal) references Nino(Letra, CI_representante),
  Constraint Letra_nino_CI_ppal_parent_nino2_fk Foreign Key(Letra_pariente, CI_pariente_principal) references Nino(Letra, CI_representante)
);

CREATE TABLE Asistencia(
  Fecha date,
  Consecutivo_Ins varchar(10),
  Ano_inscripcion char(4),
  CI_representante varchar(10),
  Letra_nino char(1),
  CI_Padre_busco varchar(6),
  CI_auth_busco varchar(6),
  Hora_entrada char(5) NOT NULL,
  Hora_salida char (5) NOT NULL,
  Comio char(2) NOT NULL,
  Constraint Fecha_asistencia_pk Primary Key(Fecha),
  Constraint Consecutivo_Ins_asistencia_pk Primary Key(Consecutivo_Ins),
  Constraint Consecutivo_Ins_asistencia_fk Foreign Key(Consecutivo_Ins) references Inscripcion(Consecutivo),
  Constraint Ano_inscripcion_asistencia_pk Primary Key(Ano),
  Constraint Ano_inscripcion_asistencia_fk Foreign Key(Ano) references Inscripcion(Ano),
  Constraint CI_representante_asistencia_pk Primary Key(CI_representante),
  Constraint CI_representante_asistencia_fk Foreign Key(CI_representante) references Inscripcion(CI_representante),
  Constraint Letra_nino_asistencia_pk Primary Key(Letra_nino),
  Constraint Letra_nino_asistencia_fk Foreign Key(Letra_nino) references Inscripcion(Letra_nino),
  Constraint CI_Padre_busco_fk Foreign Key(CI_Padre_busco) references Representante(CI),
  Constraint CI_auth_busco_fk Foreign Key(CI_auth_busco) references Autorizado(CI),
  Constraint Hora_entrada_valid Check(Hora_entrada LIKE ('__:__')),
  Constraint Hora_salida_valid Check(Hora_salida LIKE ('__:__')),
  Constraint Comio_valid Check(Comio IN ('SI', 'NO')),
);

CREATE TABLE Pediatra(
  Codigo number(10),
  Nombre varchar(15) NOT NULL,
  Tlf_movil number(10) NOT NULL,
  Tlf_oficina number(10) NOT NULL,
  Constraint Codigo_pediatra_pk Primary Key(Codigo),
);

CREATE TABLE Atencion(
  Codigo_pediatra number(10),
  Letra_nino char(1),
  CI_ppal varchar(6),
  Constraint Codigo_pediatra_atencion_pk Primary Key(Codigo_pediatra),
  Constraint Codigo_pediatra_atencion_fk Foreign Key(Codigo_pediatra) references Pediatra(Codigo),
  Constraint Letra_nino_atencion_pk Primary Key(Letra_nino),
  Constraint Letra_nino_atencion_fk Foreign Key(Letra_nino) references Nino(Letra),
  Constraint CI_ppal_atencion_pk Primary Key(CI_ppal),
  Constraint CI_ppal_atencion_fk Foreign Key(CI_ppal) references Nino(CI_representante),
);

CREATE TABLE Juego(
  Codigo number(10),
  Nombre varchar(15) NOT NULL,
  Constraint Codigo_juego_pk Primary Key(Codigo),
);

CREATE TABLE Gusto(
  Codigo_juego number(10),
  Letra_nino char(1),
  CI_representante varchar(6),
  Constraint Codigo_juego_gusto_pk Primary Key(Codigo_juego),
  Constraint Codigo_juego_gusto_fk Foreign Key(Codigo_juego) references Juego(Codigo),
  Constraint Letra_nino_gusto_pk Primary Key(Letra_nino),
  Constraint Letra_nino_gusto_fk Foreign Key(Letra_nino) references Nino(Letra),
  Constraint CI_representante_gusto_pk Primary Key(CI_representante),
  Constraint CI_representante_gusto_fk Foreign Key(CI_representante) references Nino(CI_representante),
);

CREATE TABLE Sintoma(
  Codigo number(10),
  Descripcion varchar(40),
  Constraint Codigo_sintoma_pk Primary Key(Codigo),
);

CREATE TABLE Medicamento(
  Codigo number(10),
  Descripcion varchar(40),
  Constraint Codigo_sintoma_pk Primary Key(Codigo),
);

CREATE TABLE Tratamiento(
  Codigo_sintoma number(10),
  Codigo_medicamento number(10),
  Letra_nino char(1),
  CI_representante varchar(6),
  Cantidad number(4),
  Constraint Codigo_sintoma_tratamiento_pk Primary Key(Codigo_sintoma),
  Constraint Codigo_sintoma_tratamiento_fk Foreign Key(Codigo_sintoma) references Sintoma(Codigo),
  Constraint Codigo_medicamento_tratamiento_pk Primary Key(Codigo_medicamento),
  Constraint Codigo_medicamento_tratamiento_fk Foreign Key(Codigo_medicamento) references Medicamento(Codigo),
  Constraint Letra_nino_tratamiento_pk Primary Key(Letra_nino),
  Constraint Letra_nino_tratamiento_fk Foreign Key(Letra_nino) references Nino(Letra),
  Constraint CI_representante_tratamiento_pk Primary Key(CI_representante),
  Constraint CI_representante_tratamiento_fk Foreign Key(CI_representante) references Nino(CI_representante),
  Constraint Cantidad_tratamiento Primary Key(Cantidad),
);

CREATE TABLE Alergia(
  Codigo number(10),
  Descripcion varchar(40),
  Constraint Codigo_alergia_pk Primary Key(Codigo),
);

CREATE TABLE Enfermedad(
  Codigo number(10),
  Descripcion varchar(40),
  Constraint Codigo_enfermedad_pk Primary Key(Codigo),
);

CREATE TABLE Padecimiento_enfermedad(
  Codigo_enfermedad number(10),
  Letra_nino char(1),
  CI_representante varchar(6),
  Fecha date,
  Constraint Codigo_enfermedad_padecimiento_enfermedad_pk Primary Key(Codigo_enfermedad),
  Constraint Codigo_enfermedad_padecimiento_enfermedad_fk Foreign Key(Codigo_enfermedad) references Enfermedad(Codigo),
  Constraint Letra_nino_padecimiento_enfermedad_pk Primary Key(Letra_nino),
  Constraint Letra_nino_padecimiento_enfermedad_fk Foreign Key(Letra_nino) references Nino(Letra),
  Constraint CI_representante_padecimiento_enfermedad_pk Primary Key(CI_representante),
  Constraint CI_representante_padecimiento_enfermedad_fk Foreign Key(CI_representante) references Nino(CI_representante),
  Constraint Fecha_padecimiento_enfermedad_pk Primary Key(Fecha)
);

CREATE TABLE Padecimiento_alergia(
  Codigo_alergia number(10),
  Letra_nino char(1),
  CI_representante varchar(6),
  Constraint Codigo_alergia_padecimiento_alergia_pk Primary Key(Codigo_alergia),
  Constraint Codigo_enfermedad_padecimiento_alergia_fk Foreign Key(Codigo_alergia) references Alergia(Codigo),
  Constraint Letra_nino_padecimiento_alergia_pk Primary Key(Letra_nino),
  Constraint Letra_nino_padecimiento_alergia_fk Foreign Key(Letra_nino) references Nino(Letra),
  Constraint CI_representante_padecimiento_alergia_pk Primary Key(CI_representante),
  Constraint CI_representante_padecimiento_alergia_fk Foreign Key(CI_representante) references Nino(CI_representante)
);

CREATE TABLE autorizado_buscar(
  ci_autorizado numeric(8),
  ci_representante numeric(8),
  letra_nino char(1),
  CONSTRAINT auth_buscar_pk PRIMARY KEY (ci_autorizado, ci_representante, letra_nino),
  CONSTRAINT ci_auth_buscar_fk FOREIGN KEY (ci_autorizado) REFERENCES Autorizado (ci),
  CONSTRAINT letra_nino_buscar_fk, FOREIGN KEY (letra_nino, ci_representante) REFERENCES  Nino (letra, ci_representante)
);

CREATE TABLE inscripcion(
  ano NUMERIC(4) LIKE ('____'),
  consecutivo NUMERIC(10),
  rif_guarderia VARCHAR(12),
  ci_representante NUMERIC(8),
  letra_nino CHAR(1),
  fecha_inscripcion DATE NOT NULL,
  hora_desde CHAR(5) LIKE ('__:__'),
  hora_hasta CHAR(5) LIKE ('__:__'),
  CONSTRAINT inscripcion_pk PRIMARY KEY (ano, consecutivo, ci_representante, letra_nino),
  CONSTRAINT rif_guarderia__ins_fk FOREIGN KEY (rif_guarderia) REFERENCES Guarderia(rif),
  CONSTRAINT letra_ci_nino_insc_fk FOREIGN KEY (letra_nino, ci_representante) REFERENCES Nino(letra, ci_representante)
);

CREATE TABLE act_inscripcion(
  consecutivo_inscripcion NUMERIC(10),
  ano_inscripcion NUMERIC(4) LIKE ('____'),
  rif_guarderia VARCHAR(12),
  cod_actividad NUMERIC(10),
  fecha_actividad DATE,
  hora_inicio_act CHAR(5) LIKE ('__:__'),
  letra_nino CHAR(1),
  ci_representante NUMERIC(8),
  consto_actividad NUMERIC(8, 2) NOT NULL,
  CONSTRAINT act_inscripcion_pk PRIMARY KEY (consecutivo_inscripcion, ano_inscripcion, rif_guarderia, cod_actividad, fecha_actividad, hora_inicio_act, letra_nino, ci_representante),
  CONSTRAINT cons_act_ins_fk FOREIGN KEY (consecutivo_inscripcion, ano_inscripcion, letra_nino, ci_representante) REFERENCES inscripcion(consecutivo, ano, letra_nino, ci_representante),
  CONSTRAINT guard_act_ins_fk FOREIGN KEY (rif_guarderia, cod_actividad, fecha_actividad, hora_inicio_act) REFERENCES Horario_Act_Guarderia(rif_guarderia, cod_actividad, hora_inicio),
);

CREATE TABLE pago_mensual(
  consecutivo NUMERIC(10),
  cons_inscripcion NUMERIC(10),
  ano_inscripcion NUMERIC(4),
  concepto VARCHAR(20) NOT NULL,
  monto NUMERIC(10, 2) NOT NULL,
  fecha DATE NOT NULL,
  forma_pago VARCHAR(17) NOT NULL,
  CONSTRAINT cons_pago_mensual_pk PRIMARY KEY (consecutivo),
  CONSTRAINT ins_pago_mensual_fk FOREIGN KEY (cons_inscripcion, ano_inscripcion) REFERENCES inscripcion(consecutivo, ano)
  CONSTRAINT check_forma_pago_mensual CHECK (forma_pago IN ('Cheque', 'Tarjeta de crédito', 'Tarjeta de débito')),
  CONSTRAINT like_ano_insc_pago_mensual CHECK(ano_inscripcion LIKE('____'))
);

CREATE TABLE multa(
  fecha DATE,
  cons_inscripcion NUMERIC(10),
  ano_inscripcion NUMERIC(10),
  letra_nino CHAR(1),
  ci_representante NUMERIC(8),
  monto NUMERIC(10, 2),
  num_transferencia(20),
  CONSTRAINT fecha_multa_pk PRIMARY KEY (fecha),
  CONSTRAINT asistencia_multa_fk FOREIGN KEY (cons_inscripcion, ano_inscripcion, letra_nino, ci_representante) REFERENCES Asistencia(Consecutivo_Ins, ano_inscripcion, letra_nino, ci_representante)
  CONSTRAINT like_ano_insc_multa CHECK (ano_inscripcion LIKE('____'))
);
