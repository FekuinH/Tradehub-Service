# Tradehub Service

## Descripción
Tradehub Service es una API REST desarrollada para gestionar comitentes y mercados, permitiendo la creación, actualización, y eliminación de estos, así como la asociaciones entre ellos.
## Configuración Local
Para ejecutar este proyecto localmente, sigue estos pasos:

1. Clona el repositorio en tu máquina local:
`git clone https://github.com/FekuinH/Tradehub-Service.git`
2. Navega al directorio del proyecto:
`cd Tradehub-Service`
3. Compilar el proyecto:
`mvn clean install -U`
4. Configurar variables de entorno:
   `SERVER_CONTEXT_PATH (default: /api)
   SERVER_PORT (default: 8080)
   DB_HOST (default: localhost)
   DB_PORT (default: 5432)
   DB_NAME (default: main_db)
   DB_USERNAME (default: postgres)
   DB_PASSWORD (default: 8787)`
5. Ejecuta el proyecto con Maven Wrapper: ./mvnw spring-boot:run


## Uso
Una vez que el servicio esté corriendo, podrás acceder a los endpoints definidos en `ClientController` y `MarketController` para realizar operaciones CRUD sobre las entidades Comitente y Mercado.

## Swagger:
Al levantar el servicio se genera la documentación swagger en:
`http://{host}:{puerto}/api/swagger-ui/index.html#/`


