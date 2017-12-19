package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01Dao;
import com.hisun.saas.zzb.app.console.shpc.entity.*;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01ServiceImpl extends BaseServiceImpl<Sha01,String> implements Sha01Service {

    private Sha01Dao sha01Dao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01, String> sha01Dao) {
        this.baseDao = sha01Dao;
        this.sha01Dao = (Sha01Dao) sha01Dao;
    }


    public void saveFromWordDataMap(Tenant tenant, Map<String, String> dataMap, String pcId){
        this.sha01Dao.saveFromWordDataMap(tenant,dataMap,pcId);
    }

    public PagerVo<Sha01Vo> getSha01VoS(int pageSize, int pageNum, String shpcId, String xmQuery, String noFileQuert){
        Map<String,Object> paramMap = Maps.newHashMap();
        StringBuilder sql = new StringBuilder(" from APP_SH_A01 t where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) and t.APP_SH_PC_ID=:shpcId ");
        paramMap.put("tombstone", "0");
        paramMap.put("shpcId", shpcId);
        paramMap.put("tenant_id",  UserLoginDetailsUtil.getUserLoginDetails().getTenantId());
        if(StringUtils.isNotBlank(xmQuery)){
            sql.append(" and XM like :xmQuery");
            paramMap.put("xmQuery", "%"+ xmQuery + "%");
        }
        if(StringUtils.isNotBlank(noFileQuert) && !noFileQuert.equals("noselect")){
            if(noFileQuert.equals("gbrmspb")){
                sql.append(" and t.id not in(select gbrmspb.APP_SH_A01_ID from APP_SH_A01_GBRMSPB gbrmspb where gbrmspb.file_path <>'')");
            }else if(noFileQuert.equals("kccl")){
                sql.append(" and t.id not in(select kccl.APP_SH_A01_ID from APP_SH_A01_KCCL kccl where kccl.PATH<>'')");
            }else if(noFileQuert.equals("dascqk")){
                sql.append(" and t.id not in(select dascqk.APP_SH_A01_ID from APP_SH_A01_DASCQK dascqk where dascqk.PATH <> '')");
            }else if(noFileQuert.equals("grzdsx")){
                sql.append(" and t.id not in(select grzdsx.APP_SH_A01_ID from APP_SH_A01_GRZDSX grzdsx where  grzdsx.PATH <> '')");
            }

        }

        paramMap.put("startNum", (pageNum-1)*pageSize);
        paramMap.put("pageSize",pageSize);
        List<Map> list = sha01Dao.countReturnMapBySql("select t.* " + sql.toString()+ " order by t.a01_px limit :startNum,:pageSize",paramMap);
        int count = sha01Dao.countBySql("select count(1) " + sql.toString(),paramMap);

        List<Sha01Vo> sha01VoList = Lists.newArrayList();
        Sha01Vo sha01Vo;
        for (Map map : list) {
            sha01Vo = new Sha01Vo();
            sha01Vo.setId(map.get("id").toString());
            if(map.get("jsbs")!=null){
                sha01Vo.setXm(map.get("jsbs").toString()+map.get("xm").toString());
            }else {
                sha01Vo.setXm(map.get("xm") == null ? "" : map.get("xm").toString());
            }
            sha01Vo.setXb(map.get("xb")==null ?"":map.get("xb").toString());;
            sha01Vo.setMz(map.get("mz")==null ?"":map.get("mz").toString());
            sha01Vo.setJg(map.get("jg")==null ?"":map.get("jg").toString());
            sha01Vo.setCsny(map.get("csny")==null ?"":map.get("csny").toString());
            sha01Vo.setRdsj(map.get("rdsj")==null ?"":map.get("rdsj").toString());
            sha01Vo.setWhcd(map.get("whcd")==null ?"":map.get("whcd").toString());
            sha01Vo.setRxjbsj(map.get("rxjbsj")==null ?"":map.get("rxjbsj").toString());
            sha01Vo.setMztjqk(map.get("mztjqk")==null ?"":map.get("mztjqk").toString());
            sha01Vo.setYwfpjl(map.get("ywfpjl")==null ?"":map.get("ywfpjl").toString());
            sha01Vo.setNtzpbyj(map.get("ntzpbyj")==null ?"":map.get("ntzpbyj").toString());
            sha01Vo.setShyj(map.get("shyj")==null ?"":map.get("shyj").toString());
            sha01Vo.setCjgzsj(map.get("cjgzsj")==null ?"":map.get("cjgzsj").toString());
            sha01Vo.setCjgzsjStr(map.get("cjgzsjStr")==null ?"":map.get("cjgzsjStr").toString());
            sha01Vo.setXgzdwjzw(map.get("xgzdwjzw")==null ?"":map.get("xgzdwjzw").toString());
            sha01Vo.setPx(Integer.parseInt(map.get("a01_px").toString()));
            sha01Vo.setZppath(map.get("ZP_PATH")==null ?"":map.get("ZP_PATH").toString());
            sha01VoList.add(sha01Vo);
        }

        PagerVo<Sha01Vo> page = new PagerVo<Sha01Vo>(sha01VoList, count, pageNum, pageSize);
        for(Sha01Vo vo : page.getDatas()){
            Sha01 sha01 = this.getByPK(vo.getId());
            //判断干部详细信息是否有附件
            if(sha01.getGbrmspbs()!=null &&sha01.getGbrmspbs().size()>0) {
                Sha01gbrmspb sha01gbrmspb = sha01.getGbrmspbs().get(0);
                if(sha01gbrmspb.getFilepath()!=null && !sha01gbrmspb.getFilepath().equals("")){
                    vo.setHavagbrmspbFile(true);
                }
            }
            //判断干部详细信息是否有附件
            if(sha01.getKccls()!=null &&sha01.getKccls().size()>0) {
                Sha01kccl sha01Kccl = sha01.getKccls().get(0);
                if(sha01Kccl.getPath()!=null && !sha01Kccl.getPath().equals("")){
                    vo.setHavakcclFile(true);
                }
            }
            //判断档案审查情况是否有附件
            if(sha01.getDascqks()!=null &&sha01.getDascqks().size()>0) {
                Sha01dascqk sha01dascqk = sha01.getDascqks().get(0);
                if(sha01dascqk.getPath()!=null && !sha01dascqk.getPath().equals("")){
                    vo.setHavaDascqkFile(true);
                }


            }
            //判断个人重大事项是否有附件
            if(sha01.getGrzdsxes()!=null &&sha01.getGrzdsxes().size()>0) {
                Sha01grzdsx sha01grzdsx = sha01.getGrzdsxes().get(0);
                if(sha01grzdsx.getPath()!=null && !sha01grzdsx.getPath().equals("")){
                    vo.setHavaGrzdsxFile(true);
                }
            }
        }
        return page;
    }
}