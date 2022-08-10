package com.zhangleifeng.crm.workbench.service;

import com.zhangleifeng.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueService {
    int insertClue(Clue clue);

    List<Clue> selectAllClue();

    Clue selectClueForDetailByClueId(String clueId);

    List<Clue> selectClueByConditionForPage(Map<String,Object> map);

    int selectCountClueByCondition(Map<String,Object> map);

    void saveClueConvert(Map<String,Object> map);
}
