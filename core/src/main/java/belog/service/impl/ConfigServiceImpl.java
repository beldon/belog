package belog.service.impl;


import belog.dao.OptionsMapper;
import belog.pojo.po.Options;
import belog.pojo.vo.ConfigVo;
import belog.service.ConfigService;
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
    private OptionsMapper optionsMapper;

    @CacheEvict(value = "config", allEntries = true)
    public void saveOrUpdate(ConfigVo configVo) {
        Options options = new Options();
        BeanUtils.copyProperties(configVo, options);
        options.setType(CONFIG);
        if (configVo.getId() == null || configVo.getId() == 0) {//添加
            optionsMapper.insertSelective(options);
        } else {//更新
            optionsMapper.updateByPrimaryKeySelective(options);
        }
    }

    @CacheEvict(value = "config", allEntries = true)
    public void saveOrUpdate(Collection<ConfigVo> configVos) {
        for (ConfigVo configVo : configVos) {
            saveOrUpdate(configVo);
        }
    }

    @CacheEvict(value = "config", allEntries = true)
    public void delete(ConfigVo configVo) {
        optionsMapper.deleteByPrimaryKey(configVo.getId());
    }

    @CacheEvict(value = "config", allEntries = true)
    public void delete(long id) {
        optionsMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(value = "config", key = "'config_' + #id")
    public ConfigVo findById(long id) {
        ConfigVo configVo = new ConfigVo();
        Options options = optionsMapper.selectByPrimaryKey(id);
        if (options != null) {
            BeanUtils.copyProperties(options, configVo);
        }
        return configVo;
    }

    @Cacheable(value = "config", key = "'config_' + #name")
    public ConfigVo findByName(String name) {
        ConfigVo configVo = new ConfigVo();
        Options options = optionsMapper.selectByName(name);
        if (options != null) {
            BeanUtils.copyProperties(options, configVo);
        }
        return configVo;
    }

    @Cacheable(value = "config")
    public List<ConfigVo> findAll() {
        List<ConfigVo> configVoList = new ArrayList<ConfigVo>();
        List<Options> list = optionsMapper.selectByType(CONFIG);
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
        } else {
            options.setType("config");
        }
    }

}
