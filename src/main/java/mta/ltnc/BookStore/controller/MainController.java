package mta.ltnc.BookStore.controller;
import mta.ltnc.BookStore.service.client.AuthorClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private AuthorClientService authorService;
}
