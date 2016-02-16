package belog.dao.impl;


import belog.dao.OptionsDao;
import belog.dao.common.impl.CommonDaoImpl;
import belog.pojo.po.Options;
import org.springframework.stereotype.Repository;

/**
 * @author Beldon
 */
@Repository("OptionsDao")
public class OptionsDaoImpl extends CommonDaoImpl<Options> implements OptionsDao {
    public Options findByName(String name) {
        Options options = new Options();
        options.setName(name);
        return this.findOneByExample(options);
    }
}
