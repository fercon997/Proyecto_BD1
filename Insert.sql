\connect proyecto

--Lugares
insert into Lugar_4 values(1, 'Miranda', 'Estado');
insert into Lugar_4 values(2, 'Vargas', 'Estado');
insert into Lugar_4 values(3, 'Nueva Esparta', 'Estado');
insert into Lugar_4 values(4, 'Caracas', 'Ciudad', 1);
insert into Lugar_4 values(5, 'Baruta', 'Municipio', 4);
insert into Lugar_4 values(6, 'Los Samanes', 'Urbanizacion', 5);
insert into Lugar_4 values(7, 'Av. Las Colinas', 'Avenida', 6);
insert into Lugar_4 values(8, 'Edf Las Ternarias', 'Edificio', 7);
insert into Lugar_4 values(9, 'El cafetal', 'Urbanizacion', 4);
insert into Lugar_4 values(10, 'El limon', 'Calle', 9);
insert into Lugar_4 values(11, 'Quinta Almeida', 'Casa', 10);
insert into Lugar_4 values(12, 'Chacao', 'Municipio', 4);
insert into Lugar_4 values(13, 'Calle Elice', 'Calle', 12);
insert into Lugar_4 values(14, 'Edf Merwe', 'Edificio', 13);
insert into Lugar_4 values(15, 'La Guaira', 'Ciudad', 2);
insert into Lugar_4 values(16, 'Catia La Mar', 'Municipio', 15);
insert into Lugar_4 values(17, 'Av Balneario', 'Avenida', 16);
insert into Lugar_4 values(18, 'Quinta Pampa', 'Casa', 17);
insert into Lugar_4 values(19, 'Pampatar', 'Municipio', 3);
insert into Lugar_4 values(20, 'Calle JM Vargas', 'Calle' ,19);
insert into Lugar_4 values(21, 'Castillo San Carlos de Barromeo', 'Casa', 20);
insert into Lugar_4 values(22, 'Res Colinavila', 'Edificio', 7);
insert into Lugar_4 values(23, 'Quinta Pnooorab', 'Casa', 17);
insert into Lugar_4 values(24, 'Juventus', 'Edificio', 20);
insert into Lugar_4 values(25, 'Las candelitas', 'Edificio', 10);

--Guarderias
insert into Guarderia_4 values ('J298754541-5',1200, 500, 400, 1400, 250, '08:00', '18:00', 8);
insert into Guarderia_4 values ('J764219187-2', 1250, 450, 500, 1500, 285, '08:30', '18:00', 10);
insert into Guarderia_4 values ('J128487902-9', 1400, 500,  500, 1500, 400, '08:00', '17:00', 14);
insert into Guarderia_4 values ('J516782778-4', 1100, 400, 400, 1200, 200, '07:00', '19:00', 17);
insert into Guarderia_4 values ('J279154667-2', 1200, 500, 400, 1400, 250, '08:00', '18:00', 21);

--Personal
insert into Personal_4 values ('V9214662' , 'Juan', 'Pollizschter', 04167198427, 'Universitario', 200, 1, '2017-12-02', 22, 'J128487902-9' );
insert into Personal_4 values ('V24718965' , 'Enrique', 'Pollizschter', 04167198578, 'Bachillerato', 200, 1, '2018-01-20', 22, 'J298754541-5');
insert into Personal_4 values ('V8176220', 'Maria', 'Hernandez', 04249810876, 'Postgrado', 400, 1, '2016-09-17', 23, 'J516782778-4');
insert into Personal_4 values ('V17958047', 'Ernesto', 'Dawae', 04269108765, 'TSU', 400, 1, '2017-05-20', 22, 'J764219187-2');
insert into Personal_4 values ('E25978194', 'Andrea', 'Pirlo', 04129876178, 'Universitario', 400, 1, '2017-11-10', 24, 'J279154667-2');

