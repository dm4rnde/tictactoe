/*
Copyright 2018 dm4rnde (dm4rnde@protonmail.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package dm4rnde.tictactoe;

/**
 * Game constants.
 */
public class Consts {

	// size of the main root stage
	public static int wW = 210;
	public static int wH = 240;
	// size of the canvas
	// expect bg image to be perfect square
	public static int frameImgW = wW;
	public static int frameImgH = wW;
	// size of each box
	public static int bxW = frameImgW / 3;
	public static int bxH = frameImgH / 3;
	// matrix X and matrix Y side
	// filled with either x or y coordinates that represent location where boxes end
	public static int[] mX = new int[] { bxW, 2 * bxW, frameImgW };
	public static int[] mY = new int[] { bxH, 2 * bxH, frameImgH };
	// size of the image to be draw on the box
	public static int imgW = 54;
	public static int imgH = 54;

}
