package concole;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class CommandProcessor {
    private final Map<String, Command> commands;
    private static CommandProcessor commandProcessor;

    private CommandProcessor() {
        commands = new HashMap<>();
        commands.put("RENAME", new RenameFileCommand());
        commands.put("TOUCH", new CreateFileCommand());
        commands.put("MKDIR", new CreateDirectoryCommand());
        commands.put("RM", new DeleteCommand());
        commands.put("ZIP", new ZipCommand());
        commands.put("UNZIP", new UnzipCommand());
        commands.put("MV", new MoveFileCommand());
        commands.put("LS", new ListFilesCommand());
        commands.put("HELP", new Help());
        commands.put("CP", new CopyFileCommand());
        commands.put("EXIT", new Exit());

    }

    public void executeCommand(List<String> args) {
        if (!args.isEmpty() && commands.containsKey(args.get(0).toUpperCase())) {
            commands.get(args.get(0).toUpperCase()).execute(args.subList(1, args.size()));
        } else throw new IllegalArgumentException("Command not found");
    }

    Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(commands);
    }
    static CommandProcessor getCommandProcessor(){
        if (commandProcessor==null) commandProcessor=new CommandProcessor();
        return commandProcessor;
    }
}
