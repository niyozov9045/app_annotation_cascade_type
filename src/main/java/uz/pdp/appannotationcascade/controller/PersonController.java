package uz.pdp.appannotationcascade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appannotationcascade.dto.AddressDto;
import uz.pdp.appannotationcascade.dto.PersonDto;
import uz.pdp.appannotationcascade.entity.Address;
import uz.pdp.appannotationcascade.entity.Person;
import uz.pdp.appannotationcascade.repository.AddressRepository;
import uz.pdp.appannotationcascade.repository.PersonRepository;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    // RollbackFor qayssiki xato busa orqaga qaytarilishi
    // noRollbackFor qayssiki xato busa orqaga orqaga qaytarmasligi
    @Transactional(noRollbackFor = ArithmeticException.class) // nullpointerga tushganda orqaga qaytarma didik
                                                              // arithmeticexceptionga tushga orqaga qaytar
    @PostMapping
    public HttpEntity<?> addPerson(@RequestBody PersonDto personDto) {
        //Personni saqlab oldik
        Person person = new Person();
        person.setFullName(personDto.getFullName());

        //Address yasab olamiz
        List<Address> address = new ArrayList<>();
        for (AddressDto addressDto : personDto.getAddressDtoList()) {
            Address address1 = new Address(
                    addressDto.getStreet(),
                    addressDto.getCity(),
                    person
            );
            address.add(address1);
        }
        person.setAddresses(address);
        personRepository.save(person);
        String var = null;
        boolean test = var.equals("test");
        return ResponseEntity.ok("Saccusfully");


    }

    @DeleteMapping("/{id} ")
    public HttpEntity<?> deletePerson(@PathVariable Integer id) {
        try {
            personRepository.deleteById(id);
            return ResponseEntity.ok("Rerson o'chirildi");
        } catch (Exception e) {
            return ResponseEntity.ok("Rerson o'chirilmadi");
        }
    }

    @DeleteMapping("/forTransaction/{id}")
    public HttpEntity<?> deletForTransactional(@PathVariable Integer id) {
        personRepository.deleteById(id);
        throw new NullPointerException();
//        return ResponseEnti   ty.ok("O'chjirildi");
    }

    @GetMapping
    public HttpEntity<?> getPersons() {
        return ResponseEntity.ok(personRepository.findAll());
    }

}
