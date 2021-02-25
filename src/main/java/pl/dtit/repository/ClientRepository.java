package pl.dtit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.dtit.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientByEmail(String email);
}
