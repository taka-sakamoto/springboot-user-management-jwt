# Spring Boot User Management & Reservation API (JWT Authentication)

Spring Boot を使用した **ユーザー管理・施設予約管理システム** です。  
JWT 認証、CRUD、予約ロジック、UI カスタムなど、実務を意識した構成で実装しています。

---

## 🚀 Features

- **JWT 認証**
  - ログイン / アクセストークン発行
  - 認証済みユーザーのみ API 利用可能

- **ユーザー管理**
  - ユーザー登録 / 更新 / 削除
  - 権限（Role）管理
  - ユーザー一覧表示

- **予約管理**
  - 予約作成 / 更新 / キャンセル
  - 予約ステータス管理（enum）
  - 施設ごとの予約一覧取得

- **施設管理**
  - 施設登録 / 更新 / 削除
  - 施設一覧取得

- **フロントエンド（静的ファイル）**
  - HTML / CSS / JavaScript
  - **カスタムラジオボタン UI（appearance: none）**
  - 予約一覧・ユーザー一覧画面

---

## 🛠 Tech Stack

| 分類 | 使用技術 |
|------|-----------|
| 言語 | Java 25 LTS |
| フレームワーク | Spring Boot 3.x |
| セキュリティ | Spring Security / JWT |
| データベース | H2 Database |
| ORM | Spring Data JPA |
| ビルド | Maven |
| フロント | HTML / CSS / JavaScript |
| 実行方法 | `mvn spring-boot:run` |

本プロジェクトでは最新の LTS である **Java 25** を採用し、  
将来の長期運用を見据えた構成としています。

---

## 🎨 Custom UI: Radio Button

標準のラジオボタンでは **間隔調整やデザイン変更が困難** なため、  
`appearance: none` を使用して **ネイティブ UI を無効化**し、  
CSS で完全にカスタムしたラジオボタンを実装しています。

```css
input[type="radio"] {
  appearance: none;
  width: 16px;
  height: 16px;
  border: 2px solid #333;
  border-radius: 50%;
  margin-right: 8px;
}

input[type="radio"]:checked {
  background-color: #333;
}
```

---

## 📁 Project Structure

```
reservation/
 ├── src/
 │   ├── main/
 │   │   ├── java/com/example/reservation/
 │   │   │   ├── controller/
 │   │   │   ├── service/
 │   │   │   ├── repository/
 │   │   │   ├── entity/
 │   │   │   ├── dto/
 │   │   │   └── config/
 │   │   ├── resources/
 │   │   │   ├── static/ (HTML/CSS/JS)
 │   │   │   └── application.properties
 │   └── test/
 ├── pom.xml
 └── README.md
```

---

## ▶ How to Run

```
git clone https://github.com/taka-sakamoto/springboot-user-management-jwt.git
cd springboot-user-management-jwt
mvn spring-boot:run
```

H2 Console:

```
http://localhost:8080/h2-console
```

---

## 📌 Future Improvements

- フロントエンドを React / Vue に置き換え
- 予約のバリデーション強化
- 管理者画面の追加
- Docker 対応

---

## 📄 License

This project is licensed under the MIT License.
