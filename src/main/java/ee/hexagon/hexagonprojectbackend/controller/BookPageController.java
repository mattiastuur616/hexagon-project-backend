package ee.hexagon.hexagonprojectbackend.controller;

import ee.hexagon.hexagonprojectbackend.service.BookPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@RestController
public class BookPageController {

    private final BookPageService bookPageService;

    @GetMapping("/content/{chapter}/{pageNumber}")
    public List<String> getContentOfBook(@PathVariable String chapter, @PathVariable String pageNumber) throws IOException {
        return bookPageService.getContentFromFile(chapter, pageNumber);
    }
}
