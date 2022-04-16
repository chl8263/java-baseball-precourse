package baseball.domain;

import baseball.exception.PlaceValueIllegalArgumentException;
import baseball.exception.PlaceValueIndexOutOfException;
import camp.nextstep.edu.missionutils.Randoms;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;

import java.util.List;
import java.util.Set;

import static baseball.util.ErrorMessageConst.*;

public class PlaceValueFactory {

    public static List<PlaceValue> createPlaceValues(int gameRulePlaceValueCount, String placeValueString) throws PlaceValueIllegalArgumentException {
        validatePlaceValueString(gameRulePlaceValueCount, placeValueString);

        List<PlaceValue> placeValues = Lists.newArrayList();
        Set<Integer> duplicateCheckSet = Sets.newHashSet();

        for (int i = 0; i < placeValueString.length(); i ++) {
            int placeValueNumber = convertCharacterToInteger(placeValueString.charAt(i));
            validateNumber(placeValueNumber);
            validateDuplicateNumber(placeValueNumber, duplicateCheckSet);
            duplicateCheckSet.add(placeValueNumber);
            PlaceValue placeValue = PlaceValue.of(placeValueNumber);
            placeValues.add(placeValue);
        }

        return placeValues;
    }

    public static List<PlaceValue> createRandomPlaceValues(int gameRulePlaceValueCount) {
        if (gameRulePlaceValueCount > 9) throw new PlaceValueIndexOutOfException(ERROR_CANNOT_RECEIVE_NUMBER_AGAINST_RULES);

        List<PlaceValue> result = Lists.newArrayList();
        Set<Integer> duplicateCheckSet = Sets.newHashSet();

        for (int i = 0; i < gameRulePlaceValueCount; i ++) {
            int pickedRandomNumber = pickRandomNumber(duplicateCheckSet);
            duplicateCheckSet.add(pickedRandomNumber);
            PlaceValue pickedPlaceValue = PlaceValue.of(pickedRandomNumber);
            result.add(pickedPlaceValue);
        }

        return result;
    }

    private static int pickRandomNumber(Set<Integer> duplicateCheckSet) {
        boolean isDuplicateNumber;
        int pickNumber;

        do {
            pickNumber = Randoms.pickNumberInRange(1, 9);
            isDuplicateNumber = isDuplicateNumber(pickNumber, duplicateCheckSet);
        } while (isDuplicateNumber);

        return pickNumber;
    }

    public static int convertCharacterToInteger(char character) {
        if (!Character.isDigit(character)) {
            throw new PlaceValueIllegalArgumentException(ERROR_CANNOT_RECEIVE_CHARACTER_NOT_ABLE_TO_CONVERT_INTEGER);
        }
        return Integer.parseInt(String.valueOf(character));
    }

    private static boolean isDuplicateNumber(int number, Set<Integer> duplicateCheckSet) {
        return duplicateCheckSet.contains(number);
    }

    private static void validateDuplicateNumber(int number, Set<Integer> duplicateCheckSet) throws PlaceValueIllegalArgumentException {
        if (duplicateCheckSet.contains(number)) throw new PlaceValueIllegalArgumentException(ERROR_CANNOT_RECEIVE_DUPLICATE_NUMBER);
    }

    private static void validateNumber(int number) throws PlaceValueIllegalArgumentException {
        if (number < 1 || number >9) throw new PlaceValueIllegalArgumentException(ERROR_CANNOT_RECEIVE_NUMBER_AGAINST_RULES);
    }

    public static void validatePlaceValueString(int gameRulePlaceValueCount, String placeValueString) throws PlaceValueIllegalArgumentException {
        if (placeValueString.isEmpty()) {
            throw new PlaceValueIllegalArgumentException(ERROR_CANNOT_RECEIVE_EMPTY_VALUE);
        } else if (placeValueString.length() > gameRulePlaceValueCount) {
            throw new PlaceValueIllegalArgumentException(ERROR_CANNOT_RECEIVE_MORE_THAN_GIVEN_COUNT);
        } else if (placeValueString.length() < gameRulePlaceValueCount) {
            throw new PlaceValueIllegalArgumentException(ERROR_CANNOT_RECEIVE_LESS_THAN_GIVEN_COUNT);
        }
    }
}
