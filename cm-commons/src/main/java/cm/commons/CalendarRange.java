package cm.commons;

import javax.persistence.Embeddable;
import java.util.Calendar;

/**
 * 日期范围
 *
 * @author leizhenchun
 */
@Embeddable
public class CalendarRange {
    //开始日期
    private Calendar start;
    //结束日期
    private Calendar end;

    /**
     * 构造函数
     */
    public CalendarRange() {
        start = Calendar.getInstance();
        end = (Calendar) start.clone();
    }

    /**
     * 构造函数
     *
     * @param start
     * @param end
     */
    public CalendarRange(Calendar start, Calendar end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + "after " + end);
        }
        this.start = start;
        this.end = end;
    }

    /**
     * 构造函数
     *
     * @param start
     * @param spanInMinute
     */
    public CalendarRange(Calendar start, int spanInMinute) {
        this.start = start;
        this.end = (Calendar) start.clone();
        this.end.add(Calendar.MINUTE, spanInMinute);
    }

    /**
     * 构造函数
     *
     * @param start
     * @param field
     * @param span
     */
    public CalendarRange(Calendar start, int field, int span) {
        this.start = start;
        this.end = (Calendar) start.clone();
        this.end.add(field, span);
    }

    /**
     * 获取开始日期
     *
     * @return
     */
    public Calendar getStart() {
        return start;
    }

    /**
     * 设置开始日期
     *
     * @param start
     */
    public void setStart(Calendar start) {
        this.start = start;
    }

    /**
     * 获取结束日期
     *
     * @return
     */
    public Calendar getEnd() {
        return end;
    }

    /**
     * 设置结束日期
     *
     * @param end
     */
    public void setEnd(Calendar end) {
        this.end = end;
    }

    /**
     * 时间间隔
     *
     * @return 间隔，毫秒
     */
    public long spanInMillis() {
        return this.end.getTimeInMillis() - this.start.getTimeInMillis();
    }

    /**
     * 日期时间是否在范围之内
     *
     * @param date
     * @return
     */
    public boolean include(Calendar date) {
        return ((date.compareTo(start) >= 0) && (date.compareTo(end) <= 0));
    }

    /**
     * 日期是否在范围之内，不考虑时间部分
     *
     * @param date
     * @return
     */
    public boolean includeDate(Calendar date) {
        return (date.get(Calendar.YEAR) >= start.get(Calendar.YEAR) &&
                date.get(Calendar.YEAR) <= end.get(Calendar.YEAR) &&
                date.get(Calendar.DAY_OF_YEAR) >= start.get(Calendar.DAY_OF_YEAR) &&
                date.get(Calendar.DAY_OF_YEAR) <= end.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * 是否有效，结束时间大于开始时间
     *
     * @return
     */
    public boolean isValid() {
        return (end.compareTo(start) >= 0);
    }

    /**
     * 比较
     *
     * @return
     */
    public int compare() {
        return end.compareTo(start);
    }

    /**
     * 日期间隔转换成字符串
     */
    @Override
    public String toString() {
        return "CalendarRange:" + start.getTime().toString() + ":" + end.getTime().toString();
    }
}
