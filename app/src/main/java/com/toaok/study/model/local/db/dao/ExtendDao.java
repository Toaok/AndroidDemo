package com.toaok.study.model.local.db.dao;

import java.util.List;

/**
 * @author Toaok
 * @version 1.0  2018/9/10.
 */
public interface ExtendDao<T> extends BaseDao<T> {
    //查询所有
    List<T> queryAll();

    //插入多条数据
    void save(List<T> list);

}
