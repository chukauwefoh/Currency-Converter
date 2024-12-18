package com.example.currencyconversion;
public class User {
    private String userFromCurrency;
    private String userToCurrency;
    private double amount;

    public User(String userFromCurrency,String userToCurrency, double amount) {
        setUserFromCurrency(userFromCurrency);
        setUserToCurrency(userToCurrency);
        setAmount(amount);
    }


    public String getUserFromCurrency() {
        return this.userFromCurrency;

    }

    public void setUserFromCurrency(String userFromCurrency) {
        this.userFromCurrency = userFromCurrency;

    }

    public String getUserToCurrency() {
        return this.userToCurrency;

    }

    public void setUserToCurrency(String userToCurrency) {
        this.userToCurrency = userToCurrency;

    }

    public double getAmount() {
        return this.amount;

    }

    public void setAmount(double amount) {
        this.amount = amount;

    }


}


