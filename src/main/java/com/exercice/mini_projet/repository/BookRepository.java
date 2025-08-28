package com.exercice.mini_projet.repository;


import com.exercice.mini_projet.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public Book findByYear(Integer year);
}