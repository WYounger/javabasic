package datetimetest;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeTest {

    @Test
    public void lcoalDateTime() {
        /**
         * LocalDate localDate = LocalDate.now();
         * LocalDate localDate2 = LocalDate.of(2015, 12, 31);
         */
        LocalDate date = LocalDate.now();
        date.toString();//2020-02-16
        LocalDate date1 = LocalDate.of(2020, 1, 1);
        date1.toString();//2020-01-01

        /**
         * getYear()
         * getMonth()
         * getDayOfMonth()
         * getDayOfWeek()
         * getDayOfYear()
         */
        date.getYear(); //2020
        date.getMonth(); //FEBRUARY
        date.getDayOfMonth();//16
        date.getDayOfWeek();//SUNDAY
        date.getDayOfYear();//47


        /**
         * plusDays()
         * plusWeeks()
         * plusMonths()
         * plusYears()
         * minusDays()
         * minusWeeks()
         * minusMonths()
         * minusYears()
         */

        LocalDate localDate = date.plusYears(2);
        //date的值不变
        localDate.toString(); //2022-02-16
    }

    @Test
    public void localTime(){
        /**
         *  LocalTime localTime = LocalTime.now();
         *  LocalTime localTime2 = LocalTime.of(21, 30, 59, 11001);
         */
        LocalTime time = LocalTime.now();
        time.toString();//10:08:30.006339900

        LocalTime time1 = LocalTime.of(10,15,50,20);
        time1.toString();//10:15:50.000000020


        /**
         * getHour()
         * getMinute()
         * getSecond()
         * getNano()
         */
        time1.getHour();//10
        time1.getMinute();//15
        time1.getSecond();//50
        time1.getNano();//20

        /**
         * plusHours()
         * plusMinutes()
         * plusSeconds()
         * plusNanos()
         * minusHours()
         * minusMinutes()
         * minusSeconds()
         * minusNanos()
         * compareTo()
         */
        LocalTime localTime = time1.plusHours(2);
        //原始值不变
        localTime.toString();// 12:15:50.000000020

        time.compareTo(time1);//time > time1
    }

    @Test
    public void localDateTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime.toString();//2020-02-16T10:31:18.944879100
        LocalDateTime dateTime1 = LocalDateTime.of(2020,1,1,1,1,1,1);
        dateTime1.toString();//2020-01-01T01:01:01.000000001

        /**
         * getYear()
         * getMonth()
         * getDayOfMonth()
         * getDayOfWeek()
         * getDayOfYear()
         * getHour()
         * getMinute()
         * getSecond()
         * getNano()
         */

        /**
         * plusYears()
         * plusMonths()
         * plusDays()
         * plusHours()
         * plusMinutes()
         * plusSeconds()
         * plusNanos()
         * minusYears()
         * minusMonths()
         * minusDays()
         * minusHours()
         * minusMinutes()
         * minusSeconds()
         * minusNanos()
         * isBefore()
         * isAfter()
         */
        LocalDateTime dateTime2 = dateTime1.plusYears(2);
        dateTime2.toString();//2022-01-01T01:01:01.000000001

        dateTime.isBefore(dateTime1);//false
        dateTime2.isAfter(dateTime1); //true
    }

    @Test
    public void dateTimeFormatter(){
        //LocalDateTime -> String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime.format(formatter); //String: 2020-02-16 10:53:39

        // String -> LocalDateTime
        //标准格式
        LocalDateTime parse = LocalDateTime.parse("2020-01-01T01:01:01");
        parse.toString();//2020-01-01T01:01:01

        //当string不符合标准字符串格式，需要传递该string的格式
        LocalDateTime parse1 = LocalDateTime.parse("2020-01-01 01:01:01",formatter);
        parse1.toString();//2020-01-01T01:01:01

        //LocalDate相同
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        formatter1.format(date);//String: 2020/02/16
        //标准格式2020-01-01，非标准格式，需传递格式化器
        LocalDate parse2 = LocalDate.parse("2020/01/01", formatter1);
        parse2.toString(); // 2020-01-01
    }
}
