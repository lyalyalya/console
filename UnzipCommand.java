package concole;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipCommand implements Command {
    @Override
    public String getDescription() {
        return "Unzipping a file/directory.\nSYNOPSIS\nunzip[source name] - unzip archive\n";
    }

    @Override
    public void execute(List<String> args) {
        if (args.size() != 1) throw new IllegalArgumentException("Illegal arguments");
        if (!(new File(args.get(0))).exists()) throw new IllegalArgumentException("File doesn't exist");
        try {
            unzip(args.get(0));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Something wrong");
        }
    }

    private void unzip(final String zipDir) throws IOException {
        ZipFile zipFile = new ZipFile(zipDir, StandardCharsets.UTF_8);
        Enumeration<?> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String entryName = entry.getName();
            if (entryName.endsWith("/")) {
                createFolder(entryName);
                continue;
            } else checkFolder(entryName);
            InputStream fis = zipFile.getInputStream(entry);
            FileOutputStream fos = new FileOutputStream(entryName);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer, 0, buffer.length);
            fos.write(buffer, 0, buffer.length);
            fis.close();
            fos.close();
        }
        zipFile.close();
        System.out.println("Zip файл разархивирован!");
    }

    private void createDir(final String dir) {
        File file = new File(dir);
        if (!file.exists())
            file.mkdirs();
    }

    private void createFolder(final String dirName) {
        if (dirName.endsWith("/"))
            createDir(dirName.substring(0, dirName.length() - 1));
    }

    private void checkFolder(final String file_path) {
        if (!file_path.endsWith("/") && file_path.contains("/")) {
            String dir = file_path.substring(0, file_path.lastIndexOf("/"));
            createDir(dir);
        }
    }

}
