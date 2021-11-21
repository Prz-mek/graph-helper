package graph_helper.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import graph_helper.exceptions.DataError;
import org.json.JSONArray;
import org.json.JSONException;

import graph_helper.exceptions.FileError;
import graph_helper.exceptions.NotNumericError;
import graph_helper.exceptions.WrongDimensionsError;
import graph_helper.interfaces.DataParser;

public class DataReader implements DataParser {

    public int[][] getWeightsMatrix(String path) throws RuntimeException {
        String tmp, text = "";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((tmp = br.readLine()) != null)
                text += tmp;
        } catch (IOException e) {
            throw new FileError();
        }
        JSONArray data = new JSONArray(text);
        int count = data.length();
        double root = Math.sqrt(count);
        if (root % 1 != 0)
            throw new WrongDimensionsError();
        int nodes = (int)root;
        int[][] result = new int[nodes][nodes];
        for (int i = 0; i < nodes; i++)
            for (int j = 0; j < nodes; j++)
                try {
                    result[i][j] = data.getInt(i * nodes + j);
                    if (result[i][j] < 0) {
                        throw new DataError();
                    }
                    if (i > j && result[i][j] != result[j][i]) {
                        throw new DataError();
                    }
                } catch (JSONException e) {
                    throw new NotNumericError();
                }
        return result;
    }
    
}
