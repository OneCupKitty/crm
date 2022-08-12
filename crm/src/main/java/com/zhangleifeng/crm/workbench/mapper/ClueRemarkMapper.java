package com.zhangleifeng.crm.workbench.mapper;

import com.zhangleifeng.crm.workbench.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Mon Aug 08 16:08:57 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Mon Aug 08 16:08:57 CST 2022
     */
    int insert(ClueRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Mon Aug 08 16:08:57 CST 2022
     */
    int insertSelective(ClueRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Mon Aug 08 16:08:57 CST 2022
     */
    ClueRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Mon Aug 08 16:08:57 CST 2022
     */
    int updateByPrimaryKeySelective(ClueRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Mon Aug 08 16:08:57 CST 2022
     */
    int updateByPrimaryKey(ClueRemark record);

    /**
     * 根据线索id 查询线索备注的详细信息
     * @param clueId
     * @return
     */
    List<ClueRemark> selectClueRemarkForDetailByClueId(String clueId);

    /**
     * 通过线索id 创建线索备注
     * @param clueRemark
     * @return
     */
    int insertClueRemarkByClueId(ClueRemark clueRemark);

    /**
     * 通过线索备注id 更新线索备注
     * @param clueRemark
     * @return
     */
    int updateClueRemarkByClueRemarkId(ClueRemark clueRemark);

    /**
     * 通过线索备注id 删除备注
     * @param id
     * @return
     */
    int deleteClueRemarkByClueRemarkId(String id);

    /**
     * 根据线索id 查询该线索的备注原始信息
     * @param clueId
     * @return
     */
    List<ClueRemark> selectClueRemarkByClueId(String clueId);

    /**
     * 通过线索id 删除线索备注
     * @param clueId
     * @return
     */
    int deleteClueRemarkByClueId(String clueId);
}