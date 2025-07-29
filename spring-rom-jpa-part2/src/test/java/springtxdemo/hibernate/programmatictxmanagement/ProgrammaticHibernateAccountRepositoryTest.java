package springtxdemo.hibernate.programmatictxmanagement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import springtxdemo.hibernate.account.HibernateAppConfig;
import springtxdemo.hibernate.dao.HibernateAccountRepositoryImpl;
import springtxdemo.hibernate.entity.HibernateAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {HibernateAppConfig.class})
public class ProgrammaticHibernateAccountRepositoryTest {
    @Autowired
    private HibernateAccountRepositoryImpl accountRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    void testSaveFindById(){
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionManager.getTransaction(transactionDefinition);
        HibernateAccount hibernateAccount = new HibernateAccount(1, 20000.00);
        accountRepository.save(hibernateAccount);
        HibernateAccount retrievedAccount = accountRepository.findById(hibernateAccount.getId());
        transactionManager.commit(transactionManager.getTransaction(transactionDefinition));
        assertEquals(retrievedAccount.getId(), 1);
        assertEquals(retrievedAccount.getBalance(), 20000.00);
    }

}
