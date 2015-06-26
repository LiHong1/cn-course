//package cm.commons.dao;
//
//
//import java.util.List;
//import java.util.UUID;
//
////import cm.commons.util.QueryHelper;
//
//
///*
// * 公共dao接口
// */
//public interface DaoSupport<T> {
//    /**
//     * 保存实体
//     *
//     * @param entity
//     */
//    void save(T entity);
//
//    /**
//     * 更新实体
//     *
//     * @param entity
//     */
//    void update(T entity);
//
//    /**
//     * 删除实体
//     *
//     * @param id
//     */
//    void delete(UUID id);
//
//    /**
//     * 删除实体
//     *
//     * @param entity
//     */
//    void delete(T entity);
//
//    /**
//     * 查找实体
//     *
//     * @return
//     */
//    List<T> findAll();
//
//    /**
//     * 根据id号查实体
//     *
//     * @param id
//     */
//    T findById(UUID id);
//
//    /**
//     * 根据名字查实体
//     *
//     * @param name
//     * @return
//     */
////    T findByName(String name);
//
////    PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);
//}
