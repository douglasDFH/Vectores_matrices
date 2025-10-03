package presentacion; // declaramos que esta clase pertenece al paquete presentacion

import negocio.NegocioArreglos;                // importamos la clase de logica de negocio
import javax.swing.*;                           // importamos todos los componentes de Swing
import javax.swing.table.DefaultTableModel;   // importamos modelo de tabla por defecto
import java.awt.*;                             // importamos componentes de AWT para layout
import java.awt.event.ActionEvent;            // importamos clase para eventos de acciones

public class FormularioArreglos extends JFrame {  // definimos la clase que hereda de JFrame (ventana)
    private NegocioArreglos negocio;              // atributo para manejar la logica de negocio
    private JTable tablaVector, tablaMatriz;     // atributos para las tablas que muestran vector y matriz
    private DefaultTableModel modelVector, modelMatriz; // modelos de datos para las tablas
    private JTextField txtTamVector, txtFilas, txtColumnas; // campos de texto para dimensiones
    private JTextField txtMin, txtMax;           // campos de texto para valores minimo y maximo
    private JTextArea areaResultadosVectores, areaResultadosMatrices; // areas de texto para mostrar resultados
    private JTabbedPane tabbedPane;             // componente de pestañas para separar vectores y matrices
    
    public FormularioArreglos() {                                      // constructor de la clase FormularioArreglos
        negocio = new NegocioArreglos();                               // inicializamos la instancia de la clase de negocio
        initComponents();                                              // llamamos al metodo que inicializa los componentes graficos
        setTitle("Gestión de Vectores y Matrices - Actividad01");     // establecemos el titulo de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);               // configuramos que la aplicacion se cierre al cerrar la ventana
        setSize(1000, 750);                                           // establecemos el tamaño de la ventana (ancho x alto)
        setLocationRelativeTo(null);                                  // centramos la ventana en la pantalla
    }
    
    private void initComponents() {                                    // metodo privado para inicializar los componentes de la interfaz
        // Panel principal con pestañas
        tabbedPane = new JTabbedPane();                               // creamos un contenedor de pestañas
        
        // Pestaña para Vectores (con su propia área de resultados)
        tabbedPane.addTab("Vectores", crearPanelVector());           // agregamos la pestaña de vectores llamando al metodo que crea su panel
        
        // Pestaña para Matrices (con su propia área de resultados)
        tabbedPane.addTab("Matrices", crearPanelMatriz());           // agregamos la pestaña de matrices llamando al metodo que crea su panel
        
        add(tabbedPane, BorderLayout.CENTER);                        // agregamos el contenedor de pestañas al centro de la ventana
    }
    
