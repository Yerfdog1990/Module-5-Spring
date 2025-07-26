package springtxdemo.jdbc.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class ProgrammaticAccountService implements AccountService{
    private final JdbcTemplate jdbcTemplate;
    private final PlatformTransactionManager transactionManager;

    public ProgrammaticAccountService(JdbcTemplate jdbcTemplate, PlatformTransactionManager transactionManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionManager = transactionManager;
    }

    @Override
    public void transferMoney(int fromAccountId, int toAccountId, double amount) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try{
            // Validate that there are enough balances in the source account
            Double availableBalance = jdbcTemplate.queryForObject("select balance from accounts where id = ?", Double.class, fromAccountId);
            if (availableBalance < amount){
                throw new IllegalArgumentException("Insufficient balance in account " + fromAccountId);
            }
            // Subtract amount from the source account
            jdbcTemplate.update("update accounts set balance = balance - ? where id = ?", amount, fromAccountId);
            // Add amount to teh destination account
            jdbcTemplate.update("update accounts set balance = balance + ? where id = ?", amount, toAccountId);
            transactionManager.commit(transactionStatus);
        } catch (Throwable th){
            transactionManager.rollback(transactionStatus);
            throw th;
        }
    }
}
