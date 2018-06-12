<!-- toc -->

## 1.获取项目列表
- url : /project/list
- method:get
- request param
    - ascOrDesc  ：排序方式 1-正序，-1 - 倒序
    - orderCondition：排序字段 项目名排序，修改日
    - pageNum ：页码
    - pageSize ：每页大小
    - projectName ：项目名
    - projectToken ：项目token
    - pageNum ：用户的邮箱，用于收取验证码
    - pageNum ：用户的邮箱，用于收取验证码
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
>data.list参数说明

|字段|类型|是否必须|说明|
|---|---|---|---|
|projectGid|string|是|项目唯一标识|
|projectStatus|number|是|项目状态(0-未开始 1-开始认筹还未到软顶 2-开始认筹还未到硬顶 3-认筹完成且成功 4-认筹完成但是失败)|
|projectName|string|是|项目名称|
|projectToken|string|是|项目token|
|projectLogoLink|string|是|项目logo地址|
|projectInstruction|string|是|项目简介|
|startTime|date|是|开始时间|
|endTime|date|是|结束时间|




## 1.获取项目详情
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
         "projectLogoLink":"",
         "projectImgLink":"",
         "projectInstruction":"",
         "projectContent":"",
         "officialLink":"",
         "whitePaperLink":"",
         "socialList":[
             {
                  "linkName":"",
                  "linkUrl":""
             }
         ],
         "price":0.00,
         "soldAmount":"",
         "nextPriceInterval":"",
         "startTime":"",
         "endTime":""
      }
 }
```
>data参数说明

|字段|类型|是否必须|说明|
|---|---|---|---|
|projectGid|string|是|项目唯一标识|
|projectStatus|number|是|项目状态(0-未开始 1-开始认筹还未到软顶 2-开始认筹还未到硬顶 3-认筹完成且成功 4-认筹完成但是失败)|
|projectName|string|是|项目名称|
|projectToken|string|是|项目token|
|projectLogoLink|string|是|项目logo地址|
|projectImgLink|string|是|项目大图地址|
|projectInstruction|string|是|项目简介|
|projectContent|string|是|项目详情|
|officialLink|string|是|官网链接|
|whitePaperLink|string|是|白皮书连接|
|socialList|array|是|社交网站链接|
|price|number|是|当前价格（ETH）|
|soldAmount|number|是|已售数量|
|nextPriceInterval|number|是|距下一个价格开始时间的间隔，单位ms|
|startTime|string|是|开始时间|
|endTime|string|是|结束时间|




