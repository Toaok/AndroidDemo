package com.toaok.study.model.local.db.dao;/** * @author Toaok * @version 1.0  2018/9/10. */public interface BaseDao<T> {    // 增    long insert(T t);    // 删    long delete(T t);    // 改    long update(T t);    // 查    T query(int id);    //    void close();}