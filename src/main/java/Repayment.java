import java.time.LocalDate;

public class Repayment {
    LocalDate repaymentDate;
    Double amount;

    public Repayment(String repaymentDate, Double amount) {
        String[] date = repaymentDate.split("-");
        this.repaymentDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        this.amount = amount;
    }

    public Repayment(LocalDate repaymentDate, Double amount) {
        this.repaymentDate = repaymentDate;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return repaymentDate +": "+ String.format("%.2f", amount).replace(",",".");
    }
}
