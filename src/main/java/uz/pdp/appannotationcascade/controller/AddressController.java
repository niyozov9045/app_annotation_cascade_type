package uz.pdp.appannotationcascade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appannotationcascade.dto.AddressDto;
import uz.pdp.appannotationcascade.dto.PersonDto;
import uz.pdp.appannotationcascade.entity.Address;
import uz.pdp.appannotationcascade.entity.Person;
import uz.pdp.appannotationcascade.repository.AddressRepository;
import uz.pdp.appannotationcascade.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    PersonRepository personRepository;

    @PostMapping
    public HttpEntity<?> addPerson(@RequestBody List<AddressDto> addressDtoList) {
        Person person = new Person();
        person.setFullName(person.getFullName());
        List<Address> address = new ArrayList<>();
        for (AddressDto addressDto : addressDtoList) {
                Address address1 = new Address(
                        addressDto.getStreet(),
                        addressDto.getCity(),
                        personRepository.getOne(addressDto.getPersonId())
                );
                address.add(address1);
        }
        addressRepository.saveAll(address);
        return ResponseEntity.ok("Saccusfully");
    }

    @PutMapping("{id}")
    public HttpEntity<?> editPerson(@PathVariable Integer id) {
        Person person = personRepository.getOne(id);
        person.setFullName("Ism uzgartirildi");

        for (Address address : person.getAddresses()) {
          address.setStreet("Ko'cha nomi o'zgardi");
        }
        personRepository.save(person);
        return ResponseEntity.ok("Saccusfully");
    }
}
