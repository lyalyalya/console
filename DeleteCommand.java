package concole;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;


public class DeleteCommand implements Command {
    @Override
    public String getDescription() {
        return "Deleting file or directory\nSYNOPSIS\nrm [file name] - deleting file/directory in current directory\nrm [path/new file name] - deleting file/directory in target directory";
    }

    @Override
    public void execute(List<String> args) {
        if (args.size() != 1) throw new IllegalArgumentException("Unsuccessful(\n\n" + getDescription());
        Path dir = Paths.get(args.get(0));
        try {
            if (!Files.exists(dir)) throw new IllegalArgumentException("Directory doesn't exist");
            File file = new File(args.get(0));
            if (file.isDirectory() && isDirNotEmpty(dir)) {
                System.out.println("Deleting directory content...");
                deleteFolderAndContent(dir);
            }
            Files.deleteIfExists(dir);
            System.out.println("Successful!");
        } catch (IOException e) {
            throw new IllegalArgumentException("Unsuccessful(\n\n" + getDescription());
        }
    }

    private static boolean isDirNotEmpty(final Path dir) {
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir)) {
            return dirStream.iterator().hasNext();
        } catch (IOException e) {
            throw new IllegalArgumentException("Something wrong");
        }
    }

    private static void deleteFolderAndContent(final Path dir) throws IOException {
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
