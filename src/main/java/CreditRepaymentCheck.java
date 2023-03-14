import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;


public class CreditRepaymentCheck {
    public static void main(String[] args) throws IOException {

        File dir = new File(args[0]);
        List<File> list = new ArrayList<>(Arrays.asList(Objects.requireNonNull(dir.listFiles()))).stream().sorted(Comparator.comparing(File::lastModified).reversed()).collect(Collectors.toList());
        BufferedReader reader = null;
        for (File f : list) {
            if (f.getName().contains(".csv") && f.getName().contains("lista_operacji")) {
                System.out.println(f.getName());
                reader = new BufferedReader(new InputStreamReader(Files.newInputStream(f.toPath()), StandardCharsets.UTF_8));
                break;
            }
        }

        CreditCard card = new CreditCard();
        String s;
        boolean startProcessing = false;
        while (true) {
            assert reader != null;
            if ((s = reader.readLine()) == null) break;
            if  (s.length()>0 && startProcessing)
                card.processEntry(s);
            if (s.equals("#Data operacji;#Opis operacji;#Rachunek;#Kategoria;#Kwota;"))
                startProcessing = true;
        }
        card.processRepayments();
        card.displayPaidLoans();
        card.displayUnpayedLoans();
        card.displayTotals();
    }
}
