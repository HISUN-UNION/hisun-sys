package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface ShpcService extends BaseService<Shpc,String> {
    public static String ATTS_PATH = File.separator+"shpc"+ File.separator;
    List<Sha01Vo> getShpcById(String shpcId) throws Exception;
    void exportExcel(String title, List<Sha01Vo> Sha01Vos, OutputStream out);
}
