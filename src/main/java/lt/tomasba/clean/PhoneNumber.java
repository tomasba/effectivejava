package lt.tomasba.clean;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import java.util.*;

/**
 * 1.
 * <li>keep your objects immutable
 * <li>prefer using factory for creating object instances (see code/comments for more details)
 * <li>equals/hashcode
 * <li>printing out the object for developer and end-user
 * <li>comparing the object
 */
public class PhoneNumber implements Formattable, Comparable<PhoneNumber> {

    // static factory is convinient - you provide a meaningfull name. i.e. having return type an interface might return
    // concrete instances. Also might include some validations.
    // might not be applicable when there are many parameters (i.e. more then 3) or some of the parameters is optional.
    public static PhoneNumber create(int number, int areaCode) {
        Preconditions.checkArgument(number > 0);
        Preconditions.checkArgument(areaCode > 0);
        return new PhoneNumber(number, areaCode);
    }

    private static final Comparator<PhoneNumber> PHONE_NUMBER_COMPARATOR = Comparator
            .comparingInt((PhoneNumber pn) -> pn.areaCode)
            .thenComparingInt(pn -> pn.number);

    private final int number;
    private final int areaCode;

    // keep your objects immutable!!
    private PhoneNumber(int number, int areaCode) {
        this.number = number;
        this.areaCode = areaCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return number == that.number &&
                areaCode == that.areaCode;
    }

    @Override
    public int hashCode() {
        // the Objects.hash may be heavy as internally it is creating arrays.
        // consider using other options if the object is heavily used in sets, or as a key in hashmap.
        return Objects.hash(number, areaCode);
    }

    // usually for debugging output
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("number", number)
                .add("areaCode", areaCode)
                .toString();
    }

    // for user output
    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%d-%d", areaCode, number);
    }

    @Override
    public int compareTo(PhoneNumber phoneNumber) {

        // before java 8
//        ComparisonChain.start()
//                .compare(this.areaCode, phoneNumber.areaCode)
//                .compare(this.number, phoneNumber.number)
//                .result();

        return PHONE_NUMBER_COMPARATOR.compare(this, phoneNumber);
    }

    public static void main(String[] args) {
        PhoneNumber pn1 = PhoneNumber.create(56327489, 5);
        System.out.println(pn1);
        System.out.println(String.format("%s", pn1));

        PhoneNumber pn2 = PhoneNumber.create(5632, 2);
        PhoneNumber pn3 = PhoneNumber.create(5632, 4);
        PhoneNumber pn4 = PhoneNumber.create(32, 9);
        PhoneNumber pn5 = PhoneNumber.create(632, 5);

        List<PhoneNumber> numberList = Arrays.asList(pn1, pn2, pn3, pn4, pn5);
        Collections.sort(numberList);

        // another way to sort if avoiding implementing Comparable
        //numberList.sort(PHONE_NUMBER_COMPARATOR);

        System.out.println(numberList);
    }

}
