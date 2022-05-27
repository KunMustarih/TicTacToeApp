package edu.Truman;

import java.util.Scanner;

public class HumanPlayer extends Player {

    char[] gameBoard;
    public HumanPlayer(Board obj) {
        // initialize the gameboard object
        gameBoard = obj.gameBoard;
    }

    public int CompNextMove()
    {
        Scanner input = new Scanner(System.in);

        // input position from the user
        System.out.print("Enter a position (0--8): ");

        while(!input.hasNextInt()) {
            System.out.print("Input must be Integer. Enter a valid position between 0 and 8: ");
            input.nextLine();
        }
        int num = input.nextInt(); // Stores the value entered by the user in num variable

        // A loop that checks if num is between 0 and 8
        while(num < 0 || num > 8) {
            System.out.print("Enter a valid position between 0 and 8: ");
            num = input.nextInt();
        }

        //A loop that checks if the user inputted a position that is taken
        while(gameBoard[num] == 'H' || gameBoard[num] == 'C') {
            System.out.println("Someone has already made a move at this position! Try again. ");
            System.out.print("Enter a position (0--8): ");
            num = input.nextInt();
        }
        //Assigning the H to the positon chosen by the user
        gameBoard[num] = 'H';
        return num;
    }

}