package graph_helper.classes;

import graph_helper.exceptions.DataError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class MinimalSpanTreeAlgorithm implements graph_helper.interfaces.GraphMST {

    public int[][] applyAlg(int[][] weights) {
        int size = weights.length;
        int[][] connections = new int[size][size];
        HashSet<Integer> nodesConnected = new HashSet<>();
        ArrayList<Integer> possibleWeights = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!possibleWeights.contains(weights[i][j]) && weights[i][j] != 0 && i != j) {
                    possibleWeights.add(weights[i][j]);
                }
            }
        }
        if (possibleWeights.size() == 0) {
            System.out.println("brak połączeń");
            throw new DataError();
        }
        Collections.sort(possibleWeights);

        for (int n = 0; n < possibleWeights.size(); n++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (nodesConnected.size() == size) {
                        return connections;
                    } else if (weights[i][j] <= possibleWeights.get(n) && weights[i][j] > 0) {
                        connections[i][j] = weights[i][j];
                        nodesConnected.add(i);
                        nodesConnected.add(j);
                    }
                }
            }
        }

        boolean noChanges = false;
        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!noChanges) {
            noChanges = true;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (connections[i][j] != 0 && ((visited.contains(i) && !visited.contains(j)) || (!visited.contains(i) && visited.contains(j)))) {
                        visited.add(i);
                        visited.add(j);
                        noChanges = false;
                    }
                }
            }
        }

        if (visited.size() == size) {
            System.out.println("Dobrze");
            return connections;
        } else {
            System.out.println("niepołączone wszystkie");
            throw new DataError();
        }
    }
}
