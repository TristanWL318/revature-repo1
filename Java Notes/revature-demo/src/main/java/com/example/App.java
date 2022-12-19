package com.example;

import java.util.ArrayList;
import java.util.List;

import io.javalin.Javalin;

/**
 * Hello world app
 *
 */
public class App {

    public static List<String> names = new ArrayList<>();

    public static void main( String[] args ) {
        // adding to the ArrayList()
        names.add("Billy");
        names.add("John");
        names.add("Mandy");

        // Creates a new instance w/out config
        Javalin testApp = Javalin.create();

        // GET http request that ends with /hello and responsds with "Hello World!"
        testApp.get("/hello", ctx -> ctx.result("Hello World!"));

        testApp.post("/add", ctx -> {
            // get the name from the http request body and saving it on a variable
            String newName = ctx.body();
            // add the new name into the list
            names.add(newName);
            // tells Javalin to return a String message to the requester
            ctx.result("Congrats! New name is added.");
            // tells Javalin to return status code 201 as a response "CREATED"
            ctx.status(201);
        });

        testApp.get("/person/{num}", ctx -> {
            // using pathParam method to ge the value
            String stringNum = ctx.pathParam("num");
            // converts pathParam result to int 
            int num = Integer.parseInt(stringNum);
            // uses the converted pathParam as index of an array
            String person = names.get(num);
            // tells Javalin to return name of the person from the arrayList
            ctx.result(person);
            // tells Javalin to return status code 200 as a response "OK"
            ctx.status(200);
        });

        testApp.get("/protected/{num}", ctx -> {
            String stringNum = ctx.pathParam("num");
            int num = Integer.parseInt(stringNum);
            try {
                String person = names.get(num);
                ctx.result(person);
                ctx.status(200);
            } catch (IndexOutOfBoundsException e) {
                ctx.result("No name were found.");
                ctx.status(404);
            }
        });

        testApp.put("/replace/{num}", ctx -> {
            // using pathParam method to ge the value
            String stringNum = ctx.pathParam("num");
            // converts pathParam result to int 
            int num = Integer.parseInt(stringNum);
            // retrieves text from body and is held be newPerson
            String newPerson = ctx.body();
            //  replaces the name in the specified position in the ArraryList
            names.set(num, newPerson);
            // tells Javalin to return a String message to the requester
            ctx.result("Replaced with a new Person successfully!");
            // tells Javalin to return status code 200 as a response "OK"
            ctx.status(200);
        });

        testApp.patch("update/{num}", ctx -> {
            String stringNum = ctx.pathParam("num");
            int num = Integer.parseInt(stringNum);
            String updatedInfo = ctx.body();
            names.set(num, updatedInfo);
            ctx.result("Update success!");
            ctx.status(200);
        });

        testApp.delete("delete", ctx -> {
            names.clear();
            ctx.result("All names were removed from the list.");
            ctx.status(200);
        });

        testApp.start();

    }
}
