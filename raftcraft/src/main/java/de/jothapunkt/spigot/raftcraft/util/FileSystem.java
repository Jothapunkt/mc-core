package com.jothapunkt.spigot.raftcraft.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;

public class FileSystem {
    public static void deleteDirectory(File dir) throws IOException  {
        try (Stream<Path> pathStream = Files.walk(dir.toPath())) {
            pathStream.sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
        }
    }

    public static void copyFolder(Path src, Path dest) throws IOException {
        try (Stream<Path> stream = Files.walk(src)) {
            stream.forEach(source -> copy(source, dest.resolve(src.relativize(source))));
        }
    }

    public static void copy(Path source, Path dest) {
        try {
            Files.copy(source, dest, REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
