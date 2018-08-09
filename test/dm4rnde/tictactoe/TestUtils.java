package dm4rnde.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestUtils {

	@Test
	void findFirstPosOfNumInNumArrThatIsLargerThanGivenNum() {
		// Having 1,4,6 and given 3, should return 2.
		int givenNum = 3;
		int[] arrayOfSortedNums = new int[] { 1, 4, 6 };
		assertEquals(2, Utils.findFirstPosOfNumInNumArrThatIsLargerThanGivenNum(givenNum, arrayOfSortedNums));
	}

	@Test
	void findFirstPosOfNumInNumArrThatIsLargerThanGivenNum2() {
		// Having 1,4,6 and given 5, should return 3.
		int givenNum = 5;
		int[] arrayOfSortedNums = new int[] { 1, 4, 6 };
		assertEquals(3, Utils.findFirstPosOfNumInNumArrThatIsLargerThanGivenNum(givenNum, arrayOfSortedNums));
	}

	@Test
	void findFirstPosOfNumInNumArrThatIsLargerThanGivenNum3() {
		// Having 1,4,6 and given 4, should return 3.
		int givenNum = 4;
		int[] arrayOfSortedNums = new int[] { 1, 4, 6 };
		assertEquals(3, Utils.findFirstPosOfNumInNumArrThatIsLargerThanGivenNum(givenNum, arrayOfSortedNums));
	}

	@Test
	void findFirstPosOfNumInNumArrThatIsLargerThanGivenNum4() {
		// Having 1,4,6 and given 4, should return -1.
		int givenNum = 10;
		int[] arrayOfSortedNums = new int[] { 1, 4, 6 };
		assertEquals(-1, Utils.findFirstPosOfNumInNumArrThatIsLargerThanGivenNum(givenNum, arrayOfSortedNums));
	}

	@Test
	void findFirstPosOfNumInNumArrThatIsLargerThanGivenNum5() {
		// Having 1,4,6 and given 0, should return 1.
		int givenNum = 0;
		int[] arrayOfSortedNums = new int[] { 1, 4, 6 };
		assertEquals(1, Utils.findFirstPosOfNumInNumArrThatIsLargerThanGivenNum(givenNum, arrayOfSortedNums));
	}

}
