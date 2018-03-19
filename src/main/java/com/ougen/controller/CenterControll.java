package com.ougen.controller;

import com.ougen.Entity.Seckill;
import com.ougen.dto.Exposer;
import com.ougen.dto.SeckillExecution;
import com.ougen.dto.SeckillResult;
import com.ougen.infoenum.InfoEnum;
import com.ougen.myexception.RepeatKillException;
import com.ougen.myexception.SeckillCloseException;
import com.ougen.myexception.SeckillException;
import com.ougen.service.impl.ServiceIpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author:ougen
 * @date:2018/3/1416:22
 */

@Controller
@RequestMapping("/seckill")
public class CenterControll {
    @Autowired
    private ServiceIpml serviceIpml;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> list=serviceIpml.queryAll(0,4);
        model.addAttribute("list",list);
        return "/list";
    }
    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if (seckillId==null){
            return "redirect:/seckill/list";
        }
        Seckill seckill=serviceIpml.queryById(seckillId);
        if (seckill==null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "/detail";
    }
    //暴露秒杀接口的方法
    @RequestMapping(value = "/{seckillId}/exposer",
                    method = RequestMethod.GET,
                    produces = "application/json;charset=utf-8")
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> seckillResult=null;
        try {
            Exposer  exposer = serviceIpml.exportSeckillUrl(seckillId);
            seckillResult=new SeckillResult<Exposer>(true,exposer);
        }catch (SeckillException e){
            seckillResult=new SeckillResult<Exposer>(false,e.getMessage());
        }
        return seckillResult;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution")
    @ResponseBody
    public SeckillResult<SeckillExecution> excution(@PathVariable("seckillId") Long seckillId,
                                                    @PathVariable("md5") String md5,
                                                    @CookieValue(value = "userPhone",required = false) Long userPhone){
        if (userPhone==null){
            return new SeckillResult(false,"未注册");
        }
        SeckillExecution execution=null;
        try {
            execution=serviceIpml.executeSeckill(seckillId,userPhone,md5);
        }catch (RepeatKillException e){
            execution=new SeckillExecution( InfoEnum.REPEAT, seckillId);
        }catch (SeckillCloseException e){
            execution=new SeckillExecution(InfoEnum.CLOSE,seckillId);

        }catch (SeckillException e){
            execution=new SeckillExecution(InfoEnum.PUBLIC_ERROR,seckillId);
        }
        return new SeckillResult<SeckillExecution>(true,execution);

    }
    @RequestMapping(value = "/time/now",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public SeckillResult<Long> time()
    {
        Date now=new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }


}
