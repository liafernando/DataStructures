import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class AlgorithmGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextArea inputArea;
    private JTextField searchField;
    private JButton sortButton;
    private JButton searchButton;
    private JLabel resultLabel;
    private JButton importButton;

    public void createGUI() {
        // Creating the panel for the gui
        frame = new JFrame("Algorithm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        panel = new JPanel(new BorderLayout());

        //input area for the gui
        inputArea = new JTextArea();
        inputArea.setText("9, 5, 1, 8, 2, 7, 4, 6, 3");
        JScrollPane scrollPane = new JScrollPane(inputArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        //Control panel for the gui
        JPanel controlPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(10);
        sortButton = new JButton("Sort");
        searchButton = new JButton("Search");
        importButton = new JButton("Import"); // Add an import button
        resultLabel = new JLabel("Results will be displayed here");
        controlPanel.add(searchField);
        controlPanel.add(sortButton);
        controlPanel.add(searchButton);
        controlPanel.add(importButton); // Add the import button to the control panel
        controlPanel.add(resultLabel);
        panel.add(controlPanel, BorderLayout.SOUTH);

        // Adding an action listener to the sortButton
        sortButton.addActionListener(e -> {
            String[] inputArray = inputArea.getText().replaceAll("\\[|\\]|\\s", "").split(",");
            int[] data = new int[inputArray.length]; // Creating an integer array with the same length as the inputArray
            for (int i = 0; i < inputArray.length; i++) { // Looping through each element in the inputArray
                data[i] = Integer.parseInt(inputArray[i]); //parsing the elements to an integer and sorting it in the data array.
            }

            long startTime = System.nanoTime(); //Calculating the starting time
            sortUsingBubble(data);
            long endTime = System.nanoTime(); //Calculating the sorting time
            double elapsedTime = (endTime - startTime) / 1000; //Calculating the time in sec

            DecimalFormat df = new DecimalFormat("#.0");
            String elapsedTimeFormatted = df.format(elapsedTime);
            resultLabel.setText("Sorting time: " + elapsedTimeFormatted + " seconds");

            inputArea.setText(Arrays.toString(data));
        });

        searchButton.addActionListener(e -> {
            String[] inputArray = inputArea.getText().replaceAll("\\[|\\]|\\s", "").split(",");
            int[] data = new int[inputArray.length];
            for (int i = 0; i < inputArray.length; i++) {
                data[i] = Integer.parseInt(inputArray[i]);
            }
            int searchElement = Integer.parseInt(searchField.getText());  // Converting the array of strings to an array of integers.

            long startTime = System.nanoTime();
            int index = binarySearchElement(data, searchElement);
            long endTime = System.nanoTime();
            double elapsedTime = (endTime - startTime) / 1000;

            DecimalFormat df = new DecimalFormat("#.0");
            String elapsedTimeFormatted = df.format(elapsedTime);

            if (index != -1) {
                resultLabel.setText("Element " + searchElement + " is found at index: " + index + ". Search time: " + elapsedTimeFormatted + " seconds");
            } else {
                resultLabel.setText("Element " + searchElement + " is not present in the dataset. Search time: " + elapsedTimeFormatted + " seconds");
            }
        });

        importButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(); // Creating a file chooser
            int result = fileChooser.showOpenDialog(frame); // Show the file chooser dialog
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile(); // Get the selected file
                importCSV(file.getAbsolutePath());
            }
        });

        // Display the frame
        frame.add(panel);
        frame.setVisible(true);
    }

    public void importCSV(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.useDelimiter(",|\\n"); // Set the delimiter to comma or newline

            // Creating an array to store the values read from CSV
            int[] dataArray = new int[100];
            int index = 0;

            while (scanner.hasNext()) {
                String value = scanner.next();
                int intValue = Integer.parseInt(value);
                dataArray[index++] = intValue;
            }

            dataArray = Arrays.copyOf(dataArray, index);

            // the dataArray for further processing
            inputArea.setText(Arrays.toString(dataArray));

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sortUsingBubble(int[] arr) { //Sorting Method
        int arraySize = arr.length;
        boolean hasSwapped;

        for
        (int i = 0; i < arraySize - 1; i++) {
            hasSwapped = false;
            for (int
                 j = 0; j < arraySize - 1 - i; j++) {
                if (arr[j] >
                        arr[j + 1]) { // Swap if the element found is greater

                    int temp = arr[j]; //next element
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    hasSwapped = true;
                }
            }
            // If no swaps occur, the array is already sorted
            if (!hasSwapped) {
                break;
            }
        }
    }

    public static int binarySearchElement(int[] arr, int target) { //binary search method
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}





