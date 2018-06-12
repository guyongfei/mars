<!-- toc -->

# 用户相关

## 1.获取邮箱验证码

- url : /verifycode/email?action=register|other & email={email}

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
      "success": true,
      "data":{
          "admin":true,
          "email":"",
          "managementPage":""
      }
    }
```

>data参数说明

|字段|类型|是否必须|说明|
|---|---|---|---|
|admin|boolean|是|是否是管理员|
|email|string|是|当前用户邮箱|
|managementPage|string|是|后台管理页面|

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

## 6.修改昵称

- url：/user/nickname
- method:post
- request body
    ```json
    {
       "nickname":""
    }
    ```
- response body

```json
{
  "message": "",
  "success": true
}

```

## 7.修改头像

- url：/user/avatar
- method:post
- request body
    ```json
    {
       "avatar":""
    }
    ```
- response body

```json
{
  "message": "",
  "success": true
}

```