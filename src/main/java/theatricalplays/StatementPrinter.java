package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {
  private static final String TRAGEDY = "tragedy";
  private static final String COMEDY = "comedy";

  public String print(Invoice invoice, Map<String, Play> plays) {
    double totalAmount = 0;
    int volumeCredits = 0;
    StringBuilder result = new StringBuilder();

    result.append(String.format("Statement for %s\n", invoice.customer));

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    
    //calcule la facture
    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      double thisAmount = 0;

      switch (play.type) {
        case TRAGEDY:
          thisAmount = 400.00;
          if (perf.audience > 30) {
            thisAmount += 10.00 * (perf.audience - 30);
          }
          break;
        case COMEDY:
          thisAmount = 300.00;
          if (perf.audience > 20) {
            thisAmount += 100.00 + 5.00 * (perf.audience - 20);
          }
          thisAmount += 3.00 * perf.audience;
          break;
        default:
          throw new Error("unknown type: ${play.type}");
      }

      //ajout de credits(points de fidelité)
      volumeCredits += Math.max(perf.audience - 30, 0);
      //ajout de points suplémentaire si c'est une comedie
      if ("comedy".equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      //affiche le montant de chaque performance
      result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience));
      //calcule le montant total à payer
      totalAmount += thisAmount;
    }
    //affiche le montant total à payer et les credits
    result.append(String.format("Amount owed is %s\n", frmt.format(totalAmount)));
    result.append(String.format("You earned %s credits\n", volumeCredits));
    return result.toString();
  }

}
