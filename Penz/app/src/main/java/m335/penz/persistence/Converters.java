package m335.penz.persistence;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.util.Date;

public class Converters {
    @TypeConverter
    public static LocalDate fromTimestamp(String value){
        return value == null ? null : LocalDate.parse(value);
    }

    @TypeConverter
    public static String dateToString(LocalDate date){
        return date == null ? null : date.toString();
    }
}
