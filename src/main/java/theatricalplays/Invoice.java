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
    return play.calculateAmount(perf);
}
public int calculCredits(Invoice invoice, Map<String, Play> plays) {
    int volumeCredits = 0;
    for (Performance perf : invoice.performances) {
        Play play = plays.get(perf.playID);
        volumeCredits += play.calculateCredits(perf);
    }
    return volumeCredits;
}
}
