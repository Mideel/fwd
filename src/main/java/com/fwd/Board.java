/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.fwd;

/**
 * @author benny susanto
 * @version $Id: Board.java, v 0.1 2022-01-26 16:00 benny susanto Exp $$
 */
public class Board {

  private int size = 0;
  private char[][] grid;
  public static final char SYMBOL_1 = 'O';
  public static final char SYMBOL_2 = 'X';
  public static final char SYMBOL_EMPTY = '-';

  public static final int PLAYER_1 = 0;
  public static final int PLAYER_2 = 1;

  public Board(int size) {
   this.setSize(size);
   initBoard();
  }

  private void initBoard() {
    setGrid(new char [this.getSize()][this.getSize()]);
    for(int i = 0; i < getGrid().length; i++) {
      for(int i2 = 0; i2 < getGrid()[i].length; i2++) {
        getGrid()[i][i2] = SYMBOL_EMPTY ;
      }
    }
  }

  public void fill(int player, int row, int col) {
    if(player != PLAYER_1 && player != PLAYER_2)
      throw new RuntimeException("Invalid player argument");

    char c = player == PLAYER_1 ? SYMBOL_1 : SYMBOL_2;
    if(getGrid()[row][col] != SYMBOL_EMPTY) {
      throw new InvalidCoordinateException();
    }
    getGrid()[row][col] = c;
  }

  public Line checkLine(int player) {
    char c = player == PLAYER_1 ? SYMBOL_1 : SYMBOL_2;
    int retval = checkRows(c);
    if(retval != -1) {
      return new Line(Line.TYPE_ROW, retval);
    }
    retval = checkColumns(c);
    if(retval != -1) {
      return new Line(Line.TYPE_COLUMN, retval);
    }
    retval = checkDiagonals(c);
    if(retval != -1) {
      return new Line(Line.TYPE_DIAGONAL, retval);
    }

    return null;
  }

  private int checkRows(char expChar) {
    for(int i = 0; i < getGrid().length; i++) {
      boolean straight = true;
      for(int i2 = 0; i2 < getGrid()[i].length; i2++) {
        char c = getGrid()[i][i2];
        if(expChar != c) {
          straight = false;
          break;
        }
      }
      if(straight) {
        return i;
      }
    }
    return -1;
  }

  private int checkColumns(char expChar) {
    for(int i = 0; i < getGrid()[0].length; i++) {
      boolean straight = true;
      for(int i2 = 0; i2 < getGrid().length; i2++) {
        char c = getGrid()[i2][i];
        if(expChar != c) {
          straight = false;
          break;
        }
      }
      if(straight) {
        return i;
      }
    }
    return -1;
  }

  private int checkDiagonals(char expChar) {
//    int topLeftCornerIndex = grid[0][0];
//    int bottomLeftCornerIndex = grid[grid.length][0];

    boolean straight = true;

    for(int i = 0; i< getGrid().length; i++) {
      char c = getGrid()[i][i];
      if(c != expChar) {
        straight = false;
      }
    }

    if(straight)
      return Line.TOP_LEFT_BOTTOM_RIGHT;

    straight = true ;

    for(int i = 0; i<grid.length; i++) {
      char c = getGrid()[i][grid.length-1-i];
      if(c != expChar) {
        straight = false;
      }
    }

    if(straight)
      return Line.BOTTOM_LEFT_TOP_RIGHT;

    return -1;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public char[][] getGrid() {
    return grid;
  }

  public void setGrid(char[][] grid) {
    this.grid = grid;
  }

//  public int getPlayerAt(int row, int col) {
//    return grid[row][col];
//  }

  public char getPlayerSymbolAt(int row, int col) {
    return grid[row][col];
  }
}