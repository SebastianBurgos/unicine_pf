/*
 (cedula, contrasenia, direccion, edad, email, estado, imagenPerfil, nombreCompleto)
 */
insert into cliente values ("1", "pepe1", "direccion de mi casa", 20, "juanp.loaizan@uqvirtual.edu.co", "PRUEBA_0", "imagen-url", "Juan Pablo Loaiza");
insert into cliente values ("2", "pepe1", "direccion de mi casa", 20, "juanpedde@gnlkk.com", "PRUEBA", "imagen-url", "Alfonso");
insert into cliente values ("3", "pepe1", "direccion de mi casa", 20, "juanpepec@gnlkk.com", "PRUEBA_0", "imagen-url", "Rodrigo");
insert into cliente values ("4", "pepe1", "direccion de mi casa", 20, "juanpepsdcsdc@gnlkk.com", "PRUEBA", "imagen-url", "Sigifredo");
insert into cliente values ("5", "pepe1", "direccion de mi casa", 20, "juanpcdepe@gnlkk.com", "PRUEBA_0", "imagen-url", "Roberto");

/*
 (cedulaCliente, telefono)
 */
insert into cliente_telefonos values ("1", "3127584888");
insert into cliente_telefonos values ("1", "3127583384");
insert into cliente_telefonos values ("2", "3127584845");
insert into cliente_telefonos values ("3", "3127585858");
insert into cliente_telefonos values ("3", "3127586425");


/*
 (codigo, dia, fechaFin, fechaInicio, hora)
 */
insert into horario values (1, "LMXJV", "2022-10-02", "2022-09-20", "16:00");
insert into horario values (2, "JVSDL", "2022-10-29", "2022-10-15", "14:00");
insert into horario values (3, "LMXJVS", "2022-10-30", "2022-10-16", "20:00");
insert into horario values (4, "LMXJV", "2022-11-03", "2022-10-20", "16:00");
insert into horario values (5, "LMXJV", "2022-11-20", "2022-10-30", "16:00");

/*
 (codigo, nombre, precioAdicional )
 */
insert into tipo_sala values (1, "Estandar", 0);
insert into tipo_sala values (2, "VIP", 5500);
insert into tipo_sala values (3, "4D", 9600);

/*
 (codigo, columnas, esquemaDeSillas, filas, totalSillas)
 */
