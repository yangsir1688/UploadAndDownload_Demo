package cn.javabs.demo.dao.impl;

import cn.javabs.demo.dao.SourceDao;
import cn.javabs.demo.entity.Source;
import cn.javabs.demo.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class SourceDaoImpl implements SourceDao {
    QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public int saveSource(Source source) {
        try {
            int result = qr.update("insert into source(author,sourceName,sourcePath,downCount,createTime) values (?,?,?,?,?)",
                    source.getAuthor(),
                    source.getSourceName(),
                    source.getSourcePath(),
                    source.getDownCount(),
                    source.getCreateTime()
            );
            return result;
        } catch (SQLException e) {
            throw new  RuntimeException(e);
        }
    }

    @Override
    public int removeSource(int id) {
        try {
            return qr.update("delete from source" ,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int editSource(Source source) {
        try {
            return qr.update("update source set author = ? ,sourceName = ?,sourcePath = ?,downCount = ?,createTime = ? where id = ?" ,
                    source.getAuthor(),
                    source.getSourceName(),
                    source.getSourcePath(),
                    source.getDownCount(),
                    source.getCreateTime(),
                    source.getId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Source> getAllSources() {
        try {
            return qr.query("select * from source" ,new BeanListHandler<Source>(Source.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Source getSourceById(int id) {
        try {
            return qr.query("select * from source where id  = ?" ,new BeanHandler<Source>(Source.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Source> getSourceByLikeName(String sourcename) {
        return null;
    }
}
