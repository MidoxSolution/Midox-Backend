package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.GroupEntity;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GroupEntityJDBC {

    @Autowired
    JdbcTemplate jdbcTemplate;

    String findAllForGroupMasterCdQuery = "select child.entity_id as entityId, child.entity_cd as entityCd, child.parent_entity_cd as parentEntityCd, child.master_cd as masterCd, child.display_value as displayValue, parent.display_value as parentDisplayValue, child.created_at as createdAt, child.updated_at as updatedAt, child.created_by as createdBy, child.updated_by as updatedBy " +
            " from group_entity child left outer join group_entity parent on child.parent_entity_cd = parent.entity_cd where child.master_cd = ?";



    //@Query(value = findAllForGroupMasterCdQuery, nativeQuery = true)
    public List<GroupEntity> findAllForGroupMasterCd(String masterCd){

       /* List result = jdbcTemplate.query(findAllForGroupMasterCdQuery, new Object[]{masterCd},(rs, rowNum) -> new GroupEntity(
                rs.getInt("entityId"), rs.getString("entityCd"), rs.getString("parentEntityCd"),
                rs.getString("masterCd"), rs.getString("displayValue"), rs.getString("parentDisplayValue"),
                rs.getTimestamp("createdAt"), rs.getTimestamp("updatedAt"), rs.getInt("createdBy"), rs.getInt("updatedBy")
        ));*/

        List ls = jdbcTemplate.queryForList(findAllForGroupMasterCdQuery, new Object[]{masterCd});
        SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllForGroupMasterCdQuery, new Object[]{masterCd}, new int[]{Types.VARCHAR});

        List result = new ArrayList();
        while(rs.next()){
            GroupEntity ge = new GroupEntity(
                    rs.getInt("entityId"), rs.getString("entityCd"), rs.getString("parentEntityCd"),
                    rs.getString("masterCd"), rs.getString("displayValue"), rs.getString("parentDisplayValue"),
                    rs.getTimestamp("createdAt"), rs.getTimestamp("updatedAt"), rs.getInt("createdBy"), rs.getInt("updatedBy"));
            result.add(ge);
        }

        /*        GroupEntity result = jdbcTemplate.queryForObject(findAllForGroupMasterCdQuery, new RowMapper<GroupEntity>(){
            @Override
            public GroupEntity mapRow(ResultSet rs, int i) throws SQLException{
                return new GroupEntity(
                        rs.getInt("entityId"), rs.getString("entityCd"), rs.getString("parentEntityCd"),
                        rs.getString("masterCd"), rs.getString("displayValue"), rs.getString("parentDisplayValue"),
                        rs.getTimestamp("createdAt"), rs.getTimestamp("updatedAt"), rs.getInt("createdBy"), rs.getInt("updatedBy"));
            }
        }, new Object[]{masterCd});*/

        return result;
    }

}
