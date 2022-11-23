/*
 (cedula, contrasenia, direccion, edad, email, estado, imagenPerfil, nombreCompleto)
 */
insert into cliente values ("1", "/Cgi48EN8rKo0ZAyJk1nPtGsVhhvhanOAItnCZ0KlyyCF8yM1PU1U/cI6oEBX81j", "direccion de mi casa", 20, "juanp.loaizan@uqvirtual.edu.co", "VERIFICADO", "imagen-url", "Juan Pablo Loaiza");
insert into cliente values ("2", "/Cgi48EN8rKo0ZAyJk1nPtGsVhhvhanOAItnCZ0KlyyCF8yM1PU1U/cI6oEBX81j", "direccion de mi casa", 20, "juanpedde@gnlkk.com", "VERIFICADO", "imagen-url", "Alfonso");
insert into cliente values ("3", "/Cgi48EN8rKo0ZAyJk1nPtGsVhhvhanOAItnCZ0KlyyCF8yM1PU1U/cI6oEBX81j", "direccion de mi casa", 20, "juanpepec@gnlkk.com", "VERIFICADO", "imagen-url", "Rodrigo");
insert into cliente values ("4", "/Cgi48EN8rKo0ZAyJk1nPtGsVhhvhanOAItnCZ0KlyyCF8yM1PU1U/cI6oEBX81j", "direccion de mi casa", 20, "juanpepsdcsdc@gnlkk.com", "VERIFICADO", "imagen-url", "Sigifredo");
insert into cliente values ("5", "/Cgi48EN8rKo0ZAyJk1nPtGsVhhvhanOAItnCZ0KlyyCF8yM1PU1U/cI6oEBX81j", "direccion de mi casa", 20, "juanpcdepe@gnlkk.com", "NO_VERIFICADO", "imagen-url", "Roberto");

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
insert into horario values (1, "LMXJVSD", "2022-12-20", "2022-11-14", "14:00");
insert into horario values (2, "LMXJVSD", "2022-12-20", "2022-11-14", "16:00");
insert into horario values (3, "LMXJVSD", "2022-12-20", "2022-11-14", "20:00");
insert into horario values (4, "LMXJVSD", "2022-12-20", "2022-11-14", "22:00");
insert into horario values (5, "LMXJVSD", "2022-12-20", "2022-11-14", "12:00");

/*
 (codigo, nombre, precioAdicional )
 */
insert into tipo_sala values (1, "Sala 2D", 0);
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

insert into administrador_plataforma values ("1234", "1234", "admplatunicine@unicine.com", "urlImagen", "Cristiano Ronaldo Messi", "3124545343");

/*
 (codigo, direccion, nombre, cedulaAdministrador, codigoCiudad)
 */
insert into teatro values (1, "Barrio la aguapanela calle 2", "C.C Armenia", "1000", 1);
insert into teatro values (2, "Direccion equis de Pereira", "C.C Pereira", "5000", 2);
insert into teatro values (3, "Otra direccion, pero de Manizales", "C.C Unicentro", "2000", 3);
insert into teatro values (4, "Avenida Centenario, cerca al round point", "C.C Calima", "3000", 1);
insert into teatro values (5, "Por la 14, con KFC se reconoce de una", "C.C Portal del Quindio", "4000", 1);

/*
 (codigo, numero, distribucionSillas, teatroCodigo, tipoSalaCodigo)
 */
insert into sala values (1, 1, 1, 4, 1);
insert into sala values (2, 2, 1, 4, 1);
insert into sala values (3, 1, 3, 2, 2);
insert into sala values (4, 1, 2, 5, 1);
insert into sala values (5, 2, 1, 5, 2);
insert into sala values (6, 3, 1, 5, 1);
insert into sala values (7, 4, 1, 5, 3);
insert into sala values (8, 1, 1, 1, 1);

/*
 (codigo, duracionMinutos, edadApropiada, estado, nombre, nombreDirector, nombreEstudio, sinopsis, trailer)
 */
