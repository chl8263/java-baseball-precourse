package baseball.game;

import baseball.domain.PlaceValue;
import baseball.domain.PlaceValueFactory;
import baseball.exception.PlaceValueIllegalArgumentException;
import baseball.exception.PlaceValueIndexOutOfException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static baseball.util.ErrorMessageConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BaseBallGameTest {

    @InjectMocks
    private Game baseBallGame = BaseBallGame.newInstance();

    @ParameterizedTest
    @CsvSource({
            "3, 123, 123, 3, 0",
            "3, 123, 213, 1, 2",
            "3, 123, 163, 2, 0",
            "3, 123, 749, 0, 0",
    })
    void getScoreBoard_GiveAnswerAndUserInput_ReturnExpectedScore(int gameRulePlaceValueCount, String answers, String userInputs, int expectedStrikeCount, int expectedBallCount) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<PlaceValue> answerList = PlaceValueFactory.createPlaceValues(gameRulePlaceValueCount, answers);
        List<PlaceValue> userInputList = PlaceValueFactory.createPlaceValues(gameRulePlaceValueCount, userInputs);

        Method method = BaseBallGame.class.getDeclaredMethod("getScoreBoard", List.class, List.class);
        method.setAccessible(true);
        Scoreboard scoreboard = (Scoreboard)method.invoke(baseBallGame, answerList, userInputList);

        assertThat(scoreboard).isNotNull();
        assertThat(scoreboard.getStrike()).isEqualTo(expectedStrikeCount);
        assertThat(scoreboard.getBall()).isEqualTo(expectedBallCount);
    }
}
