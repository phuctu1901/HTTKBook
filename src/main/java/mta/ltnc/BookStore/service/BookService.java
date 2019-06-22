package mta.ltnc.BookStore.service;

import mta.ltnc.BookStore.dto.ResponseDto;
import mta.ltnc.BookStore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public ResponseDto getAll() {
        return new ResponseDto("0", "Success", bookRepository.findAll());
    }

}
