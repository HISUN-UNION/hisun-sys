drop table if exists `app_sh_pc` ;
create table if not exists app_sh_pc (
  `id` varchar(32) not null,
  `pc_mc` varchar(255) null ,
  `pc_sj` datetime null ,
  `shlx` varchar(1) null ,
  `sjlx` varchar(1) null ,
  `path` varchar(128) null ,
  `pc_px` varchar(1) null ,
  primary key (`id`));

drop table if exists `app_sh_tp` ;
create table if not exists `app_sh_tp` (
  `id` varchar(32) not null,
  `tpq_bh` varchar(128) null ,
  `tpr_id` varchar(32) null ,
  `tpr_xm` varchar(64) null ,
  `tp_sj` datetime null ,
  `app_sh_pc_id` varchar(32) not null,
  `is_posted` varchar(1) not null DEFAULT 0,
  primary key (`id`),

  constraint `fk_rm_sh_tp_rm_sh_pc1`
    foreign key (`app_sh_pc_id`)
    references `app_sh_pc` (`id`)
    on delete no action
    on update no action);

drop table if exists `app_sh_a01` ;
create table if not exists `app_sh_a01` (
  `id` varchar(32) not null,
  `app_sh_pc_id` varchar(32) not null,
  `rmlx` varchar(1) null ,
  `rmlx_str` varchar(128) null,
  `xm` varchar(20) null,
  `xb` varchar(10) null,
  `mz` varchar(20) null,
  `jg` varchar(20) null,
  `csny` varchar(24) null,
  `cjgzsj` varchar(24) null,
  `rdsj` varchar(24) null,
  `whcd` varchar(40) null,
  `rxjbsj` varchar(24) null,
  `mztjqk` varchar(80) null,
  `ywfpjl` varchar(10) null,
  `xgzdwjzw` varchar(128) null,
  `ntzpbyj` varchar(128) null,
  `shyj` varchar(10) null ,
  `zp_path` varchar (128) null,
  `a01_px` int null,
  primary key (`id`));

drop table if exists `app_sh_tp_sj` ;

create table if not exists `app_sh_tp_sj` (
  `id` varchar(32) not null,
  `tp` int not null ,
  `app_sh_tp_id` varchar(32) not null,
  `app_sh_a01_id` varchar(32) not null,
  primary key (`id`));

drop table if exists `app_sh_a01_kccl` ;

create table if not exists `app_sh_a01_kccl` (
  `id` varchar(32) not null,
  `path` varchar(128) null,
  `app_sh_a01_id` varchar(32) not null,
  primary key (`id`));


drop table if exists `app_sh_a01_dascqk` ;

create table if not exists `app_sh_a01_dascqk` (
  `id` varchar(32) not null,
  `path` varchar(128) null,
  `app_sh_a01_id` varchar(32) not null,
  primary key (`id`));



drop table if exists `app_sh_a01_grzdsx` ;

create table if not exists `app_sh_a01_grzdsx` (
  `id` varchar(32) not null,
  `path` varchar(128) null,
  `app_sh_a01_id` varchar(32) not null,
  primary key (`id`));



drop table if exists `app_sh_a01_dascqk_tips` ;

create table if not exists `app_sh_a01_dascqk_tips` (
  `id` varchar(32) not null,
  `tip` varchar(256) null,
  `app_sh_a01_dascqk_id` varchar(32) not null,
  primary key (`id`));



drop table if exists `app_sh_a01_gbrmspb` ;

create table if not exists `app_sh_a01_gbrmspb` (
  `id` varchar(32) not null,
  `xm` varchar(20) null,
  `xb` varchar(10) null,
  `csny` varchar(24) null,
  `nl` varchar(10) null,
  `mz` varchar(24) null,
  `jg` varchar(24) null,
  `csd` varchar(24) null,
  `rdsj` varchar(10) null,
  `cjgzsj` varchar(10) null,
  `jkzk` varchar(24) null,
  `zyjszw` varchar(60) null,
  `zytc` varchar(60) null,
  `xl_qrz` varchar(24) null,
  `xw_qrz` varchar(24) null,
  `xl_zz` varchar(24) null,
  `xw_zz` varchar(24) null,
  `qrz_byyx` varchar(128) null,
  `zz_byyx` varchar(128) null,
  `xrzw` varchar(128) null,
  `nrzw` varchar(128) null,
  `nmzw` varchar(128) null,
  `rmly` varchar(255) null,
  `cbdwyj` varchar(255) null,
  `spjgyj` varchar(255) null,
  `xzjgrmyj` varchar(255) null,
    `path` varchar(128) null,
  `app_sh_a01_id` varchar(32) not null,
  primary key (`id`));

drop table if exists `app_sh_a01_gzjl` ;

create table if not exists `app_sh_a01_gzjl` (
  `id` varchar(32) not null,
  `c_sj` varchar(24) null,
  `z_sj` varchar(24) null,
  `jlsm` varchar(256) null,
  `app_sh_a01_id` varchar(32) not null,
  `gzjl_px` int null,
  primary key (`id`));


drop table if exists `app_sh_a01_jc` ;

create table if not exists `app_sh_a01_jc` (
  `id` varchar(32) not null,
  `nd` varchar(24) null,
  `jcsm` varchar(256) null,
  `app_sh_a01_id` varchar(32) not null,
  `jc_px` int null,
  primary key (`id`));



drop table if exists `app_sh_a01_ndkh` ;

