package graph_helper;

import graph_helper.classes.MinimalSpanTreeAlgorithm;
import graph_helper.exceptions.DataError;
import org.junit.Test;

public class MinimalSpanTreeAlgorithmTest {

    MinimalSpanTreeAlgorithm mst = new MinimalSpanTreeAlgorithm();

    int[][] dataNoConnections = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    int[][] dataGraphNotConnected = {
            {0, 1, 0},
            {1, 0, 0},
            {0, 0, 3}
    };
    int[][] dataCorrect = {
            {0, 1, 9},
            {1, 0, 2},
            {9, 2, 0}
    };

    @Test(expected = DataError.class)
    public void noConnections() {
        mst.applyAlg(dataNoConnections);
    }

    @Test(expected = DataError.class)
    public void graphNotConnected() {
        mst.applyAlg(dataGraphNotConnected);
    }

    @Test
    public void correct() {
        mst.applyAlg(dataCorrect);
    }
}
