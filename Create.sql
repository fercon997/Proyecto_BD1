CREATE DATABASE Proyecto;
\connect proyecto

CREATE SEQUENCE Lugar_sequence
  start 1
  increment 1;

CREATE SEQUENCE Actividad_sequence
  start 1
  increment 1;

CREATE SEQUENCE Inscripcion_sequence
  start 1
  increment 1;

CREATE SEQUENCE Pediatra_sequence
  start 1
  increment 1;

CREATE SEQUENCE Juego_sequence
  start 1
  increment 1;

CREATE SEQUENCE Sintoma_sequence
  start 1
  increment 1;

CREATE SEQUENCE Medicamento_sequence
  start 1
  increment 1;

CREATE SEQUENCE Alergia_sequence
  start 1
  increment 1;

CREATE SEQUENCE Enfermedad_sequence
  start 1
  increment 1;

CREATE SEQUENCE Comida_sequence
  start 1
  increment 1;

CREATE SEQUENCE Plato_sequence
  start 1
  increment 1;

CREATE SEQUENCE Menu_sequence
  start 1
  increment 1;

CREATE TABLE Lugar_4(
  Codigo numeric(10),
  Nombre varchar(50) NOT NULL,
  tipo varchar(12) NOT NULL,
  cod_superior numeric(10),
  Constraint cod_dir_pk Primary Key(Codigo),
  Constraint cod_dir_fk Foreign Key(cod_superior) references Lugar_4(Codigo),
  Constraint tipo_valid Check(tipo IN ('Estado', 'Ciudad', 'Municipio', 'Urbanizacion', 'Calle', 'Avenida', 'Casa', 'Edificio'))
);

CREATE TABLE Guarderia_4(
  RIF varchar(12),
  nombre varchar(50) NOT NULL,
  Costo_mensualidad numeric(15) NOT NULL,
  Costo_multa numeric(10) NOT NULL,
  Costo_transporte numeric(10) NOT NULL,
  Costo_agodic numeric(10) NOT NULL,
  Costo_hora_extra numeric(10) NOT NULL,
  Horario_entrada time,
  Horario_salida  time,
  Cod_direccion numeric(10),
  Constraint rif_guard_pk Primary Key(Rif),
  Constraint cod_dir_guard_fk Foreign Key(Cod_direccion) references Lugar_4 (Codigo)
);

CREATE TABLE Personal_4(
  CI varchar(10),
  Nombre varchar(20) NOT NULL,
  Apellido varchar(20) NOT NULL,
  Celular numeric(11) NOT NULL,
  Nivel_estudio varchar(15) NOT NULL,
  Sueldo numeric(10) NOT NULL,
  SenalEncargada numeric(1) NOT NULL Default 0,
  Fecha_responsable Date Default NULL,
  Cod_direccion numeric(10),
  RIF_guarderia varchar(12),
  Constraint CI_per_pk Primary Key(CI),
  Constraint RIF_guard_per_fk Foreign Key(Rif_guarderia) references Guarderia_4 (Rif),
  Constraint Cod_dir_per_fk Foreign Key(Cod_direccion) references Lugar_4 (Codigo),
  Constraint encargada_bool Check(SenalEncargada IN (0,1)),
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
  Nombre varchar(20) NOT NULL,
  Apellido varchar(20) NOT NULL,
  Celular numeric(10) NOT NULL,
  Tlf_casa numeric(10) NOT NULL,
  Tlf_oficina numeric(10) NOT NULL,
  Email varchar(30) NOT NULL,
  Profesion varchar(20) NOT NULL,
  Estado_civil char(1) NOT NULL,
  Principal numeric(1) NOT NULL,
  Cod_direccion numeric(10),
  Constraint CI_rep_pk Primary Key(CI),
  Constraint Cod_dir_rep_fk Foreign Key(Cod_direccion) references Lugar_4(Codigo),
  Constraint edo_civil_valid Check(Estado_civil IN ('C', 'S')),
  Constraint ppal_bool Check(Principal IN (0,1)),
  Constraint email_valid Check(Email LIKE ('%_@%_._%')),
  Constraint ppal_valid check(Principal IN (0,1))
);