create table if not exists `app_sh_a01_ndkh` (
  `id` varchar(32) not null,
  `nd` varchar(24) null,
  `khjg` varchar(60) null,
  `app_sh_a01_id` varchar(32) not null,
  `ndkh_px` int null,
  primary key (`id`));



drop table if exists `app_sh_a01_shgx` ;

create table if not exists `app_sh_a01_shgx` (
  `id` varchar(32) not null,
  `cw` varchar(24) null,
  `xm` varchar(24) null,
  `nl` varchar(10) null,
  `zzmm` varchar(24) null,
  `gzdwjzw` varchar(128) null,
  `app_sh_a01_id` varchar(32) not null,
  `shgx_px` int null,
  primary key (`id`));


drop table if exists `app_menu_setting` ;

create table if not exists `app_menu_setting` (
  `id` int not null,
  `menu_code` varchar(40) not null ,
  `display` int not null default 0 ,
  primary key (`id`));


drop table if exists `app_dwjg_tj` ;

create table if not exists `app_dwjg_tj` (
  `id` varchar(32) not null,
  `tj_mc` varchar(128) null ,
  `tj_json_data` text null ,
  `tj_px` int null,
  `tblx` varchar(1) not null,
  primary key (`id`));

drop table if exists `app_api_register`;
create table `app_api_register` (
  `id` varchar(32) not null,
  `ip` varchar(32) not null,
  `port` varchar(10) not null,
  `context` varchar(32) default null,
  `uri` varchar(128) not null,
  `api_code` varchar(32) not null,
  `request_method` varchar(32) not null,
  primary key (`id`));

  drop table if exists `app_mc`;
  CREATE TABLE `app_mc` (
  `id` varchar(32) NOT NULL,
  `mc` varchar(255) NOT NULL,
  `px` int(11) NOT NULL DEFAULT '99',
  PRIMARY KEY (`id`)
);

drop table if exists `app_mc_b01`;
CREATE TABLE `app_mc_b01` (
  `id` varchar(32) NOT NULL,
  `b0101` varchar(255) NOT NULL,
  `px` int(11) NOT NULL DEFAULT '99',
  `mc_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `app_mc_b01_app_mc_id_fk` FOREIGN KEY (`mc_id`) REFERENCES `app_mc` (`id`)
);

drop table if exists `app_mc_a01`;
CREATE TABLE `app_mc_a01` (
  `id` varchar(32) NOT NULL,
  `xm` varchar(10) DEFAULT NULL,
  `mz` varchar(10) DEFAULT NULL,
  `zw` varchar(255) DEFAULT NULL,
  `csd` varchar(40) DEFAULT NULL,
  `jg` varchar(20) DEFAULT NULL,
  `csny` varchar(20) DEFAULT NULL,
  `cjgzsj` varchar(20) DEFAULT NULL,
  `rdsj` varchar(20) DEFAULT NULL,
  `qrzxlxwjzy` varchar(100) DEFAULT NULL,
  `zzxlxwjzy` varchar(100) DEFAULT NULL,
  `zyjszw` varchar(100) DEFAULT NULL,
  `xrzwsj` varchar(20) DEFAULT NULL,
  `xrzjsj` varchar(40) DEFAULT NULL,
  `b01_id` varchar(32) NOT NULL,
  `a01_px` int(11) NOT NULL,
  `zp_path` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `app_mc_a01_app_mc_b01_id_fk` FOREIGN KEY (`b01_id`) REFERENCES `app_mc_b01` (`id`)
);


drop table if exists `app_mc_a01_gbrmspb` ;

create table if not exists `app_mc_a01_gbrmspb` (
  `id` varchar(32) not null,
  `xm` varchar(20) null,
  `xb` varchar(10) null,
  `csny` varchar(24) null,
  `nl` varchar(10) null,
  `mz` varchar(24) null,
  `jg` varchar(24) null,
  `csd` varchar(24) null,
  `rdsj` varchar(10) null,
  `cjgzsj` varchar(10) null,
  `jkzk` varchar(24) null,
  `zyjszw` varchar(60) null,
  `zytc` varchar(60) null,
  `xl_qrz` varchar(24) null,
  `xw_qrz` varchar(24) null,
  `xl_zz` varchar(24) null,
  `xw_zz` varchar(24) null,
  `qrz_byyx` varchar(128) null,
  `zz_byyx` varchar(128) null,
  `xrzw` varchar(128) null,
  `nrzw` varchar(128) null,
  `nmzw` varchar(128) null,
  `rmly` varchar(255) null,
  `cbdwyj` varchar(255) null,
  `spjgyj` varchar(255) null,
  `xzjgrmyj` varchar(255) null,
    `path` varchar(128) null,
  `app_mc_a01_id` varchar(32) not null,
  primary key (`id`));


drop table if exists `app_mc_a01_gzjl` ;

create table if not exists `app_mc_a01_gzjl` (
  `id` varchar(32) not null,
  `c_sj` varchar(24) null,
  `z_sj` varchar(24) null,
  `jlsm` varchar(256) null,
  `app_mc_a01_id` varchar(32) not null,
  `gzjl_px` int null,
  primary key (`id`));

  drop table if exists `app_sh_pc_atts` ;
  CREATE TABLE `app_sh_pc_atts`
(
    `id` VARCHAR(32) PRIMARY KEY NOT NULL,
    `file_name` VARCHAR(128) NOT NULL,
    `file_path` VARCHAR(255) NOT NULL,
    `sh_pc_id` VARCHAR(32) NOT NULL,
    CONSTRAINT app_sh_pc_atts_app_sh_pc_id_fk FOREIGN KEY (sh_pc_id) REFERENCES app_sh_pc (id)
);

