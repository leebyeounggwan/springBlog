# springBlog
주특기 기본주차 과제


# 1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body) 
RequestBody 사용
  
  
# 2. 어떤 상황에 어떤 방식의 request를 써야하나요? 

## - path 파라미터
특정 리소스를 정의할 필요가 있을 때   ex)특정 id 나 이름을 가지고 조회

Path Variable 은 저 경로의 존재하는 페이지가 없으므로 404 에러를 발생합니다.
그에 비해 Query Parameter는 서버로 데이터가 넘어가고 쿼리를 날리며 해당하는 데이터가 없을 경우 따로 에러 핸들링을 해줘야합니다.


## - query 파라미터
정렬 혹은 필터링이 필요할 때 사용한다. 

path 

## - body
JSON 오브젝트를 전달할 때 사용됩니다.


# 3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
