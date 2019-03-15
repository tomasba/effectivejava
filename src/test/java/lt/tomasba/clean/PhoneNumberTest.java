package lt.tomasba.clean;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneNumberTest {

    @Test
    public void testReflective() {
        PhoneNumber p1 = PhoneNumber.create(123, 123456);
        assertThat(p1, equalTo(p1));
    }

    @Test
    public void testSymetrical() {
        PhoneNumber p1 = PhoneNumber.create(123, 123456);
        PhoneNumber p2 = PhoneNumber.create(123, 123456);
        assertThat(p1, equalTo(p2));
        assertThat(p2, equalTo(p1));
    }

    @Test
    public void testTransitive() {
        PhoneNumber p1 = PhoneNumber.create(123, 123456);
        PhoneNumber p2 = PhoneNumber.create(123, 123456);
        PhoneNumber p3 = PhoneNumber.create(123, 123456);
        assertThat(p1, equalTo(p2));
        assertThat(p2, equalTo(p3));
        assertThat(p3, equalTo(p1));
    }

    @Test
    public void testConsistent() {
        PhoneNumber p1 = PhoneNumber.create(123, 123456);
        PhoneNumber p2 = PhoneNumber.create(123, 123456);
        assertThat(p1, equalTo(p2));
        assertThat(p1, equalTo(p2));
        assertThat(p1, equalTo(p2));
        assertThat(p1, equalTo(p2));
        assertThat(p1, equalTo(p2));
        assertThat(p1, equalTo(p2));
    }

    @Test
    public void testNonNullity() {
        PhoneNumber p1 = PhoneNumber.create(123, 123456);
        assertThat(p1, not(equalTo(null)));
    }

}
