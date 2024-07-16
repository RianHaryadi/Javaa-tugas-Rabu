import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Interface untuk mendefinisikan metode dasar hewan peliharaan
interface Pet {
    String getName();
    String getType();
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
    private JFrame frame;
    private JTextField petNameField;
    private JComboBox<String> petTypeComboBox;
    private JTextArea outputArea;

    public PetManagementApp() {
        frame = new JFrame("Pet Management");
        frame.setSize(400, 300);
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

        // Area untuk output
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Menambahkan komponen ke frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Listener untuk tombol tambah
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPet();
            }
        });
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

        outputArea.append("Added: " + pet.getType() + " - " + pet.getName() + "\n");
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        PetManagementApp app = new PetManagementApp(); // Pembuatan object PetManagementApp
        app.display();
    }
}