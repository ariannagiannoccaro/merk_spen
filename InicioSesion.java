package Proyecto2;

import javax.swing.JOptionPane;

public class InicioSesion {

    public static void main(String[] args) {
        
            Usuario2 usuarioAutenticado = null;
            while (usuarioAutenticado == null) {
                String departamento = JOptionPane.showInputDialog("Ingrese el departamento:");
                String contraseña = JOptionPane.showInputDialog("Ingrese la contraseña:");
    
                
                usuarioAutenticado = Usuario2.validarCredenciales(departamento, contraseña);
    
            
                if (usuarioAutenticado == null) {
                    int opcion = JOptionPane.showConfirmDialog(null, "Credenciales incorrectas. ¿Intentar de nuevo?", "Error", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                        System.exit(0); 
                    }
                }
            }
    
            
            MenuGestion2.mostrarMenuPrincipal();
        }
    
}
