package negocio;

import java.util.Random;

public class NegocioArreglos {
    private int[] vector;
    private int[][] matriz;
    private Random random;

    public NegocioArreglos() {
        this.random = new Random();
    }

    // ========== OPERACIONES CON VECTORES ==========
    
    public void crearVector(int tamaño) {
        this.vector = new int[tamaño];
    }

    public void llenarVectorAleatorio(int min, int max) {
        if (vector == null) return;
        
        for (int i = 0; i < vector.length; i++) {
            vector[i] = random.nextInt(max - min + 1) + min;
        }
    }

    public int sumarVector() {
        if (vector == null) return 0;
        
        int suma = 0;
        for (int valor : vector) {
            suma += valor;
        }
        return suma;
    }

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

    public void reemplazarElementoVector(int posicion, int nuevoValor) {
        if (vector != null && posicion >= 0 && posicion < vector.length) {
            vector[posicion] = nuevoValor;
        }
    }

    public int[] eliminarElementoVector(int posicion) {
        if (vector == null || posicion < 0 || posicion >= vector.length) {
            return vector;
        }
        
        int[] nuevoVector = new int[vector.length - 1];
        int index = 0;
        
        for (int i = 0; i < vector.length; i++) {
            if (i != posicion) {
                nuevoVector[index++] = vector[i];
            }
        }
        
        vector = nuevoVector;
        return vector;
    }

    // ========== OPERACIONES CON MATRICES ==========
    
    public void crearMatriz(int filas, int columnas) {
        this.matriz = new int[filas][columnas];
    }

    public void llenarMatrizAleatoria(int min, int max) {
        if (matriz == null) return;
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = random.nextInt(max - min + 1) + min;
            }
        }
    }

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

    public int contarPrimosMatriz() {
        if (matriz == null) return 0;
        
        int contador = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (esPrimo(matriz[i][j])) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public String obtenerPrimosMatriz() {
        if (matriz == null) return "Matriz no creada";
        
        StringBuilder primos = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (esPrimo(matriz[i][j])) {
                    if (primos.length() > 0) {
                        primos.append(", ");
                    }
                    primos.append(matriz[i][j]);
                }
            }
        }
        
        return primos.length() > 0 ? primos.toString() : "No hay primos";
    }

    public void reemplazarElementoMatriz(int fila, int columna, int nuevoValor) {
        if (matriz != null && fila >= 0 && fila < matriz.length && 
            columna >= 0 && columna < matriz[0].length) {
            matriz[fila][columna] = nuevoValor;
        }
    }

    // ========== MÉTODOS NUEVOS PARA ELIMINAR FILAS Y COLUMNAS ==========

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

    public int getFilasMatriz() {
        return matriz != null ? matriz.length : 0;
    }

    public int getColumnasMatriz() {
        return (matriz != null && matriz.length > 0) ? matriz[0].length : 0;
    }

    // ========== MÉTODOS AUXILIARES ==========
    
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

    // ========== GETTERS PARA ACCESO ==========
    
    public int[] getVector() {
        return vector;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public String getVectorComoTexto() {
        if (vector == null) return "Vector no creado";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vector.length; i++) {
            sb.append(vector[i]);
            if (i < vector.length - 1) sb.append(", ");
        }
        return sb.toString();
    }

    public String getMatrizComoTexto() {
        if (matriz == null) return "Matriz no creada";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                sb.append(" ").append(matriz[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}