package com.zhangleifeng.crm.workbench.service;

import com.zhangleifeng.crm.workbench.domain.TranRemark;

import java.util.List;

public interface TranRemarkService {

    List<TranRemark> selectTranRemarkForDetailByTranId(String id);
}
