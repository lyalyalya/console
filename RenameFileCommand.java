package concole;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RenameFileCommand implements Command {
    @Override
    public String getDescription() {
        return "Renaming a file.\nSYNOPSIS\nrename[file name] [new name] - renaming file in current directory\nrename [path/file name] [path/new name] - renaming file in target directory";
    }

    @Override
    public void execute(List<String> args) {
        if (args.size() != 2) {
            throw new IllegalArgumentException("Illegal arguments\n\n" + getDescription());
        } else {
            Path source = Paths.get(args.get(0));
            if (!Files.exists(source)) throw new IllegalArgumentException("File doesn't exist");
            if (Files.exists(Paths.get(args.get(1)))) throw new IllegalArgumentException("New name already in use");
            try {
                Files.move(source, source.resolveSibling(args.get(1)));
                System.out.println("Lucky boy! successful;))");
            } catch (IOException e) {
                throw new IllegalArgumentException("Unsuccessful\n\n" + getDescription());
            }
        }
    }
}
