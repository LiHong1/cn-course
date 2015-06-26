import cm.entity.StAttendance;
import cm.enu.AttendanceType;
import org.junit.Test;

/**
 * Created by li hong on 2015/6/12.
 */
public class EnumTest {

    @Test
	public void test(){
		System.out.println(AttendanceType.LEAVE.ordinal());
	}

}
