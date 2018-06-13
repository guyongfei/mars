 <!-- toc -->

# API请求规范

## 1.API定义

### [用户](api-user.md)

### [项目](api-project.md)

### [交易](api-transaction.md)

### [统计](api-statistic.md)

## 2.通用说明

### 2.1接口地址

- 正式环境：
- 测试环境：
- 开发环境：

### 2.2请求格式

- get请求：直接在url后拼接参数

- post请求：使用json格式的字符串附在request body中传入，需要在header中指定 "Content-Type:application/json;charset=UTF-8"

> cookie列表

|编号|名称|时效|描述|备注|
|---|---|---|---|---|
|1|witshare.user.token|-1|鉴权token，用来关联一个用户|登陆后在后台置入|
|2|witshare.i18n.language|-1|国际化,值为en、cn、ko、ja|前端置入，默认为en|

> 分页必带参数

- ascOrDesc：排序方式 （-1 - 倒序，1-正序）
- orderCondition：排序字段
- pageNum：页码
- pageSize：每页大小

### 2.3响应格式

- json格式
- 统一示例：

``` json
  {
    "success": true,
    "message": "success",
    "data": object
  }
```

> 后台返回responseStatus=401表示需要重新登陆

### 2.4补充说明

- 除特别声明外，接口响应体中涉及日期时间的字段返回类型都是long（长整型），为1970-01-01 08:00:00至今的毫秒数。
