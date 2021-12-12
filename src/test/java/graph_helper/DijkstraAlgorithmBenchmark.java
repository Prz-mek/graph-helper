package graph_helper;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import graph_helper.classes.DijkstraAlgorithm;
import graph_helper.classes.DijkstraAlgorithm2;
import graph_helper.interfaces.GraphShotestPath;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 3, warmups = 2)
@Warmup(iterations = 5, time = 150, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 7, time = 200, timeUnit = TimeUnit.MILLISECONDS)
public class DijkstraAlgorithmBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        public GraphShotestPath gsp = new DijkstraAlgorithm();
        public GraphShotestPath gsp2 = new DijkstraAlgorithm2();

        public int[][] adjecencyMatrix = { { 0, 8, 4, 18 }, { 10, 0, 25, 4 }, { 11, 3, 0, 15 }, { 8, 9, 20, 0 } };
        public int source = 0;
        public int target = 3;
    }

    @Benchmark
    public void testForDijkstraAlgorithm(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.gsp.applyAlg(state.adjecencyMatrix, state.source, state.target));
    }

    @Benchmark
    public void testForDijkstraAlgorithm2(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.gsp2.applyAlg(state.adjecencyMatrix, state.source, state.target));
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(DijkstraAlgorithmBenchmark.class.getSimpleName())
                .addProfiler(GCProfiler.class)
                .build();

        new Runner(options).run();
    }
}
