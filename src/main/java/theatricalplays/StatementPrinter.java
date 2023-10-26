package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {
  

  public String print(Invoice invoice, Map<String, Play> plays) {
    Customer customer = invoice.customer; // Récupére l'objet Customer

    int volumeCredits = invoice.calculCredits(invoice, plays);
    StringBuilder result = new StringBuilder();
    result.append(String.format("Statement for %s\n", customer.getName()));

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(invoice.calculAmount(perf, play)), perf.audience));
    }
    //Affiche le montant à payer final en le convertissant en dollar
    result.append(String.format("Amount owed is %s\n", frmt.format(invoice.calculateTotalAmount(invoice,plays))));
    /*Affiche ces 2 lignes si credits > 150 , le gain de 15$ et le montant final à payer apres remise*/
    if (volumeCredits>150){
      double totaldiscount = invoice.calulTotalDiscount (invoice,plays);
      result.append(String.format("You have won  %s\n", frmt.format(15)));
      result.append(String.format("Final Amount owed is %s\n", frmt.format(totaldiscount)));
  }
  //Affiche les credits obtenus et les crédits Actuels , on soustrait 150 si les credits obtenus est plus de 150 aux credits actuels
  result.append(String.format("You earned %s credits\n", volumeCredits));
  result.append(String.format("Your new credits is %s \n", customer.applyCreditsDiscount(invoice, plays)));

    // Convert the StringBuilder to a String and return it
    return result.toString();
}


public String toHTML(Invoice invoice, Map<String, Play> plays) {
      Customer customer = invoice.customer; // Récupére l'objet Customer
      StringBuilder result = new StringBuilder();

      result.append("<html>\n<head><title>Invoice</title></head>\n<body>\n");
      result.append("<h1>Statement for ").append(customer.getName()).append("</h1>\n");
      result.append("<table border=1 >\n");
      result.append("<tr><th>Play</th><th>Seats</th><th>Amount</th></tr>\n");

      NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

      for (Performance perf : invoice.performances) {
          Play play = plays.get(perf.playID);
          double thisAmount = invoice.calculAmount(perf, play) ;
          result.append("<tr><td>").append(play.name).append("</td><td>").append(perf.audience).
          append("</td><td>").append(frmt.format(thisAmount)).append("</td></tr>\n");
          //totalAmount += thisAmount;
      }
      result.append("<tr><td  colspan =2 >Total </td><td>").append(frmt.format(invoice.calculateTotalAmount(invoice,plays))).append("</td><tr>\n");

      /*Si les credits obtenus par le client est superieur à 150 ,
            la condition affiche d'autre ligne en plus (la remise de 15$ et le montant final à payer après remise)*/
      int volumeCredits = invoice.calculCredits(invoice, plays);
      if (volumeCredits> 150 ) {
          double totaldiscount = invoice.calulTotalDiscount (invoice,plays);
          result.append("<tr><td  colspan =2 >Discount </td><td> - ").append(frmt.format(15)).append("</td><tr>\n");
          result.append("<tr><td  colspan =2 >TOTAL </td><td>").append(frmt.format(totaldiscount)).append("</td><tr>\n");
      }
      result.append("</table>\n");
      result.append("<p>Your loyality points</p>\n");

          /*Affiche les crédits obtenus et les crédits actuels si il y a une remise ,
          on a soustrait 150 aux credits Actuels sinon ce sont les crédits obtenus qui sont affichés*/
      result.append("<table border=1 >\n");
      result.append("<tr><th>Credits</th><th>Amount</th></tr>\n");
      result.append("<tr><td  >Owed</td><td>").append(volumeCredits).append("</td></tr>\n");
      result.append("<tr><td  >Actual</td><td>").append(customer.applyCreditsDiscount(invoice, plays)).append("</td></tr>\n");
      result.append("</table>\n");
      
      result.append("<p>You must pay within 15 days otherwise your reservation will be cancelled.</p>\n");
      result.append("</body>\n</html>");

      return result.toString();
  }
  
}
