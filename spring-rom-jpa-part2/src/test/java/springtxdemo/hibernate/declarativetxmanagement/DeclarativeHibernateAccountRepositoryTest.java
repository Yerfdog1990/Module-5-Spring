package springtxdemo.hibernate.declarativetxmanagement;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import springtxdemo.hibernate.account.HibernateAppConfig;
import springtxdemo.hibernate.dao.HibernateAccountRepositoryImpl;
import springtxdemo.hibernate.entity.HibernateAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {HibernateAppConfig.class})
@Transactional
public class DeclarativeHibernateAccountRepositoryTest {
    @Autowired
    private HibernateAccountRepositoryImpl accountRepository;

    @Test
    void testSaveFindById(){
        HibernateAccount hibernateAccount = new HibernateAccount(1, 20000.00);
        accountRepository.save(hibernateAccount);
        HibernateAccount account = accountRepository.findById(hibernateAccount.getId());
        assertEquals(account.getId(), 1);
        assertEquals(account.getBalance(), 20000.00);
    }
}
