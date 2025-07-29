import java.util.ArrayList;
import java.util.Scanner;
//newproject123
//OOPS PROJECT(USES INHERITANCE,POLYMORPHISM,ENCAPSULATION,ABSTRACTION)

// Abstract base class   (LOKESH PROJECT)
abstract class Person {
    //abstract class
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age; 
    }

    // Encapsulated accessors
    public String getName() { return name; }
    public int getAge() { return age; }

    // Abstract method
    public abstract void displayDetails();
}

// Doctor class (Inheritance)
class Doctor extends Person {
    private String specialization;

    public Doctor(String name, int age, String specialization) {
        super(name, age);
        this.specialization = specialization;
    }

    @Override
    public void displayDetails() {
        System.out.println("Doctor: " + getName() + ", Age: " + getAge() + ", Specialization: " + specialization);
    }
}

// Patient class (Inheritance)
class Patient extends Person {
    private String ailment;

    public Patient(String name, int age, String ailment) {
        super(name, age);
        this.ailment = ailment;
    }

    @Override
    public void displayDetails() {
        System.out.println("Patient: " + getName() + ", Age: " + getAge() + ", Ailment: " + ailment);
    }
}

// Hospital class (Composition)
class Hospital {
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();

    public void addDoctor(Doctor d) {
        doctors.add(d);
    }

    public void addPatient(Patient p) {
        patients.add(p);
    }

    public void showAllDoctors() {
        System.out.println("\n--- Doctors ---");
        for (Doctor d : doctors) {
            d.displayDetails();
        }
    }

    public void showAllPatients() {
        System.out.println("\n--- Patients ---");
        for (Patient p : patients) {
            p.displayDetails();
        }
    }
}

// Main class
public class HospitalManagementSystem {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n--- Hospital Menu ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Show All Doctors");
            System.out.println("4. Show All Patients");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Doctor Name: ");
                    String dName = sc.nextLine();
                    System.out.print("Age: ");
                    int dAge = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Specialization: ");
                    String spec = sc.nextLine();
                    hospital.addDoctor(new Doctor(dName, dAge, spec));
                    break;

                case 2:
                    System.out.print("Patient Name: ");
                    String pName = sc.nextLine();
                    System.out.print("Age: ");
                    int pAge = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ailment: ");
                    String ailment = sc.nextLine();
                    hospital.addPatient(new Patient(pName, pAge, ailment));
                    break;

                case 3:
                    hospital.showAllDoctors();
                    break;

                case 4:
                    hospital.showAllPatients();
                    break;

                case 5:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
