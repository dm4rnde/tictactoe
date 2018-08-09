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

import java.util.ArrayList;
import java.util.List;

public class BoxesLayer {

	// represent boxes/squares/cells in a matrix
	private List<Box> boxes = new ArrayList<>();

	private boolean alreadyContains(Box candidateBox) {
		return getBoxes().contains(candidateBox);
	}

	public boolean isAlreadyFilled(Box candidateBox) {
		return alreadyContains(candidateBox);
	}

	public List<Box> getBoxes() {
		return boxes;
	}

	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}

	public void addBox(Box box) {
		boxes.add(box);
	}

	public void removeBoxes() {
		boxes.clear();
	}

}
