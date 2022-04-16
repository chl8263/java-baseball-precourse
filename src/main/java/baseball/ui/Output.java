package baseball.ui;

import baseball.game.BaseBallGame;

public class Output {
    public static final String INDUCE_NUMBER_MESSAGE = "숫자를 입력해주세요 : ";
    public static final String CORRECT_ALL_NUMBER_AND_END_GAME_MESSAGE = BaseBallGame.PLACE_VALUE_COUNT + "개의 숫자를 모두 맞히셨습니다! 게임 종료";
    public static final String INDUCE_THE_GAME_RESUME_OR_END_MESSAGE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    public static final String ERROR_RESTART_AND_EXIT_MESSAGE = "1 또는 2만 입력하여 주세요.";

    public static void printInduceKeepPlaying() {
        System.out.println(INDUCE_THE_GAME_RESUME_OR_END_MESSAGE);
    }

    public static void printInduceNumberMessage() {
        System.out.print(INDUCE_NUMBER_MESSAGE);
    }

    public static void printScore(String score) {
        System.out.println(score);
    }

    public static void printFinishMessage() {
        System.out.println(CORRECT_ALL_NUMBER_AND_END_GAME_MESSAGE);
    }

    public static void printRestartAndExitErrorMessage() {
        System.out.println(ERROR_RESTART_AND_EXIT_MESSAGE);
    }
}
