import java.util.*;

// INTERFACES
interface Payable {
    double calculateSalary();
}

interface Billable {
    double calculateBill();
}

// ABSTRACT CLASSES
abstract class Person {
    protected String id, name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void display();
}

// CONCRETE CLASSES
class Doctor extends Person implements Payable {
    double salary;

    public Doctor(String id, String name, double salary) {
        super(id, name);
        this.salary = salary;
    }

    public double calculateSalary() {
        return salary * 1.15;
    }

    public void display() {
        System.out.println("Doctor: " + name + " Salary: " + calculateSalary());
    }
}

class Patient extends Person implements Billable {
    double bill;

    public Patient(String id, String name, double bill) {
        super(id, name);
        this.bill = bill;
    }

    public double calculateBill() {
        return bill;
    }

    public void display() {
        System.out.println("Patient: " + name + " Bill: " + calculateBill());
    }
}

class Nurse extends Person implements Payable {
    double salary;

    public Nurse(String id, String name, double salary) {
        super(id, name);
        this.salary = salary;
    }

    public double calculateSalary() {
        return salary * 1.10;
    }

    public void display() {
        System.out.println("Nurse: " + name + " Salary: " + calculateSalary());
    }
}

// MAIN CLASS
public class HospitalSystemActivity1 {
    public static void main(String[] args) {

        Doctor d = new Doctor("D1", "Dr John", 100000);
        Nurse n = new Nurse("N1", "Grace", 50000);
        Patient p = new Patient("P1", "Ali", 2000);

        d.display();
        n.display();
        p.display();
    }
}