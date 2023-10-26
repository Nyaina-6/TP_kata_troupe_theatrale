package theatricalplays;

import java.util.List;
import java.util.Map;

public class Invoice {
  
  public Customer customer;
  public List<Performance> performances;
  
  public Invoice(Customer customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

public double calculAmount (Performance perf , Play play){
    return play.calculateAmount(perf);
}
public int calculCredits(Invoice invoice, Map<String, Play> plays) {
    int volumeCredits = customer.getVolumeCredits();
    for (Performance perf : invoice.performances) {
        Play play = plays.get(perf.playID);
        volumeCredits += play.calculateCredits(perf);
    }
    return volumeCredits;
}
//calcule le montant total des performances
public double calculateTotalAmount(Invoice invoice, Map<String, Play> plays) {
  double totalAmount = 0;

  for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      totalAmount += calculAmount(perf, play);
  }

  return totalAmount;
}

//Calcule le montant total à payer apres la remise de 15$ dollar s'il y en a
public double calulTotalDiscount(Invoice invoice, Map<String, Play> plays) {
    double totalAmount = calculateTotalAmount(invoice, plays) ;
    if (calculCredits(invoice, plays) > 150) {
        return totalAmount - 15.0;
    } else {
        return totalAmount;
    }
}

}
