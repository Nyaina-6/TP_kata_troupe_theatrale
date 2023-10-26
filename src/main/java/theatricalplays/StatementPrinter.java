package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {
  

  public String print(Invoice invoice, Map<String, Play> plays) {
    int volumeCredits = invoice.calculCredits(invoice, plays);
    StringBuilder result = new StringBuilder();
    double totalAmount= 0;
    result.append(String.format("Statement for %s\n", invoice.customer));

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      double thisAmount = invoice.calculAmount(perf, play);
      totalAmount += thisAmount;
      result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience));
    }
    //Affiche le montant à payer final en le convertissant en dollar
    result.append(String.format("Amount owed is %s\n", frmt.format(totalAmount)));
    //Affiche les credits obtenus
    result.append(String.format("You earned %s credits\n", volumeCredits));

    // Convert the StringBuilder to a String and return it
    return result.toString();
}


public String toHTML(Invoice invoice, Map<String, Play> plays) {
      double totalAmount = 0;
      int volumeCredits = 0;
      StringBuilder result = new StringBuilder();

      result.append("<html>\n<head><title>Invoice</title></head>\n<body>\n");
      result.append("<h1>Statement for ").append(invoice.customer).append("</h1>\n");
      result.append("<table border=1 >\n");
      result.append("<tr><th>Play</th><th>Seats</th><th>Amount</th></tr>\n");

      NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

      for (Performance perf : invoice.performances) {
          Play play = plays.get(perf.playID);
          double thisAmount = invoice.calculAmount(perf, play) ;
          totalAmount += thisAmount;
          result.append("<tr><td>").append(play.name).append("</td><td>").append(perf.audience).
          append("</td><td>").append(frmt.format(thisAmount)).append("</td></tr>\n");
          //totalAmount += thisAmount;
      }
      
      result.append("<tr><td  colspan =2 >TOTAL </td><td>").append(frmt.format(totalAmount)).append("</td><tr>\n");
      result.append("</table>\n");
      volumeCredits = invoice.calculCredits(invoice, plays);
      result.append("<p>You earned ").append(volumeCredits).append(" credits</p>\n");
      result.append("<p>You must pay within 15 days otherwise your reservation will be cancelled.</p>\n");
      result.append("</body>\n</html>");

      return result.toString();
  }
  
}
