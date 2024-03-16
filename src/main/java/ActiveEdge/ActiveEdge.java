package ActiveEdge;

import ActiveEdge.Command.ActiveEdgeException;
import ActiveEdge.Parser.Parser;
import ActiveEdge.Ui.ByeUi;

import java.util.Scanner;

public class ActiveEdge {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void run() throws ActiveEdgeException {
        Scanner in = new Scanner(System.in);
        String logo = "ACTIVE EDGE";
        System.out.println("Hello from\n" + logo + " AI assistant!");
        System.out.println("How can I help you today?");

        Parser parser = new Parser();

        String input = in.nextLine();

        while(!input.equals("bye")) {
            parser.handleInput(input);
            input = in.nextLine();
        }
        ByeUi.printByeMessage();
    }

    public static void main(String[] args) throws ActiveEdgeException {
        new ActiveEdge().run();
    }
}