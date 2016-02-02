package belog.service.impl;

import belog.dao.TermTaxonomyDao;
import belog.pojo.po.TermTaxonomy;
import belog.service.TermTaxonomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Beldon
 */
@Service
public class TermTaxonomyServiceImpl implements TermTaxonomyService {

    @Autowired
    private TermTaxonomyDao termTaxonomyDao;

    public void countPlus(long catId, long sum) {
        TermTaxonomy taxonomy = termTaxonomyDao.findById(catId);
        taxonomy.setCount(taxonomy.getCount() + sum);
        termTaxonomyDao.updateEntity(taxonomy);
    }

    public void countMinus(long catId, long sum) {
        TermTaxonomy taxonomy = termTaxonomyDao.findById(catId);
        long count = taxonomy.getCount();
        if (count >= sum) {
            taxonomy.setCount(taxonomy.getCount() - sum);
        }
        termTaxonomyDao.updateEntity(taxonomy);
    }
}
