package concole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        CommandProcessor cp = CommandProcessor.getCommandProcessor();
        System.out.println("Nice to see you!");
        System.out.println();
        System.out.println("For more information type \"help\"");
        System.out.println("For exit type \"exit\"");
        System.out.println();
        try (BufferedReader cmd = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                try {
                    cp.executeCommand(readCommand(cmd));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    private static List<String> readCommand(BufferedReader cmd) throws IOException {
        System.out.println("\nEnter command");
        String[] commandArgs = cmd.readLine().trim().split(" ");
        return Arrays.asList(commandArgs);
    }

}
