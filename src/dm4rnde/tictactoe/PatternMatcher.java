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

import java.util.List;

public class PatternMatcher {

	/**
	 * Find if there is a match (at least one full row, or at least one full column,
	 * or at least one full diagonal) for either 'circles' or 'crosses' (whichever
	 * has the turn).
	 * 
	 * @param isCrossTurn
	 * @return
	 */
	public static boolean checkForMatch(boolean isCrossTurn, List<Box> boxes) {

		char[][] c = new char[3][3];
		for (Box b : boxes) {
			char cr = b.getContainsCross() ? 'c' : 'o';
			c[b.getDownPos() - 1][b.getRightPos() - 1] = cr;
		}
		// Arrays.toString(c);
		// Stream.of(c).forEach(r -> System.out.println(Arrays.toString(r)));
		if (horizontalMatch(c, isCrossTurn)) {
			String cr = isCrossTurn ? "crosses" : "circles";
			System.out.format("%s won %s", cr, "horizontal");
			return true;
		}
		if (verticalMatch(c, isCrossTurn)) {
			String cr = isCrossTurn ? "crosses" : "circles";
			System.out.format("%s won %s", cr, "vertical");
			return true;
		}
		if (diagonalMatch(c, isCrossTurn)) {
			String cr = isCrossTurn ? "crosses" : "circles";
			System.out.format("%s won %s", cr, "diagonal");
			return true;
		}
		return false;

	}

	/**
	 * Having matrix find if symbol 'c' or 'o' fills at least one full diagonal.
	 * 
	 * @param matrix
	 * @param checkCrosses
	 * @return
	 */
	private static boolean diagonalMatch(char[][] matrix, boolean checkCrosses) {

		char cr = checkCrosses ? 'c' : 'o';
		if (matrix[0][0] == cr && matrix[1][1] == cr && matrix[2][2] == cr) {
			return true;
		}
		if (matrix[0][2] == cr && matrix[1][1] == cr && matrix[2][0] == cr) {
			return true;
		}
		return false;

	}

	/**
	 * Having matrix find if symbol 'c' or 'o' fills at least one full row.
	 * 
	 * @param matrix
	 * @param checkCrosses
	 * @return
	 */
	private static boolean horizontalMatch(char[][] matrix, boolean checkCrosses) {

		char cr = checkCrosses ? 'c' : 'o';
		int count = 0;
		for (int i = 0; i < 3; i++) {
			count = 0;
			for (int j = 0; j < 3; j++) {
				if (matrix[i][j] == cr) {
					count++;
				}
			}
			if (count == 3) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Having matrix find if symbol 'c' or 'o' fills at least one full column.
	 * 
	 * @param matrix
	 * @param checkCrosses
	 * @return
	 */
	private static boolean verticalMatch(char[][] matrix, boolean checkCrosses) {

		char cr = checkCrosses ? 'c' : 'o';
		int count = 0;
		for (int i = 0; i < 3; i++) {
			count = 0;
			for (int j = 0; j < 3; j++) {
				if (matrix[j][i] == cr) {
					count++;
				}
			}
			if (count == 3) {
				return true;
			}
		}
		return false;

	}

}
