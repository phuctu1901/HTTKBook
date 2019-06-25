package mta.ltnc.BookStore.service.client;

import mta.ltnc.BookStore.dto.ResponseDto;
import mta.ltnc.BookStore.dto.client.BookDto;
import mta.ltnc.BookStore.entity.Book;
import mta.ltnc.BookStore.repositories.BookImageRepository;
import mta.ltnc.BookStore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookClientService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookImageRepository bookImageRepository;

    public ResponseDto getAll() {
        return new ResponseDto("0", "Success", bookRepository.findAll());
    }

    public List<BookDto> getAllDto() {
        List<BookDto> ls = bookRepository.getAllDto();
        ls.forEach(x -> {
            x.setListImages(bookImageRepository.getAllByBook(x.getId()));
        });
        return ls;
    }

    public BookDto getOneDto(Long id) {
        BookDto temp = bookRepository.getOneDto(id);
        temp.setListImages(bookImageRepository.getAllByBook(temp.getId()));
        return temp;
    }

    public List<BookDto> getSameAuthorDto(Long authorId) {
        List<BookDto> temp = bookRepository.getSameAuthorDto(authorId);
        temp = temp.size() > 4 ? temp.subList(0,3) : temp;
        temp.forEach(x -> {
            x.setListImages(bookImageRepository.getAllByBook(x.getId()));
        });
        return temp;
    }
    public List<BookDto> getSameBookCategoryDto(Long authorId) {
        List<BookDto> temp = bookRepository.getSameBookCategoryDto(authorId);
        temp = temp.size() > 4 ? temp.subList(0,3) : temp;
        temp.forEach(x -> {
            x.setListImages(bookImageRepository.getAllByBook(x.getId()));
        });
        return temp;
    }
    public List<BookDto> getSamePublisherDto(Long publisherId) {
        List<BookDto> temp = bookRepository.getSamePublisherDto(publisherId).subList(0,3);
        temp = temp.size() > 4 ? temp.subList(0,3) : temp;
        temp.forEach(x -> {
            x.setListImages(bookImageRepository.getAllByBook(x.getId()));
        });
        return temp;
    }
    public Book findById(Long bookId){
        return bookRepository.findById(bookId).get();
    }
    public List<BookDto> getTop4ByOrdOrderByBuysDesc(){
        List<BookDto> temp = bookRepository.getTop4ByOrderByBuysDesc();
        temp = temp.size() > 4 ? temp.subList(0,3) : temp;
        temp.forEach(x -> {
            x.setListImages(bookImageRepository.getAllByBook(x.getId()));
        });
        return temp;
    }
    public List<BookDto> getTop8ByOrdOrderByBuysDesc(){
        List<BookDto> temp = bookRepository.getTop8ByOrderByBuysDesc();
        temp.forEach(x -> {
            x.setListImages(bookImageRepository.getAllByBook(x.getId()));
        });
        return temp;
    }
}
