package baseball.game;

public class Scoreboard {
    private int strike;
    private int ball;
    private final int rulePlaceValueCount;

    public Scoreboard(int rulePlaceValueCount) {
        this.strike = 0;
        this.ball = 0;
        this.rulePlaceValueCount = rulePlaceValueCount;
    }

    public static Scoreboard newInstance(int rulePlaceValueCount) {
        return new Scoreboard(rulePlaceValueCount);
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public void setScore(Score score) {
        if (score.equals(Score.STRIKE)) {
            this.strike++;
        }
        else if (score.equals(Score.BALL)) {
            this.ball++;
        }
    }

    public boolean isAnswer() {
        return strike == rulePlaceValueCount;
    }

    public String getScoreIfo() {
        if (strike + ball == 0) return Score.NOTHING.getScoreName();

        StringBuilder scoreStringBuilder = new StringBuilder();
        if (ball > 0) {
            scoreStringBuilder.append(ball).append(Score.BALL.getScoreName());
            scoreStringBuilder.append(" ");
        }
        if (strike > 0) {
            scoreStringBuilder.append(strike).append(Score.STRIKE.getScoreName());
        }
        return scoreStringBuilder.toString();
    }

    public enum Score {
        STRIKE("스트라이크"),
        BALL("볼"),
        MISS("미스"),
        NOTHING("낫싱");

        private final String scoreName;

        Score(String scoreName) {
            this.scoreName = scoreName;
        }

        public String getScoreName() {
            return scoreName;
        }
    }
}
