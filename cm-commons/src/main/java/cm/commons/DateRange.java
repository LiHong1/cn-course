package cm.commons;

import javax.persistence.Embeddable;
import java.util.Date;

/**
 * 日期范围
 *
 * @author leizhenchun
 */
@Embeddable
public class DateRange {

    private Date start;
    private Date end;

    public DateRange() {
    }

    public DateRange(Date start, Date end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + "after " + end);
        }
        this.start = (Date) start.clone();
        this.end = (Date) end.clone();
    }

    public Date getStart() {
        return (Date) start.clone();
    }

    public void setStart(Date start) {
        this.start = (Date) start.clone();
    }

    public Date getEnd() {
        return (Date) end.clone();
    }

    public void setEnd(Date end) {
        this.end = (Date) end.clone();
    }

    public long getSpan() {
        return end.getTime() - start.getTime();
    }

    //
    public boolean includes(Date date) {
        return ((date.compareTo(start) >= 0) && (date.compareTo(end) <= 0));
    }

    //
    public boolean isValid() {
        return (end.compareTo(start) >= 0);
    }

    public int compare() {
        return end.compareTo(start);
    }
}
