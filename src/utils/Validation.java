package utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validation {


    public class DateValidator {
        private static final String DATE_FORMAT = "yyyy-MM-dd";

        public static boolean isValidDate(String date) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.setLenient(false);

            try {
                sdf.parse(date);
                return true;
            } catch (ParseException e) {
                return false;
            }
        }
    }

}
