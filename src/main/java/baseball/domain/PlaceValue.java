package baseball.domain;

import java.util.Objects;

public class PlaceValue {

    private int digit;

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public PlaceValue(int digit) {
        this.digit = digit;
    }

    public static PlaceValue of(int digit) {
        return new PlaceValue(digit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(digit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        return obj instanceof PlaceValue &&
                ((PlaceValue) obj).digit == this.digit;
    }
}
