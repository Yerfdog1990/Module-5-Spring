package rest.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.api.model.entity.User;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
}
