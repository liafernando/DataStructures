package org.example;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HashTableSearchGUI {
    private static MyHashTable hashTable;

    public static void main(String[] args) {
        //Initialize the hash table
        hashTable = new MyHashTable(100);

        //Creating rhe window
        JFrame frame = new JFrame("Hash Table Search algorithm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.PINK);
        GridBagConstraints c = new GridBagConstraints();
        frame.add(panel);

        //upload button
        JButton uploadButton = new JButton("Upload Dataset");
        c.gridx = 0;
        c.gridy = 0;
        panel.add(uploadButton, c);

        //input field adn key
        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Key:"), c);

        JTextField keyField = new JTextField(30);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(keyField, c);

        //search button
        JButton searchButton = new JButton("Search");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        panel.add(searchButton, c);

        //Result and output
        c.gridx = 0;
        c.gridy = 3;
        panel.add(new JLabel("Result:"), c);

        JTextField resultField = new JTextField(20);
        resultField.setEditable(false);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(resultField, c);

        //time output
        c.gridx = 0;
        c.gridy = 4;
        panel.add(new JLabel("Time (ns):"), c);

        JTextField timeField = new JTextField(10);
        timeField.setEditable(false);
        c.gridx = 1;
        c.gridy = 4;
        panel.add(timeField, c);

        //show gui
        frame.setVisible(true);

        //upload button function
        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                loadDataset(selectedFile.getAbsolutePath());
            }
        });
        searchButton.addActionListener(e -> {
            String searchKey = keyField.getText();
            long startTime = System.nanoTime();
            String result = hashTable.get(searchKey);
            long endTime = System.nanoTime();

            long timeTaken = endTime - startTime;
            timeField.setText(Long.toString(timeTaken));
            if (result != null) {
                resultField.setText(result);
            } else {
                resultField.setText("Key not found");
            }
        });
    }

    private static void loadDataset(String datasetFile) {
        hashTable = new MyHashTable(100);
        try {
            BufferedReader br = new BufferedReader(new FileReader(datasetFile));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String movieId = parts[1];
                    hashTable.insert(movieId, line);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