insert into distribucion_sillas values(1, 11,
   "xxxxxxxxxxx
    xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx", 10, 110);
insert into distribucion_sillas values(2, 11,
"xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx", 10, 110);
insert into distribucion_sillas values(3, 11,
"xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx", 10, 110);
insert into distribucion_sillas values(4, 17, "xxxxx x x x   x x xxxxxx", 7, 190);
insert into distribucion_sillas values(5, 11,
"xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx
xxxxxxxxxxx", 10, 110);

/*
 (codigo, nombre)
 */
insert into ciudad values (1, "Armenia");
insert into ciudad values (2, "Pereira");
insert into ciudad values (3, "Manizales");
insert into ciudad values (4, "Bogotá");
insert into ciudad values (5, "Cali");

/*
 (cedula, contrasenia, email, imagenPerfil, nombreCompleto, telefono)
 */
insert into administrador_teatro values ("1000", "1234", "jloasiisi@email.com", "urlImagen", "Jose Alonso Perez", "3127588409");
insert into administrador_teatro values ("2000", "1234", "jloasi22i@email.com", "urlImagen", "Pedro Antonio Marín", "3125506568");
insert into administrador_teatro values ("3000", "1234", "jloasiddi@email.com", "urlImagen", "María Teresa Nieto", "3124545840");
insert into administrador_teatro values ("4000", "1234", "jloasi45i@email.com", "urlImagen", "John Freddy Gomez", "3127495409");
insert into administrador_teatro values ("5000", "1234", "jloasitsi@email.com", "urlImagen", "Alfredo Perez", "3127448409");

/*
 (codigo, direccion, nombre, cedulaAdministrador, codigoCiudad)
 */
insert into teatro values (1, "Barrio la aguapanela calle 2", "C.C Armenia", "1000", 1);
insert into teatro values (2, "Direccion equis de Pereira", "C.C Pereira", "5000", 2);
insert into teatro values (3, "Otra direccion, pero de Manizales", "C.C Unicentro", "2000", 3);
insert into teatro values (4, "Avenida Centenario, cerca al round point", "C.C Calima", "3000", 4);
insert into teatro values (5, "Por la 14, con KFC se reconoce de una", "C.C Portal del Quindio", "4000", 1);

/*
 (codigo, numero, distribucionSillas, teatroCodigo, tipoSalaCodigo)
 */
insert into sala values (1, 1, 1, 4, 1);
insert into sala values (2, 2, 1, 4, 1);
insert into sala values (3, 1, 4, 2, 2);
insert into sala values (4, 1, 5, 5, 1);
insert into sala values (5, 2, 1, 5, 2);
insert into sala values (6, 3, 1, 5, 1);
insert into sala values (7, 4, 1, 5, 3);
insert into sala values (8, 1, 1, 1, 1);

/*
 (codigo, duracionMinutos, edadApropiada, estado, imagen, nombre, nombreDirector, nombreEstudio, sinopsis, trailer)
 */
insert into pelicula values (1, 120, 7, "EN_CARTELERA", "urlImagen", "Las Aventuras del Pescado",
                             "Antonio Director", "Universal", "sinopsis larga", "urlTrailer1");
insert into pelicula values (2, 152, 14, "EN_CARTELERA", "urlImagen", "Un aguacate en el desierto",
                             "Unos directores", "Marvel", "sinopsis larga", "urlTrailer2");
insert into pelicula values (3, 131, 14, "PROXIMAMENTE", "urlImagen", "Mandingo",
                             "Otros directores", "21th Fox", "sinopsis larga", "urlTrailer3");
insert into pelicula values (4, 110, 7, "FINALIZADA", "urlImagen", "Habia una vez una pelicula",
                             "Mi amigo el fotografo", "Disney", "sinopsis larga", "urlTrailer4");
insert into pelicula values (5, 98, 18, "EN_CARTELERA", "urlImagen", "Terror en la ciudad de Armenia",
                             "Pepe Aguilar", "Paramount", "sinopsis larga", "urlTrailer5");

/*
 (codigoPelicula, genero)
 */
insert into pelicula_generos values (1, "DRAMA");
insert into pelicula_generos values (1, "CIENCIA_FICCION");
insert into pelicula_generos values (1, "ACCION");
insert into pelicula_generos values (2, "FANTASIA");
insert into pelicula_generos values (2, "FANTASIA");
insert into pelicula_generos values (3, "DOCUMENTAL");
insert into pelicula_generos values (4, "AVENTURA");
insert into pelicula_generos values (4, "ACCION");
insert into pelicula_generos values (5, "TERROR");

/*
 (codigo, precioFuncion, codigoHorario, codigoPelicula, codigoSala)
 */
insert into funcion values (1, 7500, 1, 4, 1);
insert into funcion values (2, 8000, 3, 3, 3);
insert into funcion values (3, 7500, 2, 1, 4);
insert into funcion values (4, 8000, 5, 2, 2);
insert into funcion values (5, 7500, 5, 5, 4);
insert into funcion values (6, 7500, 4, 5, 5);
insert into funcion values (7, 7500, 3, 5, 7);
insert into funcion values (8, 7500, 1, 5, 6);

/*
 (codigo, estado, fechaCompra, medioPago, PrecioTotal, clienteCedula, funcionCodigo)
 */
insert into compra values (1, 5, "2022-10-15", "MASTERCARD", 25600, "1", 1);
insert into compra values (2, 5, "2022-10-18", "MASTERCARD", 82600, "1", 2);
insert into compra values (3, 5, "2022-10-19", "MASTERCARD", 29600, "3", 3);
insert into compra values (4, 5, "2022-10-26", "MASTERCARD", 118400, "4", 4);
insert into compra values (5, 5, "2022-10-30", "MASTERCARD", 35600, "1", 5);
insert into compra values (6, 1, null, null, null, "1", 5);
insert into compra values (7, 2, null, null, null, "1", 5);
insert into compra values (8, 3, null, null, null, "1", 5);
insert into compra values (9, 4, null, "NEQUI", null, "1", 5);

/*
 (codigo, concepto, descuento)
 */
insert into cupon values (1, "Descuento de registro", 15);
insert into cupon values (2, "Descuento por primera compra", 10);
insert into cupon values (3, "Descuento de cumpleaños", 20);
insert into cupon values (4, "Descuento de VIP", 10);

/*
 (codigo, estado, fechaVencimiento, clienteCedula, compraCodigo, cuponCodigo)
 */
insert into cliente_cupon values (1, "ACTIVO", "2022-10-31", "1", 9, 1);
insert into cliente_cupon values (2, "REDIMIDO", "2022-10-31", "2", null, 1);
insert into cliente_cupon values (3, "ACTIVO", "2022-11-06", "1", null, 2);
insert into cliente_cupon values (4, "VENCIDO", "2022-11-09", "3", null, 3);
insert into cliente_cupon values (5, "ACTIVO", "2022-11-15", "4", null, 3);

/*
 (codigo, columna, fila, precio, compraCodigo)
 */
insert into entrada values (1, 11, 'A', 7800, 7); --Estado entradas
insert into entrada values (2, 10, 'A', 7800, 7);

insert into entrada values (9, 11, 'D', 7800, 8); -- Estado confiteria
insert into entrada values (10, 11, 'D', 7800, 8);

insert into entrada values (11, 11, 'D', 7800, 9); -- Estado confiteria
insert into entrada values (12, 11, 'D', 7800, 9);

insert into entrada values (3, 8, 'F', 7800, 2);
insert into entrada values (4, 10, 'G', 7800, 3);
insert into entrada values (5, 5, 'J', 7800, 5);
insert into entrada values (6, 6, 'J', 7800, 4);
insert into entrada values (7, 9, 'B', 6000, 1);
insert into entrada values (8, 10, 'B', 6000, 1);

/*
 (codigo, extras, imagenProducto, nombre, precio)
 */

insert into producto_confiteria values (1, null, "url-imagen-producto", "Combo 1", 22000);
insert into producto_confiteria values (2, null, "url-imagen-producto", "Combo 2", 18000);
insert into producto_confiteria values (3, null, "url-imagen-producto", "Combo 3", 35600);
insert into producto_confiteria values (4, null, "url-imagen-producto", "Combo 4", 12000);
insert into producto_confiteria values (5, null, "url-imagen-producto", "Combo 5", 26800);

/*
 (codigo, precio, unidades, compraCodigo, productoConfiteriaCodigo)
 */
insert into compra_confiteria values (1, 24000, 2, 8, 4);
insert into compra_confiteria values (2, 22000, 1, 8, 1);

insert into compra_confiteria values (6, 24000, 2, 9, 4);
insert into compra_confiteria values (7, 22000, 1, 9, 1);

insert into compra_confiteria values (3, 54000, 3, 3, 2);
insert into compra_confiteria values (4, 24000, 2, 1, 4);
insert into compra_confiteria values (5, 35600, 1, 5, 3);

insert into pqrs values (1, "2022-10-15", "Aquí va el mensaje 1", "Motivo de la solicitud 1", "1");
insert into pqrs values (2, "2022-10-15", "Aquí va el mensaje 2", "Motivo de la solicitud 2", "1");
insert into pqrs values (3, "2022-10-15", "Aquí va el mensaje 3", "Motivo de la solicitud 3", "1");
insert into pqrs values (4, "2022-10-15", "Aquí va el mensaje 4", "Motivo de la solicitud 4", "1");
insert into pqrs values (5, "2022-10-15", "Aquí va el mensaje 5", "Motivo de la solicitud 5", "1");