package com.young.mapper;

import com.young.model.Vip;
import com.young.model.VipExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface VipMapper {
    long countByExample(VipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Vip record);

    int insertSelective(Vip record);

    List<Vip> selectByExample(VipExample example);

    Vip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vip record);

    int updateByPrimaryKey(Vip record);
    
    @Select("select vip.*,dict.name as levelName,REPLACE(concat(vip.mobile1,vip.mobile2), SUBSTR(concat(vip.mobile1,vip.mobile2),4,4), '****') as mobile from vip LEFT JOIN dict on vip.`level` = dict.`no` where dict.`key`='vip_level' ${sql} ORDER BY id")
    List<Vip> selectVipList(String sql);
    
    @Select("select * from vip where CONCAT(mobile1,mobile2) = #{mobile}")
    Vip selectVipByMobile(String mobile);

    @Update("update vip set back1 = #{newName} where back1 = #{oldName} ")
	int updateVipRemByBack(String oldName, String newName);

    @Select("select vip.*,d.name from(\r\n" + 
    		"select v.`level`,count(0) as count, sum(v.now_money) as now_money,sum(v.total_money) as total_money,sum(v.sum_consume) as sum_consume from vip v GROUP BY v.`level`\r\n" + 
    		") vip left join dict d on vip.`level` = d.`no` where d.`key`='vip_level'")
	List<Map<String, Object>> getVipTableData();

    @Select("select count(0) as count,sum(v.now_money) as now_money,sum(v.total_money) as total_money,sum(v.sum_consume) as sum_consume from vip v")
	Map<String, Object> getVipTableSumData();
}