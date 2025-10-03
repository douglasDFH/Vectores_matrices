package com.mycompany.actividad01;  // direccion del archivo 

import presentacion.FormularioArreglos; // importamos la clase FormularioArreglos de la carpeta presentacion

public class Main {  //definimos la clase Main
    public static void main(String[] args) { // metodo main, punto de entrada del programa el inicio de la aplicacion
        
        FormularioArreglos formulario = new FormularioArreglos();  /// creamos el objeto formulario (se construye la la presentacion GUI)
        formulario.setVisible(true);  // muestra la ventana del formulario
    }
}  
