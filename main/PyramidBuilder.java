package pyramid;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        // Check that input numbers are not null
        if (inputNumbers == null) throw new CannotBuildPyramidException();
        for (int i = 0; i < inputNumbers.size(); i++)
            if (inputNumbers.get(i) == null) throw new CannotBuildPyramidException();

        // Check that size of input numbers is a triangular value (so it is possible to build a pyramid)
        double triangNum = (Math.sqrt(8 * inputNumbers.size() + 1) - 1) / 2;
        if (triangNum % 1 != 0) throw new CannotBuildPyramidException();

        // Determine the array for a pyramid
        int[][] pyramid = new int[(int)triangNum][(int)triangNum * 2 - 1];
        for (int i = 0; i < pyramid.length; i++)
            for (int j = 0; j < pyramid[i].length; j++)
                pyramid[i][j] = 0;

        Collections.sort(inputNumbers);

        // Filling a pyramid with sorted input numbers
        int currentElemNum = 0;
        for (int i = 0; i < pyramid.length; i++) {
            int j = (int)triangNum - i - 1;     // Place for input number
            int numbersInARow = i + 1;          // The amount of numbers in each row
            while (numbersInARow > 0) {
                pyramid[i][j] = inputNumbers.get(currentElemNum);
                currentElemNum++;
                numbersInARow--;
                j += 2;
            }
        }

        return pyramid;
    }
}
