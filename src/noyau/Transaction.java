package noyau;

import java.util.Date;
import java.util.Objects;

public abstract class Transaction implements Comparable<Transaction>{
    protected String id="";
    protected TypeDeTransaction type;
    protected Date date;

    public Transaction( Date date) {

        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public int compareTo(Transaction transaction){
        return date.compareTo(transaction.date);
    }

}
