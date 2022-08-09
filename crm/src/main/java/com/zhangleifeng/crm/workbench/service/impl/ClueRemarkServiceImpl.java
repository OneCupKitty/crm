package com.zhangleifeng.crm.workbench.service.impl;

import com.zhangleifeng.crm.workbench.domain.ClueRemark;
import com.zhangleifeng.crm.workbench.mapper.ClueRemarkMapper;
import com.zhangleifeng.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-08 16:56
 **/
@Service
public class ClueRemarkServiceImpl implements ClueRemarkService {

    @Autowired
    ClueRemarkMapper clueRemarkMapper;

    @Override
    public List<ClueRemark> selectClueRemarkForDetailByClueId(String clueId) {
        return clueRemarkMapper.selectClueRemarkForDetailByClueId(clueId);
    }

    @Override
    public int insertClueRemarkByClueId(ClueRemark clueRemark) {
        return clueRemarkMapper.insertClueRemarkByClueId(clueRemark);
    }

    @Override
    public int updateClueRemarkByClueRemarkId(ClueRemark clueRemark) {
        return clueRemarkMapper.updateClueRemarkByClueRemarkId(clueRemark);
    }

    @Override
    public int deleteClueRemarkByClueId(String id) {
        return clueRemarkMapper.deleteClueRemarkByClueId(id);
    }
}
