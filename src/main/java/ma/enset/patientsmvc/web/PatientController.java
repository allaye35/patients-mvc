package ma.enset.patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.patientsmvc.entites.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;


    @GetMapping( path = "/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0" ) int page,
                           @RequestParam( name= "keyword", defaultValue = "")  String keyword,
                           @RequestParam(name = "size", defaultValue = "5")  int size){
        Page<Patient> pagePatients=patientRepository.findByNomContains(keyword,  PageRequest.of(page, size));
        model.addAttribute("ListPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        return "patients";
    }
    @GetMapping(path = "/deletePatient")
    public  String delete(Long id, String keyword, int page, int size){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword+"&size="+size;
    }

    @GetMapping(path = "/formPatient")
    public List<Patient> listPatient(){
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }
}
