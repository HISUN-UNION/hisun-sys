package com.hisun.saas.zzb.app.console.apiregister.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by zhouying on 2017/10/12.
 */
@Entity
@Table(name = "app_api_register")
public class ApiRegister extends TombstoneEntity implements Serializable{

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 32)
    private String id;
    @Column(name = "ip",nullable = false,length = 32)
    private String ip;
    @Column(name = "port",nullable = false,length = 10)
    private String port;
    @Column(name = "context",nullable = true,length = 32)
    private String context;
    @Column(name = "uri",nullable = false,length = 128)
    private String uri;
    @Column(name = "api_code",nullable = false,length = 32)
    private String apiCode;
    @Column(name = "request_method",nullable = false,length = 32)
    private String requestMethod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }


    public String toInsertSql(){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" app_api_register ");
        sb.append("(");
        sb.append("id");
        sb.append(",ip");
        sb.append(",port");
        sb.append(",context");
        sb.append(",uri");
        sb.append(",api_code");
        sb.append(",request_method");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.transNull(id)+"'");
        sb.append(",'"+ StringUtils.transNull(ip)+"'");
        sb.append(",'"+ StringUtils.transNull(port)+"'");
        sb.append(",'"+ StringUtils.transNull(context)+"'");
        sb.append(",'"+ StringUtils.transNull(uri)+"'");
        sb.append(",'"+ StringUtils.transNull(apiCode)+"'");
        sb.append(",'"+ StringUtils.transNull(requestMethod)+"'");
        sb.append(")");
        return sb.toString();
    }
}
