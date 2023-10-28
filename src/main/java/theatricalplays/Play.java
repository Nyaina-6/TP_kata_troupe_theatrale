package theatricalplays;

public abstract class Play {
    public String name;

    public Play(String name) {
        this.name = name;
    }

    // Méthode pour vérifier la validité du type
    protected boolean ValidType(String type) {
        return type.equals("tragedy") || type.equals("comedy");
    }
    //pour calculer le montant à payer pour chaque performance
    public abstract double calculateAmountPerformance(Performance perf);
    
    //calcul les credits qu'on attribue aux clients pour chaque performance 
    public abstract int calculateCreditsPerformance(Performance perf);
    

}


