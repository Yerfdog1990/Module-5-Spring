package springmvcdemo.databinding;

public class Price {
  private double amount;
  private String currency;

  public Price(double amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public Price() {
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public String toString() {
    return "Price{" +
        "amount=" + amount +
        ", currency='" + currency + '\'' +
        '}';
  }
}
