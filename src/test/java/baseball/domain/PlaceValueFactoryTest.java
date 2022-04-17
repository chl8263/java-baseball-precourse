package baseball.domain;

import baseball.exception.PlaceValueIllegalArgumentException;
import baseball.exception.PlaceValueIndexOutOfException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static baseball.util.ErrorMessageConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlaceValueFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "3, 123",
            "5, 12376",
            "7, 3942856",
    })
    void createPlaceValues_GiveValidString_ReturnPlaceValueList(int gameRulePlaceValueCount, String placeValueString) {
        List<PlaceValue> placeValueList = PlaceValueFactory.createPlaceValues(gameRulePlaceValueCount, placeValueString);

        assertThat(placeValueList).isNotNull();
        assertThat(placeValueList).isNotEmpty();
        assertThat(placeValueList.size()).isEqualTo(gameRulePlaceValueCount);
        for (int i = 0; i < gameRulePlaceValueCount; i ++) {
            assertThat(placeValueList).containsAnyOf(placeValueList.get(i));
        }
    }

    @Test
    void createPlaceValues_InputStringIsEmpty_ExceptionThrown() {
        int gameRulePlaceValueCount = 3;
        String placeValueString = "";

        assertThatThrownBy(() -> PlaceValueFactory.createPlaceValues(gameRulePlaceValueCount, placeValueString))
                .isInstanceOf(PlaceValueIllegalArgumentException.class)
                .hasMessageContaining(ERROR_CANNOT_RECEIVE_EMPTY_VALUE);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 1234125231542",
            "5, 1234125231542ascs",
            "7, 1234125231542124253325",
    })
    void createPlaceValues_InputStringPlaceValueCountIsMoreThanGameRulesPlaceValueCount_ExceptionThrown(int gameRulePlaceValueCount, String placeValueString) {
        assertThatThrownBy(() -> PlaceValueFactory.createPlaceValues(gameRulePlaceValueCount, placeValueString))
                .isInstanceOf(PlaceValueIllegalArgumentException.class)
                .hasMessageContaining(ERROR_CANNOT_RECEIVE_MORE_THAN_GIVEN_COUNT);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 12",
            "5, 146",
            "7, 1",
    })
    void createPlaceValues_InputStringPlaceValueCountIsLessThanGameRulesPlaceValueCount_ExceptionThrown(int gameRulePlaceValueCount, String placeValueString) {
        assertThatThrownBy(() -> PlaceValueFactory.createPlaceValues(gameRulePlaceValueCount, placeValueString))
                .isInstanceOf(PlaceValueIllegalArgumentException.class)
                .hasMessageContaining(ERROR_CANNOT_RECEIVE_LESS_THAN_GIVEN_COUNT);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 012",
            "3, 901",
            "3, 000",
    })
    void createPlaceValues_InputStringPlaceValueWithInvalidNumber_ExceptionThrown(int gameRulePlaceValueCount, String placeValueString) {
        assertThatThrownBy(() -> PlaceValueFactory.createPlaceValues(gameRulePlaceValueCount, placeValueString))
                .isInstanceOf(PlaceValueIllegalArgumentException.class)
                .hasMessageContaining(ERROR_CANNOT_RECEIVE_NUMBER_AGAINST_RULES);
    }

    @ParameterizedTest
    @CsvSource({
            "3, (12",
            "3, ()*",
            "3, WVS",
            "5, !#!^W",
    })
    void createPlaceValues_InputStringPlaceValueWithNotNumber_ExceptionThrown(int gameRulePlaceValueCount, String placeValueString) {
        assertThatThrownBy(() -> PlaceValueFactory.createPlaceValues(gameRulePlaceValueCount, placeValueString))
                .isInstanceOf(PlaceValueIllegalArgumentException.class)
                .hasMessageContaining(ERROR_CANNOT_RECEIVE_CHARACTER_NOT_ABLE_TO_CONVERT_INTEGER);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 992",
            "5, 11125",
            "7, 1523369",
    })
    void createPlaceValues_InputStringPlaceValueWithDuplicateNumber_ExceptionThrown(int gameRulePlaceValueCount, String placeValueString) {
        assertThatThrownBy(() -> PlaceValueFactory.createPlaceValues(gameRulePlaceValueCount, placeValueString))
                .isInstanceOf(PlaceValueIllegalArgumentException.class)
                .hasMessageContaining(ERROR_CANNOT_RECEIVE_DUPLICATE_NUMBER);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 7, 9})
    void createRandomPlaceValues_GiveCount_ReturnWithoutDuplicates(int gameRulePlaceValueCount) {
        List<PlaceValue> placeValueList = PlaceValueFactory.createRandomPlaceValues(gameRulePlaceValueCount);

        assertThat(placeValueList).isNotNull();
        assertThat(placeValueList).isNotEmpty();
        assertThat(placeValueList.size()).isEqualTo(gameRulePlaceValueCount);
        for (int i = 0; i < gameRulePlaceValueCount; i ++) {
            assertThat(placeValueList).containsAnyOf(placeValueList.get(i));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {10 , 20})
    void createRandomPlaceValues_PlaceValueCountIsMoreThan10_ExceptionThrown(int count) {
        assertThatThrownBy(() -> PlaceValueFactory.createRandomPlaceValues(count))
                .isInstanceOf(PlaceValueIndexOutOfException.class)
                        .hasMessageContaining(ERROR_CANNOT_RECEIVE_NUMBER_AGAINST_RULES);
    }
}
