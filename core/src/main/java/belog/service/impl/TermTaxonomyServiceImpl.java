package belog.service.impl;

import belog.dao.TermRelationshipsDao;
import belog.dao.TermTaxonomyDao;
import belog.dao.TermsDao;
import belog.pojo.po.TermRelationships;
import belog.pojo.po.TermTaxonomy;
import belog.pojo.po.Terms;
import belog.service.TermTaxonomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Beldon
 */
@Service("TermTaxonomyService")
public class TermTaxonomyServiceImpl extends BaseService implements TermTaxonomyService {

    @Autowired
    private TermTaxonomyDao termTaxonomyDao;

    @Autowired
    private TermsDao termsDao;

    @Autowired
    private TermRelationshipsDao termRelationshipsDao;

    public void countPlus(long catId, long sum) {
        TermTaxonomy taxonomy = termTaxonomyDao.findById(catId);
        long count = taxonomy.getCount() == null ? sum : taxonomy.getCount() + sum;
        taxonomy.setCount(count);
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

    public void deleteById(long id) {
        TermTaxonomy termTaxonomy = termTaxonomyDao.findById(id);
        Terms terms = termTaxonomy.getTerms();
        Set<TermRelationships> relationshipses = termTaxonomy.getTermRelationships();
        if (relationshipses != null && relationshipses.size() > 0) {
            termRelationshipsDao.deleteBatch(relationshipses);
        }

        termTaxonomyDao.deleteEntity(termTaxonomy);
        termsDao.deleteEntity(terms);
    }
}
