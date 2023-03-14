import java.util.Comparator;

public class LoanSortByDate implements Comparator<Loan> {
    @Override
    public int compare(Loan o1, Loan o2) {
        return o1.dateTaken.compareTo(o2.dateTaken);
    }
}
