package com.example;

import io.javalin.Javalin;

/**
 * Hello world app
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );

        // Creates a new instance w/out config
        Javalin testApp = Javalin.create();

        testApp.get("/hello", ctx -> ctx.result("Hello World!"));

        testApp.start();
    }
}
