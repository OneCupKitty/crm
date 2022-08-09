package com.zhangleifeng.crm.settings.service.impl;

import com.zhangleifeng.crm.settings.domain.DicValue;
import com.zhangleifeng.crm.settings.mapper.DictValueMapper;
import com.zhangleifeng.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-08 09:31
 **/
@Service
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    DictValueMapper dictValueMapper;

    @Override
    public List<DicValue> selectDicValueByTypeCode(String typeCode) {
        return dictValueMapper.selectDicValueByTypeCode(typeCode);
    }
}
