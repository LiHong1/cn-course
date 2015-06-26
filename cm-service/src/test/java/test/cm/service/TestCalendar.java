package test.cm.service;

import org.junit.Test;

import java.util.Calendar;

/**
 * Created by li hong on 2015/4/26.
 */
public class TestCalendar {
    @Test
    public void TestRoll() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        calendar.add(Calendar.MILLISECOND, 1000);
        System.out.println(calendar.getTime());
    }
}
