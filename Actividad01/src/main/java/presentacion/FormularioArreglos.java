package presentacion; // declaramos que esta clase pertenece al paquete presentacion

import negocio.NegocioArreglos;                // importamos la clase de logica de negocio
import javax.swing.*;                           // importamos todos los componentes de Swing
import java.awt.*;                             // importamos componentes de AWT para layout
import java.awt.event.ActionEvent;            // importamos clase para eventos de acciones

public class FormularioArreglos extends JFrame {  // definimos la clase que hereda de JFrame (ventana)
    private NegocioArreglos negocio;              // atributo para manejar la logica de negocio
    private JTextField txtTamVector, txtFilas, txtColumnas; // campos de texto para dimensiones
    private JTextField txtMin, txtMax;           // campos de texto para valores minimo y maximo
    private JTextArea areaResultadosVectores, areaResultadosMatrices; // areas de texto para mostrar resultados
    private JTabbedPane tabbedPane;             // componente de pestañas para separar vectores y matrices
    
    // ===== NUEVOS COMPONENTES PARA CUADRITOS VISUALES =====
    private JPanel panelCuadritosVector;         // panel que contendrá los cuadritos del vector
    private JPanel panelCuadritosMatriz;         // panel que contendrá los cuadritos de la matriz
    private JPanel[] cuadritosVector;            // array de paneles para cada elemento del vector
    private JPanel[][] cuadritosMatriz;          // matriz de paneles para cada celda de la matriz
    private int elementoSeleccionadoVector = -1; // indice del elemento seleccionado en vector (-1 = ninguno)
    private int filaSeleccionadaMatriz = -1;     // fila seleccionada en matriz (-1 = ninguna)
    private int columnaSeleccionadaMatriz = -1;  // columna seleccionada en matriz (-1 = ninguna)
    
    public FormularioArreglos() {                                      // constructor de la clase FormularioArreglos
        negocio = new NegocioArreglos();                               // inicializamos la instancia de la clase de negocio
        initComponents();                                              // llamamos al metodo que inicializa los componentes graficos
        configurarVentana();                                           // configuramos propiedades generales de la ventana
    }
    
    private void configurarVentana() {                                 // metodo para configurar las propiedades de la ventana principal
        setTitle("Gestión de Vectores y Matrices - Actividad 01");  // titulo sin emoji para mejor compatibilidad
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);               // configuramos que la aplicacion se cierre al cerrar la ventana
        setSize(1200, 800);                                           // tamaño mas grande para mejor visualizacion
        setLocationRelativeTo(null);                                  // centramos la ventana en la pantalla
        setResizable(true);                                           // permitimos redimensionar la ventana
        
