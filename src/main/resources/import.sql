
/* Populate table socios*/

INSERT INTO LOCALIDADES ( nombre, cp ) VALUES ('Ushuaia', 9410);
INSERT INTO LOCALIDADES ( nombre, cp ) VALUES ('Tolhuin', 9420);
INSERT INTO LOCALIDADES ( nombre, cp ) VALUES ('Rio grande', 9420);
INSERT INTO TIPOS_DOCUMENTOS ( nombre ) VALUES ('DNI');
INSERT INTO TIPOS_DOCUMENTOS ( nombre ) VALUES ('Pasaporte');
INSERT INTO CATEGORIAS ( nombre ) VALUES ('Mutual');
INSERT INTO CATEGORIAS ( nombre ) VALUES ('Alimentos');
INSERT INTO CATEGORIAS ( nombre ) VALUES ('Combustible');
INSERT INTO CATEGORIAS ( nombre ) VALUES ('Medicamentos');
INSERT INTO ESTADOS_CIVILES ( nombre ) VALUES ('Soltero');
INSERT INTO ESTADOS_CIVILES ( nombre ) VALUES ('Casado');
INSERT INTO ESTADOS_CIVILES ( nombre ) VALUES ('Divorciado');
INSERT INTO ESTADOS_CIVILES ( nombre ) VALUES ('Viudo');
INSERT INTO VARIABLES ( nombre, valor ) VALUES ('Cuota', 880.00);
INSERT INTO VARIABLES ( nombre, valor ) VALUES ('Comisión', 3.00);
INSERT INTO VARIABLES ( nombre, valor ) VALUES ('Valor item', 1500.00);
INSERT INTO VARIABLES ( nombre, valor ) VALUES ('Fecha de cierre', 25.00);
INSERT INTO TIPOS ( nombre ) VALUES ('Activo');
INSERT INTO TIPOS ( nombre ) VALUES ('Adherente');



INSERT INTO ROLES (nombre_rol) VALUES ('admin');
INSERT INTO ROLES (nombre_rol) VALUES ('usuario');
INSERT INTO ROLES (nombre_rol) VALUES ('socio');
INSERT INTO ROLES (nombre_rol) VALUES ('comercio');
INSERT INTO USUARIOS (nombre_usuario, baja, fecha_alta, contrasena, rol_id ) VALUES ('mfuhr', FALSE, '2020-11-12', '$2a$10$987EdWf2BbDn7Lw45EZJ2OX53ge15OVKK7DtoialpsBWmsFVcVXEa', 1);
INSERT INTO USUARIOS (nombre_usuario, baja, fecha_alta, contrasena, rol_id ) VALUES ('mariano', FALSE, '2020-11-12', 'fuhr', 3);
INSERT INTO SOCIOS (nombre, apellido, cuil, direccion, extranjero, fecha_ingreso_laboral, fecha_nacimiento, fecha_alta, legajo, correo, num_cuenta, num_doc, baja, habilitado, telefono, usuario_id, localidad_id, tipo_documento_id, estado_civil_id, tipo_id, variable_id ) VALUES ('mariano', 'fuhr', 13234567,'direccion 123', FALSE,'2020/12/25','1976/12/25', '2021/01/25', 1234, 'mfuhr@mail.com', 123456, 35356600, FALSE, TRUE, 2901555555, 2, 1, 1, 1, 1, 1);
INSERT INTO USUARIOS (nombre_usuario, baja, fecha_alta, contrasena, rol_id ) VALUES ('pperez', FALSE, '2020-11-12', 'perez', 3);
INSERT INTO SOCIOS (nombre, apellido, cuil, direccion, extranjero, fecha_ingreso_laboral, fecha_nacimiento, fecha_alta, legajo, correo, num_cuenta, num_doc, baja, habilitado, telefono, usuario_id, localidad_id, tipo_documento_id, estado_civil_id, tipo_id, variable_id ) VALUES ('pedro', 'perez', 13234567,'direccion 123', FALSE,'2020/12/25','1976/12/25', '2021/01/25', 1234, 'pperez@mail.com', 123456, 32321321, FALSE, TRUE, 2901555555, 3, 1, 1, 1, 1, 1);
INSERT INTO CONVENIOS ( nombre, contacto, direccion, telefono, cuit, baja, correo, fecha_alta, localidad_id, categoria_id ) VALUES ('AMPS', 'Alejandro Barrionuevo', 'Gob. Paz', 2901422354, 1234567890, FALSE, 'amps@mail.com', '2021/01/01', 1, 1);
-- INSERT INTO USUARIOS (nombre_usuario, contrasena, rol_id ) VALUES ('gualdesi@mail.com', '$2a$10$njX2.WvIWM/TpsFPVGLKR.0EewoR1kZ5inJD4KM/mRYguIJCGYFXG', 4);
-- INSERT INTO CONVENIOS (nombre, direccion , contacto, correo, telefono, baja, cuit, fecha_alta, localidad_id, categoria_id, usuario_id ) VALUES ('Gualdesi', 'Gob. Campos', 'Ramon Perez', 'gualdesi@mail.com', 3232323 , FALSE, 4214231254,'2020/10/21', 1, 2, 4 );
-- INSERT INTO DESCUENTOS (descripcion, fecha_alta, socio_id, convenio_id, num_cuotas, valor_cuota, valor_sub_total, valor_total) VALUES ('alim','2021/02/01',2, 1, 1,1271.02, 1234, 1271.02);
-- INSERT INTO CUOTAS (num_cuota, fecha, valor, descuento_id) VALUES ( 1,'2021/03/25' ,1271.02, 1);
-- INSERT INTO ITEMS (valor, descuento_id) VALUES (1271.02, 1);
