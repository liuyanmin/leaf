package com.lym.distributed.controller;

import com.lym.distributed.service.IDistributedUniqueNoService;
import com.lym.util.ResponseData;
import com.lym.util.Const;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OrderController
 * @Description
 * @Author LYM
 * @Date 2018/7/30 14:16
 * @Version 1.0
 */
@RestController
@RequestMapping("/distributed")
@EnableAutoConfiguration
public class DistributedUniqueNoController {
    private static final Logger logger = Logger.getLogger(DistributedUniqueNoController.class);

    @Autowired
    private IDistributedUniqueNoService distributedUniqueNoService;


    /**
     * 获取分布式唯一号接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/no", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getUniqueNo(@RequestBody Map<String, Object> request, HttpServletRequest servletRequest) {
        ResponseData responseData = new ResponseData(Const.ERR_CODE_SUCCESS);
        try {
            String bizTag = (String) request.get(Const.MSG_BIZ_TAG);
            Long orderNo = distributedUniqueNoService.getUniqueNo(bizTag);
            Map<String, Object> data = new HashMap<>();
            data.put(bizTag, orderNo);
            responseData.setData(data);
            return new ResponseEntity(responseData, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("获取分布式唯一号接口异常：", e);
            responseData = new ResponseData(Const.ERR_CODE_SYS_ERR);
            return new ResponseEntity(responseData, HttpStatus.OK);
        }
    }

}
