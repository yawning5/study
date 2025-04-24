# ☕ Spring Backend Portfolio
내가 학습한 기술들을 직접 구현하고 배포한 성장형 실전 백엔드 프로젝트

학습내용 정리
https://www.notion.so/Study-1b4daed3c94981d5a271c81640307db0

웹 배포 기능 테스트
http://yawn-study-bucket.s3-website.ap-northeast-2.amazonaws.com/
<br/>
접속이 안 될경우 https 로 접속된걸 수도 있어 프로토콜 확인필요

Spring Version: 3.3.3
<br/>
Java: 17

---

## 🛠️ 사용 기술 스택

<p align="center">
  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"/>
  <img src="https://img.shields.io/badge/SpringJPA-007396?style=for-the-badge&logo=hibernate&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/GitHubActions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white"/>
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white"/>
  <img src="https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"/>
  <img src="https://img.shields.io/badge/EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"/>
  <img src="https://img.shields.io/badge/RDS-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white"/>
  <img src="https://img.shields.io/badge/S3-569A31?style=for-the-badge&logo=amazons3&logoColor=white"/>
</p>

---

## 📦 Spring 서버 배포 파이프라인

![Image](https://github.com/user-attachments/assets/fb8210c2-2408-4660-9068-730d8583732f)

---

## 🌐 클라이언트 - 서버 요청 흐름도

![Image](https://github.com/user-attachments/assets/b88b0ce3-6921-4f1c-a748-d9563a072104) 


---
<details>
<summary>🛠️ 실행에 필요한 환경변수 </summary>

| 환경변수 이름              | 설명                         | 예시값                            |
|--------------------------|------------------------------|----------------------------------|
| `LOCAL_USERNAME`         | 로컬 MySQL 사용자명             | `root`                           |
| `LOCAL_PASSWORD`         | 로컬 MySQL 비밀번호             | `1234`                           |
| `RDS_DB_NAME`            | RDS에서 사용할 DB 이름          | `myappdb`                        |
| `RDS_ENDPOINT`           | RDS 엔드포인트 주소             | `mydb.abc123.ap-northeast-2.rds.amazonaws.com` |
| `RDS_USERNAME`           | RDS 사용자명                   | `admin`                          |
| `RDS_PASSWORD`           | RDS 비밀번호                   | `yourStrongPassword`             |
| `SPRING_PROFILES_ACTIVE` | 스프링 프로파일 (local, prod 등) | `local`                          |

</details>

---

## API

<details>
<summary>🔐 로그인 API</summary>

### 🔐 로그인

| 항목 | 내용 |
| --- | --- |
| **Method** | POST |
| **URL** | `/login` |
| **설명** | 사용자 로그인 처리 후 JWT 토큰 반환 |
| **요청 헤더** | `Content-Type: application/json` |
| **요청 바디** | `{ "id": "test@example.com", "password": "1234" }` |
| **응답 바디** | `{ "accessToken": "...", "tokenType": "Bearer" }` |
| **응답 코드** | 200 OK |

### 🧪 로그인 테스트

| 항목 | 내용 |
| --- | --- |
| **Method** | GET |
| **URL** | `/login/test` |
| **설명** | JWT 인증된 유저의 닉네임을 반환함 |
| **요청 헤더** | `Authorization: Bearer <accessToken>` |
| **응답 바디** | `"testUser"` |
| **응답 코드** | 200 OK |

</details>

<details>
<summary>👤 회원 API</summary>

### 👤 회원가입

| 항목 | 내용 |
| --- | --- |
| **Method** | POST |
| **URL** | `/member` |
| **설명** | 새로운 사용자를 등록합니다 |
| **요청 헤더** | `Content-Type: application/json` |
| **요청 바디** | `{ "email": "user@email.com", "nickname": "닉네임", "password": "1234" }` |
| **응답 바디** | `{ "id": 1, "nickname": "닉네임" }` |
| **응답 코드** | `201 Created` + `Location: /member/{id}` |

### 🧑‍💼 마이페이지 조회

| 항목 | 내용 |
| --- | --- |
| **Method** | GET |
| **URL** | `/member/my` |
| **설명** | JWT 인증된 사용자의 마이페이지(작성 글/댓글 목록 포함)를 반환합니다 |
| **요청 헤더** | `Authorization: Bearer <accessToken>` |
| **응답 바디** | `{ "nickname": "닉네임", "email": "user@email.com", "boards": [...], "comments": [...] }` |
| **응답 코드** | `200 OK` |

</details>

<details>
<summary>📝 게시글 API</summary>

### 📝 게시글 작성

| 항목 | 내용 |
| --- | --- |
| **Method** | POST |
| **URL** | `/board` |
| **설명** | 인증된 사용자가 게시글을 작성함 |
| **요청 헤더** | `Authorization: Bearer <accessToken>`, `Content-Type: application/json` |
| **요청 바디** | `{ "title": "제목", "content": "내용" }` |
| **응답 바디** | `{ "id": 1, "title": "제목", "content": "내용", "nickname": "작성자", "email": "작성자이메일" }` |
| **응답 코드** | `201 Created` + `Location: /board/{id}` |

---

### 📄 게시글 단일 조회

| 항목 | 내용 |
| --- | --- |
| **Method** | GET |
| **URL** | `/board/{id}` |
| **설명** | 게시글 상세 내용 + 댓글 목록 반환 |
| **응답 바디** | `{ "id": 1, "title": "제목", "content": "내용", "nickname": "작성자", "comments": [...] }` |
| **응답 코드** | `200 OK` |

---

### 📚 게시글 목록 조회

| 항목 | 내용 |
| --- | --- |
| **Method** | GET |
| **URL** | `/board` |
| **설명** | 전체 게시글 목록 반환 (간략 정보만 포함) |
| **응답 바디** | `[{ "id": 1, "title": "제목", "nickname": "작성자", "commentCount": 3 }]` |
| **응답 코드** | `200 OK` |

---

### ✏ 게시글 수정

| 항목 | 내용 |
| --- | --- |
| **Method** | PATCH |
| **URL** | `/board/{id}` |
| **설명** | 로그인된 사용자가 본인의 게시글을 수정 |
| **요청 헤더** | `Authorization: Bearer <accessToken>` |
| **요청 바디** | `{ "title": "수정된 제목", "content": "수정된 내용" }` |
| **응답 바디** | `{ "id": 1, "title": "수정된 제목", "content": "수정된 내용", "nickname": "작성자", "email": "작성자이메일" }` |
| **응답 코드** | `200 OK` |

---

### ❌ 게시글 삭제

| 항목 | 내용 |
| --- | --- |
| **Method** | DELETE |
| **URL** | `/board/{id}` |
| **설명** | 로그인된 사용자가 본인의 게시글을 삭제함 |
| **요청 헤더** | `Authorization: Bearer <accessToken>` |
| **응답 바디** | 없음 |
| **응답 코드** | `204 No Content` |

</details>

<details>
<summary>💬 댓글 API</summary>

### 💬 댓글 작성

| 항목 | 내용 |
| --- | --- |
| **Method** | POST |
| **URL** | `/board/{boardId}/comment` |
| **설명** | 특정 게시글에 대한 댓글을 작성함 |
| **요청 헤더** | `Authorization: Bearer <accessToken>`, `Content-Type: application/json` |
| **요청 바디** | `{ "content": "댓글 내용입니다" }` |
| **응답 바디** | `{ "id": 1, "boardId": 1, "content": "댓글 내용입니다", "nickname": "작성자", "createdAt": "2025-04-24T11:00:00" }` |
| **응답 코드** | `201 Created` + `Location: /board/{boardId}/comment/{id}` |

---

### ✏ 댓글 수정

| 항목 | 내용 |
| --- | --- |
| **Method** | PATCH |
| **URL** | `/board/{boardId}/comment/{commentId}` |
| **설명** | 특정 댓글 내용을 수정함 |
| **요청 헤더** | `Authorization: Bearer <accessToken>` |
| **요청 바디** | `{ "content": "수정된 댓글 내용" }` |
| **응답 바디** | `{ "id": 1, "boardId": 1, "content": "수정된 댓글 내용", "nickname": "작성자", "createdAt": "..." }` |
| **응답 코드** | `200 OK` |

---

### ❌ 댓글 삭제

| 항목 | 내용 |
| --- | --- |
| **Method** | DELETE |
| **URL** | `/board/{boardId}/comment/{commentId}` |
| **설명** | 해당 사용자의 댓글을 삭제함 |
| **요청 헤더** | `Authorization: Bearer <accessToken>` |
| **응답 바디** | 없음 |
| **응답 코드** | `204 No Content` |

</details>
