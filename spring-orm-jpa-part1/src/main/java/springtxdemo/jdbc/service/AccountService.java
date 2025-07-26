package springtxdemo.jdbc.service;

public interface AccountService {
    void transferMoney(int fromAccountId, int toAccountId, double amount);
}
