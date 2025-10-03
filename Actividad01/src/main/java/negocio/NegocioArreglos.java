package negocio;

import java.util.Random; // importamos la clase Random para generar numeros aleatorios

public class NegocioArreglos { // definimos la clase NegocioArreglos
    private int[] vector;      // atributo para el vector
    private int[][] matriz;    // atributo para la matriz
    private Random random;     // atributo para generar numeros aleatorios

    public NegocioArreglos() {  // constructor de la clase
        this.random = new Random(); // inicializamos el generador de numeros aleatorios
    }

    // ========== OPERACIONES CON VECTORES ==========
    
    public void crearVector(int tamaño) {    // metodo para crear el vector
        this.vector = new int[tamaño];        // inicializamos el vector con el tamaño especificado
    }

    public void llenarVectorAleatorio(int min, int max) {   // metodo para llenar el vector con numeros aleatorios entre min y max
        if (vector == null) return;                         // si el vector no ha sido creado, salimos del metodo (condicional)
        
        for (int i = 0; i < vector.length; i++) {            // recorremos el vector
            vector[i] = random.nextInt(max - min + 1) + min;  // asignamos un numero aleatorio entre min y max a cada posicion del vector
        }
    }

    public int sumarVector() {                                   // metodo para sumar los elementos del vector
        if (vector == null) return 0;                            // si el vector no ha sido creado, retornamos 0
        
        int suma = 0;                                             // variable para almacenar la suma
        for (int valor : vector) {                            // recorremos el vector
            suma += valor;                                     // sumamos cada elemento a la variable suma
        }
        return suma;                                         // retornamos la suma total
    }

    public int contarPrimosVector() {                       // metodo para contar los numeros primos en el vector
        if (vector == null) return 0;                       // si el vector no ha sido creado, retornamos 0

        int contador = 0;                                   // variable para contar los primos
        for (int valor : vector) {                          // recorremos cada elemento del vector
            if (esPrimo(valor)) {                           // verificamos si el valor es primo
                contador++;                                 // incrementamos el contador si es primo
            }
        }
        return contador;                                    // retornamos la cantidad de primos encontrados
    }

    public String obtenerPrimosVector() {                   // metodo que devuelve los numeros primos del vector como texto
        if (vector == null) return "Vector no creado";     // si el vector no existe, retornamos mensaje

        StringBuilder primos = new StringBuilder();         // creamos StringBuilder para construir el texto eficientemente
        for (int valor : vector) {                          // recorremos cada elemento del vector
            if (esPrimo(valor)) {                           // verificamos si el valor es primo
                if (primos.length() > 0) {                  // si ya hay primos agregados
                    primos.append(", ");                    // agregamos coma separadora
                }
                primos.append(valor);                       // agregamos el numero primo al texto
            }
        }

        return primos.length() > 0 ? primos.toString() : "No hay primos"; // operador ternario: si hay primos devolvemos texto, sino "No hay primos"
    }

    public void reemplazarElementoVector(int posicion, int nuevoValor) {  // metodo para cambiar el valor de un elemento en una posicion del vector
        if (vector != null && posicion >= 0 && posicion < vector.length) { // validamos que el vector exista y la posicion sea valida
            vector[posicion] = nuevoValor;                                  // asignamos el nuevo valor en la posicion indicada
        }
    }

    public int[] eliminarElementoVector(int posicion) {                    // metodo para eliminar un elemento del vector en la posicion indicada
        if (vector == null || posicion < 0 || posicion >= vector.length) { // validamos que el vector exista y la posicion sea valida
            return vector;                                                  // si hay error, retornamos el vector sin cambios
        }

        int[] nuevoVector = new int[vector.length - 1];                    // creamos un nuevo vector con un elemento menos
        int index = 0;                                                      // indice para el nuevo vector

        for (int i = 0; i < vector.length; i++) {                          // recorremos el vector original
            if (i != posicion) {                                            // si NO es la posicion a eliminar
                nuevoVector[index++] = vector[i];                           // copiamos el elemento al nuevo vector y aumentamos indice
            }
        }

        vector = nuevoVector;                                               // reemplazamos el vector antiguo por el nuevo
        return vector;                                                      // retornamos el vector modificado
    }

    // ========== OPERACIONES CON MATRICES ==========

