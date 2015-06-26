package cm.commons;

import java.util.List;

/**
 * 页面对象显示列表，包括总数
 *
 * @param <T>
 * @author leizhenchun
 */
public class Page<T> {
    // 当前页记录
    private List<T> list;
    // 记录开始位置
    private int firstResult;
    // 最大记录数目
    private int maxResults;
    // 所有记录数目
    private long totalCount;
    //当前页
    private int currentPage;

    public Page(List<T> list, long totalCount, int firstResult, int maxResults) {
        this.list = list;
        this.totalCount = totalCount;
        this.firstResult = firstResult;
        this.maxResults = maxResults;
    }

    /**
     * 转换firstResult值，在正常范围之内
     *
     * @param firstResult
     * @param totalCount
     * @param maxResults
     * @return
     */
    public static int getFirstResult(int firstResult, long totalCount, int maxResults) {
        long maxPageNo = (totalCount % maxResults == 0) ? (totalCount / maxResults) : (totalCount / maxResults + 1);
        long maxPageFirstResult = maxPageNo > 0 ? (maxPageNo - 1) * maxResults : 0;
        return (int) ((firstResult > maxPageFirstResult) ? maxPageFirstResult : firstResult);
    }

    /**
     * 总页数
     *
     * @return
     */
    public long getTotalPageCount() {
        if (totalCount % maxResults == 0) {
            return totalCount / maxResults;
        } else {
            return totalCount / maxResults + 1;
        }
    }

    /**
     * 当前页
     *
     * @return
     */
    public long getCurrentPageNum() {
        return firstResult / maxResults + 1;
    }

    /**
     * 是否有下一页
     *
     * @return
     */
    public boolean hasNextPage() {
        return this.getCurrentPageNum() < this.getTotalPageCount();
    }

    /**
     * 是否有前一页
     *
     * @return
     */
    public boolean hasPreviousPage() {
        return this.getCurrentPageNum() > 1;
    }

    /**
     * 总记录数
     *
     * @return
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * 对象列表
     *
     * @return
     */
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 起始记录位置
     *
     * @return
     */
    public int getFirstResult() {
        return firstResult;
    }

    /**
     * 最大页面记录数目（每页面记录数）
     *
     * @return
     */
    public int getMaxResults() {
        return maxResults;
    }
}
