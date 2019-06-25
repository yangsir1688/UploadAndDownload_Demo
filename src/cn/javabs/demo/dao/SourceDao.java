package cn.javabs.demo.dao;

import cn.javabs.demo.entity.Source;

import java.util.List;

public interface SourceDao {
    int saveSource(Source source);

    int removeSource(int id);

    int editSource(Source source);

    List<Source> getAllSources();

    Source getSourceById(int id);

    List<Source>  getSourceByLikeName(String sourcename);
}
