package com.hisun.saas.zzb.app.console.shtp.api;

import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtp;
import com.hisun.saas.zzb.app.console.shtp.service.ShtpService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouying on 2017/10/10.
 */

@RestController
@RequestMapping(value="/api/shtp")
public class ShtpRestApi {

    @Resource
    private ShtpService shtpService;
    @Resource
    private ShpcService shpcService;


    @RequestMapping(value = "/add", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Map> add( @RequestBody Shtp shtp) {
        Map<String,String> resultMap = new HashMap<String,String>();
        try {
            shtpService.save(shtp);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("success","false");
            return new ResponseEntity<Map>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        resultMap.put("success","true");
        return new ResponseEntity<Map>(resultMap, HttpStatus.CREATED);
    }


}
