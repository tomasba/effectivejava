package lt.tomasba.clean;

import com.google.common.base.Preconditions;

/**
 * 2.
 * <li>builder pattern
 *
 * might be useful in JPA entities preventing mutability:
 * https://stackoverflow.com/questions/8111461/how-can-you-use-builder-pattern-for-entities-with-jpa
 */

public class Person {

    private String title;
    private final String name;
    private final String surName;
    private String prefix;

    // make private enforsing the builder usage
    private Person(String title, String name, String surName, String prefix) {
        this.title = title;
        this.name = name;
        this.surName = surName;
        this.prefix = prefix;
    }

    // more elegant way of creating Person objects
    public static Builder builder(String name, String surName) {
        return new Builder(name, surName);
    }

    // a builder in cae we have many class attributes and some of them are optional
    // there are opinions that builder is evil. why there are so many class attributes?
    // isn't the object too big&complex then? cascading constructors might be helpfull instead of the builder.
    public static class Builder {

        private String title;
        private final String name;
        private final String surName;
        private String prefix;

        private Builder(String name, String surName) {
            this.name = name;
            this.surName = surName;
        }

        public static Builder create(String name, String surName) {
            return new Builder(name, surName);
        }

        public Builder title(final String title) {
            this.title = title;
            return this;
        }

        public Builder prefix(final String prefix) {
            this.prefix = prefix;
            return this;
        }

        public Person build() {
            // potentially good place to check for well known validation rules just before object cretion
            // to avoid further exceptions and uncertain functionality.
            Preconditions.checkState(name != null, "Inconsistent Person object state. Person must have a name.");
            Preconditions.checkState(surName != null, "Inconsistent Person object state. Person must have a surname.");
            return new Person(title, name, surName, prefix);
        }
    }

    public static void main(String[] args) {
//        java.lang.IllegalStateException: Inconsistent Person object state. Person must have a name.
//        Person p1 = Person.Builder.create(null, "PEterson").title("Dr.").build();

        //Person p1 = Person.Builder.create("Peter", "Peterson").title("Dr.").build();
        Person p1 = Person.builder("Peter", "Peterson").title("Dr.").build();
    }
}
