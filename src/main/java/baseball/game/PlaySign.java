package baseball.game;

public enum PlaySign {
    RESTART(1),
    EXIT(2);

    private final int signNumber;

    PlaySign(int signNumber) {
        this.signNumber = signNumber;
    }

    public int getSignNumber() {
        return signNumber;
    }
}
