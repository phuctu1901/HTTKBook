package mta.ltnc.BookStore.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.ltnc.BookStore.entity.Book;
import mta.ltnc.BookStore.entity.Image;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String code;
    private String name;
    private Integer numberPage;
    private Integer weight;
    private String form;
    private Date publishDate;
    private Integer buys;
    private Integer price;
    private Integer promotionPrice;
    private Integer viewCount;
    private Integer inventory;
    private Boolean status;
    private String description;
    private Timestamp createDate;
    private String image1;
    private String author;
    private Long authorId;
    private String publisher;
    private Long publisherId;
    private String bookCategory;
    private Long bookCategoryId;
    private Boolean isNew;
    private List<Image> listImages;
    public BookDto(Book b){
        this.id = b.getId();
        this.code = b.getCode();
        this.name = b.getName();
        this.numberPage = b.getNumberPage();
        this.weight = b.getWeight();
        this.form = b.getForm();
        this.publishDate = b.getPublishDate();
        this.buys = b.getBuys();
        this.price = b.getPrice();
        this.promotionPrice = b.getPromotionPrice();
        this.viewCount = b.getViewCount();
        this.inventory = b.getInventory();
        this.status = b.getStatus();
        this.image1 = b.getImage1();
        this.description = b.getDescription();
        this.createDate = b.getCreateDate();
        this.author = b.getAuthor().getName();
        this.authorId = b.getAuthor().getId();
        this.publisher = b.getPublisher().getName();
        this.publisherId = b.getPublisher().getId();
        this.bookCategory = b.getBookCategory().getName();
        this.bookCategoryId = b.getBookCategory().getId();
        long diffInMillies = Math.abs(new Date().getTime() - this.publishDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        long newDate = 30;
        this.isNew = diff < 30 ? true : false;
        this.listImages = null;
    }
}