    private JPanel crearPanelVector() {                               // metodo que crea y configura el panel para operaciones con vectores
        JPanel panel = new JPanel(new BorderLayout());               // creamos panel principal con layout BorderLayout
        
        // Panel de controles
        JPanel panelControles = new JPanel(new GridLayout(2, 1));   // creamos panel de controles con 2 filas y 1 columna
        
        // Fila 1: Dimensiones
        JPanel panelDimensiones = new JPanel(new FlowLayout());     // creamos panel para dimensiones con layout FlowLayout
        panelDimensiones.add(new JLabel("Tamaño Vector:"));         // agregamos etiqueta para tamaño del vector
        txtTamVector = new JTextField(5);                           // creamos campo de texto con ancho de 5 caracteres
        panelDimensiones.add(txtTamVector);                         // agregamos el campo de texto al panel
        
        JButton btnCrearVector = new JButton("Crear Vector");       // creamos boton para crear el vector
        btnCrearVector.addActionListener(this::crearVector);        // asignamos el metodo que se ejecutara al hacer clic
        panelDimensiones.add(btnCrearVector);                       // agregamos el boton al panel
        
        // Fila 2: Rango aleatorio
        JPanel panelRango = new JPanel(new FlowLayout());           // creamos panel para rango con layout FlowLayout
        panelRango.add(new JLabel("Mín:"));                         // agregamos etiqueta para valor minimo
        txtMin = new JTextField(3);                                 // creamos campo de texto para valor minimo
        txtMin.setText("1");                                        // establecemos valor por defecto
        panelRango.add(txtMin);                                     // agregamos campo al panel
        panelRango.add(new JLabel("Máx:"));                         // agregamos etiqueta para valor maximo
        txtMax = new JTextField(3);                                 // creamos campo de texto para valor maximo
        txtMax.setText("100");                                      // establecemos valor por defecto
        panelRango.add(txtMax);                                     // agregamos campo al panel
        
        JButton btnLlenarAleatorio = new JButton("Llenar Aleatorio");   // creamos boton para llenar vector aleatoriamente
        btnLlenarAleatorio.addActionListener(this::llenarVectorAleatorio); // asignamos metodo para el evento clic
        panelRango.add(btnLlenarAleatorio);                         // agregamos boton al panel de rango
        
        panelControles.add(panelDimensiones);                       // agregamos panel de dimensiones a controles
        panelControles.add(panelRango);                             // agregamos panel de rango a controles
        
        // Tabla para mostrar el vector
        modelVector = new DefaultTableModel(new String[]{"Índice", "Valor"}, 0); // creamos modelo de tabla con columnas Indice y Valor
        tablaVector = new JTable(modelVector);                      // creamos tabla usando el modelo
        JScrollPane scrollVector = new JScrollPane(tablaVector);    // envolvemos tabla en scroll para navegacion
        
        // Panel de operaciones
        JPanel panelOperaciones = new JPanel(new FlowLayout());     // creamos panel para botones de operaciones
        JButton btnSumar = new JButton("Sumar Elementos");          // creamos boton para sumar elementos del vector
        btnSumar.addActionListener(this::sumarVector);              // asignamos metodo para el evento clic
        panelOperaciones.add(btnSumar);                             // agregamos boton al panel
        
        JButton btnContarPrimos = new JButton("Contar Primos");     // creamos boton para contar numeros primos
        btnContarPrimos.addActionListener(this::contarPrimosVector); // asignamos metodo para el evento clic
        panelOperaciones.add(btnContarPrimos);                      // agregamos boton al panel
        
        JButton btnEditar = new JButton("Editar Valor");            // creamos boton para editar valores del vector
        btnEditar.addActionListener(this::editarValorVector);       // asignamos metodo para el evento clic
        panelOperaciones.add(btnEditar);                            // agregamos boton al panel
        
        JButton btnEliminar = new JButton("Eliminar Elemento");     // creamos boton para eliminar elementos del vector
        btnEliminar.addActionListener(this::eliminarElementoVector); // asignamos metodo para el evento clic
        panelOperaciones.add(btnEliminar);                          // agregamos boton al panel
        
        // Área de resultados ESPECÍFICA para Vectores
        areaResultadosVectores = new JTextArea(5, 50);              // creamos area de texto de 5 filas y 50 columnas
        areaResultadosVectores.setEditable(false);                  // hacemos el area no editable por el usuario
        areaResultadosVectores.setBackground(new Color(240, 240, 240)); // establecemos color de fondo gris claro
        areaResultadosVectores.setText(">>> Resultados de operaciones con VECTORES:\n"); // texto inicial del area
        JScrollPane scrollResultadosVectores = new JScrollPane(areaResultadosVectores); // envolvemos en scroll
        
        // Organizar componentes en un panel principal para Vectores
        JPanel panelCentral = new JPanel(new BorderLayout());       // creamos panel central con BorderLayout
        panelCentral.add(scrollVector, BorderLayout.CENTER);        // agregamos tabla del vector al centro
        panelCentral.add(panelOperaciones, BorderLayout.SOUTH);     // agregamos operaciones en la parte inferior
        
        panel.add(panelControles, BorderLayout.NORTH);              // agregamos controles en la parte superior
        panel.add(panelCentral, BorderLayout.CENTER);               // agregamos panel central al centro
        panel.add(scrollResultadosVectores, BorderLayout.SOUTH);    // agregamos resultados en la parte inferior
        
        return panel;                                               // retornamos el panel completo configurado
    }
    
