package cm.commons.dao;

import cm.commons.bean.News;

import java.util.Calendar;
import java.util.List;

/**
 * 新闻Dao接口
 *
 * @author lzc
 */
public interface NewsDao extends NonDeletableBaseDao<News> {

    /**
     * 根据标题、起始时间、分类、是否审核，获取新闻总数
     *
     * @param title
     * @param startDate
     * @param endDate
     * @param category
     * @param approved
     * @return
     */
    Long getCount(String title, Calendar startDate, Calendar endDate, Integer category, Boolean approved);

    /**
     * 根据标题、起始时间、分类、是否审核，获取新闻列表（分页）
     *
     * @param title
     * @param startDate
     * @param endDate
     * @param category
     * @param approved
     * @param firstResult
     * @param maxResults
     * @return
     */
    List<News> get(String title, Calendar startDate, Calendar endDate, Integer category, Boolean approved,
                   int firstResult, int maxResults);

    /**
     * 根据标题、起始时间、分类、是否审核，获取新闻列表（分页），安装创建时间排序
     *
     * @param title
     * @param startDate
     * @param endDate
     * @param category
     * @param approved
     * @param firstResult
     * @param maxResults
     * @return
     */
    List<News> getOrderByCreationDate(String title, Calendar startDate, Calendar endDate, Integer category,
                                      Boolean approved, int firstResult, int maxResults);
}