    public void crearMatriz(int filas, int columnas) {             // metodo para crear una matriz con el numero de filas y columnas especificado
        this.matriz = new int[filas][columnas];                    // inicializamos la matriz bidimensional
    }

    public void llenarMatrizAleatoria(int min, int max) {          // metodo para llenar la matriz con numeros aleatorios entre min y max
        if (matriz == null) return;                                // si la matriz no ha sido creada, salimos del metodo

        for (int i = 0; i < matriz.length; i++) {                  // bucle externo: recorremos las filas
            for (int j = 0; j < matriz[i].length; j++) {           // bucle interno: recorremos las columnas
                matriz[i][j] = random.nextInt(max - min + 1) + min; // asignamos numero aleatorio en la posicion [fila][columna]
            }
        }
    }

    public int sumarMatriz() {                                     // metodo que devuelve la suma de todos los elementos de la matriz
        if (matriz == null) return 0;                              // si la matriz no existe, retornamos 0

        int suma = 0;                                              // variable para almacenar la suma
        for (int i = 0; i < matriz.length; i++) {                 // recorremos las filas
            for (int j = 0; j < matriz[i].length; j++) {          // recorremos las columnas
                suma += matriz[i][j];                              // sumamos el valor de la celda [i][j] al acumulador
            }
        }
        return suma;                                               // retornamos la suma total
    }

    public int contarPrimosMatriz() {                              // metodo para contar los numeros primos en la matriz
        if (matriz == null) return 0;                              // si la matriz no existe, retornamos 0

        int contador = 0;                                          // variable para contar los primos
        for (int i = 0; i < matriz.length; i++) {                 // recorremos las filas
            for (int j = 0; j < matriz[i].length; j++) {          // recorremos las columnas
                if (esPrimo(matriz[i][j])) {                       // verificamos si el valor en [i][j] es primo
                    contador++;                                    // incrementamos el contador si es primo
                }
            }
        }
        return contador;                                           // retornamos la cantidad de primos encontrados
    }

    public String obtenerPrimosMatriz() {                          // metodo que devuelve los numeros primos de la matriz como texto
        if (matriz == null) return "Matriz no creada";            // si la matriz no existe, retornamos mensaje

        StringBuilder primos = new StringBuilder();                // creamos StringBuilder para construir el texto eficientemente
        for (int i = 0; i < matriz.length; i++) {                 // recorremos las filas
            for (int j = 0; j < matriz[i].length; j++) {          // recorremos las columnas
                if (esPrimo(matriz[i][j])) {                       // verificamos si el valor en [i][j] es primo
                    if (primos.length() > 0) {                     // si ya hay primos agregados
                        primos.append(", ");                       // agregamos coma separadora
                    }
                    primos.append(matriz[i][j]);                   // agregamos el numero primo al texto
                }
            }
        }

        return primos.length() > 0 ? primos.toString() : "No hay primos"; // operador ternario: si hay primos devolvemos texto, sino "No hay primos"
    }

    public void reemplazarElementoMatriz(int fila, int columna, int nuevoValor) { // metodo para cambiar el valor de una celda en la matriz
        if (matriz != null && fila >= 0 && fila < matriz.length &&                // validamos que la matriz exista y la fila sea valida
            columna >= 0 && columna < matriz[0].length) {                          // validamos que la columna sea valida
            matriz[fila][columna] = nuevoValor;                                    // asignamos el nuevo valor en la celda [fila][columna]
        }
    }

    // ========== MÉTODOS NUEVOS PARA ELIMINAR FILAS Y COLUMNAS ==========

    public int[][] eliminarFilaMatriz(int fila) {                          // metodo para eliminar una fila de la matriz
        if (matriz == null || fila < 0 || fila >= matriz.length) {        // validamos que la matriz exista y la fila sea valida
            return matriz;                                                  // si hay error, retornamos la matriz sin cambios
        }

        int[][] nuevaMatriz = new int[matriz.length - 1][matriz[0].length]; // creamos nueva matriz con una fila menos
        int index = 0;                                                       // indice para las filas de la nueva matriz

        for (int i = 0; i < matriz.length; i++) {                          // recorremos las filas de la matriz original
            if (i != fila) {                                                // si NO es la fila a eliminar
                nuevaMatriz[index++] = matriz[i];                           // copiamos la fila completa y aumentamos indice
            }
        }

        matriz = nuevaMatriz;                                               // reemplazamos la matriz antigua por la nueva
        return matriz;                                                      // retornamos la matriz modificada
    }

