package graph_helper;

import graph_helper.classes.DijkstraAlgorithm;
import graph_helper.exceptions.*;
import graph_helper.interfaces.GraphShotestPath;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.Test;

public class DijkstraAlgorithmTest {

    GraphShotestPath gsp = new DijkstraAlgorithm();

    int[][] dataNoPath = { { 0, 2, Integer.MAX_VALUE }, { 4, 0, Integer.MAX_VALUE }, { 1, 5, 0 } };
    int[][] dataNonZeroDiagonal = { { 0, 1, 0 }, { 1, 0, 0 }, { 0, 0, 3 } };
    int[][] dataNotSquareMatrix = { { 0, 2, 9 }, { 4, 0, 6 } };
    int[][] dataCorrect = { { 0, 2, 9 }, { 4, 0, 6 }, { 1, 5, 0 } };

    @Test(expected = NoPathError.class)
    public void noPath() {
        gsp.applyAlg(dataNoPath, 0, 2);
    }

    @Test(expected = DataError.class)
    public void nonZeroDiagonal() {
        gsp.applyAlg(dataNonZeroDiagonal, 0, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notSquareMatrix() {
        gsp.applyAlg(dataNotSquareMatrix, 0, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullWeigths() {
        gsp.applyAlg(null, 0, 2);
    }

    @Test
    public void correct() {
        int[] correctPath = { 0, 1, 2 };
        int[] actualPath = gsp.applyAlg(dataCorrect, 0, 2);
        assertArrayEquals(correctPath, actualPath);
    }
}
