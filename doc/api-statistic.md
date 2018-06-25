<!-- toc -->

# 统计相关

## 1.项目统计信息

- url：/statistic/project
- method:GET
  - projectGid 项目唯一标识和token必选其一
  - projectToken 
  - startTime 用于分页区间查询
  - endTime
- response body

 ```json
    {
      "message": "",
      "success": true,
      "data":{
        "projectGid":"",
        "projectToken":"",
        "summaryInfo":{
            "userCount":5,
            "actualUserCount":5,
            "txCount":5,
            "actualTxCount":5,
            "getEthAmount":5.0,
            "actualGetEthAmount":5.0,
            "payTokenAmount":5.0,
            "actualPayTokenAmount":5.0,
            "dayTime":1521234561000
         },
        "dailyInfo":[  {
            "userCount":5,
            "actualUserCount":5,
            "txCount":5,
            "actualTxCount":5,
            "getEthAmount":5.0,
            "actualGetEthAmount":5.0,
            "payTokenAmount":5.0,
            "actualPayTokenAmount":5.0,
            "dayTime":1521234561000
        }
        ]
      }
    }

  ```

|字段|类型|是否必须|说明|
|---|---|---|---|
|projectGid|string|是|项目唯一标识|
|projectToken|string|是|项目token|
|summaryInfo|object|是|汇总信息|
|dailyInfo|arrary|是|每日详情|

>  字段详解

|字段|类型|是否必须|说明|
|---|---|---|---|
|userCount|number|是|用户数量|
|actualUserCount|number|是|有效用户|
|txCount|number|是|申请数量|
|actualTxCount|number|是|有效申请|
|getEthAmount|number|是|ETH数量|
|actualGetEthAmount|number|是|有效ETH|
|payTokenAmount|number|是|Token数量|
|actualPayTokenAmount|number|是|有效Token|
|dayTime|number|是|统计时间戳|
