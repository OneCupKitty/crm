package com.zhangleifeng.crm.settings.service;

import com.zhangleifeng.crm.settings.domain.DicValue;

import java.util.List;

public interface DicValueService {
    List<DicValue> selectDicValueByTypeCode(String typeCode);
}
