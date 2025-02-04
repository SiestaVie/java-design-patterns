/**
 * The MIT License
 * Copyright © 2014-2019 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.typeobject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import org.json.simple.parser.ParseException;
import com.iluwatar.typeobject.Candy.Type;

/**
 * The CellPool class allows the reuse of crushed cells instead of creation of new
 * cells each time. The reused cell is given a new candy to hold using the randomCode
 * field which holds all the candies available.
 */

public class CellPool {
  private static final Random RANDOM = new Random();
  ArrayList<Cell> pool;
  int pointer;
  Candy[] randomCode;
  
  CellPool(int num) {
    this.pool = new ArrayList<Cell>(num);    
    try {
      this.randomCode = assignRandomCandytypes();
    } catch (Exception e) {
      e.printStackTrace();
      //manually initialising this.randomCode
      this.randomCode = new Candy[5];
      randomCode[0] = new Candy("cherry", "fruit", Type.rewardFruit, 20);
      randomCode[1] = new Candy("mango", "fruit", Type.rewardFruit, 20);
      randomCode[2] = new Candy("purple popsicle", "candy", Type.crushableCandy, 10);
      randomCode[3] = new Candy("green jellybean", "candy", Type.crushableCandy, 10);
      randomCode[4] = new Candy("orange gum", "candy", Type.crushableCandy, 10);
    }
    for (int i = 0; i < num; i++) {
      var c = new Cell();
      c.candy = randomCode[RANDOM.nextInt(randomCode.length)];
      this.pool.add(c);
    }
    this.pointer = num - 1;
  }
  
  Cell getNewCell() {
    var newCell = this.pool.remove(pointer);
    pointer--;
    return newCell;
  }
  
  void addNewCell(Cell c) {
    c.candy = randomCode[RANDOM.nextInt(randomCode.length)]; //changing candytype to new
    this.pool.add(c);
    pointer++;
  }
  
  Candy[] assignRandomCandytypes() throws FileNotFoundException, IOException, ParseException {
    var jp = new JsonParser();
    jp.parse();
    var randomCode = new Candy[jp.candies.size() - 2]; //exclude generic types 'fruit' and 'candy'
    var i = 0;
    for (var e = jp.candies.keys(); e.hasMoreElements();) {
      var s = e.nextElement();
      if (!s.equals("fruit") && !s.equals("candy")) {
        //not generic
        randomCode[i] = jp.candies.get(s);
        i++;
      }
    }
    return randomCode;
  }
}
