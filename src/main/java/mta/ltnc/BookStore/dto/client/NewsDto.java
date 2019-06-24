package mta.ltnc.BookStore.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private Long id;
    private String title;
    private Long created;
    private Timestamp createdDate;
    private String nameType;
    private Boolean isPublic;
    private Long image;
    private String content;
    private String author;
}
