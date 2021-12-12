package graph_helper;

import graph_helper.classes.MinimalSpanTreeAlgorithm;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 3, warmups = 2)
@Warmup(iterations = 5, time = 150, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 7, time = 200, timeUnit = TimeUnit.MILLISECONDS)
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

    public void testForFour(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.algorithm.applyAlg(state.fourMatrix));
    }
    public void testForFive(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.algorithm.applyAlg(state.fiveMatrix));
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}

