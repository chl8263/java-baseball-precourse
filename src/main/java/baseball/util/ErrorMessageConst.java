package baseball.util;

import baseball.game.BaseBallGame;

public class ErrorMessageConst {
    public static final String ERROR_CANNOT_INDUCE_PLACE_VALUE_GIVEN_COUNT = BaseBallGame.PLACE_VALUE_COUNT + "자리의 값을 입력해 주세요.";
    public static final String ERROR_CANNOT_RECEIVE_EMPTY_VALUE = "빈 값을 받을 수 없습니다. " + ERROR_CANNOT_INDUCE_PLACE_VALUE_GIVEN_COUNT;
    public static final String ERROR_CANNOT_RECEIVE_NUMBER_AGAINST_RULES = "1 ~ 10 사이의 수만 입력을 허용합니다.";
    public static final String ERROR_CANNOT_RECEIVE_MORE_THAN_GIVEN_COUNT = BaseBallGame.PLACE_VALUE_COUNT + "보다 큰 자릿수는 입력받을 수 없습니다. " + ERROR_CANNOT_INDUCE_PLACE_VALUE_GIVEN_COUNT;
    public static final String ERROR_CANNOT_RECEIVE_LESS_THAN_GIVEN_COUNT = BaseBallGame.PLACE_VALUE_COUNT + "보다 작은 자릿수는 입력받을 수 없습니다. " + ERROR_CANNOT_INDUCE_PLACE_VALUE_GIVEN_COUNT;
    public static final String ERROR_CANNOT_RECEIVE_CHARACTER_NOT_ABLE_TO_CONVERT_INTEGER = "숫자가 아닌 값은 입력받을 수 없습니다.";
    public static final String ERROR_CANNOT_RECEIVE_DUPLICATE_NUMBER = "중복된 숫자는 입력받을 수 없습니다.";
}