insert into pelicula values (1, 161, 12, "EN_CARTELERA", "Pantera Negra: Wakanda Por Siempre",
                             "Ryan Coogler", "Marvel Studios", "La reina Ramonda (Angela Bassett), Shuri (Letitia Wright), M''Baku (Winston Duke), Okoye (Danai Gurira) y las Dora Milaje (incluida Florence Kasumba) luchan por proteger a su nación de las potencias mundiales que intervienen tras la muerte del Rey T''Challa. Mientras los habitantes de Wakanda se esfuerzan por embarcarse en un nuevo capítulo, los héroes deben unirse con la ayuda de War Dog Nakia (Lupita Nyong''o) y Everett Ross (Martin Freeman) y forjar un nuevo camino para el reino de Wakanda. El film que cuenta con Tenoch Huerta como Namor, rey de una nación submarina oculta, también está protagonizada por Dominique Thorne, Michaela Coel, Mabel Cadena y Alex Livanalli.", "https://www.youtube.com/embed/BPjbiZQmBI4");

insert into pelicula values (2, 124, 12, "EN_CARTELERA", "Black Adam",
                             "Jaume Collet-Serra", "DC", "Casi 5.000 años después de haber sido dotado de los poderes omnipotentes de los antiguos dioses -y encarcelado con la misma rapidez-, Black Adam (Johnson) es liberado de su tumba terrenal, listo para desatar su forma única de justicia en el mundo moderno.", "https://www.youtube.com/embed/kOFTZWyaOgc");

insert into pelicula values (3, 93, 12, "EN_CARTELERA", "La Luz del Diablo",
                             "Daniel Stamn", "Gold Circle Films", "La Hermana Ann cree que está respondiendo a un llamado para ser la primera mujer exorcista... Pero ¿quién o qué la llamó? En respuesta a un aumento mundial de las posesiones demoníacas, ella buscará un lugar en una escuela de exorcismo reabierta por la Iglesia Católica. Hasta ahora, estas escuelas solo han enseñado el Rito del Exorcismo a sacerdotes. Sin embargo, un profesor y experto exorcista, reconoce los dones de la Hermana Ann y accede a capacitarla. Empujada a su primera experiencia junto a su compañero de estudios, el Padre Dante (Christian Navarro), la Hermana Ann se enfrentará a la batalla por el alma de una niña. Y ella empezará a sentir que la menor está poseída por el mismo demonio que atormentó a su propia madre hace años. Decidida a erradicar el mal, Ann pronto se dará cuenta de que el Diablo la tiene justo donde la quiere.", "https://www.youtube.com/watch?v=cqQwSbNhN50&ab_channel=TrailersInSpanish");

insert into pelicula values (4, 190, 7, "PROXIMAMENTE", "Avatar: El Camino Del Agua",
                             "James Cameron", "20th Century Studios", "Ambientada más de una década después de los sucesos que tuvieron lugar en la primera película, AVATAR: EL CAMINO DEL AGUA narra la historia de la familia Sully (Jake, Neytiri y sus hijos), el peligro que los persigue, los esfuerzos que hacen para mantenerse a salvo, las batallas que libran para seguir con vida, y las tragedias que sobrellevan. Dirigida por James Cameron y producida por Cameron y Jon Landau, la película es protagonizada por Zoe Saldana, Sam Worthington, Sigourney Weaver, Stephen Lang, Cliff Curtis, Joel David Moore, CCH Pounder, Edie Falco, Jemaine Clement, Giovanni Ribisi y Kate Winslet.", "https://www.youtube.com/watch?v=u0hxjdWG84k&t=2s&ab_channel=SensaCineTRAILERS");

insert into pelicula values (5, 102, 7, "PROXIMAMENTE", "Un Mundo Extraño",
                             "Don Hall (Director), Qui Nguyen (Co-Director).", "Walt Disney Studios Motion Pictures", "La nueva película de Walt Disney Animation Studios, presenta un viaje de acción y aventura que transcurre en una tierra inexplorada y traicionera, donde criaturas fantásticas aguardan a los legendarios Clade, una familia de exploradores cuyas diferencias amenazan con derribar su última misión que es, por lejos, la más decisiva.", "https://www.youtube.com/watch?v=LvIMiuYG6KQ&ab_channel=DisneyLatinoam%C3%A9rica");

