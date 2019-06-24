package mta.ltnc.BookStore.dto.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import mta.ltnc.BookStore.repositories.BookCategoryRepository;

import java.util.List;

@Data
@NoArgsConstructor
public class SpecialBookCategoryDto {
    private static BookCategoryRepository bookCategoryRepository;
    private Long id;
    private String name;
    private Integer numberBooks;
    private List<BookDto> lsBook;
    private SpecialBookCategoryDto(Long id, List<BookDto> ls){
        this.id = id;
        this.name = bookCategoryRepository.findById(id).get().getName();
        this.lsBook = ls;
        this.numberBooks = ls.size();
};
}
