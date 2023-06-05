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

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/501dd951-f6a5-4167-aaf9-ecf6c08b5853)



Optamos por registrarnos. En este caso se nos pide Nombre de usuario, contraseña e email.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/f55c710b-00c3-47d6-af75-90599c0f12fd)

Comprobamos que se encuentra en la base de datos:

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/120e53f3-4557-45b0-a576-48eed85ac74d)

Una vez registrados iniciamos sesión para poder ver el resto de funcionalidades implementadas.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/33ebc050-4ea7-4b7e-ac86-becf3e79ba0f)


Tras iniciar sesión podemos comprobar que en la barra de menú de la aplicación Aparece nuestro nombre de usuario y el botón para cerrar sesión.
![image](https://github.com/DaniCP909/Tesseract/assets/123632882/59e03b3a-3f9d-4cf6-9163-b5feafe7a3e1)

Si hacemos click sobre el nombre de usuario podemos acceder a una versión muy básica de nuestro perfil. En el podremos editar la información del mismo.
![image](https://github.com/DaniCP909/Tesseract/assets/123632882/c766c3a3-2140-46ae-9f0b-fc453cde2eec)

Desde el perfil podemos Editar la información del usuarios.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/9e86f063-22e6-4081-a777-537bf74b2803)

De momento el borrado de usuarios solo esta accesible para el administrador. Accediendo a /all-resources obtenemos la lista de todos los usuarios registrados.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/ab6d1703-9b3e-4ddc-a56f-ebe53c5816de)

Al hacer click en borrar aparece la siguiente pantalla que nos indica que se ha borrado correctamente.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/83288cb3-43a6-4c14-8afe-0e5555fcc36b)

Ahora nos centraremos en la entidad recurso.
Desde el perfil podremos crear recursos que se asociarán directamente al usuario que lo crea. Hacemos click en "Subir recurso". Se nos muestra el siguiente formulario donde se nos piden los dos archivos necesarios para el recurso, la previsualización y el archivo en si.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/bfc72387-1c5b-4a10-9e35-b37d1d56cb0c)

Al crear el recurso se nos dirige a la pantalla del propio recurso.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/d073d1b1-3b0e-4f52-98c4-22a30d22085f)


Si nos dirigimos al perfil, aparece nuestro recurso subido. Este también es accesible desde la pantalla "Todos los recursos"
Cambiamos a User 1 que ya tiene varios recursos subidos a su perfil.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/40ea45b9-0ca8-4059-ab03-dbd7f7329c2d)

Si accedemos al recurso y el usuario dueño del recurso está loggeado, se mostrarán disponibles lo botones de editar y borrar. 

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/c2e27749-1a3b-470e-ab5e-eb36640395b1)

Si deseamos editar el recurso se nos mostrará una pantalla con el formulario de edición.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/25712436-656c-4114-8cbe-1a4487eee120)

Si optamos por borrar el recurso, del mismo modo que con usuario, se nos mostrará una pantalla que nos indica que la acción se ha realizado.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/bff8de93-7b07-414b-a2fc-4bea61b4e205)

Ahora nos enfocamos en la siguiente entidad, "Comentario".
Si nos dirigimos a cualquier recurso, en la parte inferior se muestra el tablón de comentarios de dicho recurso. 
Si añadimos un comentario, este se creará asociado al usuario que lo ha realizado, y asociado al recurso donde se ha escrito. 

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/53a5fb56-d6a6-4358-ab0a-5b39fc6dbd55)

Al aceptar se añadirá a la misma pantalla y a la base de datos. Lo comprobamos.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/32d915cf-6439-47f7-8471-410914361a1b)

Para editarlos y borrarlos debemos acceder a nuestro perfil. En el se nos muestra el boton "Todos mis comentarios"

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/ea0007fd-456a-4618-a3a7-8c102f020f48)

En este apartado se muestran todos los comentarios que el usuario ha hecho en la aplicación, y con cada comentario la opción de borrar y editar el mismo.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/cbcff48f-ee34-4cf3-89a4-9f323f319cc7)

Para editar también se mostrará el formulario de edición

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/926b838d-f7a0-4979-87ec-11a8d1e59d89)

Y al borrar se nos dirige a la pantalla que nos indica que se ha borrado correctamente.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/c0bf629b-fc60-4e7c-8a5d-7b81b31a2858)



## Fase 3













### Generación de clave SSH para github

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/60faac96-25dd-4178-8a0f-d99a3ce418a3)

