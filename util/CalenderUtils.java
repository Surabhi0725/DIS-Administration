package sgsits.cse.dis.administration.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * <h1><b>CalenderUtils</b> class.</h1>
 * <p>This class contains implementation of all the general purpose calendar related services.
 *
 * @author Arjit Mishra.
 * @version 1.0.
 * @throws ParseException
 * @see ParseException
 * @since 2 -DEC-2019.
 */
public class CalenderUtils {

  /**
   * Add days date.
   *
   * @param date the date
   * @param days the days
   * @return the date
   */
  public static Date addDays(Date date, int days) {
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(date);
    cal.add(Calendar.DATE, days);

    return cal.getTime();
  }

  /**
   * Subtract days date.
   *
   * @param date the date
   * @param days the days
   * @return the date
   */
  public static Date subtractDays(Date date, int days) {
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(date);
    cal.add(Calendar.DATE, -days);

    return cal.getTime();
  }

  /**
   * Date passed away boolean.
   *
   * @param date the date
   * @return the boolean
   */
  public static Boolean datePassedAway(Date date) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String currentDate = dateFormat.format(new Date());
    String formattedDate = dateFormat.format(date);
    return formattedDate.compareTo(currentDate) < 0;
  }

  /**
   * Gets days between dates.
   *
   * @param date1 the date 1
   * @param date2 the date 2
   * @return the days between dates
   * @throws ParseException the parse exception
   */
  public static long getDaysBetweenDates(Date date1, Date date2) throws ParseException {
    long diff = Math.abs(date1.getTime() - date2.getTime());
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
  }

}
