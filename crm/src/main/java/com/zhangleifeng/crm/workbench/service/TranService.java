package com.zhangleifeng.crm.workbench.service;

import com.zhangleifeng.crm.workbench.domain.FunnelVO;
import com.zhangleifeng.crm.workbench.domain.Tran;

import java.util.List;
import java.util.Map;

public interface TranService {

    List<Tran> selectTranByConditionForPage(Map<String,Object> map);

    int selectCountTranByConditionForPage(Map<String,Object> map);

    void saveCreateTran(Map<String,Object> map);

    Tran selectTranForDetailById(String id);

    List<FunnelVO> selectCountOfTranGroupByStage();
}
