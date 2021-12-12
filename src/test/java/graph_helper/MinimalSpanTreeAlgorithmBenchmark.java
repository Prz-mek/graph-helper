package graph_helper;

import graph_helper.classes.MinimalSpanTreeAlgorithm;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@Measurement(iterations = 3)
@Warmup(iterations = 2)
@Fork(1)
public class MinimalSpanTreeAlgorithmBenchmark {
    @State(Scope.Benchmark)
    public static class BenchmarkState {
        MinimalSpanTreeAlgorithm algorithm = new MinimalSpanTreeAlgorithm();
        public int[][] threeMatrix = {
                {0, 1, 9},
                {1, 0, 2},
                {9, 2, 0}
        };

        public int[][] fourMatrix = {
                {0, 1, 9, 3},
                {1, 0, 2, 1},
                {9, 2, 0, 2},
                {3, 1, 2, 0}
        };

        public int[][] fiveMatrix = {
                {0, 1, 9, 3, 4},
                {1, 0, 2, 1, 3},
                {9, 2, 0, 2, 2},
                {3, 1, 2, 0, 1},
                {4, 3, 2, 1, 0}
        };
    }

    @Benchmark
    public void testForThree(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.algorithm.applyAlg(state.threeMatrix));
    }

    @Benchmark
    public void testForFour(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.algorithm.applyAlg(state.fourMatrix));
    }

    @Benchmark
    public void testForFive(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.algorithm.applyAlg(state.fiveMatrix));
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}

