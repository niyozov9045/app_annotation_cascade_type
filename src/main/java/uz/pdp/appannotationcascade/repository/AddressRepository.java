package uz.pdp.appannotationcascade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appannotationcascade.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
