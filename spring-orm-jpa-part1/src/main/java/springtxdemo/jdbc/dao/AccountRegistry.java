package springtxdemo.jdbc.dao;

public interface AccountRegistry {
    int registerAccount(String accountName, double balance);
    double getBalance(int id);
}
