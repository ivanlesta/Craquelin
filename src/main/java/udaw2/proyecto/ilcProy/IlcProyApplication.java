package udaw2.proyecto.ilcProy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import udaw2.proyecto.ilcProy.domain.Categoria;
import udaw2.proyecto.ilcProy.domain.Cliente;
import udaw2.proyecto.ilcProy.domain.EstadoPedido;
import udaw2.proyecto.ilcProy.domain.LineaDePedido;
import udaw2.proyecto.ilcProy.domain.Pedido;
import udaw2.proyecto.ilcProy.domain.Producto;
import udaw2.proyecto.ilcProy.domain.Rol;
import udaw2.proyecto.ilcProy.services.CategoriaService;
import udaw2.proyecto.ilcProy.services.ClienteService;
import udaw2.proyecto.ilcProy.services.LineaDePedidoService;
import udaw2.proyecto.ilcProy.services.PedidoService;
import udaw2.proyecto.ilcProy.services.ProductoService;

@SpringBootApplication
public class IlcProyApplication {

        public static void main(String[] args) {
                SpringApplication.run(IlcProyApplication.class, args);
        }

        @Bean
        CommandLineRunner initData(ProductoService productoService, CategoriaService categoriaService,
                        ClienteService clienteService, PedidoService pedidoService,
                        LineaDePedidoService lineaDePedidoService) {
                return args -> {
                        // Se añaden las categorias deseadas
                        Categoria cookies = categoriaService.añadir(new Categoria(1L, "Cookies"));
                        Categoria cupcakes = categoriaService.añadir(new Categoria(2L, "Cupcakes"));
                        Categoria tartas = categoriaService.añadir(new Categoria(3L, "Tartas"));
                        Categoria empanadas = categoriaService.añadir(new Categoria(4L, "Empanadas"));
                        Categoria panes = categoriaService.añadir(new Categoria(5L, "Panes"));

                        // Se añaden 4 productos a cada categoria
                        // Cookies
                        Producto chocoNuez = new Producto(1L, "Galletas de chocolate con nueces", 1.50,
                                        "Deliciosas galletas de chocolate repletas de trozos crujientes de nueces, "
                                                        +
                                                        "perfectas para los amantes del chocolate y las nueces",
                                        cookies, "/images/productos/cookies/chocoNuez.png");
                        productoService.añadir(chocoNuez);

                        Producto manteCaramelo = new Producto(2L, "Galletas rellenas de caramelo", 1.75,
                                        "Galletas de mantequilla irresistibles rellenas de caramelo que se derriten en la boca.",
                                        cookies, "/images/productos/cookies/manteCaramelo.png");

                        productoService.añadir(manteCaramelo);

                        Producto avenaPasas = new Producto(3L, "Galletas de avena y pasas", 1.25,
                                        "Galletas suaves de avena con pasas jugosas, ideales para quienes "
                                                        +
                                                        "buscan un equilibrio entre lo saludable y lo delicioso.",
                                        cookies, "/images/productos/cookies/avenaPasas.png");

                        productoService.añadir(avenaPasas);

                        Producto avenaCoco = new Producto(4L, "Galletas de avena y coco", 1.50,
                                        "Combinación única de avena y coco en unas galletas tiernas y llenas de sabor.",
                                        cookies, "/images/productos/cookies/avenaCoco.png");

                        productoService.añadir(avenaCoco);

                        // Cupcakes
                        Producto vainillaFresa = new Producto(5L, "Cupcake de vainilla con fresa", 2.0,
                                        "Cupcake esponjoso de vainilla coronado con un delicioso frosting de crema de fresa.",
                                        cupcakes, "/images/productos/cupcakes/vainillaFresa.png");

                        productoService.añadir(vainillaFresa);

                        Producto cupChocoAvellana = new Producto(6L, "Cupcake de chocolate con avellanas",
                                        2.25,
                                        " Intenso cupcake de chocolate cubierto con una suave crema de avellanas.",
                                        cupcakes, "/images/productos/cupcakes/cupChocoAvellana.png");

                        productoService.añadir(cupChocoAvellana);

                        Producto zanahoriaCrema = new Producto(7L, "Cupcake de zanahoria con queso crema",
                                        2.50,
                                        "Jugoso cupcake de zanahoria con un generoso glaseado de queso crema.",
                                        cupcakes, "/images/productos/cupcakes/zanahoriaCrema.png");

                        productoService.añadir(zanahoriaCrema);

                        Producto limonMerengue = new Producto(8L, "Cupcake de limón con merengue", 2.0,
                                        "Cupcake refrescante de limón con una cobertura ligera de merengue.",
                                        cupcakes, "/images/productos/cupcakes/limonMerengue.png");
                        productoService.añadir(limonMerengue);

                        // Tartas
                        Producto manzanaAlmendra = new Producto(9L, "Tarta de manzana con almendra ", 20.0,
                                        "Tarta clásica de manzana con una capa crujiente de almendras tostadas.",
                                        tartas, "/images/productos/tartas/manzanaAlmendra.png");
                        productoService.añadir(manzanaAlmendra);

                        Producto tarChocoAvellana = new Producto(10L, "Tarta de chocolate y avellanas", 25.0,
                                        "Tentadora tarta de chocolate con capas de crema de avellanas y decorada con avellanas picadas.",
                                        tartas, "/images/productos/tartas/tarChocoAvellana.png");
                        productoService.añadir(tarChocoAvellana);

                        Producto frutaCrema = new Producto(11L, "Tarta de frutas con crema pastelera", 22.0,
                                        "Tarta colorida con frutas frescas de temporada sobre una base de crema pastelera.",
                                        tartas, "/images/productos/tartas/frutaCrema.png");
                        productoService.añadir(frutaCrema);

                        Producto quesoFrutos = new Producto(12L, "Tarta de queso con frutos rojos", 18.0,
                                        "Tarta de queso cremosa con un delicioso coulis de frutos rojos que realza su sabor.",
                                        tartas, "/images/productos/tartas/quesoFrutos.png");
                        productoService.añadir(quesoFrutos);

                        // Empanadas
                        Producto carneAceitunasHuevo = new Producto(13L, "Empanada de carne con aceitunas y huevo",
                                        3.50,
                                        "Empanada jugosa de carne sazonada con aceitunas y huevo duro en su interior.",
                                        empanadas, "/images/productos/empanadas/carneAceitunasHuevo.png");
                        productoService.añadir(carneAceitunasHuevo);

                        Producto polloCurry = new Producto(14L, "Empanada de pollo al curry", 3.75,
                                        "Empanada con un relleno de pollo sazonado al curry que despierta los sentidos.",
                                        empanadas, "/images/productos/empanadas/polloCurry.png");
                        productoService.añadir(polloCurry);

                        Producto espinacasFeta = new Producto(15L, "Empanada de espinacas y queso feta", 3.25,
                                        "Empanada vegetariana con espinacas frescas y queso feta.",
                                        empanadas, "/images/productos/empanadas/espinacasFeta.png");
                        productoService.añadir(espinacasFeta);

                        Producto jamonQueso = new Producto(16L, "Empanada de jamón y queso", 3.50,
                                        "Clásica empanada rellena de jamón y queso derretido.",
                                        empanadas, "/images/productos/empanadas/jamonQueso.png");
                        productoService.añadir(jamonQueso);

                        // Panes
                        Producto baguetteMadre = new Producto(17L, "Baguette de masa madre", 3.5,
                                        "Baguette artesanal con auténtico sabor a masa madre.",
                                        panes, "/images/productos/panes/baguetteMadre.png");
                        productoService.añadir(baguetteMadre);

                        Producto aceitunasTomate = new Producto(18L, "Pan de aceitunas y tomate seco", 4.50,
                                        "Pan rústico con trozos de aceitunas y tomates secos que aportan sabores intensos.",
                                        panes, "/images/productos/panes/aceitunasTomate.png");

                        productoService.añadir(aceitunasTomate);

                        Producto integralSemillas = new Producto(19L, "Pan integral con semillas", 3.25,
                                        "Pan integral nutritivo con una mezcla de semillas para un toque saludable.",
                                        panes, "/images/productos/panes/integralSemillas.png");

                        productoService.añadir(integralSemillas);

                        Producto ajoHierbas = new Producto(20L, "Pan de ajo con hierbas", 4.0,
                                        "Pan crujiente untado con mantequilla de ajo y hierbas aromáticas.",
                                        panes, "/images/productos/panes/ajoHierbas.png");

                        productoService.añadir(ajoHierbas);

                        // Se crean 3 clientes, uno con cada rol
                        Cliente user = new Cliente(1L, "user@gmail.com", "1234", "user", "user1",
                                        "C/inventada, nº 1, 15000, Coruña", 0L, Rol.USER, LocalDateTime.now());
                        clienteService.añadir(user);

                        Cliente manager = new Cliente(2L, "manager@gmail.com", "1234", "manager", "manager1",
                                        "C/inventada, nº 2, 15000, Coruña", 0L, Rol.MANAGER, LocalDateTime.now());
                        clienteService.añadir(manager);

                        Cliente admin = new Cliente(3L, "admin@gmail.com", "1234", "admin", "admin1",
                                        "C/inventada, nº 3, 15000, Coruña", 0L, Rol.ADMIN, LocalDateTime.now());
                        clienteService.añadir(admin);

                        // Pedido 1: 2 productos de la categoría Cookies
                        Pedido pedido1 = new Pedido();
                        pedido1.setUnidadesTotales(1L);
                        pedido1.setPrecioTotal(1.5);
                        pedido1.setFechaPedido(LocalDateTime.now());
                        pedido1.setEstadoPedido(EstadoPedido.CARRITO);
                        pedido1.setComprador(admin);

                        List<LineaDePedido> lineasDePedido1 = new ArrayList<>();
                        Producto productoCookies1 = productoService.obtenerPorId(1L);

                        LineaDePedido linea1 = new LineaDePedido();
                        linea1.setProducto(productoCookies1);
                        linea1.setCantidadProducto(1L);
                        linea1.setPedido(pedido1);
                        lineasDePedido1.add(linea1);

                        Producto productoCookies2 = productoService.obtenerPorId(2L);
                        LineaDePedido linea2 = new LineaDePedido();
                        linea2.setProducto(productoCookies2);
                        linea2.setCantidadProducto(1L);
                        pedidoService.añadir(pedido1);
                        linea2.setPedido(pedido1);
                        lineaDePedidoService.añadir(linea2);
                        lineasDePedido1.add(linea2);

                        pedido1.setLineasDePedido(lineasDePedido1);
                        pedidoService.editar(pedido1);

                        // Pedido 2: 4 productos de la categoría Empanadas
                        Pedido pedido2 = new Pedido();
                        pedido2.setUnidadesTotales(4L);
                        pedido2.setPrecioTotal(80.0);
                        pedido2.setFechaPedido(LocalDateTime.of(2024, 6, 2, 10, 15, 0));
                        pedido2.setEstadoPedido(EstadoPedido.CARRITO);
                        pedido2.setComprador(user);

                        List<LineaDePedido> lineasDePedido2 = new ArrayList<>();
                        Producto productoEmpanadas1 = productoService.obtenerPorId(9L);
                        LineaDePedido linea3 = new LineaDePedido();
                        linea3.setProducto(productoEmpanadas1);
                        linea3.setCantidadProducto(4L);
                        pedidoService.añadir(pedido2);
                        linea3.setPedido(pedido2);
                        lineaDePedidoService.añadir(linea3);
                        lineasDePedido2.add(linea3);

                        pedido2.setLineasDePedido(lineasDePedido2);
                        pedidoService.editar(pedido2);

                        // Pedido 3: 1 producto de la categoría Tartas
                        Pedido pedido3 = new Pedido();
                        pedido3.setUnidadesTotales(1L);
                        pedido3.setPrecioTotal(22.0);
                        pedido3.setFechaPedido(LocalDateTime.of(2024, 6, 3, 15, 45, 0));
                        pedido3.setEstadoPedido(EstadoPedido.CONFIRMADO);
                        pedido3.setComprador(user);

                        List<LineaDePedido> lineasDePedido3 = new ArrayList<>();
                        Producto productoTartas = productoService.obtenerPorId(11L);
                        LineaDePedido linea4 = new LineaDePedido();
                        linea4.setProducto(productoTartas);
                        linea4.setCantidadProducto(1L);
                        pedidoService.añadir(pedido3);
                        linea4.setPedido(pedido3);
                        lineaDePedidoService.añadir(linea4);
                        lineasDePedido3.add(linea4);
                        

                        pedido3.setLineasDePedido(lineasDePedido3);
                        pedidoService.editar(pedido3);


                        // Pedido 4: 1 producto de la categoría Tartas
                        Pedido pedido4 = new Pedido();
                        pedido4.setUnidadesTotales(1L);
                        pedido4.setPrecioTotal(3.5);
                        pedido4.setFechaPedido(LocalDateTime.of(2024, 5, 3, 17, 45, 0));
                        pedido4.setEstadoPedido(EstadoPedido.ELABORANDOSE);
                        pedido4.setComprador(user);

                        List<LineaDePedido> lineasDePedido4 = new ArrayList<>();
                        Producto productoTartas1 = productoService.obtenerPorId(13L);
                        LineaDePedido linea5 = new LineaDePedido();
                        linea5.setProducto(productoTartas1);
                        linea5.setCantidadProducto(1L);
                        pedidoService.añadir(pedido4);
                        linea5.setPedido(pedido4);
                        lineaDePedidoService.añadir(linea5);
                        lineasDePedido4.add(linea5);
                        

                        pedido4.setLineasDePedido(lineasDePedido4);
                        pedidoService.editar(pedido4);

                        // Pedido 5: 2 producto de la categoría Tartas
                        Pedido pedido5 = new Pedido();
                        pedido5.setUnidadesTotales(5L);
                        pedido5.setPrecioTotal(18.75);
                        pedido5.setFechaPedido(LocalDateTime.of(2024, 4, 3, 18, 35, 0));
                        pedido5.setEstadoPedido(EstadoPedido.FINALIZADO);
                        pedido5.setComprador(user);

                        List<LineaDePedido> lineasDePedido5 = new ArrayList<>();
                        Producto productoTartas2 = productoService.obtenerPorId(14L);
                        LineaDePedido linea6 = new LineaDePedido();
                        linea6.setProducto(productoTartas2);
                        linea6.setCantidadProducto(5L);
                        pedidoService.añadir(pedido5);
                        linea6.setPedido(pedido5);
                        lineaDePedidoService.añadir(linea6);
                        lineasDePedido5.add(linea6);
                        

                        pedido5.setLineasDePedido(lineasDePedido5);
                        pedidoService.editar(pedido5);

                        // Pedido 6: 3 producto de la categoría Tartas
                        Pedido pedido6 = new Pedido();
                        pedido6.setUnidadesTotales(2L);
                        pedido6.setPrecioTotal(16.0);
                        pedido6.setFechaPedido(LocalDateTime.of(2024, 3, 3, 8, 15, 0));
                        pedido6.setEstadoPedido(EstadoPedido.ANULADO);
                        pedido6.setComprador(manager);

                        List<LineaDePedido> lineasDePedido6 = new ArrayList<>();
                        Producto productoTartas3 = productoService.obtenerPorId(20L);
                        LineaDePedido linea7 = new LineaDePedido();
                        linea7.setProducto(productoTartas3);
                        linea7.setCantidadProducto(2L);
                        pedidoService.añadir(pedido6);
                        linea7.setPedido(pedido6);
                        lineaDePedidoService.añadir(linea7);
                        lineasDePedido6.add(linea7);
                        

                        pedido6.setLineasDePedido(lineasDePedido6);
                        pedidoService.editar(pedido6);
                };
        }
}
