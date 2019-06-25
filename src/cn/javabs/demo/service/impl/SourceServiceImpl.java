package cn.javabs.demo.service.impl;

import cn.javabs.demo.dao.SourceDao;
import cn.javabs.demo.dao.impl.SourceDaoImpl;
import cn.javabs.demo.entity.Source;
import cn.javabs.demo.service.SourceService;

import java.util.List;
import java.util.UUID;

public class SourceServiceImpl implements SourceService {

    SourceDao sourceDao = new SourceDaoImpl();
    /**
     * 添加资源
     *
     * @param source
     * @return
     */
    @Override
    public int addSource(Source source) {
        return sourceDao.saveSource(source);
    }


    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @Override
    public int delSource(int id) {
        return sourceDao.removeSource(id);
    }

    /**
     * 修改资源
     *
     * @param source
     * @return
     */
    @Override
    public int updateSource(Source source) {
        return sourceDao.editSource(source);
    }

    /**
     * 查询所有资源
     *
     * @return list
     */
    @Override
    public List<Source> findAllSources() {
        String stringId = UUID.randomUUID().toString();
        return sourceDao.getAllSources();
    }

    /**
     * 根据id查询资源
     *
     * @param id
     * @return
     */
    @Override
    public Source findSourceById(int id) {
        return sourceDao.getSourceById(id);
    }

    /**
     * 模糊查询
     *
     * @param sourcename
     * @return
     */
    @Override
    public List<Source>  findSourceByLikeName(String sourcename) {
        return sourceDao.getSourceByLikeName(sourcename);
    }

}
