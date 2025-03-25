# schedule

![erd.PNG](erd.PNG)

![api.PNG](api.PNG)

아래 표는 사진에 다 담지 못한 내용들만 따로 정리해서 표로 만든 것 입니다.

|등록 request | 등록 response  | 선택 조회 response   | 수정 request                                                                 |수정 response   | 삭제 request                             |                                                                                        
|-----       |--------        |--------------      |----------------------------------------------------------------------------| ---             |----------------------------------------|
| 요청body <br>{<br> ”name”:“작성자명”, <br>”password”: “비밀번호”, ”to do”: “할 일” <br>} | 응답 body <br> {<br> ”id”: “아이디값”,<br> ”name”:  “작성자명”, <br> ”to do”:  “할 일”, <br>”creationdate”: “작성일”, <br> ”modificationdate”:”수정일” <br>} | 응답 body<br>{<br>”id”: “아이디값”,<br>”name”:  “작성자명”,<br>”to do”:  “할 일”,<br>”creationdate”:  “작성일”<br>”modificationdate”: ”수정일”<br>} | 요청 param, <br>요청 body<br>{ <br>”password”: “비밀번호”,<br> ”name”: ”작성자명”,<br> ”todo”: “할 일”<br> } |응답 body<br>{<br>”id”: “아이디값”,<br>”name”:  “작성자명”,<br>”to do”:  “할 일”,<br>”creationdate”:  “작성일”<br>”modificationdate”: ”수정일”<br>}| 요청 param, <br>요청 body<br>{ <br>”password”: 비밀번호” <br>} | 



