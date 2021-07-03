package noyau;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class Transaction implements Comparable<Transaction>{
    protected String id="";
    protected TypeDeTransaction type;
    protected Date date;
    protected String stringDate;

    public Transaction( Date date) {
        this.date = date;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        stringDate= simpleDateFormat.format(date);
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
      return transaction.date.compareTo(date);
    }

    public String getDate() {
        return stringDate;
    }

    public Date getDateO() {
        return date;
    }
}