--Experiencia
insert into Experiencia_4 values ('V9214662', 'Reformatorio');
insert into Experiencia_4 values ('V9214662', 'Plan vacacional');
insert into Experiencia_4 values ('V24718965', 'Reformatorio');
insert into Experiencia_4 values ('V17958047', '7 Hermanos menores');
insert into Experiencia_4 values ('E25978194', 'Academia de futbol infantil');

--Representantes
insert into Representante_4 values ('V8108418', 'Henrietta', 'Zazalli', 04245019827, 02129448721, 02129871451, 'Hzaza@gmail.com', 'Albañil', 'C', 1, 23 );
insert into Representante_4 values ('V24871662', 'Daniel', 'Guerra', 04167810091, 02129876612, 02128876542, 'losada@gmail.com', 'Ingeniero', 'S', 1, 24);
insert into Representante_4 values ('V20145701', 'Cristoff', 'Ruiz', 04129018764, 02129087651, 02127650918, 'ffRui@gmail.com', 'Profesor', 'C', 1, 25);
insert into Representante_4 values ('V14910819', 'Claudia', 'Marcano', 04269018765, 02120918716, 02129108714, 'siso@hotmail.com', 'Contadora', 'C', 1, 25);
insert into Representante_4 values ('E10917655', 'Danielle', 'Sturaro', 04169870191, 02129980198, 02126517891, 'atalancia@yahoo.net', 'Mafioso', 'S', 1, 22);

-- Niño
INSERT INTO Nino_4 VALUES ('V8108418', 'A', 'Diego', 'Gonzáles', '2012-06-21', 'M');
INSERT INTO Nino_4 VALUES ('V8108418', 'B', 'Fernando', 'Gonzáles', '2013-03-17', 'M');
INSERT INTO Nino_4 VALUES ('V8108418', 'C', 'Antonieta', 'Gonzáles', '2014-05-13', 'F');
INSERT INTO Nino_4 VALUES ('V8108418', 'D', 'Daniela', 'Gonzáles', '2014-05-13', 'F');
INSERT INTO Nino_4 VALUES ('V8108418', 'E', 'Armando', 'Gonzáles', '2014-05-21', 'M');
INSERT INTO Nino_4 VALUES ('V24871662', 'A', 'María', 'Guerra', '2013-02-15', 'F');
INSERT INTO Nino_4 VALUES ('V24871662', 'B', 'Adrián', 'Guerra', '2014-03-21', 'M');
INSERT INTO Nino_4 VALUES ('V24871662', 'C', 'Jesús', 'Guerra', '2016-12-25', 'M');
INSERT INTO Nino_4 VALUES ('V20145701', 'A', 'Rosana', 'Ruiz', '2012-01-14', 'F');
INSERT INTO Nino_4 VALUES ('V20145701', 'B', 'Carlos', 'Ruiz', '2013-02-26', 'M');
INSERT INTO Nino_4 VALUES ('V20145701', 'C', 'Laureano', 'Ruiz', '2013-02-26', 'M');
INSERT INTO Nino_4 VALUES ('V20145701', 'D', 'Luis', 'Ruiz', '2015-08-30', 'M');
INSERT INTO Nino_4 VALUES ('V20145701', 'E', 'Ariana', 'Ruiz', '2015-08-30', 'F');
INSERT INTO Nino_4 VALUES ('V20145701', 'F', 'Laura', 'Ruiz', '2015-08-30', 'F');
INSERT INTO Nino_4 VALUES ('V20145701', 'G', 'José', 'Ruiz', '2016-10-12', 'M');

--Autorizado
insert into Autorizado_4 values ('V9187221', 'Jorge', 'Ramirez', 04166908170);
insert into Autorizado_4 values ('V9214662', 'Juan', 'Pollizschter', 04167198427);
insert into Autorizado_4 values ('V12018977', 'Alfredo', 'Martinez', 04249108761);
insert into Autorizado_4 values ('V4987907', 'Sergio', 'Reveron', 04168901245);
insert into Autorizado_4 values ('V25987122', 'Augusto', 'Marchisio', 04149871020);

