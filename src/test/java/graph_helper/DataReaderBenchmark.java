package graph_helper;

import graph_helper.classes.DataReader;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@Measurement(iterations = 3)
@Warmup(iterations = 2)
@Fork(1)
public class DataReaderBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        public DataReader dr = new DataReader();

        public String
        pathSmallCorrect = "src/test/data/data7.json",
        pathLargeCorrect = "src/test/data/data11.json";
    }

    @Benchmark
    public void testForSmallCorrect(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.dr.getWeightsMatrix(state.pathSmallCorrect));
    }

    @Benchmark
    public void testForLargeCorrect(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.dr.getWeightsMatrix(state.pathLargeCorrect));
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
