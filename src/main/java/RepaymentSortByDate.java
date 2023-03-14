import java.util.Comparator;

public class RepaymentSortByDate implements Comparator<Repayment> {


    @Override
    public int compare(Repayment o1, Repayment o2) {
        return o1.repaymentDate.compareTo(o2.repaymentDate);
    }
}
