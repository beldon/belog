package belog.service;


import belog.pojo.vo.ConfigVo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Beldon
 */
public interface ConfigService {

    String CONFIG = "config";


    void saveOrUpdate(ConfigVo configVo);

    void saveOrUpdate(Collection<ConfigVo> configVos);

    void delete(ConfigVo configVo);

    void delete(long id);

    ConfigVo findById(long id);

    ConfigVo findByName(String name);

    List<ConfigVo> findAll();

    Map<String ,String> getConfigs();
}
