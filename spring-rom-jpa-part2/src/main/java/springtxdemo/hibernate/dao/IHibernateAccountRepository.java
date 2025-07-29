package springtxdemo.hibernate.dao;

import springtxdemo.hibernate.entity.HibernateAccount;

public interface IHibernateAccountRepository {

    HibernateAccount findById(int id);

    void save(HibernateAccount account);
}
