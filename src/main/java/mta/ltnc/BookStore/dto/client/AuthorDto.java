package mta.ltnc.BookStore.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.ltnc.BookStore.entity.Author;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    public Author author;
    public Integer numberBooks;
    public AuthorDto(Author author, Long numberBooks){
        this.author = author;
        this.numberBooks = Math.toIntExact(numberBooks);
    }
}
