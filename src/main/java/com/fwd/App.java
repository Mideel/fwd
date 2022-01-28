package com.fwd;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
  private static Scanner scanner = new Scanner(System.in);
  private static Board board = null;

  public static void main( String[] args )
    {
      showWelcomeMessages();
      takeTurns();
    }

    private static void showWelcomeMessages() {
      System.out.println("Welcome to Tic Tac Toe");
      System.out.print("Please select the grid size(1 digit): ");
      int size = -1;
      boolean error = true;

      while(error) {
        try {
          size = scanner.nextInt();
          error = false;
        }
        catch (Exception e) {
          System.out.println("Invalid input, please try again");
        }
      }
      board = new Board(size);
    }

    private static void takeTurns() {
      boolean gameOver = false;
      int turn = Board.PLAYER_1;

      while(!gameOver) {
        showGrid();

        String p = turn == Board.PLAYER_1 ? "Player 1" : "Player 2";
        System.out.print(p + "'s turn" + ", please enter row and col number separated by a space: ");

        int row = -1;
        int col = -1;
        try {
          row = scanner.nextInt();
          col = scanner.nextInt();
        }
        catch (Exception e) {
          System.out.println("Invalid input, please try again");
          continue;
        }

        try {
          board.fill(turn, row, col);
        }
        catch(InvalidCoordinateException e) {
          System.out.println("Invalid index, index row: " + row + " col: " + col + " is already filled");
          continue;
        }

        Line line = board.checkLine(turn);
        if(line != null) {
          String t = line.getType() == Line.TYPE_ROW ? "row" : line.getType() == Line.TYPE_COLUMN ?
                  "column" : "diagonal";
          int index = line.getIndex();
          String sIndex = line.getType() == Line.TYPE_DIAGONAL ?
                  line.getIndex() == Line.TOP_LEFT_BOTTOM_RIGHT ? " line, top left "
                          : " bottom left" : " line at index: " + index;
          showGrid();
          System.out.println(p + " won by " + t + sIndex );
          gameOver = true;
        }

        turn = (turn == Board.PLAYER_1 ? Board.PLAYER_2 : Board.PLAYER_1);
      }
    }

    private static void showGrid() {
      System.out.println("Current grid is: ");
      for(int i=0; i<board.getSize(); i++) {
        for(int i2=0; i2<board.getSize(); i2++) {
          char c = board.getPlayerSymbolAt(i,i2);
          System.out.print(c + " ");
        }
        System.out.println();
      }
    }
}
