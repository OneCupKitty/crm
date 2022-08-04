package com.zhangleifeng.uuid;

import com.zhangleifeng.crm.commons.utils.UUIDUtils;
import org.junit.Test;

import java.util.UUID;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-01 16:42
 **/
public class MyTest {
    @Test
    public void testUUID(){
        String s = UUID.randomUUID().toString().replace("-","");
        System.out.println(s);
        System.out.println(UUIDUtils.getUUID());
    }
}