CREATE TABLE Autorizado_4(
  CI varchar(10),
  Nombre varchar(20) NOT NULL,
  Apellido varchar(20) NOT NULL,
  Celular numeric(10) NOT NULL,
  Constraint CI_auth_pk Primary Key(CI)
);

CREATE TABLE Actividad_4(
  Codigo numeric(10),
  Nombre varchar(15) NOT NULL,
  Descripcion varchar(100) NOT NULL,
  EdadMinima numeric(1) NOT NULL,
  Transporte numeric(1) NOT NULL,
  Constraint Cod_act_pk Primary Key(Codigo),
  Constraint edad_min_act_valid Check(EdadMinima IN (1,2,3,4,5,6)),
  Constraint transpor_bool Check(Transporte IN (0,1))
);

CREATE TABLE Act_Guarderia_4(
  RIF_guarderia varchar(12),
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
  RIF_guarderia varchar(12),
  Cod_actividad numeric(10),
  Fecha Date,
  Hora_inicio time,
  Hora_fin time NOT NULL,
  Constraint RIF_guard_Cod_act_hora_Fecha_Hora_init_pk Primary Key(RIF_guarderia, Cod_actividad, Fecha, Hora_inicio),
  Constraint RIF_guard_Cod_act_hora_fk Foreign Key(RIF_guarderia, Cod_actividad) references Act_Guarderia_4(RIF_guarderia, Cod_actividad)
);

CREATE TABLE Nino_4(
  CI_representante varchar(10),
  Letra char(1),
  Nombre varchar(15) NOT NULL,
  Apellido varchar (15) NOT NULL,
  Fecha_nacimiento Date NOT NULL,
  Sexo char(1) NOT NULL,
  Constraint CI_rep_Letra_nino_pk Primary Key(CI_representante, Letra),
  Constraint CI_rep_nino_fk Foreign Key(CI_representante) references Representante_4(CI) ON DELETE CASCADE,
  Constraint letra_nino_valid Check (Letra IN ('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')),
  Constraint sexo_valid Check(Sexo IN ('M', 'F')),
  Constraint nino_edad_valid Check( (CURRENT_DATE - Fecha_nacimiento) < 2550 AND (CURRENT_DATE - Fecha_nacimiento) >365)
);

CREATE TABLE inscripcion_4(
  ano NUMERIC(4),
  consecutivo NUMERIC(10),
  rif_guarderia VARCHAR(12),
  ci_representante varchar(10),
  letra_nino CHAR(1),
  fecha_inscripcion DATE NOT NULL,
  hora_desde time NOT NULL,
  hora_hasta time NOT NULL,
  CONSTRAINT inscripcion_pk PRIMARY KEY (ano, consecutivo, ci_representante, letra_nino),
  CONSTRAINT rif_guarderia__ins_fk FOREIGN KEY (rif_guarderia) REFERENCES Guarderia_4(rif),
  CONSTRAINT letra_ci_nino_insc_fk FOREIGN KEY (letra_nino, ci_representante) REFERENCES Nino_4(letra, ci_representante) ON DELETE CASCADE,
  Constraint tiempo_total_valid Check( (EXTRACT(HOUR FROM hora_hasta)  - Extract(HOUR FROM hora_desde) ) <= 10)
);

CREATE TABLE Parentesco_Padre_4(
  Letra_nino char(1),
  CI_principal varchar(10),
  CI_representante varchar(10),
  Parentesco varchar(5),
  Constraint Letra_nino_CI_ppal_CI_rep_Parentesco_pk Primary Key(Letra_nino,CI_principal,CI_representante,Parentesco),
  Constraint Letra_nino_CI_ppal_parent_fk Foreign Key(Letra_nino, CI_principal) references Nino_4(Letra, CI_representante) ON DELETE CASCADE,
  Constraint CI_rep_parent_fk Foreign Key (CI_representante) references Representante_4(CI) ON DELETE CASCADE,
  Constraint parent_valid Check(Parentesco IN ('Padre', 'Madre'))
);

