# schedule

![erd.PNG](erd.PNG)

![api.PNG](api.PNG)
아래 표는 사진에 다 담지 못한 내용들만 따로 정리해서 표로 만든 것 입니다.

등록 request | 등록 response                                                                                                                                                      |선택 조회 response |수정 response                                                                                            |
|---------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|---|
| 요청body <br>{<br> ”name”:“작성자명”, <br>”password”: “비밀번호”, ”to do”: “할 일” <br>} | 응답body <br> {<br> ”id”: “아이디값”,<br> ”name”:  “작성자명”,<br> ”to do”:  “할 일”, <br>”creationdate”: “작성일”, <br> ”modificationdate”:”수정일” <br>} |단건 일정 정보<br>{<br>”id”: “아이디값”,<br>”name”:  “작성자명”,<br>”to do”:  “할 일”,<br>”creationdate”:  “작성일”<br>”modificationdate”: ”수정일”<br>} |수정 정보 body<br>{<br>\+ request에서 수정 요청한 것들,<br>”creationdate”:  “작성일”,<br>”modificationdate”: “수정일”<br>} 



