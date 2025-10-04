¡Bienvenido al Check-in Aula!

Este es un proyecto sencillo para registrar inscripciones al inicio de clases. Funciona tanto en la consola como con una interfaz gráfica básica si lo ejecutas con --gui.

Requisitos

Necesitas tener Java 17 o superior (asegúrate de que el JDK esté instalado y configurado en tu PATH).

También necesitarás una consola o terminal.

Compilación

Desde la carpeta raíz del proyecto, ejecuta:

javac -d out $(find src -name "*.java")

Esto compilará todos los archivos en la carpeta out/.

Ejecución en consola (modo predeterminado)
java -cp out edu.cerp.checkin.App

Aquí tienes un ejemplo del menú en consola:

== Check-in Aula (Consola) ==
1) Registrar 2) Listar 3) Buscar 4) Resumen 0) Salir

Ejecución con GUI
java -cp out edu.cerp.checkin.App --gui

Al hacerlo, se abrirá una ventana con un formulario simple para registrar inscripciones y ver la lista de registros.

Estructura del proyecto
src/
└─ edu/cerp/checkin/
├─ model/
│ └─ Inscripcion.java
├─ logic/
│ └─ SesionService.java
├─ console/
│ └─ MainConsole.java
├─ ui/
│ └─ CheckInGUI.java
└─ App.java

🧾 Notas

Los datos se manejan en memoria (no se utilizan archivos ni bases de datos).

Incluye datos de prueba precargados al iniciar.

Se permiten duplicados y no hay validaciones complejas.

Autor

Entrega individual – Check-in Aula

Resumen: Este proyecto presenta un sistema para registrar inscripciones al inicio de clases. Permite añadir alumnos con su nombre, documento y curso, y guarda automáticamente la hora de inscripción. Puedes listar todas las inscripciones, buscar por nombre o documento, y obtener un resumen por curso con la cantidad de alumnos inscritos. Funciona a través de una consola con un menú interactivo, y también cuenta con una interfaz gráfica mínima, donde puedes completar un formulario y ver la lista de registros. Incluye datos de prueba precargados para que puedas ver cómo funciona desde el inicio. Todo esto se gestiona en memoria, sin necesidad de guardar archivos ni bases de datos, mostrando solo la información en el momento de ejecución.

# Check-in Aula (Registro de Inscripciones)

Este proyecto implementa un registro simple de inscripciones al inicio de clase, con una lógica de negocio en memoria y dos interfaces de usuario: Consola (por defecto) y Gráfica (GUI) activada con el argumento `--gui`.

---

## Compilación y Ejecución

El proyecto sigue la estructura estándar de paquetes y se compila usando `javac`.

### 1. Compilación

Ejecute este comando desde la raíz del proyecto (donde se encuentra la carpeta `src`):

```bash
javac -d out $(find src -name "*.java")
