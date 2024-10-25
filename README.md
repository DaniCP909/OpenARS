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

### Login

Se ha implementado un login para que los usuarios puedan acceder a la web, al igual que el registro para los que todavía no se han dado de alta en la aplicación. Además se han añadido sus respectivas plantillas así como la pantalla de error de login.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/cbfbfdfb-e21a-4ff7-a09e-1c70c49fe709)

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/7305f387-ab86-46c0-8289-444ad52169b3)

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/28028a72-b0a5-447d-ac51-290cc2372860)

### Pantallas públicas y privadas

![PANTALLAS](https://github.com/DaniCP909/Tesseract/assets/123632882/9bd4c95b-c0bb-4cb8-b326-6b95f77b066a)

Además, en el archivo WebSecurityConfig, se especifica el grado de acceso según los roles del usuario a las distintas URL's.

### HTTPS
Para implementar HTTPS en la aplicación web se ha generado un certificado SSL autofirmado. Este dió problemas, por lo tanto se usó el certificado que se proporciona en el proyecto de prueba de la Fase 3. En el momento de creación se añade al fichero keystore.jks.
En el archivo application.properties se especifica el fichero antriormente comentado, y se especifica el puerto 8443.

En la siguiente captura se puede ver el certificado desde el navegador, y a continuación podemos comprobar que se accede con la URL con HTTPS.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/e30e7172-64cd-4dee-8f5d-2be7f4152c42)

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/b012ea5c-6c44-4aa6-ab28-26528c5d28cb)
Al ser un certificado autofirmado, y no generado por una entidad autorizada, el sitio aparece como No Seguro.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/8ed11e88-ed84-4ae9-b6dc-bc5afea1837e)

#### CSRF

Para la parte HTTPS se han añadido unas clases qeu permitan el uso de tokens generados por "interceptor", a modo de salvaguarda básico contra ataques Cross Site Request Forgery.
A continuación se muestra la clase y cómo se reflejan en las plantillas HTML.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/ba1cf20e-560e-408b-b710-ba200767c56c)

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/b65de4c0-98f8-44bd-abcf-cf2acb9a6d8d)

### Servicio Interno y Mecanismo de comunicación

Se ha creado un nuevo proyecto Spring Boot llamado "Comment Service", el cual se encargará de procesar los comentarios añadidos a los recursos subidos por los usuarios, y enviará un mail al dueño del recurso a modo de "notificación".

Para comunicar ambos servicios se ha decidido implementar una Cola de Mensajes con RabbitMQ.
![ServicioInternoEsquema](https://github.com/DaniCP909/Tesseract/assets/123632882/db437245-a65d-48c8-90b3-5a13c5e7c97c)

En la pantalla "Recurso" encontramos varias opciones, entre ellas, en la parte inferior, un cuadro con los comentarios que dejan los usuarios en dicho recurso. Al escribir un comentario y enviarlo, el método controlador inyecta la información que deseamos del comentario en un objeto de la clase CommentDTO, la cual tambien tiene el otro proyecto. Este objeto CommentDTO es lo que se transformará en mensaje para la RabbitMQ, se lanzará a la cola, y el otro servicio consumirá el mensaje. Al recibirlo, este servicio se ha habilitado para que pueda mandar correos electrónicos, y por lo tanto, procesará el mensaje, obteniendo la informaci´pon pertinente, y enviará el correo al Mail del usuario dueño del recurso publicado.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/34a3de99-d541-4ae0-8da7-7bf0e0f0a2e5)

### Despliegue

Tenemos 4 partes: Web, Servicio interno, Base de datos y RabbitMQ.

La web lanza la base de datos, y para desplegar dicha web nos situamos en el directorio y ejecutamos el sihuiente comando:

```mvn spring-boot:run```

El servicio interno de manera similar, pero necesita que le pasemos como argumento el valor de la contraseña para acceder al correo como aplicación de terceros, el usuario está esecificado en el application.properties aunque podría hacerse lo mismo.

```mvn spring-boot:run -Dspring-boot.run.arguments=--spring.mail.password=password```

Además lanzamos la cola de mensajes de RabbitMQ en un contenedor docker con el siguiente mandato:

```docker run -p 5672:5672 -p 15672:15672 rabbitmq```

Así estarán lanzados los 2 servicios y la cola de mensajes y podremos acceder a ella.

### Navegación

Inicialmente entramos a la página "Greeting" o lo que sería home, y al no estar loggeados podemos acceder solo a "Todos los recursos", haciendo click en cualquier imagen de esta pantalla inicial, y despues podremos ver los recursos pero ninguna opcion de "Descargar", "Editar", "Eliminar" etc. Las funciones de un usuario no logueado son solo de lectura.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/7232fd3e-47b9-49b8-811d-1572bc04a112)

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/ad52807b-d6f6-4147-9e95-a394a63ea861)

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/a47086fd-06cf-4630-b276-4ad6cb90b4ca)

Podemos proceder a registrar un usuario con el que más tarde probaremos el loguin.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/6a7f47f6-1490-4643-8526-93dd8c9e670d)

Iniciamos sesión con este nuevo usuario.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/d2fa8b36-8074-49f2-850d-8f86da621acc)

Nos aparece el nombre del usuario loggeado. 

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/99b91bdb-5225-4f0e-bb6e-f8bde4a619fc)

Podemos hacer logout y nos devuelve a la página de inicio.

O podemos hacer click en el nombre y dirigirnos a nuestro perfil.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/2fc03170-d76f-4f12-a87b-f9e1cc8c3706)

Aquí podemos realizar la acción principal de la aplicación web, entre otras accciones.

