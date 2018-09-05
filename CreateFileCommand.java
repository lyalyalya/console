package concole;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CreateFileCommand implements Command {
    public void execute(List<String> args) {
        if (args.size() != 1) throw new IllegalArgumentException("Unsupported synopsis, for help type \"help touch\"");
        Path newPath = Paths.get(args.get(0));
        try {
            if (!Files.exists(newPath)) {
                Files.createFile(newPath);
                System.out.printf("Successful, \"%s\" was created\n", args.get(0));
            } else throw new IllegalArgumentException("File already exists");
        } catch (IOException e) {
            throw new IllegalArgumentException("Unsuccessful\n\n" + getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "Creating a new file\nSYNOPSIS\ntouch [new file name] - creating file in current directory\ntouch [path/new file name] - creating file in target directory";
    }
}
