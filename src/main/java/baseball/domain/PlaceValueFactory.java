package baseball.domain;

import baseball.exception.PlaceValueIndexOutOfException;
import camp.nextstep.edu.missionutils.Randoms;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;

import java.util.List;
import java.util.Set;

public class PlaceValueFactory {
    public static final String INDEX_OUT_OF_ERROR_MSG = "Place value must be lower than 10";

    public static List<PlaceValue> createPlaceValues(int count) {
        if (count > 9) throw new PlaceValueIndexOutOfException(INDEX_OUT_OF_ERROR_MSG);

        List<PlaceValue> result = Lists.newArrayList();
        Set<Integer> duplicateCheckSet = Sets.newHashSet();

        for (int i = 0; i < count; i ++){
            int pickedNumber = pickNumber(duplicateCheckSet);
            duplicateCheckSet.add(pickedNumber);
            PlaceValue pickedPlaceValue = PlaceValue.of(pickedNumber);
            result.add(pickedPlaceValue);
        }

        return result;
    }

    private static int pickNumber(Set<Integer> duplicateCheckSet) {
        boolean loopFlag;
        int pickNumber;

        do {
            pickNumber = Randoms.pickNumberInRange(1, 9);
            loopFlag = isDuplicateNumber(pickNumber, duplicateCheckSet);
        } while (loopFlag);

        return pickNumber;
    }

    private static boolean isDuplicateNumber(int pickNumber, Set<Integer> duplicateCheckSet) {
        return duplicateCheckSet.contains(pickNumber);
    }
}