Hacemos click en subir recurso y nos mostrará el formulario de subida.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/fc9f3a2e-bae6-4030-a234-26db7295879d)

Una vez aceptamos la subida nos redirecciona a la propia página del recurso.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/eaa15743-92a8-480e-8693-5b9afc5c616f)

Podemos editar el recurso 

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/ed92eeaa-ff65-42bf-87b1-c212e6b6c162)

Y podemos borrarlo.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/ef68399d-03f9-468a-9d0e-c136fada4596)

Manteniendo la sesión iniciada con este usuario podemos dirigirnos desde el perfil a "Inicio" -> "Todos los recursos" -> "Recurso"
Podemos bajar en la página y veremos un cuadro donde podremos escribir un comentario y probamos el servicio interno.
Recordamos que el mensaje se enviará al correo del dueño del recurso y le notificara quién y en cual de sus recursos se ha realizado.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/309b1bd7-fb33-49fb-b3d1-62d9ff2824fb)

El correo recibido es el siguiente:

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/5cb673b9-4233-457f-8e22-2f8985936a93)


Podemos volver a nuestro perfil y acceder a la sección "Todos nuestros comentarios" donde podremos borrarlos y editarlos.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/1f2aac2a-bd8b-4a0e-a3fe-92eaa8dc8d8b)

Por último iniciando sesion como admin, podemos acceder a la pantalla "All-users"

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/2de19ea4-2b7a-43c3-b4dd-8b783d89e768)



### Generación de clave SSH para github

En esta fase y en la siguiente se usado una máquina virtual limpia donde se instala Java (JDK), docker y MySQL.
Con el objetivo de trabajar más cómodo y poder realizar cambios en el IDE instalado en el ordenador normal con windows, y actualizar rápido los cambios en la máquina virtual, GitHub nos da la mejor solución.

Un paso necesario para ello es la generación de un SSH.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/60faac96-25dd-4178-8a0f-d99a3ce418a3)

## Fase 4
Queremos hacer nuestra aplicación tolerante a fallos, y para ello implementaremos:
  - Definición de estructura con Docker-compose
  - Balanceo de carga.
  - Algunas consultas de entidades cacheadas.
  - Sesión distribuida
  - Diagrama UML
  - Diagrama de la infraestructura
  - Video presentación de la aplicación

### Definición de estructura con Docker-compose
La aplicación completa se desplegará la aplicación con un Dockerfile en cada proyecto y definiendo la configuración de los servicios y el balanceador en el archivo Docker-compose, al cual uniremos con otro fichero haproxy. Se levantan los siguientes servicios:

  - Una instancia de la base de datos.
  - Dos instancias de la aplicación web.
  - Dos instancias de la rabbitMQ.
  - Dos instancias del servicio interno.
  - Una instancia del balanceador de carga

### Balanceo de carga

En este caso se ha optado por balancear la carga con Haproxy, contemplando también el caso de que uno de los nodos falle.
Para configurarlo lo definimos tanto como servicio dentro de Docker-compose, especificando la versión, la configuración de los puertos, y donde se encuentra el fichero haproxy.cfg. 

En este archivo haproxy es donde se configura mayormente el balanceador:
    + En la primera sección se definen aspectos generales del balanceador.
    + Definimos el frontend a continuación. Especificando el modo tcp y el backend por defecto bkopenars.
    + Definimos el backend, con dos servidores "s1" y "s2" cada uno seguido del nombre del servicio que sale como resultado de levantar docker-compose, un ejemplo sería una instancia de tesseract_openars_1, tesseract_comment_service1 o tesseract_loadbalancer_1.

Una vez levantado podremos observar el log de la aplicación completa con todo los servicios.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/04bc1be9-cfb4-425e-99f2-f21c778c2c10)

Podríamos abrir otro terminal y observar el log de un servicio en concreto de la siguiente forma

```docker logs tesseract_loadbalancer_1```


### Algunas consultas de entidades cacheadas

Las consultas que van a ser cacheadas son las de retorno de todos los elementos de las entidades Resource y Comment, es decir findAll(). Lo que invalidará la cache seran las operaciones de save() o guardado/actualización y deleteById().

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/cdb7e8d2-5373-4334-9451-45faafa21cb9)

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/16f7b24a-55ce-4b18-8b06-47fd4bbf57d3)

IInvalidación de la cache.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/e23ef166-1d0b-4efe-b1b6-e719e6499620)

En el application.java se define el bean de cache manager, y al final se añade otro bean que soluciona errores de los archivos de timpo imagen.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/1ed9e1fd-845e-484e-9da6-83aea0c8c4d9)

Por último tenemos el rest controler que define los métodos controladores para acceder a ambas caches desde su respectivas URL.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/de119b78-b428-4397-a60c-47be4a633e79)


El resultado de la cache tras haber accedido a todos los recursos es

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/afd31cb9-988b-45ce-a920-260f0678ac18)

Tras su invalidación

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/87db54dc-cd6b-4028-9d10-128a59900b10)


### Sesión distribuida

Para dotar a la aplicación de sesión distribuida se ha elegido implementar Sticky Session a través del uso de cookies por medio del haproxy.

Se define la cookie con el modo roundrobin y luego se especifica en cada servidor, en nuestro caso s1 y s2.

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/7ddb7712-5ae1-464f-9d79-900bebc667ca)

Me he encontrado con algunos errores a la hora de distribuir la sesión, en concreto con los tokens CSRF.


### Diagrama de la infraestructura

![image](https://github.com/DaniCP909/Tesseract/assets/123632882/8634be28-8393-4730-94f7-43a039d005fd)






