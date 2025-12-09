package com.baeldung.benchmarks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.baeldung.generics.CollectionUtils;

/**
 * JMH Microbenchmarks for CollectionUtils generic methods.
 * Measures performance of collection operations.
 * 
 * Run with: mvn clean package && java -jar target/benchmarks.jar
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, warmups = 0)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class CollectionUtilsBenchmark {

    private List<String> stringList;
    private List<Integer> intList;
    private List<Number> numberList;

    @Setup(Level.Trial)
    public void setup() {
        stringList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            stringList.add("Element_" + i);
        }
        
        intList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            intList.add(i);
        }
        
        numberList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            numberList.add((long)i);
        }
    }

    /**
     * Benchmark: print generic element (simple operation)
     */
    @Benchmark
    public void benchmarkPrintGeneric() {
        CollectionUtils.print("Test_Element");
    }

    /**
     * Benchmark: swap operation in list
     */
    @Benchmark
    public void benchmarkSwap() {
        CollectionUtils.swap(stringList, 0, 999);
    }

    /**
     * Benchmark: mergeTypeParameter with bounded types
     */
    @Benchmark
    public List<String> benchmarkMergeTypeParameter() {
        List<String> list1 = new ArrayList<>(stringList.subList(0, 100));
        List<String> list2 = new ArrayList<>(stringList.subList(100, 200));
        return CollectionUtils.mergeTypeParameter(list1, list2);
    }

    /**
     * Benchmark: mergeWildcard with wildcard bounds
     */
    @Benchmark
    public List<? extends String> benchmarkMergeWildcard() {
        List<String> list1 = new ArrayList<>(stringList.subList(0, 100));
        List<String> list2 = new ArrayList<>(stringList.subList(100, 200));
        return CollectionUtils.mergeWildcard(list1, list2);
    }

    /**
     * Benchmark: sum operation on List<Number>
     */
    @Benchmark
    public long benchmarkSum() {
        return CollectionUtils.sum(numberList);
    }

    /**
     * Benchmark: sumTypeParameter with bounded type parameter
     */
    @Benchmark
    public long benchmarkSumTypeParameter() {
        return CollectionUtils.sumTypeParameter(intList);
    }

    /**
     * Benchmark: sumWildcard with wildcard bounds
     */
    @Benchmark
    public long benchmarkSumWildcard() {
        return CollectionUtils.sumWildcard(numberList);
    }

    /**
     * Benchmark: addNumber with super wildcard
     */
    @Benchmark
    public void benchmarkAddNumber() {
        List<Integer> integerList = new ArrayList<>();
        CollectionUtils.addNumber(integerList, 42);
    }

    /**
     * Main method to run benchmarks
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CollectionUtilsBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(3)
                .measurementIterations(5)
                .build();

        new Runner(opt).run();
    }
}
