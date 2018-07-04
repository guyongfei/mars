
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
|0|未校验||
|1|交易还未被打包||
|2|验证成功||
|21|验证失败（to不是平台地址)||
|22|验证失败（from不匹配）||
|23|验证失败（金额不匹配）||
|3|交易失败||
|4|交易不存在||

> platformTxStatus 打币验证状态

|代号|描述|备注|
|---|---|---|
|0|未打币||
|1|打币中||
|2|成功||
|3|失败||

## 1.查询用户项目交易信息

- url：/transaction/info
- method:get
- request param
  - projectGid 项目唯一标识
- response body

  ```json
    {
      "message": "",
      "success": true,
      "data":  {
        "projectGid":"",
        "projectToken":"",
        "payEthAddress":"",
        "getTokenAddress":"",
        "priceRate":5.0,
        "platformAddress":"",
        "minPurchaseAmount":5.0,
        "txCount":1,
        "txCountLimit":false,
        "gasPrice": {
               "gasPrice": 1000000000,
               "gasPriceGWei": "1gwei",
               "ethGasLimit": 21000
             }
       }
    }
  ```

> data字段详解

|字段|类型|是否必须|说明|
|---|---|---|---|
|projectGid|string|是|项目唯一标识|
|payEthAddress|string|是|支付Eth地址|
|getTokenAddress|string|是|获取token地址|
|priceRate|number|是|eth:token的价格比|
|platformAddress|string|是|项目平台地址|
|minPurchaseAmount|number|是|最低购买Eth数量|
|txCount|number|是|购买次数|
|txCountLimit|boolean|是|是否交易数量限制，如true表示达到交易次数上限不能再次交易|
|gasPrice|object|是|gas价格|

> gasPrice 字段详解

| 字段         | 类型   |是否必须| 说明    |
| ------------ | ------ |---| ---------------------- |
| gasPrice     | number |是|当前gasPrice，单位wei  |
| gasPriceGWei | string |是| 当前gasPrice，单位gwei |
| ethGasLimit  | number |是| ETH转账默认的gasLimit  |


## 2.设置用户交易地址

- url：/transaction/user-address
- method:post
- request body

  ```json
  {
    "projectGid":"",
    "payEthAddress":"",
    "getTokenAddress":""
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
|projectGid|string|是|项目唯一标识|
|payEthAddress|string|是|支付Eth地址|
|getTokenAddress|string|是|获取token地址|


## 3.提交交易

- url：/transaction
- method:post
- request body

  ```json
  {
    "projectGid":"",
    "priceRate":200.0,
    "payAmount":2.0,
    "payCoinType":0,
    "payTx":"0xdc9f30b716597999eafa0cabaa0b33423845e2f13d4c30d845018d4cf7bad959",
    "hopeGetAmount":400
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
|projectGid|string|是|认筹项目唯一标识|
|priceRate|number|是|当前eth:token的价格比|
|payAmount|number|是|支付数量|
|payCoinType|number|是|支付类型|
|payTx|number|是|交易号|
|hopeGetAmount|number|是|期望得到的token数量|

## 4.个人交易列表

> 只能查看自己的（通过cookie）

- url：/transactions
- method:get
- request param
  - projectGid
- response body

  ```json
    {
      "message": "",
      "success": true,
      "data":{
        "projectGid":"",
        "token":"",
        "payTx":"",
        "payTxId":"",
        "payAmount":"",
        "priceRate":"",
        "hopeGetAmount":"",
        "userTxStatus":5,
        "createTime":1521234561000
      }
    }

  ```

>data参数说明

|字段|类型|是否必须|说明|
|---|---|---|---|
|projectGid|string|是|项目唯一标识|
|token|string|是|项目token|
|payTx|string|是|认筹交易号|
|payTxId|string|是|购买交易ID|
|payAmount|number|是|支付eth数额|
|priceRate|number|是|eth:token的价格比|
|hopeGetAmount|number|是|希望获得eth数额|
|userTxStatus|number|是|认筹交易状态|
|createTime|number|是|认筹时间|

## 5.交易详情

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
        "projectGid":"",
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
|projectGid|string|是|项目唯一标识|
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