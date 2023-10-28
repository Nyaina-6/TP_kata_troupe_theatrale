package theatricalplays;

import java.util.Map;

public class Customer {
    private String name;
    private String customerNumber;
    private int volumeCredits;

    public Customer(String name, String customerNumber, int volumeCredits) {
        this.name = name;
        this.customerNumber = customerNumber;
        this.volumeCredits = volumeCredits;
    }

    public String getName() {
        return name;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }

    //si le client a obtenu plus de 150 credits , on enleve 150 credits à ce qu'il a obtenu
    public int applyCreditsDiscount (Invoice invoice, Map<String, Play> plays){
        int volumeCredits = invoice.calculCredits(invoice, plays) ;
        if (volumeCredits> 150) {
            volumeCredits -= 150 ;
        }
        return volumeCredits;
        
    }

    
    
}
