package noyau;

import java.util.Set;

public class Carnet {
    private Set<Transaction> transactions ;


    public Carnet(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
    public void ajouterAuCarnet(Transaction transaction){
        this.transactions.add(transaction);
    }
    public void retierDecarnet(Transaction transaction){
        this.transactions.remove(transaction);
    }
}
