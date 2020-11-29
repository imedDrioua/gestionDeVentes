package noyau;

import java.util.Date;

public abstract class Transaction {
    protected TypeDeTransaction type;
    protected Date date;

    public Transaction(TypeDeTransaction type, Date date) {
        this.type = type;
        this.date = date;
    }
}
