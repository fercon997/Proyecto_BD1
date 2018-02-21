\connect proyecto

--Lugares
insert into Lugar_4 values(1, 'Miranda', 'Estado');
insert into Lugar_4 values(2, 'Vargas', 'Estado');
insert into Lugar_4 values(3, 'Nueva Esparta', 'Estado');
insert into Lugar_4 values(4, 'Caracas', 'Ciudad', 1);
insert into Lugar_4 values(5, 'Baruta', 'Municipio', 4);
insert into Lugar_4 values(6, 'Los Samanes', 'Urbanizacion', 5);
insert into Lugar_4 values(7, 'Av. Las Colinas', 'Avenida', 6);
insert into Lugar_4 values(8, 'Edf. Las Ternarias', 'Edificio', 7);
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
insert into Guarderia_4 values ('J764219187-2', 1250, 450, 500, 1500, 285, '08:30', '18:00', 11);
insert into Guarderia_4 values ('J128487902-9', 1400, 500,  500, 1500, 400, '08:00', '17:00', 14);
insert into Guarderia_4 values ('J516782778-4', 1100, 400, 400, 1200, 200, '07:00', '19:00', 18);
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
insert into Representante_4 values ('V24871662', 'Daniel', 'Guerra', 04167810091, 02959876612, 02958876542, 'losada@gmail.com', 'Ingeniero', 'S', 1, 24);
insert into Representante_4 values ('V20145701', 'Cristoff', 'Ruiz', 04129018764, 02129087651, 02127650918, 'ffRui@gmail.com', 'Profesor', 'C', 1, 25);
insert into Representante_4 values ('V14910819', 'Claudia', 'Marcano', 04269018765, 02120918716, 02129108714, 'siso@hotmail.com', 'Contadora', 'C', 1, 25);
insert into Representante_4 values ('E10917655', 'Danielle', 'Sturaro', 04169870191, 02129980198, 02126517891, 'atalancia@yahoo.net', 'Mafioso', 'S', 1, 22);
insert into Representante_4 values ('V9757397', 'Gabriela', 'Correia', 04141862963, 02127549738, 02129758467, 'gaby007@gmail.com', 'Profesora', 'C', 1, 22);
insert into Representante_4 values ('V12769574', 'Jorge', 'Colina', 04168479356, 02957935621, 02958768933, 'jorgecol@hotmail.com', 'Psicólogo', 'S', 1, 24);
insert into Representante_4 values ('E21879397', 'Matías', 'Messi', 04247893873, 02121231232, 02126894783, 'messimatias@gmail.com', 'Chef', 'C', 1, 23);
insert into Representante_4 values ('V20775874', 'Valeria', 'Gutierrez', 04128746736, 02126749839, 02128476378, 'gutivaleria@gmail.com', 'Ingeniero', 'C', 1, 8);
insert into Representante_4 values ('V15748739', 'María', 'Guevara', 04267578476, 02957389873, 02956748937, 'maryguevara@hotmail.com', 'Periodista', 'C', 1, 24);

