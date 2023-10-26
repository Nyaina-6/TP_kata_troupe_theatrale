package theatricalplays;

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

        Invoice invoice = new Invoice("BigCo", List.of(
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
