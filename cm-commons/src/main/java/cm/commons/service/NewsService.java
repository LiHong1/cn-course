package cm.commons.service;

import cm.commons.Page;
import cm.commons.bean.News;

import java.util.Calendar;
import java.util.UUID;

/**
 * 新闻服务接口
 *
 * @author leizhenchun
 */
public interface NewsService extends BaseService<News> {
    /**
     * 新增新闻
     *
     * @param creatorId
     * @param title
     * @param content
     * @param category
     * @return
     */
    Long create(UUID creatorId, String title, String content, Integer category);

    /**
     * 审批新闻
     *
     * @param newsId
     * @param approved
     * @param comment
     * @return
     */
    void approve(Long newsId, Long approverId, Boolean approved, String comment);

    /**
     * 根据条件查询新闻列表
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
    Page<News> getPageList(String title, Calendar startDate, Calendar endDate, Integer category, Boolean approved,
                           int firstResult, int maxResults);
}
