package theatricalplays;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Créez une instance de StatementPrinter
        StatementPrinter statementPrinter = new StatementPrinter();
        
        Customer customer = new Customer("Lala", "C02", 47);
        // Créez un exemple d'Invoice
        Invoice invoice = new Invoice(customer, List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 55),
                new Performance("othello", 100)));


        // Créez une collection de Plays
        Map<String, Play> plays = Map.of(
            "hamlet",  new TragedyPlay("Hamlet"),
            "as-like", new ComedyPlay("As You Like It"),
            "othello", new TragedyPlay("Othello"));
        // Appelez la méthode print de StatementPrinter
        String statement = statementPrinter.print(invoice, plays);

        // Affichez le résultat
        System.out.println(statement);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("statement.txt"))) {
            writer.write(statement);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String htmlstatement = statementPrinter.toHTML(invoice, plays);
        System.out.println("HTML Statement:\n" + htmlstatement);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("invoice.html"))) {
            writer.write(htmlstatement);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



/*package theatricalplays;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[]args) {
        Map<String, Play> plays = Map.of(
                "hamlet",  new TragedyPlay("Hamlet"),
                "as-like", new ComedyPlay("As You Like It"),
                "othello", new TragedyPlay("Othello"));
        
        Customer customer = new Customer("BigCo", "C01", 0) ;        
        Invoice invoice = new Invoice(customer ,List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        String statement = statementPrinter.toHTML(invoice, plays);
        System.out.println(statement);
        String htmlStatement = statementPrinter.toHTML(invoice, plays);
        System.out.println("HTML Statement:\n" + htmlStatement);

        writeToFile("text_statement.txt", statement);
        writeToFile("html_statement.html", htmlStatement);
    }
    private static void writeToFile(String fileName, String content) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
            System.out.println("File '" + fileName + "' written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file '" + fileName + "': " + e.getMessage());
        }
    }
}
*/