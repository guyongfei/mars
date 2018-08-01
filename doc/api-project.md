<!-- toc -->

# 项目相关

> projectStatus 项目状态

|代号|完整描述|前端状态|
|---|---|---|
|0|未开始|未开始|
|1|开始认筹还未到软顶|已开始|
|2|开始认筹还未到硬顶|-|
|3|认筹完成且成功|已完成|

## 1.获取项目列表

- url : /project/list
- method:get
- request param
  - projectName：项目名
  - projectToken：项目token
  - projectStatus：项目状态
- response body

    ```json
    {
        "message": "",
        "success": true,
        "data":{
            "pageNum":12,
            "pageSize":10,
            "total":20,
            "list":[
                {
                    "projectGid":"",
                    "projectStatus":"",
                    "projectName":"",
                    "projectToken":"",
                    "projectLogoLink":"",
                    "projectInstruction":"",
                    "startTime":"",
                    "endTime":""
                }
            ]
        }
    }
    ```

> data.list参数说明

|字段|类型|是否必须|说明|
|---|---|---|---|
|projectGid|string|是|项目唯一标识|
|projectStatus|number|是|项目状态|
|projectName|string|是|项目名称|
|projectToken|string|是|项目token|
|projectLogoLink|string|是|项目logo地址|
|projectInstruction|string|是|项目简介|
|startTime|date|是|开始时间|
|endTime|date|是|结束时间|

## 2.获取项目详情

- url : /project/{projectGid}
- method:get
- response body

    ```json
    {
        "message": "",
        "success": true,
        "data":{
            "projectGid":"",
            "projectStatus":1,
            "projectName":"",
            "projectToken":"",
            "projectAddress":"",
            "projectLogoLink":"",
            "projectImgLink":"",
            "projectInstruction":"",
            "projectContent":"",
            "webSites":
                {
                    "officialLink":"",
                    "whitePaperLink":"",
                    "facebook":"",
                    "twitter":"",
                    "biYong":"",
                    "gitHub":"",
                    "telegram":"",
                    "reddit":""
                },
            "priceRate":0.00,
            "freeGiveRate":0.1,
            "freeGiveEnd":1521234561000,
            "minPurchaseAmount":0.00,
            "maxPurchaseAmount":0.00,
            "soldAmount":3215456,
            "soldTokenAmount":3215456,
            "nextPriceInterval":32156420,
            "startTime":1523212345600,
            "endTime":1523212345600
        }
    }
    ```

> data参数说明

|字段|类型|是否必须|说明|
|---|---|---|---|
|projectGid|string|是|项目唯一标识|
|projectStatus|number|是|项目状态|
|projectName|string|是|项目名称|
|projectToken|string|是|项目token|
|projectAddress|string|是|项目方地址|
|projectLogoLink|string|是|项目logo地址|
|projectImgLink|string|是|项目大图地址|
|projectInstruction|string|是|项目简介|
|projectContent|string|是|项目详情|
|webSites|object|是|网站链接集合|
|priceRate|number|是|当前单价比（ETH:Token）|
|minPurchaseAmount|number|是|单笔最小认购数（ETH）|
|maxPurchaseAmount|number|是|单笔最大认购数（ETH）|
|soldAmount|number|是|已售折算Eth数量|
|soldTokenAmount|number|是|已售折算token数量|
|nextPriceInterval|number|是|距下一个价格开始时间的间隔，单位ms|
|startTime|number|是|开始时间，单位ms|
|endTime|number|是|结束时间，单位ms|
