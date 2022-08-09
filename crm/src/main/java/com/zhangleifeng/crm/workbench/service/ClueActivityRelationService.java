package com.zhangleifeng.crm.workbench.service;

import com.zhangleifeng.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-09 11:50
 **/
public interface ClueActivityRelationService {
    int insertClueActivityRelationByList(List<ClueActivityRelation> list);

    int deleteClueActivityRelationByActivityIdAndClueId(ClueActivityRelation clueActivityRelation);
}