    private JPanel crearPanelMatriz() {                               // metodo que crea y configura el panel para operaciones con matrices
        JPanel panel = new JPanel(new BorderLayout());               // creamos panel principal con layout BorderLayout
        
        // Panel de controles
        JPanel panelControles = new JPanel(new GridLayout(2, 1));   // creamos panel de controles con 2 filas y 1 columna
        
        // Fila 1: Dimensiones
        JPanel panelDimensiones = new JPanel(new FlowLayout());     // creamos panel para dimensiones con layout FlowLayout
        panelDimensiones.add(new JLabel("Filas:"));                 // agregamos etiqueta para numero de filas
        txtFilas = new JTextField(3);                               // creamos campo de texto para filas
        panelDimensiones.add(txtFilas);                             // agregamos campo al panel
        panelDimensiones.add(new JLabel("Columnas:"));              // agregamos etiqueta para numero de columnas
        txtColumnas = new JTextField(3);                            // creamos campo de texto para columnas
        panelDimensiones.add(txtColumnas);                          // agregamos campo al panel
        
        JButton btnCrearMatriz = new JButton("Crear Matriz");       // creamos boton para crear la matriz
        btnCrearMatriz.addActionListener(this::crearMatriz);        // asignamos metodo para el evento clic
        panelDimensiones.add(btnCrearMatriz);                       // agregamos boton al panel
        
        // Fila 2: Operaciones
        JPanel panelOperaciones = new JPanel(new FlowLayout());     // creamos panel para operaciones con layout FlowLayout
        JButton btnLlenarMatriz = new JButton("Llenar Aleatorio");  // creamos boton para llenar matriz aleatoriamente
        btnLlenarMatriz.addActionListener(this::llenarMatrizAleatoria); // asignamos metodo para el evento clic
        panelOperaciones.add(btnLlenarMatriz);                      // agregamos boton al panel
        
        JButton btnSumarMatriz = new JButton("Sumar Matriz");       // creamos boton para sumar elementos de la matriz
        btnSumarMatriz.addActionListener(this::sumarMatriz);        // asignamos metodo para el evento clic
        panelOperaciones.add(btnSumarMatriz);                       // agregamos boton al panel
        
        JButton btnPrimosMatriz = new JButton("Primos Matriz");     // creamos boton para contar primos en matriz
        btnPrimosMatriz.addActionListener(this::contarPrimosMatriz); // asignamos metodo para el evento clic
        panelOperaciones.add(btnPrimosMatriz);                      // agregamos boton al panel
        
        JButton btnEditarMatriz = new JButton("Editar Celda");      // creamos boton para editar celdas de la matriz
        btnEditarMatriz.addActionListener(this::editarValorMatriz); // asignamos metodo para el evento clic
        panelOperaciones.add(btnEditarMatriz);                      // agregamos boton al panel
        
        JButton btnEliminarFila = new JButton("Eliminar Fila");     // creamos boton para eliminar filas
        btnEliminarFila.addActionListener(this::eliminarFilaMatriz); // asignamos metodo para el evento clic
        panelOperaciones.add(btnEliminarFila);                      // agregamos boton al panel
        
        JButton btnEliminarColumna = new JButton("Eliminar Columna"); // creamos boton para eliminar columnas
        btnEliminarColumna.addActionListener(this::eliminarColumnaMatriz); // asignamos metodo para el evento clic
        panelOperaciones.add(btnEliminarColumna);                   // agregamos boton al panel
        
        panelControles.add(panelDimensiones);                          // agregamos panel de dimensiones a controles
        panelControles.add(panelOperaciones);                       // agregamos panel de operaciones a controles
        
        // Tabla para matriz
        modelMatriz = new DefaultTableModel();                      // creamos modelo de tabla vacio para la matriz
        tablaMatriz = new JTable(modelMatriz);                      // creamos tabla usando el modelo
        JScrollPane scrollMatriz = new JScrollPane(tablaMatriz);    // envolvemos tabla en scroll para navegacion
        
        // Área de resultados ESPECÍFICA para Matrices
        areaResultadosMatrices = new JTextArea(5, 50);              // creamos area de texto de 5 filas y 50 columnas
        areaResultadosMatrices.setEditable(false);                  // hacemos el area no editable por el usuario
        areaResultadosMatrices.setBackground(new Color(240, 240, 240)); // establecemos color de fondo gris claro
        areaResultadosMatrices.setText(">>> Resultados de operaciones con MATRICES:\n"); // texto inicial del area
        JScrollPane scrollResultadosMatrices = new JScrollPane(areaResultadosMatrices); // envolvemos en scroll
        
        // Organizar componentes en un panel principal para Matrices
        JPanel panelCentral = new JPanel(new BorderLayout());       // creamos panel central con BorderLayout
        panelCentral.add(scrollMatriz, BorderLayout.CENTER);        // agregamos tabla de la matriz al centro
        
        panel.add(panelControles, BorderLayout.NORTH);              // agregamos controles en la parte superior
        panel.add(panelCentral, BorderLayout.CENTER);               // agregamos panel central al centro
        panel.add(scrollResultadosMatrices, BorderLayout.SOUTH);    // agregamos resultados en la parte inferior
        
        return panel;                                               // retornamos el panel completo configurado
    }
    
