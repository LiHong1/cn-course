//package test.cn.commons.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import java.util.Calendar;
//import java.util.List;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import cn.commons.service.NewsService;
//
//import cn.commons.PageList;
//import cn.commons.bean.News;
//
//public class NewsServiceTest {
//    private static ApplicationContext ctx;
//    private static NewsService newsService;
//    private static Long userId;
//
//    private static final Log logger = LogFactory.getLog(NewsServiceTest.class);
//
//    @BeforeClass
//    public static void setUp() {
//        ctx = new ClassPathXmlApplicationContext(new String[] { "commonsApplicationContextTest.xml" });
//        newsService = (NewsService) ctx.getBean("newsServiceImpl");
//        userId = 0L;
//    }
//
//    @Test
//    public void testNews(){
//        List<News> oldNewsList = newsService.getPageList(0, Integer.MAX_VALUE).getList();
//        long oldNewsCount = oldNewsList.size();// newsService.getAllCount();
//        logger.debug("新增之前新闻总数：" + oldNewsCount);
//
//        Long newsId = newsService.create(userId, "测试新闻标题", "测试新闻内容", 1);
//        logger.debug("新增新闻Id：" + newsId);
//
//        News news = newsService.get(newsId);
//        logger.debug(news.getTitle());
//
//        news.setTitle("更新新闻标题");
//        newsService.update(news);
//        newsService.approve(newsId, 1L, true, "测试审核意见");
//        logger.debug(news.getTitle());
//
//        List<News> afterNewsList = newsService.getPageList(0, Integer.MAX_VALUE).getList();
//        long afterNewsCount = afterNewsList.size();// newsService.getAllCount();
//        logger.debug("新增之后新闻总数：" + afterNewsCount);
//
//        newsService.delete(newsId);
//
//        List<News> afterDelNewsList = newsService.getPageList(0, Integer.MAX_VALUE).getList();
//        long afterDelNewsCount = afterDelNewsList.size();// newsService.getAllCount();
//        logger.debug("删除新增之后新闻总数：" + afterDelNewsCount);
//
//        assertNotNull(oldNewsList);
//        assertEquals(oldNewsList.size(), oldNewsCount);
//        assertNotNull(afterNewsList);
//        assertEquals(afterNewsList.size(), afterNewsCount);
//        assertNotNull(afterDelNewsList);
//        assertEquals(afterDelNewsList.size(), afterDelNewsCount);
//        assertEquals(oldNewsCount + 1, afterNewsCount);
//        assertEquals(afterNewsCount - 1, afterDelNewsCount);
//    }
//
//    @Test
//    public void testGetList() {
//        Calendar from = Calendar.getInstance();
//        Calendar to = Calendar.getInstance();
//        from.set(Calendar.YEAR, 2000);
//        String newsTitle = "新闻标题：" + RandomStringUtils.randomAlphabetic(20);
//        String newsContent = "新闻内容：" + RandomStringUtils.randomAlphabetic(20);
//        PageList<News> oldNewsPageList = newsService.getPageList(newsTitle, from, to, null, null, 0, Integer.MAX_VALUE);
//        List<News> oldNewsList = oldNewsPageList.getList();
//        long oldNewsCount = oldNewsList.size();// newsService.getCount(newsTitle,
//                                               // from, to, null, null);
//        logger.debug("新增之前新闻总数：" + oldNewsList.size());
//
//        Long newsId = newsService.create(userId, newsTitle, newsContent, 1);
//        logger.debug("新增新闻Id：" + newsId);
//
//        List<News> afterNewsList = newsService.getPageList(newsTitle.substring(10), from, Calendar.getInstance(), null,
//                null, 0, Integer.MAX_VALUE).getList();
//        long afterNewsCount = afterNewsList.size();// newsService.getCount(newsTitle.substring(10),
//                                                   // from,
//                                                   // Calendar.getInstance(),
//                                                   // null, null);
//        logger.debug("新增之后新闻总数：" + afterNewsList.size());
//
//        assertNotNull(oldNewsList);
//        assertEquals(oldNewsList.size(), oldNewsCount);
//        assertNotNull(afterNewsList);
//        assertEquals(afterNewsList.size(), afterNewsCount);
//    }
//
//    // //测试最近新闻记
//    // //@Test
//    // public void testGetRecentNews(){
//    //
//    // // newsService.tt();
//    // //List<News> list = newsService.getRecentNews();
//    //
//    // // assertNotNull(list);
//    // // assertTrue(list.size() > 0);
//    // }
//
//    // @Test
//    // public void testCreate() {
//    // // Long id = newsService.create("春哥来了", "春哥来了", "公告", 1,
//    // Calendar.getInstance(), Calendar.getInstance(), 1000008L);
//    // /// Long result = newsService.approved(id, "纯纯", "纯纯", "公告", 1, 10008L,
//    // Boolean.TRUE, "提交");
//    // List<News> list = newsService.get(null, null, null, null, Boolean.TRUE,
//    // 0, 10);
//    // logger.debug("result ====== "+ list.size());
//    // }
//    //
//    // @Test
//    // public void testDelete(){
//    // newsService.delete(10130L);
//    // }
//    //
//    // @Test
//    // public void testDeleteandget(){
//    // Long counts1 = newsService.getCount(null, null, null, null, null);
//    // logger.debug("counts1 ====== "+counts1);
//    // // Long id = newsService.create(RandomStringUtils.randomAlphabetic(5),
//    // RandomStringUtils.randomAlphabetic(5), "公告", 1, Calendar.getInstance(),
//    // Calendar.getInstance(), 1000008L);
//    // // Long counts2 = newsService.getCounts(null, null, null, null, null);
//    // // System.out.println("counts2 ====== "+ counts2);
//    // // newsService.delete(id);
//    // // Long counts3 = newsService.getCounts(null, null, null, null, null);
//    // // System.out.println("counts3 == == = = == = "+ counts3);
//    //
//    //
//    // }
//    //
//    // @Test
//    // public void create(){
//    // Long result = newsService.create(RandomStringUtils.randomAlphabetic(5),
//    // RandomStringUtils.randomAlphabetic(5),
//    // RandomStringUtils.randomAlphabetic(5), userId);
//    // logger.debug("result == = == == = = "+ result);
//    // }
//    //
//
//    @Test
//    public void testGetPageList() {
//        PageList<News> newsPageList = newsService.getPageList(0, Integer.MAX_VALUE);
//        logger.debug("新闻总数：" + newsPageList.getList().size());
//
//        assertEquals(newsPageList.getTotalCount(), newsPageList.getList().size());
//    }
//
//    @Test
//    public void testEhCache() {
//        Long newsId = newsService.create(0L, "testEhCache", "testEhCache-content", 0);
//        Long newsId2 = newsService.create(0L, "testEhCache", "testEhCache-content", 0);
//        
//        logger.debug("---------------1----------");
//        newsService.get(newsId);
//        logger.debug("---------------2----------");
//        newsService.get(newsId);
//        logger.debug("---------------3----------");
//        newsService.get(newsId);
//        logger.debug("---------------4----------");
//        newsService.get(newsId);
//        logger.debug("---------------5----------");
//        
//        newsService.delete(newsId);
//    }
//
//    @Test
//    public void testgetNews() {
//        List<News> list = newsService.getPageList(null, null, null, null,
//                null, 0, Integer.MAX_VALUE).getList();
//        System.out.println("********"+list.size());
//
//    }
//}
