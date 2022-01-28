/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.fwd;

/**
 * @author benny susanto
 * @version $Id: Line.java, v 0.1 2022-01-26 16:13 benny susanto Exp $$
 */
public class Line {

  public static final int TYPE_ROW = 0;
  public static final int TYPE_COLUMN = 1;
  public static final int TYPE_DIAGONAL = 2;
  public static final int TOP_LEFT_BOTTOM_RIGHT = 0;
  public static final int BOTTOM_LEFT_TOP_RIGHT = 1;

  private int type;
  private int index;

  public Line(int type, int index) {
    this.setType(type);
    this.setIndex(index);
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}