package com.hisun.saas.zzb.app.console.gendata.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/23.
 */
@Entity
@Table(name = "APP_GENDATA")
public class Gendata extends TenantEntity implements Serializable {

    public static int IS_CURRENT=1;
    public static int IS_NOT_CURRENT=0;

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;

    @Column(name = "PATH",length = 255)
    private String path;

    @Column(name = "is_current_packet")//是否当前数据包 0-不是数据包，1-当前数据包
    private int isCurrentPacket=IS_NOT_CURRENT;

    @Column(name = "packet_md5",length = 64)
    private String packetMd5;

    @Column(name = "packet_size",length = 32)
    private String packetSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIsCurrentPacket() {
        return isCurrentPacket;
    }

    public void setIsCurrentPacket(int isCurrentPacket) {
        this.isCurrentPacket = isCurrentPacket;
    }

    public String getPacketMd5() {
        return packetMd5;
    }

    public void setPacketMd5(String packetMd5) {
        this.packetMd5 = packetMd5;
    }

    public String getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(String packetSize) {
        this.packetSize = packetSize;
    }
}