        // Usar apariencia por defecto de Java Swing
    }
    
    private void initComponents() {                                    // metodo privado para inicializar los componentes de la interfaz
        setLayout(new BorderLayout());                                // establecemos layout principal de la ventana
        
        // Crear panel principal con pestañas mejoradas
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);               // pestañas en la parte superior
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));        // fuente mas grande y negrita para las pestañas
        
        // Pestaña para Vectores con icono dibujado y tooltip
        JPanel panelVector = crearPanelVector();                     // creamos el panel de vectores
        tabbedPane.addTab("Vectores", new IconoContenido(new Color(25, 25, 112)), panelVector); // pestaña con icono dibujado
        tabbedPane.setToolTipTextAt(0, "Operaciones con arreglos unidimensionales"); // tooltip explicativo
        
        // Pestaña para Matrices con icono dibujado y tooltip
        JPanel panelMatriz = crearPanelMatriz();                     // creamos el panel de matrices
        tabbedPane.addTab("Matrices", new IconoMatriz(new Color(139, 0, 139)), panelMatriz); // pestaña con icono dibujado
        tabbedPane.setToolTipTextAt(1, "Operaciones con arreglos bidimensionales"); // tooltip explicativo
        
        // Panel de encabezado con información
        JPanel panelHeader = crearPanelEncabezado();                 // creamos panel de encabezado informativo
        
        add(panelHeader, BorderLayout.NORTH);                        // agregamos encabezado en la parte superior
        add(tabbedPane, BorderLayout.CENTER);                        // agregamos pestañas en el centro
    }
    
    private JPanel crearPanelEncabezado() {                          // metodo que crea panel de encabezado informativo
        JPanel panel = new JPanel(new BorderLayout());              // panel con layout BorderLayout
        panel.setBackground(new Color(240, 248, 255));              // color de fondo azul muy claro
        panel.setBorder(BorderFactory.createEtchedBorder());         // borde con relieve
        
        JLabel titulo = new JLabel("Sistema de Gestión de Vectores y Matrices", SwingConstants.CENTER); // titulo centrado
        titulo.setFont(new Font("Arial", Font.BOLD, 18));           // fuente grande y negrita
        titulo.setForeground(new Color(25, 25, 112));               // color azul oscuro
        
        JLabel instruccion = new JLabel("Seleccione una pestaña para trabajar con vectores o matrices", SwingConstants.CENTER); // instrucciones
        instruccion.setFont(new Font("Arial", Font.ITALIC, 12));    // fuente italica para instrucciones
        instruccion.setForeground(Color.GRAY);                      // color gris para las instrucciones
        
        panel.add(titulo, BorderLayout.CENTER);                     // titulo en el centro
        panel.add(instruccion, BorderLayout.SOUTH);                 // instrucciones abajo
        
        return panel;                                               // retornamos el panel configurado
    }
    
    private JPanel crearPanelVector() {                               // metodo que crea y configura el panel para operaciones con vectores
        JPanel panel = new JPanel(new BorderLayout(10, 10));        // panel principal con espaciado entre componentes
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // margen interno para mejor apariencia
        
        // Panel de controles con título
        JPanel panelControlesConTitulo = crearPanelConTitulo("Configuración del Vector", new Color(25, 25, 112)); // panel con titulo sin emoji
        JPanel panelControles = (JPanel) panelControlesConTitulo.getComponent(1); // obtenemos el panel de contenido
        panelControles.setLayout(new GridLayout(3, 1, 5, 5));       // 3 filas con espaciado entre elementos
        
        // Fila 1: Dimensiones del vector con mejor diseño
        JPanel panelDimensiones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); // layout con espaciado personalizado
        panelDimensiones.setBackground(new Color(248, 248, 255));   // fondo azul muy claro
        panelDimensiones.setBorder(BorderFactory.createTitledBorder("Dimensiones")); // borde con titulo sin emoji
        
        JLabel lblTamaño = new JLabel("Tamaño:");                   // etiqueta mas corta y clara
        lblTamaño.setFont(new Font("Arial", Font.BOLD, 12));        // fuente negrita
        panelDimensiones.add(lblTamaño);                            // agregamos etiqueta
        
        txtTamVector = new JTextField(8);                           // campo mas ancho para mejor usabilidad
        txtTamVector.setToolTipText("Ingrese el número de elementos del vector (ej: 5)"); // tooltip explicativo
        txtTamVector.setFont(new Font("Arial", Font.PLAIN, 12));    // fuente consistente
        panelDimensiones.add(txtTamVector);                         // agregamos campo de texto
        
        JButton btnCrearVector = new JButton("Crear Vector", new IconoCrear(Color.WHITE)); // boton con icono dibujado
        btnCrearVector.setFont(new Font("Arial", Font.BOLD, 11));   // fuente negrita para botones
        btnCrearVector.setBackground(new Color(70, 130, 180));      // color azul atractivo
        btnCrearVector.setForeground(Color.WHITE);                  // texto blanco
        btnCrearVector.setToolTipText("Crear un vector con el tamaño especificado"); // tooltip explicativo
        btnCrearVector.addActionListener(this::crearVector);        // asignamos evento
        panelDimensiones.add(btnCrearVector);                       // agregamos boton
        
        // Fila 2: Rango aleatorio con diseño mejorado
        JPanel panelRango = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); // layout con espaciado
        panelRango.setBackground(new Color(255, 248, 248));         // fondo rosa muy claro
        panelRango.setBorder(BorderFactory.createTitledBorder("Valores Aleatorios")); // borde con titulo sin emoji
        
        JLabel lblMin = new JLabel("Mín:");                         // etiqueta para minimo
        lblMin.setFont(new Font("Arial", Font.BOLD, 12));           // fuente negrita
        panelRango.add(lblMin);                                     // agregamos etiqueta
        
        txtMin = new JTextField(5);                                 // campo mas ancho
        txtMin.setText("1");                                        // valor por defecto
        txtMin.setToolTipText("Valor mínimo para números aleatorios"); // tooltip explicativo
        txtMin.setHorizontalAlignment(JTextField.CENTER);          // texto centrado
        panelRango.add(txtMin);                                     // agregamos campo
        
        JLabel lblMax = new JLabel("Máx:");                         // etiqueta para maximo
        lblMax.setFont(new Font("Arial", Font.BOLD, 12));           // fuente negrita
        panelRango.add(lblMax);                                     // agregamos etiqueta
        
        txtMax = new JTextField(5);                                 // campo mas ancho
        txtMax.setText("100");                                      // valor por defecto
        txtMax.setToolTipText("Valor máximo para números aleatorios"); // tooltip explicativo
        txtMax.setHorizontalAlignment(JTextField.CENTER);          // texto centrado
        panelRango.add(txtMax);                                     // agregamos campo
        
        JButton btnLlenarAleatorio = new JButton("Llenar Aleatorio", new IconoAleatorio(Color.WHITE)); // boton con icono dibujado
        btnLlenarAleatorio.setFont(new Font("Arial", Font.BOLD, 11)); // fuente negrita
        btnLlenarAleatorio.setBackground(new Color(34, 139, 34));   // color verde
        btnLlenarAleatorio.setForeground(Color.WHITE);              // texto blanco
        btnLlenarAleatorio.setToolTipText("Llenar el vector con números aleatorios en el rango especificado"); // tooltip
        btnLlenarAleatorio.addActionListener(this::llenarVectorAleatorio); // asignamos evento
        panelRango.add(btnLlenarAleatorio);                         // agregamos boton
        
        // Fila 3: Panel de operaciones mejorado
        JPanel panelOperaciones = new JPanel(new GridLayout(2, 2, 10, 5)); // rejilla 2x2 con espaciado
        panelOperaciones.setBackground(new Color(248, 255, 248));   // fondo verde muy claro
        panelOperaciones.setBorder(BorderFactory.createTitledBorder("Operaciones")); // borde con titulo sin emoji
        
        // Botones con iconos dibujados y colores
        JButton btnSumar = new JButton("Sumar", new IconoSuma(Color.WHITE)); // boton con icono dibujado
        configurarBoton(btnSumar, new Color(255, 140, 0), "Calcular la suma de todos los elementos"); // naranja
        btnSumar.addActionListener(this::sumarVector);              // asignamos evento
        
        JButton btnContarPrimos = new JButton("Primos", new IconoPrimos(Color.WHITE)); // boton con icono dibujado
        configurarBoton(btnContarPrimos, new Color(138, 43, 226), "Contar y mostrar números primos"); // violeta
        btnContarPrimos.addActionListener(this::contarPrimosVector); // asignamos evento
        
        JButton btnEditar = new JButton("Editar", new IconoEditar(Color.WHITE)); // boton con icono dibujado
        configurarBoton(btnEditar, new Color(30, 144, 255), "Modificar un elemento seleccionado"); // azul
        btnEditar.addActionListener(this::editarValorVector);       // asignamos evento
        
        JButton btnEliminar = new JButton("Eliminar", new IconoEliminar(Color.WHITE)); // boton con icono dibujado
        configurarBoton(btnEliminar, new Color(220, 20, 60), "Eliminar el elemento seleccionado"); // rojo
        btnEliminar.addActionListener(this::eliminarElementoVector); // asignamos evento
        
        JButton btnOrdenar = new JButton("Ordenar", new IconoOrdenar(Color.WHITE)); // boton con icono dibujado
        configurarBoton(btnOrdenar, new Color(34, 139, 34), "Ordenar vector de menor a mayor"); // verde
        btnOrdenar.addActionListener(this::ordenarVector);          // asignamos evento
        
        panelOperaciones.add(btnSumar);                             // agregamos boton de suma
        panelOperaciones.add(btnContarPrimos);                      // agregamos boton de primos
        panelOperaciones.add(btnEditar);                            // agregamos boton de edicion
        panelOperaciones.add(btnEliminar);                          // agregamos boton de eliminacion
        panelOperaciones.add(btnOrdenar);                           // agregamos boton de ordenamiento
        
        panelControles.add(panelDimensiones);                       // agregamos panel de dimensiones
        panelControles.add(panelRango);                             // agregamos panel de rango
        panelControles.add(panelOperaciones);                       // agregamos panel de operaciones
        
        // Panel de cuadritos para mostrar el vector visualmente
        panelCuadritosVector = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); // layout de flujo con espaciado
        panelCuadritosVector.setBackground(Color.WHITE);            // fondo blanco
        panelCuadritosVector.setBorder(BorderFactory.createTitledBorder("Contenido del Vector")); // titulo sin emoji
        
        JScrollPane scrollVector = new JScrollPane(panelCuadritosVector); // scroll para los cuadritos
        scrollVector.setPreferredSize(new Dimension(0, 120));       // altura fija para el scroll
        scrollVector.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // scroll horizontal cuando sea necesario
        scrollVector.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // no scroll vertical
        
        // Área de resultados mejorada para Vectores
        areaResultadosVectores = new JTextArea(6, 50);              // area mas alta para mejor visualizacion
        areaResultadosVectores.setEditable(false);                  // no editable por el usuario
        areaResultadosVectores.setBackground(new Color(245, 245, 245)); // color de fondo mas suave
        areaResultadosVectores.setFont(new Font("Courier New", Font.PLAIN, 12)); // fuente monoespaciada para mejor formato
        areaResultadosVectores.setText(">> Resultados de Operaciones con Vectores:\n" + 
                                      "===============================================\n" +
                                      "Esperando operaciones...\n"); // texto inicial sin emojis
        areaResultadosVectores.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // margen interno
        JScrollPane scrollResultadosVectores = new JScrollPane(areaResultadosVectores); // scroll para resultados
        scrollResultadosVectores.setBorder(BorderFactory.createTitledBorder("Historial de Resultados")); // titulo del scroll sin emoji
        
        // Organizar componentes con mejor distribución
        JPanel panelCentral = new JPanel(new BorderLayout(5, 5));   // panel central con espaciado
        panelCentral.add(scrollVector, BorderLayout.CENTER);        // tabla en el centro
        
        // Layout final optimizado
        panel.add(panelControlesConTitulo, BorderLayout.NORTH);     // controles en la parte superior
        panel.add(panelCentral, BorderLayout.CENTER);               // tabla en el centro
        panel.add(scrollResultadosVectores, BorderLayout.SOUTH);    // resultados en la parte inferior
        
        return panel;                                               // retornamos panel completo
    }
    
    // ========== MÉTODOS AUXILIARES PARA INTERFAZ ==========
    
    private JPanel crearPanelConTitulo(String titulo, Color colorTitulo) { // metodo que crea panel con titulo decorativo
        JPanel panelPrincipal = new JPanel(new BorderLayout());     // panel principal con BorderLayout
        
        JLabel labelTitulo = new JLabel(titulo, new IconoConfiguracion(colorTitulo), SwingConstants.CENTER); // etiqueta con icono y centrada
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));      // fuente negrita para el titulo
        labelTitulo.setForeground(colorTitulo);                     // color personalizado para el titulo
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0)); // espaciado superior e inferior
        
        JPanel panelContenido = new JPanel();                       // panel para el contenido interno
        panelContenido.setBorder(BorderFactory.createEtchedBorder()); // borde con relieve para separacion visual
        
        panelPrincipal.add(labelTitulo, BorderLayout.NORTH);        // titulo en la parte superior
        panelPrincipal.add(panelContenido, BorderLayout.CENTER);    // contenido en el centro
        
        return panelPrincipal;                                      // retornamos el panel completo
    }
    
    private void configurarBoton(JButton boton, Color color, String tooltip) { // metodo para configurar apariencia de botones
        boton.setFont(new Font("Arial", Font.BOLD, 11));            // fuente negrita para todos los botones
        boton.setBackground(color);                                 // color de fondo personalizado
        boton.setForeground(Color.WHITE);                           // texto blanco para contraste
        boton.setToolTipText(tooltip);                              // tooltip explicativo
        boton.setFocusPainted(false);                               // quitar borde de foco
        boton.setBorderPainted(true);                               // mantener borde del boton
        boton.setOpaque(true);                                      // hacer el color visible
    }
    
    private JPanel crearCuadritoElemento(int indice, int valor, boolean esVector) { // metodo para crear un cuadrito individual
        JPanel cuadrito = new JPanel(new BorderLayout());          // panel con BorderLayout para organizar contenido
        cuadrito.setPreferredSize(new Dimension(60, 80));          // tamaño fijo para cada cuadrito
        cuadrito.setBorder(BorderFactory.createRaisedBevelBorder()); // borde con relieve
        cuadrito.setBackground(Color.WHITE);                        // fondo blanco por defecto
        
        // Etiqueta para mostrar el índice/posición
        JLabel lblIndice = new JLabel(String.valueOf(indice), SwingConstants.CENTER); // índice centrado
        lblIndice.setFont(new Font("Arial", Font.BOLD, 10));        // fuente pequeña y negrita
        lblIndice.setForeground(Color.GRAY);                        // color gris para el índice
        lblIndice.setOpaque(true);                                  // fondo opaco
        lblIndice.setBackground(new Color(240, 240, 240));          // fondo gris claro
        
        // Etiqueta para mostrar el valor
        JLabel lblValor = new JLabel(String.valueOf(valor), SwingConstants.CENTER); // valor centrado
        lblValor.setFont(new Font("Arial", Font.BOLD, 14));         // fuente mas grande para el valor
        lblValor.setForeground(new Color(25, 25, 112));             // color azul oscuro
        
        cuadrito.add(lblIndice, BorderLayout.NORTH);                // índice en la parte superior
        cuadrito.add(lblValor, BorderLayout.CENTER);                // valor en el centro
        
        // Agregar funcionalidad de clic para selección
        final int pos = indice;                                     // variable final para usar en el listener
        cuadrito.addMouseListener(new java.awt.event.MouseAdapter() { // listener para clics del mouse
            public void mouseClicked(java.awt.event.MouseEvent e) { // cuando se hace clic en el cuadrito
                if (esVector) {                                     // si es un cuadrito de vector
                    seleccionarElementoVector(pos);                 // seleccionamos el elemento
                } else {                                            // si es cuadrito de matriz
                    // Para matriz necesitaremos manejar fila/columna
                }
            }
        });
        
        return cuadrito;                                            // retornamos el cuadrito configurado
    }
    
    private JPanel crearCuadritoMatriz(int fila, int columna, int valor) { // metodo específico para cuadritos de matriz
        JPanel cuadrito = new JPanel(new BorderLayout());          // panel con BorderLayout
        cuadrito.setPreferredSize(new Dimension(60, 80));          // MISMO tamaño que los cuadritos del vector (cuadrados)
        cuadrito.setBorder(BorderFactory.createRaisedBevelBorder()); // borde con relieve
        cuadrito.setBackground(Color.WHITE);                        // fondo blanco
        
        // Etiqueta para coordenadas [fila,col]
        JLabel lblCoordenadas = new JLabel("[" + fila + "," + columna + "]", SwingConstants.CENTER); // coordenadas
        lblCoordenadas.setFont(new Font("Arial", Font.BOLD, 8));    // fuente muy pequeña
        lblCoordenadas.setForeground(Color.GRAY);                   // color gris
        lblCoordenadas.setOpaque(true);                             // fondo opaco
        lblCoordenadas.setBackground(new Color(240, 240, 240));     // fondo gris claro
        
        // Etiqueta para el valor
        JLabel lblValor = new JLabel(String.valueOf(valor), SwingConstants.CENTER); // valor centrado
        lblValor.setFont(new Font("Arial", Font.BOLD, 12));         // fuente para el valor
        lblValor.setForeground(new Color(139, 0, 139));             // color morado
        
        cuadrito.add(lblCoordenadas, BorderLayout.NORTH);           // coordenadas arriba
        cuadrito.add(lblValor, BorderLayout.CENTER);                // valor en el centro
        
        // Funcionalidad de clic para selección de matriz
        final int f = fila, c = columna;                            // variables finales para el listener
        cuadrito.addMouseListener(new java.awt.event.MouseAdapter() { // listener para clics
            public void mouseClicked(java.awt.event.MouseEvent e) { // cuando se hace clic
                seleccionarElementoMatriz(f, c);                    // seleccionamos el elemento de la matriz
            }
        });
        
        return cuadrito;                                            // retornamos cuadrito configurado
    }
    
    private void seleccionarElementoVector(int indice) {            // metodo para seleccionar elemento del vector
        // Limpiar selección anterior
        if (elementoSeleccionadoVector != -1 && cuadritosVector != null && 
            elementoSeleccionadoVector < cuadritosVector.length) {  // si había selección previa válida
            cuadritosVector[elementoSeleccionadoVector].setBackground(Color.WHITE); // quitar color de selección
        }
        
        elementoSeleccionadoVector = indice;                        // guardar nuevo elemento seleccionado
        if (cuadritosVector != null && indice < cuadritosVector.length) { // si la selección es válida
            cuadritosVector[indice].setBackground(new Color(173, 216, 230)); // color azul claro para selección
        }
    }
    
    private void seleccionarElementoMatriz(int fila, int columna) { // metodo para seleccionar elemento de matriz
        // Limpiar selección anterior
        if (filaSeleccionadaMatriz != -1 && columnaSeleccionadaMatriz != -1 && 
            cuadritosMatriz != null) {                              // si había selección previa válida
            cuadritosMatriz[filaSeleccionadaMatriz][columnaSeleccionadaMatriz].setBackground(Color.WHITE); // quitar selección
        }
        
        filaSeleccionadaMatriz = fila;                              // guardar fila seleccionada
        columnaSeleccionadaMatriz = columna;                        // guardar columna seleccionada
        if (cuadritosMatriz != null) {                              // si la matriz existe
            cuadritosMatriz[fila][columna].setBackground(new Color(221, 160, 221)); // color morado claro para selección
        }
    }
    
    private JPanel crearPanelMatriz() {                               // metodo que crea y configura el panel para operaciones con matrices
        JPanel panel = new JPanel(new BorderLayout(10, 10));        // panel principal con espaciado entre componentes
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // margen interno para mejor apariencia
        
        // Panel de controles con título para matrices
        JPanel panelControlesConTitulo = crearPanelConTitulo("Configuración de la Matriz", new Color(139, 0, 139)); // panel con titulo sin emoji
        JPanel panelControles = (JPanel) panelControlesConTitulo.getComponent(1); // obtenemos el panel de contenido
        panelControles.setLayout(new GridLayout(3, 1, 5, 5));       // 3 filas con espaciado
        
        // Fila 1: Dimensiones de la matriz con mejor diseño
        JPanel panelDimensiones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); // layout con espaciado
        panelDimensiones.setBackground(new Color(255, 248, 255));   // fondo morado muy claro
        panelDimensiones.setBorder(BorderFactory.createTitledBorder("Dimensiones de la Matriz")); // borde con titulo sin emoji
        
        JLabel lblFilas = new JLabel("Filas:");                     // etiqueta para filas
        lblFilas.setFont(new Font("Arial", Font.BOLD, 12));         // fuente negrita
        panelDimensiones.add(lblFilas);                             // agregamos etiqueta
        
        txtFilas = new JTextField(5);                               // campo mas ancho
        txtFilas.setToolTipText("Número de filas de la matriz (ej: 3)"); // tooltip explicativo
        txtFilas.setHorizontalAlignment(JTextField.CENTER);        // texto centrado
        txtFilas.setFont(new Font("Arial", Font.PLAIN, 12));        // fuente consistente
        panelDimensiones.add(txtFilas);                             // agregamos campo
        
        JLabel lblColumnas = new JLabel("Columnas:");               // etiqueta para columnas
        lblColumnas.setFont(new Font("Arial", Font.BOLD, 12));      // fuente negrita
        panelDimensiones.add(lblColumnas);                          // agregamos etiqueta
        
        txtColumnas = new JTextField(5);                            // campo mas ancho
        txtColumnas.setToolTipText("Número de columnas de la matriz (ej: 4)"); // tooltip explicativo
        txtColumnas.setHorizontalAlignment(JTextField.CENTER);     // texto centrado
        txtColumnas.setFont(new Font("Arial", Font.PLAIN, 12));     // fuente consistente
        panelDimensiones.add(txtColumnas);                          // agregamos campo
        
        JButton btnCrearMatriz = new JButton("Crear Matriz", new IconoCrear(Color.WHITE)); // boton con icono dibujado
        configurarBoton(btnCrearMatriz, new Color(72, 61, 139), "Crear una matriz con las dimensiones especificadas"); // azul oscuro
        btnCrearMatriz.addActionListener(this::crearMatriz);        // asignamos evento
        panelDimensiones.add(btnCrearMatriz);                       // agregamos boton
        
        // Fila 2: Panel para llenar matriz
        JPanel panelLlenado = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); // panel para llenado
        panelLlenado.setBackground(new Color(248, 255, 248));       // fondo verde claro
        panelLlenado.setBorder(BorderFactory.createTitledBorder("Llenar Matriz")); // borde con titulo sin emoji
        
        JButton btnLlenarMatriz = new JButton("Llenar Aleatorio", new IconoAleatorio(Color.WHITE)); // boton con icono dibujado
        configurarBoton(btnLlenarMatriz, new Color(34, 139, 34), "Llenar matriz con valores aleatorios"); // verde
        btnLlenarMatriz.addActionListener(this::llenarMatrizAleatoria); // asignamos evento
        panelLlenado.add(btnLlenarMatriz);                          // agregamos boton
        
        // Fila 3: Operaciones de la matriz organizadas en rejilla
        JPanel panelOperaciones = new JPanel(new GridLayout(2, 3, 8, 5)); // rejilla 2x3 con espaciado
        panelOperaciones.setBackground(new Color(255, 250, 240));   // fondo beige claro  
        panelOperaciones.setBorder(BorderFactory.createTitledBorder("Operaciones con Matriz")); // borde con titulo sin emoji
        
        // Primera fila de operaciones
        JButton btnSumarMatriz = new JButton("Sumar", new IconoSuma(Color.WHITE)); // boton con icono dibujado
        configurarBoton(btnSumarMatriz, new Color(255, 140, 0), "Sumar todos los elementos de la matriz"); // naranja
        btnSumarMatriz.addActionListener(this::sumarMatriz);        // asignamos evento
        
        JButton btnPrimosMatriz = new JButton("Primos", new IconoPrimos(Color.WHITE)); // boton con icono dibujado
        configurarBoton(btnPrimosMatriz, new Color(138, 43, 226), "Contar números primos en la matriz"); // violeta
        btnPrimosMatriz.addActionListener(this::contarPrimosMatriz); // asignamos evento
        
        JButton btnEditarMatriz = new JButton("Editar", new IconoEditar(Color.WHITE)); // boton con icono dibujado
        configurarBoton(btnEditarMatriz, new Color(30, 144, 255), "Editar celda seleccionada"); // azul
        btnEditarMatriz.addActionListener(this::editarValorMatriz); // asignamos evento
        
        // Segunda fila de operaciones
        JButton btnEliminarFila = new JButton("Fila", new IconoEliminar(Color.WHITE)); // boton con icono dibujado
        configurarBoton(btnEliminarFila, new Color(220, 20, 60), "Eliminar fila seleccionada"); // rojo
        btnEliminarFila.addActionListener(this::eliminarFilaMatriz); // asignamos evento
        
        JButton btnEliminarColumna = new JButton("Columna", new IconoEliminar(Color.WHITE)); // boton con icono dibujado
        configurarBoton(btnEliminarColumna, new Color(178, 34, 34), "Eliminar columna seleccionada"); // rojo oscuro
        btnEliminarColumna.addActionListener(this::eliminarColumnaMatriz); // asignamos evento
        
        JLabel espacioVacio = new JLabel();                         // espacio vacio para balancear la rejilla
        
        // Agregar botones a la rejilla
        panelOperaciones.add(btnSumarMatriz);                       // suma
        panelOperaciones.add(btnPrimosMatriz);                      // primos
        panelOperaciones.add(btnEditarMatriz);                      // editar
        panelOperaciones.add(btnEliminarFila);                      // eliminar fila
        panelOperaciones.add(btnEliminarColumna);                   // eliminar columna
        panelOperaciones.add(espacioVacio);                         // espacio vacio
        
        panelControles.add(panelDimensiones);                          // panel de dimensiones
        panelControles.add(panelLlenado);                           // panel de llenado
        panelControles.add(panelOperaciones);                       // panel de operaciones
        
        // Panel de cuadritos para mostrar la matriz visualmente
        panelCuadritosMatriz = new JPanel();                        // panel que contendrá la cuadrícula
        panelCuadritosMatriz.setBackground(Color.WHITE);            // fondo blanco
        panelCuadritosMatriz.setBorder(BorderFactory.createTitledBorder("🏁 Contenido de la Matriz")); // titulo
        
        JScrollPane scrollMatriz = new JScrollPane(panelCuadritosMatriz); // scroll para los cuadritos de matriz
        scrollMatriz.setPreferredSize(new Dimension(0, 200));       // altura fija para el scroll
        
        // Área de resultados mejorada para Matrices
        areaResultadosMatrices = new JTextArea(6, 50);              // area mas alta
        areaResultadosMatrices.setEditable(false);                  // no editable
        areaResultadosMatrices.setBackground(new Color(245, 245, 245)); // fondo gris suave
        areaResultadosMatrices.setFont(new Font("Courier New", Font.PLAIN, 12)); // fuente monoespaciada
        areaResultadosMatrices.setText("Resultados de Operaciones con Matrices:\n" + 
                                      "═════════════════════════════════════════════════════\n" +
                                      "Esperando operaciones...\n"); // texto inicial atractivo
        areaResultadosMatrices.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // margen interno
        JScrollPane scrollResultadosMatrices = new JScrollPane(areaResultadosMatrices); // scroll para resultados
        scrollResultadosMatrices.setBorder(BorderFactory.createTitledBorder("Historial de Resultados")); // titulo
        
        // Organizar componentes con mejor distribución
        JPanel panelCentral = new JPanel(new BorderLayout(5, 5));   // panel central con espaciado
        panelCentral.add(scrollMatriz, BorderLayout.CENTER);        // tabla en el centro
        
        // Layout final optimizado para matrices
        panel.add(panelControlesConTitulo, BorderLayout.NORTH);     // controles en la parte superior
        panel.add(panelCentral, BorderLayout.CENTER);               // tabla en el centro
        panel.add(scrollResultadosMatrices, BorderLayout.SOUTH);    // resultados en la parte inferior
        
        return panel;                                               // retornamos panel completo
    }
    
    // ========== MÉTODOS PARA VECTORES ==========
    
    private void crearVector(ActionEvent e) {                       // metodo mejorado para crear vectores
        try {                                                       // bloque try para manejar excepciones
            int tamaño = Integer.parseInt(txtTamVector.getText().trim()); // convertimos texto limpio a entero
            if (tamaño <= 0) {                                      // validamos que el tamaño sea positivo
                mostrarError("[!] El tamaño debe ser mayor a 0");    // mensaje de error mejorado
                txtTamVector.requestFocus();                        // enfocamos el campo con error
                return;                                             // salimos del metodo
            }
            if (tamaño > 1000) {                                    // validamos tamaño maximo razonable
                mostrarError("[!] El tamaño máximo permitido es 1000 elementos");
                txtTamVector.requestFocus();
                return;
            }
            negocio.crearVector(tamaño);                           // creamos el vector en el negocio
            actualizarTablaVector();                               // actualizamos la tabla visual
            mostrarResultadoVectores("[OK] Vector creado exitosamente con " + tamaño + " elementos"); // mensaje de exito mejorado
        } catch (NumberFormatException ex) {                       // capturamos excepcion de formato
            mostrarError("[!] Por favor ingrese un número válido para el tamaño"); // error mas descriptivo
            txtTamVector.requestFocus();                           // enfocamos el campo con error
            txtTamVector.selectAll();                              // seleccionamos todo el texto para reemplazar
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
    
    private void sumarVector(ActionEvent e) {                       // metodo mejorado para sumar elementos del vector
        int suma = negocio.sumarVector();                           // obtenemos la suma del vector
        int[] vector = negocio.getVector();                         // obtenemos el vector para conocer su tamaño
        int tamaño = vector != null ? vector.length : 0;            // calculamos el tamaño actual
        
        mostrarResultadoVectores(String.format("[OK] Suma del vector [%d elementos]: %,d", 
                                              tamaño, suma));       // mensaje formateado con información del tamaño
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
            if (elementoSeleccionadoVector == -1) {                 // verificamos si no hay elemento seleccionado
                mostrarError("[!] Seleccione un cuadrito del vector para editar su valor"); // mostramos error mejorado
                return;                                             // salimos del metodo
            }
            
            String nuevoValorStr = JOptionPane.showInputDialog(this, // mostramos dialogo para ingresar nuevo valor
                "Nuevo valor para la posición " + elementoSeleccionadoVector + ":", // mensaje con posicion seleccionada
                "Editar Elemento del Vector", JOptionPane.QUESTION_MESSAGE); // titulo mejorado
            
            if (nuevoValorStr != null && !nuevoValorStr.trim().isEmpty()) { // verificamos que se ingreso un valor
                int nuevoValor = Integer.parseInt(nuevoValorStr);   // convertimos el texto a numero entero
                negocio.reemplazarElementoVector(elementoSeleccionadoVector, nuevoValor); // reemplazamos usando seleccion
                actualizarTablaVector();                           // actualizamos los cuadritos para mostrar el cambio
                mostrarResultadoVectores("[OK] Valor en posición " + elementoSeleccionadoVector + " cambiado a: " + nuevoValor); // mensaje de exito
            }
        } catch (NumberFormatException ex) {                       // capturamos excepcion si el texto no es un numero
            mostrarError("Ingrese un número válido");              // mostramos mensaje de error
        }
    }
    
    private void eliminarElementoVector(ActionEvent e) {            // metodo que maneja la eliminacion de elementos del vector
        try {                                                       // bloque try para manejar excepciones
            if (elementoSeleccionadoVector == -1) {                 // verificamos si no hay elemento seleccionado
                mostrarError("[!] Seleccione un cuadrito del vector para eliminar"); // error mejorado con emoji
                return;                                             // salimos del metodo
            }
            
            int confirmacion = JOptionPane.showConfirmDialog(this,  // mostramos dialogo de confirmacion
                "🗑️ ¿Está seguro de eliminar el elemento en la posición " + elementoSeleccionadoVector + "?\n" +
                "Esta acción no se puede deshacer.", // mensaje mas descriptivo
                "⚠️ Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); // titulo mejorado
            
            if (confirmacion == JOptionPane.YES_OPTION) {           // si el usuario confirma la eliminacion
                negocio.eliminarElementoVector(elementoSeleccionadoVector); // eliminamos usando seleccion de cuadrito
                actualizarTablaVector();                           // actualizamos los cuadritos para mostrar el cambio
                mostrarResultadoVectores("🗑️ Elemento en posición " + elementoSeleccionadoVector + " eliminado exitosamente"); // mensaje mejorado
            }
        } catch (Exception ex) {                                   // capturamos cualquier excepcion
            mostrarError("Error al eliminar elemento: " + ex.getMessage()); // mostramos mensaje de error con detalles
        }
    }
    
    private void ordenarVector(ActionEvent e) {                     // metodo que maneja el ordenamiento del vector
        try {                                                       // bloque try para manejar excepciones
            int[] vector = negocio.getVector();                     // obtenemos el vector actual
            if (vector == null || vector.length == 0) {            // verificamos si el vector existe y tiene elementos
                mostrarError("[!] No hay vector creado para ordenar"); // mostramos error si no hay vector
                return;                                             // salimos del metodo
            }
            
            // Opciones de ordenamiento para el usuario
            String[] opciones = {
                "Ascendente (Menor a Mayor)", 
                "Descendente (Mayor a Menor)", 
                "Por Frecuencia (Más repetidos primero)"
            };
            
            // Mostramos dialogo con opciones de ordenamiento
            int seleccion = JOptionPane.showOptionDialog(this,
                "Seleccione el tipo de ordenamiento para el vector:",
                "Opciones de Ordenamiento",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]); // opcion por defecto: ascendente
            
            if (seleccion != -1) {                                 // si el usuario selecciono una opcion
                String tipoOrdenamiento = "";                      // variable para el mensaje de resultado
                
                switch (seleccion) {
                    case 0: // Ordenamiento ascendente
                        negocio.ordenarVectorAscendente();
                        tipoOrdenamiento = "ascendente (menor a mayor)";
                        break;
                    case 1: // Ordenamiento descendente
                        negocio.ordenarVectorDescendente();
                        tipoOrdenamiento = "descendente (mayor a menor)";
                        break;
                    case 2: // Ordenamiento por frecuencia
                        negocio.ordenarVectorPorFrecuencia();
                        tipoOrdenamiento = "por frecuencia (más repetidos primero)";
                        break;
                }
                
                actualizarTablaVector();                           // actualizamos los cuadritos para mostrar el cambio
                elementoSeleccionadoVector = -1;                   // limpiamos seleccion ya que las posiciones cambiaron
                mostrarResultadoVectores("[OK] Vector ordenado " + tipoOrdenamiento + " exitosamente"); // mensaje de exito
            }
        } catch (Exception ex) {                                   // capturamos cualquier excepcion
            mostrarError("Error al ordenar vector: " + ex.getMessage()); // mostramos mensaje de error con detalles
        }
    }
    
    private void actualizarTablaVector() {                          // metodo que actualiza los cuadritos que muestran el vector
        panelCuadritosVector.removeAll();                          // eliminamos todos los cuadritos existentes
        int[] vector = negocio.getVector();                        // obtenemos el vector actual del negocio
        
        if (vector != null) {                                      // verificamos que el vector no sea nulo
            cuadritosVector = new JPanel[vector.length];           // creamos array de paneles para cuadritos
            
            for (int i = 0; i < vector.length; i++) {              // recorremos cada elemento del vector
                JPanel cuadrito = crearCuadritoElemento(i, vector[i], true); // creamos cuadrito para este elemento
                cuadritosVector[i] = cuadrito;                     // guardamos referencia al cuadrito
                panelCuadritosVector.add(cuadrito);                // agregamos cuadrito al panel principal
            }
        } else {                                                   // si no hay vector creado
            cuadritosVector = null;                                // limpiar referencia a cuadritos
        }
        
        elementoSeleccionadoVector = -1;                           // limpiar selección
        panelCuadritosVector.revalidate();                         // revalidar el layout
        panelCuadritosVector.repaint();                            // repintar el panel
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
    
    private void sumarMatriz(ActionEvent e) {                       // metodo mejorado para sumar matriz
        int suma = negocio.sumarMatriz();                           // obtenemos la suma de la matriz
        int filas = negocio.getFilasMatriz();                       // obtenemos numero de filas
        int columnas = negocio.getColumnasMatriz();                 // obtenemos numero de columnas
        
        mostrarResultadoMatrices(String.format("[OK] Suma total de la matriz [%dx%d]: %,d", 
                                              filas, columnas, suma)); // mensaje formateado con separador de miles
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
            if (filaSeleccionadaMatriz == -1 || columnaSeleccionadaMatriz == -1) { // verificamos seleccion
                mostrarError("[!] Seleccione un cuadrito de la matriz para editar su valor"); // error mejorado
                return;                                             // salimos del metodo
            }
            
            String nuevoValorStr = JOptionPane.showInputDialog(this, // mostramos dialogo mejorado
                "Nuevo valor para la posición [" + filaSeleccionadaMatriz + "," + columnaSeleccionadaMatriz + "]:", // mensaje con coordenadas
                "Editar Elemento de la Matriz", JOptionPane.QUESTION_MESSAGE); // titulo mejorado
            
            if (nuevoValorStr != null && !nuevoValorStr.trim().isEmpty()) { // verificamos que se ingreso un valor
                int nuevoValor = Integer.parseInt(nuevoValorStr);   // convertimos el texto a numero entero
                negocio.reemplazarElementoMatriz(filaSeleccionadaMatriz, columnaSeleccionadaMatriz, nuevoValor); // reemplazamos usando seleccion
                actualizarTablaMatriz();                           // actualizamos los cuadritos para mostrar el cambio
                mostrarResultadoMatrices("[OK] Valor en [" + filaSeleccionadaMatriz + "," + columnaSeleccionadaMatriz + "] cambiado a: " + nuevoValor); // mensaje mejorado
            }
        } catch (NumberFormatException ex) {                       // capturamos excepcion si el texto no es un numero
            mostrarError("Ingrese un número válido");              // mostramos mensaje de error
        }
    }
    
    private void eliminarFilaMatriz(ActionEvent e) {                // metodo que maneja la eliminacion de filas de la matriz
        try {                                                       // bloque try para manejar excepciones
            if (filaSeleccionadaMatriz == -1) {                     // verificamos si no hay elemento seleccionado
                mostrarError("[!] Seleccione un cuadrito de la matriz para eliminar su fila"); // error mejorado
                return;                                             // salimos del metodo
            }
            
            int confirmacion = JOptionPane.showConfirmDialog(this,  // mostramos dialogo de confirmacion
                "🗑️ ¿Está seguro de eliminar la fila " + filaSeleccionadaMatriz + "?\n" + // mensaje mejorado con fila seleccionada
                "📊 Tamaño actual: " + negocio.getFilasMatriz() + "x" + negocio.getColumnasMatriz() +  // tamaño actual con emoji
                " → Nuevo tamaño: " + (negocio.getFilasMatriz() - 1) + "x" + negocio.getColumnasMatriz() + "\n" +
                "Esta acción no se puede deshacer.", // advertencia adicional
                "⚠️ Confirmar Eliminación de Fila", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); // titulo mejorado
            
            if (confirmacion == JOptionPane.YES_OPTION) {           // si el usuario confirma la eliminacion
                negocio.eliminarFilaMatriz(filaSeleccionadaMatriz); // eliminamos usando seleccion de cuadrito
                actualizarTablaMatriz();                           // actualizamos los cuadritos para mostrar el cambio
                mostrarResultadoMatrices("🗑️ Fila " + filaSeleccionadaMatriz + " eliminada exitosamente. Nueva matriz: " +  // mensaje mejorado
                               negocio.getFilasMatriz() + "x" + negocio.getColumnasMatriz()); // con las nuevas dimensiones
            }
        } catch (Exception ex) {                                   // capturamos cualquier excepcion
            mostrarError("Error al eliminar fila: " + ex.getMessage()); // mostramos mensaje de error con detalles
        }
    }
    
    private void eliminarColumnaMatriz(ActionEvent e) {             // metodo que maneja la eliminacion de columnas de la matriz
        try {                                                       // bloque try para manejar excepciones
            if (columnaSeleccionadaMatriz == -1) {                  // verificamos si no hay elemento seleccionado
                mostrarError("[!] Seleccione un cuadrito de la matriz para eliminar su columna"); // error mejorado
                return;                                             // salimos del metodo
            }
            
            int confirmacion = JOptionPane.showConfirmDialog(this,  // mostramos dialogo de confirmacion
                "🗑️ ¿Está seguro de eliminar la columna " + columnaSeleccionadaMatriz + "?\n" + // mensaje mejorado
                "📊 Tamaño actual: " + negocio.getFilasMatriz() + "x" + negocio.getColumnasMatriz() +  // tamaño con emoji
                " → Nuevo tamaño: " + negocio.getFilasMatriz() + "x" + (negocio.getColumnasMatriz() - 1) + "\n" +
                "Esta acción no se puede deshacer.", // advertencia
                "⚠️ Confirmar Eliminación de Columna", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); // titulo mejorado
            
            if (confirmacion == JOptionPane.YES_OPTION) {           // si el usuario confirma la eliminacion
                negocio.eliminarColumnaMatriz(columnaSeleccionadaMatriz); // eliminamos usando seleccion de cuadrito
                actualizarTablaMatriz();                           // actualizamos los cuadritos para mostrar el cambio
                mostrarResultadoMatrices("🗑️ Columna " + columnaSeleccionadaMatriz + " eliminada exitosamente. Nueva matriz: " + // mensaje mejorado
                               negocio.getFilasMatriz() + "x" + negocio.getColumnasMatriz()); // con las nuevas dimensiones
            }
        } catch (Exception ex) {                                   // capturamos cualquier excepcion
            mostrarError("Error al eliminar columna: " + ex.getMessage()); // mostramos mensaje de error con detalles
        }
    }
    
    private void actualizarTablaMatriz() {                          // metodo que actualiza los cuadritos que muestran la matriz
        panelCuadritosMatriz.removeAll();                          // eliminamos todos los cuadritos existentes
        int[][] matriz = negocio.getMatriz();                      // obtenemos la matriz actual del negocio
        
        if (matriz != null && matriz.length > 0) {                 // verificamos que la matriz no sea nula y tenga elementos
            int filas = matriz.length;                             // numero de filas
            int columnas = matriz[0].length;                       // numero de columnas
            
            // Crear panel contenedor centrado para la cuadrícula
            JPanel panelCuadricula = new JPanel(new GridLayout(filas, columnas, 5, 5)); // cuadrícula con más espaciado
            panelCuadricula.setBackground(Color.WHITE);            // fondo blanco para la cuadrícula
            
            cuadritosMatriz = new JPanel[filas][columnas];         // crear matriz de referencias a cuadritos
            
            // Crear cuadritos para cada celda de la matriz
            for (int i = 0; i < filas; i++) {                      // recorremos cada fila
                for (int j = 0; j < columnas; j++) {               // recorremos cada columna
                    JPanel cuadrito = crearCuadritoMatriz(i, j, matriz[i][j]); // creamos cuadrito cuadrado
                    cuadritosMatriz[i][j] = cuadrito;              // guardamos referencia
                    panelCuadricula.add(cuadrito);                 // agregamos al panel de cuadrícula
                }
            }
            
            // Configurar el panel principal para centrar la cuadrícula
            panelCuadritosMatriz.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // layout centrado con márgenes
            panelCuadritosMatriz.add(panelCuadricula);             // agregar cuadrícula centrada al panel principal
            
        } else {                                                   // si no hay matriz creada
            panelCuadritosMatriz.setLayout(new FlowLayout(FlowLayout.CENTER)); // layout centrado simple
            cuadritosMatriz = null;                                // limpiar referencia
        }
        
        filaSeleccionadaMatriz = -1;                               // limpiar selección de fila
        columnaSeleccionadaMatriz = -1;                            // limpiar selección de columna
        panelCuadritosMatriz.revalidate();                         // revalidar layout
        panelCuadritosMatriz.repaint();                            // repintar panel
    }
    
    // ========== MÉTODOS AUXILIARES MEJORADOS ==========
    
    private void mostrarResultadoVectores(String mensaje) {         // metodo mejorado para mostrar resultados de vectores
        String timestamp = java.time.LocalTime.now().toString().substring(0, 8); // hora actual
        areaResultadosVectores.append(String.format("[%s] %s\n", timestamp, mensaje)); // mensaje con timestamp
        areaResultadosVectores.setCaretPosition(areaResultadosVectores.getDocument().getLength()); // mover al final
    }
    
    private void mostrarResultadoMatrices(String mensaje) {         // metodo mejorado para mostrar resultados de matrices  
        String timestamp = java.time.LocalTime.now().toString().substring(0, 8); // hora actual
        areaResultadosMatrices.append(String.format("[%s] %s\n", timestamp, mensaje)); // mensaje con timestamp
        areaResultadosMatrices.setCaretPosition(areaResultadosMatrices.getDocument().getLength()); // mover al final
    }
    
    private void mostrarError(String mensaje) {                     // metodo mejorado para mostrar errores
        JOptionPane.showMessageDialog(this,                         // ventana padre
                                    mensaje,                         // mensaje del error
                                    "ERROR - Sistema de Vectores y Matrices", // titulo descriptivo sin emoji
                                    JOptionPane.ERROR_MESSAGE);     // tipo de mensaje de error
    }
    
    // ========== CLASES DE ICONOS DIBUJADOS PERSONALIZADOS ==========
    
    // Icono de suma (+)
    private static class IconoSuma implements Icon {
        private Color color;
        public IconoSuma(Color color) { this.color = color; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            // Línea horizontal del +
            g2.drawLine(x + 3, y + 8, x + 13, y + 8);
            // Línea vertical del +
            g2.drawLine(x + 8, y + 3, x + 8, y + 13);
            g2.dispose();
        }
        public int getIconWidth() { return 16; }
        public int getIconHeight() { return 16; }
    }
    
    // Icono de configuración (engranaje)
    private static class IconoConfiguracion implements Icon {
        private Color color;
        public IconoConfiguracion(Color color) { this.color = color; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(1.5f));
            // Círculo central
            g2.fillOval(x + 6, y + 6, 4, 4);
            // Dientes del engranaje
            for (int i = 0; i < 8; i++) {
                double angle = i * Math.PI / 4;
                int x1 = x + 8 + (int)(6 * Math.cos(angle));
                int y1 = y + 8 + (int)(6 * Math.sin(angle));
                int x2 = x + 8 + (int)(4 * Math.cos(angle));
                int y2 = y + 8 + (int)(4 * Math.sin(angle));
                g2.drawLine(x2, y2, x1, y1);
            }
            g2.dispose();
        }
        public int getIconWidth() { return 16; }
        public int getIconHeight() { return 16; }
    }
    
    // Icono de creación/construcción
    private static class IconoCrear implements Icon {
        private Color color;
        public IconoCrear(Color color) { this.color = color; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            // Forma de herramienta/martillo
            g2.drawRect(x + 2, y + 2, 12, 3);
            g2.drawLine(x + 8, y + 5, x + 8, y + 14);
            g2.drawLine(x + 6, y + 12, x + 10, y + 12);
            g2.dispose();
        }
        public int getIconWidth() { return 16; }
        public int getIconHeight() { return 16; }
    }
    
    // Icono de números aleatorios (dados)
    private static class IconoAleatorio implements Icon {
        private Color color;
        public IconoAleatorio(Color color) { this.color = color; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(1.5f));
            // Cubo (dado)
            g2.drawRect(x + 2, y + 4, 8, 8);
            // Puntos del dado
            g2.fillOval(x + 4, y + 6, 2, 2);
            g2.fillOval(x + 4, y + 9, 2, 2);
            g2.fillOval(x + 7, y + 9, 2, 2);
            g2.dispose();
        }
        public int getIconWidth() { return 16; }
        public int getIconHeight() { return 16; }
    }
    
    // Icono de números primos (P)
    private static class IconoPrimos implements Icon {
        private Color color;
        public IconoPrimos(Color color) { this.color = color; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setFont(new Font("Arial", Font.BOLD, 12));
            FontMetrics fm = g2.getFontMetrics();
            String texto = "P";
            int textX = x + (16 - fm.stringWidth(texto)) / 2;
            int textY = y + (16 + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(texto, textX, textY);
            g2.dispose();
        }
        public int getIconWidth() { return 16; }
        public int getIconHeight() { return 16; }
    }
    
    // Icono de edición (lápiz)
    private static class IconoEditar implements Icon {
        private Color color;
        public IconoEditar(Color color) { this.color = color; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            // Lápiz
            g2.drawLine(x + 3, y + 13, x + 10, y + 6);
            g2.drawLine(x + 10, y + 6, x + 12, y + 4);
            g2.drawLine(x + 12, y + 4, x + 14, y + 6);
            // Punta del lápiz
            g2.fillOval(x + 2, y + 12, 3, 3);
            g2.dispose();
        }
        public int getIconWidth() { return 16; }
        public int getIconHeight() { return 16; }
    }
    
    // Icono de eliminar (X)
    private static class IconoEliminar implements Icon {
        private Color color;
        public IconoEliminar(Color color) { this.color = color; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            // X para eliminar
            g2.drawLine(x + 4, y + 4, x + 12, y + 12);
            g2.drawLine(x + 12, y + 4, x + 4, y + 12);
            g2.dispose();
        }
        public int getIconWidth() { return 16; }
        public int getIconHeight() { return 16; }
    }
    
    // Icono de contenido/lista
    private static class IconoContenido implements Icon {
        private Color color;
        public IconoContenido(Color color) { this.color = color; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            // Líneas horizontales (lista)
            g2.drawLine(x + 3, y + 4, x + 13, y + 4);
            g2.drawLine(x + 3, y + 7, x + 13, y + 7);
            g2.drawLine(x + 3, y + 10, x + 13, y + 10);
            g2.drawLine(x + 3, y + 13, x + 13, y + 13);
            g2.dispose();
        }
        public int getIconWidth() { return 16; }
        public int getIconHeight() { return 16; }
    }
    
    // Icono de ordenar (flechas hacia arriba y abajo)
    private static class IconoOrdenar implements Icon {
        private Color color;
        public IconoOrdenar(Color color) { this.color = color; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            // Flecha hacia arriba
            g2.drawLine(x + 6, y + 3, x + 8, y + 1);
            g2.drawLine(x + 8, y + 1, x + 10, y + 3);
            g2.drawLine(x + 8, y + 1, x + 8, y + 7);
            // Flecha hacia abajo
            g2.drawLine(x + 6, y + 13, x + 8, y + 15);
            g2.drawLine(x + 8, y + 15, x + 10, y + 13);
            g2.drawLine(x + 8, y + 15, x + 8, y + 9);
            g2.dispose();
        }
        public int getIconWidth() { return 16; }
        public int getIconHeight() { return 16; }
    }
    
    // Icono de matriz/cuadrícula
    private static class IconoMatriz implements Icon {
        private Color color;
        public IconoMatriz(Color color) { this.color = color; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(1.5f));
            // Cuadrícula 3x3
            for (int i = 0; i < 4; i++) {
                g2.drawLine(x + 3 + i * 3, y + 3, x + 3 + i * 3, y + 12);
                g2.drawLine(x + 3, y + 3 + i * 3, x + 12, y + 3 + i * 3);
            }
            g2.dispose();
        }
        public int getIconWidth() { return 16; }
        public int getIconHeight() { return 16; }
    }
}