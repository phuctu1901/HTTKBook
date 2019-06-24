package mta.ltnc.BookStore.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.ltnc.BookStore.entity.BookCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCateDto {
    private BookCategory bookCategory;
    private int numberBooks;
    public  BookCateDto(BookCategory bookCategory,Long numberBooks){
        this.bookCategory = bookCategory;
        this.numberBooks = Math.toIntExact(numberBooks);
    }
}
