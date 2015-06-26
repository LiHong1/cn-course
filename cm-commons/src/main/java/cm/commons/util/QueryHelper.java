//package cm.commons.util;
//
//import cm.commons.bean.PageBean;
//import cm.commons.dao.DaoSupport;
//import com.opensymphony.xwork2.ActionContext;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 用于辅助拼接HQL语句
// *
// * @author tyg
// */
//public class QueryHelper {
//
//    private String fromClause; // FROM子句
//    private String whereClause = ""; // Where子句
//    private String orderByClause = ""; // OrderBy子句
//
//    private List<Object> parameters = new ArrayList<Object>(); // 参数列表
//
//    /**
//     * 生成From子句
//     *
//     * @param clazz
//     * @param alias 别名
//     */
//    public QueryHelper(Class<?> clazz, String alias) {
//        fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
//    }
//
//    /**
//     * 拼接Where子句
//     *
//     * @param condition
//     * @param params
//     */
//    public QueryHelper addCondition(String condition, Object... params) {
//        // 拼接
//        if (whereClause.length() == 0) {
//            whereClause = " WHERE " + condition;
//        } else {
//            whereClause += " AND " + condition;
//        }
//
//        // 参数
//        if (params != null) {
//            for (Object p : params) {
//                parameters.add(p);
//            }
//        }
//
//        return this;
//    }
//
//    /**
//     * 如果第一个参数为true，则拼接Where子句
//     *
//     * @param append
//     * @param condition
//     * @param params
//     */
//    public QueryHelper addCondition(boolean append, String condition, Object... params) {
//        if (append) {
//            addCondition(condition, params);
//        }
//        return this;
//    }
//
//    /**
//     * 拼接OrderBy子句
//     *
//     * @param propertyName 参与排序的属性名
//     * @param asc          true表示升序，false表示降序
//     */
//    public QueryHelper addOrderProperty(String propertyName, boolean asc) {
//        if (orderByClause.length() == 0) {
//            orderByClause = " ORDER BY " + propertyName + (asc ? " ASC" : " DESC");
//        } else {
//            orderByClause += ", " + propertyName + (asc ? " ASC" : " DESC");
//        }
//        return this;
//    }
//
//    /**
//     * 如果第一个参数为true，则拼接OrderBy子句
//     *
//     * @param append
//     * @param propertyName
//     * @param asc
//     */
//    public QueryHelper addOrderProperty(boolean append, String propertyName, boolean asc) {
//        if (append) {
//            addOrderProperty(propertyName, asc);
//        }
//        return this;
//    }
//
//    /**
//     * 获取生成的用于查询数据列表的HQL语句
//     *
//     * @return
//     */
//    public String getListQueryHql() {
//        return fromClause + whereClause + orderByClause;
//    }
//
//    /**
//     * 获取生成的用于查询总记录数的HQL语句
//     *
//     * @return
//     */
//    public String getCountQueryHql() {
//        return "SELECT COUNT(*) " + fromClause + whereClause;
//    }
//
//    /**
//     * 获取HQL中的参数值列表
//     *
//     * @return
//     */
//    public List<Object> getParameters() {
//        return parameters;
//    }
//
//    /**
//     * 查询分页信息，并放到值栈栈顶
//     *
//     * @param service
//     * @param pageNum
//     * @param pageSize
//     */
//    public void preparePageBean(DaoSupport<?> dao, int pageNum, int pageSize) {
//        PageBean pageBean = dao.getPageBean(pageNum, pageSize, this);
//        ActionContext.getContext().getValueStack().push(pageBean);
//    }
//
//}