-- Niño
INSERT INTO Nino_4 VALUES ('V8108418', 'A', 'Diego', 'Gonzáles', '2012-06-21', 'M');
INSERT INTO Nino_4 VALUES ('V8108418', 'B', 'Fernando', 'Gonzáles', '2013-03-17', 'M');
INSERT INTO Nino_4 VALUES ('V9757397', 'A', 'Antonieta', 'González', '2014-05-13', 'F');
INSERT INTO Nino_4 VALUES ('V9757397', 'B', 'Daniela', 'González', '2014-05-13', 'F');
INSERT INTO Nino_4 VALUES ('V9757397', 'C', 'Armando', 'González', '2014-05-21', 'M');
INSERT INTO Nino_4 VALUES ('V24871662', 'A', 'María', 'Guerra', '2013-02-15', 'F');
INSERT INTO Nino_4 VALUES ('V24871662', 'B', 'Adrián', 'Guerra', '2014-03-21', 'M');
INSERT INTO Nino_4 VALUES ('V24871662', 'C', 'Jesús', 'Guerra', '2016-12-25', 'M');
INSERT INTO Nino_4 VALUES ('V12769574', 'A', 'Rosana', 'Colina', '2012-01-14', 'F');
INSERT INTO Nino_4 VALUES ('V12769574', 'B', 'Carlos', 'Colina', '2013-02-26', 'M');
INSERT INTO Nino_4 VALUES ('V12769574', 'C', 'Laureano', 'Colina', '2013-02-26', 'M');
INSERT INTO Nino_4 VALUES ('V20145701', 'A', 'Luis', 'Ruiz', '2015-08-30', 'M');
INSERT INTO Nino_4 VALUES ('V20145701', 'B', 'Ariana', 'Ruiz', '2015-08-30', 'F');
INSERT INTO Nino_4 VALUES ('E21879397', 'A', 'Laura', 'Messi', '2015-08-30', 'F');
INSERT INTO Nino_4 VALUES ('E21879397', 'B', 'José', 'Messi', '2016-10-12', 'M');
INSERT INTO Nino_4 VALUES ('V14910819', 'A', 'Sofía', 'Cañizales', '2012-09-11', 'F');
INSERT INTO Nino_4 VALUES ('V14910819', 'B', 'Carmen', 'Cañizales', '2012-09-11', 'F');
INSERT INTO Nino_4 VALUES ('V20775874', 'A', 'Selena', 'Pérez', '2012-09-11', 'F');
INSERT INTO Nino_4 VALUES ('V20775874', 'B', 'Rosita', 'Pérez', '2012-09-11', 'F');
INSERT INTO Nino_4 VALUES ('V20775874', 'C', 'Hector', 'Pérez', '2015-02-28', 'M');
INSERT INTO Nino_4 VALUES ('V15748739', 'A', 'Andrea', 'Gómez', '2012-01-02', 'F');
INSERT INTO Nino_4 VALUES ('V15748739', 'B', 'Fabio', 'Gómez', '2013-11-15', 'M');
INSERT INTO Nino_4 VALUES ('E10917655', 'A', 'Concetta', 'Sturaro', '2012-12-28', 'F');
INSERT INTO Nino_4 VALUES ('E10917655', 'B', 'Giorgio', 'Sturaro', '2014-10-21', 'M');
INSERT INTO Nino_4 VALUES ('E10917655', 'C', 'Giulia', 'Sturaro', '2015-12-03', 'F');

--Prentesco Padre
INSERT INTO Parentesco_Padre_4 VALUES ('A', 'V8108418', 'V8108418', 'Madre');
INSERT INTO Parentesco_Padre_4 VALUES ('B', 'V8108418', 'V8108418', 'Madre');
INSERT INTO Parentesco_Padre_4 VALUES ('A', 'V9757397', 'V9757397', 'Madre');
INSERT INTO Parentesco_Padre_4 VALUES ('B', 'V9757397', 'V9757397', 'Madre');
INSERT INTO Parentesco_Padre_4 VALUES ('C', 'V9757397', 'V9757397', 'Madre');
INSERT INTO Parentesco_Padre_4 VALUES ('A', 'V24871662', 'V24871662', 'Padre');
INSERT INTO Parentesco_Padre_4 VALUES ('B', 'V24871662', 'V24871662', 'Padre');
INSERT INTO Parentesco_Padre_4 VALUES ('C', 'V24871662', 'V24871662', 'Padre');
INSERT INTO Parentesco_Padre_4 VALUES ('A', 'V12769574', 'V12769574', 'Padre');
INSERT INTO Parentesco_Padre_4 VALUES ('B', 'V12769574', 'V12769574', 'Padre');
INSERT INTO Parentesco_Padre_4 VALUES ('C', 'V12769574', 'V12769574', 'Padre');
INSERT INTO Parentesco_Padre_4 VALUES ('A', 'V20145701', 'V20145701', 'Padre');
INSERT INTO Parentesco_Padre_4 VALUES ('B', 'V20145701', 'V20145701', 'Padre');
INSERT INTO Parentesco_Padre_4 VALUES ('A', 'E21879397', 'E21879397', 'Padre');
INSERT INTO Parentesco_Padre_4 VALUES ('B', 'E21879397', 'E21879397', 'Padre');
INSERT INTO Parentesco_Padre_4 VALUES ('A', 'V14910819', 'V14910819', 'Madre');
INSERT INTO Parentesco_Padre_4 VALUES ('B', 'V14910819', 'V14910819', 'Madre');
INSERT INTO Parentesco_Padre_4 VALUES ('A', 'V20775874', 'V20775874', 'Madre');
INSERT INTO Parentesco_Padre_4 VALUES ('B', 'V20775874', 'V20775874', 'Madre');
INSERT INTO Parentesco_Padre_4 VALUES ('C', 'V20775874', 'V20775874', 'Madre');
INSERT INTO Parentesco_Padre_4 VALUES ('A', 'V15748739', 'V15748739', 'Madre');
INSERT INTO Parentesco_Padre_4 VALUES ('B', 'V15748739', 'V15748739', 'Madre');
INSERT INTO Parentesco_Padre_4 VALUES ('A', 'E10917655', 'E10917655', 'Padre');
INSERT INTO Parentesco_Padre_4 VALUES ('B', 'E10917655', 'E10917655', 'Padre');
INSERT INTO Parentesco_Padre_4 VALUES ('C', 'E10917655', 'E10917655', 'Padre');

