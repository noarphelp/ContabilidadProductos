
![image](https://github.com/user-attachments/assets/7f706b08-3df3-4367-a9af-51a3dd8d079d)



Este proyecto está compuesto por varios microservicios y herramientas que se comunican entre sí para ofrecer una solución distribuida, escalable y reactiva. A continuación se describe cada uno de los componentes del ecosistema:

Componentes del Ecosistema
--------------------------
**EurekaServer:**
- Descripción: Servidor de registro y descubrimiento de servicios. Los microservicios se registran aquí para ser descubiertos dinámicamente.
- URL: http://localhost:8761

**Pedidos:**
- Descripción: Gestiona operaciones relacionadas con pedidos (creación, modificación, consulta).
- Nueva Funcionalidad: Publica eventos en Kafka cuando se crea un pedido.

**Productos:**
- Descripción: Maneja el catálogo de productos (consultas, altas, actualizaciones).

**ServerConfig:**
- Descripción: Configuración centralizada para todos los microservicios usando Spring Cloud Config.

**Server Gateway:**
- Descripción: Punto de entrada único para las peticiones. Enruta tráfico a los microservicios usando Spring Cloud Gateway.

**Vista:**
- Descripción: Interfaz de usuario para interactuar con el sistema.

**EnvioMails (Nuevo ✨):**
- Descripción: Microservicio suscrito a Kafka que envía emails con detalles de pedidos creados.
- Tópico Kafka: pedidos-topic

**Apache Kafka (Nuevo 🔄):**
- Descripción: Sistema de mensajería distribuido para comunicación asíncrona entre servicios.

Arquitectura del Sistema
------------------------
  
    Cliente --> Gateway  
    Gateway --> Pedidos  
    Gateway --> Productos  
    Pedidos -->|Publica eventos| Kafka  
    Kafka -->|Consume eventos| EnvioMails  
    EnvioMails -->|Envía email| Usuario  
    Eureka -. Registro .- Pedidos  
    Eureka -. Registro .- Productos  
    Eureka -. Registro .- EnvioMails  
    ServerConfig -->|Provee config| Todos


Requisitos para Ejecutar el Proyecto
-------------------------------------
**Java 21:** Versión base del proyecto.

**Docker:** Para ejecutar Kafka y Zookeeper.

**MySQL:** Base de datos para persistencia.

**Kafka:**
- Crear red de Docker (si no existe)
- Ejecutar Kafka + Zookeeper
```
docker network create kafka-network
```
```
docker-compose -f docker-compose-kafka.yml up -d
```

Contenido de docker-compose-kafka.yml:
```
version: '3'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.3.0
    networks:
      - kafka-network
    ports:
      - "2181:2181"
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181

  kafka:
    image: confluentinc/cp-kafka:7.3.0
    depends_on:
      - zookeeper
    networks:
      - kafka-network
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_AUTO_CREATE_TOPICS_ENABLE: "true"

networks:
  kafka-network:
    external: true
```

Guardar el archivo docker-compose-kafka.yml en la raíz de tu proyecto.

Ejecutar los contenedores: En la terminal, navega hasta el directorio donde guardaste el archivo y ejecuta:

```
  docker-compose -f docker-compose-kafka.yml up -d
```
Este comando levantará dos contenedores:
Zookeeper en el puerto 2181.
Kafka en el puerto 9092.
Verificar que los contenedores estén funcionando correctamente con:
 ```
   docker ps
```

**Credenciales de Email (en EnvioMails):**
Configura en application.yml:
```
spring:
  mail:
    host: smtp.gmail.com
    username: tu_email@gmail.com
    password: tu_contraseña_o_app_password
```
Uso
----
**Secuencia de Inicio:**
- **Eureka Server:**

./mvnw spring-boot:run -pl EurekaServer
```

- **ServerConfig:**
```bash
./mvnw spring-boot:run -pl ServerConfig
```

- **Kafka:**
```bash
docker-compose -f docker-compose-kafka.yml up -d
```

- **Microservicios Base:**
```bash
./mvnw spring-boot:run -pl Pedidos
./mvnw spring-boot:run -pl Productos
```

- **EnvioMails (Nuevo):**
```bash
./mvnw spring-boot:run -pl EnvioMails
```

- **Gateway:**
```bash
./mvnw spring-boot:run -pl ServerGateway
```

- **Vista:**
Accede a la interfaz en [http://localhost:8080).

Flujo de Notificaciones por Email
----------------------------------
**Creación de Pedido:**
1. Un cliente crea un pedido a través de la Vista.
2. El microservicio Pedidos procesa la solicitud y publica un evento en Kafka:
json
```
{  
  "pedidosId": 101,  
  "codProducto": "SKU-123",  
  "total": 99.99,  
  "fechaPedido": "2023-10-05T14:30:00"  
}

```
**Procesamiento en EnvioMails:**
- EnvioMails consume el evento desde pedidos-topic.
- Genera un email con formato HTML y lo envía al usuario.

**Ejemplo de Email:**
html
```
<h1>Detalles de su Pedido #101</h1>  
<p><b>Producto:</b> SKU-123</p>  
<p><b>Total:</b> $99.99</p>  
<p><b>Fecha:</b> 2023-10-05 14:30</p>

```
Tecnologías Usadas
------------------
- **Spring Boot:** Construcción de microservicios.
- **Spring Cloud:** Eureka, Config Server, Gateway.
- **Apache Kafka:** Mensajería asíncrona.
- **Spring Kafka:** Integración con Kafka.
- **JavaMailSender:** Envío de correos electrónicos.
- **Docker:** Contenerización de Kafka.
- **MySQL:** Almacenamiento persistente.

Autor
-----
Desarrollado por Nicolás F.


