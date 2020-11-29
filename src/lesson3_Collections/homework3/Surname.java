package lesson3_Collections.homework3;

public class Surname {
    private final String surname;

    public Surname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Surname another = (Surname) obj;
        return this.hashCode() == another.hashCode();
    }

    @Override
    public int hashCode() {
        return (int) (surname.hashCode() * Math.random());
    }

    @Override
    public String toString() {
        return surname;
    }
}
