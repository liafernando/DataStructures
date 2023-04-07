import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Program extends JFrame {

    public Program() {
        setTitle("Main Form");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        JLabel label = new JLabel("Select a data structure:");
        panel.add(label);

        JButton arrayListButton = new JButton("ArrayList");
        arrayListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main form = new Main();
                form.setVisible(true);
            }
        });
        panel.add(arrayListButton);

        JButton binaryTreeButton = new JButton("Binary Tree");
        binaryTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                BinaryTreeForm form = new BinaryTreeForm();
//                form.setVisible(true);
            }
        });
        panel.add(binaryTreeButton);

        JButton hashButton = new JButton("Hash");
        hashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                HashForm form = new HashForm();
//                form.setVisible(true);
            }
        });
        panel.add(hashButton);

        add(panel);
    }

    public static void main(String[] args) {
        Program form = new Program();
        form.setVisible(true);
    }
}

