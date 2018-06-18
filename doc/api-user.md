<!-- toc -->

# 用户相关

## 1.获取邮箱验证码

- url : /verify-code/email?action=register|other & email={email}

- method:get
- request body
  - action = register :注册时用
  - action = other :其他场景
  - email ：用户的邮箱，用于收取验证码
- response body

  ```json
    {
      "message": "",
      "success": true
    }
  ```

## 2.注册

> 注册成功后，后台会直接处理登录,前端需通过请求 /user/user-info 接口来获取该用户的相关信息；
- url：/register
- method:post
- request body

  ```json
    {
      "email":"",
      "password":"",
      "verifyCode":""
    }
  ```

- response body

  ```json
    {
      "message": "",
      "success": true
    }
  ```

## 3.登录

- url：/login
- method:post
- request body

  ```json
      {
        "email":"",
        "password":""
      }
  ```

- response body

  ```json
      {
        "message": "",
        "success": true
      }
  ```

> response说明

|字段|类型|是否必须|说明|
|---|---|---|---|
|success|bool|是|是否登陆成功，当登陆成功后 witshare_mars_token 将会置于Cookie中|

## 4.登出

- url：/logout
- method:post
- response body

  ```json
    {
      "message": "",
      "success": true
    }
  ```

## 4.修改重置密码

- url：/user/password?action=modify|reset
- method:post
- request body
  - action = modify:
      ```json
      {
        "originPassword":"",
        "password":""
      }
      ```
  - action = reset:
    ```json
          {
          "email":"",
          "password":"",
          "verifyCode":""
          }
      ```

- response body

  ```json
    {
      "message": "",
      "success": true
    }
  ```

## 5.获取用户信息（只争对本人）

- url：/user/user-info
- method:get
- response body

  ```json
    {
      "message": "",
      "success": true,
      "data":{
        "userGid":"",
        "email":"",
        "nickname":"",
        "headImgUrl":"",
        "projectNum":5,
        "sendEthAddress":"",
        "getTokenAddress":"",
        "admin":true,
        "managementPage":""
      }
    }

  ```

>data参数说明

|字段|类型|是否必须|说明|
|---|---|---|---|
|userGid|string|是|用户唯一标识|
|email|string|是|邮箱|
|nickname|string|是|昵称|
|headImgUrl|string|是|头像地址|
|projectNum|number|是|实际认购项目数|
|sendEthAddress|string|是|支付Eth地址|
|getTokenAddress|string|是|获取token地址|
|admin|boolean|是|是否是管理员|
|managementPage|string|否|后台管理页面|
