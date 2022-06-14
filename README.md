# Baseball Team
야구 팀 관리 어플리케이션

### 패키지 구조
~~~

main
├── Application.java
├── application
│   ├── PlayerDto.java
│   ├── PlayerFactory.java
│   ├── PlayerService.java
│   └── domain
│       ├── PlayerType.java
│       ├── Batter.java
│       ├── Pitcher.java
│       └── Player.java
├── exception
│   ├── InValidDataException.java
│   ├── WrongFileException.java
│   └── WrongTypeException.java
├── repository
│   └── BaseballDao.java
└── ui
    └── BaseballTeamController.java

~~~
ui
- Controller
- View 가 따로 없기 때문에 Controller 에서 View 역할도 함께 수행

application
- 도메인 관련 비즈니스 로직 수행

repository
- 데이터 접근 
- dao가 data access + db 역할을 모두 수행하고 있음 

### 기능
~~~
// 시작
[ 야구 팀 관리 ]
>> 파일을 불러옵니다.
>> 파일을 정상적으로 불러왔습니다.

// 파일 불러온 뒤 기능
[ 야구 팀 관리 ]
1. 전체보기
2. 이름으로 조회하기
3. 번호로 조회하기
4. 추가
5. 수정
6. 삭제
7. 파일에 저장
8. 종료
~~~