## RestauranteIsrael

El proyecto se encarga de procesar ordenes de comida para lo cual se pondra a disposición  
el siguiente flujo:

1.  **Consulta el cliente**
2.  **Seleccionar un cliente**
3.  **Obtener menú**
4.  **Seleccionar platillo**
5.  **Ingresar orden**
6.  **Entregar orden**

## Diseño

En este proyecto se aplica una arquitectura en **microservicios** y **api-gateway**.  
Se generan 3 proyectos Rest API que se encargaran de administrar un solo componente,  
mientras que un api-gateway se encargara de centralizar todas las peticiones y  
distribuirlas al proyecto correspondiente.

El almacenamiento de información se realiza en memoria con un patrón de diseño singleton para el cual cada proyecto autogenera información fake para tener data inicial.

**api-person** genera 20 registros fake

**api-products** genera 8 registros

**api-orders** genera 2 registros

## Componentes

| **Componente** | **Descripción** | Función |
| --- | --- | --- |
| **api-person** | Se encarga de los clientes | Administra los clientes |
| **api-products** | Se encarga de los platillos de comida | Administra el menú (Platillos de comida) |
| **api-orders** | Se encarga de las ordenes | Administra las ordenes |
| **uisrael-lib-utils** | Contiene las clases estándar para todos los proyectos |   |
| **api-gateway** | Se encarga de centralizar y distribuir las peticiones | Centraliza las peticiones |

## Levantar microservicios

Descargar los war en un directorio

```sh
┌──(usuario㉿EQUIPO)-[/SEMANA_7/components]
└─$ ll
total 93924
-rw-rw-r-- 1 akira akira 28606522 Nov 17 19:30 api-gateway.jar
-rw-rw-r-- 1 akira akira 22526407 Nov 17 19:23 api-orders.jar
-rw-rw-r-- 1 akira akira 22516540 Nov 17 19:24 api-person.jar
-rw-rw-r-- 1 akira akira 22517859 Nov 17 19:26 api-products.jar
```

Cada aplicativo es un componente jar de springboot cada uno ocupa un puerto

#### API-PERSON

```sh
$ java -jar api-person.jar 
```

```sh

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.5)

2024-11-17T19:31:16.172-05:00  INFO 62557 --- [api-person] [           main] e.e.u.apiperson.ApiPersonApplication     : Starting ApiPersonApplication v0.0.1-SNAPSHOT using Java 17.0.12 with PID 62557 (/SEMANA_7/components/api-person.jar started by akira in /SEMANA_7/components)
2024-11-17T19:31:16.176-05:00  INFO 62557 --- [api-person] [           main] e.e.u.apiperson.ApiPersonApplication     : No active profile set, falling back to 1 default profile: "default"
2024-11-17T19:31:17.243-05:00  INFO 62557 --- [api-person] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8081 (http)
2024-11-17T19:31:17.256-05:00  INFO 62557 --- [api-person] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-11-17T19:31:17.257-05:00  INFO 62557 --- [api-person] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.31]
2024-11-17T19:31:17.304-05:00  INFO 62557 --- [api-person] [           main] o.a.c.c.C.[.[localhost].[/api-person]    : Initializing Spring embedded WebApplicationContext
2024-11-17T19:31:17.305-05:00  INFO 62557 --- [api-person] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1054 ms
2024-11-17T19:31:17.988-05:00  INFO 62557 --- [api-person] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8081 (http) with context path '/api-person'
2024-11-17T19:31:18.008-05:00  INFO 62557 --- [api-person] [           main] e.e.u.apiperson.ApiPersonApplication     : Started ApiPersonApplication in 2.291 seconds (process running for 2.748)
```

#### API-PRODUCTS

```sh
java -jar api-products.jar 
```

