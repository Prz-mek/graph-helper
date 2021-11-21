package graph_helper.classes;

import graph_helper.interfaces.GraphAlg;

public class TspDynamicAlgorithm implements GraphAlg{

    private static int INF = Integer.MAX_VALUE;

    @Override
    public int[] applyAlg(int[][] weights) {
        if (weights == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        if (weights.length < 3) {
            throw new IllegalArgumentException("Too small array.");
        }

        if (weights.length != weights[0].length) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        if (weights.length > 20) {
            throw new IllegalArgumentException("Too big array.");
        }

        int n = weights.length;
        int[][] costs = new int[n][1 << n];

        // Add all outgoing edges from starting node (0) to memory
        for (int end = 1; end < n; end++) {
            costs[end][(1 << end) + 1] = weights[0][end];
        }

        // Main part of algorithm
        for (int s = 2; s < (1 << n); s++) {
            for (int startNode = 1; startNode < n; startNode++) {
                if (((1 << startNode) & s) == 0) continue;
                int minWeight = INF;
                for (int lastNode = 1; lastNode < n; lastNode++) {
                    if (lastNode == startNode || ((1 << lastNode) & s) == 0) continue;
                    minWeight = Math.min(minWeight,
                            costs[lastNode][s ^ (1 << startNode)] + weights[lastNode][startNode]);
                }
                costs[startNode][s] = minWeight;
            }
        }

        return reconstructRoute(weights, costs);
    }

    // Path reconstruction
    private int[] reconstructRoute(int[][] weights, int[][] costs) {
        int n = weights.length;
        int nextIndex = 0;
        int s = (1 << n) - 1;
        int[] route = new int[n + 1];
        route[n] = 0;

        for (int i = 1; i < n; i++) {
            int bestIndex = -1;
            int bestWeight = INF;
            for (int endNode = 1; endNode < n; endNode++) {
                if (((1 << endNode) & s) == 0) continue;
                int newWeight = costs[endNode][s] + weights[endNode][nextIndex];
                if (newWeight < bestWeight) {
                    bestIndex = endNode;
                    bestWeight = newWeight;
                }
            }
            route[n - i] = bestIndex;
            s ^= (1 << bestIndex);
            nextIndex = bestIndex;
        }
        route[0] = 0;
        return route;
    }
}
