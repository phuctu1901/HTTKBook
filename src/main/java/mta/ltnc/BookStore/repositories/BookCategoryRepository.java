package mta.ltnc.BookStore.repositories;

import mta.ltnc.BookStore.dto.client.BookCateDto;
import mta.ltnc.BookStore.dto.client.SpecialBookCategoryDto;
import mta.ltnc.BookStore.entity.BookCategory;
import mta.ltnc.BookStore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* Generated by Spring Data Generator on 22/06/2019
*/
@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long>, JpaSpecificationExecutor<BookCategory> {
    @Query("SELECT new mta.ltnc.BookStore.dto.client.BookCateDto(bc,COUNT(bc.name)) FROM BookCategory bc LEFT JOIN Book b "
            + "ON bc = b.bookCategory GROUP BY bc")
    List<BookCateDto> getAll();
    @Query("SELECT new mta.ltnc.BookStore.dto.client.BookCateDto(bc,COUNT(bc.name)) FROM BookCategory bc LEFT JOIN Book b"
            + " ON bc = b.bookCategory WHERE bc.category = :category GROUP BY bc")
    List<BookCateDto> getAllByCategory(@Param("category") Category category);
    @Query("SELECT new mta.ltnc.BookStore.dto.client.BookCateDto(bc,COUNT(bc.name)) FROM BookCategory bc LEFT JOIN Book b "
            + "ON bc = b.bookCategory GROUP BY bc")
    List<SpecialBookCategoryDto> getSpecials();
}
