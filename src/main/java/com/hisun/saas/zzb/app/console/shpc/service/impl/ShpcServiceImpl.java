package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.google.common.collect.Lists;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.ShpcDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtpsj;
import com.hisun.saas.zzb.app.console.shtp.service.ShtpsjService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class ShpcServiceImpl extends BaseServiceImpl<Shpc,String> implements ShpcService{

    private ShpcDao shpcDao;
    @Resource
    private Sha01Service sha01Service;
    @Resource
    private ShtpsjService shtpsjService;
    @Autowired
    public void setBaseDao(BaseDao<Shpc, String> shpcDao) {
        this.baseDao = shpcDao;
        this.shpcDao = (ShpcDao) shpcDao;
    }
    @Override
    public List<Sha01Vo> getShpcById(String shpcId) throws Exception{
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" shpc.id = :shpcId", "shpcId", shpcId));
        query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("px"));

        //取得当前批次下的人员列表
        Long total = this.sha01Service.count(query);
        List<Sha01> sha01s = this.sha01Service.list(query,orderBy);
        List<Sha01Vo> sha01Vos = new ArrayList<Sha01Vo>();
        //查询该批次下的投票数据
        query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" shtp.shpc.id = :shpcId", "shpcId", shpcId));
        query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
        List<Shtpsj> shtpsjs = this.shtpsjService.list(query, null);

        if (sha01s != null) {// entity ==> vo
            for (Sha01 sha01 : sha01s) {
                Sha01Vo vo = new Sha01Vo();
                int tyCount = 0;//同意票数
                int btyCount = 0;//不同意票数
                int qqCount = 0;//弃权票数
                String dplCount = "0";//得票率

                if(shtpsjs!=null){
                    for(Shtpsj shtpsj : shtpsjs){
                        if(shtpsj.getSha01().getId().equals(sha01.getId())){
                            if(shtpsj.getTp()==1){
                                tyCount++;
                            }else  if(shtpsj.getTp()==2){
                                btyCount++;
                            }else  if(shtpsj.getTp()==3){
                                qqCount++;
                            }
                        }
                    }
                }
                float num= (float)tyCount/(tyCount+btyCount+qqCount)*100;
                if(Float.isNaN(num)){
                    dplCount="NaN";
                }else {
                    DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                    dplCount = df.format(num);//返回的是String类型
                }
                vo.setXm(sha01.getXm());
                vo.setTyCount(tyCount);
                vo.setBtyCount(btyCount);
                vo.setQqCount(qqCount);
                vo.setDplCount(dplCount);

                sha01Vos.add(vo);
            }
        }
        return sha01Vos;
    }

    public void exportExcel(String title, List<Sha01Vo> Sha01Vos, OutputStream out) {
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(title);
        // 声明一个画图的顶级管理器
        Drawing patriarch = sheet.createDrawingPatriarch();
        // 产生表格标题行
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        CellStyle titleCellStyle = workbook.createCellStyle(); // 标题样式对象
        Font font = workbook.createFont();//设置字体
        font.setFontHeightInPoints((short) 13);//设置字体大小
        font.setBold(true);//加粗
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
        titleCellStyle.setFont(font);
        titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cell.setCellValue(title);
        cell.setCellStyle(titleCellStyle);



        CellStyle cellStyle1 = workbook.createCellStyle(); // title样式对象
        font = workbook.createFont();//设置字体
        font.setFontHeightInPoints((short) 10);//设置字体大小
        font.setBold(true);//加粗
        cellStyle1.setFont(font);
        cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);


        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("姓名");
        cell.setCellStyle(cellStyle1);

        cell = row.createCell(1);
        cell.setCellValue("票决结果");
        cell.setCellStyle(cellStyle1);

        cell = row.createCell(4);
        cell.setCellValue("得票率");
        cell.setCellStyle(cellStyle1);


        sheet.setColumnWidth(0,3000);
        sheet.setColumnWidth(1,3000);
        sheet.setColumnWidth(2,3000);
        sheet.setColumnWidth(3,3000);
        sheet.setColumnWidth(4,3000);

        row = sheet.createRow(2);
        List<String> headers= Lists.newArrayList();
        headers.add("同意(票数）");
        headers.add("不同意(票数）");
        headers.add("弃权(票数）");
        String name = "";
        for (short i = 0; i < headers.size(); i++) {

            name=headers.get(i);
            cell = row.createCell(i+1);
            cell.setCellStyle(cellStyle1);
            cell.setCellValue(name);

        }
        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);

        if(Sha01Vos!=null){
            int index = 2;
            for(Sha01Vo Sha01Vo : Sha01Vos) {
                index++;
                row = sheet.createRow(index);
                cell = row.createCell(0);
                cell.setCellValue(Sha01Vo.getXm());

                cell = row.createCell(1);
                cell.setCellValue(Sha01Vo.getTyCount());
				cell.setCellStyle(cellStyle2);

                cell = row.createCell(2);
                cell.setCellValue(Sha01Vo.getBtyCount());
				cell.setCellStyle(cellStyle2);

                cell = row.createCell(3);
                cell.setCellValue(Sha01Vo.getQqCount());
				cell.setCellStyle(cellStyle2);

                cell = row.createCell(4);
                cell.setCellValue(Sha01Vo.getDplCount()+"%");
				cell.setCellStyle(cellStyle2);

            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
