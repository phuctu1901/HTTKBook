package mta.ltnc.BookStore.repositories;

import mta.ltnc.BookStore.dto.client.CategoryDto;
import mta.ltnc.BookStore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* Generated by Spring Data Generator on 22/06/2019
*/
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    @Query("SELECT new mta.ltnc.BookStore.dto.client.CategoryDto(c,COUNT(c.name)) "
            + "FROM Category c LEFT JOIN BookCategory bc ON c = bc.category LEFT JOIN Book b ON bc = b.bookCategory GROUP BY c")
    List<CategoryDto> getAllWithouListBookCate();
}
