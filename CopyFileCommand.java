package concole;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class CopyFileCommand implements Command {
    @Override
    public String getDescription() {
        return "Coping file/directory to the target directory.\nSYNOPSIS\ncp [source] [target `path] - coping file to the target directory";
    }

    @Override
    public void execute(List<String> args) {
        if (args.size() != 2 || !Files.exists(Paths.get(args.get(0))) || !Files.exists(Paths.get(args.get(1))))
            throw new IllegalArgumentException("Illegal arguments\n" + getDescription());
        else {
            try {
                /*Files.copy(Paths.get(args.get(0)),Paths.get(args.get(1),args.get(0)), StandardCopyOption.REPLACE_EXISTING);*/
                final Path source = Paths.get(args.get(0));
                final Path target = Paths.get(args.get(1), args.get(0));
                Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
                        Files.createDirectories(target.resolve(source.relativize(dir)));
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                        Files.copy(file, target.resolve(source.relativize(file)));
                        return FileVisitResult.CONTINUE;
                    }
                });
                System.out.println("Successful!");
            } catch (IOException e) {
                throw new IllegalArgumentException("Something wrong");
            }
        }
    }

}