    // ========== MÉTODOS PARA VECTORES ==========
    
    private void crearVector(ActionEvent e) {                       // metodo que maneja la creacion de vectores
        try {                                                       // bloque try para manejar excepciones
            int tamaño = Integer.parseInt(txtTamVector.getText());  // convertimos el texto a entero para obtener el tamaño
            if (tamaño <= 0) {                                      // validamos que el tamaño sea positivo
                mostrarError("El tamaño debe ser mayor a 0");       // mostramos mensaje de error si es invalido
                return;                                             // salimos del metodo
            }
            negocio.crearVector(tamaño);                           // llamamos al metodo de negocio para crear el vector
            actualizarTablaVector();                               // actualizamos la tabla para mostrar el vector
            mostrarResultadoVectores("Vector creado con tamaño: " + tamaño); // mostramos mensaje de exito
        } catch (NumberFormatException ex) {                       // capturamos excepcion si el texto no es un numero
            mostrarError("Ingrese un tamaño válido");              // mostramos mensaje de error
        }
    }
    
    private void llenarVectorAleatorio(ActionEvent e) {             // metodo que maneja el llenado aleatorio del vector
        try {                                                       // bloque try para manejar excepciones
            int min = Integer.parseInt(txtMin.getText());           // convertimos texto a entero para valor minimo
            int max = Integer.parseInt(txtMax.getText());           // convertimos texto a entero para valor maximo
            if (min >= max) {                                       // validamos que min sea menor que max
                mostrarError("El valor mínimo debe ser menor al máximo"); // mostramos error si la validacion falla
                return;                                             // salimos del metodo
            }
            negocio.llenarVectorAleatorio(min, max);               // llamamos al metodo de negocio para llenar el vector
            actualizarTablaVector();                               // actualizamos la tabla para mostrar los nuevos valores
            mostrarResultadoVectores("Vector llenado aleatoriamente entre " + min + " y " + max); // mensaje de exito
        } catch (NumberFormatException ex) {                       // capturamos excepcion si los textos no son numeros
            mostrarError("Ingrese valores mín y máx válidos");     // mostramos mensaje de error
        }
    }
    
    private void sumarVector(ActionEvent e) {                       // metodo que maneja la suma de elementos del vector
        int suma = negocio.sumarVector();                           // llamamos al metodo de negocio para sumar el vector
        mostrarResultadoVectores("Suma del vector: " + suma);       // mostramos el resultado en el area de vectores
    }
    
