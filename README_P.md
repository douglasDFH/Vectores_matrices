 ESTRUCTURA GENERAL

  1. Herencia (Línea 9)

  public class FormularioArreglos extends JFrame
  - extends JFrame: La clase hereda de JFrame (ventana principal de Swing)
  - Esto convierte a FormularioArreglos en una ventana GUI

  ---
  2. Atributos (Líneas 10-16)

  Lógica de Negocio:

  - NegocioArreglos negocio → Conexión con la capa de lógica

  Componentes de Tablas:

  - JTable tablaVector, tablaMatriz → Tablas para mostrar datos
  - DefaultTableModel modelVector, modelMatriz → Modelos de datos de las tablas

  Campos de Texto:

  - JTextField txtTamVector, txtFilas, txtColumnas → Para ingresar dimensiones
  - JTextField txtMin, txtMax → Para rangos aleatorios

  Áreas de Resultados:

  - JTextArea areaResultadosVectores → Muestra resultados de vectores
  - JTextArea areaResultadosMatrices → Muestra resultados de matrices

  Contenedor de Pestañas:

  - JTabbedPane tabbedPane → Crea pestañas (Vectores y Matrices)

  ---
  3. Constructor (Líneas 18-25)

  public FormularioArreglos() {
      negocio = new NegocioArreglos();        // Crea objeto de lógica de negocio
      initComponents();                        // Construye la interfaz
      setTitle("...");                         // Título de la ventana
      setDefaultCloseOperation(EXIT_ON_CLOSE); // Cerrar al dar X
      setSize(1000, 750);                      // Tamaño ventana
      setLocationRelativeTo(null);             // Centrar en pantalla
  }

  ---
  4. Métodos de Construcción de Interfaz

  initComponents() - Líneas 27-38

  - Crea el JTabbedPane (contenedor de pestañas)
  - Agrega 2 pestañas: "Vectores" y "Matrices"

  crearPanelVector() - Líneas 40-114

  Construye toda la interfaz de la pestaña VECTORES:

  Componentes:
  1. Panel de controles (líneas 44-72):
    - Campo de texto para tamaño
    - Botón "Crear Vector"
    - Campos Min/Max para números aleatorios
    - Botón "Llenar Aleatorio"
  2. Tabla (líneas 75-77):
    - Muestra el vector con 2 columnas: Índice y Valor
  3. Panel de operaciones (líneas 80-95):
    - Botón "Sumar Elementos"
    - Botón "Contar Primos"
    - Botón "Editar Valor"
    - Botón "Eliminar Elemento"
  4. Área de resultados (líneas 98-102):
    - Muestra los resultados de las operaciones

  Layout (organización):
  ┌─────────────────────────────────────┐
  │ NORTH: Panel Controles              │
  │  [Tamaño: __] [Crear]              │
  │  [Min:__] [Max:__] [Llenar]        │
  ├─────────────────────────────────────┤
  │ CENTER: Tabla + Botones             │
  │  ┌─────────────────────────────┐   │
  │  │ Tabla Vector                │   │
  │  └─────────────────────────────┘   │
  │  [Sumar] [Primos] [Editar] [X]    │
  ├─────────────────────────────────────┤
  │ SOUTH: Área Resultados              │
  │  >>> Resultados...                  │
  └─────────────────────────────────────┘

  crearPanelMatriz() - Líneas 116-185

  Similar a crearPanelVector() pero para MATRICES:
  - Campos: Filas y Columnas
  - Tabla bidimensional
  - Botones adicionales: "Eliminar Fila" y "Eliminar Columna"

  ---
  5. Métodos de Eventos (Action Listeners)

  Cada botón tiene un método asociado que se ejecuta al hacer clic:

  Para Vectores:

  crearVector() - Líneas 189-202
  1. Lee el texto del campo txtTamVector
  2. Convierte a número entero
  3. Valida que sea > 0
  4. Llama a negocio.crearVector(tamaño)
  5. Actualiza la tabla
  6. Muestra mensaje de éxito

  llenarVectorAleatorio() - Líneas 204-218
  1. Lee valores Min y Max
  2. Valida que Min < Max
  3. Llama a negocio.llenarVectorAleatorio()
  4. Actualiza la tabla

  sumarVector() - Líneas 220-223
  1. Llama a negocio.sumarVector()
  2. Muestra el resultado en el área de texto

  contarPrimosVector() - Líneas 225-234
  1. Cuenta primos con negocio.contarPrimosVector()
  2. Obtiene valores con negocio.obtenerPrimosVector()
  3. Muestra cantidad y lista de primos

  editarValorVector() - Líneas 236-257
  1. Obtiene la fila seleccionada de la tabla
  2. Muestra diálogo para ingresar nuevo valor
  3. Llama a negocio.reemplazarElementoVector()
  4. Actualiza la tabla

  eliminarElementoVector() - Líneas 259-279
  1. Obtiene fila seleccionada
  2. Muestra diálogo de confirmación
  3. Si confirma: llama a negocio.eliminarElementoVector()
  4. Actualiza la tabla

  actualizarTablaVector() - Líneas 281-289
  1. Limpia la tabla (setRowCount(0))
  2. Obtiene el vector desde negocio.getVector()
  3. Recorre el vector y agrega cada elemento a la tabla

  Para Matrices (Líneas 293-436)

  - Similar a los métodos de vectores
  - Métodos adicionales para eliminar filas y columnas
  - actualizarTablaMatriz() más complejo porque maneja 2 dimensiones

  ---
  6. Métodos Auxiliares (Líneas 438-452)

  mostrarResultadoVectores() - Líneas 440-443
  - Agrega mensaje al área de resultados de vectores
  - Mueve el scroll al final (setCaretPosition)

  mostrarResultadoMatrices() - Líneas 445-448
  - Similar pero para el área de matrices

  mostrarError() - Líneas 450-452
  - Muestra un cuadro de diálogo de error

  ---
  FLUJO DE INTERACCIÓN

  Usuario hace clic en botón
           ↓
  ActionListener captura el evento
           ↓
  Método asociado se ejecuta (ej: crearVector)
           ↓
  Valida datos ingresados
           ↓
  Llama al método de NegocioArreglos (lógica)
           ↓
  Actualiza la tabla con nuevos datos
           ↓
  Muestra resultado en área de texto

  ---
  CONCEPTOS CLAVE DE SWING

  Layouts (Administradores de diseño):

  - BorderLayout: Divide en 5 zonas (NORTH, SOUTH, EAST, WEST, CENTER)
  - FlowLayout: Coloca componentes en fila de izquierda a derecha
  - GridLayout: Organiza en cuadrícula (filas x columnas)

  Event Listeners:

  btnCrearVector.addActionListener(this::crearVector);
  - Asocia el botón con el método crearVector
  - this:: es una referencia a método (method reference)

  JOptionPane:

  - showInputDialog(): Pide al usuario ingresar texto
  - showConfirmDialog(): Pide confirmación (Sí/No)
  - showMessageDialog(): Muestra un mensaje

  Try-Catch:

  try {
      int tamaño = Integer.parseInt(txtTamVector.getText());
  } catch (NumberFormatException ex) {
      mostrarError("Ingrese un tamaño válido");
  }
  - Maneja errores cuando el usuario ingresa texto no numérico

  ---
  RESUMEN VISUAL DE LA ARQUITECTURA

  FormularioArreglos (GUI)
  │
  ├── Pestaña VECTORES
  │   ├── Panel Controles (crear, llenar)
  │   ├── Tabla (mostrar datos)
  │   ├── Panel Operaciones (botones)
  │   └── Área Resultados
  │
  ├── Pestaña MATRICES
  │   ├── Panel Controles
  │   ├── Tabla 2D
  │   ├── Panel Operaciones
  │   └── Área Resultados
  │
  └── Métodos que conectan con NegocioArreglos
      - crearVector() → negocio.crearVector()
      - sumarVector() → negocio.sumarVector()
      - etc.