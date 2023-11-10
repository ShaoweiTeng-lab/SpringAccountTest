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

### <h2> 資料庫預設資料 (帳號密碼相同，使用 bcryptPasswordEncoder 加密) </h2>
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/84e86841-940a-4584-a3e6-9f1c2e28b17f)


## 功能展示(會員):

* <h2> 註冊會員 (新增功能) : </h2>
  
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/0ebed0d9-03c3-4a1f-b9f8-f47d56cd4582)

* <h2> 登入會員 拿到token :</h2>

![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/eb29d7cc-fb67-49f7-bc13-e07210050728)


* <h2> 查詢此帳戶資訊 (單筆查詢):</h2>

  1. <h3> 在 header 中帶入 token </h3> 
  
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/1d4313b2-9721-4542-8368-e892a27574ca)
  2. <h3> 取得此帳戶資訊</h3>
  
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/0df8a664-3139-4411-948d-387c196bfe48)
* <h2>會員修改密碼 (修改功能):</h2>

  1. <h3> 在 header 中帶入 token </h3> 
  
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/4d70d448-17d6-4432-bb4b-3c03fb88688f)
  2. <h3> 在RequestBody 設定密碼 </h3>
  
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/c9743916-3605-4cc6-a13b-3b38277f95d0)
  3. <h3> 再次登入 即可以新密碼登入</h3>
  
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/08424978-368b-4b29-a4b6-00486c0738c7)




## 功能展示(管理員):
* <h2>登入管理員 拿到token : </h2>
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/6c29616e-e7b8-4682-9d2c-53baafb14458)

* <h2>管理員查詢會員(關鍵字查詢、分頁查詢) : </h2> 

   1. <h3> 在 header 中帶入 token</h3>
      
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/84ef5806-db3e-4997-9776-37e8f507c5b3)
  
   2. <h3> 取得會員資訊</h3>
      * 可帶參數 : search (帳戶名稱，使用模糊查詢) 、page(頁數)、 order(排序類別) 、 size(回傳筆數) 、sort (降冪、 升冪 )

     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/78a0dd1e-19e8-4822-9d2b-310aaefb56e1)
 
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/a711a636-b91e-4ea9-a516-6c3626333908)


*  <h2> 管理員修改帳戶狀態(修改功能): </h2>  
  1. <h3> 在 header 中帶入 token</h3>
  
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/95334ba2-3927-4a37-9db8-5e4bdf6d4e3a)
     
  2. <h3> 設定帳戶狀態為 : 開啟/停權</h3>
  
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/8cda36b3-076a-4d5f-a639-974446a3f438)
     
* <h3> 管理員刪除帳戶(刪除功能):</h3>

    1. <h3> 在 header 中帶入 token</h3>
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/ababb7b9-568a-474f-8342-8b381dad3c7c)
  
    2. <h3> 刪除會員資訊</h3>
     ![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/30f7a493-95c0-4322-bd5d-eb7691a8b478)

     
## 異常處理
* <h3>密碼錯誤</h3>
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/4eaa81d4-5d50-4951-bff2-53122a205284)

*  <h3>無此帳戶</h3>
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/d0d8ffe1-ee50-4c1a-a286-759da64eaefe)
*  <h3>帳戶停權後 登入</h3>
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/548f77ab-18db-4855-b48e-25c364e5b72c)
*  <h3>會員使用token 呼叫管理員專用 api</h3>
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/f70b37cd-7306-4f6e-a47d-9b6573191000)
*  <h3>管理員刪除管理員</h3>
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/d497a7aa-3d35-42a8-bc5d-eac2173a5e33)
*  <h3>更改管理員帳號狀態</h3>
![image](https://github.com/ShaoweiTeng-lab/SpringAccountTest/assets/50354880/807e39d6-8c83-451e-ac82-75ba6d8a074f)



<!-- Markdown link & img dfn's -->
[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/yourname/yourproject/wiki
