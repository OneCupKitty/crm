package com.zhangleifeng.crm.workbench.web.controller;

import com.zhangleifeng.crm.commons.contants.Contants;
import com.zhangleifeng.crm.commons.domain.ReturnObject;
import com.zhangleifeng.crm.commons.utils.DateUtils;
import com.zhangleifeng.crm.commons.utils.SessionUserUtils;
import com.zhangleifeng.crm.commons.utils.UUIDUtils;
import com.zhangleifeng.crm.workbench.domain.ClueRemark;
import com.zhangleifeng.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-09 17:27
 **/
@Controller
public class ClueRemarkController {

    @Autowired
    ClueRemarkService clueRemarkService;

    @RequestMapping("/workbench/activity/createClueRemark.do")
    @ResponseBody
    public Object createClueRemark(String clueId, String noteContent, HttpSession session){
        //创建备注
        ClueRemark clueRemark = new ClueRemark();
        clueRemark.setClueId(clueId);
        clueRemark.setNoteContent(noteContent);
        clueRemark.setCreateBy(SessionUserUtils.getSessionUserId(session));
        clueRemark.setCreateTime(DateUtils.formatDateTime(new Date()));
        clueRemark.setId(UUIDUtils.getUUID());
        clueRemark.setEditFlag(Contants.REMARK_EDIT_FLAG_NO_EDITED);

        ReturnObject returnObject=new ReturnObject();
        try {
            int ret = clueRemarkService.insertClueRemarkByClueId(clueRemark);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setReturnData(clueRemark);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/updateClueRemarkByClueId.do")
    @ResponseBody
    public Object updateClueRemarkByClueId(String id,String noteContent,HttpSession session){
        ClueRemark clueRemark = new ClueRemark();
        clueRemark.setId(id);
        clueRemark.setNoteContent(noteContent);
        clueRemark.setEditBy(SessionUserUtils.getSessionUserId(session));
        clueRemark.setEditTime(DateUtils.formatDateTime(new Date()));
        clueRemark.setEditFlag(Contants.REMARK_EDIT_FLAG_YES_EDITED);

        ReturnObject returnObject=new ReturnObject();
        try {
            int ret = clueRemarkService.updateClueRemarkByClueRemarkId(clueRemark);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setReturnData(clueRemark);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/deleteClueRemarkByClueRemarkId.do")
    @ResponseBody
    public Object deleteClueRemarkByClueId(String id){

        ReturnObject returnObject=new ReturnObject();
        try {
            int ret = clueRemarkService.deleteClueRemarkByClueRemarkId(id);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }
        return returnObject;
    }
}
