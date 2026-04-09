import java.util.*;
import java.io.*;

// ===== INTERFACES =====
interface Billable {
    double calculateBill();

    void printReceipt();
}

interface Schedulable {
    void scheduleAppointment(String date, String time);
}

interface Payable {
    double calculateSalary();
}

interface Storable {
    void addStock(int quantity);

    boolean removeStock(int quantity);
}

// ===== ABSTRACT CLASSES =====
abstract class Person {
    private String id, name;
    private int age;

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract String getRole();
}

abstract class MedicalRecord {
    private String recordId, diagnosis;

    public MedicalRecord(String recordId, String diagnosis) {
        this.recordId = recordId;
        this.diagnosis = diagnosis;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public abstract void display();
}

// ===== CLASSES =====
class Doctor extends Person implements Payable, Schedulable {
    private String specialization;
    private double salary;

    public Doctor(String id, String name, int age, String spec, double salary) {
        super(id, name, age);
        this.specialization = spec;
        this.salary = salary;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getRole() {
        return "Doctor";
    }

    public double calculateSalary() {
        return salary * 1.15;
    }

    public void scheduleAppointment(String d, String t) {
        System.out.println("Appointment set on " + d + " at " + t);
    }
}

class Patient extends Person implements Billable {
    private String bloodType;
    private double fee;

    public Patient(String id, String name, int age, String blood) {
        super(id, name, age);
        this.bloodType = blood;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getRole() {
        return "Patient";
    }

    public double calculateBill() {
        return fee;
    }

    public void printReceipt() {
        System.out.println("Bill: " + calculateBill());
    }
}

class Nurse extends Person implements Payable {
    private double salary;

    public Nurse(String id, String name, int age, double salary) {
        super(id, name, age);
        this.salary = salary;
    }

    public String getRole() {
        return "Nurse";
    }

    public double calculateSalary() {
        return salary * 1.10;
    }
}

class PatientRecord extends MedicalRecord {
    public PatientRecord(String id, String diag) {
        super(id, diag);
    }

    public void display() {
        System.out.println("Record displayed");
    }
}

// ===== MAIN SYSTEM =====
public class HospitalManagementSystem {

    static List<Patient> patients = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadPatients(); // Activity 2

        boolean run = true;

        while (run) {
            System.out.println("\n1. Add Patient\n2. View Patients\n0. Exit");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addPatient();
                        break;
                    case 2:
                        viewPatients();
                        break;
                    case 0:
                        savePatients(); // Activity 2
                        run = false;
                        break;
                }

            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }

    // ===== FEATURES =====
    static void addPatient() {
        try {
            System.out.print("ID: ");
            String id = sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Blood Type: ");
            String blood = sc.nextLine();

            Patient p = new Patient(id, name, age, blood);

            System.out.print("Fee: ");
            double fee = Double.parseDouble(sc.nextLine());
            p.setFee(fee);

            patients.add(p);

        } catch (Exception e) {
            System.out.println("Error adding patient.");
        }
    }

    static void viewPatients() {
        for (Patient p : patients) {
            System.out.println(p.getId() + " - " + p.getName());
        }
    }

    // ===== FILE HANDLING (Activity 2) =====
    static void savePatients() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("patients.txt"))) {
            for (Patient p : patients) {
                bw.write(p.getId() + "," + p.getName() + "," + p.getAge());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Save failed.");
        }
    }

    static void loadPatients() {
        try (BufferedReader br = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                patients.add(new Patient(d[0], d[1], Integer.parseInt(d[2]), "Unknown"));
            }
        } catch (Exception e) {
            System.out.println("No file found.");
        }
    }
}