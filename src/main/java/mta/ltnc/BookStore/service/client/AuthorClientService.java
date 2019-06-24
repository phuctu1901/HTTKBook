package mta.ltnc.BookStore.service.client;

import mta.ltnc.BookStore.dto.ResponseDto;
import mta.ltnc.BookStore.dto.client.AuthorDto;
import mta.ltnc.BookStore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorClientService {
    @Autowired
    private AuthorRepository authorRepository;

    public ResponseDto test(Long authorType) {
        List<AuthorDto> b =authorRepository.getAllAuthorDtoByAuthorType(authorType);
        return new ResponseDto("0", "Success", b);

    }
    public List<AuthorDto> getAllByAuthorType(Long authorType){
        return authorRepository.getAllAuthorDtoByAuthorType(authorType);
    }
}
