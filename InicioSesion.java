package Proyecto2;

import javax.swing.*;
import java.awt.GridLayout;

public class InicioSesion {

    public static void main(String[] args) {
        while (true) {
            Usuario2 usuarioAutenticado = null;
            while (usuarioAutenticado == null) {
                
                JPanel panel = new JPanel(new GridLayout(3, 2));
                JTextField departamentoField = new JTextField();
                JPasswordField contraseñaField = new JPasswordField();

                panel.add(new JLabel("Departamento:"));
                panel.add(departamentoField);
                panel.add(new JLabel("Contraseña:"));
                panel.add(contraseñaField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Inicio de Sesión", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    String departamento = departamentoField.getText();
                    String contraseña = new String(contraseñaField.getPassword());

                    
                    usuarioAutenticado = Usuario2.validarCredenciales(departamento, contraseña);

                    if (usuarioAutenticado == null) {
                        int opcion = JOptionPane.showConfirmDialog(null, "Credenciales incorrectas. ¿Intentar de nuevo?", "Error", JOptionPane.YES_NO_OPTION);
                        if (opcion == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                            System.exit(0);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    System.exit(0); 
                }
            }

            
            MenuGestion2.mostrarMenuPrincipal();
        }
    }
}