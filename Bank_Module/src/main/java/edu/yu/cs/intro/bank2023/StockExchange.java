package edu.yu.cs.intro.bank2023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StockExchange {
    private List<StockListing> stockListings;

    protected StockExchange(){
        this.stockListings = new ArrayList<>();
    }

    /**
     *
     * @param tickerSymbol symbol of the new stock to be created, e.g. "IBM", "GOOG", etc.
     * @param initialPrice price of a single share of the stock
     * @param availableShares how many shares of the stock are available initially
     * @throws IllegalArgumentException if there's already a listing with that tickerSymbol
     */
    public void createNewListing(String tickerSymbol, double initialPrice, int availableShares){
        if (getStockListing(tickerSymbol) != null){
            throw new IllegalArgumentException();
        }
        StockListing newStock = new StockListing(tickerSymbol, initialPrice, availableShares);
        stockListings.add(newStock);
    }

    /**
     * @param tickerSymbol
     * @return the StockListing object for the given tickerSymbol, or null if there is none
     */
    public StockListing getStockListing(String tickerSymbol){
        for (StockListing stockList : stockListings){
            if (stockList.getTickerSymbol().equals(tickerSymbol)){
                return stockList;
            }
        }
        return null;
    }

    /**
     * @return an umodifiable list of all the StockListings currently found on this exchange
     * @see java.util.Collections#unmodifiableList(List)
     */
    public List<StockListing> getAllCurrentListings(){
        return Collections.unmodifiableList(stockListings);
    }
} 