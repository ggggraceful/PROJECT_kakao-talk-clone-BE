
# 카카오톡 클론코딩

-----
## 👩🏻‍💻🧑🏻‍💻 팀원

FE : 윤상민, 김휘린

BE : 윤시원, 남궁은, 강창식

-----
## 🛠️ BE 기술 스택

* Lombok
* MYSQL Driver
* Spring Web
* Spring Data Jpa
* Spring Security
* Validation
* H2 Database
* Web Socket
* AWS

-----
## 3) 기능

<details>
<summary>회원가입/로그인</summary>
<div markdown="1">

* 회원가입
  * 아이디, 비밀번호, 비밀번호 체크, 프로필 이미지, 상태 메세지 입력
  * 중복 회원 검사
  * 비밀번호 검사
* 로그인
  * 아이디, 비밀번호 입력
  * 회원이 아니거나 비밀번호가 틀릴 경우 로그인 실패 

</div>
</details>

<details>
<summary>프로필</summary>
<div markdown="1">

* 마이 프로필 수정
  * 프로필 이미지, 상태 메세지, 닉네임 변경 가능
  * 이미지를 변경하지 않을 경우 기존 프로필 이미지 유지
* 마이 프로필, 친구 프로필 조회
  * 내 프로필과 친구 프로필 상세 조회 기능

</div>
</details>

<details>
<summary>친구</summary>
<div markdown="1">

* 친구 추가
  * 검색된 친구 아이디로 친구 추가
  * 이미 추가된 친구일 경우 친구 추가 실패
* 친구 검색 
  * 친구 아이디로 친구 검색
  * 해당 아이디가 없을 경우 친구 검색 실패

</div>
</details>

<details>
<summary>채팅방 개설</summary>
<div markdown="1">

* 친구를 클릭하여 채팅방 개설

</div>
</details>

<details>
<summary>1:1 채팅</summary>
<div markdown="1">

* handshake 후, 양방향 통신이 되어 요청이 없더라도 서버에서 응답을 할 수 있도록 하여 메세지를 즉각적으로 주고 받을 수 있게 함

</div>
</details>

<details>
<summary>추가 사항(2차 목표)</summary>
<div markdown="1">

* 채팅방에서 사진 전송
* 채팅방 내 검색
* 단톡방
* 채팅 읽음 표시
* 채팅 알림

</div>
</details>

