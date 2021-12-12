package graph_helper;

//import org.openjdk.jmh.annotations.*;

import graph_helper.classes.TspDynamicAlgorithm;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@Measurement(iterations = 3)
@Warmup(iterations = 2)
@Fork(1)
public class TspDynamicAlgorithmBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        public TspDynamicAlgorithm algorithm = new TspDynamicAlgorithm();

        public int[][] matrixOne = {
                {0, 2, 9, 10},
                {1, 0, 6, 4},
                {15, 7, 0, 8},
                {6, 3, 12, 0},
        };

        public int[][] matrixTwo = {
                {0, 49, 34, 96, 74},
                {49, 0, 10, 94, 43},
                {34, 10, 0, 21, 6},
                {96, 94, 21, 0, 70},
                {74, 43, 6, 70, 0}
        };

        public int[][] matrixThree = {
                {0, 1000, 1000, 8, 1000, 1000},
                {1000, 0, 1000, 1000, 1000, 12},
                {1000, 1000, 0, 1000, 4, 1000},
                {1000, 1000, 6, 0, 1000, 1000},
                {1000, 2, 1000, 1000, 0, 1000},
                {10, 1000, 1000, 1000, 1000, 0}
        };
    }

    @Benchmark
    public void testForFourVertices(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.algorithm.applyAlg(state.matrixOne));
    }

    @Benchmark
    public void testForFiveVertices(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.algorithm.applyAlg(state.matrixTwo));
    }

    @Benchmark
    public void testForSixVertices(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.algorithm.applyAlg(state.matrixThree));
    }


    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
