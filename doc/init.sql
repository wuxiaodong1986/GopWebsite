SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS  `bc_block`;
CREATE TABLE `bc_block` (
  `block_num` bigint(20) NOT NULL COMMENT '区块号',
  `previous_secret` varchar(100) DEFAULT NULL COMMENT '下一轮出块凭证',
  `delegate_signature` varchar(300) DEFAULT NULL COMMENT '代理签名',
  `user_transaction_ids` text COMMENT '所有的交易id列表',
  `id` varchar(100) DEFAULT NULL COMMENT '块hash',
  `block_size` int(11) DEFAULT NULL COMMENT '块大小',
  `latency` int(11) DEFAULT NULL COMMENT '产块和本地同步到块时间差',
  `signee_shares_issued` varchar(100) DEFAULT NULL COMMENT '保留参数',
  `signee_fees_collected` int(11) DEFAULT NULL COMMENT '块中统计的手续费',
  `signee_fees_destroyed` int(11) DEFAULT NULL COMMENT '块中销毁的手续费',
  `random_seed` varchar(100) DEFAULT NULL COMMENT '随机种子',
  `timestamp` varchar(50) DEFAULT NULL COMMENT '本地处理时间 YYYY-MM-DDTHH:mm:ss',
  `signee` varchar(50) DEFAULT NULL COMMENT '代理名',
  `amount` bigint(20) DEFAULT NULL COMMENT '区块交易金额',
  `fee` bigint(20) DEFAULT NULL COMMENT '区块交易手续费',
  `trans_count` bigint(20) DEFAULT NULL COMMENT '交易总数',
  PRIMARY KEY (`block_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区块信息表';

insert into `bc_block`(`block_num`,`previous_secret`,`delegate_signature`,`user_transaction_ids`,`id`,`block_size`,`latency`,`signee_shares_issued`,`signee_fees_collected`,`signee_fees_destroyed`,`random_seed`,`timestamp`,`signee`,`amount`,`fee`,`trans_count`) values
('680235','3c1139e4082c6d71ead9211b04381277fe5506d3','2067ad5d186cdc5e85c260f35cbb46135c730a2c1e54f09de11f100dbe340bae483e26245039bc849f9afcedf31dc786ab67b0df403f6ddcb35d1f32c2e5287a19','["e7dc2bab6cddeea73931b5784b981a5ecd3cc27b","e604f2dbb144c8e4cae7b061afaa18a96b5fc1b6","c710507cc855d52acbd43386ca14376f813f343f","8fff706fa63de51437461afb6b02c35c9e9f0eb9","8c8290c2009bbad3d1ce9554aa5da04bbd5be136","3bbdddf1a0292e6f258c831bfc9e01fd998f6a79","36b925b7b4f3fc9269f065e3e2b82a323e7ad8a3","351c60492f5bb1fe930cda017e2fe4e1dc55650f","0b1626562ee5e510c97bfce278311e511c55c9f7","07880919371a0ec79710fa748d015cf656e362fc"]','92f094cbc79a7c5262bdad961fb521cd49ca8523','1716','0','0','0','0','0000000000000000000000000000000000000000','2016-02-22T12:37:20','gop50',null,null,null);
DROP TABLE IF EXISTS  `bc_statis`;
CREATE TABLE `bc_statis` (
  `statis_key` varchar(50) NOT NULL,
  `statis_value` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`statis_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='统计表';

insert into `bc_statis`(`statis_key`,`statis_value`) values
('maxHourCount','0'),
('transAmount','0'),
('transAveHour','0'),
('transCount','0'),
('transFee','0');
DROP TABLE IF EXISTS  `bc_transaction`;
CREATE TABLE `bc_transaction` (
  `trx_id` varchar(50) NOT NULL COMMENT '交易id',
  `block_num` bigint(20) DEFAULT NULL COMMENT '区块数',
  `address1` varchar(100) DEFAULT NULL COMMENT '地址1',
  `address2` varchar(100) DEFAULT NULL COMMENT '地址2',
  `amount` bigint(20) DEFAULT NULL COMMENT '金额',
  `fee` bigint(20) DEFAULT NULL COMMENT '手续费',
  `timestamp` varchar(50) DEFAULT NULL COMMENT '区块本地处理时间',
  `memo` text COMMENT '备注',
  `signee` varchar(50) DEFAULT NULL COMMENT '代理名',
  PRIMARY KEY (`trx_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易信息表';

SET FOREIGN_KEY_CHECKS = 1;

