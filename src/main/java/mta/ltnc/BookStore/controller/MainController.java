package mta.ltnc.BookStore.controller;
import mta.ltnc.BookStore.dto.ResponseDto;
import mta.ltnc.BookStore.service.client.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private AuthorService authorService;




    @GetMapping("/getauthor")
    public ResponseDto getAccount(@RequestParam Long id) {
        return authorService.test(id);
    }
}
