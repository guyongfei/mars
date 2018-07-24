<!-- toc -->

# 用户相关


## 1.1 获取图片验证码

- url : /verify-code/img?imgToken=3e50fc7ee9ac4e65aaa3262431bf00a8

- method:get
- request param
  - imgToken = 32位uuid  
  
## 1.2 校验图片验证码

- url : /verify-code/img?imgToken=3e50fc7ee9ac4e65aaa3262431bf00a8 & imgVerifyCode=xxx

- method:post
- request param
  - imgToken = 32位uuid
  - imgVerifyCode = xxx
  
>校验失败后需再次获取新的校验图片；<br>
获取邮箱验证码时一并将成功的校验对发送后台；<br>
  
## 2.获取邮箱验证码

- url : /verify-code/email?action=register|other

- method:post
- request param
  - action = register :注册时用
  - action = other :其他场景
- request body
  - email ：用户的邮箱，用于收取验证码
  - imgToken: 注册时带，第1.1接口所带imgToken
  - imgVerifyCode: 注册时带，第1.2成功校验的图片验证码

- response body

  ```json
    {
      "message": "",
      "success": true
    }
  ```


## 3.注册

> 注册成功后，后台会直接处理登录,前端需通过请求 /user/user-info 接口来获取该用户的相关信息；
- url：/register
- method:post
- request body

  ```json
    {
      "email":"",
      "password":"",
      "verifyCode":"",
      "channel":""
    }
  ```
  >data参数说明
  
  |字段|类型|是否必须|说明|
  |---|---|---|---|
  |verifyCode|string|是|邮箱验证码|
  |channel|string|否|渠道号|

- response body

  ```json
    {
      "message": "",
      "success": true
    }
  ```

## 4.登录

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
|success|bool|是|是否登陆成功，当登陆成功后 token将会置于Cookie中|

## 5.登出

- url：/logout
- method:post
- response body

  ```json
    {
      "message": "",
      "success": true
    }
  ```

## 6.修改重置密码

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

## 7.获取用户信息（只争对本人）

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
|admin|boolean|是|是否是管理员|
|managementPage|string|否|后台管理页面|
