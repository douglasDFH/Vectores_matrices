 1. package negocio;

  ¿Qué es? Declaración del paquete

  Ubicación física: src/main/java/negocio/NegocioArreglos.java

  Propósito: Esta clase contiene toda la lógica de negocio (operaciones con vectores y matrices)

  ---
  2. import java.util.Random;

  ¿Qué importa? La clase Random del paquete estándar de Java

  ¿Para qué? Generar números aleatorios para llenar vectores y matrices

  Sin import tendrías que escribir: java.util.Random random = new java.util.Random();

  ---
  3. public class NegocioArreglos {

  Definición de la clase principal

  - public = Accesible desde cualquier parte (desde Main.java y FormularioArreglos.java)
  - NegocioArreglos = Nombre de la clase

  ---
  4. ATRIBUTOS (Variables de instancia)

  private int[] vector;
  private int[][] matriz;
  private Random random;

  private int[] vector;

  - private = Solo accesible dentro de esta clase (encapsulamiento)
  - int[] = Array (arreglo) de enteros unidimensional
  - vector = Nombre de la variable
  - Ejemplo: [5, 10, 15, 20]

  private int[][] matriz;

  - int[][] = Array bidimensional (matriz)
  - Ejemplo:
  [ [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9] ]

  private Random random;

  - Objeto para generar números aleatorios
  - Se usa en llenarVectorAleatorio() y llenarMatrizAleatoria()

  ---
  5. CONSTRUCTOR

  public NegocioArreglos() {
      this.random = new Random();
  }

  ¿Qué es un constructor? Un método especial que se ejecuta cuando creas un objeto con new

  Desglose:
  - public = Accesible desde otras clases
  - NegocioArreglos() = Mismo nombre que la clase (sin tipo de retorno)
  - this.random = new Random() = Inicializa el generador de números aleatorios

  Cuándo se ejecuta: Cuando en Main.java (a través de FormularioArreglos) se hace new NegocioArreglos()

  ---
  6. OPERACIONES CON VECTORES

  public void crearVector(int tamaño)

  public void crearVector(int tamaño) {
      this.vector = new int[tamaño];
  }

  Desglose:
  - public = Puede llamarse desde FormularioArreglos
  - void = No devuelve ningún valor
  - tamaño = Parámetro que indica cuántos elementos tendrá el vector
  - new int[tamaño] = Crea un array de enteros del tamaño especificado
  - this.vector = Asigna el array al atributo de la clase

  Ejemplo:
  crearVector(5);  // Crea: [0, 0, 0, 0, 0]

  ---
  public void llenarVectorAleatorio(int min, int max)

  public void llenarVectorAleatorio(int min, int max) {
      if (vector == null) return;  // Si no existe vector, salir

      for (int i = 0; i < vector.length; i++) {
          vector[i] = random.nextInt(max - min + 1) + min;
      }
  }

  Desglose línea por línea:

  if (vector == null) return;

  - Verifica que el vector exista
  - Si es null (no ha sido creado), sale del método

  for (int i = 0; i < vector.length; i++)

  - int i = 0 = Empieza en índice 0
  - i < vector.length = Condición: mientras i sea menor que el tamaño
  - i++ = Incrementa i en cada iteración

  vector[i] = random.nextInt(max - min + 1) + min;

  - random.nextInt(max - min + 1) = Genera número aleatorio entre 0 y (max-min)
  - + min = Ajusta el rango al mínimo deseado

  Ejemplo:
  llenarVectorAleatorio(10, 50);
  // Si min=10, max=50:
  // random.nextInt(41) genera 0-40
  // + 10 = rango final 10-50
  // Resultado: [23, 45, 12, 38, 50]

  ---
  public int sumarVector()

  public int sumarVector() {
      if (vector == null) return 0;

      int suma = 0;
      for (int valor : vector) {
          suma += valor;
      }
      return suma;
  }

  Desglose:

  int suma = 0;

  - Inicializa la variable acumuladora en 0

  for (int valor : vector)

  - For-each loop (bucle mejorado)
  - Lee: "Para cada valor dentro de vector"
  - Itera automáticamente por todos los elementos

  suma += valor;

  - Equivale a: suma = suma + valor
  - Acumula cada elemento en la variable suma

  return suma;

  - Devuelve el resultado final

  Ejemplo:
  vector = [10, 20, 30]
  sumarVector() → 10 + 20 + 30 = 60

  ---
  public int contarPrimosVector()

  public int contarPrimosVector() {
      if (vector == null) return 0;

      int contador = 0;
      for (int valor : vector) {
          if (esPrimo(valor)) {
              contador++;
          }
      }
      return contador;
  }

  Desglose:

  int contador = 0;

  - Variable para contar cuántos primos hay

  if (esPrimo(valor))

  - Llama al método privado esPrimo() (explicado después)
  - Si el valor es primo, devuelve true

  contador++;

  - Incrementa el contador en 1
  - Equivale a: contador = contador + 1

  Ejemplo:
  vector = [2, 4, 5, 9, 11]
  Primos: 2, 5, 11
  contarPrimosVector() → 3

  ---
  public String obtenerPrimosVector()

  public String obtenerPrimosVector() {
      if (vector == null) return "Vector no creado";

      StringBuilder primos = new StringBuilder();
      for (int valor : vector) {
          if (esPrimo(valor)) {
              if (primos.length() > 0) {
                  primos.append(", ");
              }
              primos.append(valor);
          }
      }

      return primos.length() > 0 ? primos.toString() : "No hay primos";
  }

  Desglose:

  StringBuilder primos = new StringBuilder();

  - Clase para construir Strings de forma eficiente
  - Mejor que concatenar con + en bucles

  if (primos.length() > 0)

  - Si ya hay primos agregados, añade una coma

  primos.append(", ");

  - Añade coma y espacio separador

  primos.append(valor);

  - Añade el número primo

  return primos.length() > 0 ? primos.toString() : "No hay primos";

  - Operador ternario: condición ? siVerdadero : siFalso
  - Si hay primos, devuelve el String
  - Si no hay primos, devuelve "No hay primos"

  Ejemplo:
  vector = [2, 4, 5, 9, 11]
  obtenerPrimosVector() → "2, 5, 11"

  ---
  public void reemplazarElementoVector(int posicion, int nuevoValor)

  public void reemplazarElementoVector(int posicion, int nuevoValor) {
      if (vector != null && posicion >= 0 && posicion < vector.length) {
          vector[posicion] = nuevoValor;
      }
  }

  Desglose:

  vector != null

  - Verifica que el vector exista

  posicion >= 0

  - La posición debe ser mayor o igual a 0

  posicion < vector.length

  - La posición debe estar dentro del rango del vector

  vector[posicion] = nuevoValor;

  - Reemplaza el valor en la posición indicada

  Ejemplo:
  vector = [10, 20, 30, 40]
  reemplazarElementoVector(2, 99);
  Resultado: [10, 20, 99, 40]

  ---
  public int[] eliminarElementoVector(int posicion)

  public int[] eliminarElementoVector(int posicion) {
      if (vector == null || posicion < 0 || posicion >= vector.length) {
          return vector;  // Si hay error, devuelve el vector sin cambios
      }

      int[] nuevoVector = new int[vector.length - 1];  // Tamaño reducido
      int index = 0;

      for (int i = 0; i < vector.length; i++) {
          if (i != posicion) {
              nuevoVector[index++] = vector[i];
          }
      }

      vector = nuevoVector;
      return vector;
  }

  Desglose:

  int[] nuevoVector = new int[vector.length - 1];

  - Crea un nuevo vector con un elemento menos

  int index = 0;

  - Índice para el nuevo vector

  if (i != posicion)

  - Si NO es la posición a eliminar, copia el elemento

  nuevoVector[index++] = vector[i];

  - Copia el elemento al nuevo vector
  - index++ = Post-incremento (usa el valor y luego incrementa)

  vector = nuevoVector;

  - Reemplaza el vector antiguo por el nuevo

  Ejemplo:
  vector = [10, 20, 30, 40]
  eliminarElementoVector(1);  // Elimina posición 1 (valor 20)

  Proceso:
  i=0: 10 → nuevoVector[0] = 10
  i=1: 20 → SALTAR (es la posición a eliminar)
  i=2: 30 → nuevoVector[1] = 30
  i=3: 40 → nuevoVector[2] = 40

  Resultado: [10, 30, 40]

  ---
  7. OPERACIONES CON MATRICES

  public void crearMatriz(int filas, int columnas)

  public void crearMatriz(int filas, int columnas) {
      this.matriz = new int[filas][columnas];
  }

  Desglose:
  - int[filas][columnas] = Matriz bidimensional
  - filas = Número de filas (horizontal)
  - columnas = Número de columnas (vertical)

  Ejemplo:
  crearMatriz(3, 4);  // 3 filas x 4 columnas
  Resultado:
  [ [0, 0, 0, 0],
    [0, 0, 0, 0],
    [0, 0, 0, 0] ]

  ---
  public void llenarMatrizAleatoria(int min, int max)

  public void llenarMatrizAleatoria(int min, int max) {
      if (matriz == null) return;

      for (int i = 0; i < matriz.length; i++) {         // Filas
          for (int j = 0; j < matriz[i].length; j++) {  // Columnas
              matriz[i][j] = random.nextInt(max - min + 1) + min;
          }
      }
  }

  Desglose:

  Bucle externo: for (int i = 0; i < matriz.length; i++)

  - i = Índice de fila
  - matriz.length = Número de filas

  Bucle interno: for (int j = 0; j < matriz[i].length; j++)

  - j = Índice de columna
  - matriz[i].length = Número de columnas en la fila i

  matriz[i][j] = random.nextInt(max - min + 1) + min;

  - Asigna un número aleatorio a la posición [fila][columna]

  Visualización del recorrido:
  Matriz 2x3:
  [i=0][j=0]  [i=0][j=1]  [i=0][j=2]
  [i=1][j=0]  [i=1][j=1]  [i=1][j=2]

  Orden de llenado:
  1. [0][0] → 2. [0][1] → 3. [0][2]
  4. [1][0] → 5. [1][1] → 6. [1][2]

  ---
  public int sumarMatriz()

  public int sumarMatriz() {
      if (matriz == null) return 0;

      int suma = 0;
      for (int i = 0; i < matriz.length; i++) {
          for (int j = 0; j < matriz[i].length; j++) {
              suma += matriz[i][j];
          }
      }
      return suma;
  }

  Ejemplo:
  matriz = [ [1, 2],
             [3, 4] ]

  Suma: 1 + 2 + 3 + 4 = 10

  ---
  public int[][] eliminarFilaMatriz(int fila)

  public int[][] eliminarFilaMatriz(int fila) {
      if (matriz == null || fila < 0 || fila >= matriz.length) {
          return matriz;
      }

      int[][] nuevaMatriz = new int[matriz.length - 1][matriz[0].length];
      int index = 0;

      for (int i = 0; i < matriz.length; i++) {
          if (i != fila) {
              nuevaMatriz[index++] = matriz[i];
          }
      }

      matriz = nuevaMatriz;
      return matriz;
  }

  Desglose:

  int[][] nuevaMatriz = new int[matriz.length - 1][matriz[0].length];

  - Crea matriz con una fila menos
  - Mantiene el mismo número de columnas

  nuevaMatriz[index++] = matriz[i];

  - Copia la fila completa (no elemento por elemento)

  Ejemplo:
  matriz = [ [10, 20],
             [30, 40],  ← Eliminar fila 1
             [50, 60] ]

  eliminarFilaMatriz(1);

  Resultado:
  [ [10, 20],
    [50, 60] ]

  ---
  public int[][] eliminarColumnaMatriz(int columna)

  public int[][] eliminarColumnaMatriz(int columna) {
      if (matriz == null || columna < 0 || columna >= matriz[0].length) {
          return matriz;
      }

      int[][] nuevaMatriz = new int[matriz.length][matriz[0].length - 1];

      for (int i = 0; i < matriz.length; i++) {
          int index = 0;
          for (int j = 0; j < matriz[i].length; j++) {
              if (j != columna) {
                  nuevaMatriz[i][index++] = matriz[i][j];
              }
          }
      }

      matriz = nuevaMatriz;
      return matriz;
  }

  Desglose:

  int[][] nuevaMatriz = new int[matriz.length][matriz[0].length - 1];

  - Mantiene el número de filas
  - Reduce en 1 el número de columnas

  Bucle externo: recorre filas

  Bucle interno: copia solo las columnas que NO sean la eliminada

  Ejemplo:
  matriz = [ [10, 20, 30],
             [40, 50, 60] ]
                ↑
           Eliminar columna 1

  eliminarColumnaMatriz(1);

  Resultado:
  [ [10, 30],
    [40, 60] ]

  ---
  8. MÉTODOS AUXILIARES

  private boolean esPrimo(int numero)

  private boolean esPrimo(int numero) {
      if (numero <= 1) return false;
      if (numero == 2) return true;
      if (numero % 2 == 0) return false;

      for (int i = 3; i <= Math.sqrt(numero); i += 2) {
          if (numero % i == 0) {
              return false;
          }
      }
      return true;
  }

  Desglose:

  if (numero <= 1) return false;

  - 0, 1 y negativos NO son primos

  if (numero == 2) return true;

  - 2 es el único primo par

  if (numero % 2 == 0) return false;

  - Si es divisible por 2 (par), NO es primo

  for (int i = 3; i <= Math.sqrt(numero); i += 2)

  - i = 3 = Empieza en 3
  - i <= Math.sqrt(numero) = Solo revisa hasta la raíz cuadrada (optimización)
  - i += 2 = Incrementa de 2 en 2 (solo impares)

  if (numero % i == 0) return false;

  - Si es divisible por i, NO es primo

  Ejemplo:
  esPrimo(17):
  - 17 > 1 ✓
  - 17 != 2
  - 17 % 2 = 1 (impar) ✓
  - √17 ≈ 4.12
  - Prueba: 3 → 17 % 3 = 2 (no divisible)
  - i=5 > 4.12 → Sale del bucle
  - return true → 17 ES PRIMO

  ---
  9. GETTERS (Métodos de acceso)

  public int[] getVector()

  public int[] getVector() {
      return vector;
  }

  Propósito: Permite acceder al vector desde otras clases

  Uso: int[] miVector = negocio.getVector();

  ---
  public String getVectorComoTexto()

  public String getVectorComoTexto() {
      if (vector == null) return "Vector no creado";

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < vector.length; i++) {
          sb.append(vector[i]);
          if (i < vector.length - 1) sb.append(", ");
      }
      return sb.toString();
  }

  Propósito: Convierte el vector a formato de texto legible

  Ejemplo:
  vector = [10, 20, 30]
  getVectorComoTexto() → "10, 20, 30"

  ---
  Resumen de la Arquitectura

  NegocioArreglos.java
  │
  ├── ATRIBUTOS (Datos)
  │   ├── int[] vector         → Array unidimensional
  │   ├── int[][] matriz        → Array bidimensional
  │   └── Random random         → Generador aleatorio
  │
  ├── CONSTRUCTOR
  │   └── NegocioArreglos()     → Inicializa Random
  │
  ├── OPERACIONES VECTORES
  │   ├── crearVector()
  │   ├── llenarVectorAleatorio()
  │   ├── sumarVector()
  │   ├── contarPrimosVector()
  │   ├── obtenerPrimosVector()
  │   ├── reemplazarElementoVector()
  │   └── eliminarElementoVector()
  │
  ├── OPERACIONES MATRICES
  │   ├── crearMatriz()
  │   ├── llenarMatrizAleatoria()
  │   ├── sumarMatriz()
  │   ├── contarPrimosMatriz()
  │   ├── obtenerPrimosMatriz()
  │   ├── reemplazarElementoMatriz()
  │   ├── eliminarFilaMatriz()
  │   └── eliminarColumnaMatriz()
  │
  ├── MÉTODOS AUXILIARES
  │   └── esPrimo()             → Verifica si es primo (privado)
  │
  └── GETTERS
      ├── getVector()
      ├── getMatriz()
      ├── getVectorComoTexto()
      └── getMatrizComoTexto()