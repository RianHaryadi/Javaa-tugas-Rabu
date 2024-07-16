/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.util.Scanner;

// Interface untuk objek yang dapat diregistrasi
interface Registrable {
    void register(String username, String password);
}

// Kelas User yang mengimplementasikan interface Registrable
class User implements Registrable {
    protected String username;
    protected String password;

    @Override
    public void register(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("username : " + this.username);
        System.out.println("password : " + this.password);
    }

    void notification() {
        System.out.println("User created by User");
    }
}

// Kelas Admin yang merupakan turunan dari User dan juga mengimplementasikan Registrable
class Admin extends User implements Registrable {
    private final String role = "Admin";

    @Override
    public void notification() {
        System.out.println("User created by " + role);
    }
}

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("REGISTRASI");
        
        System.out.println("Masukkan username:");
        String username = input.nextLine();
        
        System.out.println("Masukkan Password:");
        String password = input.nextLine();
        
        Registrable user = new Admin(); // Instansiasi Admin sebagai Registrable
        user.register(username, password);
        // Memanggil notification tanpa polimorfisme
        if (user instanceof Admin) {
            ((Admin) user).notification(); // Casting ke Admin untuk memanggil notification
        }
        
        input.close();
    }
}

