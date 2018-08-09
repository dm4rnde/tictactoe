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

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Box {

	private int rightPos;
	private int downPos;
	private int smallestDivideRight;
	private int smallestDivideDown;
	private int upperLeftXforImg;
	private int upperLeftYforImg;

	// otherwise circle
	private boolean containsCross = false;

	public Box(int rightPos, int downPos, int frameImgWidth, int frameImgHeight, int imgW, int imgH) {

		this.rightPos = rightPos;
		this.downPos = downPos;
		this.smallestDivideRight = (frameImgWidth / 3 - imgW) / 2;
		this.upperLeftXforImg = smallestDivideRight + ((rightPos - 1) * (imgW + smallestDivideRight * 2));
		this.smallestDivideDown = (frameImgHeight / 3 - imgH) / 2;
		this.upperLeftYforImg = smallestDivideDown + ((downPos - 1) * (imgH + smallestDivideDown * 2));

	}

	public void draw(double x, double y, GraphicsContext gc, Image imgToDrw, boolean containsCross) {
		gc.setFill(Color.BLUE);
		gc.drawImage(imgToDrw, upperLeftXforImg, upperLeftYforImg);
		this.containsCross = containsCross;
	}

	public boolean getContainsCross() {
		return containsCross;
	}

	public int getRightPos() {
		return rightPos;
	}

	public int getDownPos() {
		return downPos;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Box) {
			Box b2 = (Box) obj;
			if (this.rightPos == b2.rightPos && this.downPos == b2.downPos) {
				return true;
			}
		}
		return false;

	}
}