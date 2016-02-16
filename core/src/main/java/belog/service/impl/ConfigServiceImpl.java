package belog.service.impl;


import belog.dao.OptionsDao;
import belog.pojo.po.Options;
import belog.pojo.vo.ConfigVo;
import belog.service.ConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Beldon
 */
@Service("ConfigService")
public class ConfigServiceImpl extends BaseService implements ConfigService {

    @Autowired
    private OptionsDao optionsDao;

    @CacheEvict(value = "config", allEntries = true)
    public void saveOrUpdate(ConfigVo configVo) {
        saveOrUp(configVo);
    }

    @CacheEvict(value = "config", allEntries = true)
    public void saveOrUpdate(Collection<ConfigVo> configVos) {
        for (ConfigVo configVo : configVos) {
            saveOrUp(configVo);
        }
    }

    @CacheEvict(value = "config", allEntries = true)
    public void delete(ConfigVo configVo) {
        optionsDao.delete(configVo.getId());
    }

    @CacheEvict(value = "config", allEntries = true)
    public void delete(long id) {
        optionsDao.delete(id);
    }

    @Cacheable(value = "config", key = "'config_' + #id")
    public ConfigVo findById(long id) {
        ConfigVo configVo = new ConfigVo();
        Options options = optionsDao.findById(id);
        if (options != null) {
            BeanUtils.copyProperties(options, configVo);
        }
        return configVo;
    }

    @Cacheable(value = "config", key = "'config_' + #name")
    public ConfigVo findByName(String name) {
        ConfigVo configVo = new ConfigVo();
        Options options = optionsDao.findByName(name);
        if (options != null) {
            BeanUtils.copyProperties(options, configVo);
        }
        return configVo;
    }

    @Cacheable(value = "config")
    public List<ConfigVo> findAll() {
        List<ConfigVo> configVoList = new ArrayList<ConfigVo>();
        Options options = new Options();
        options.setType("config");
        List<Options> list = optionsDao.findByExample(options);
        for (Options o : list) {
            ConfigVo configVo = new ConfigVo();
            BeanUtils.copyProperties(o, configVo);
            configVoList.add(configVo);
        }
        return configVoList;
    }

    @Cacheable(value = "config")
    public Map<String, String> getConfigs() {
        Map<String, String> config = new HashMap<String, String>();
        List<ConfigVo> configVoList = findAll();
        for (ConfigVo configVo : configVoList) {
            config.put(configVo.getName(), configVo.getValue());
        }
        return config;
    }

    /**
     * 复制属性
     *
     * @param configVo
     * @param options
     */
    private void copyProperties(ConfigVo configVo, Options options) {
        BeanUtils.copyProperties(configVo, options);
        if (org.springframework.util.StringUtils.hasText(options.getType())) {
            options.setType(options.getType());
        }else{
            options.setType("config");
        }
    }

    private void saveOrUp(ConfigVo configVo) {
        Options options = optionsDao.findByName(configVo.getName());
        if (options != null) {
            options.setValue(configVo.getValue());
            if (!StringUtils.isEmpty(configVo.getAutoLoad())) {
                options.setAutoLoad(configVo.getAutoLoad());
            }
            optionsDao.updateEntity(options);
        } else {
            options = new Options();
            copyProperties(configVo, options);
            if (!StringUtils.isEmpty(configVo.getAutoLoad())) {
                options.setAutoLoad(configVo.getAutoLoad());
            }else{
                options.setAutoLoad("yes");
            }
            optionsDao.saveEntity(options);
        }

    }
}
