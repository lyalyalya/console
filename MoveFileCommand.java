package concole;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class MoveFileCommand implements Command {
    @Override
    public String getDescription() {
        return "Moving file to the target directory.\nSYNOPSIS\nmv[file name] [path] - moving file to the target directory";
    }

    @Override
    public void execute(List<String> args) {
        if (args.size() != 2) {
            throw new IllegalArgumentException("Illegal arguments\n\n" + getDescription());
        } else {
            Path source = Paths.get(args.get(0));
            if (!Files.exists(source)) throw new IllegalArgumentException("File doesn't exist");
            try {
                Files.move(source, Paths.get(args.get(1), args.get(0)));
                System.out.println("Lucky boy! successful;))");
            } catch (IOException e) {
                throw new IllegalArgumentException("Unsuccessful\n\n" + getDescription());
            }
        }
    }
}
