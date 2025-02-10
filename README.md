
![image](https://github.com/user-attachments/assets/7f706b08-3df3-4367-a9af-51a3dd8d079d)




                                           Ecosistema de Microservicios

Este proyecto está compuesto por varios microservicios y herramientas que se comunican entre sí para ofrecer una solución distribuida y escalable. A continuación se describe cada uno de los componentes del ecosistema:

## Componentes del Ecosistema

1. **EurekaServer**:
   - Es el servidor de registro y descubrimiento de servicios. Los microservicios se registran en Eureka para ser descubiertos y utilizados por otros servicios.
   - **URL**: http://localhost:8761

2. **Pedidos**:
   - Este microservicio gestiona las operaciones relacionadas con los pedidos. Proporciona una API para crear, modificar y consultar pedidos.

3. **Productos**:
   - Este microservicio maneja el catálogo de productos. Permite realizar operaciones como consultar información sobre productos, agregar nuevos productos, y actualizar los existentes.

4. **ServerConfig**:
   - Este servicio proporciona la configuración centralizada para todos los microservicios. Utiliza Spring Cloud Config para cargar configuraciones específicas de cada entorno y perfil.

5. **Server Gateway**:
   - El Gateway actúa como un punto de entrada único para todas las peticiones. Redirige las peticiones entrantes a los servicios correspondientes. Se utiliza Spring Cloud Gateway para implementar esta funcionalidad.

6. **Vista**:
   - Este es el componente de frontend que permite a los usuarios interactuar con el sistema. Sirve como interfaz de usuario para lanzar peticiones a los microservicios backend.

## Archivos de Configuración

- **servicio-pedidos.yml**: Configuración del servicio de pedidos.
- **servicio-productos.yml**: Configuración del servicio de productos.

## Requisitos para Ejecutar el Proyecto

1. **Instalar Java**: Este proyecto está desarrollado en Java 21. Asegúrate de tener la versión correcta instalada.
   
2. **Eureka Server**: Asegúrate de que el servidor Eureka esté ejecutándose antes de iniciar otros microservicios.

3. **ConfigServer**: Asegúrate de que el Config Server esté corriendo para proporcionar la configuración centralizada.

4. **Base de Datos**: Cada microservicio que interactúa con la base de datos necesita tener configurada la conexión a la base de datos MySQL.

5. **Iniciar los Microservicios**: Cada microservicio debe ser ejecutado de forma independiente. Se recomienda iniciar primero el **Eureka Server** y luego los demás microservicios.

## Uso

1. Inicia el **Eureka Server** en el puerto 8761.
2. Inicia el microservicio de **Pedidos** en el puerto 7777.
3. Inicia el microservicio de **Productos** en el puerto 8000.
4. Inicia el **Config Server** para gestionar la configuración centralizada.
5. Inicia el **Gateway Server** para centralizar las peticiones a los microservicios.
6. Usa la **Vista** para interactuar con los microservicios desde el frontend.

## Tecnologías Usadas

- **Spring Cloud**: Para gestión de microservicios, Eureka, y Config Server.
- **Spring Boot**: Para crear los microservicios.
- **Spring Cloud Gateway**: Para el enrutamiento y agregación de microservicios.
- **Spring Data JPA**: Para la interacción con bases de datos.
- **MySQL**: Para almacenamiento de datos.
- **Frontend (Vista)**: Interfaz de usuario para interactuar con el backend.

## Autor

Desarrollado por [Nicolás f].

