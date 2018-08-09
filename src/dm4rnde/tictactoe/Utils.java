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

public class Utils {

	/**
	 * Having list of increasing numbers (numerically sorted array), and a given
	 * number, finds the first number in array that is larger that given number and
	 * returns its position (first index is 1).
	 * 
	 * Examples: see {@link dm4rnde.tictactoe.TestUtils}.
	 * 
	 * @param givenNum
	 *            given number
	 * @param arrayOfSortedNums
	 *            array of numbers, in ascending order
	 * @return corresponding position (first index is 1) of first number in array
	 *         that is larger that given number; on mismatch, return -1
	 */
	public static int findFirstPosOfNumInNumArrThatIsLargerThanGivenNum(int givenNum, int[] arrayOfSortedNums) {

		for (int i = 0; i < arrayOfSortedNums.length; i++) {
			if (givenNum < arrayOfSortedNums[i]) {
				return i + 1;
			}
		}
		return -1;

	}

}