```sh
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.5)

2024-11-17T19:34:43.665-05:00  INFO 62803 --- [api-products] [           main] e.e.u.a.ApiProductsApplication           : Starting ApiProductsApplication v0.0.1-SNAPSHOT using Java 17.0.12 with PID 62803 (/SEMANA_7/components/api-products.jar started by akira in /SEMANA_7/components)
2024-11-17T19:34:43.669-05:00  INFO 62803 --- [api-products] [           main] e.e.u.a.ApiProductsApplication           : No active profile set, falling back to 1 default profile: "default"
2024-11-17T19:34:44.884-05:00  INFO 62803 --- [api-products] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8082 (http)
2024-11-17T19:34:44.896-05:00  INFO 62803 --- [api-products] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-11-17T19:34:44.897-05:00  INFO 62803 --- [api-products] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.31]
2024-11-17T19:34:44.949-05:00  INFO 62803 --- [api-products] [           main] o.a.c.c.C.[.[localhost].[/api-products]  : Initializing Spring embedded WebApplicationContext
2024-11-17T19:34:44.951-05:00  INFO 62803 --- [api-products] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1203 ms
2024-11-17T19:34:45.398-05:00  INFO 62803 --- [api-products] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8082 (http) with context path '/api-products'
2024-11-17T19:34:45.417-05:00  INFO 62803 --- [api-products] [           main] e.e.u.a.ApiProductsApplication           : Started ApiProductsApplication in 2.224 seconds (process running for 2.704)
```

#### API-ORDERS

```sh
java -jar api-orders.jar 
```

```sh
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.5)

2024-11-17 19:37:13.487-[]-[INFO]-[api-orders]-org.springframework.boot.StartupInfoLogger-logStarting:50 : Starting ApiOrdersApplication v0.0.1-SNAPSHOT using Java 17.0.12 with PID 62956 (/SEMANA_7/components/api-orders.jar started by akira in /SEMANA_7/components)
2024-11-17 19:37:13.489-[]-[DEBUG]-[api-orders]-org.springframework.boot.StartupInfoLogger-logStarting:51 : Running with Spring Boot v3.3.5, Spring v6.1.14
2024-11-17 19:37:13.490-[]-[INFO]-[api-orders]-org.springframework.boot.SpringApplication-logStartupProfileInfo:654 : No active profile set, falling back to 1 default profile: "default"
2024-11-17 19:37:14.497-[]-[INFO]-[api-orders]-org.springframework.boot.web.embedded.tomcat.TomcatWebServer-initialize:111 : Tomcat initialized with port 8083 (http)
2024-11-17 19:37:14.508-[]-[INFO]-[api-orders]-org.apache.juli.logging.DirectJDKLog-log:173 : Initializing ProtocolHandler ["http-nio-8083"]
2024-11-17 19:37:14.514-[]-[INFO]-[api-orders]-org.apache.juli.logging.DirectJDKLog-log:173 : Starting service [Tomcat]
2024-11-17 19:37:14.514-[]-[INFO]-[api-orders]-org.apache.juli.logging.DirectJDKLog-log:173 : Starting Servlet engine: [Apache Tomcat/10.1.31]
2024-11-17 19:37:14.561-[]-[INFO]-[api-orders]-org.apache.juli.logging.DirectJDKLog-log:173 : Initializing Spring embedded WebApplicationContext
2024-11-17 19:37:14.562-[]-[INFO]-[api-orders]-org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext-prepareWebApplicationContext:296 : Root WebApplicationContext: initialization completed in 1009 ms
2024-11-17 19:37:14.976-[]-[INFO]-[api-orders]-org.apache.juli.logging.DirectJDKLog-log:173 : Starting ProtocolHandler ["http-nio-8083"]
2024-11-17 19:37:14.995-[]-[INFO]-[api-orders]-org.springframework.boot.web.embedded.tomcat.TomcatWebServer-start:243 : Tomcat started on port 8083 (http) with context path '/api-orders'
2024-11-17 19:37:15.012-[]-[INFO]-[api-orders]-org.springframework.boot.StartupInfoLogger-logStarted:56 : Started ApiOrdersApplication in 2.115 seconds (process running for 2.538)
```

#### API-GATEWAY

java -jar api-gateway.jar

