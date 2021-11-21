package graph_helper;

import static org.junit.Assert.assertArrayEquals;

import graph_helper.exceptions.DataError;
import org.json.JSONException;
import org.junit.Test;

import graph_helper.classes.DataReader;
import graph_helper.exceptions.FileError;
import graph_helper.exceptions.NotNumericError;
import graph_helper.exceptions.WrongDimensionsError;

public class DataReaderTest {
    
    DataReader dr = new DataReader();

    final String DATA = "src\\test\\data\\data";

    String getPath(int n) {
        return DATA + n + ".json";
    }

    @Test(expected = FileError.class)
    public void noSuchFile() {
        dr.getWeightsMatrix(getPath(0));
    }

    @Test(expected = JSONException.class)
    public void wrongJSON() {
        dr.getWeightsMatrix(getPath(1));
    }

    @Test(expected = JSONException.class)
    public void notArray() {
        dr.getWeightsMatrix(getPath(2));
    }

    @Test(expected = NotNumericError.class)
    public void wrongElementClass() {
        dr.getWeightsMatrix(getPath(3));
    }

    @Test(expected = WrongDimensionsError.class)
    public void notSquare() {
        dr.getWeightsMatrix(getPath(4));
    }

    @Test(expected = DataError.class)
    public void notSymmetrical(){
        dr.getWeightsMatrix(getPath(5));
    }

    @Test
    public void correct() {
        int[][] expectedResult = {{0, 2}, {2, 0}};
        int[][] result = dr.getWeightsMatrix(getPath(6));
        assertArrayEquals(expectedResult, result);
    }

}
