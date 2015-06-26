package cm.security;

import cm.dao.ManageDao;
import cm.dao.StudentDao;
import cm.dao.TeacherDao;
import cm.entity.Manage;
import cm.entity.Person;
import cm.entity.Student;
import cm.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by li hong on 2015/5/20.
 */
@Service
public class CmUserDetailService implements UserDetailsService {
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private ManageDao manageDao;
	protected static Logger logger = Logger.getLogger("service");
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		UserDetails user = null;
		try {

			// 搜索数据库以匹配用户登录名.
			// 我们可以通过dao使用JDBC来访问数据库
			Person person = studentDao.findByNumber(s);
			if(person == null)
				person = teacherDao.findByNumber(s);
			if (person == null)
				person = manageDao.findByNumber(s);
			user = new User(person.getNumber(), person.getPassword()
					, true, true, true, true,
					getAuthorities(person));
		} catch (Exception e) {
			logger.info("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		return user;
	}
	/**
	 * 获得访问角色权限
	 *
	 * @return
	 */
	public Collection<GrantedAuthority> getAuthorities(Person p) {

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		// 所有的用户默认拥有ROLE_USER权限
		logger.info("Grant ROLE_USER to this user");
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));

		// 如果参数access为1.则拥有ROLE_ADMIN权限
		if (p instanceof  Student) {
			logger.info("Grant ROLE_STUDENT to this user");
			authList.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
		}
		if (p instanceof Teacher) {
			logger.info("Grant ROLE_TEACHER to this user");
			authList.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
		}
		if (p instanceof Manage) {
			logger.info("Grant ROLE_MANAGE to this user");
			authList.add(new SimpleGrantedAuthority("ROLE_MANAGE"));
		}

		return authList;
	}
}
