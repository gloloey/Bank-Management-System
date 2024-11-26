package edu.yu.cs.intro.bank2023;

import java.util.*;

public class Bank {

private Set<Account> accounts = new HashSet<>();
private Set<Patron> patrons = new HashSet<>();
private StockExchange exchange;
private int patronsId = 0;
private int accountsId = 0;

    /**
     * @param exchange the stock exchange on which all stock are listed
     * @throws IllegalArgumentException if exchange is null
     */
    protected Bank(StockExchange exchange){
        if(exchange == null){
            throw new IllegalArgumentException();
        }
        this.exchange = exchange;
    }
    /**
     * Create a new Patron whose ID is the next unique available Patron ID and whose Bank is set to this bank.
     * Add the new Patron to the Bank's Set of Patrons.
     * No two Patrons can have the same ID. Each ID which is assigned should be greater than the previous ID.
     * @return a new Patron with a unique ID, but no accounts
     */

    public Patron createNewPatron(){
        this.patronsId++;
        Patron newPatron = new Patron(this.patronsId, this);//?
        patrons.add(newPatron);
        return newPatron;
    }

    /**
     * Create a new SavingsAccount for the Patron.
     * The SavingsAccount's id must be the next unique account ID available.
     * No two accounts of ANY KIND can have the same ID. Each ID which is assigned should be greater than the previous ID.
     * Add the new SavingsAccount to the Bank's Set of Accounts.
     * @param p the Patron for whom the account is being created
     * @return the SavingsAccount's id
     * @throws ApplicationDeniedException thrown if Patron already has a SavingsAccount
     * @throws IllegalArgumentException if p is null
     */
    public int openNewSavingsAccount(Patron p) throws ApplicationDeniedException{
        if (p == null){
            throw new IllegalArgumentException();
        }   
        if(p.getSavingsAccount() != null){
            throw new ApplicationDeniedException("Patron already has a SavingsAccount");
        }
        this.accountsId++;
        SavingsAccount newSavingsAccount = new SavingsAccount(this.accountsId, p);
        p.setSavingsAccount(newSavingsAccount);
        accounts.add(newSavingsAccount);
        return this.accountsId;
    }

    /**
     * Create a new BrokerageAccount for the Patron.
     * The BrokerageAccount's id must be the next unique account ID available.
     * No two accounts of ANY KIND can have the same ID. Each ID which is assigned should be greater than the previous ID.
     * Add the new BrokerageAccount to the Bank's Set of Accounts.
     * @param p the Patron for whom the account is being created
     * @return the BrokerageAccount's id
     * @throws ApplicationDeniedException thrown if the Patron doesn't have a SavingsAccount or DOES already have a BorkerageAccount
     * @throws IllegalArgumentException if p is null
     */
    public int openNewBrokerageAccount(Patron p)throws ApplicationDeniedException{
        if (p == null){
            throw new IllegalArgumentException();
        }
        this.accountsId++;
        BrokerageAccount newBrokerageAccount = new BrokerageAccount(this.accountsId, p);
        p.setBrokerageAccount(newBrokerageAccount);
        accounts.add(newBrokerageAccount);
        return this.accountsId;
    }

    /**
     *
     * @return an unmodifiable set of all the accounts (both Savings and Brokerage)
     * @see java.util.Collections#unmodifiableSet(Set)
     */
    protected Set<Account> getAllAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    /**
     *
     * @return an unmodifiable set of all the Patrons
     * @see java.util.Collections#unmodifiableSet(Set)
     */
    protected Set<Patron> getAllPatrons() {
        return Collections.unmodifiableSet(patrons);
    }

    /**
     * @return the exchange used by this Bank
     */
    protected StockExchange getExchange() {
        return this.exchange;
    }
}