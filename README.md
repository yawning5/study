# ☕ Spring Backend Portfolio
내가 학습한 기술들을 직접 구현하고 배포한 실전 백엔드 프로젝트

학습내용 정리
https://www.notion.so/Study-1b4daed3c94981d5a271c81640307db0

웹 배포 기능 테스트
http://yawn-study-bucket.s3-website.ap-northeast-2.amazonaws.com/

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