CREATE TABLE Parentesco_nino_4(
  Letra_nino char(1),
  CI_principal varchar(10),
  Letra_pariente char(1),
  CI_pariente_principal varchar(10),
  Parentesco varchar(10) NOT NULL,
  Constraint Letra_nino_CI_ppal_parent_nino1_y_nino2_pk Primary Key(Letra_nino, CI_principal, Letra_pariente, CI_pariente_principal),
  Constraint Letra_nino_CI_ppal_parent_nino1_fk Foreign Key(Letra_nino, CI_principal) references Nino_4(Letra, CI_representante) ON DELETE CASCADE,
  Constraint Letra_nino_CI_ppal_parent_nino2_fk Foreign Key(Letra_pariente, CI_pariente_principal) references Nino_4(Letra, CI_representante) ON DELETE CASCADE
);

CREATE TABLE Asistencia_4(
  Fecha date,
  Consecutivo_Ins numeric(10),
  Ano_inscripcion numeric(4),
  CI_representante varchar(10),
  Letra_nino char(1),
  CI_Padre_busco varchar(10),
  CI_auth_busco varchar(10),
  Hora_entrada time NOT NULL,
  Hora_salida time (5) NOT NULL,
  Comio char(2) NOT NULL,
  Constraint Asistencia_pk Primary Key(Fecha,Consecutivo_Ins,Ano_inscripcion,CI_representante,Letra_nino),
  Constraint Inscripcion_asistencia_fk Foreign Key(Consecutivo_Ins,Ano_inscripcion,CI_representante, letra_nino) references Inscripcion_4(consecutivo, ano, ci_representante, letra_nino) ON DELETE CASCADE,
  Constraint Representante_asistencia_fk Foreign Key(CI_Padre_busco) references Representante_4(CI) ON DELETE CASCADE,
  Constraint Autorizado_asistencia_fk Foreign Key(CI_auth_busco) references Autorizado_4(CI),
  Constraint Comio_valid Check(Comio IN ('SI', 'NO')),
  Constraint fecha_anterior_valid Check( (Extract(YEAR FROM Fecha) - Ano_inscripcion) >= 0)
);

CREATE TABLE Pediatra_4(
  Codigo numeric(10),
  Nombre varchar(25) NOT NULL,
  Tlf_movil numeric(10) NOT NULL,
  Tlf_oficina numeric(10) NOT NULL,
  Constraint Pediatra_pk Primary Key(Codigo)
);

CREATE TABLE Atencion_4(
  Codigo_pediatra numeric(10),
  Letra_nino char(1),
  CI_ppal varchar(10),
  Constraint Atencion_pk Primary Key(Codigo_pediatra,Letra_nino,CI_ppal),
  Constraint Pediatra_atencion_fk Foreign Key(Codigo_pediatra) references Pediatra_4(Codigo),
  Constraint Nino_atencion_fk Foreign Key(Letra_nino,CI_ppal) references Nino_4(Letra,CI_representante) ON DELETE CASCADE
);

CREATE TABLE Juego_4(
  Codigo numeric(10),
  Nombre varchar(25) NOT NULL,
  Constraint Juego_pk Primary Key(Codigo)
);

CREATE TABLE Gusto_4(
  Codigo_juego numeric(10),
  Letra_nino char(1),
  CI_representante varchar(10),
  Constraint Gusto_pk Primary Key(Codigo_juego,Letra_nino,CI_representante),
  Constraint Juego_gusto_fk Foreign Key(Codigo_juego) references Juego_4(Codigo),
  Constraint Nino_gusto_fk Foreign Key(Letra_nino,CI_representante) references Nino_4(Letra,CI_representante) ON DELETE CASCADE
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
  CI_representante varchar(10),
  Cantidad varchar(7),
  Constraint Tratamiento_pk Primary Key(Codigo_sintoma,Codigo_medicamento,Letra_nino,CI_representante,Cantidad),
  Constraint Sintoma_tratamiento_fk Foreign Key(Codigo_sintoma) references Sintoma_4(Codigo),
  Constraint Medicamento_tratamiento_fk Foreign Key(Codigo_medicamento) references Medicamento_4(Codigo),
  Constraint Nino_tratamiento_fk Foreign Key(Letra_nino,CI_representante) references Nino_4(Letra,CI_representante) ON DELETE CASCADE
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
  CI_representante varchar(10),
  Fecha date,
  Constraint Padecimiento_enfermedad_pk Primary Key(Codigo_enfermedad,Letra_nino,CI_representante,Fecha),
  Constraint Enfermedad_padecimiento_enfermedad_fk Foreign Key(Codigo_enfermedad) references Enfermedad_4(Codigo),
  Constraint Nino_padecimiento_enfermedad_fk Foreign Key(Letra_nino,CI_representante) references Nino_4(Letra,CI_representante) ON DELETE CASCADE
);

