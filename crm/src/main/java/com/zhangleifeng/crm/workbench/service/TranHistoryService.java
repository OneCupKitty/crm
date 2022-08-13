package com.zhangleifeng.crm.workbench.service;

import com.zhangleifeng.crm.workbench.domain.TranHistory;

import java.util.List;

public interface TranHistoryService {

    List<TranHistory> selectTranHistoryForDetailByTranId(String id);
}
