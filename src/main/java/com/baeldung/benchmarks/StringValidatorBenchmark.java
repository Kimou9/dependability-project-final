package com.baeldung.benchmarks;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * JMH Microbenchmarks for validation performance.
 * Measures performance of string validation methods using regex.
 * 
 * Run with: mvn clean package && java -jar target/benchmarks.jar
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, warmups = 0)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class StringValidatorBenchmark {

    private String validName = "Jean Dupont";
    private String invalidName = "Jean123";
    private String nullString = null;
    private String defaultValue = "Default";
    private String shortString = "Test";
    private int limit = 100;
    
    // Pre-compiled regex pattern for performance testing
    private Pattern namePattern = Pattern.compile("^[\\p{L}\\s]+$");

    /**
     * Benchmark: Regex match with valid name
     */
    @Benchmark
    public boolean benchmarkRegexMatchValid() {
        return validName != null && namePattern.matcher(validName).find();
    }

    /**
     * Benchmark: Regex match with invalid name
     */
    @Benchmark
    public boolean benchmarkRegexMatchInvalid() {
        return invalidName != null && namePattern.matcher(invalidName).find();
    }

    /**
     * Benchmark: Null check and string operations
     */
    @Benchmark
    public String benchmarkDefaultIfNullNonNull() {
        return shortString != null ? shortString : defaultValue;
    }

    /**
     * Benchmark: Null check returning default
     */
    @Benchmark
    public String benchmarkDefaultIfNullNull() {
        return nullString != null ? nullString : defaultValue;
    }

    /**
     * Benchmark: String length calculation
     */
    @Benchmark
    public int benchmarkCalculerLongueurValid() {
        return (shortString != null && shortString.length() <= limit) ? shortString.length() : -1;
    }

    /**
     * Benchmark: Pattern compilation (expensive operation)
     */
    @Benchmark
    public Pattern benchmarkPatternCompilation() {
        return Pattern.compile("^[\\p{L}\\s]+$");
    }

    /**
     * Main method to run benchmarks
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringValidatorBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(3)
                .measurementIterations(5)
                .build();

        new Runner(opt).run();
    }
}
