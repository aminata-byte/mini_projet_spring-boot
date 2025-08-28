package com.exercice.mini_projet.service;

import com.exercice.mini_projet.entity.Book;
import com.exercice.mini_projet.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 🔹 Récupérer tous les livres
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 🔹 Récupérer un livre par ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // 🔹 Créer un livre
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // 🔹 Modifier un livre
    public Book updateBook(Long id, Book bookDetails) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé avec id " + id));

        existing.setTitle(bookDetails.getTitle());
        existing.setAuthor(bookDetails.getAuthor());
        existing.setYear(bookDetails.getYear());
        existing.setIsbn(bookDetails.getIsbn());

        return bookRepository.save(existing);
    }

    // 🔹 Supprimer un livre
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
