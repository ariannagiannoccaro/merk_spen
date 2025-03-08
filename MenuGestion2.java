package Proyecto2;
import javax.swing.JOptionPane;

public class MenuGestion2 {
    
    public static void mostrarMenuPrincipal() {
        while (true) {
            String[] opciones = {"Gestión de Usuarios", "Gestión de Artículos", "Solicitud de Artículos", "Gestión de Inventario", "Salir"};
            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menú Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    mostrarMenuUsuarios();
                    break;
                case 1:
                    mostrarMenuArticulos();
                    break;
                case 2:
                    mostrarMenuSolicitudes(); // Nueva función para gestionar solicitudes
                    break;
                case 3:
                    mostrarMenuInventario();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    private static void mostrarMenuUsuarios() {
        while (true) {
            String[] opciones = {"Crear Usuario", "Leer Usuarios", "Actualizar Usuario", "Eliminar Usuario", "Volver"};
            int seleccion = JOptionPane.showOptionDialog(null, "Gestión de Usuarios", "Menú Usuarios",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    Usuario2.crearUsuario();
                    break;
                case 1:
                    Usuario2.leerUsuarios();
                    break;
                case 2:
                    Usuario2.actualizarUsuario();
                    break;
                case 3:
                    Usuario2.eliminarUsuario();
                    break;
                case 4:
                    return; // Vuelve al menú principal
                default:
                    break;
            }
        }
    }

    private static void mostrarMenuArticulos() {
        while (true) {
            String[] opciones = {"Crear Artículo", "Leer Artículos", "Actualizar Artículo", "Modificar Cantidad", "Eliminar Artículo", "Volver"};
            int seleccion = JOptionPane.showOptionDialog(null, "Gestión de Artículos", "Menú Artículos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    Articulo2.crearArticulo();
                    break;
                case 1:
                    Articulo2.leerArticulos();
                    break;
                case 2:
                    Articulo2.actualizarArticulo();
                    break;
                case 3:
                    Articulo2.modificarCantidadArticulo();
                    break;
                case 4:
                    Articulo2.eliminarArticulo();
                    break;
                case 5:
                    return; // Vuelve al menú principal
                default:
                    break;
            }
        }
    }

    private static void mostrarMenuSolicitudes() {
        while (true) {
            String[] opciones = {"Crear Solicitud", "Leer Solicitudes", "Actualizar Solicitud", "Eliminar Solicitud", "Volver"};
            int seleccion = JOptionPane.showOptionDialog(null, "Gestión de Solicitudes", "Menú Solicitudes",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    SolicitudArticulos2.crearSolicitud();
                    break;
                case 1:
                    SolicitudArticulos2.verSolicitudes();
                    break;
                case 2:
                    SolicitudArticulos2.actualizarEstadoSolicitud();
                    break;
                case 3:
                    SolicitudArticulos2.eliminarSolicitud();
                    break;
                case 4:
                    return; // Vuelve al menú principal
                default:
                    break;
            }
        }
    }

    private static void mostrarMenuInventario() {
        while (true) {
            String[] opciones = {"Consultar Inventario", "Artículos más Solicitados", "Reporte por Departamento", "Reporte por Fecha", "Volver"};
            int seleccion = JOptionPane.showOptionDialog(null, "Gestión de Inventario", "Menú Inventario",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    InventarioArticulos2.consultarInventario();
                    break;
                case 1:
                    InventarioArticulos2.mostrarArticulosSolicitados();
                    break;
                case 2:
                    InventarioArticulos2.generarReportePedidosPorDepartamento();
                    break;
                case 3:
                    InventarioArticulos2.generarReportePedidosPorFecha();
                    break;
                case 4:
                    return; 
                default:
                    break;
            }
        }
    }

}
