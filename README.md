# Tradehub Service

## Descripción
Tradehub Service es una API REST desarrollada para gestionar comitentes y mercados, permitiendo la creación, actualización, y eliminación de estos, así como la asociación entre comitentes y múltiples mercados. Creado con Spring Boot, este proyecto cumple con las especificaciones detalladas en el desafío propuesto, asegurando que no haya comitentes repetidos y que cada comitente pueda estar relacionado con uno o más mercados.

## Configuración Local
Para ejecutar este proyecto localmente, sigue estos pasos:

1. Clona el repositorio en tu máquina local:
git clone https://github.com/FekuinH/Tradehub-Service.git

2. Navega al directorio del proyecto:
cd Tradehub-Service

3. Ejecuta el proyecto con Maven Wrapper:
./mvnw spring-boot:run



## Uso
Una vez que el servicio esté corriendo, podrás acceder a los endpoints definidos en `ClientController` y `MarketController` para realizar operaciones CRUD sobre las entidades Comitente y Mercado.

## Contribuciones
Las contribuciones son bienvenidas. Para contribuir, por favor haz un fork del repositorio, crea tu feature branch, commit tus cambios, y envía un pull request.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT - ver el archivo LICENSE.md para más detalles.
