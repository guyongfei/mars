
<!-- toc -->

# 交易相关

> payCoinType 购买支付币种类型

|代号|类型|是否支持|
|---|---|---|
|0|ETH|是|
|1|BTC|否|

> userTxStatus 认筹交易状态

|代号|描述|备注|
|---|---|---|
|0|初始状态||
|1|认筹成功||
|2|认筹成功但数量不符||
|3|认筹失败（交易号不存在）||
|4|认筹失败（打币失败）||

> platformTxStatus 打币验证状态

|代号|描述|备注|
|---|---|---|
|0|初始状态||
|1|校验成功||
|2|校验失败||

## 1.提交交易

- url：/transaction
- method:post
- request body

  ```json
  {
    "token":"SLN",
    "payAmount":123.45,
    "payCoinType":0,
    "payTx":"0xdc9f30b716597999eafa0cabaa0b33423845e2f13d4c30d845018d4cf7bad959",
    "hopeGetAmount":12354.123,
    "userAddress":"0x01a52D062B7425E6D60ed1f553c4E743136a8F07"
  }

  ```

- response body

  ```json
    {
      "message": "",
      "success": true
    }
  ```

> 请求字段详解

|字段|类型|是否必须|说明|
|---|---|---|---|
|token|string|是|认筹项目token|
|payAmount|number|是|支付数量|
|payCoinType|number|是|支付类型|
|payTx|number|是|交易号|
|hopeGetAmount|number|是|期望得到的token数量|
|userAddress|string|是|用户地址|

## 2.个人交易列表

> 只能查看自己的（通过cookie）

- url：/transactions
- method:get
- request param
  - payTx
  - projectStatus
  - userTxStatus
  - startTime 交易时间（起）
  - endTime 交易时间（至）
- response body

  ```json
    {
      "message": "",
      "success": true,
      "data":{
        "token":"",
        "payTx":"",
        "projectStatus":0,
        "userTxStatus":5,
        "createTime":1521234561000
      }
    }

  ```

>data参数说明

|字段|类型|是否必须|说明|
|---|---|---|---|
|token|string|是|项目token|
|payTx|string|是|认筹交易号|
|projectStatus|string|是|项目状态|
|userTxStatus|string|是|认筹交易状态|
|createTime|number|是|认筹时间|

## 3.交易详情

> 只能查看自己的（通过cookie）

- url：/transaction/{payTx}
- method:get
- request param
  - payTx 认筹交易号
- response body

  ```json
    {
      "message": "",
      "success": true,
      "data":{
        "token":"",
        "payTx":"",
        "projectStatus":0,
        "userTxStatus":5,
        "platformTxStatus":2,
        "userAddress":"",
        "payCoinType":"",
        "payAmount":12,
        "actualPayAmount":123,
        "price":123,
        "hopeGetAmount":123,
        "actualGetAmount":123,
        "createTime":1521234561000,
        "distribution":1521234561000
      }
    }

  ```

>data参数说明

|字段|类型|是否必须|说明|
|---|---|---|---|
|token|string|是|项目token|
|payTx|string|是|认筹交易号|
|projectStatus|number|是|项目状态|
|userTxStatus|number|是|认筹交易状态|
|userAddress|string|是|用户地址|
|payCoinType|number|是|购买币种类型|
|payAmount|number|是|支付数量（ETH）|
|actualPayAmount|number|是|实际支付数量（ETH）|
|price|number|是|购买时token单价（ETH）|
|hopeGetAmount|number|是|期望得到的token数量|
|actualPayAmount|number|是|实际能得到的数量|
|createTime|number|是|认筹时间|
|platformTxStatus|number|否|打币校验状态|
|distribution|number|否|打币时间|