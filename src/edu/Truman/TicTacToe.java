//package edu.Truman;
//
//import java.util.Scanner;
//
//public class TicTacToe {
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        Board gameBoard= new Board();// creating board object
//
//        // Create a computer player object
//        CompPlayer computer = new CompPlayer(gameBoard);
//        // Create a HumanPlayer object
//
//        HumanPlayer individual = new HumanPlayer(gameBoard);
//        // Pass a copy of the Board object to these two objects
//
//        String gameInput= "Y";
//
//
//        while(gameInput.equals("Y") ) // A while loop that repeats the game until user enters Y
//        {
//            gameBoard.boardReset(); //Resets the board game
//            gameBoard.drawBoard(); //Displays the board
//            while(true)
//            {
//                System.out.println("Human player turn .. ");
//                // Input move for the human player by calling the
//                //
//                individual.CompNextMove();
//                gameBoard.drawBoard();
//
//                if(gameBoard.playerHasWon() =='H')// checking if human won
//                {
//                    System.out.println("Human won the game");
//                    gameBoard.drawBoard();
//                    break;
//                }
//
//                // call the computer player method to make computer move
//                 computer.CompNextMove();
//
//                System.out.println("Computer has made the following move... ");
//                gameBoard.drawBoard();
//
//
//                if(gameBoard.playerHasWon() =='C')//checking if computer won
//                {
//                    System.out.println("Computer won the game");
//                    gameBoard.drawBoard();
//                    break;
//                }
//
//                if(gameBoard.playerHasWon() =='T')// checking if it is a tie
//                {
//                    System.out.println("There is a Tie between the Computer and the Human");
//                    gameBoard.drawBoard();
//                    break;
//                }
//            }
//
//            System.out.println("Game Over");
//            // Take input to determine whether the user would like to continue to
//            // play the game
//            System.out.println("Do you want to continue or not, if so press Y otherwise N");
//            gameInput = in.next();
//        }
//        in.close();
//    }
//}
