# Spring Boot User Management API with JWT Authentication

Spring Boot を使用したユーザー管理 API です。  
JWT 認証、ユーザー CRUD、予約管理、カスタム UI（ラジオボタン含む）など、  
実務を意識した構成で実装しています。

## Tech Stack
- Java 25
- Spring Boot 3.x
- Spring Security
- JWT (JSON Web Token)
- H2 Database
- Custom Form UI (CSS)

### Custom Radio Button UI
標準のラジオボタンでは間隔調整が困難なため、  
appearance: none を使用してネイティブ UI を無効化し、  
CSS で完全にカスタムしたラジオボタンを実装しています。
