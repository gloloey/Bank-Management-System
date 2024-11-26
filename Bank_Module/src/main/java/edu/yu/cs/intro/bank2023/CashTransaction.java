package edu.yu.cs.intro.bank2023;

import edu.yu.cs.intro.bank2023.Transaction.TxType;

/**
 * A CashTransaction is immutable. Value of nanoTimeStamp must be set at time of construction to the return value of System.nanoTime().
 */
public class CashTransaction implements Transaction{
    private double amount;
    private TxType type;
    private final long nanoTimeStamp = System.nanoTime();
    /**
     *
     * @param type
     * @param amount
     * @throws InvalidTransactionException thrown if type is neither DEPOSIT nor WITHDRAW, or if amount <= 0
     */
    public CashTransaction(TxType type, double amount) throws InvalidTransactionException{
        if ((!(type == TxType.DEPOSIT) && !(type ==TxType.WITHDRAW)) || amount <= 0){//check
            throw new InvalidTransactionException("invalid type ", type);
        }
        this.amount = amount;
        this.type = type;
    }

    public double getAmount(){
        return this.amount;
    }
    @Override
    public TxType getType() {
        return this.type;
    }
    @Override
    public long getNanoTimestamp() {
        return this.nanoTimeStamp;
    }
}