
/* Populate table socios*/

INSERT INTO LOCALIDADES ( nombre, cp ) VALUES ('Ushuaia', 9410);
INSERT INTO LOCALIDADES ( nombre, cp ) VALUES ('Tolhuin', 9420);
INSERT INTO LOCALIDADES ( nombre, cp ) VALUES ('Rio grande', 9420);
INSERT INTO TIPOS_DOCUMENTOS ( nombre ) VALUES ('DNI');
INSERT INTO TIPOS_DOCUMENTOS ( nombre ) VALUES ('Pasaporte');
INSERT INTO CATEGORIAS ( nombre ) VALUES ('Activo');
INSERT INTO CATEGORIAS ( nombre ) VALUES ('Adherente');
INSERT INTO ESTADOS_CIVILES ( nombre ) VALUES ('Soltero');
INSERT INTO ESTADOS_CIVILES ( nombre ) VALUES ('Casado');
INSERT INTO ESTADOS_CIVILES ( nombre ) VALUES ('Divorciado');
INSERT INTO ESTADOS_CIVILES ( nombre ) VALUES ('Viudo');

INSERT INTO AUTHORITIES (authority) VALUES ('admin');
INSERT INTO AUTHORITIES (authority) VALUES ('socio');
INSERT INTO USUARIOS (nombre_usuario, contraseña, authority_id ) VALUES ('mariano', 'fuhr', 1);
INSERT INTO SOCIOS (nombre, apellido, extranjero, baja, num_doc, correo, telefono, usuario_id, localidad_id, fecha_alta, tipo_documento_id, estado_civil_id, categoria_id ) VALUES ('mariano', 'fuhr', FALSE, TRUE, '35356600', 'mfuhr@gmail.com', '290123234', 1, 1, '2021/01/08', 1, 1,1);