package com.exercice.mini_projet.controller;

import com.exercice.mini_projet.entity.Book;
import com.exercice.mini_projet.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookControllerMVC {

    private final BookService bookService;

    public BookControllerMVC(BookService bookService) {
        this.bookService = bookService;
    }

    // ✅ 1. Routes spécifiques EN PREMIER (très important pour éviter les conflits)

    // 👉 Formulaire d'ajout d'un livre
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book()); // objet vide pour le formulaire
        return "book-form"; // renvoie vers book-form.html
    }

    // ✅ 2. Routes avec paramètres APRÈS

    // Afficher la liste des livres
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books"; // renvoie vers le template books.html
    }

    // Voir les détails d'un livre par ID
    @GetMapping("/{id}")
    public String viewBookDetails(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livre non trouvé avec l'id : " + id));
        model.addAttribute("book", book);
        return "book-details"; // renvoie vers book-details.html
    }

    // 👉 Sauvegarder le livre soumis
    @PostMapping
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.createBook(book);
        return "redirect:/books"; // après ajout, revenir à la liste
    }

    // ✅ BONUS : Gestion d'erreur pour les IDs invalides
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleNotFound(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error"; // page d'erreur (optionnel)
    }

    // 👉 Formulaire d’édition d’un livre
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livre non trouvé avec l'id : " + id));
        model.addAttribute("book", book);
        return "book-form"; // on réutilise le même formulaire
    }

    // 👉 Sauvegarde après modification
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book book) {
        bookService.updateBook(id, book);
        return "redirect:/books"; // retour à la liste
    }

    // 👉 Supprimer un livre
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

}