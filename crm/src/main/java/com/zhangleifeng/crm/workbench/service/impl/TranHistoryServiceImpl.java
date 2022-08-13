package com.zhangleifeng.crm.workbench.service.impl;

import com.zhangleifeng.crm.workbench.domain.TranHistory;
import com.zhangleifeng.crm.workbench.mapper.TranHistoryMapper;
import com.zhangleifeng.crm.workbench.service.TranHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-13 09:33
 **/
@Service
public class TranHistoryServiceImpl implements TranHistoryService {

    @Autowired
    TranHistoryMapper tranHistoryMapper;

    @Override
    public List<TranHistory> selectTranHistoryForDetailByTranId(String id) {
        return tranHistoryMapper.selectTranHistoryForDetailByTranId(id);
    }
}
