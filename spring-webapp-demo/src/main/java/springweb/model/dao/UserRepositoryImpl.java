package springweb.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import springweb.model.entity.User;

@Repository
public class UserRepositoryImpl implements IUserRepository {
  @PersistenceContext private EntityManager entityManager;

  @Override
  public void createUser(User user) {
    entityManager.persist(user);
  }

  @Override
  public User findUserById(Integer id) {
    return entityManager.find(User.class, id);
  }
}
