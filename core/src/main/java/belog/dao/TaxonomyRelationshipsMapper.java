package belog.dao;

import belog.pojo.po.TaxonomyRelationships;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxonomyRelationshipsMapper {
    @Delete({
        "delete from t_taxonomy_relationships",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_taxonomy_relationships (term_order, type, ",
        "object_id, taxonomy_id)",
        "values (#{termOrder,jdbcType=INTEGER}, #{type,jdbcType=VARCHAR}, ",
        "#{objectId,jdbcType=BIGINT}, #{taxonomyId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TaxonomyRelationships record);

    int insertSelective(TaxonomyRelationships record);

    @Select({
        "select",
        "id, term_order, type, object_id, taxonomy_id",
        "from t_taxonomy_relationships",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    TaxonomyRelationships selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaxonomyRelationships record);

    @Update({
        "update t_taxonomy_relationships",
        "set term_order = #{termOrder,jdbcType=INTEGER},",
          "type = #{type,jdbcType=VARCHAR},",
          "object_id = #{objectId,jdbcType=BIGINT},",
          "taxonomy_id = #{taxonomyId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TaxonomyRelationships record);

    @Delete({
            "delete from t_taxonomy_relationships",
            "where taxonomy_id = #{taxonomyId,jdbcType=BIGINT}"
    })
    int deleteByTaxonomyId(Long taxonomyId);

    @Delete({
            "delete from t_taxonomy_relationships",
            "where object_id = #{objectId} and type = #{type}"
    })
    int deleteByTypeAndObjectId(@Param("type") String type, @Param("objectId")Long objectId);
}