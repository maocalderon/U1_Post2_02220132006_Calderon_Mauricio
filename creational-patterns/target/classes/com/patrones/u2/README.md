# Taller de Patrones Creacionales - U2
**Estudiante:** Mauricio calderon bayona 
**Institución:** Universidad de Santander (UDES)

## Analisis de Patrones Implementados

### 1. Singleton (Variante Enum)
[cite_start]Se implemento en la clase NotificationLogger utilizando la variante enum[cite: 61, 74]. [cite_start]Este patron resuelve el problema de tener multiples instancias de un log dispersas por la aplicacion, garantizando que todos los componentes escriban en un unico historial[cite: 48, 70]. [cite_start]Al usar un enum, la instancia es thread-safe por diseño de la JVM, lo que asegura la integridad de los datos en entornos concurrentes[cite: 74, 75].

### 2. Factory Method (Registro Dinamico)
[cite_start]Se implemento en la clase NotifierFactory utilizando un Map con Supplier[cite: 170, 181]. [cite_start]Este patron permite crear diferentes tipos de notificadores (Email, SMS, Push) sin que el codigo cliente conozca o dependa de las clases concretas, trabajando solo a traves de la interfaz Notifier[cite: 106, 110, 178]. 

[cite_start]Gracias al registro dinamico con el metodo register(), el sistema cumple con el Principio Abierto/Cerrado (OCP), permitiendo añadir nuevos canales de comunicacion (como se demostro con Slack) en tiempo de ejecucion sin necesidad de modificar o recompilar la fabrica existente[cite: 171, 192, 194].

## Evidencia de Ejecucion

[cite_start]El proyecto compila correctamente mediante Maven y la clase Main demuestra la funcionalidad de ambos patrones[cite: 226, 271].

* [cite_start]**Prueba Singleton:** Se verifica que logger1 == logger2 retorna "true", confirmando la instancia unica[cite: 238, 271].
* [cite_start]**Prueba Factory:** Se crean y envian notificaciones exitosamente para Email, SMS y Push[cite: 239, 243, 245].
* [cite_start]**Prueba OCP:** Se registra un nuevo notificador para Slack dinamicamente y se envia un mensaje sin errores[cite: 248, 265].

(Aqui debes pegar la captura de pantalla de tu terminal con la salida del programa)