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
### * Swagger

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
 


<!-- Markdown link & img dfn's -->
[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/yourname/yourproject/wiki
