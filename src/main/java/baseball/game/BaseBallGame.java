package baseball.game;

import baseball.domain.PlaceValue;
import baseball.domain.PlaceValueFactory;
import baseball.ui.Output;
import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class BaseBallGame extends Game{
    public static final int PLACE_VALUE_COUNT = 3;

    public static BaseBallGame newInstance() {
        return new BaseBallGame();
    }

    @Override
    public void play() {
        boolean keepPlaying;
        do {
            playOneRound();
            keepPlaying = getPlaySign();
        } while (keepPlaying);
    }

    private void playOneRound() {
        List<PlaceValue> answerNumbers = getAnswers();
        boolean isFinish;
        do {
            List<PlaceValue> triedNumbers = getUserTriedNumbers();
            Scoreboard scoreboard = getScoreBoard(answerNumbers, triedNumbers);
            isFinish = scoreboard.isAnswer();
            showScore(scoreboard.getScoreIfo());
        } while (!isFinish);

        showFinishMessage();
    }

    private boolean getPlaySign() {
        Output.printInduceKeepPlaying();
        String playSignString = Console.readLine();
        if (!validatePlayingSign(playSignString)) {
            showRestartAndExitErrorMessage();
            return getPlaySign();
        }
        return Integer.parseInt(playSignString) == PlaySign.RESTART.getSignNumber();
    }

    private boolean validatePlayingSign(String playSign) {
        return playSign.length() == 1 && Character.isDigit(playSign.charAt(0)) && (Integer.parseInt(playSign) == 1 || Integer.parseInt(playSign) == 2);
    }

    private List<PlaceValue> getUserTriedNumbers() {
        Output.printInduceNumberMessage();
        String triedData = Console.readLine();
        return PlaceValueFactory.createPlaceValues(PLACE_VALUE_COUNT, triedData);
    }

    private List<PlaceValue> getAnswers() {
        return PlaceValueFactory.createRandomPlaceValues(PLACE_VALUE_COUNT);
    }

    private Scoreboard getScoreBoard(List<PlaceValue> answerNumbers, List<PlaceValue> triedNumbers) {
        Scoreboard scoreboard = Scoreboard.newInstance(PLACE_VALUE_COUNT);

        for (int i = 0; i < triedNumbers.size(); i++) {
            Scoreboard.Score score = calculateScore(answerNumbers, triedNumbers.get(i), i);
            scoreboard.setScore(score);
        }
        return scoreboard;
    }

    private Scoreboard.Score calculateScore(List<PlaceValue> answerNumbers, PlaceValue triedNumber, int index) {
        if (answerNumbers.get(index).equals(triedNumber)) {
            return Scoreboard.Score.STRIKE;
        } else if(!answerNumbers.get(index).equals(triedNumber) && answerNumbers.contains(triedNumber)) {
            return Scoreboard.Score.BALL;
        }
        return Scoreboard.Score.MISS;
    }

    private void showScore(String score) {
        Output.printScore(score);
    }

    private void showFinishMessage() {
        Output.printFinishMessage();
    }

    private void showRestartAndExitErrorMessage() {
        Output.printRestartAndExitErrorMessage();
    }
}