--Parentesco Niños
INSERT INTO Parentesco_nino_4 VALUES ('A', 'V8108418', 'B', 'V8108418', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'V8108418', 'A', 'V8108418', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'V9757397', 'B', 'V9757397', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'V9757397', 'C', 'V9757397', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'V9757397', 'A', 'V9757397', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'V9757397', 'C', 'V9757397', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('C', 'V9757397', 'A', 'V9757397', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('C', 'V9757397', 'B', 'V9757397', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'V24871662', 'B', 'V24871662', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'V24871662', 'C', 'V24871662', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'V24871662', 'A', 'V24871662', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'V24871662', 'C', 'V24871662', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('C', 'V24871662', 'A', 'V24871662', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('C', 'V24871662', 'B', 'V24871662', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'V12769574', 'B', 'V12769574', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'V12769574', 'C', 'V12769574', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'V12769574', 'A', 'V12769574', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'V12769574', 'C', 'V12769574', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('C', 'V12769574', 'A', 'V12769574', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('C', 'V12769574', 'B', 'V12769574', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'V20145701', 'B', 'V20145701', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'V20145701', 'A', 'V20145701', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'E21879397', 'B', 'E21879397', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'E21879397', 'A', 'E21879397', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'V14910819', 'B', 'V14910819', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'V14910819', 'A', 'V14910819', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'V20775874', 'B', 'V20775874', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'V20775874', 'C', 'V20775874', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'V20775874', 'A', 'V20775874', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'V20775874', 'C', 'V20775874', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('C', 'V20775874', 'A', 'V20775874', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('C', 'V20775874', 'B', 'V20775874', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'V15748739', 'B', 'V15748739', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'V15748739', 'A', 'V15748739', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'E10917655', 'B', 'E10917655', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('A', 'E10917655', 'C', 'E10917655', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'E10917655', 'A', 'E10917655', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('B', 'E10917655', 'C', 'E10917655', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('C', 'E10917655', 'A', 'E10917655', 'Hermano');
INSERT INTO Parentesco_nino_4 VALUES ('C', 'E10917655', 'B', 'E10917655', 'Hermano');

--Autorizado
insert into Autorizado_4 values ('V9187221', 'Jorge', 'Ramirez', 04166908170);
insert into Autorizado_4 values ('V9214662', 'Juan', 'Pollizschter', 04167198427);
insert into Autorizado_4 values ('V12018977', 'Alfredo', 'Martinez', 04249108761);
insert into Autorizado_4 values ('V4987907', 'Sergio', 'Reveron', 04168901245);
insert into Autorizado_4 values ('V25987122', 'Augusto', 'Marchisio', 04149871020);

--Autorizado Buscar
insert into autorizado_buscar_4 values ('V9187221', 'B', 'V9757397');
insert into autorizado_buscar_4 values ('V9214662', 'A', 'V20775874');
insert into autorizado_buscar_4 values ('V12018977', 'A', 'V12769574');
insert into autorizado_buscar_4 values ('V4987907', 'A', 'V15748739');
insert into autorizado_buscar_4 values ('V25987122', 'C', 'E10917655');

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

--Horario-actividad-guardería
insert into Horario_Act_Guarderia_4 values('J298754541-5', 1, '2018-03-04', '11:15', '12:45');
insert into Horario_Act_Guarderia_4 values('J764219187-2', 2, '2018-04-05', '09:00', '11:00');
insert into Horario_Act_Guarderia_4 values('J128487902-9', 3, '2018-04-04', '09:00', '10:40');
insert into Horario_Act_Guarderia_4 values('J516782778-4', 4, '2018-05-03', '10:00', '11:00');
insert into Horario_Act_Guarderia_4 values('J279154667-2', 5, '2018-04-14', '14:00', '16:00');

--Inscripción
INSERT INTO inscripcion_4 values (2013, 1, 'J298754541-5', 'V8108418', 'A', '2013-09-02', '07:00', '17:00');
INSERT INTO inscripcion_4 values (2014, 2, 'J298754541-5', 'V8108418', 'B', '2014-09-11', '07:00', '17:00');
INSERT INTO inscripcion_4 values (2015, 3, 'J764219187-2', 'V9757397', 'A', '2015-09-23', '08:00', '17:00');
INSERT INTO inscripcion_4 values (2015, 4, 'J764219187-2', 'V9757397', 'B', '2015-09-23', '08:00', '17:00');
INSERT INTO inscripcion_4 values (2015, 5, 'J764219187-2', 'V9757397', 'C', '2015-10-02', '08:00', '18:00');
INSERT INTO inscripcion_4 values (2014, 6, 'J516782778-4', 'V24871662', 'A', '2014-08-18', '08:00', '18:00');
INSERT INTO inscripcion_4 values (2015, 7, 'J516782778-4', 'V24871662', 'B', '2015-08-28', '08:00', '18:00');
INSERT INTO inscripcion_4 values (2018, 8, 'J516782778-4', 'V24871662', 'C', '2018-01-18', '08:00', '18:00');
INSERT INTO inscripcion_4 values (2013, 9, 'J128487902-9', 'V12769574', 'A', '2013-02-25', '07:00', '17:00');
INSERT INTO inscripcion_4 values (2014, 10, 'J128487902-9', 'V12769574', 'B', '2014-09-14', '07:00', '17:00');
INSERT INTO inscripcion_4 values (2014, 11, 'J128487902-9', 'V12769574', 'C', '2014-09-14', '07:00', '17:00');
INSERT INTO inscripcion_4 values (2016, 12, 'J764219187-2', 'V20145701', 'A', '2016-09-12', '08:00', '17:00');
INSERT INTO inscripcion_4 values (2016, 13, 'J764219187-2', 'V20145701', 'B', '2016-09-12', '08:00', '17:00');
INSERT INTO inscripcion_4 values (2016, 14, 'J516782778-4', 'E21879397', 'A', '2016-09-23', '08:00', '18:00');
INSERT INTO inscripcion_4 values (2018, 15, 'J516782778-4', 'E21879397', 'B', '2018-01-10', '08:00', '18:00');
INSERT INTO inscripcion_4 values (2013, 16, 'J128487902-9', 'V14910819', 'A', '2013-09-20', '07:00', '17:00');
INSERT INTO inscripcion_4 values (2013, 17, 'J128487902-9', 'V14910819', 'B', '2013-09-20', '07:00', '17:00');
INSERT INTO inscripcion_4 values (2013, 18, 'J298754541-5', 'V20775874', 'A', '2013-09-12', '07:00', '17:00');
INSERT INTO inscripcion_4 values (2013, 19, 'J298754541-5', 'V20775874', 'B', '2013-09-12', '07:00', '17:00');
INSERT INTO inscripcion_4 values (2016, 20, 'J298754541-5', 'V20775874', 'C', '2016-09-25', '07:00', '17:00');
INSERT INTO inscripcion_4 values (2013, 21, 'J279154667-2', 'V15748739', 'A', '2013-01-10', '08:00', '17:00');
INSERT INTO inscripcion_4 values (2015, 22, 'J279154667-2', 'V15748739', 'B', '2015-01-20', '08:00', '17:00');
INSERT INTO inscripcion_4 values (2015, 23, 'J279154667-2', 'E10917655', 'A', '2015-01-13', '08:00', '17:00');
INSERT INTO inscripcion_4 values (2016, 24, 'J279154667-2', 'E10917655', 'B', '2016-01-06', '08:00', '17:00');
INSERT INTO inscripcion_4 values (2017, 25, 'J279154667-2', 'E10917655', 'C', '2017-01-12', '08:00', '17:00');

--Asistencia
INSERT INTO asistencia_4 values ('2016-01-07', 24, 2016, 'E10917655', 'B', NULL, 'V9187221', '08:15', '16:50', 'SI');
INSERT INTO asistencia_4 values ('2016-09-27', 20, 2016, 'V20775874', 'C', NULL, 'V9214662', '08:05', '16:55', 'NO');
INSERT INTO asistencia_4 values ('2013-10-03', 1, 2013, 'V8108418', 'A', 'V8108418', NULL, '07:05', '16:55', 'NO');
INSERT INTO asistencia_4 values ('2014-10-25', 2, 2014, 'V8108418', 'B', NULL, 'V12018977', '07:25', '16:45', 'SI');
INSERT INTO asistencia_4 values ('2015-11-03', 5, 2015, 'V9757397', 'C', 'V9757397', NULL, '08:05', '17:55', 'NO');

--Pediatras
insert into Pediatra_4 values (1 , 'Jose Feliciano' , 04123345621, 02121298331);
insert into Pediatra_4 values (2 , 'Diego Garcia' , 04241948631, 02125359871);
insert into Pediatra_4 values (3 , 'Mariana Mendez' , 04167821231,  02128721345);
insert into Pediatra_4 values (4 , 'Javier Clemente' , 04143561891, 02123457896);
insert into Pediatra_4 values (5 , 'Daniela Bustamante' , 04126521487, 02123567823);

--Atención
insert into Atencion_4 values (1, 'A', 'V8108418');
insert into Atencion_4 values (1, 'B', 'V8108418');
insert into Atencion_4 values (1, 'A', 'V9757397');
insert into Atencion_4 values (1, 'B', 'V9757397');
insert into Atencion_4 values (1, 'C', 'V9757397');
insert into Atencion_4 values (2, 'A', 'V24871662');
insert into Atencion_4 values (2, 'B', 'V24871662');
insert into Atencion_4 values (2, 'C', 'V24871662');
insert into Atencion_4 values (2, 'A', 'V12769574');
insert into Atencion_4 values (2, 'B', 'V12769574');
insert into Atencion_4 values (2, 'C', 'V12769574');
insert into Atencion_4 values (3, 'A', 'V20145701');
insert into Atencion_4 values (3, 'B', 'V20145701');
insert into Atencion_4 values (3, 'A', 'E21879397');
insert into Atencion_4 values (3, 'B', 'E21879397');
insert into Atencion_4 values (4, 'A', 'V14910819');
insert into Atencion_4 values (4, 'B', 'V14910819');
insert into Atencion_4 values (4, 'A', 'V20775874');
insert into Atencion_4 values (4, 'B', 'V20775874');
insert into Atencion_4 values (4, 'C', 'V20775874');
insert into Atencion_4 values (5, 'A', 'V15748739');
insert into Atencion_4 values (5, 'B', 'V15748739');
insert into Atencion_4 values (5, 'A', 'E10917655');
insert into Atencion_4 values (5, 'B', 'E10917655');
insert into Atencion_4 values (5, 'C', 'E10917655');

--Juego
insert into Juego_4 values (1 , 'Lego');
insert into Juego_4 values (2 , 'Lanzar Pelota');
insert into Juego_4 values (3 , 'Patear Pelota');
insert into Juego_4 values (4 , 'Tacos');
insert into Juego_4 values (5 , 'Triciclo');

--Gusto
insert into Gusto_4 values (1, 'A', 'V8108418');
insert into Gusto_4 values (1, 'B', 'V8108418');
insert into Gusto_4 values (1, 'A', 'V9757397');
insert into Gusto_4 values (1, 'B', 'V9757397');
insert into Gusto_4 values (1, 'C', 'V9757397');
insert into Gusto_4 values (2, 'A', 'V24871662');
insert into Gusto_4 values (2, 'B', 'V24871662');
insert into Gusto_4 values (2, 'C', 'V24871662');
insert into Gusto_4 values (2, 'A', 'V12769574');
insert into Gusto_4 values (2, 'B', 'V12769574');
insert into Gusto_4 values (2, 'C', 'V12769574');
insert into Gusto_4 values (3, 'A', 'V20145701');
insert into Gusto_4 values (3, 'B', 'V20145701');
insert into Gusto_4 values (3, 'A', 'E21879397');
insert into Gusto_4 values (3, 'B', 'E21879397');
insert into Gusto_4 values (4, 'A', 'V14910819');
insert into Gusto_4 values (4, 'B', 'V14910819');
insert into Gusto_4 values (4, 'A', 'V20775874');
insert into Gusto_4 values (4, 'B', 'V20775874');
insert into Gusto_4 values (4, 'C', 'V20775874');
insert into Gusto_4 values (5, 'A', 'V15748739');
insert into Gusto_4 values (5, 'B', 'V15748739');
insert into Gusto_4 values (5, 'A', 'E10917655');
insert into Gusto_4 values (5, 'B', 'E10917655');
insert into Gusto_4 values (5, 'C', 'E10917655');

--Sintoma
insert into Sintoma_4 values (1 , 'Tos');
insert into Sintoma_4 values (2 , 'Dolor de Barriga');
insert into Sintoma_4 values (3 , 'Fiebre');
insert into Sintoma_4 values (4 , 'Dolor de Garganta');
insert into Sintoma_4 values (5 , 'Mocos');

--Medicamento
insert into Medicamento_4 values (1 , 'Atamel');
insert into Medicamento_4 values (2 , 'Ibuprofeno');
insert into Medicamento_4 values (3 , 'Jarabe para la Tos');
insert into Medicamento_4 values (4 , 'Antialérgico');
insert into Medicamento_4 values (5 , 'Manzanilla');

-- Tratamiento
insert into Tratamiento_4 values (1, 3, 'A', 'V8108418', '100 ml');
insert into Tratamiento_4 values (5, 4, 'B', 'V8108418', '10 mg');
insert into Tratamiento_4 values (1, 3, 'A', 'V9757397', '100 ml');
insert into Tratamiento_4 values (2, 5, 'A', 'V9757397', '300 ml');
insert into Tratamiento_4 values (4, 2, 'B', 'V9757397', '20 mg');
insert into Tratamiento_4 values (4, 5, 'B', 'V14910819', '350 ml');
insert into Tratamiento_4 values (3, 1, 'C', 'E10917655', '25 mg');

--Alergia
insert into Alergia_4 values (1 , 'Maní');
insert into Alergia_4 values (2 , 'Camarones');
insert into Alergia_4 values (3 , 'Intolerante a la Lactosa');
insert into Alergia_4 values (4 , 'Acaros');
insert into Alergia_4 values (5 , 'Gluten');

-- Padecimiento Alergia
insert into Padecimiento_alergia_4 values (1, 'B', 'V20775874');
insert into Padecimiento_alergia_4 values (3, 'A', 'V24871662');
insert into Padecimiento_alergia_4 values (5, 'A', 'V14910819');
insert into Padecimiento_alergia_4 values (2, 'B', 'E10917655');
insert into Padecimiento_alergia_4 values (4, 'C', 'V12769574');

--Enfermedad
insert into Enfermedad_4 values (1 , 'Lechina');
insert into Enfermedad_4 values (2 , 'Paperas');
insert into Enfermedad_4 values (3 , 'Asma');
insert into Enfermedad_4 values (4 , 'Sarampión');
insert into Enfermedad_4 values (5 , 'Otitis');

--Padecimiento Enfermedad
insert into Padecimiento_enfermedad_4 values (1, 'B', 'V12769574', '2013-12-04');
insert into Padecimiento_enfermedad_4 values (3, 'A', 'V15748739', '2014-04-15');
insert into Padecimiento_enfermedad_4 values (2, 'A', 'E21879397', '2016-11-01');
insert into Padecimiento_enfermedad_4 values (4, 'A', 'V15748739', '2014-04-15');
insert into Padecimiento_enfermedad_4 values (5, 'A', 'V9757397', '2017-08-11');

--Comida
insert into comida_4 values(1, 'Proteina', 'Carne');
insert into comida_4 values(2, 'Carbohidrato', 'Papas');
insert into comida_4 values(3, 'Ensalada', 'Ensalada Cesar');
insert into comida_4 values(4, 'Jugo natural', 'Juego de lechosa');
insert into comida_4 values(5, 'Jugo natural', 'Juego de fresa');

--Platos
insert into plato_4 values(1, 'Plato del lunes');
insert into plato_4 values(2, 'Plato del martes');
insert into plato_4 values(3, 'Plato del miercoles');
insert into plato_4 values(4, 'Plato del jueves');
insert into plato_4 values(5, 'Plato del viernes');

--Comida-plato
insert into comida_plato_4 values(1, 1);
insert into comida_plato_4 values(1, 2);
insert into comida_plato_4 values(1, 3);
insert into comida_plato_4 values(1, 4);
insert into comida_plato_4 values(2, 1);
insert into comida_plato_4 values(2, 2);
insert into comida_plato_4 values(2, 3);
insert into comida_plato_4 values(3, 5);
insert into comida_plato_4 values(3, 1);
insert into comida_plato_4 values(3, 2);
insert into comida_plato_4 values(3, 3);
insert into comida_plato_4 values(3, 5);
insert into comida_plato_4 values(4, 1);
insert into comida_plato_4 values(4, 2);
insert into comida_plato_4 values(4, 3);
insert into comida_plato_4 values(4, 4);
insert into comida_plato_4 values(5, 1);
insert into comida_plato_4 values(5, 2);
insert into comida_plato_4 values(5, 3);
insert into comida_plato_4 values(5, 4);

  --Menu
insert into menu_4 values (1, '2018-03-05', 'J298754541-5', 200);
insert into menu_4 values (2, '2018-03-05', 'J764219187-2', 200);
insert into menu_4 values (3, '2018-03-05', 'J128487902-9', 200);
insert into menu_4 values (4, '2018-03-05', 'J516782778-4', 200);
insert into menu_4 values (5, '2018-03-05', 'J279154667-2', 200);

--Factura-menu
insert into factura_menu_4 values('2018-03-04', 2, 2014, 'V8108418', 'B', 1, '2018-03-05', '2558976495', 'Mercantil');
insert into factura_menu_4 values('2018-03-04', 5, 2015, 'V9757397', 'C', 2, '2018-03-05', '2550176251', 'Mercantil');
insert into factura_menu_4 values('2018-03-04', 11, 2014, 'V12769574', 'C', 3, '2018-03-05', '1987654102', 'Banesco');
insert into factura_menu_4 values('2018-03-04', 14, 2016, 'E21879397', 'A', 4, '2018-03-05', '2551298578', 'Mercantil');
insert into factura_menu_4 values('2018-03-04', 22, 2015, 'V15748739', 'B', 5, '2018-03-05', '9870154178', 'BOD');

--Menu semanal
insert into menu_semanal_4 values(1, '2018-03-05', 1);
insert into menu_semanal_4 values(2, '2018-03-05', 3);
insert into menu_semanal_4 values(3, '2018-03-05', 4);
insert into menu_semanal_4 values(4, '2018-03-05', 5);
insert into menu_semanal_4 values(5, '2018-03-05', 2);
