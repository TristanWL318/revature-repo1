public class ControlFlow {
    /*
     * When we are talking about control flow, it is discussing the tools available to us
     * to determine parts of our code to execute, when they execute
     * and conditions under which they will execute
     */

    public static void main(String[] args) {
        int creditCard = 100;
        int pizzaCost = 27;

        // The 3 if statements below are identical
        // condition is true and the statement inside will execute
        if (creditCard >= pizzaCost) {
            System.out.println("Purchase success");
        }

        if (100 >= 27) {
            System.out.println("Purchase success");
        }

        if (true)  {
            System.out.println("Purchase success");
        }

        int newCard = 20;

        // The 3 if statements below are identical
        // condition is false and the statement inside will not execute
        if (newCard >= pizzaCost) {
            System.out.println("Purchase success");
        }

        // Shows an issue because condition fails all the time
        if (20 >= 27)  {
            // This code inside is never run
            System.out.println("Purchase success");
        }

        // shows an issue because condition fails all the time
        if (false) {
            // This code inside is never run
            System.out.println("Purchase success");
        }

        /*
         * conditions can be describe by these logical operators:
         * > greater than
         * < less than
         * >= greater or equal
         * <= less or equal
         * == equal
         * ! negates logic
         */
    }
}
