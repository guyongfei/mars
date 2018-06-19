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
           "txUserCount":5,
           "actualTxUserCount":5,
           "txAddressCount":5,
           "actualTxAddressCount":5,
           "getEthAmount":5.0,
           "actualGetEthAmount":5.0,
           "payTokenAmount":5.0,
           "actualPayTokenAmount":5.0,
           "dayTime":1521234561000
         },
        "dailyInfo":[  {
          "txUserCount":5,
          "actualTxUserCount":5,
          "txAddressCount":5,
          "actualTxAddressCount":5,
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
|txUserCount|number|是|交易用户数|
|actualTxUserCount|number|是|实际交易用户数|
|txAddressCount|number|是|交易地址数|
|actualTxAddressCount|number|是|实际交易地址数|
|getEthAmount|number|是|募集到的ETH数量|
|actualGetEthAmount|number|是|实际募集到的ETH数量|
|payTokenAmount|number|是|需要分发的token数量|
|actualPayTokenAmount|number|是|实际需要分发的token数量|
|dayTime|number|是|统计时间戳|
