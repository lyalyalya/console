package concole;

import java.util.List;
import java.util.Map;

public class Help implements Command {
    public void execute(List<String> args) {
        Map<String, Command> commands = CommandProcessor.getCommandProcessor().getCommands();
        if (args.isEmpty()) {
            for (Map.Entry<String, Command> pair : commands.entrySet()) {
                System.out.println(pair.getKey());
                System.out.println(pair.getValue().getDescription());
                System.out.println();
            }
        } else {
            if (commands.containsKey(args.get(0).toUpperCase())) {
                System.out.println(commands.get(args.get(0).toUpperCase()).getDescription());
            } else throw new IllegalArgumentException("command not found");
        }
    }

    public String getDescription() {
        return "I will try to help you;)\n SYNOPSIS\n help [command name] - will show command description\n help - will show all commands with it description";
    }
}
