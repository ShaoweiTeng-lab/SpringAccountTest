# SpringAccountTest
[![Build Status][travis-image]][travis-url]
[![Downloads Stats][npm-downloads]][npm-url]

## 專題說明:
<H2>奧爾資訊 面試前測驗</H2>





## 實作項目:
### * 帳號管理功能
### * 新增、修改、刪除、單筆查詢、列表查詢 
### * 額外功能 : 分頁查詢 
### * 角色 : 會員、管理員
### * 角色驗證方式 : Json Web Token (JWT)

## MySql Table:
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/984446bc-7888-4008-acb3-ceb2ea724618)
## 使用技術:
### * Spring boot 2.7.13
### * Spring boot Starter Security 2.7.13
### * mysql 8.0.33
### * Redis
### * Swagger (knife4j)

## 安裝方式:
   * 若有Docker 可參考 Docker版(以下可省略):https://github.com/ShaoweiTeng-lab/SpringAccountTest-Docker


## MySql 連接:
```sh
帳號:root
密碼:password
```
## Redis 連接:
```sh
host:localhost
password:無
port:6379
```

   1.Import sql :請至 src/main/resources/sql  找到spring_account_test_sql.sql  並使用 mysql work branch 匯入

![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/4bf7ade7-31ba-476e-aeee-0fcf02845cd0)


  2.確保Redis 有開啟

  3.進入build_jar 資料夾 ，打開命令提示字元 並輸入:

  ```sh
java -jar backend.jar
```
  
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/0c6853c1-37ba-4637-8d7a-30395b8f53fb)






### 測試方式:
 * Local測試網址: http://localhost:8080/doc.html#/home
 
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/132072cf-956a-4996-bf05-265aa990af3e)

### 資料庫預設資料 (帳號密碼相同，使用 bcryptPasswordEncoder 加密)
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/84e86841-940a-4584-a3e6-9f1c2e28b17f)


## 功能展示(會員):

* 註冊會員 (新增功能) :
  
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/0ebed0d9-03c3-4a1f-b9f8-f47d56cd4582)

* 登入會員 拿到token :

![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/eb29d7cc-fb67-49f7-bc13-e07210050728)


* 查詢此帳戶資訊 (單筆查詢):
  1. 在 header 中帶入 token
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/1d4313b2-9721-4542-8368-e892a27574ca)
  2. 取得此帳戶資訊
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/0df8a664-3139-4411-948d-387c196bfe48)
* 會員修改密碼 (修改功能):
  1. 在 header 中帶入 token
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/4d70d448-17d6-4432-bb4b-3c03fb88688f)
  2. 在RequestBody 設定密碼
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/c9743916-3605-4cc6-a13b-3b38277f95d0)
  3. 再次登入 即可以新密碼登入
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/08424978-368b-4b29-a4b6-00486c0738c7)




## 功能展示(管理員):
* 登入管理員 拿到token :
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/6c29616e-e7b8-4682-9d2c-53baafb14458)

* 會員修改密碼 (修改功能):  
  1. 在 header 中帶入 token
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/95334ba2-3927-4a37-9db8-5e4bdf6d4e3a)
     
  2.設定狀態為停權/開啟
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/8cda36b3-076a-4d5f-a639-974446a3f438)


<!-- Markdown link & img dfn's -->
[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/yourname/yourproject/wiki
