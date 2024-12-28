import java.util.*;
import java.util.function.*;

public class LambdasAndMethodReferences {
    public static void main(String[] args) {
        staticMR();

        boundMR();

        unboundMR();

        constructorMR();
    }

    private static void constructorMR() {
        Supplier<List<String>> listSupplierLambda = () -> new ArrayList<>();

        List<String> list = listSupplierLambda.get();

        list.add("Lambda");

        System.out.println("List after adding 'Lambda': " + list);

        Supplier<List<String>> listSupplierMethodRef = ArrayList::new;

        list = listSupplierMethodRef.get();

        list.add("Method Reference");

        System.out.println("List after adding 'Method Reference' using method reference: " + list);

        Function<Integer, List<Integer>> listWithCapacityLambda = capacity -> new ArrayList<>(capacity);

        List<Integer> intList = listWithCapacityLambda.apply(10);

        intList.add(10);

        System.out.println("List with capacity 10 after adding 'Lambda': " + intList);

        Function<Integer, List<Integer>> listWithCapacityMethodRef = ArrayList::new;

        intList = listWithCapacityMethodRef.apply(10);

        intList.add(20);

        System.out.println("List with capacity 10 after adding 'Method Reference' using method reference: " + intList);
    }

    private static void unboundMR() {
        Predicate<String> isEmptyLambda = str -> str.isEmpty();

        boolean result1 = isEmptyLambda.test("");
        System.out.println("Is the string empty (\" \")? " + result1); //true

        boolean result2 = isEmptyLambda.test("xyz");
        System.out.println("Is the string empty (\"xyz\")? " + result2); //false

        Predicate<String> isEmptyMethodRef = String::isEmpty;

        boolean result3 = isEmptyMethodRef.test("");
        System.out.println("Is the string empty (\" \") using method reference? " + result3); //true

        boolean result4 = isEmptyMethodRef.test("xyz");
        System.out.println("Is the string empty (\"xyz\") using method reference? " + result4); //false

        BiPredicate<String, String> startsWithLambda = (str1, str2) -> str1.startsWith(str2);

        boolean result5 = startsWithLambda.test("Mr. Joe Bloggs", "Mr.");
        System.out.println("Does \"Mr. Joe Bloggs\" start with \"Mr.\"? " + result5); //true

        boolean result6 = startsWithLambda.test("Mr. Joe Bloggs", "Ms.");
        System.out.println("Does \"Mr. Joe Bloggs\" start with \"Ms.\"? " + result6); //false

        BiPredicate<String, String> startsWithMethodRef = String::startsWith;

        boolean result7 = startsWithMethodRef.test("Mr. Joe Bloggs", "Mr.");
        System.out.println("Does \"Mr. Joe Bloggs\" start with \"Mr.\" using method reference? " + result7); //true

        boolean result8 = startsWithMethodRef.test("Mr. Joe Bloggs", "Ms.");
        System.out.println("Does \"Mr. Joe Bloggs\" start with \"Ms.\" using method reference? " + result8); //false
    }

    private static void boundMR() {
        String name = "Mr. Joe Bloggs";

        Predicate<String> startsWithPrefixLambda = prefix -> name.startsWith(prefix);

        boolean result1 = startsWithPrefixLambda.test("Mr.");
        System.out.println("Does the name start with 'Mr.'? " + result1); // true

        boolean result2 = startsWithPrefixLambda.test("Ms.");
        System.out.println("Does the name start with 'Ms.'? " + result2); //false

        Predicate<String> startsWithPrefixMethodRef = name::startsWith;
        boolean result3 = startsWithPrefixMethodRef.test("Mr.");
        System.out.println("Does the name start with 'Mr.' using method reference? " + result3); //true

        boolean result4 = startsWithPrefixMethodRef.test("Ms.");
        System.out.println("Does the name start with 'Ms.' using method reference? " + result4); //false

    }

    private static void staticMR() {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,7,4,5));

        Consumer<ArrayList<Integer>> sortLambda = list -> Collections.sort(list);
        sortLambda.accept(numbers);

        System.out.println("Sorted list using lambda: " + numbers); //[1, 2, 4, 5, 7]

        numbers = new ArrayList<>(Arrays.asList(1,2,7,4,5));

        Consumer<ArrayList<Integer>> sortMethodReference = Collections::sort;

        sortMethodReference.accept(numbers);

        System.out.println("Sorted list using method reference: " + numbers); //[1, 2, 4, 5, 7]


    }
}
