package com.example;

import java.util.ArrayList;
import java.util.List;

import io.javalin.Javalin;

public class Library {

    static List<Book> library = new ArrayList<>();

    public static void main(String[] args) {
        Book startingBook = new Book();
        startingBook.setTitle("The End");
        startingBook.setAuthor("Alone");
        startingBook.setGenre("Non-Fiction");
        startingBook.setIsbn(123456789);

        library.add(startingBook);

        Javalin bookApp = Javalin.create();

        bookApp.get("/book/{index}", ctx -> {
            int index = Integer.parseInt(ctx.pathParam("index"));
            Book book = library.get(index);
            ctx.json(book);
            ctx.status(200);
        });

        bookApp.post("/book", ctx -> {
            Book newBook = ctx.bodyAsClass(Book.class);
            library.add(newBook);
            ctx.result("New book added to the library.");
            ctx.status(201);
        });

        bookApp.patch("/book/{index}", ctx -> {
            int index = Integer.parseInt(ctx.pathParam("index"));
            Book updateBook = ctx.bodyAsClass(Book.class);
            library.get(index).setTitle(updateBook.getTitle());
            library.get(index).setAuthor(updateBook.getAuthor());
            library.get(index).setGenre(updateBook.getGenre());
            //library.get(index).setIsbn(updateBook.getIsbn());
            ctx.json(library.get(index));
            ctx.status(200);
        });

        bookApp.put("/book/{index}", ctx -> {
            int index = Integer.parseInt(ctx.pathParam("index"));
            Book replaceBook = ctx.bodyAsClass(Book.class);
            library.set(index, replaceBook);
            ctx.json(library.get(index));
            ctx.status(200);
        });

        bookApp.delete("/book/{index}", ctx -> {
            int index = Integer.parseInt(ctx.pathParam("index"));
            library.remove(index);
            ctx.status(200);
        });

        bookApp.start();
    }
    
}
