/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2013 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package cm.commons.service.impl;


import cm.commons.Page;
import cm.commons.bean.News;
import cm.commons.dao.NewsDao;
import cm.commons.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.UUID;

/**
 * 新闻Service实现类
 *
 * @author lzc
 */
@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Transactional
    public Long create(UUID creatorId, String title, String content, Integer category) {
        Calendar now = Calendar.getInstance();
        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setCategory(category);
        news.setPublishDate(now);
        news.setShowDate(now);
        news.setCreatedDate(now);
        news.setCreatedBy(creatorId);

        return (Long) newsDao.save(news);
    }


    @Transactional
    public Long create(News news) {
        news.setCreatedDate(Calendar.getInstance());
        return (Long) newsDao.save(news);
    }


    @Transactional
    public void approve(Long newsId, Long approverId, Boolean approved, String comment) {
        News news = newsDao.get(newsId);
        news.setApproverId(approverId);
        news.setApproved(approved);
        news.setApprovedComment(comment);
        news.setApprovedDate(Calendar.getInstance());
        newsDao.update(news);
    }


    @Transactional(readOnly = true)
    public Page<News> getPageList(int firstResult, int maxResults) {
/*    	List<News> news = newsDao.getOrderByCreationDate(null, null, null, null, true, firstResult, maxResults);
        long count = newsDao.getCount(null, null, null, null, true);*/
        return new Page<News>(
                newsDao.getOrderByCreationDate(null, null, null, null, true, firstResult, maxResults),
                newsDao.getCount(null, null, null, null, true), firstResult, maxResults);
    }


    @Transactional(readOnly = true)
    public Page<News> getPageList(String title, Calendar startDate, Calendar endDate, Integer category,
                                  Boolean approved, int firstResult, int maxResults) {
        return new Page<News>(newsDao.getOrderByCreationDate(title, startDate, endDate, category, approved,
                firstResult, maxResults), newsDao.getCount(title, startDate, endDate, category, approved), firstResult,
                maxResults);
    }

}