CREATE TABLE Padecimiento_alergia_4(
  Codigo_alergia numeric(10),
  Letra_nino char(1),
  CI_representante varchar(10),
  Constraint Padecimiento_alergia_pk Primary Key(Codigo_alergia,Letra_nino,CI_representante),
  Constraint Alergia_padecimiento_alergia_fk Foreign Key(Codigo_alergia) references Alergia_4(Codigo),
  Constraint Nino_padecimiento_alergia_fk Foreign Key(Letra_nino,CI_representante) references Nino_4(Letra,CI_representante) ON DELETE CASCADE
);

CREATE TABLE autorizado_buscar_4(
  ci_autorizado varchar(10),
  letra_nino char(1),
  ci_representante varchar(10),
  CONSTRAINT auth_buscar_pk PRIMARY KEY (ci_autorizado, ci_representante, letra_nino),
  CONSTRAINT ci_auth_buscar_fk FOREIGN KEY (ci_autorizado) REFERENCES Autorizado_4(ci),
  CONSTRAINT letra_nino_buscar_fk FOREIGN KEY (letra_nino, ci_representante) REFERENCES  Nino_4(letra, ci_representante) ON DELETE CASCADE
);

CREATE TABLE act_inscripcion_4(
  ano_inscripcion NUMERIC(4),
  consecutivo_inscripcion NUMERIC(10),
  rif_guarderia VARCHAR(12),
  ci_representante varchar(10),
  letra_nino CHAR(1),
  cod_actividad NUMERIC(10),
  fecha_actividad DATE,
  hora_inicio_act time,
  consto_actividad NUMERIC(8, 2) NOT NULL,
  CONSTRAINT act_inscripcion_pk PRIMARY KEY (consecutivo_inscripcion, ano_inscripcion, rif_guarderia, cod_actividad, fecha_actividad, hora_inicio_act, letra_nino, ci_representante),
  CONSTRAINT cons_act_ins_fk FOREIGN KEY (consecutivo_inscripcion, ano_inscripcion, letra_nino, ci_representante) REFERENCES inscripcion_4(consecutivo, ano, letra_nino, ci_representante) ON DELETE CASCADE,
  CONSTRAINT guard_act_ins_fk FOREIGN KEY (rif_guarderia, cod_actividad, fecha_actividad, hora_inicio_act) REFERENCES Horario_Act_Guarderia_4(rif_guarderia, cod_actividad, fecha, hora_inicio)
);

CREATE TABLE pago_mensual_4(
  consecutivo NUMERIC(10),
  cons_inscripcion NUMERIC(10),
  ano_inscripcion NUMERIC(4),
  ci_representante varchar(10),
  letra_nino CHAR(1),
  concepto VARCHAR(20) NOT NULL,
  monto NUMERIC(10, 2) NOT NULL,
  fecha DATE NOT NULL,
  forma_pago VARCHAR(17) NOT NULL,
  CONSTRAINT cons_pago_mensual_pk PRIMARY KEY (consecutivo),
  CONSTRAINT ins_pago_mensual_fk FOREIGN KEY (cons_inscripcion, ano_inscripcion, ci_representante, letra_nino) REFERENCES inscripcion_4(consecutivo, ano, ci_representante, letra_nino) ON DELETE CASCADE,
  CONSTRAINT check_forma_pago_mensual CHECK (forma_pago IN ('Cheque', 'Tarjeta de crédito', 'Tarjeta de débito'))
);

