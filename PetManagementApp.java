//Kelompok 2 
//  1   RAFRI ATHALLAH MARSHALL -11122167
//  2   ROBIYANSAH              -11122311
//  3   RIAN HARYADI            -11122261
//  4   WISNU DANU BRATA        -11122477

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Interface untuk mendefinisikan metode dasar hewan peliharaan
interface Pet {
    String getName();
    String getType();
    void setName(String name);
    void setType(String type);
}

// Class dasar untuk hewan peliharaan
class Animal implements Pet {
    private String name;
    private String type;

    public Animal(String name, String type) {
        this.name = name;
        this.type = type;
    }

    // Getter untuk name (encapsulation)
    @Override
    public String getName() {
        return name;
    }

    // Getter untuk type (encapsulation)
    @Override
    public String getType() {
        return type;
    }

    // Setter untuk name
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Setter untuk type
    @Override
    public void setType(String type) {
        this.type = type;
    }
}

// Subclass untuk Anjing, mewarisi dari Animal (inheritance)
class Dog extends Animal {
    public Dog(String name) {
        super(name, "Dog");
    }
}

// Subclass untuk Kucing, mewarisi dari Animal (inheritance)
class Cat extends Animal {
    public Cat(String name) {
        super(name, "Cat");
    }
}

// Class utama untuk GUI aplikasi
public class PetManagementApp {
    private final JFrame frame;
    private final JTextField petNameField;
    private final JComboBox<String> petTypeComboBox;
    private final JTextArea outputArea;
    private final JTable petTable;
    private final DefaultTableModel tableModel;
    private final List<Pet> petList;

    public PetManagementApp() {
        frame = new JFrame("Pet Management");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel untuk input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Pet Name:"));
        petNameField = new JTextField();
        inputPanel.add(petNameField);

        inputPanel.add(new JLabel("Pet Type:"));
        petTypeComboBox = new JComboBox<>(new String[]{"Dog", "Cat"});
        inputPanel.add(petTypeComboBox);

        JButton addButton = new JButton("Add Pet");
        inputPanel.add(addButton);

        JButton editButton = new JButton("Edit Pet");
        inputPanel.add(editButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Area untuk output
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Table model
        petList = new ArrayList<>();
        tableModel = new DefaultTableModel(new String[]{"Type", "Name"}, 0);
        petTable = new JTable(tableModel);
        frame.add(new JScrollPane(petTable), BorderLayout.SOUTH);

        // Listener untuk tombol tambah
        addButton.addActionListener(e -> addPet());

        // Listener untuk tombol edit
        editButton.addActionListener(e -> editPet());
    }

    private void addPet() {
        String name = petNameField.getText();
        String type = (String) petTypeComboBox.getSelectedItem();

        Pet pet;
        if (type.equals("Dog")) {
            pet = new Dog(name); // Pembuatan object Dog
        } else {
            pet = new Cat(name); // Pembuatan object Cat
        }

        petList.add(pet);
        tableModel.addRow(new Object[]{pet.getType(), pet.getName()});

        outputArea.append("Added: " + pet.getType() + " - " + pet.getName() + "\n");
    }

    private void editPet() {
        int selectedRow = petTable.getSelectedRow();
        if (selectedRow != -1) {
            String newName = JOptionPane.showInputDialog("Enter new pet name:");
            String newType = (String) JOptionPane.showInputDialog(frame, "Select pet type:", "Pet Type",
                    JOptionPane.QUESTION_MESSAGE, null, new String[]{"Dog", "Cat"}, petTypeComboBox.getSelectedItem());

            Pet selectedPet = petList.get(selectedRow);
            selectedPet.setName(newName);
            selectedPet.setType(newType);
            tableModel.setValueAt(newType, selectedRow, 0);
            tableModel.setValueAt(newName, selectedRow, 1);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a pet to edit.");
        }
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        PetManagementApp app = new PetManagementApp();
        app.display();
    }
}