¡Bienvenidos a Craquelin!

Proyecto de fin de ciclo del FP Dual Superior de Desarrollo de Aplicaciones Web, en el IES Fernando Wirtz (2022-2024).
Por: Iván Lesta Casáis.

Estado actual:

-Servidor: Java 17 con Spring MVC. Envío de datos al front con Thymeleaf.

-Base de datos:Se puede conectar a MariaDB en local, pero actualmente está configurado en memoria H2. Tiene un usuario admin con control total
para poder ejecutar con facilidad las pruebas:

	- Admin: admin@gmail.com
	  Contraseña: 1234

-Front-End: HTML, CSS, JavaScript (validación de formularios, pop-up de cookies, carrusel de imágenes), y Bootstrap.

-Despliegue: VSC+GitHub

-Nota de permisos: Mediante requestMatchers, y verificaciones con Thymeleaf los usuarios tienen 
acceso completo a todos los apartados de la aplicación salvo a: 
	-Crear, editar, y borrar productos y categorías.
	-Consultar datos de clientes registrados y modificarlos.
	-Visualización del ID interno de los productos y categorías.
	
	Los manager a mayores de los permisos de usuarios pueden: crear, editar, y borrar productos y categorías,
	, consultar sus IDs, y gestionar pedidos (CRUD y cambiar estado).

	Los admin tiene acceso completo a la app y pueden modificar todos los datos de clientes, 
	categorías y productos.

Enlace a la presentación del proyecto: https://www.canva.com/design/DAGHuYqsHiw/AUWTq3VMeTwBFgWhftkmGw/edit?utm_content=DAGHuYqsHiw&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton
