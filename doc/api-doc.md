 <!-- toc -->

# API请求规范

## API定义

### [用户](api-user.md)

### [项目](api-project.md)

## 通用说明

### 接口地址

- 正式环境：
- 测试环境：
- 开发环境：

### 请求格式

> 登陆后，后台将 X-WITSHARE-TOKEN 置于cookie中 ，在涉及到鉴权的接口中会校验该token的合法性，如responseStatus=401表示需要重新登陆。

- get请求：直接在url后拼接参数

- post请求：使用json格式的字符串附在request body中传入，需要在header中指定 "Content-Type:application/json;charset=UTF-8"

### 响应格式

- json格式
- 统一示例：

``` json
  {
    "success": true,
    "message": "success",
    "data": object
  }
```

### 补充说明

- 除特别声明外，接口响应体中涉及日期时间的字段返回类型都是long（长整型），为1970-01-01 08:00:00至今的毫秒数。
