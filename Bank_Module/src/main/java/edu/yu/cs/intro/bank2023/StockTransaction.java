package edu.yu.cs.intro.bank2023;
/**
 * A StockTransaction is immutable. Value of nanoTimeStamp must be set at time of construction to the return value of System.nanoTime().
 */
public class StockTransaction implements Transaction{
    private StockListing listing;
    private TxType type;
    private int quantity;
    private final long nanoTimeStamp = System.nanoTime();

    /**
     *
     * @param listing
     * @param type
     * @param quantity
     * @throws InvalidTransactionException thrown if TxType is neither BUY nor SELL, or if quantity <= 0, or if listing == null
     */
    public StockTransaction(StockListing listing, TxType type, int quantity) throws InvalidTransactionException{
        if ((type!=TxType.BUY && type!=TxType.SELL) || quantity <= 0 || listing == null){
            throw new InvalidTransactionException("hai tirato una InvalidTransactionException", type);
        }
        this.listing = listing;
        this.type = type;
        this.quantity = quantity;
    }
    public StockListing getStock(){
        return this.listing;
    }
    public int getQuantity(){
      return this.quantity;
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
