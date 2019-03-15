package lt.tomasba.clean;

import java.util.Map;
import java.util.Optional;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operation {

    ADD(Addition::add, "A"),
    SUBTRACT((x, y) -> x - y, "S"),
    DIVIDE((x, y) -> x - y, "D"),
    MULTIPLY((x , y) -> x * y, "M");

    private final IntBinaryOperator operator;

    private final String databaseValue;

    private final static Map<String, Operation> VALUE_MAP =
            Stream.of(values()).collect(Collectors.toMap(o -> o.databaseValue, o -> o));
    // prior to java 8
//    static {
//        for (Operation op : values()) {
//            // populate the map
//        }
//    }

    Operation(IntBinaryOperator operator, String databaseValue) {
        this.operator = operator;
        this.databaseValue = databaseValue;
    }


    public static Optional<Operation> fromString(String databaseValue) {
        // to avoid NULLs might use getOrDefault. But might also use Optionals.
        // return VALUE_MAP.getOrDefault(databaseValue, Operation.ADD);
        return Optional.ofNullable(VALUE_MAP.get(databaseValue));
    }

    public String toDatabaseValue() {
        return databaseValue;
    }

    // previous to java 8
    // each and every enum constant should implement the apply method.
    // public abstract int apply(int x, int y);
}
