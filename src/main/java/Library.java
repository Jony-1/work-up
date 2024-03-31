import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Library {
        public static void main(String[] args) {
            // (ventana)
            JFrame frame = new JFrame("Work-up");
            frame.setSize(300, 200); // Establecer el tamaño de la ventana
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para cerrar la aplicación cuando se cierra la ventana

            // Crear un panel
            JPanel panel = new JPanel();

            // Crear un botón
            JButton button = new JButton("Haz clic aquí");

            // Agregar un evento de clic al botón
            button.addActionListener(e -> {
                // Mostrar un mensaje de diálogo
                JOptionPane.showMessageDialog(frame, "¡Bienvenido!!!");
            });

            // Agregar el botón al panel
            panel.add(button);

            // Agregar el panel al marco
            frame.add(panel);

            // Hacer visible el marco
            frame.setVisible(true);
        }


}
