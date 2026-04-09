import java.util.*;
import java.io.*;

class Patient {
    String id, name;
    int age;

    public Patient(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

public class HospitalSystemActivity2 {

    static List<Patient> patients = new ArrayList<>();

    public static void main(String[] args) {

        patients.add(new Patient("P1", "John", 25));
        patients.add(new Patient("P2", "Aisha", 30));

        savePatients();
        loadPatients();
    }

    static void savePatients() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("patients.txt"))) {
            for (Patient p : patients) {
                bw.write(p.id + "," + p.name + "," + p.age);
                bw.newLine();
            }
            System.out.println("Saved to file");
        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }

    static void loadPatients() {
        try (BufferedReader br = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                System.out.println("Loaded: " + d[1]);
            }
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }
}