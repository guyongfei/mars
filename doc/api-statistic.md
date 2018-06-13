<!-- toc -->

# 统计相关

## 1.平台情况(支持分页)

- url：/statistic/platform
- method:GET
  - startTime
  - endTime
- response body

 ```json
    {
      "message": "",
      "success": true,
      "data":{
        "projectAmount":20,
        "projectStatusAmount":[0,5,6,9,0],
        "userAmount":0,
        "txUseAmount":5,
        "getEthAmount":5,
        "dailyInfo":[
            {
                "activeProjectAmount":5,
                "activeUserAmount":5,
                "getEthAmount":5,
                "dayTime":15212345610000
            }
        ]
      }
    }

  ```

|字段|类型|是否必须|说明|
|---|---|---|---|
|projectAmount|number|是|项目总数|
|projectStatusAmount|arrary|是|按照项目状态分别统计的数量，顺序同项目状态表|
|userAmount|number|是|所有用户数|
|txUseAmount|number|是|所有发生过真实交易的用户数|
|getEthAmount|number|是|认筹到的ETH总数|
|dailyInfo|arrary|是|每日详情|

> dailyInfo 字段详解

|字段|类型|是否必须|说明|
|---|---|---|---|
|activeProjectAmount|number|是|认筹中的项目数|
|activeUserAmount|number|是|发生交易的用户数|
|getEthAmount|number|是|认筹到的ETH总数|
|dayTime|number|是|时间戳|

## 2.项目情况（支持分页）

- url：/statistic/project?action=daily|project
- method:GET

- action = daily 单日项目横向统计
  - request param
    - dayTime 日期
    - token 项目token
  - response body
    ```json
        {
        "message": "",
        "success": true,
        "data":{
            "projects":[
                {
                    "projectGid":"",
                    "token":"",
                    "price":"",
                    "getEthAmount":"",
                    "actualGetEthAmount":"",
                    "distributeAmount":"",
                    "actualDistributeAmount":"",
                    "txUserAmount":"",
                    "actualTxUserAmount":"",
                }
            ],
            "dayTime":1521234561000
        }
        }

    ```
- action = project 单个项目纵向统计
  - request param
    - startTime 日期(起)
    - startTime 日期(止)
    - token 项目token
  - response body
    ```json
        {
        "message": "",
        "success": true,
        "data":{
            "projectGid":"",
            "token":"",
            "dailyInfo":[
                {
                    "price":"",
                    "getEthAmount":"",
                    "actualGetEthAmount":"",
                    "distributeAmount":"",
                    "actualDistributeAmount":"",
                    "txUserAmount":"",
                    "actualTxUserAmount":"",
                    "dayTime":1521234561000
                }
            ]
        }
        }

    ```

> 字段详解

|字段|类型|是否必须|说明|
|---|---|---|---|
|projectGid|string|是|项目唯一标识|
|token|string|是|项目token|
|price|number|是|今日价格(ETH)|
|getEthAmount|number|是|今日认筹数量(ETH)|
|actualGetEthAmount|number|是|今日实际认筹数量(ETH)|
|txUserAmount|number|是|认筹人数|
|actualTxUserAmount|number|是|实际认筹人数|
|dayTime|number|是|时间戳|