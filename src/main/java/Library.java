import javax.swing.*;

public class Library {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUI gui = new LibraryGUI();
            gui.showGUI();
        });
    }
}

