package edu.Truman;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * This class holds the implementation of the TicTacToeApp and implements ActionListener
 */
public class TicTacToeApp implements ActionListener{

    Board gameBoard= new Board();// creating board object

    // Create a computer player object
    CompPlayer computer = new CompPlayer(gameBoard);

    boolean gameover = false;

    final int SIZE = 9;
    // Buttons to hold the selection values
    JButton boardButtons[]= new JButton[SIZE];

    // buttons to restart or exit teh game
    JButton bRestart;
    JButton bExit;

    // to provide status message
    JLabel gameStatusLabel;
    JFrame gWindow; // main window object

    //To generate randomness on how plays first
    Random choice = new Random();
    boolean moveToggleFlag = choice.nextBoolean(); // toggles computer/user move

    int gameMoveCount =0; // counts the number of moves to determine draw, etc

    /**
     * Constructor of the TicTacToeApp class
     * @param title
     */
    public TicTacToeApp(String title) {

        // creating a JFrame window with the title
        gWindow = new JFrame(title);

        // The JPanel holds the buttons
        JPanel upperLayerPanel = new JPanel();
        upperLayerPanel.setLayout(new GridLayout(3, 3));
        // creating memory for the buttons
        for(int i=0;i<SIZE;i++){
            boardButtons[i] = new JButton();
            boardButtons[i].setText(Integer.toString(i+1));
            boardButtons[i].setFont(new Font("SansSerif", Font.PLAIN, 20));
            boardButtons[i].addActionListener(this);
            // adding the button to the Panel
            upperLayerPanel.add(boardButtons[i]);
        }


        // Panel holding buttons at the south side
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));

        //Initialization of the restart button and adding action listener
        bRestart = new JButton("Restart Game");
        bRestart.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bRestart.addActionListener(this);

        //Initialization of the exit button and adding action listener
        bExit = new JButton("Exit Game");
        bExit.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bExit.addActionListener(this);


        ////Initialization of the label and adjusting the text shown
        gameStatusLabel= new JLabel("   Welcome. Your Turn. Select any button above to begin ..");
        gameStatusLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gameStatusLabel.setPreferredSize(new Dimension(100, 40));

        southPanel.add(gameStatusLabel, BorderLayout.CENTER);

        JPanel lowerButtonPanel =new JPanel();
        lowerButtonPanel.setLayout(new GridLayout(1, 2));
        lowerButtonPanel.add(bRestart, BorderLayout.WEST);
        lowerButtonPanel.add(bExit, BorderLayout.EAST);
        southPanel.add(lowerButtonPanel);


        // adding all the panels to the main window
        gWindow.setLayout(new BorderLayout());
        gWindow.add(upperLayerPanel, BorderLayout.CENTER);
        gWindow.add(southPanel, BorderLayout.SOUTH);


        gWindow.setSize(500, 500); //Setting the size of the window
        gWindow.setVisible(true); //Making the window object visible on screen
        gWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Setting the close operation to exit the window
        computerplay(); //Making the computer do a move
    }

    /**
     * Restarts the game
     */
    public void resetGame()
    {
        gameBoard.boardReset();
        // creating memory for the buttons
        for(int i=0;i<SIZE;i++){
            boardButtons[i].setText(Integer.toString(i+1));
            boardButtons[i].setForeground(Color.BLACK);
        }
        gameMoveCount = 0;
        // other actions can be taken here
    }

    /**
     * Allows the computer to make a move when it is its turn
     */
    public void computerplay()
    {
        int computer_move;
        if(moveToggleFlag)
        {
            computer_move = computer.CompNextMove();
            boardButtons[computer_move].doClick();
        }
    }

    /**
     * Handles clicks on Compute button by computing the BMI.
     * @param event
     */
    public void actionPerformed(ActionEvent event) {
        // if the event source is the restart button then
        if(event.getSource().equals(bExit))
        {
            gWindow.dispose(); //Closes the window
            return;
        }
        //Checks if the restart button is selected
        if(event.getSource().equals(bRestart)){
            resetGame();
            this.gameStatusLabel.setText("   Game has restarted. Select any button above to begin ..");
            gWindow.setTitle("TicTacToe [Your Turn]"); // this can be randomized
            gameover = false;
        }
        //Checks if the game is over
        if(gameover)
        {
            return;
        }

        else { // determine which cell button triggered the action event
            for(int i=0;i<SIZE;i++){
                if(event.getSource().equals(boardButtons[i])){ // button found

                    // if the cell has already been selected then do not do anything
                    try
                    {
                        if(boardButtons[i].getText().equals("H") == true && boardButtons[i].getText().equals("C") == true)
                        {
                            throw new IllegalArgumentException("The button is selected");
                        }
                    }
                    catch (IllegalArgumentException e)
                    {
                        gameStatusLabel.setText(e.getMessage());
                    }

                    if(boardButtons[i].getText().equals("H") == false && boardButtons[i].getText().equals("C") == false) {

                        // this is the selected cell number
                        gameStatusLabel.setText("  You have selected cell no " + (i+1) );

                        if(moveToggleFlag==true){
                            gWindow.setTitle("TicTacToe [Your Turn]");
                            boardButtons[i].setText("C");
                            boardButtons[i].setForeground(Color.RED);
                        }else {
                            gWindow.setTitle("TicTacToe [Computers Turn]");
                            boardButtons[i].setText("H");
                            boardButtons[i].setForeground(Color.BLUE);
                            gameBoard.gameBoard[i] = 'H';
                        }
                        moveToggleFlag = !moveToggleFlag;
                        gameMoveCount ++; // keep counting the moves

                        if(gameMoveCount == SIZE){ // if this is the last move
                            gameStatusLabel.setText("    The Game Over! Restart the game to continue ...");
                            gWindow.setTitle("TicTacToe [Game Over!]");
                        }
                    } // new move: if condition ends
                } // main if inside the loop ends
            } // for loop ends
            if(gameBoard.playerHasWon() == 'H')
            {
                gameover = true;
                gameStatusLabel.setText("    You won! Restart the game to continue ...");
                gWindow.setTitle("TicTacToe [Game Over!]");
                if(event.getSource().equals(bExit))
                {
                    gWindow.dispose();
                }
                //gameBoard.boardReset();
                return;
            }

            if(gameBoard.playerHasWon() == 'C')
            {
                gameover = true;
                gameStatusLabel.setText("   You lost! Restart the game to continue ...");
                gWindow.setTitle("TicTacToe [Game Over!]");
                if(event.getSource().equals(bExit))
                {
                    gWindow.dispose();
                }
                return;
            }

            if(gameBoard.playerHasWon() == 'T')
            {
                gameover = true;
                gameStatusLabel.setText("    It is a tie! Restart the game to continue ...");
                gWindow.setTitle("TicTacToe [Game Over!]");
                if(event.getSource().equals(bExit))
                {
                    gWindow.dispose();
                }
                return;
            }
        } // else block ends
        computerplay();
    } // actionPerformed function ends


    // main driver program
    public static void main(String[] args) {
        // create an object of the TikTakToe class
        TicTacToeApp gameWindow = new TicTacToeApp("TikTakToe Game");
    }

}
