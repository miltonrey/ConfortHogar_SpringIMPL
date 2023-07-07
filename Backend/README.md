# ConfortHogar_SpringIMPL
 "Confort Hogar" es un proyecto en Spring basado en BPMN que ofrece un endpoint externo. Permite a los usuarios interactuar con flujo y recibir notificaciones. La arquitectura escalable de Spring garantiza una integración fluida con otros sistemas.

## Requisitos
- Java 11 o superior

## Instalación
- Primero descargue el repositorio, click en el botón verde "Code" y luego en "Download ZIP". Descomprima el archivo y ábralo en su IDE de preferencia.
- Asegúrese de tener instalado Java 11 o superior.
- Ejecute el proyecto comenzando con "Camunda" y ejecutando \src\main\java\com\tekkytalks\camunda\CamundaApplication.java
- Posteriormente ejecute el proyecto "camunda_external_task" y ejecutando \src\main\java\org\camunda\platform\runtime\example\Application.java
- Una vez que el proyecto se haya ejecutado, abra su navegador y vaya a la siguiente dirección: http://localhost:8090
- El usuario y la contraseña son "demo" y "demo" respectivamente.
- Una vez que haya iniciado sesión, vaya a la pestaña "Tasklist", haga clic en "Start process" y ejecute el proceso "loanprocess" (En el input "Tipos de madera", las opciones son: duras, blandas, silvestres).
- Hay un tiempo de espera de 10 segundos para poder visualizar el proceso.
- Ahora ejecute el front de la aplicacion para poder visualizar las tareas (ver README del frontend)
 


