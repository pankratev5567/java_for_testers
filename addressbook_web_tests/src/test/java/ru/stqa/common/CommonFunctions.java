package ru.stqa.common;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int n){
        var r = new Random();
        Supplier<Integer> randomNumbers = ()-> r.nextInt(26);
        return Stream.generate(randomNumbers)
                .limit(n)
                .map(i->'a'+i)
                .map(Character::toString)
                .collect(Collectors.joining());
    }
}
