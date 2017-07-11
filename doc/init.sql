SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS  `bc_block`;
CREATE TABLE `bc_block` (
  `block_num` bigint(20) NOT NULL COMMENT '�����',
  `previous_secret` varchar(100) DEFAULT NULL COMMENT '��һ�ֳ���ƾ֤',
  `delegate_signature` varchar(300) DEFAULT NULL COMMENT '����ǩ��',
  `user_transaction_ids` text COMMENT '���еĽ���id�б�',
  `id` varchar(100) DEFAULT NULL COMMENT '��hash',
  `block_size` int(11) DEFAULT NULL COMMENT '���С',
  `latency` int(11) DEFAULT NULL COMMENT '����ͱ���ͬ������ʱ���',
  `signee_shares_issued` varchar(100) DEFAULT NULL COMMENT '��������',
  `signee_fees_collected` int(11) DEFAULT NULL COMMENT '����ͳ�Ƶ�������',
  `signee_fees_destroyed` int(11) DEFAULT NULL COMMENT '�������ٵ�������',
  `random_seed` varchar(100) DEFAULT NULL COMMENT '�������',
  `timestamp` varchar(50) DEFAULT NULL COMMENT '���ش���ʱ�� YYYY-MM-DDTHH:mm:ss',
  `signee` varchar(50) DEFAULT NULL COMMENT '������',
  `amount` bigint(20) DEFAULT NULL COMMENT '���齻�׽��',
  `fee` bigint(20) DEFAULT NULL COMMENT '���齻��������',
  `trans_count` bigint(20) DEFAULT NULL COMMENT '��������',
  PRIMARY KEY (`block_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������Ϣ��';

insert into `bc_block`(`block_num`,`previous_secret`,`delegate_signature`,`user_transaction_ids`,`id`,`block_size`,`latency`,`signee_shares_issued`,`signee_fees_collected`,`signee_fees_destroyed`,`random_seed`,`timestamp`,`signee`,`amount`,`fee`,`trans_count`) values
('680235','3c1139e4082c6d71ead9211b04381277fe5506d3','2067ad5d186cdc5e85c260f35cbb46135c730a2c1e54f09de11f100dbe340bae483e26245039bc849f9afcedf31dc786ab67b0df403f6ddcb35d1f32c2e5287a19','["e7dc2bab6cddeea73931b5784b981a5ecd3cc27b","e604f2dbb144c8e4cae7b061afaa18a96b5fc1b6","c710507cc855d52acbd43386ca14376f813f343f","8fff706fa63de51437461afb6b02c35c9e9f0eb9","8c8290c2009bbad3d1ce9554aa5da04bbd5be136","3bbdddf1a0292e6f258c831bfc9e01fd998f6a79","36b925b7b4f3fc9269f065e3e2b82a323e7ad8a3","351c60492f5bb1fe930cda017e2fe4e1dc55650f","0b1626562ee5e510c97bfce278311e511c55c9f7","07880919371a0ec79710fa748d015cf656e362fc"]','92f094cbc79a7c5262bdad961fb521cd49ca8523','1716','0','0','0','0','0000000000000000000000000000000000000000','2016-02-22T12:37:20','gop50',null,null,null);
DROP TABLE IF EXISTS  `bc_statis`;
CREATE TABLE `bc_statis` (
  `statis_key` varchar(50) NOT NULL,
  `statis_value` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`statis_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ͳ�Ʊ�';

insert into `bc_statis`(`statis_key`,`statis_value`) values
('maxHourCount','0'),
('transAmount','0'),
('transAveHour','0'),
('transCount','0'),
('transFee','0');
DROP TABLE IF EXISTS  `bc_transaction`;
CREATE TABLE `bc_transaction` (
  `trx_id` varchar(50) NOT NULL COMMENT '����id',
  `block_num` bigint(20) DEFAULT NULL COMMENT '������',
  `address1` varchar(100) DEFAULT NULL COMMENT '��ַ1',
  `address2` varchar(100) DEFAULT NULL COMMENT '��ַ2',
  `amount` bigint(20) DEFAULT NULL COMMENT '���',
  `fee` bigint(20) DEFAULT NULL COMMENT '������',
  `timestamp` varchar(50) DEFAULT NULL COMMENT '���鱾�ش���ʱ��',
  `memo` text COMMENT '��ע',
  `signee` varchar(50) DEFAULT NULL COMMENT '������',
  PRIMARY KEY (`trx_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������Ϣ��';

SET FOREIGN_KEY_CHECKS = 1;

