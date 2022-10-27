/*
 Navicat Premium Data Transfer

 Source Server         : 175.24.228.204
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 175.24.228.204:3306
 Source Schema         : mc_server

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 27/10/2022 22:39:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bm_category
-- ----------------------------
DROP TABLE IF EXISTS `bm_category`;
CREATE TABLE `bm_category`  (
  `uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一标识符',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '板块名称',
  `image` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `del_flag` int(0) NULL DEFAULT 0 COMMENT '数据逻辑删除标记 0未删除 1 删除',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_category
-- ----------------------------
INSERT INTO `bm_category` VALUES ('8eca8e242a7b422dbea9d15192754cf8', '消耗品', '/img/health_pack.a346ae85.png', '在菲尼斯大陆活下去的必需品', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_category` VALUES ('a122c2225f4642a5ad15063589f09e64', '怪物', '/img/nocsy_drakonin_baby2.c56cf686.png', '你必须面对的梦魇', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');

-- ----------------------------
-- Table structure for bm_item
-- ----------------------------
DROP TABLE IF EXISTS `bm_item`;
CREATE TABLE `bm_item`  (
  `uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一标识符',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `category_uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '板块的UUID',
  `type_uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属种类的UUID',
  `image` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图片',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `attributes` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性',
  `consist` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组成',
  `region` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区域',
  `spoils` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '战利品',
  `del_flag` int(0) NULL DEFAULT 0 COMMENT '数据逻辑删除标记 0未删除 1 删除',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_item
-- ----------------------------
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('03fe7bc7646d48159ab67a74a5946bda', '僵尸蘑菇', 'a122c2225f4642a5ad15063589f09e64', '93341b88163646ff94819c5a0e26e9d8', '/img/monstermushroomzombie.3dd9000c.png', '被地脉阴气腐蚀的蘑菇怪，原本的生命气息转化为腐蚀生命的力量，要小心', NULL, NULL, '宴清村西部森林', '化生箓 森菇晶体 腐化孢囊 符灵木', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('0ea5a7ad893a45ebbd69cdc959873d7c', '绿色蘑菇', 'a122c2225f4642a5ad15063589f09e64', '93341b88163646ff94819c5a0e26e9d8', '/img/monstermushroomgreen1.2d9e5b31.png', '绿色的蘑菇怪，生活在森林中，讨厌破坏森林的人类', NULL, NULL, '宴清村西部森林', '生命孢子 森菇结晶 符灵木', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('15729d0290834974933fba69da9ea2b8', '哥布林野蛮人', 'a122c2225f4642a5ad15063589f09e64', 'e88ab1af01504009a93b04fa4044bb81', '/img/goblin.51d2c07a.png', '嚎哭洞穴中的野蛮生物 会大喊着向你冲过去', '', '', '宴清村嚎哭洞穴', '破布 ', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('15815341d94a4a7cb588c93d7759daef', '哥布林弓箭手', 'a122c2225f4642a5ad15063589f09e64', 'e88ab1af01504009a93b04fa4044bb81', '/img/goblinarcher.e9fe68e5.png', '嚎哭洞穴中阴冷狡诈的生物 躲在暗处朝你射箭', '', '', '宴清村嚎哭深渊', '箭羽 韧弦', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('61007d1c0e4b49479735cf4eab02f6d3', '哥布林萨满', 'a122c2225f4642a5ad15063589f09e64', 'e88ab1af01504009a93b04fa4044bb81', '/img/goblinshaman.5e857101.png', '信仰邪神的哥布林萨满，可以释放强力魔法攻击', NULL, NULL, '宴清村嚎哭洞穴', '红色结晶碎片     蓝色结晶碎片  白色结晶碎片  绿色结晶碎片  黑色结晶碎片', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('872b603f5cb24d73838c5f1646f272ab', '哥布林之王', 'a122c2225f4642a5ad15063589f09e64', 'e88ab1af01504009a93b04fa4044bb81', '/img/goblinking.d5915bea.png', '地穴哥布林群落的王', NULL, NULL, '宴清村嚎哭洞穴', '破碎王冠 棕色趾甲 黑树之根 加拉尔号角 暮龟甲壳 戈贡之眼', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('a98d9db95ea64bf99ad3aee109aba0b8', '蓝色蘑菇', 'a122c2225f4642a5ad15063589f09e64', '93341b88163646ff94819c5a0e26e9d8', '/img/monstermushroomblue.44dd11c4.png', '蓝色的蘑菇怪，生活在森林中，讨厌破坏森林的人类', '', '', '宴清村西部森林', '生命孢子 森菇结晶 符灵木', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('ca6e920acbec498ab9c5fc5fdf0db83f', '绿色蜗牛', 'a122c2225f4642a5ad15063589f09e64', 'e6422a72852f4a758dfca79e283a427a', '/img/greensnail.f32698d0.png', '森林里的蜗牛，行动起来并不像看起来那样缓慢', NULL, NULL, '宴清村东部森林', '坚硬蜗壳 粘液块 铁木', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('d67d4c7408b341ad83cb297d8003e849', '蜗牛爷爷', 'a122c2225f4642a5ad15063589f09e64', 'e6422a72852f4a758dfca79e283a427a', '/img/snailking.7fd903d5.png', '森林里的蜗牛长老，有个词怎么说来着？老而弥坚', NULL, NULL, '宴清村东部森林', '弥坚蜗壳 明障之玉 固化晶体 雷击木', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('d6b7a5f16304447c8999703607833391', '红色蜗牛', 'a122c2225f4642a5ad15063589f09e64', 'e6422a72852f4a758dfca79e283a427a', '/img/Redsnail.75eecb3a.png', '森林里的蜗牛，行动起来并不像看起来那样缓慢', NULL, NULL, '宴清村东部森林', '坚硬蜗壳 粘液块 铁木', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('e189b61daac84eb4963375182678018f', '蓝色胖胖菇', 'a122c2225f4642a5ad15063589f09e64', '93341b88163646ff94819c5a0e26e9d8', '/img/monstermushroom1.19d6ee34.png', '比一般蘑菇怪更强壮，也许是在潮湿的地方生长时间太长，和水元素有着微弱共鸣', NULL, NULL, '宴清村西部森林', '生命孢核 森菇晶石 蘑菇肉 符灵木', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('e37407b21e8c43d0bef99aa4474e90bc', '蓝色蜗牛', 'a122c2225f4642a5ad15063589f09e64', 'e6422a72852f4a758dfca79e283a427a', '/img/bluesnail.b4b94e86.png', '森林里的蜗牛，行动起来并不像看起来那样缓慢', NULL, NULL, '宴清村东部森林', '坚硬蜗壳 粘液块 铁木', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('e6ec24b99c3e46e9b8c4d388d3760d7b', '哥布林战士', 'a122c2225f4642a5ad15063589f09e64', 'e88ab1af01504009a93b04fa4044bb81', '/img/goblinsoldier.976142c4.png', '受到训练体格比一般哥布林强健的战士', '', '', '宴清村嚎哭洞穴', '甲片    哥布林头骨   哥布林獠牙   磨损皮革', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('e9e1af8ab526485eac37a9c3e6377add', '小偷菇', 'a122c2225f4642a5ad15063589f09e64', '93341b88163646ff94819c5a0e26e9d8', '/img/monsterexchangemushrrom.8422dd20.png', '受到风元素启迪智慧的蘑菇怪，喜欢到人类的村庄进行偷窃（或许你能在它身上找到村里失窃的物品，带回去会有奖励的）', NULL, NULL, '宴清村西部森林', '灵动孢囊 小偷菇的包袱 森菇晶体 符灵木', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_item`(`uuid`, `name`, `category_uuid`, `type_uuid`, `image`, `description`, `attributes`, `consist`, `region`, `spoils`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('f876c776fd58450da40fcf1bc5bce62f', '黄色胖胖菇', 'a122c2225f4642a5ad15063589f09e64', '93341b88163646ff94819c5a0e26e9d8', '/img/monstermushroom.1c7b12b4.png', '比一般蘑菇怪更强壮，也许是在潮湿的地方生长时间太长，和水元素有着微弱共鸣', NULL, NULL, '宴清村西部森林', '生命孢核 森菇晶石 蘑菇肉 符灵木', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');

-- ----------------------------
-- Table structure for bm_option
-- ----------------------------
DROP TABLE IF EXISTS `bm_option`;
CREATE TABLE `bm_option`  (
  `question_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识符',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `grade` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_option
-- ----------------------------
--INSERT INTO `bm_option` VALUES ('0e1daeb4d5bc47678124c37bfeea0749', 'a4cf407f13c845449cfca5a6fb1523bd', '测试A', 1);


-- ----------------------------
-- Table structure for bm_papi
-- ----------------------------
DROP TABLE IF EXISTS `bm_papi`;
CREATE TABLE `bm_papi`  (
  `papi` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '占位符 placeholderApi',
  `player` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '玩家',
  `value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '玩家信息',
  `del_flag` int(0) NULL DEFAULT 0 COMMENT '数据逻辑删除标记 0未删除 1 删除',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  UNIQUE INDEX `player`(`player`, `papi`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_papi
-- ----------------------------
INSERT INTO `bm_papi` VALUES ('player_name', 'tank_ice', 'tank_ice', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_papi` VALUES ('player_name', '404E', '404E', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_papi` VALUES ('player_name', 'kaiwater1203', 'kaiwater1203', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_papi` VALUES ('player_name', 'Ink_Dream', 'Ink_Dream', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_papi` VALUES ('player_name', 'inrhor', 'inrhor', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_papi` VALUES ('player_name', 'maomao', 'maomao', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');

-- ----------------------------
-- Table structure for bm_question
-- ----------------------------
DROP TABLE IF EXISTS `bm_question`;
CREATE TABLE `bm_question`  (
  `uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识符',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_question
-- ----------------------------
--INSERT INTO `bm_question` VALUES ('0e1daeb4d5bc47678124c37bfeea0749', '测试题目');


-- ----------------------------
-- Table structure for bm_type
-- ----------------------------
DROP TABLE IF EXISTS `bm_type`;
CREATE TABLE `bm_type`  (
  `uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一标识符',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '种类的名称',
  `category_uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属种类的uuid',
  `icon` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图标',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `del_flag` int(0) NULL DEFAULT 0 COMMENT '数据逻辑删除标记 0未删除 1 删除',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_type
-- ----------------------------
INSERT INTO `bm_type` VALUES ('127f76ce552043968f661cd2cc066364', '药水', '8eca8e242a7b422dbea9d15192754cf8', '', NULL, 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_type` VALUES ('5e787d8602b34fcfb536a5022504539d', '食物', '8eca8e242a7b422dbea9d15192754cf8', '', NULL, 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_type` VALUES ('93341b88163646ff94819c5a0e26e9d8', '森林蘑菇怪', 'a122c2225f4642a5ad15063589f09e64', '', '', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_type` VALUES ('e6422a72852f4a758dfca79e283a427a', '森林蜗牛怪', 'a122c2225f4642a5ad15063589f09e64', '', NULL, 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `bm_type` VALUES ('e88ab1af01504009a93b04fa4044bb81', '哥布林系列', 'a122c2225f4642a5ad15063589f09e64', '', '', 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `uuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'UUID',
  `account` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `isadmin` int(0) NULL DEFAULT 0 COMMENT '是否是管理员 0-不是， 1-是',
  `del_flag` int(0) NULL DEFAULT 0 COMMENT '数据逻辑删除标记 0未删除 1 删除',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', 'admin', 1, 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');
INSERT INTO `sys_user` VALUES ('cf71d0aff4dd4e1992709d94d6d2c037', 'tank_ice', '', 'tank:1998', 1, 0, 'admin', '2022-10-27 22:19:45', 'admin', '2022-10-27 22:19:45');

SET FOREIGN_KEY_CHECKS = 1;
