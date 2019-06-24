package mta.ltnc.BookStore.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.ltnc.BookStore.entity.Publisher;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDto {
    private Publisher publisher;
    private Integer numberBooks;
    public  PublisherDto(Publisher publisher,Long numberBooks){
        this.publisher = publisher;
        this.numberBooks = Math.toIntExact(numberBooks);
    }
}
