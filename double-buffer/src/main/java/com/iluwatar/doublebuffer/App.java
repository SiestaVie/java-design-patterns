/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.iluwatar.doublebuffer;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Double buffering is a term used to describe a device that has two buffers.
 * The usage of multiple buffers increases the overall throughput of a device
 * and helps prevents bottlenecks. This example shows using double buffer pattern
 * on graphics. It is used to show one image or frame while a separate frame
 * is being buffered to be shown next. This method makes animations and games
 * look more realistic than the same done in a single buffer mode.
 */
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  /**
   * Program main entry point.
   * @param args runtime arguments
   */
  public static void main(String[] args) {
    var scene = new Scene();
    List<Pair<Integer, Integer>> drawPixels = new ArrayList<>();
    Pair<Integer, Integer> pixel1 = new MutablePair<>(1, 1);
    Pair<Integer, Integer> pixel2 = new MutablePair<>(5, 6);
    Pair<Integer, Integer> pixel3 = new MutablePair<>(3, 2);
    drawPixels.add(pixel1);
    drawPixels.add(pixel2);
    drawPixels.add(pixel3);
    scene.draw(drawPixels);
    var buffer1 = scene.getBuffer();
    printBlackPixelCoordinate(buffer1);

    drawPixels.clear();
    Pair<Integer, Integer> pixel4 = new MutablePair<>(3, 7);
    Pair<Integer, Integer> pixel5 = new MutablePair<>(6, 1);
    drawPixels.add(pixel4);
    drawPixels.add(pixel5);
    scene.draw(drawPixels);
    Buffer buffer2 = scene.getBuffer();
    printBlackPixelCoordinate(buffer2);
  }

  private static void printBlackPixelCoordinate(Buffer buffer) {
    var log = "Black Pixels: ";
    Pixel[] pixels = buffer.getPixels();
    for (var i = 0; i < pixels.length; ++i) {
      if (pixels[i] == Pixel.BLACK) {
        var y = i / FrameBuffer.WIDTH;
        var x = i % FrameBuffer.WIDTH;
        log += " (" + x + ", " + y + ")";
      }
    }
    LOGGER.info(log);
  }
}
