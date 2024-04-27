package ee.hexagon.hexagonprojectbackend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class BookPageService {

    @Transactional
    public List<String> getContentFromFile(String chapter, String pageNumber) throws IOException {
        Path path = Paths.get("C:\\Users\\Mattias Tüür\\IdeaProjects\\hexagon-project-backend\\src\\main\\java\\ee\\hexagon\\hexagonprojectbackend\\chapters\\" + chapter + "\\" + pageNumber + ".txt");
        try (Stream<String> stream = Files.lines(path)) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