--Actividades
insert into Actividad_4 values (1, 'Futbol', 'Los niños juegan futbol con un balón 3', 4, 1);
insert into Actividad_4 values(2, 'Natacion', 'Los niños aprenden a nadar con un instructor en un club seleccionado', 4, 1);
insert into Actividad_4 values(3, 'Baby gym', 'Actividades recreativas para mantener activos a los mas pequeños', 1, 0);
insert into Actividad_4 values(4, 'Matematica', 'Se les enseña lo básico de matematicas que requieren para entrar al colegio', 3, 0);
insert into Actividad_4 values(5, 'Siesta', 'Los niños duermen, ¡Es importante descansar!', 1, 0);

--Actividad-guarderia
insert into Act_Guarderia_4 values('J298754541-5', 1, 200, 6, 30, 'V24718965');
insert into Act_Guarderia_4 values('J764219187-2', 2, 200, 10, 40, 'V17958047');
insert into Act_Guarderia_4 values ('J128487902-9', 3, 200, 3, 25, 'V9214662');
insert into Act_Guarderia_4 values ('J516782778-4', 4, 200, 2, 40, 'V8176220');
insert into Act_Guarderia_4 values ('J279154667-2', 5, 200, 1, 100, 'E25978194');

--Inscripción
INSERT INTO inscripcion_4 values (2013, 1, 'J298754541-5', 'V8108418', 'A', '2013-09-02', '07:00', '17:00');

--Pediatras
insert into Pediatra_4 values ('1' , 'Jose Feliciano' , '04123345621' , '02121298331');
insert into Pediatra_4 values ('2' , 'Diego Garcia' , '04241948631' , '02125359871');
insert into Pediatra_4 values ('3' , 'Mariana Mendez' , '04167821231' ,  '02128721345');
insert into Pediatra_4 values ('4' , 'Javier Clemente' , '04143561891' , '02123457896');
insert into Pediatra_4 values ('5' , 'Daniela Bustamante' , '04126521487' , '02123567823');

--Juego
insert into Juego_4 values ('1' , 'Lego');
insert into Juego_4 values ('2' , 'Lanzar Pelota');	
insert into Juego_4 values ('3' , 'Patear Pelota');	
insert into Juego_4 values ('4' , 'Tacos');	
insert into Juego_4 values ('5' , 'Triciclo');

--Sintoma
insert into Sintoma_4 values ('1' , 'Tos');
insert into Sintoma_4 values ('2' , 'Dolor de Barriga');
insert into Sintoma_4 values ('3' , 'Fiebre');
insert into Sintoma_4 values ('4' , 'Dolor de Garganta');
insert into Sintoma_4 values ('5' , 'Mocos');

--Medicamento
insert into Medicamento_4 values ('1' , 'Atamel');
insert into Medicamento_4 values ('2' , 'Ibuprofeno');
insert into Medicamento_4 values ('3' , 'Jarabe para la Tos');
insert into Medicamento_4 values ('4' , 'Antialérgico');
insert into Medicamento_4 values ('5' , 'Manzanilla');

--Alergia
insert into Alergia_4 values ('1' , 'Al Ibuprofeno');
insert into Alergia_4 values ('2' , 'A los Camarones');
insert into Alergia_4 values ('3' , 'Intolerante a la Lactosa');
insert into Alergia_4 values ('4' , 'A los Acaros');
insert into Alergia_4 values ('5' , 'Al gluten');

--Enfermedad
insert into Enfermedad_4 values ('1' , 'Lechina');
insert into Enfermedad_4 values ('2' , 'Paperas');
insert into Enfermedad_4 values ('3' , 'Asma');
insert into Enfermedad_4 values ('4' , 'Sarampión');
insert into Enfermedad_4 values ('5' , 'Otitis');

--Autorizado Buscar
