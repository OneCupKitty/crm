package com.zhangleifeng.crm.workbench.service;

import com.zhangleifeng.crm.workbench.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkService {

    List<ClueRemark> selectClueRemarkForDetailByClueId(String clueId);

    int insertClueRemarkByClueId(ClueRemark clueRemark);

    int updateClueRemarkByClueRemarkId(ClueRemark clueRemark);

    int deleteClueRemarkByClueId(String id);
}
