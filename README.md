Proyecto Iván Lesta

Estado actual:

-Servidor: Java 17 con Spring MVC. Envío de datos al front con Thymeleaf.

-Base de datos:Se puede conectar a MariaDB en local, pero actualmente está configurado en memoria H2. Tiene un usuario admin con control total
para poder ejecutar con facilidad las pruebas:

	- Admin: admin@gmail.com
	  Contraseña: 1234

-JavaScript: Validación de formularios, pop-up de cookies, carrusel de imágenes.

-Front-End: HTML con Thymeleaf y CSS.

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
