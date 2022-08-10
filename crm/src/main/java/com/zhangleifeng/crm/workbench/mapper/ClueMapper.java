package com.zhangleifeng.crm.workbench.mapper;

import com.zhangleifeng.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Mon Aug 08 09:49:43 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Mon Aug 08 09:49:43 CST 2022
     */
    int insert(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Mon Aug 08 09:49:43 CST 2022
     */
    int insertSelective(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Mon Aug 08 09:49:43 CST 2022
     */
    Clue selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Mon Aug 08 09:49:43 CST 2022
     */
    int updateByPrimaryKeySelective(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Mon Aug 08 09:49:43 CST 2022
     */
    int updateByPrimaryKey(Clue record);

    /**
     * 创建 线索
     * @param clue
     * @return
     */
    int insertClue(Clue clue);

    /**
     * 查询所有的线索
     * @return
     */
    List<Clue> selectAllClue();

    /**
     * 根据线索id 查询线索详细信息
     * @param clueId
     * @return
     */
    Clue selectClueForDetailByClueId(String clueId);

    /**
     * 条件分页查询线索
     * @param map
     * @return
     */
    List<Clue> selectClueByConditionForPage(Map<String,Object> map);

    /**
     * 根据条件 查询线索数量
     * @param map
     * @return
     */
    int selectCountClueByCondition(Map<String,Object> map);

    /**
     * 根据线索id 查询线索原始信息
     * @param id
     * @return
     */
    Clue selectClueByClueId(String id);

}