package gerenciador;

import javax.swing.*;

public class Validacao {

    public boolean contemTexto(String texto) {
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, digite a informação solicitada...");
            return false;
        }
        return true;
    }
}
