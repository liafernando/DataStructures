//Worst Case:  O(n^2)
//Average Case:  O(n^2/2^p), where p is a number of increment
//Best Case:  O(n*logn)

// Run Program.Java to open the Main Form of th Application

// The button on the form works both ways. If you want to import the CVS file then just click the sort button
// if you want to use the input boxes mainly then add a data in the 10 input boxes and press again sort but close the
// upload file form fast

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.util.List;

public class Main extends JFrame implements ActionListener {
    private JTextField[] inputFields;
    private JButton sortButton;
    private JTextArea outputArea;
    private JLabel timeLabel;
    private JFileChooser fileChooser;

    // creating the frame and the layout
    public Main() {
        setTitle("CombSort Algorithm");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 10, 5, 5));
        inputFields = new JTextField[10];
        for (int i = 0; i < inputFields.length; i++) {
            inputFields[i] = new JTextField();
            inputPanel.add(inputFields[i]);
        }
        add(inputPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        sortButton = new JButton("Sort");
        sortButton.addActionListener(this);
        buttonPanel.add(sortButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Output panel
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        timeLabel = new JLabel();
        outputPanel.add(timeLabel, BorderLayout.SOUTH);
        add(outputPanel, BorderLayout.SOUTH);

        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortButton) {
            long[] numbersToSort;

            // If user selects a file, read the numbers from the file
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    Scanner scanner = new Scanner(selectedFile);
                    List<Long> numberList = new ArrayList<>();
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] values = line.split(",");
                        for (String value : values) {
                            try {
                                numberList.add(Long.parseLong(value.trim()));
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }
                    numbersToSort = numberList.stream().mapToLong(l -> l).toArray();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                // If user doesn't select a file, read the numbers from the text fields
                numbersToSort = new long[10];
                for (int i = 0; i < inputFields.length; i++) {
                    try {
                        numbersToSort[i] = Long.parseLong(inputFields[i].getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            outputArea.setText("Array before sorting: " + Arrays.toString(numbersToSort) + "\n");

            // Time the sorting algorithm
            long startTime = System.nanoTime();
            combSort(numbersToSort, 10);
            long endTime = System.nanoTime();

            outputArea.append("Array after sorting: " + Arrays.toString(numbersToSort) + "\n");

            long elapsedTimeInMilliseconds = (endTime - startTime) / 1000;
            timeLabel.setText("Time taken for sorting: " + elapsedTimeInMilliseconds + "ms");
        }
    }

    // This is the Comb Sort algorithm that sorts the numbers
    public static void combSort(long[] numbers, long size) {
        long gap = size;
        boolean swapped = true;

        while (gap != 1 || swapped == true) {
            gap = (long)(gap / 1.3);
            if (gap < 1) {
                gap = 1;
            }

            swapped = false;

            for (long i = 0; i < size - gap; i++) {
                if (numbers[(int)i] > numbers[(int)(i + gap)]) {
                    long temp = numbers[(int)i];
                    numbers[(int)i] = numbers[(int)(i + gap)];
                    numbers[(int)(i + gap)] = temp;
                    swapped = true;
                }
            }
        }
    }
//runs the program
    public static void main(String[] args) {
        new Main();
    }
}

