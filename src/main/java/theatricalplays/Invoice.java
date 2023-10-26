package theatricalplays;

import java.util.List;
import java.util.Map;

public class Invoice {
  
  public String customer;
  public List<Performance> performances;
  
  private static final String TRAGEDY = "tragedy";
  private static final String COMEDY = "comedy";

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

public double calculAmount (Performance perf , Play play){   
    double thisAmount = 0;
    switch (play.type) {
        case TRAGEDY:
            thisAmount = 400.0;
            if (perf.audience > 30) {
                thisAmount += 10.0 * (perf.audience - 30);
            }
            break;
        case COMEDY:
            thisAmount = 300.0;
            if (perf.audience > 20) {
                thisAmount += 100.0 + 5.0 * (perf.audience - 20);
            }
            thisAmount += 3.0 * perf.audience;
            break;
        default:
            throw new Error("Unknown type: " + play.type);
    }
    //Retourne du montant final à payer
    return thisAmount ;
}
public int calculCredits(Invoice invoice , Map<String , Play>plays){
          int volumeCredits = 0;
          for (Performance perf : invoice.performances) {
            Play play = plays.get(perf.playID);

            //Calcul des crédits obtenus, pour tout audience > 30
            volumeCredits += Math.max(perf.audience - 30, 0);

            // Ajout de crédits supplements si la preformance est une comédie
            if (COMEDY.equals(play.type)) {
                volumeCredits += Math.floor(perf.audience / 5);
            }
          }
          
          return volumeCredits;
    }
}
