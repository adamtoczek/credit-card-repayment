import java.util.ArrayList;
import java.util.List;

public class CreditCard {
    List<Loan> loans = new ArrayList<>();
    List<Repayment> repayments= new ArrayList<>();

    public void addLoan (Loan l) {
        loans.add(l);
    }

    public void addRepayment(String s) {
        String[] p = s.split(";");
        repayments.add(new Repayment(p[0], Double.parseDouble(p[4].replace("PLN", "").replace(",",".").replace(" ",""))));
    }

    public void processRepayments(){
        repayments.sort(new RepaymentSortByDate());
        loans.sort(new LoanSortByDate());



        int i = 0;
        for (Repayment r : repayments) {
            double amount = r.amount;

            while (amount>0.0001) {
                double outstandingamount = loans.get(i).outstandingAmount();
                if (amount > outstandingamount * -1) {
                    loans.get(i).addRepayment(outstandingamount * -1, r.repaymentDate);
                    amount += outstandingamount;
//                    System.out.println("Pelna splata, pozycja i:"+i+" dzienPozyczki:"+loans.get(i).dateTaken + " kwota:"+String.format("%.2f", loans.get(i).amount)+" pozostalo "+String.format("%.2f", amount));
                    i++;

                }
                else {
                    loans.get(i).addRepayment(amount, r.repaymentDate);
//                    System.out.println("Czesciowa splata, pozycja i:"+i+" dzienPozyczki:"+loans.get(i).dateTaken + " kwota:"+String.format("%.2f", amount));
                    amount = 0.;

                }
            }
        }
    }


    public void processEntry(String s) {
        String[] p = s.split(";");
        double amount = Double.parseDouble(p[4].replace("PLN", "").replace(",",".").replace(" ",""));
        if (amount > 0)
            addRepayment(s);
        else
            addLoan(new Loan(p[0], amount, p[1]));
    }

    public void displayUnpayedLoans() {
        System.out.println("########################################");
        System.out.println("# UNPAID LOANS                        #");
        System.out.println("########################################");
        for (Loan loan : loans) {
            if (loan.outstandingAmount()<-0.0001)
                System.out.println(loan);
        }
    }

//    public void displayTotalAmount() {
//        System.out.println("########################################");
//        System.out.println("# TOTAL OUTSTANDING                    #");
//        System.out.println("########################################");
//        System.out.println(String.format("%.2f",getTotalOutstanding()));
//    }

    public void displayTotals() {
        System.out.println("########################################");
        System.out.println("# TOTALS                               #");
        System.out.println("########################################");
        System.out.println("Outstanding: " + String.format("% 9.2f",getTotalOutstanding()));
        System.out.println("   Borrowed: " + String.format("% 9.2f",getTotalBorrowed()));
        System.out.println("     Repaid: " + String.format("% 9.2f",getTotalRepaid()));
    }

    private double getTotalRepaid() {
        double total=0;
        for (Repayment repayment : repayments) {
            total+=repayment.amount;
        }
        return total;
    }

    private double getTotalBorrowed() {
        double total=0;
        for (Loan loan : loans) {
            total+=loan.amount;
        }
        return total;
    }

    private double getTotalOutstanding() {
        double total=0;
        for (Loan loan : loans) {
            if (loan.outstandingAmount()<-0.0001)
                total += loan.outstandingAmount();
        }
        return total;
    }

    public void displayPaidLoans() {
        System.out.println("########################################");
        System.out.println("# PAID LOANS                          #");
        System.out.println("########################################");

        for (Loan loan : loans) {
            if (loan.outstandingAmount()>-0.0001)
                System.out.println(loan);
        }
    }

}
