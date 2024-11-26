package edu.yu.cs.intro.bank2023;

import edu.yu.cs.intro.bank2023.Transaction.TxType;

public class SavingsAccount extends Account{ 
    private double balance;
   
    protected SavingsAccount(int accountNumber, Patron patron) {
        super(accountNumber,patron);
    }

    /**
     * for a DEPOSIT transaction: increase the balance by transaction amount
     * for a WITHDRAW transaction: decrease the balance by transaction amount
     * add the transaction to the transaction history of this account
     * @param tx
     * @return
     * @throws IllegalArgumentException thrown if tx is not a CashTransaction
     */
    @Override
    public void executeTransaction(Transaction tx) throws InsufficientAssetsException,InvalidTransactionException  {
        
        if (!(tx instanceof CashTransaction)){
            throw new InvalidTransactionException("hai tirato una InvalidTransactionException", tx.getType());//giusto!
        }
        
        CashTransaction tx2 = (CashTransaction)tx;
        if(tx2.getType() == TxType.DEPOSIT){
            this.balance += tx2.getAmount();
        }
        
        if(tx2.getType() == TxType.WITHDRAW){
            if(this.balance < tx2.getAmount()){
                throw new InsufficientAssetsException(tx, getPatron());
            }
            this.balance -= tx2.getAmount();
        }
        this.transactions.add(tx);//?
    }

    /**
     * @return the account's balance
     */
    @Override
    public double getValue() {
        return this.balance;
    }
}