/* Pelicula codigo, url imagen, ruta imagen */
insert into pelicula_imagenes values (1, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668454030/unicine/peliculas/cc8fdbb388a4-480x670_kpbojz.jpg", "unicine/peliculas/cc8fdbb388a4-480x670_kpbojz");
insert into pelicula_imagenes values (1, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668697733/unicine/peliculas/wakanda2_jtm9pj.jpg", "unicine/peliculas/wakanda2_jtm9pj");
insert into pelicula_imagenes values (2, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668453992/unicine/peliculas/cb55daaeb245-480x670-3_hb8k2s.png", "unicine/peliculas/cb55daaeb245-480x670-3_hb8k2s");
insert into pelicula_imagenes values (2, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668697784/unicine/peliculas/black-adam2_lmqdu2.jpg", "unicine/peliculas/black-adam2_lmqdu2");

insert into pelicula_imagenes values (3, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668454138/unicine/peliculas/la-luz-del-diablo-1-aposter_lai5xs.jpg", "unicine/peliculas/la-luz-del-diablo-1-aposter_lai5xs");
insert into pelicula_imagenes values (3, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668697841/unicine/peliculas/la-luz-del-diablo-2-banner_bd7fg8.jpg", "unicine/peliculas/la-luz-del-diablo-2-banner_bd7fg8");

insert into pelicula_imagenes values (4, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668454068/unicine/peliculas/86c502a6cc59-poster-avatar-el-camino-del-agua_r2utxd.jpg", "unicine/peliculas/86c502a6cc59-poster-avatar-el-camino-del-agua_r2utxd");
insert into pelicula_imagenes values (4, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668697542/unicine/peliculas/avatar2_wx6ypt.jpg", "unicine/peliculas/avatar2_wx6ypt");

insert into pelicula_imagenes values (5, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668697889/unicine/peliculas/mundoextranio-2-cil8ay.jpg", "unicine/peliculas/mundoextranio-2-cil8ay");
insert into pelicula_imagenes values (5, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668453961/unicine/peliculas/mundoextranio-1-aubeg9c.jpg", "unicine/peliculas/mundoextranio-1-aubeg9c");

/*
 (codigoPelicula, genero)
 */
insert into pelicula_generos values (1, "DRAMA");
insert into pelicula_generos values (1, "CIENCIA_FICCION");
insert into pelicula_generos values (1, "ACCION");
insert into pelicula_generos values (2, "FANTASIA");
insert into pelicula_generos values (3, "DOCUMENTAL");
insert into pelicula_generos values (4, "AVENTURA");
insert into pelicula_generos values (4, "ACCION");
insert into pelicula_generos values (5, "ANIMADA");

/*
 (codigo, precioFuncion, codigoHorario, codigoPelicula, codigoSala)
 */
insert into funcion values (1, 7500, 1, 1, 1);
insert into funcion values (2, 8000, 3, 3, 3);
insert into funcion values (3, 7500, 2, 1, 2);
insert into funcion values (4, 8000, 5, 2, 2);
insert into funcion values (5, 7500, 5, 1, 4);
insert into funcion values (6, 7500, 4, 3, 5);
insert into funcion values (7, 7500, 3, 1, 7);
insert into funcion values (8, 7500, 1, 2, 6);

/*
 (codigo, estado, fechaCompra, fechaPelicula, medioPago, PrecioTotal, clienteCedula, funcionCodigo)
 */
insert into compra values (1, "CREADA", "2022-10-15", "2022-10-15" , "2022-10-15", "MASTERCARD", 25600, "1", null, 1);
insert into compra values (2, "CREADA", "2022-10-18", "2022-10-15" , "2022-10-15", "MASTERCARD", 82600, "1", null, 2);
insert into compra values (3, "CREADA", "2022-10-19","2022-10-15", "2022-10-15", "MASTERCARD", 29600, "3", null, 3);
insert into compra values (4, "CREADA", "2022-10-26","2022-10-15", "2022-10-15", "MASTERCARD", 118400, "4", null, 4);
insert into compra values (5, "CREADA", "2022-10-30","2022-10-15" , "2022-10-15", "MASTERCARD", 35600, "1", null, 5);

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
insert into cliente_cupon values (1, "ACTIVO", "2022-10-31", "1", 1);
insert into cliente_cupon values (2, "REDIMIDO", "2022-10-31", "2", 1);
insert into cliente_cupon values (3, "ACTIVO", "2022-11-06", "1", 2);
insert into cliente_cupon values (4, "VENCIDO", "2022-11-09", "3", 3);
insert into cliente_cupon values (5, "ACTIVO", "2022-11-15", "4", 3);

/*
 (codigo, columna, fila, precio, compraCodigo)
 */
insert into entrada values (1, 11, 'A', 7800, 1);
insert into entrada values (2, 10, 'A', 7800, 1);
insert into entrada values (3, 8, 'F', 7800, 2);
insert into entrada values (4, 10, 'G', 7800, 3);
insert into entrada values (5, 5, 'J', 7800, 5);
insert into entrada values (6, 6, 'J', 7800, 4);
insert into entrada values (7, 9, 'B', 6000, 1);
insert into entrada values (8, 10, 'B', 6000, 1);
insert into entrada values (9, 11, 'D', 7800, 2);
insert into entrada values (10, 11, 'D', 7800, 2);
insert into entrada values (11, 11, 'D', 7800, 3);
insert into entrada values (12, 11, 'D', 7800, 3);

/*
 (codigo, extras, imagenProducto, nombre, precio)
 */

insert into producto_confiteria values (1, null, "Combo 1", 22000);
insert into producto_confiteria values (2, null, "Combo 2", 18000);
insert into producto_confiteria values (3, null, "Combo 3", 35600);
insert into producto_confiteria values (4, null, "Combo 4", 12000);
insert into producto_confiteria values (5, null, "Combo 5", 26800);

insert into producto_confiteria_imagen_producto values (1, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668484854/unicine/confiteria/borrar_ckwag1.png", "unicine/confiteria/borrar_ckwag1");
insert into producto_confiteria_imagen_producto values (2, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668484854/unicine/confiteria/borrar_ckwag1.png", "unicine/confiteria/borrar_ckwag1");
insert into producto_confiteria_imagen_producto values (3, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668484854/unicine/confiteria/borrar_ckwag1.png", "unicine/confiteria/borrar_ckwag1");
insert into producto_confiteria_imagen_producto values (4, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668484854/unicine/confiteria/borrar_ckwag1.png", "unicine/confiteria/borrar_ckwag1");
insert into producto_confiteria_imagen_producto values (5, "https://res.cloudinary.com/djrslfkmr/image/upload/v1668484854/unicine/confiteria/borrar_ckwag1.png", "unicine/confiteria/borrar_ckwag1");


/*
 (codigo, precio, unidades, compraCodigo, productoConfiteriaCodigo)
 */
insert into compra_confiteria values (1, 24000, 2, 1, 4);
insert into compra_confiteria values (2, 22000, 1, 1, 1);
insert into compra_confiteria values (3, 54000, 3, 2, 2);
insert into compra_confiteria values (4, 24000, 2, 3, 4);
insert into compra_confiteria values (5, 35600, 1, 3, 3);
insert into compra_confiteria values (6, 24000, 2, 4, 4);
insert into compra_confiteria values (7, 22000, 1, 5, 1);

insert into pqrs values (1, "2022-10-15", "Aquí va el mensaje 1", "Motivo de la solicitud 1", "1");
insert into pqrs values (2, "2022-10-15", "Aquí va el mensaje 2", "Motivo de la solicitud 2", "1");
insert into pqrs values (3, "2022-10-15", "Aquí va el mensaje 3", "Motivo de la solicitud 3", "1");
insert into pqrs values (4, "2022-10-15", "Aquí va el mensaje 4", "Motivo de la solicitud 4", "1");
insert into pqrs values (5, "2022-10-15", "Aquí va el mensaje 5", "Motivo de la solicitud 5", "1");