package cn.javabs.demo.service;

import cn.javabs.demo.entity.Source;

import java.util.List;

public interface SourceService {

    /**
     * 添加资源
     * @param source
     * @return
     */
    int addSource(Source source);

    /**
     * 删除资源
     * @param id
     * @return
     */
    int delSource(int id);

    /**
     * 修改资源
     * @param source
     * @return
     */
    int updateSource(Source source);

    /**
     * 查询所有资源
     * @return list
     */
    List<Source> findAllSources();

    /**
     * 根据id查询资源
     * @param id
     * @return
     */
    Source findSourceById(int id);

    /**
     * 模糊查询
     * @param sourcename
     * @return
     */
    List<Source>  findSourceByLikeName(String sourcename);


}