-----
## 🧲 ERD 설계
![image](https://user-images.githubusercontent.com/100689993/199663436-4ba49cdb-d019-432a-8c67-b7375b87d20f.png)

-----
# 🕹️ 회의 내용

<details>
<summary>양방향 통신이 가능한 범위</summary>
<div markdown="1">


철수와 영희가 카카오톡으로 채팅을 한다고 하자. 철수가 영희와의 카톡방에 접속해서 안녕? 이라는 메세지를 전송했다. 
하지만 영희는 카톡방에 접속해 있지 않았기 때문에 양방향 통신이 불가능한 상황이다.
두 명이 모두 채팅방에 접속해서 핸드셰이크를 마친 후에야 메세지를 주고 받을 수 있게 된다.

이러한 이슈를 해결하기 위해서 어떻게 해야할까 프런트엔드 분들과 고민을 했다.
채팅방에 접속을 해야 핸드셰이크를 하고 양방향 통신이 이루어지는게 아니라, 
로그인을 하고 나서부터 핸드셰이크를 하고 양방향 통신이 이루어진다면?

그럼 영희는 로그인되어있는 상태에서 별다른 요청을 하지 않더라도 
철수가 메세지를 보내게 되면 알람 기능을 구현해서 영희가 메세지가 왔다는 사실을 인지하게 만들 수 있다.

</div>
</details>

<details>
<summary>내가 속해있지 않은 채팅방 접속 이슈</summary>
<div markdown="1">


이 이슈 역시 프런트 분들과의 회의 중 나온 이슈이다. 지금 가지고 있는 자료로는 채팅방 목록이 모두 뜨고,
아무나 접근이 가능한 구조이기 때문에 카카오톡의 1:1 채팅, 단톡방 구조와는 다르다.
일단 채팅방 엔티티를 만들고 필드로 채팅방 고유아이디, 채팅방에 속해있는 유저 등을 db에 저장해 놓고, 
로그인한 유저가 속해있는 채팅방만 db로부터 조회해서 클라이언트로 리턴하면 쉽게 해결될 것 같았다.

</div>
</details>

<details>
<summary>프로필 수정 시 이미지를 변경하지 않을 경우</summary>
<div markdown="1">


프로필 이미지를 변경하지 않게 되면 null이 요청으로 들어와서 별다른 조치를 취하지 않는다면 NullPointerException이 뜨면서 에러가 나게 된다.
실제로 카톡에서는 이미지, 상태메세지, 닉네임 중 하나만 변경하더라도 기존 데이터가 그대로 적용되기 때문에 NullpointerException에 대한 처리를 따로 해줘야 한다.

</div>
</details>

-----
# 🔫 트러블 슈팅 

<details>
<summary>S3 RuntimeException Null</summary>
<div markdown="1">

로컬 환경에서는 잘 작동하는데, EC2 환경에서 배포했을 때 사진 업로드가 안되는 상황

-> git bash 로 /Pictures 폴더 생성하여 해결

</div>
</details>

<details>
<summary>마이페이지 수정 이슈</summary>
<div markdown="1">

마이페이지 수정 시, 이미지파일을 업로드하지 않았을 때 처리에 대한 고민

구현하려는 의도 : 원래 멤버가 가지고 있던 이미지 url을 가져온다.

</div>
</details>

<details>
<summary>multipartFile이 null일 경우 if문 처리</summary>
<div markdown="1">

그러나 확장자명이 .null로 적히는 문제가 발생했다.

multipartFile == null조건문에서, sout으로 "null"을 찍어봤는데 콘솔에 적히지 않았다 => multipartFile == null이 적용되지 않고있음

==null 말고 값이 비어있는 것을 확인할 수 있는 다른 방법을 찾아보았다.

</div>
</details>

<details>
<summary>multipartFile.isEmpty()사용</summary>
<div markdown="1">

multipartFile에서 지원하는 .isEmpty()메소드. 값이 비어있을 경우 true를 반환한다.

확장자 문제는 해결되었으나, 계속 빈 값의 파일을 등록할 시 imageUrl이 점점 길어지는 문제가 발생했다. (원래 있던 값이 리셋되지않고 계속 쌓임)

</div>
</details>

<details>
<summary>추가 수정 : update메소드 추가</summary>
<div markdown="1">

업데이트 시, image는 업데이트 하지 않는 메소드를 추가 (nickname과 status만 바꾸게함)

</div>
</details>

<details>
<summary>친구 추가 시 에러</summary>
<div markdown="1">

A유저가 B유저를 친구 추가하면 C유저는 B유저를 친구추가 하지 못하는 상황

-> friend DB에 해당 id가 있으면 추가되지 않음

-> findById 메소드쿼리를 findByMyUsernameAndId 로 변경

</div>
</details>

<details>
<summary>Bean이 정의되지 않은 오류</summary>
<div markdown="1">

실행시 인텔리제이에 UnsatisfiedDependencyException 오류가 떴다. 

다음 에러 메세지는 검색을 해보니 Bean이 정의되지 않아서 파일에서 Bean을 만드는데 오류가 발생했다는 이유였다.

보통 어노테이션을 안붙인 경우 발생하는 에러라고 하는데, 다 잘 정의되어있었다.

찾아보니 application.yml 파일이 비어있었다.

아마 .gitignore 파일에 yml파일을 추가해 놓았는데,

커밋과 풀을 계속 반복하면서 파일내용이 사라진것으로 파악했다.

yml파일 내용을 다시 불러와 실행시키니까 에러가 사라졌다!

</div>
</details>

<details>
<summary>ERD 설계 시 연관 관계</summary>
<div markdown="1">

백엔드 팀원분들과 ERD 설계를 하면서 연관관계에 대한 고민이 많았다. 먼저 테이블이 몇 개가 될지 생각해 봤다. 
처음에는 멤버, 토큰, 친구, 채팅방, 메세지 이렇게 5개였다. 그리고 연관관계도 아래 그림처럼 매우 복잡했었다.

![image](https://user-images.githubusercontent.com/100689993/199663913-fbd30604-8bbd-462f-a475-5674f46c1ff2.png)

하지만 박은진 매니저님의 피드백을 따라 불필요한 연관 관계가 없는지에 대해 생각해 보았다.
생각해 보면 저번주차에서도 박은진 매니저 님이 댓글과 게시물의 연관관계에 대해 다시 생각해 보라고 하셨고,
댓글 필드에 게시물 id를 넣어줌으로써 해당 댓글이 어떤 게시물의 댓글인지 파악할 수 있도록 하여 연관 관계를 끊어냈었다.
이번에도 이러한 방식을 적용해 보려고 모든 연관관계를 끊어내고 각각의 필드에 연관이 있는 엔티티의 아이디값을 넣어줬다.

![image](https://user-images.githubusercontent.com/100689993/199664410-c63ed9d2-e86a-4197-8b95-e8a8e5d636f8.png)

위의 코드는 친구와 멤버 사이의 연관관계를 끊어내고 그 대신, 멤버가 맺은 친구들의 아이디 값을 리스트 형태로 저장하려고 했었다.
하지만 똑똑한 인텔리제이가 잘못된 코드라는걸 알려 주었고, 구글링을 해보니 연관관계를 맺거나 @Embaded 라는 어노테이션을 사용하지 않는 이상
엔티티 내에 주소값을 갖는 형태의 변수를 필드로 쓸 수 없다는 사실을 알게 되었다.

하지만 이 역시도 반쪽짜리 정답이였고, 박은진 매니저님의 순회시간에 @Converter 라는 어노테이션에 대해 공부해보라고 하셨다.
구글링을 해본 결과, @Convert 라는 어노테이션을 필드에 붙이면 데이터를 변환해서 데이터베이스에 저장이 가능하다고 한다.
예를 들어 boolean 타입의 필드일 경우 false or true 값을 0 or 1의 값으로 변환해서 데이터베이스에 저장이 가능하다.
일단 @Convert 를 붙여줌으로써 에러는 사라졌지만 List 형태의 데이터는 어떻게 변환이 되는지 아직까지는 파악이 되지 않고, 더 공부해봐야 할 것 같다.

![image](https://user-images.githubusercontent.com/100689993/199665712-87ab294f-8902-4fbd-9528-e6bd8b9d8dff.png)

</div>
</details>

<details>
<summary>이미지를 요청으로 받을 때 NullPointerException</summary>
<div markdown="1">

지금껏 프로젝트르 하면서 한 번도 S3을 이용하여 이미지를 요청받는 api를 구현해 본 적이 없기 때문에 백엔드 팀원분들께 자신있게 내가 해보겠다고 했는데 
그 순간이 너무나도 후회가 된다. 어느정도 구현까지는 완료가 되었고, 실행이 되는 것도 눈으로 학인했는데
코드를 조금씩 수정하다가 어느순간 갑자기 에러가 나서 삽질을 하느라 시간을 너무 많이 잡아먹었고, 
결국 웹소켓을 구현할 시간이 부족해져서 프로젝트가 미완성으로 끝이 나버렸고, 팀원분들께도 피해를 드린 것 같다.
결국엔 시원님 브랜치에서는 잘 작동하는 것 같아서, 클론을 해서 그 위에서 다시 시작하게 되었다.
아직까지도 원인을 알 수가 없어서 아쉽다.

</div>
</details>

<details>
<summary>단톡방 개설 시 매개변수</summary>
<div markdown="1">

단톡방을 개설할 때 초대하고 싶은 친구를 고른 후, 방을 개설하는 형태인데 사용자가 친구를 몇 명 초대할지 모르는 경우, 즉 매개변수의 개수를 모를 경우에 대해
어떻게 해야하는지에 대해 고민해봤다. 그런데 이 고민은 프런트 쪽을 아무것도 모르기 때문에 이런 고민을 하게 되었던 것 같다. 그냥 매개변수로 List를 받으면 되는데
프런트에서 매개변수를 리스트 형태로 전달해 줄 수 있는 지에 대해 전혀 생각을 하지 못하고 있었다. 고민을 오래했던 것보다 간단하게 풀려서 허탈했다.

</div>
</details>

<details>
<summary>웹소켓 적용 실패</summary>
<div markdown="1">

웹소켓 이론에 대해서는 구글링을 하도 많이 해서 조금은 알 것도 같다. 양방향 통신 전 클라이언트에서 GET 요청으로 handshake를 요청한다. 
handshake를 위한 세션이 발급되고 그 세션을 통해 서로 양방향 통신을 하겠다는 수락으로 받아들여 양방향 통신이 시작되면 
상태코드 200이 아닌 101코드가 뜨면서 헤더에 HTTP 프로토콜이 아닌 Web Socket 프로토콜로 업그레이드 되었다는 데이터가 응답으로 온다.
이렇게 되면 양방향 통신이 시작되고 stomp의 메세지 브로커와 subscriber, publisher 개념이 적용되어 pub이 특정 url로 매세지를 보내면 메세지 브로커가 특정 url을
구독한 구독자들에게 메세지를 응답해준다. 서버로의 요청이 없더라도 서버가 응답을 해주는 방식이고, HTTP 방식과 달리 헤더에 데이터를 담아 보내지 않기 때문에 가벼운 특징이 있다.

이론상으로 아는 것과 달리 코드를 적용하는 것은 또 다른 문제였다. 일단 handshake를 통해 양방향 통신을 하는 것까지는 구현이 되었는데 메세지를 보내고 받는 방식이 
어떻게 이루어지는지, 우리가 어떤 방식으로 클라이언트에게 응답해야 하는지 혼동이 와서 결국 마무리를 짓지 못하게 되었다. 아쉽지만 수료한 이 후에 꼭 웹소켓 관련 토이프로젝트를 해서 이번주차의 치욕을 씻고 싶다.

<details>
<summary>API 명세 기반 설계 및 프런트 엔드 개발자와의 끊임없는 소통(제일 큰 교훈)</summary>
<div markdown="1">

미니 프로젝트 전 API 명세에 대한 중요성을 머리속에 되내이고 되내었는데도 코드를 작성하다보니 임의로 내 방식 대로 작성해서 프런트 분들과 혼선이 빚어지는 경우가 너무나도 많았다. 백번 양보했다 치고 코드를 작성하다 보면 api 명세대로 안되는 경우가 있다고 하더라도 프런트 분들과 상의 후에 작성을 해야 했는데 그렇게 하지 못했다. 협업 개발자로서 이번주차의 나는 0점이였던 것 같고, 오늘을 교훈삼아서 api 명세서를 꼭 지키고, 협업할 개발자들과 소통을 자주 해야겠다. 안녕

</div>
</details>





## 4) 




# 5. COMMIT MESSAGE CONVENTION
----------------------------------
https://blog.ull.im/engineering/2019/03/10/logs-on-git.html
