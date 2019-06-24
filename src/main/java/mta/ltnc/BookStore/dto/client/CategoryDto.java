package mta.ltnc.BookStore.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.ltnc.BookStore.entity.Category;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Category category;
    private List<BookCateDto> lsBookCategory;
    private Integer numberBooks;
    public  CategoryDto(Category category,Long numberBooks){
        this.category = category;
        this.lsBookCategory = null;
        this.numberBooks = Math.toIntExact(numberBooks);
    }
}