    private void contarPrimosVector(ActionEvent e) {               // metodo que maneja el conteo de numeros primos en el vector
        int cantidad = negocio.contarPrimosVector();               // obtenemos la cantidad de primos del vector
        String valoresPrimos = negocio.obtenerPrimosVector();      // obtenemos los valores primos como texto
        
        if (cantidad > 0) {                                        // si hay primos encontrados
            mostrarResultadoVectores("Números primos en el vector: " + cantidad + " → Valores: [" + valoresPrimos + "]"); // mostramos cantidad y valores
        } else {                                                   // si no hay primos
            mostrarResultadoVectores("Números primos en el vector: " + cantidad + " → " + valoresPrimos); // mostramos solo cantidad y mensaje
        }
    }
    
    private void editarValorVector(ActionEvent e) {                 // metodo que maneja la edicion de valores en el vector
        try {                                                       // bloque try para manejar excepciones
            int filaSeleccionada = tablaVector.getSelectedRow();    // obtenemos la fila seleccionada en la tabla
            if (filaSeleccionada == -1) {                           // verificamos si no hay fila seleccionada
                mostrarError("Seleccione una fila de la tabla para editar"); // mostramos error si no hay seleccion
                return;                                             // salimos del metodo
            }
            
            String nuevoValorStr = JOptionPane.showInputDialog(this, // mostramos dialogo para ingresar nuevo valor
                "Nuevo valor para la posición " + filaSeleccionada + ":", // mensaje del dialogo
                "Editar Valor", JOptionPane.QUESTION_MESSAGE);     // titulo y tipo de dialogo
            
            if (nuevoValorStr != null && !nuevoValorStr.trim().isEmpty()) { // verificamos que se ingreso un valor
                int nuevoValor = Integer.parseInt(nuevoValorStr);   // convertimos el texto a numero entero
                negocio.reemplazarElementoVector(filaSeleccionada, nuevoValor); // reemplazamos el valor en el negocio
                actualizarTablaVector();                           // actualizamos la tabla para mostrar el cambio
                mostrarResultadoVectores("Valor en posición " + filaSeleccionada + " cambiado a: " + nuevoValor); // mensaje de exito
            }
        } catch (NumberFormatException ex) {                       // capturamos excepcion si el texto no es un numero
            mostrarError("Ingrese un número válido");              // mostramos mensaje de error
        }
    }
    
    private void eliminarElementoVector(ActionEvent e) {            // metodo que maneja la eliminacion de elementos del vector
        try {                                                       // bloque try para manejar excepciones
            int filaSeleccionada = tablaVector.getSelectedRow();    // obtenemos la fila seleccionada en la tabla
            if (filaSeleccionada == -1) {                           // verificamos si no hay fila seleccionada
                mostrarError("Seleccione una fila de la tabla para eliminar"); // mostramos error si no hay seleccion
                return;                                             // salimos del metodo
            }
            
            int confirmacion = JOptionPane.showConfirmDialog(this,  // mostramos dialogo de confirmacion
                "¿Está seguro de eliminar el elemento en la posición " + filaSeleccionada + "?", // mensaje de confirmacion
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION); // titulo y opciones del dialogo
            
            if (confirmacion == JOptionPane.YES_OPTION) {           // si el usuario confirma la eliminacion
                negocio.eliminarElementoVector(filaSeleccionada);  // eliminamos el elemento del vector en el negocio
                actualizarTablaVector();                           // actualizamos la tabla para mostrar el cambio
                mostrarResultadoVectores("Elemento en posición " + filaSeleccionada + " eliminado"); // mensaje de exito
            }
        } catch (Exception ex) {                                   // capturamos cualquier excepcion
            mostrarError("Error al eliminar elemento: " + ex.getMessage()); // mostramos mensaje de error con detalles
        }
    }
    
