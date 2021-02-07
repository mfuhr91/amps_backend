
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
INSERT INTO VARIABLES ( nombre, valor ) VALUES ('Comision', 3.00);
INSERT INTO VARIABLES ( nombre, valor ) VALUES ('Valor item', 1500.00);
INSERT INTO VARIABLES ( nombre, valor ) VALUES ('Fecha de cierre', 25.00);
INSERT INTO TIPOS ( nombre ) VALUES ('Activo');
INSERT INTO TIPOS ( nombre ) VALUES ('Adherente');



INSERT INTO AUTHORITIES (authority) VALUES ('admin');
INSERT INTO AUTHORITIES (authority) VALUES ('usuario');
INSERT INTO AUTHORITIES (authority) VALUES ('socio');
INSERT INTO AUTHORITIES (authority) VALUES ('comercio');
INSERT INTO USUARIOS (nombre_usuario, contrasena, authority_id ) VALUES ('mfuhr', '030512', 1);
INSERT INTO USUARIOS (nombre_usuario, contrasena, authority_id ) VALUES ('mariano', 'fuhr', 2);
INSERT INTO SOCIOS (nombre, apellido, legajo, extranjero, num_cuenta, baja, num_doc, correo, telefono, usuario_id, localidad_id, fecha_alta, tipo_documento_id, estado_civil_id, tipo_id, variable_id ) VALUES ('mariano', 'fuhr', 33245, FALSE, 323123231, FALSE, '35356600', 'mfuhr@gmail.com', '290123234', 2, 1, '2020/12/08', 1, 1,1, 1);
INSERT INTO USUARIOS (nombre_usuario, contrasena, authority_id ) VALUES ('plopez', 'lopez', 2);
INSERT INTO SOCIOS (nombre, apellido, legajo, extranjero, num_cuenta, baja, num_doc, correo, telefono, usuario_id, localidad_id, fecha_alta, tipo_documento_id, estado_civil_id, tipo_id, variable_id ) VALUES ('pedro', 'perez', 132345, FALSE, 321435432321, FALSE, '21345600', 'mfuhr@gmail.com', '290123234', 3, 1, '2020/12/25', 1, 1,1, 1);
INSERT INTO CONVENIOS ( nombre, direccion , contacto, baja, localidad_id, fecha_alta, categoria_id ) VALUES ('AMPS', 'Gob. Paz', 'Alejandro Barrionuevo', FALSE, 1, '2021/01/01',1);
INSERT INTO USUARIOS (nombre_usuario, contrasena, authority_id ) VALUES ('gualdesi@mail.com', 'gualdesi', 4);
INSERT INTO CONVENIOS (nombre, direccion , contacto, correo, telefono, baja, cuit, fecha_alta, localidad_id, categoria_id, usuario_id ) VALUES ('Gualdesi', 'Gob. Campos', 'Ramon Perez', 'gualdesi@mail.com', 3232323 , FALSE, 4214231254,'2020/10/21', 1, 2, 4 );
INSERT INTO DESCUENTOS (socio_id, convenio_id, num_cuotas, valor_total) VALUES (2, 1, 1, 1234);
INSERT INTO CUOTAS (num_cuota, fecha_cuota, monto_cuota, descuento_id) VALUES ( 1,'2021/03/25' ,1234, 1);
INSERT INTO ITEMS (valor_sub_total, valor_total, descuento_id) VALUES (1234, 1271.02, 1);