```sh
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.5)

2024-11-17 19:38:49.176-[]-[INFO]-[api-gateway]-org.springframework.boot.StartupInfoLogger-logStarting:50 : Starting ApiGatewayApplication v1.0 using Java 17.0.12 with PID 63099 (/home/akira/Documents/UISRAEL/INGENIERIA_SOFTWARE_II/SEMANA_7/components/api-gateway.jar started by akira in /home/akira/Documents/UISRAEL/INGENIERIA_SOFTWARE_II/SEMANA_7/components)
2024-11-17 19:38:49.179-[]-[DEBUG]-[api-gateway]-org.springframework.boot.StartupInfoLogger-logStarting:51 : Running with Spring Boot v3.3.5, Spring v6.1.14
2024-11-17 19:38:49.180-[]-[INFO]-[api-gateway]-org.springframework.boot.SpringApplication-logStartupProfileInfo:654 : No active profile set, falling back to 1 default profile: "default"
2024-11-17 19:38:50.233-[]-[INFO]-[api-gateway]-org.springframework.boot.web.embedded.tomcat.TomcatWebServer-initialize:111 : Tomcat initialized with port 8080 (http)
2024-11-17 19:38:50.249-[]-[INFO]-[api-gateway]-org.apache.juli.logging.DirectJDKLog-log:173 : Initializing ProtocolHandler ["http-nio-8080"]
2024-11-17 19:38:50.256-[]-[INFO]-[api-gateway]-org.apache.juli.logging.DirectJDKLog-log:173 : Starting service [Tomcat]
2024-11-17 19:38:50.256-[]-[INFO]-[api-gateway]-org.apache.juli.logging.DirectJDKLog-log:173 : Starting Servlet engine: [Apache Tomcat/10.1.31]
2024-11-17 19:38:50.318-[]-[INFO]-[api-gateway]-org.apache.juli.logging.DirectJDKLog-log:173 : Initializing Spring embedded WebApplicationContext
2024-11-17 19:38:50.320-[]-[INFO]-[api-gateway]-org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext-prepareWebApplicationContext:296 : Root WebApplicationContext: initialization completed in 1059 ms
2024-11-17 19:38:51.111-[]-[INFO]-[api-gateway]-org.apache.juli.logging.DirectJDKLog-log:173 : Starting ProtocolHandler ["http-nio-8080"]
2024-11-17 19:38:51.127-[]-[INFO]-[api-gateway]-org.springframework.boot.web.embedded.tomcat.TomcatWebServer-start:243 : Tomcat started on port 8080 (http) with context path '/api-gateway'
2024-11-17 19:38:51.145-[]-[INFO]-[api-gateway]-org.springframework.boot.StartupInfoLogger-logStarted:56 : Started ApiGatewayApplication in 2.538 seconds (process running for 3.025)
```

## **Flujo de prueba**

  
Para este caso de prueba se realiza peticiones vía CURL para que sean accesibles a todo público. se deben ejecutar en una terminal de la siguiente manera.

#### **Obtener persona**

```sh
curl --request GET \
  --url http://localhost:8080/api-gateway/v1/person \
  --header 'User-Agent: insomnia/10.1.1'
```

```json
{
	"code": 200,
	"message": "OK",
	"data": {
		"506-85-2953": {
			"firstName": "Marcell",
			"lastName": "Braun",
			"dni": "506-85-2953"
		},
		"348-86-0175": {
			"firstName": "Virginia",
			"lastName": "Buckridge",
			"dni": "348-86-0175"
		}
		.........
		
		"484-20-9035": {
			"firstName": "Fredda",
			"lastName": "Beahan",
			"dni": "484-20-9035"
		}
	}
}
```

#### Seleccionar Persona

```sh
curl --request GET \
  --url http://localhost:8080/api-gateway/v1/person/506-85-2953 \
  --header 'User-Agent: insomnia/10.1.1'
```

```json
{
	"code": 200,
	"message": "OK",
	"data": {
		"firstName": "Marcell",
		"lastName": "Braun",
		"dni": "506-85-2953"
	}
}
```