    private void actualizarTablaVector() {                          // metodo que actualiza la tabla que muestra el vector
        modelVector.setRowCount(0);                                // eliminamos todas las filas existentes del modelo
        int[] vector = negocio.getVector();                        // obtenemos el vector actual del negocio
        if (vector != null) {                                      // verificamos que el vector no sea nulo
            for (int i = 0; i < vector.length; i++) {              // recorremos cada elemento del vector
                modelVector.addRow(new Object[]{i, vector[i]});     // agregamos fila con indice y valor al modelo
            }
        }
    }
    
    // ========== MÉTODOS PARA MATRICES ==========
    
    private void crearMatriz(ActionEvent e) {                      // metodo que maneja la creacion de matrices
        try {                                                       // bloque try para manejar excepciones
            int filas = Integer.parseInt(txtFilas.getText());       // convertimos texto a entero para numero de filas
            int columnas = Integer.parseInt(txtColumnas.getText()); // convertimos texto a entero para numero de columnas
            if (filas <= 0 || columnas <= 0) {                     // validamos que filas y columnas sean positivas
                mostrarError("Filas y columnas deben ser mayores a 0"); // mostramos error si la validacion falla
                return;                                             // salimos del metodo
            }
            negocio.crearMatriz(filas, columnas);                  // llamamos al metodo de negocio para crear la matriz
            actualizarTablaMatriz();                               // actualizamos la tabla para mostrar la matriz
            mostrarResultadoMatrices("Matriz creada: " + filas + "x" + columnas); // mensaje de exito
        } catch (NumberFormatException ex) {                       // capturamos excepcion si los textos no son numeros
            mostrarError("Ingrese filas y columnas válidas");      // mostramos mensaje de error
        }
    }
    
    private void llenarMatrizAleatoria(ActionEvent e) {             // metodo que maneja el llenado aleatorio de la matriz
        try {                                                       // bloque try para manejar excepciones
            int min = Integer.parseInt(txtMin.getText());           // convertimos texto a entero para valor minimo
            int max = Integer.parseInt(txtMax.getText());           // convertimos texto a entero para valor maximo
            if (min >= max) {                                       // validamos que min sea menor que max
                mostrarError("El valor mínimo debe ser menor al máximo"); // mostramos error si la validacion falla
                return;                                             // salimos del metodo
            }
            negocio.llenarMatrizAleatoria(min, max);               // llamamos al metodo de negocio para llenar la matriz
            actualizarTablaMatriz();                               // actualizamos la tabla para mostrar los nuevos valores
            mostrarResultadoMatrices("Matriz llenada aleatoriamente entre " + min + " y " + max); // mensaje de exito
        } catch (NumberFormatException ex) {                       // capturamos excepcion si los textos no son numeros
            mostrarError("Ingrese valores mín y máx válidos");     // mostramos mensaje de error
        }
    }
    
    private void sumarMatriz(ActionEvent e) {                       // metodo que maneja la suma de elementos de la matriz
        int suma = negocio.sumarMatriz();                           // llamamos al metodo de negocio para sumar la matriz
        mostrarResultadoMatrices("Suma de la matriz: " + suma);     // mostramos el resultado en el area de matrices
    }
    
    private void contarPrimosMatriz(ActionEvent e) {               // metodo que maneja el conteo de numeros primos en la matriz
        int cantidad = negocio.contarPrimosMatriz();               // obtenemos la cantidad de primos de la matriz
        String valoresPrimos = negocio.obtenerPrimosMatriz();      // obtenemos los valores primos como texto
        
        if (cantidad > 0) {                                        // si hay primos encontrados
            mostrarResultadoMatrices("Números primos en la matriz: " + cantidad + " → Valores: [" + valoresPrimos + "]"); // mostramos cantidad y valores
        } else {                                                   // si no hay primos
            mostrarResultadoMatrices("Números primos en la matriz: " + cantidad + " → " + valoresPrimos); // mostramos solo cantidad y mensaje
        }
    }
    
