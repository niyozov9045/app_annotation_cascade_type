package uz.pdp.appannotationcascade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appannotationcascade.entity.Client;

@RepositoryRestResource(path = "client")
public interface ClientRepository extends JpaRepository<Client,Integer> {
}
