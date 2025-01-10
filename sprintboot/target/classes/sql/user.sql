CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
    `password` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
    `name` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
    `phone` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
    `email` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
    `address` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '详细地址',
    `avatar` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';