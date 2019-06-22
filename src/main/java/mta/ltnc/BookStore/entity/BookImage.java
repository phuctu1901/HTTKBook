package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Book_Image", schema = "dbo", catalog = "BookShop3")
public class BookImage {

    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "BookID", referencedColumnName = "ID", nullable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "ImageID", referencedColumnName = "ID", nullable = false)
    private Image image;

}
