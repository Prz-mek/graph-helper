package graph_helper;

import graph_helper.classes.TspDynamicAlgorithm;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;

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

        public int[][] matrixFour = {
                {-1155484576, -723955400, 1033096058, -1690734402, -1557280266, 1327362106, -1930858313, 502539523, -1728529858, -938301587},
                {1431162155, 1085665355, 1654374947, -1661998771, -65105105, -73789608, -518907128, 99135751, -252332814, 755814641},
                {1180918287, 1344049776, 553609048, 1580443894, 629649304, -1266264776, 99807007, 5955764, -1946737912, 39620447},
                {-152527805, -1877116806, 448784075, 1086124775, -1609984092, 1227951724, 1764356251, 64111306, -960731419, -100082026},
                {-39845375, -1339022546, 2092649110, -568315836, -1089884900, -81839914, -1146103148, -1846688624, -784703072, 55004124},
                {-691960657, 1770461755, -2032810463, -1177788003, -432352882, -65824064, 575267217, -1949367821, 356750287, 798819494},
                {-92022521, 1318001577, -1192467086, -1412716779, -1223932479, 276053035, 615126903, 1542603436, 1988388716, 1177882237},
                {19265476, -1430871151, 307082914, -1333570194, 1496453452, -790542135, 1455004595, -1690249972, -604059026, -290476856},
                {-122204761, -1097539750, -576617062, -2002123957, -1663951485, 193034304, 768747578, 1337360486, 934457803, 873612482},
                {-624972850, 355564760, 41547336, 1781447028, -1321591463, 1081281446, -982203381, -222544851, -1233998085, -1331702554},
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

    @Benchmark
    public void testForTenVertices(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(state.algorithm.applyAlg(state.matrixThree));
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