    private void editarValorMatriz(ActionEvent e) {                 // metodo que maneja la edicion de valores en la matriz
        try {                                                       // bloque try para manejar excepciones
            int filaSeleccionada = tablaMatriz.getSelectedRow();    // obtenemos la fila seleccionada en la tabla
            int columnaSeleccionada = tablaMatriz.getSelectedColumn(); // obtenemos la columna seleccionada en la tabla
            
            if (filaSeleccionada == -1 || columnaSeleccionada == -1) { // verificamos si no hay celda seleccionada
                mostrarError("Seleccione una celda de la tabla para editar"); // mostramos error si no hay seleccion
                return;                                             // salimos del metodo
            }
            
            String nuevoValorStr = JOptionPane.showInputDialog(this, // mostramos dialogo para ingresar nuevo valor
                "Nuevo valor para la posición [" + filaSeleccionada + "," + columnaSeleccionada + "]:", // mensaje del dialogo
                "Editar Valor Matriz", JOptionPane.QUESTION_MESSAGE); // titulo y tipo de dialogo
            
            if (nuevoValorStr != null && !nuevoValorStr.trim().isEmpty()) { // verificamos que se ingreso un valor
                int nuevoValor = Integer.parseInt(nuevoValorStr);   // convertimos el texto a numero entero
                negocio.reemplazarElementoMatriz(filaSeleccionada, columnaSeleccionada, nuevoValor); // reemplazamos valor en el negocio
                actualizarTablaMatriz();                           // actualizamos la tabla para mostrar el cambio
                mostrarResultadoMatrices("Valor en [" + filaSeleccionada + "," + columnaSeleccionada + "] cambiado a: " + nuevoValor); // mensaje de exito
            }
        } catch (NumberFormatException ex) {                       // capturamos excepcion si el texto no es un numero
            mostrarError("Ingrese un número válido");              // mostramos mensaje de error
        }
    }
    
    private void eliminarFilaMatriz(ActionEvent e) {                // metodo que maneja la eliminacion de filas de la matriz
        try {                                                       // bloque try para manejar excepciones
            int filaSeleccionada = tablaMatriz.getSelectedRow();    // obtenemos la fila seleccionada en la tabla
            if (filaSeleccionada == -1) {                           // verificamos si no hay fila seleccionada
                mostrarError("Seleccione una fila para eliminar");  // mostramos error si no hay seleccion
                return;                                             // salimos del metodo
            }
            
            int confirmacion = JOptionPane.showConfirmDialog(this,  // mostramos dialogo de confirmacion
                "¿Está seguro de eliminar la fila " + filaSeleccionada + "?\n" + // mensaje con numero de fila
                "Tamaño actual: " + negocio.getFilasMatriz() + "x" + negocio.getColumnasMatriz() +  // tamaño actual
                " → Nuevo tamaño: " + (negocio.getFilasMatriz() - 1) + "x" + negocio.getColumnasMatriz(), // nuevo tamaño
                "Confirmar Eliminación de Fila", JOptionPane.YES_NO_OPTION); // titulo y opciones
            
            if (confirmacion == JOptionPane.YES_OPTION) {           // si el usuario confirma la eliminacion
                negocio.eliminarFilaMatriz(filaSeleccionada);      // eliminamos la fila en el negocio
                actualizarTablaMatriz();                           // actualizamos la tabla para mostrar el cambio
                mostrarResultadoMatrices("Fila " + filaSeleccionada + " eliminada. Nueva matriz: " +  // mensaje de exito
                               negocio.getFilasMatriz() + "x" + negocio.getColumnasMatriz()); // con las nuevas dimensiones
            }
        } catch (Exception ex) {                                   // capturamos cualquier excepcion
            mostrarError("Error al eliminar fila: " + ex.getMessage()); // mostramos mensaje de error con detalles
        }
    }
    
