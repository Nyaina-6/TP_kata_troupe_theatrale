package theatricalplays;

public abstract class Play {
    public String name;

    public Play(String name) {
        this.name = name;
    }

    public abstract double calculateAmount(Performance perf);
    public abstract int calculateCredits(Performance perf);
}


