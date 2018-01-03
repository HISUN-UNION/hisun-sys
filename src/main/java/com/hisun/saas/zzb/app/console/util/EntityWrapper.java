package com.hisun.saas.zzb.app.console.util;

import com.hisun.base.entity.BaseEntity;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;

import java.util.Date;

/**
 * Created by zhouying on 2017/12/25.
 */
public class EntityWrapper {

    public static void wrapperSaveBaseProperties(BaseEntity entity, UserLoginDetails userLoginDetails) {
        if (userLoginDetails == null) {
            return;
        }
        entity.setCreateDate(new Date());
        entity.setCreateUserId(userLoginDetails.getUserid());
        entity.setCreateUserName(userLoginDetails.getUsername());

    }

    public static void wrapperUpdateBaseProperties(BaseEntity entity, UserLoginDetails userLoginDetails) {
        if (userLoginDetails == null) {
            return;
        }
        entity.setUpdateDate(new Date());
        entity.setUpdateUserId(userLoginDetails.getUserid());
        entity.setUpdateUserName(userLoginDetails.getUsername());
    }

    public static void wrapperSaveBaseProperties(TenantEntity entity, UserLoginDetails userLoginDetails) {
        if (userLoginDetails == null) {
            return;
        }
        entity.setTenant(userLoginDetails.getTenant());
        entity.setCreateDate(new Date());
        entity.setCreateUserId(userLoginDetails.getUserid());
        entity.setCreateUserName(userLoginDetails.getUsername());

    }

    public static void wrapperUpdateBaseProperties(TenantEntity entity, UserLoginDetails userLoginDetails) {
        if (userLoginDetails == null) {
            return;
        }
        entity.setUpdateDate(new Date());
        entity.setUpdateUserId(userLoginDetails.getUserid());
        entity.setUpdateUserName(userLoginDetails.getUsername());
    }
}
