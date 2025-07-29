package springtxdemo.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import springtxdemo.hibernate.entity.HibernateAccount;

@Repository
public class HibernateAccountRepositoryImpl implements IHibernateAccountRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public HibernateAccount findById(int id){
        return entityManager.find(HibernateAccount.class, id);
    }
    @Override
    public void save(HibernateAccount account){
        entityManager.persist(account);
    }
}
