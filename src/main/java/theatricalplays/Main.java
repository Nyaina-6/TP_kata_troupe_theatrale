package theatricalplays;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // crée une instance de StatementPrinter
        StatementPrinter statementPrinter = new StatementPrinter();
        // prend un client
        Customer customer = new Customer("Lala", "C02", 47);
        // crée un exemple d'Invoice
        Invoice invoice = new Invoice(customer, List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 55),
                new Performance("othello", 60)));

        // crée une collection de Plays choisi par le client
        Map<String, Play> plays = Map.of(
            "hamlet",  new TragedyPlay("Hamlet","tragedy"),
            "as-like", new ComedyPlay("As You Like It" ,"comedy"),
            "othello", new TragedyPlay("Othello","tragedy"));

        // appele la méthode print et toHTML de StatementPrinter
        String statement = statementPrinter.print(invoice, plays);
        String htmlstatement = statementPrinter.toHTML(invoice, plays);
        
        //génére le fichier text
        generateStatementFile(statement, "statement.pdf", "Text Statement");

        // génére le fichier HTML
        generateStatementFile(htmlstatement, "invoice.html", "HTML Statement");
    }
    // la methode qui renvoie les fichiers 
    private static void generateStatementFile(String content, String fileName, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println(message + ":\n" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }



