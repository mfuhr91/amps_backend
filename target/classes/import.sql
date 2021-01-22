
/* Populate table socios*/

INSERT INTO LOCALIDADES ( nombre, cp ) VALUES ('Ushuaia', 9410);
INSERT INTO LOCALIDADES ( nombre, cp ) VALUES ('Tolhuin', 9420);
INSERT INTO LOCALIDADES ( nombre, cp ) VALUES ('Rio grande', 9420);
INSERT INTO TIPOS_DOCUMENTOS ( nombre ) VALUES ('DNI');
INSERT INTO TIPOS_DOCUMENTOS ( nombre ) VALUES ('Pasaporte');
INSERT INTO CATEGORIAS ( nombre ) VALUES ('Alimentos');
INSERT INTO CATEGORIAS ( nombre ) VALUES ('Combustible');
INSERT INTO CATEGORIAS ( nombre ) VALUES ('Medicamentos');
INSERT INTO ESTADOS_CIVILES ( nombre ) VALUES ('Soltero');
INSERT INTO ESTADOS_CIVILES ( nombre ) VALUES ('Casado');
INSERT INTO ESTADOS_CIVILES ( nombre ) VALUES ('Divorciado');
INSERT INTO ESTADOS_CIVILES ( nombre ) VALUES ('Viudo');
INSERT INTO VARIABLES ( nombre, valor ) VALUES ('cuota', 880.00);
INSERT INTO VARIABLES ( nombre, valor ) VALUES ('comision', 3.00);
INSERT INTO TIPOS ( nombre ) VALUES ('Activo');
INSERT INTO TIPOS ( nombre ) VALUES ('Adherente');



INSERT INTO AUTHORITIES (authority) VALUES ('admin');
INSERT INTO AUTHORITIES (authority) VALUES ('usuario');
INSERT INTO AUTHORITIES (authority) VALUES ('socio');
INSERT INTO AUTHORITIES (authority) VALUES ('comercio');
-- INSERT INTO USUARIOS (nombre_usuario, contraseña, authority_id ) VALUES ('mariano', 'fuhr', 2);
-- INSERT INTO SOCIOS (nombre, apellido, extranjero, baja, num_doc, correo, telefono, usuario_id, localidad_id, fecha_alta, tipo_documento_id, estado_civil_id, tipo_id, variable_id ) VALUES ('mariano', 'fuhr', FALSE, TRUE, '35356600', 'mfuhr@gmail.com', '290123234', 1, 1, '2021/01/08', 1, 1,1, 1);
-- INSERT INTO USUARIOS (nombre_usuario, contraseña, authority_id ) VALUES ('mariano', 'fuhr', 2);
-- INSERT INTO SOCIOS (nombre, apellido, extranjero, baja, num_doc, correo, telefono, usuario_id, localidad_id, fecha_alta, tipo_documento_id, estado_civil_id, tipo_id, variable_id ) VALUES ('mariano', 'fuhr', FALSE, TRUE, '35356600', 'mfuhr@gmail.com', '290123234', 1, 1, '2021/01/08', 1, 1,1, 1);
-- INSERT INTO USUARIOS (nombre_usuario, contraseña, authority_id ) VALUES ('gualdesi@mail.com', 'gualdesi', 4);
-- INSERT INTO CONVENIOS (nombre, direccion , contacto, correo, telefono, baja, cuit, fecha_alta, localidad_id, categoria_id, usuario_id ) VALUES ('Gualdesi', 'Gob. Campos', 'Ramon Perez', 'gualdesi@mail.com', 3232323 , FALSE, 4214231254,'2020/10/21', 1, 1, 2 );
