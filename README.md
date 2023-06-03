# Tesseract
Aplicación Web Spring Boot en la que los usuarios pueden compartir recursos 3D e imágenes, a modo de banco de recursos para videojuegos, ilustración o impresión 3d.

## Desarrollo en fases
La construción del proyecto completo se desarrolla en 4 fases.
  
  - **Fase 1**: Definición de entidades y funcionalidades del servicio interno.
  - **Fase 2**: Aplicación web con base de datos MySql en Local.
  - **Fase 3**: Seguridad y servicio interno.
  - **Fase 4**: Tolerancia a fallos, balanceo de carga.

## Fase 1
 - Entidades:
   + **User**: para registrarse en la aplcicación.
   + **Resource**: El elemento en cuestión que se comparte, fichero comprimido en .zip con el archivo o archivos del recurso. Además, se priporciona una imagen de previsualización del elemento.
   + **Comment**: Los usuarios pueden añadir comentarios a los recursos.

- La aplicación consta de 2 servicios:
   + **Web**: Relación entre entidades y el negocio, encargada de gestionar la base de datos y la interacción con la aplicación por medio del navegador. Para acceder a todas las funcionalidades proporcionadas por la aplicación, el usuario debe estar registrado. De lo contrario, solo se tendrá acceso limitado, basado en visualización de los recursos y de la web.
   + **Servicio Interno**: Recoge información proporcionada por la parte web por medio de una cola de mensajes implementada con RabbitMQ, con intención de notificar al dueño del recurso información sobre un nuevo comentario.


## Fase 2

En esta fase se implementa la persistencia de datos en una base de datos MySql. Además de su dependencia correspondiente se añadirán la de **Mustache** como motor de plantillas, para generar HTML, como otras dependencias de Spring boot.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/1361b628-1ea7-4f77-8362-0bbd8a08085e)

### Pantallas de la Web:

Nada mas acceder a la web, de momento escribiendo en el navegador localhost:8080, se nos presenta la pantalla de inicio, donde podremos accedes a los dos grupos principales de recursos, 2D y 3D. Además podremos registrarnos o bien iniciar sesión en caso de tener una cuenta. 

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/6075a176-d5dd-47be-845b-7332d892006a)



Optamos por registrarnos. En este caso se nos pide Nombre de usuario, contraseña e email.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/f55c710b-00c3-47d6-af75-90599c0f12fd)

Una vez registrados iniciamos sesión para poder ver el resto de funcionalidades implementadas.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/33ebc050-4ea7-4b7e-ac86-becf3e79ba0f)


Tras iniciar sesión podemos comprobar que en la barra de menú de la aplicación Aparece nuestro nombre de usuario y el botón para cerrar sesión.
![image](https://github.com/DaniCP909/Tesseract/assets/123632882/59e03b3a-3f9d-4cf6-9163-b5feafe7a3e1)

Si hacemos click sobre el nombre de usuario podemos acceder a una versión muy básica de nuestro perfil. En el podremos editar la información del mismo.
![image](https://github.com/DaniCP909/Tesseract/assets/123632882/901444e3-04af-4e94-8a7b-d9834828e03e)

Podemos volver a la pantalla de bienvenida y dirigirnos a todos los recursos disponibles clockando en el botón "Todos los recursos".

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/f7d8948e-492c-4db3-9a63-ebb1b3823d22)













### Generación de clave SSH para github

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/60faac96-25dd-4178-8a0f-d99a3ce418a3)