CREATE TABLE multa_4(
  fecha DATE,
  fecha_asistencia DATE,
  cons_inscripcion NUMERIC(10),
  ano_inscripcion NUMERIC(10),
  letra_nino CHAR(1),
  ci_representante varchar(10),
  monto NUMERIC(10, 2),
  num_transferencia NUMERIC(20),
  CONSTRAINT fecha_multa_pk PRIMARY KEY (fecha),
  CONSTRAINT asistencia_multa_fk FOREIGN KEY (fecha_asistencia, cons_inscripcion, ano_inscripcion, letra_nino, ci_representante) REFERENCES Asistencia_4(fecha, Consecutivo_Ins, ano_inscripcion, letra_nino, ci_representante) ON DELETE CASCADE
);

CREATE TABLE plato_4(
  codigo NUMERIC(10),
  descripcion VARCHAR(20) NOT NULL,
  CONSTRAINT cod_plato_pk PRIMARY KEY (codigo)
);

CREATE TABLE comida_4(
  codigo NUMERIC(10),
  tipo VARCHAR(13) NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  CONSTRAINT cod_comida_pk PRIMARY KEY (codigo),
  CONSTRAINT tipo_comida_is_valid CHECK (tipo IN ('Proteina', 'Carbohidrato', 'Jugo natural', 'Ensalada'))
);

CREATE TABLE comida_plato_4(
  cod_plato NUMERIC(10),
  cod_comida NUMERIC(10),
  CONSTRAINT cod_plato_comida_pk PRIMARY KEY (cod_plato, cod_comida),
  CONSTRAINT cod_plato_comida_fk FOREIGN KEY (cod_plato) REFERENCES plato_4(codigo),
  CONSTRAINT cod_comida_plato_fk FOREIGN KEY (cod_comida) REFERENCES comida_4(codigo)
);

CREATE TABLE menu_4(
  numero NUMERIC(10),
  fecha DATE,
  rif_guarderia VARCHAR(12),
  costo NUMERIC(6, 2) NOT NULL,
  CONSTRAINT menu_pk PRIMARY KEY (numero, fecha),
  CONSTRAINT rif_menu_fk FOREIGN KEY (rif_guarderia) REFERENCES Guarderia_4(rif),
  CONSTRAINT fecha_semana_valid Check( Extract(DOW FROM fecha) != 6 AND Extract(DOW FROM fecha) != 0)
);

CREATE TABLE factura_menu_4(
  fecha DATE,
  cons_inscripcion NUMERIC(10),
  ano_inscripcion NUMERIC(4),
  ci_representante varchar(10),
  letra_nino CHAR(1),
  numero_menu NUMERIC(8),
  fecha_menu DATE,
  numero_trans NUMERIC(20) NOT NULL,
  banco varchar(20) NOT NULL,
  CONSTRAINT factura_menu_pk PRIMARY KEY (fecha, cons_inscripcion, ano_inscripcion, letra_nino, ci_representante),
  CONSTRAINT factura_menu_insc_fk FOREIGN KEY (cons_inscripcion, ano_inscripcion, letra_nino, ci_representante) REFERENCES inscripcion_4(consecutivo, ano, letra_nino, ci_representante) ON DELETE CASCADE,
  CONSTRAINT factura_menu_fk FOREIGN KEY (numero_menu, fecha_menu) REFERENCES menu_4(numero, fecha),
  Constraint fecha_anterior_valid Check( (Extract(YEAR FROM Fecha) - Ano_inscripcion) >= 0)
);

CREATE TABLE menu_semanal_4(
  numero_menu NUMERIC(10),
  fecha_menu DATE,
  cod_plato NUMERIC(10),
  CONSTRAINT menu_semanal_pk PRIMARY KEY (numero_menu, fecha_menu, cod_plato),
  CONSTRAINT menu_semanal_menu_fk FOREIGN KEY (numero_menu, fecha_menu) REFERENCES menu_4(numero, fecha) ON DELETE CASCADE,
  CONSTRAINT plato_menu_semanal_fk FOREIGN KEY (cod_plato) REFERENCES plato_4(codigo)
);