    private void eliminarColumnaMatriz(ActionEvent e) {             // metodo que maneja la eliminacion de columnas de la matriz
        try {                                                       // bloque try para manejar excepciones
            int columnaSeleccionada = tablaMatriz.getSelectedColumn(); // obtenemos la columna seleccionada en la tabla
            if (columnaSeleccionada == -1) {                        // verificamos si no hay columna seleccionada
                mostrarError("Seleccione una columna para eliminar"); // mostramos error si no hay seleccion
                return;                                             // salimos del metodo
            }
            
            int confirmacion = JOptionPane.showConfirmDialog(this,  // mostramos dialogo de confirmacion
                "¿Está seguro de eliminar la columna " + columnaSeleccionada + "?\n" + // mensaje con numero de columna
                "Tamaño actual: " + negocio.getFilasMatriz() + "x" + negocio.getColumnasMatriz() +  // tamaño actual
                " → Nuevo tamaño: " + negocio.getFilasMatriz() + "x" + (negocio.getColumnasMatriz() - 1), // nuevo tamaño
                "Confirmar Eliminación de Columna", JOptionPane.YES_NO_OPTION); // titulo y opciones
            
            if (confirmacion == JOptionPane.YES_OPTION) {           // si el usuario confirma la eliminacion
                negocio.eliminarColumnaMatriz(columnaSeleccionada); // eliminamos la columna en el negocio
                actualizarTablaMatriz();                           // actualizamos la tabla para mostrar el cambio
                mostrarResultadoMatrices("Columna " + columnaSeleccionada + " eliminada. Nueva matriz: " + // mensaje de exito
                               negocio.getFilasMatriz() + "x" + negocio.getColumnasMatriz()); // con las nuevas dimensiones
            }
        } catch (Exception ex) {                                   // capturamos cualquier excepcion
            mostrarError("Error al eliminar columna: " + ex.getMessage()); // mostramos mensaje de error con detalles
        }
    }
    
    private void actualizarTablaMatriz() {                          // metodo que actualiza la tabla que muestra la matriz
        modelMatriz.setRowCount(0);                                // eliminamos todas las filas existentes del modelo
        modelMatriz.setColumnCount(0);                             // eliminamos todas las columnas existentes del modelo
        
        int[][] matriz = negocio.getMatriz();                      // obtenemos la matriz actual del negocio
        if (matriz != null && matriz.length > 0) {                 // verificamos que la matriz no sea nula y tenga elementos
            // Crear columnas
            for (int j = 0; j < matriz[0].length; j++) {            // recorremos el numero de columnas de la matriz
                modelMatriz.addColumn("Col " + j);                  // agregamos cada columna al modelo con su numero
            }
            
            // Agregar filas
            for (int i = 0; i < matriz.length; i++) {               // recorremos cada fila de la matriz
                Object[] fila = new Object[matriz[i].length];       // creamos array de objetos para la fila de la tabla
                for (int j = 0; j < matriz[i].length; j++) {        // recorremos cada columna de la fila
                    fila[j] = matriz[i][j];                         // copiamos el valor de la matriz al array de la fila
                }
                modelMatriz.addRow(fila);                           // agregamos la fila completa al modelo de la tabla
            }
        }
    }
    
    // ========== MÉTODOS AUXILIARES MEJORADOS ==========
    
    private void mostrarResultadoVectores(String mensaje) {         // metodo que muestra mensajes en el area de resultados de vectores
        areaResultadosVectores.append(">>> " + mensaje + "\n");     // agregamos el mensaje con formato al area de texto
        areaResultadosVectores.setCaretPosition(areaResultadosVectores.getDocument().getLength()); // movemos cursor al final
    }
    
    private void mostrarResultadoMatrices(String mensaje) {         // metodo que muestra mensajes en el area de resultados de matrices
        areaResultadosMatrices.append(">>> " + mensaje + "\n");     // agregamos el mensaje con formato al area de texto
        areaResultadosMatrices.setCaretPosition(areaResultadosMatrices.getDocument().getLength()); // movemos cursor al final
    }
    
    private void mostrarError(String mensaje) {                     // metodo que muestra mensajes de error en ventana emergente
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE); // mostramos dialogo de error con el mensaje
    }
}