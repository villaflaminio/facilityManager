package it.bruffa.facilitymanager.utilities;

import java.time.LocalDate;

public class DateUtility {

    private DateUtility() {
    }

    public static boolean isBetweenOrEqual(LocalDate toEvaluate, LocalDate start, LocalDate end) {
        return toEvaluate.isEqual(start) || toEvaluate.isEqual(end) || (toEvaluate.isAfter(start) && toEvaluate.isBefore(end));
    }
}
