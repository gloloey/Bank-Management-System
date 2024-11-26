package edu.yu.cs.intro.bank2023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.yu.cs.intro.bank2023.Transaction.TxType;

/**
 * Models a brokerage account, i.e. an account used to buy, sell, and own stocks
 */
public class BrokerageAccount extends Account{//giusto?
private Map<String, StockShares> sharesMap = new HashMap<>();
    /**
     * This will be called by the Bank class.
     * @param accountNumber the account number assigned by the bank to this new account
     * @param patron the Patron who owns this account
     * @see Bank#openNewBrokerageAccount(Patron)
     */
    protected BrokerageAccount(int accountNumber, Patron patron) {
        super(accountNumber, patron);
    }

    /**
     * @return an unmodifiable list of all the shares of stock currently owned by this account
     * @see java.util.Collections#unmodifiableList(List)
     */
    public List<StockShares> getListOfShares() {
        return Collections.unmodifiableList(new ArrayList<>(sharesMap.values()));

    }

    /**
     * If the transaction is not an instanceof StockTransaction, throw an IllegalArgumentException.
     *
     * If tx.getType() is BUY, do the following:
     *         If there aren't enough shares of the stock available for purchase, throw an InvalidTransactionException.
     *         The total amount of cash needed for the tx  = tx.getQuantity() * tx.getStock().getPrice(). If the patron doesn't have enough cash in his SavingsAccount for this transaction, throw InsufficientAssetsException.
     *         If he does have enough cash, do the following:
     *         1) reduce available share of StockListing by tx.getQuantity()
     *         2) reduce cash in patron's savings account by tx.getQuantity() * StockListing.getPrice()
     *         3)  create a new StockShare for this stock with the quantity set to tx.getQuantity() and listing set to tx.getStock() (or increase StockShare quantity, if there already is a StockShare instance in this account, by tx.getQuantity())
     *         4) add this to the set of transactions recorded in this account
     *
     * If tx.getType() is SELL, do the following:
     *          //If this account doesn't have the specified number of shares in the given stock, throw an InsufficientAssetsException.
     *          //Reduce the patron's shares in the stock by the tx.getQuantity()
     *          //The revenue from the sale = the current price per share of the stock * number of shares to be sold. Use a DEPOSIT transaction to add the revenue to the Patron's savings account.
     *
     * @param tx the transaction to execute on this account
     * @see StockTransaction
     */
    @Override
    public void executeTransaction(Transaction tx) throws InsufficientAssetsException,InvalidTransactionException {
        if (!(tx instanceof StockTransaction)) {
            throw new InvalidTransactionException("hai tirato una InvalidTransactionException", tx.getType());//giusto!
        }
        StockTransaction tx2 = (StockTransaction)tx;
        if (tx2.getType() == TxType.BUY) {
            // If there aren't enough shares of the stock available for purchase:
            if (tx2.getStock().getAvailableShares() < tx2.getQuantity()) {
                throw new InvalidTransactionException("hai tirato una InvalidTransactionException", tx2.getType());
            }
            Double totCashNeeded = tx2.getQuantity() * tx2.getStock().getPrice();
            Double cashDisponibile = getPatron().getSavingsAccount().getValue();
            if (cashDisponibile < totCashNeeded) {
                throw new InsufficientAssetsException(tx2, getPatron());
            }
            // 1)
            // StockListing
            tx2.getStock().reduceAvailableShares(tx2.getQuantity());
            // 2)
            double amount = tx2.getQuantity() * tx2.getStock().getPrice();
            CashTransaction newTrx = new CashTransaction(TxType.WITHDRAW, amount);
            getPatron().getSavingsAccount().executeTransaction(newTrx);
            // 3) create a new StockShare for this stock with the quantity set to tx2.getQuantity() and listing set to tx2.getStock() 
            // (or increase StockShare quantity, if there already is a StockShare instance in this account, by tx2.getQuantity())
            if (sharesMap.get(tx2.getStock().getTickerSymbol()) != null) {
                StockShares lastShares = sharesMap.get(tx2.getStock().getTickerSymbol());
                int quantity = lastShares.getQuantity();
                lastShares.setQuantity(quantity + tx2.getQuantity());
                // 4)
                sharesMap.put(tx2.getStock().getTickerSymbol(), lastShares);// check
            }else {
                StockListing newListing = tx2.getStock();
                StockShares newStSh = new StockShares(newListing);
                newStSh.setQuantity(tx2.getQuantity());
                // 4)
                sharesMap.put(tx2.getStock().getTickerSymbol(), newStSh);// check
            }
            this.transactions.add(tx);
            
        }
        if (tx2.getType() == TxType.SELL) {
            // 1)
            StockShares lastShares = sharesMap.get(tx2.getStock().getTickerSymbol());
            if (tx2.getStock().getAvailableShares() < tx2.getQuantity() && lastShares == null) { // perche qui "StockListing.availableShares() " non mi da errore!?!?!
                throw new InsufficientAssetsException(tx2, getPatron());
            }
            // 2) Reduce the patron's shares in the stock by the tx.getQuantity()
            int quantity = lastShares.getQuantity();
            lastShares.setQuantity(quantity - tx2.getQuantity());
            sharesMap.put(tx2.getStock().getTickerSymbol(), lastShares);
            // 3) The revenue from the sale = the current price per share of the stock * number of shares to be sold. 
            // Use a DEPOSIT transaction to add the revenue to the Patron's savings account.
            double revenue = tx2.getStock().getPrice() * tx2.getQuantity();
            CashTransaction newTrx = new CashTransaction(TxType.DEPOSIT, revenue);
            getPatron().getSavingsAccount().executeTransaction(newTrx);
        }
    }    

    /**
     * the value of a BrokerageAccount is calculated by adding up the values of each StockShare.
     * The value of a StockShare is calculated by multiplying the StockShare quantity by its listing's price.
     * @return
     */
    @Override
    public double getValue() {
        List<StockShares> list = new ArrayList<>(sharesMap.values());
        double brokerageAccountValue = 0;
        for (StockShares stockSH : list) {
            double valueStockShare = stockSH.getQuantity() * stockSH.getListing().getPrice();
            brokerageAccountValue += valueStockShare;
        }
        return brokerageAccountValue;
    }
    
}