    public int[][] eliminarColumnaMatriz(int columna) {                    // metodo para eliminar una columna de la matriz
        if (matriz == null || columna < 0 || columna >= matriz[0].length) { // validamos que la matriz exista y la columna sea valida
            return matriz;                                                  // si hay error, retornamos la matriz sin cambios
        }

        int[][] nuevaMatriz = new int[matriz.length][matriz[0].length - 1]; // creamos nueva matriz con una columna menos

        for (int i = 0; i < matriz.length; i++) {                          // recorremos las filas
            int index = 0;                                                  // indice para las columnas de la nueva matriz
            for (int j = 0; j < matriz[i].length; j++) {                   // recorremos las columnas
                if (j != columna) {                                         // si NO es la columna a eliminar
                    nuevaMatriz[i][index++] = matriz[i][j];                 // copiamos el elemento y aumentamos indice
                }
            }
        }

        matriz = nuevaMatriz;                                               // reemplazamos la matriz antigua por la nueva
        return matriz;                                                      // retornamos la matriz modificada
    }

    public int getFilasMatriz() {                                          // metodo que devuelve el numero de filas de la matriz
        return matriz != null ? matriz.length : 0;                         // operador ternario: si matriz existe devolvemos filas, sino 0
    }

    public int getColumnasMatriz() {                                       // metodo que devuelve el numero de columnas de la matriz
        return (matriz != null && matriz.length > 0) ? matriz[0].length : 0; // operador ternario: si matriz existe y tiene filas devolvemos columnas, sino 0
    }

    // ========== MÉTODOS AUXILIARES ==========

    private boolean esPrimo(int numero) {                          // metodo privado que verifica si un numero es primo
        if (numero <= 1) return false;                             // numeros menores o iguales a 1 no son primos
        if (numero == 2) return true;                              // 2 es el unico numero primo par
        if (numero % 2 == 0) return false;                         // si es divisible por 2 (par), no es primo

        for (int i = 3; i <= Math.sqrt(numero); i += 2) {         // recorremos solo impares hasta la raiz cuadrada (optimizacion)
            if (numero % i == 0) {                                 // si es divisible por i
                return false;                                      // no es primo
            }
        }
        return true;                                               // si no fue divisible por ningun numero, es primo
    }

    // ========== GETTERS PARA ACCESO ==========

    public int[] getVector() {                                     // metodo getter que devuelve el vector
        return vector;                                             // retornamos el array del vector
    }

    public int[][] getMatriz() {                                   // metodo getter que devuelve la matriz
        return matriz;                                             // retornamos el array bidimensional de la matriz
    }

    public String getVectorComoTexto() {                           // metodo que convierte el vector a formato de texto legible
        if (vector == null) return "Vector no creado";            // si el vector no existe, retornamos mensaje

        StringBuilder sb = new StringBuilder();                    // creamos StringBuilder para construir el texto
        for (int i = 0; i < vector.length; i++) {                 // recorremos cada posicion del vector
            sb.append(vector[i]);                                  // agregamos el valor al texto
            if (i < vector.length - 1) sb.append(", ");           // si no es el ultimo elemento, agregamos coma
        }
        return sb.toString();                                      // convertimos StringBuilder a String y lo retornamos
    }

    public String getMatrizComoTexto() {                           // metodo que convierte la matriz a formato de texto legible
        if (matriz == null) return "Matriz no creada";            // si la matriz no existe, retornamos mensaje

        StringBuilder sb = new StringBuilder();                    // creamos StringBuilder para construir el texto
        for (int i = 0; i < matriz.length; i++) {                 // recorremos las filas
            for (int j = 0; j < matriz[i].length; j++) {          // recorremos las columnas
                sb.append(" ").append(matriz[i][j]);               // agregamos espacio y el valor de la celda
            }
            sb.append("\n");                                       // al final de cada fila, agregamos salto de linea
        }
        return sb.toString();                                      // convertimos StringBuilder a String y lo retornamos
    }
}