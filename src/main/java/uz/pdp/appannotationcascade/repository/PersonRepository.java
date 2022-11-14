package uz.pdp.appannotationcascade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appannotationcascade.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Integer> {
}
