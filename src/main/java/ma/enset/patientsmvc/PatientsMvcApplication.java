package ma.enset.patientsmvc;

import ma.enset.patientsmvc.entites.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }

@Bean
CommandLineRunner commandLineRunner(  PatientRepository patientsRepository){
    return args -> {
        patientsRepository.save(
                new Patient(null, "Hassan", new Date(), false, 4));
        patientsRepository.save(
                new Patient(null, "Khalid", new Date(), false, 5));
        patientsRepository.save(
                new Patient(null, "Omar", new Date(), true, 6));
        patientsRepository.save(
                new Patient(null, "Salah", new Date(), false, 7));
        patientsRepository.save(
                new Patient(null, "Karim", new Date(), true, 8));

        patientsRepository.findAll().forEach(p -> {
            System.out.println(p.getNom());
        });
    };
}
}
