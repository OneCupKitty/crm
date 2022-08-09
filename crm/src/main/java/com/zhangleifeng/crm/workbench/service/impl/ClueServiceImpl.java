package com.zhangleifeng.crm.workbench.service.impl;

import com.zhangleifeng.crm.workbench.domain.Clue;
import com.zhangleifeng.crm.workbench.mapper.ClueMapper;
import com.zhangleifeng.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-08 09:54
 **/
@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    ClueMapper clueMapper;

    @Override
    public int insertClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    @Override
    public List<Clue> selectAllClue() {
        return clueMapper.selectAllClue();
    }

    @Override
    public Clue selectClueForDetailByClueId(String clueId) {
        return clueMapper.selectClueForDetailByClueId(clueId);
    }

    @Override
    public List<Clue> selectClueByConditionForPage(Map<String,Object> map) {
        return clueMapper.selectClueByConditionForPage(map);
    }

    @Override
    public int selectCountClueByCondition(Map<String, Object> map) {
        return clueMapper.selectCountClueByCondition(map);
    }
}
