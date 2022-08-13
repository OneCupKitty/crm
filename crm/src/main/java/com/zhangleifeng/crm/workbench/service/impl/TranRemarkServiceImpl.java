package com.zhangleifeng.crm.workbench.service.impl;

import com.zhangleifeng.crm.workbench.domain.TranRemark;
import com.zhangleifeng.crm.workbench.mapper.TranRemarkMapper;
import com.zhangleifeng.crm.workbench.service.TranRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-13 09:26
 **/
@Service
public class TranRemarkServiceImpl implements TranRemarkService {

    @Autowired
    TranRemarkMapper tranRemarkMapper;

    @Override
    public List<TranRemark> selectTranRemarkForDetailByTranId(String id) {
        return tranRemarkMapper.selectTranRemarkForDetailByTranId(id);
    }
}
