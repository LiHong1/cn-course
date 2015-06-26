//package test.cn.commons.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import cn.commons.service.MenuService;
//
//import cn.commons.PageList;
//import cn.commons.bean.MenuItem;
//
//public class MenuServiceTest {
//	private static ApplicationContext ctx;
//	private static MenuService menuService;
//	private static Long creatorId;
//
//	private static final Log logger = LogFactory.getLog(MenuServiceTest.class);
//
//	@BeforeClass
//	public static void setUp(){
//		ctx = new ClassPathXmlApplicationContext(new String[] { "commonsApplicationContextTest.xml" });
//		menuService = (MenuService) ctx.getBean("menuServiceImpl");
//		
//	}
//
//	// 测试添加Menu、通过ID拿到Menu、获得所有的Menu、更新Menu
//	@Test
//	public void testAdd() {
//		Long id = menuService.create(creatorId, "测试菜单");
//
//		MenuItem mi = menuService.get(id);
//		logger.debug("name == " + mi.getName());
//
//		List<MenuItem> list = menuService.getAll();
//
//		mi.setName("更新测试菜单");
//		menuService.update(mi);
//		logger.debug("name == " + mi.getName());
//
//		assertNotNull(id);
//		assertTrue(id > 0);
//		assertNotNull(mi);
//		assertNotNull(list);
//		assertTrue(list.size() > 0);
//	}
//
//	// 测试通过ID删除Menu
//	@Test
//	public void testDelete() {
//		Long id = menuService.create(creatorId, "测试菜单2");
//		menuService.delete(id);
//		List<MenuItem> list = menuService.getAll();
//
//		assertNotNull(list);
//		assertTrue(list.size() > 0);
//	}
//
//	// 测试删除Menu
//	@Test
//	public void testDelete1() {
//		MenuItem menuItem = new MenuItem();
//		menuItem.setName("aaaa");
//		logger.debug("name == " + menuItem.getName());
//		menuService.create(menuItem);
//
//		menuService.delete(menuItem);
//		List<MenuItem> list = menuService.getAll();
//		assertNotNull(list);
//		assertTrue(list.size() > 0);
//
//	}
//
//	@Test
//	public void testGetAll() {
//		List<MenuItem> list = menuService.getAll();
//		logger.debug("list====" + list.size());
//	}
//
//	//@Test
//	public void testCreate() {
//		// Long rootid = menuService.createRoot("中智通运营管理系统", -1L,
//		// MenuType.ADMIN);
//		// Long id1 = menuService.create("平台管理", "", MenuType.ADMIN, 500L,
//		// "/litleIco/books_close.gif", 5, "sys");
//		// Long id = menuService.create("新闻公告管理", "/membersystem/newslist.htm",
//		// MenuType.ADMIN, 10000031L, "/litleIco/books_close.gif", 5, "sys");
//		// Long id = menuService.create("操作员日志查询", "/adminloglist.htm",
//		// MenuType.ADMIN, 10000018L, "/litleIco/books_close.gif", 1, "sys");
//		// Long id2 = menuService.create("幻灯片管理", "/membersystem/slidelist.htm",
//		// MenuType.ADMIN, 10000031L, "/litleIco/books_close.gif", 6, "sys");
//	}
//
////	//@Test
////	public void getAllByType() {
////		List<MenuItem> list = menuService.getAllByType(MenuType.ADMIN);
////		logger.debug("list === " + list.size());
////	}
//	
//	//@Test
//	public void testSubMenu(){
//		Long parentId = menuService.create(creatorId, "父级菜单");
//
//		Long subId1 = menuService.create(creatorId, "子级菜单1");
//		//Long subId2 = menuService.create(creatorId, "子级菜单2");
//		//Long subId3 = menuService.create(creatorId, "子级菜单3");
//		//Long subId4 = menuService.create(creatorId, "子级菜单4");
//		
//		//menuService.
//		
//		MenuItem parentMenu = menuService.get(parentId);
//		MenuItem subMenu1 = menuService.get(subId1);
//		//MenuItem subMenu2 = menuService.get(subId2);
//		//MenuItem subMenu3 = menuService.get(subId3);
//		//MenuItem subMenu4 = menuService.get(subId4);		
//		
//		//parentMenu.getChildren().add(subMenu1);
//		//parentMenu.getChildren().add(subMenu2);
//		//subMenu1.getChildren().add(subMenu3);
//		//subMenu1.getChildren().add(subMenu4);
//		
//		menuService.update(parentMenu);
//	}
//	
//	@Test
//	public void testRootMenu() {
//		//List<MenuItem> menus = menuService.getRoot();
//		//logger.debug("Root菜单数目：" + menus.size());
//		
//		List<MenuItem> menus = menuService.getChildren(1000131L, 2);
//		logger.debug("Root菜单数目：" + menus.size());
//		
//		
////		MenuItem newmenu = new MenuItem();
////		newmenu.setType(MenuType.MEMBER);
////		newmenu.setName("测试菜单root");
////		menuService.create(newmenu);
////		
////		List<MenuItem> menus2 = menuService.getRoot(MenuType.MEMBER);
////		logger.debug("type Root菜单数目：" + menus2.size());
//	}
//	
//	
//	@Test
//	public void testPageList() {
//		PageList<MenuItem> menus = menuService.getChildren(null, 0, Integer.MAX_VALUE);
//		logger.debug("菜单项目总数：" + menus.getTotalCount());
//		
//		PageList<MenuItem> menus2 = menuService.getChildren(null, 0, 2);
//		logger.debug("菜单项目2总数：" + menus2.getTotalCount());
//		
//		assertEquals(menus.getList().size(), menus.getTotalCount());
//		assertTrue(menus2.getList().size() <= 2);
//		assertEquals(menus2.getTotalCount(), menus.getTotalCount());
//	}
//	
//	
////	@Test
////	public void testCacheMenu() throws ModelException{
////		Long parentId = menuService.create(creatorId, "父级菜单");
////		
////		logger.debug("abcdef=======================");
////		menuService.testcache(parentId);
////		menuService.testcache(parentId);
////		menuService.testcache(parentId);
////		
////		logger.debug("=======================");
////		menuService.get(parentId);
////		logger.debug("=======================");
////		menuService.get(parentId);
////		logger.debug("=======================");
////		menuService.get(parentId);
////		logger.debug("=======================");
////	}
//}
