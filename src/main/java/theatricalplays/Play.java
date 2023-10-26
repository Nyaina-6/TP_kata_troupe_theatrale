package theatricalplays;

public abstract class Play {
    public String name;

    public Play(String name) {
        this.name = name;
    }

    // Méthode pour vérifier la validité du type
    protected boolean ValidType(String type) {
        return type != null && (type.equals("tragedy") || type.equals("comedy"));
    }
    public abstract double calculateAmount(Performance perf);
    public abstract int calculateCredits(Performance perf);
    

}


