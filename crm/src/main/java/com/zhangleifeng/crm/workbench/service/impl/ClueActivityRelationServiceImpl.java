package com.zhangleifeng.crm.workbench.service.impl;

import com.zhangleifeng.crm.workbench.domain.ClueActivityRelation;
import com.zhangleifeng.crm.workbench.mapper.ClueActivityRelationMapper;
import com.zhangleifeng.crm.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-09 11:51
 **/
@Service
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {

    @Autowired
    ClueActivityRelationMapper clueActivityRelationMapper;

    @Override
    public int insertClueActivityRelationByList(List<ClueActivityRelation> list) {
        return clueActivityRelationMapper.insertClueActivityRelationByList(list);
    }

    @Override
    public int deleteClueActivityRelationByActivityIdAndClueId(ClueActivityRelation clueActivityRelation) {
        return clueActivityRelationMapper.deleteClueActivityRelationByActivityIdAndClueId(clueActivityRelation);
    }
}
