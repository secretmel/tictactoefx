package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TicTacToeController {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label statusLabel;

    private String currentPlayer = "X"; // Current player: "X" or "O"
    private final String[][] board = new String[5][5]; // 5x5 board

    @FXML
    public void initialize() {
        // Initialize the board and create buttons dynamically
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Button button = new Button();
                button.setPrefSize(80, 80);
                button.setStyle("-fx-font-size: 20px;");
                final int r = row, c = col;
                button.setOnAction(e -> handleMove(button, r, c));
                gridPane.add(button, col, row);
            }
        }
    }

    private void handleMove(Button button, int row, int col) {
        if (board[row][col] == null && button.getText().isEmpty()) {
            board[row][col] = currentPlayer;
            button.setText(currentPlayer);
            if (checkWinner(row, col)) {
                statusLabel.setText("Player " + currentPlayer + " wins!");
                disableBoard();
            } else if (isDraw()) {
                statusLabel.setText("It's a draw!");
            } else {
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                statusLabel.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }

    private boolean checkWinner(int row, int col) {
        // Check row, column, and diagonals
        return checkLine(row, 0, 0, 1) || // Row
               checkLine(0, col, 1, 0) || // Column
               (row == col && checkLine(0, 0, 1, 1)) || // Main diagonal
               (row + col == 4 && checkLine(0, 4, 1, -1)); // Anti-diagonal
    }

    private boolean checkLine(int startRow, int startCol, int stepRow, int stepCol) {
        String mark = board[startRow][startCol];
        if (mark == null) return false;

        for (int i = 1; i < 5; i++) {
            int r = startRow + i * stepRow;
            int c = startCol + i * stepCol;
            if (!mark.equals(board[r][c])) return false;
        }
        return true;
    }

    private boolean isDraw() {
        for (String[] row : board) {
            for (String cell : row) {
                if (cell == null) return false;
            }
        }
        return true;
    }

    private void disableBoard() {
        gridPane.setDisable(true);
    }
}
