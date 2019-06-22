package mta.ltnc.BookStore.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class AuthorType {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = Gener