package com.anumalm.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Main class for tic tac toe.
 *
 * Uses only one screen, 3x3 tic tac toe.
 *
 * @author      Anu Malm <anu.m.malm(at)gmail.com>
 * @version     2019.0119
 * @since       2019.0113
 */
public class MainActivity extends TTTActivity {

    private boolean xTurn;
    private String currentPlayer;

    private int turns = 0;
    private boolean winnerFound = false;

    private TextView turnText;

    private Button br1c1;
    private Button br1c2;
    private Button br1c3;

    private Button br2c1;
    private Button br2c2;
    private Button br2c3;

    private Button br3c1;
    private Button br3c2;
    private Button br3c3;

    private ArrayList<Button> buttons;
    private String[][] board;

    /**
     * AppCompatActivity's onCreate()-method.
     *
     * Initiates everything for the game.
     *
     * @param savedInstanceState        a Bundle object that is passed into the onCreate method of every Android Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Loading our own Debug-class for debugging purposes
        Debug.loadDebug(this);
        
        xTurn = true;
        currentPlayer = "X";
        buttons = new ArrayList<>();
        turnText = findViewById(R.id.turnText);

        // Find 1st row buttons
        br1c1 = findViewById(R.id.button1);
        buttons.add(br1c1);
        br1c2 = findViewById(R.id.button2);
        buttons.add(br1c2);
        br1c3 = findViewById(R.id.button3);
        buttons.add(br1c3);

        // Find 2nd row buttons
        br2c1 = findViewById(R.id.button4);
        buttons.add(br2c1);
        br2c2 = findViewById(R.id.button5);
        buttons.add(br2c2);
        br2c3 = findViewById(R.id.button6);
        buttons.add(br2c3);

        // Find 3rd row buttons
        br3c1 = findViewById(R.id.button7);
        buttons.add(br3c1);
        br3c2 = findViewById(R.id.button8);
        buttons.add(br3c2);
        br3c3 = findViewById(R.id.button9);
        buttons.add(br3c3);

        board = new String[3][3];
        initBoard();
    }

    /**
     * Initializes the logical 2-dimensional board full of ""-values.
     */
    private void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }
    }

    /**
     * Decides which symbol (X or O) is put into the button.
     *
     * All game buttons call this method. Everytime a button is called, we check the buttons, check if a winner was found
     * and lastly check if the game continues.
     * @param v         Button that's pressed
     */
    public void addSymbol(View v) {
        if(!winnerFound && turns < 9) {
            checkButtons(v);
            checkForWinner();
            checkContinue();
        }
    }

    /**
     * Checks which button was pressed and calls setButtonText()-method.
     *
     * @param v         Button that we're checking
     */
    private void checkButtons(View v) {
        switch(v.getId()) {
            case  R.id.button1:
                setButtonText(br1c1);
                board[0][0] = currentPlayer;
                break;
            case  R.id.button2:
                setButtonText(br1c2);
                board[0][1] = currentPlayer;
                break;
            case  R.id.button3:
                setButtonText(br1c3);
                board[0][2] = currentPlayer;
                break;
            case  R.id.button4:
                setButtonText(br2c1);
                board[1][0] = currentPlayer;
                break;
            case  R.id.button5:
                setButtonText(br2c2);
                board[1][1] = currentPlayer;
                break;
            case  R.id.button6:
                setButtonText(br2c3);
                board[1][2] = currentPlayer;
                break;
            case  R.id.button7:
                setButtonText(br3c1);
                board[2][0] = currentPlayer;
                break;
            case  R.id.button8:
                setButtonText(br3c2);
                board[2][1] = currentPlayer;
                break;
            case  R.id.button9:
                setButtonText(br3c3);
                board[2][2] = currentPlayer;
                break;
            default:
                Debug.print(this.TAG, "addSymbol", "Button not found!", 3, false);
                break;
        }
    }

    /**
     * Checks if the game ends.
     */
    private void checkContinue() {
        if(winnerFound) {
            disableButtons();
            showResultToast();
        } else {
            if(xTurn) {
                currentPlayer = "X";
            } else {
                currentPlayer = "O";
            }
            turnText.setText("Turn: " + currentPlayer);

            turns++;
            if(turns >= 9) {
                showResultToast();
            }
        }
    }

    /**
     * Checks if a winning move was made.
     */
    private void checkForWinner() {
        checkDiags();
        checkRows();
        checkCols();
    }

    /**
     * Check diagonals for 3 matches.
     */
    private void checkDiags() {
        if(!board[0][0].equals("") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            Debug.print(this.TAG, "checkDiags", "diag1 win", 2, false);
            winnerFound = true;
        }
        if(!board[2][0].equals("") && board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2])) {
            Debug.print(this.TAG, "checkDiags", "diag2 win", 2, false);
            winnerFound = true;
        }
    }

    /**
     * Check rows for 3 matches.
     */
    private void checkRows() {
        for (int i = 0; i < 3; i++) {
            if(!board[i][0].equals("") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                Debug.print(this.TAG, "checkRows", "row " + i + " win", 3, false);
                winnerFound = true;
            }
        }
    }

    /**
     * Check columns for 3 matches.
     */
    private void checkCols() {
        for (int i = 0; i < 3; i++) {
            if(!board[0][i].equals("") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                Debug.print(this.TAG, "checkCols", "col  " + i + " win", 3, false);
                winnerFound = true;
            }
        }
    }

    /**
     * Show the result of the game on toast.
     */
    private void showResultToast() {
        if(turns >= 9) {
            Toast.makeText(getApplicationContext(), "No more possible turns! Please reset the game.", Toast.LENGTH_SHORT).show();
        } else if (winnerFound) {
            Toast.makeText(getApplicationContext(), currentPlayer + " wins!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Set the text for the button and disable it.
     *
     * @param b         Button that we're setTexting
     */
    private void setButtonText(Button b) {
        b.setText(currentPlayer);
        b.setEnabled(false);
        xTurn = !xTurn;
    }

    /**
     * Reset the game to the starting position if a reset-button was pressed.
     *
     * @param v         Button that's pressed
     */
    public void resetGame(View v) {
        xTurn = true;
        currentPlayer = "X";
        winnerFound = false;
        turns = 0;
        turnText.setText("Turn: " + currentPlayer);

        initBoard();

        enableButtons();

        Debug.print(this.TAG, "resetGame", "Game reseted", 1, false);
    }

    /**
     * Disables all buttons.
     *
     * Called when a winner has been found.
     */
    private void disableButtons() {
        Debug.print(this.TAG, "disableButtons", "All buttons disabled", 1, false);
        for (Button c : buttons) {
            c.setEnabled(false);
        }
    }

    /**
     * Called when we want to reset the buttons to their original state.
     */
    private void enableButtons() {
        for (Button c : buttons) {
            c.setEnabled(true);
            c.setText("");
        }
    }
}
