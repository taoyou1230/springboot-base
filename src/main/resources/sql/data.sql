-- 添加活动表数据
INSERT INTO activity(id, name, descr, company_name, created_at, updated_at) SELECT '1', '初始活动', '初始实例', 'factory', '2019-02-20 17:44:07', '2019-02-26 09:17:23' FROM dual WHERE not EXISTS (select 1 from activity);
-- 添加活动-机器关联表数据
INSERT INTO ad_machine(imei, name, activity_id, ad_machine_type, created_at, updated_at) SELECT '1', '实例活动-机器关联', '1', 'NEW_ADD', '2019-02-21 10:21:04', '2019-02-21 10:21:04' FROM dual WHERE not EXISTS (select 1 from ad_machine);
-- 添加广告位置数据
INSERT INTO ad_position(id, name, descr, created_at, updated_at) SELECT '1', '初始广告位置', '初始广告位置实例', '2019-02-20 14:38:06', '2019-02-20 14:38:09' FROM dual WHERE not EXISTS (select 1 from ad_position);
-- 添加活动素材数据
INSERT INTO material(id, name, descr, type, url, activity_id, ad_position_id, created_at, updated_at) SELECT '1', '初始素材', '初始素材实例', 'PIC', 'http://', '1', '1', '2019-02-21 15:09:36', '2019-02-21 15:09:36'  FROM dual WHERE not EXISTS (select 1 from material);
