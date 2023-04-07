import javax.swing.*;

class AlgorithmApp {

  public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
        AlgorithmGUI gui = new AlgorithmGUI();
            gui.createGUI();
        });
      }
}
