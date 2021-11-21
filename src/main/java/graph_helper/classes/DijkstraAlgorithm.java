package graph_helper.classes;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.PriorityQueue;

import graph_helper.exceptions.DataError;
import graph_helper.exceptions.NoPathError;
import graph_helper.interfaces.GraphShotestPath;

public class DijkstraAlgorithm implements GraphShotestPath {

    private static int INF = Integer.MAX_VALUE;
    private static int UNDEFINED = -1;

    @Override
    public int[] applyAlg(int[][] weights, int source, int target) {
        if (weights == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        } else if (weights.length != weights[0].length) {
            throw new IllegalArgumentException("Matrix must be square.");
        }
        for (int i = 0; i < weights.length; i++) {
            if (weights[i][i] != 0)
                throw new DataError();
        }

        int[] prev = dijkstra(weights, source, target);
        int[] path = recreatePath(source, target, prev);

        return path;
    }

    private int[] dijkstra(int[][] weights, int source, int target) {
        PriorityQueue<Entry<Integer, Integer>> queue = new PriorityQueue<>(
                Comparator.comparing(entry -> entry.getValue()));
        PriorityQueue<Entry<Integer, Integer>> queue_copy = new PriorityQueue<>(
                Comparator.comparing(entry -> entry.getValue()));
        int length = weights.length;
        int[] dist = new int[length];
        int[] prev = new int[length];
        dist[source] = 0;

        for (int i = 0; i < length; i++) {
            if (i != source) {
                dist[i] = INF;
                prev[i] = UNDEFINED;
            }
            queue.add(new SimpleEntry<>(i, dist[i]));
        }

        while (!queue.isEmpty()) {
            int min = queue.poll().getKey();

            if (min == target) {
                return prev;
            }

            for (Entry<Integer, Integer> entry : queue) {
                int node = entry.getKey();
                int alt = weights[min][node] < INF ? dist[min] + weights[min][node] : INF;
                if (alt < dist[node]) {
                    dist[node] = alt;
                    prev[node] = min;
                    queue_copy.add(new SimpleEntry<>(node, alt));
                } else {
                    queue_copy.add(entry);
                }
            }
            queue = new PriorityQueue<>(queue_copy);
            queue_copy.clear();
        }

        return prev;
    }

    private int[] recreatePath(int source, int target, int[] prev) {
        LinkedList<Integer> path = new LinkedList<>();
        int current = target;

        while (current != UNDEFINED) {
            path.addFirst(current);

            if (current == source)
                break;

            current = prev[current];
        }

        if (current == UNDEFINED)
            throw new NoPathError();

        return path.stream().mapToInt(i -> i).toArray();
    }
}