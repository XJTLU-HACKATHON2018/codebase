
const express = require('express');
const router = express.Router();

router.get('/', function(req, res, next) {
    res.send(
        [
            [
                0,        // 系列索引
                [2220 + Math.random() * 100,
                 2302 + Math.random() * 100,
                 2287 + Math.random() * 100,
                 2362 + Math.random() * 100], // 新增数据
                false,     // 新增数据是否从队列头部插入
                false,     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
                "2018/1/2" // X axis of the data
            ],
            [
                0,
                [2320 + Math.random() * 100,
                 1902 + Math.random() * 100,
                 2087 + Math.random() * 100,
                 1862 + Math.random() * 100],
                false,
                false,
                "2018/1/3"
            ]
            
        ]    );
});

module.exports = router;

