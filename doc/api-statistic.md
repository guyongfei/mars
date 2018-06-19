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
           "accountCount":5,
           "actualAccountCount":5,
           "addressCount":5,
           "actualAddressCount":5,
           "ethAmount":5,
           "actualEthAmount":5,
           "tokenAmount":5,
           "actualTokenAmount":5,
           "dayTime":1521234561000
         },
        "dailyInfo":[  {
           "accountCount":5,
           "actualAccountCount":5,
           "addressCount":5,
           "actualAddressCount":5,
           "ethAmount":5,
           "actualEthAmount":5,
           "tokenAmount":5,
           "actualTokenAmount":5,
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
|accountCount|number|是|账户数|
|actualAccountCount|number|是|实际账户数|
|addressCount|number|是|地址数|
|actualAddressCount|number|是|实际地址数|
|ethAmount|number|是|募集到的ETH数量|
|actualEthAmount|number|是|实际募集到的ETH数量|
|tokenAmount|number|是|需要分发的token数量|
|actualTokenAmount|number|是|实际需要分发的token数量|
|dayTime|number|是|统计时间戳|
