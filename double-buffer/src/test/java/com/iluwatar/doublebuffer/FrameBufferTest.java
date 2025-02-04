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

import org.junit.Assert;
import org.junit.Test;

/**
 * FrameBuffer unit test.
 */
public class FrameBufferTest {

  @Test
  public void testClearAll() {
    try {
      var field = FrameBuffer.class.getDeclaredField("pixels");
      Pixel[] pixels = new Pixel[FrameBuffer.HEIGHT * FrameBuffer.WIDTH];
      for (int i = 0; i < pixels.length; ++i) {
        pixels[i] = Pixel.WHITE;
      }
      pixels[0] = Pixel.BLACK;
      var frameBuffer = new FrameBuffer();
      field.setAccessible(true);
      field.set(frameBuffer, pixels);
      frameBuffer.clearAll();
      Assert.assertEquals(Pixel.WHITE, frameBuffer.getPixels()[0]);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      Assert.fail("Fail to modify field access.");
    }
  }

  @Test
  public void testClear() {
    try {
      var field = FrameBuffer.class.getDeclaredField("pixels");
      Pixel[] pixels = new Pixel[FrameBuffer.HEIGHT * FrameBuffer.WIDTH];
      for (int i = 0; i < pixels.length; ++i) {
        pixels[i] = Pixel.WHITE;
      }
      pixels[0] = Pixel.BLACK;
      var frameBuffer = new FrameBuffer();
      field.setAccessible(true);
      field.set(frameBuffer, pixels);
      frameBuffer.clear(0, 0);
      Assert.assertEquals(Pixel.WHITE, frameBuffer.getPixels()[0]);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      Assert.fail("Fail to modify field access.");
    }
  }

  @Test
  public void testDraw() {
    var frameBuffer = new FrameBuffer();
    frameBuffer.draw(0, 0);
    Assert.assertEquals(Pixel.BLACK, frameBuffer.getPixels()[0]);
  }

  @Test
  public void testGetPixels() {
    try {
      var field = FrameBuffer.class.getDeclaredField("pixels");
      Pixel[] pixels = new Pixel[FrameBuffer.HEIGHT * FrameBuffer.WIDTH];
      for (int i = 0; i < pixels.length; ++i) {
        pixels[i] = Pixel.WHITE;
      }
      pixels[0] = Pixel.BLACK;
      var frameBuffer = new FrameBuffer();
      field.setAccessible(true);
      field.set(frameBuffer, pixels);
      Assert.assertEquals(pixels, frameBuffer.getPixels());
    } catch (NoSuchFieldException | IllegalAccessException e) {
      Assert.fail("Fail to modify field access.");
    }
  }

}
