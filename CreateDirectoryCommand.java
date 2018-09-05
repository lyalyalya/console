package concole;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CreateDirectoryCommand implements Command {
    @Override
    public void execute(List<String> args) {
        if (args.size() != 1) throw new IllegalArgumentException("Unsuccessful(\n\n" + getDescription());
        Path newDir = Paths.get(args.get(0));
        try {
            if (Files.exists(newDir)) throw new IllegalArgumentException("Directory already exists");
            Files.createDirectories(newDir);
            System.out.println("Successful!");
        } catch (IOException e) {
            throw new IllegalArgumentException("Unsuccessful(\n\n" + getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "Creating a new directory\nSYNOPSIS\nmkdir [new dir name] - creating directory in current directory\nmkdir [path/new dir name] - creating directory in target directory";
    }
}
