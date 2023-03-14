import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Loan {
    LocalDate dateTaken;
    Double amount;
    LocalDate dueDate;
    List<Repayment> repayments = new ArrayList<>();
    String name;

//    public Loan(String dateTaken, String amount, String name) {
//        String[] date = dateTaken.split("-");
//        this.dateTaken = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
//        this.dueDate = this.dateTaken.plusDays(45);
//        this.amount = Double.parseDouble(amount.replace(",",".").replace(" ",""));
//        this.name = name;
//    }
    public Loan(String dateTaken, Double amount, String name) {
        String[] date = dateTaken.split("-");
        this.dateTaken = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        this.dueDate = this.dateTaken.plusDays(45);
        this.amount = amount;
        this.name = name.replace("\"","").trim();
    }


    public double outstandingAmount() {
        Double result = this.amount;
        for(Repayment r : repayments)
            result += r.amount;

        return result;
    }


    public void addRepayment(Double amount, LocalDate repaymentDate) {
        repayments.add(new Repayment(repaymentDate, amount));
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("" + dateTaken +
                ", " + dueDate +
                ", amount: " + String.format("% 8.2f", amount) +
                ", pozostalo: " + String.format("% 8.2f", outstandingAmount()) +
                ", nazwa: " + name);
        for (Repayment r : repayments)
            s.append("\n\t").append(r.repaymentDate).append(" : ").append(String.format("% 8.2f", r.amount));
        return s.toString();
    }

}