#### Obtener Productos

```sh
curl --request GET \
  --url http://localhost:8080/api-gateway/v1/product \
  --header 'User-Agent: insomnia/10.1.1'
```

```json
{
	"b9ac7533-e454-42cc-9345-5ed6dcc645ae": {
		"id": "b9ac7533-e454-42cc-9345-5ed6dcc645ae",
		"type": "ENTRADAS",
		"name": "Ensalada César",
		"cost": 5.91,
		"stock": 0
	},
	"70f7ed2a-f633-4af7-9f7c-49bdc7f47500": {
		"id": "70f7ed2a-f633-4af7-9f7c-49bdc7f47500",
		"type": "PLATO_FUERTE",
		"name": "Asado de Cordero",
		"cost": 8.02,
		"stock": 100
	},
	............
	
	"0563acd7-330b-43ee-87e1-abc32f27c5f5": {
		"id": "0563acd7-330b-43ee-87e1-abc32f27c5f5",
		"type": "PLATO_FUERTE",
		"name": "Paella",
		"cost": 4.53,
		"stock": 100
	}
}
```

#### Seleccionar Producto

```sh
curl --request GET \
  --url http://localhost:8080/api-gateway/v1/product/b9ac7533-e454-42cc-9345-5ed6dcc645ae \
  --header 'User-Agent: insomnia/10.1.1'
```

```json
{
	"code": 200,
	"message": "OK",
	"data":{
		"id": "b9ac7533-e454-42cc-9345-5ed6dcc645ae",
		"type": "ENTRADAS",
		"name": "Ensalada César",
		"cost": 5.91,
		"stock": 0
	}
}
```

#### Visualizar Ordenes

```sh
curl --request GET \
 --url http://localhost:8080/api-gateway/v1/order \
 --header 'User-Agent: insomnia/10.1.1'
```

```json
{
	"code": 200,
	"message": "OK",
	"data":{
	"0000000001": {
			"orderId": "0000000001",
			"product": null,
			"client": null,
			"state": "pending"
		},
		"0000000002": {
			"orderId": "0000000002",
			"product": null,
			"client": null,
			"state": "pending"
		}
	}
}
```

#### Crear una orden

Para ello creamos un request body con un cliente y un platillo existente

```sh
curl --request POST \
  --url http://localhost:8080/api-gateway/v1/order \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/10.1.1' \
  --data '{
	"product": {
		"id": "b9ac7533-e454-42cc-9345-5ed6dcc645ae",
		"type": "ENTRADAS",
		"name": "Ensalada César",
		"cost": 5.91,
		"stock": 0
	},
	"client": {
		"firstName": "Marcell",
		"lastName": "Braun",
		"dni": "506-85-2953"
	},
	"state": "pending"
}'
```

```json
{
	"code": 201,
	"message": "Created",
	"data": {
		"orderId": "ec0eb5b2-ec9f-4e01-9fb7-9913578c0a33",
		"product": {
			"type": "ENTRADAS",
			"name": "Ensalada César",
			"cost": 5.91,
			"stock": 0
		},
		"client": {
			"firstName": "Marcell",
			"lastName": "Braun",
			"dni": "506-85-2953"
		},
		"state": "pending"
	}
}
```

#### Actualizar el estado una orden a entregada

```sh
curl --request PATCH \
  --url http://localhost:8080/api-gateway/v1/order/ec0eb5b2-ec9f-4e01-9fb7-9913578c0a33 \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/10.1.1' \
  --data '{
	"state": "delivered"
}'
```

```json
{
	"code": 200,
	"message": "OK",
	"data": {
		"orderId": "ec0eb5b2-ec9f-4e01-9fb7-9913578c0a33",
		"product": {
			"type": "ENTRADAS",
			"name": "Ensalada César",
			"cost": 5.91,
			"stock": 0
		},
		"client": {
			"firstName": "Marcell",
			"lastName": "Braun",
			"dni": "506-85-2953"
		},
		"state": "delivered"
	}
}
```
