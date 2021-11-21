package graph_helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import graph_helper.classes.TspDynamicAlgorithm;

class TspDynamicAlgorithmTest {

    TspDynamicAlgorithm algorithm = new TspDynamicAlgorithm();

    @Test
    public void shouldThrowAnExceptionWhenArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            algorithm.applyAlg(null);
        });
    }

    @Test
    public void shouldThrowAnExceptionWhenArrayLengthIsSmallerThenThree() {
        assertThrows(IllegalArgumentException.class, () -> {
            algorithm.applyAlg(new int[2][2]);
        });
    }

    @Test
    public void shouldThrowAnExceptionWhenArrayLengthIsBiggerThenThirty() {
        assertThrows(IllegalArgumentException.class, () -> {
            algorithm.applyAlg(new int[21][21]);
        });
    }

    @Test
    public void shouldThrowAnExceptionWhenArrayIsNotSquare() {
        assertThrows(IllegalArgumentException.class, () -> {
            algorithm.applyAlg(new int[5][7]);
        });
    }

    @Test
    public void shouldReturnCombination() {
        int[] expected = {3, 5, 9, 17, 6, 10, 18, 12, 20, 24};
        int[] result = TspDynamicAlgorithm.combinations(5, 2).stream().mapToInt(i -> i).toArray();
        assertArrayEquals(expected, result);
    }

    @Test
    public void shouldCheckIfCorrectRouteWithWikipediaExample() {
        int[][] weights = {
                {0, 2, 9, 10},
                {1, 0, 6, 4},
                {15, 7, 0, 8},
                {6, 3, 12, 0},
        };
        int[] expected = {0, 2, 3, 1, 0};

        int[] result = algorithm.applyAlg(weights);

        assertArrayEquals(expected, result);
    }

    @Test
    public void shouldCheckIfCorrectRouteWithFiveVertices() {
        int[][] weights = {
                {0, 49, 34, 96, 74},
                {49, 0, 10, 94, 43},
                {34, 10, 0, 21, 6},
                {96, 94, 21, 0, 70},
                {74, 43, 6, 70, 0}
        };
        int[] expected = {0, 3, 2, 4, 1, 0};

        int[] result = algorithm.applyAlg(weights);

        assertArrayEquals(expected, result);
    }

    @Test
    public void shouldCheckIfCorrectRouteWithSixVertices() {
        int n = 6;
        int[][] weights = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                weights[i][j] = 1000;
            }
        }
        weights[5][0] = 10;
        weights[1][5] = 12;
        weights[4][1] = 2;
        weights[2][4] = 4;
        weights[3][2] = 6;
        weights[0][3] = 8;
        int[] expected = {0, 3, 2, 4, 1, 5, 0};

        int[] result = algorithm.applyAlg(weights);

        assertArrayEquals(expected, result);
    }
}