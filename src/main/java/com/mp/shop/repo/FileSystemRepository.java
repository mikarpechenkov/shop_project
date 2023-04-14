package com.mp.shop.repo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Repository
public class FileSystemRepository {
    @Getter @Setter
    private String RESOURCES_DIR = Objects.requireNonNull(FileSystemRepository.class.getResource("/"))
            .getPath();

    public String save(byte[] content, String imageName) throws Exception {
        Path newFile = Paths.get(RESOURCES_DIR + "-" + imageName);
        Files.createDirectories(newFile.getParent());

        Files.write(newFile, content);

        return newFile.toAbsolutePath()
                .toString();
    }

    public FileSystemResource findInFileSystem(String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
