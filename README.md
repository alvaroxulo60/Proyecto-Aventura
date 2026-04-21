# 🚀 Proyecto: The history of best witcher of the world

**Miembros del Equipo:**
* Álvaro Rodríguez Esparragosa
* Carlos Simoes Sequera

---

## 📖 Nuestra Historia

**Temática del Juego:** Escape / Misterio en el Instituto.

**Premisa:**
> Empiezas en una aldea tranquila, Kael el Comerciante, el cual conoces muy bien, muy amable te ofrece sin coste una poción misteriosa, según él esa poción te volverá el mejor alumno de la academia de la luz y la sombra, te llevas la poción con gusto y mientras das un paseo te la tomas para volverte el mejor de todos.
A los segundos después de tomarla te empiezas a sentir mareado y se te nubla la vista hasta que finalmente te desplomas en el suelo.
Te despiertas en un lugar familiar, no sabes como llegaste a ahí, ni cuánto tiempo llevas ahí.

**Objetivo:**
Descubrir la manera de salir de ese extraño lugar y descubrir quién te ha hecho esto.

---

## ⚙️ Estado del Proyecto (Versión Actual)

El proyecto ha evolucionado de su versión procedural básica a un sistema robusto utilizando **Programación Orientada a Objetos (POO)**, **Colecciones** y **Persistencia de Datos**.

**Funcionalidad del Núcleo:**
* Arquitectura basada en POO con herencia e interfaces (`Inventariable`, `Combinable`, `Abrible`, `Leible`).
* Navegación y mapeo de habitaciones dinámico mediante `Map` y `List`.
* Sistema de inventario avanzado para el jugador y gestión de objetos dinámicos.
* Mecánicas complejas: abrir contenedores con llaves, leer notas, y combinación de objetos (ej. Llave Especial + Libro de Hechizos).
* Menú principal interactivo y persistencia de datos (Guardar, Cargar y Borrar Partidas).
* Sistema de trazabilidad profesional de errores y eventos mediante logs (`SLF4J/Logback`).
* **Comandos implementados:** `ir`, `mirar`, `examinar`, `coger objeto`, `inventario`, `abrir`, `combinar`, `ayuda`, `guardar partida` y `salir`.

**Tecnologías:**
* Java 16+ (Uso de *Pattern Matching*)
* Programación Orientada a Objetos (POO)
* Estructuras de datos (HashMap, ArrayList)
* Excepciones personalizadas
* Gestión de Ficheros (I/O)
* SLF4J / Logback (Logging)
* Git

---

## 🔜 Próximas Fases

* **Fase GUI/BBDD:** Crear una interfaz gráfica de usuario para sustituir la consola y conectar a una base de datos para guardar progresos globales, perfiles o logros.
* **Expansión de Historia:** Añadir nuevos puzles, NPCs y mecánicas de combate o magia.
