<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX 실습</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="crossorigin="anonymous">
</script> <!-- jQuery 안에 ajax가 있으므로 CDN방식으로 jQuery 사용 -->

</head>
<body>

<div id="box">
	<input type="text" name="mid" id="mid"><!-- 이 태그를 js에서 찾을려면 id나 class가 필요하다 -->
	<button class="btn" onclick="check();">중복검사</button>
	<div id="result"></div> <!-- 결과 나오는 공간 -->
</div>

<script type="text/javascript">
	function check(){
		var mid=$("#mid").val(); // id가 mid인 것 .var()는 뭐지?
		
		$.ajax({
			type: 'GET', // 어떤 타입으로 보낼래? 보통 get or post
			url: '${pageContext.request.contextPath}/check.do?mid='+mid, // url로 파라미터 값보내는 get방식
			// 어느 url로 요청을 보낼래? 여기 url이 요청임 이 요청으로 자바(서블릿) 로직실행가능
			// mid(V에서 입력한 값) 라는 변수 만들어서 사용자가 입력한 값을 확보한 상태 DB한테 물어볼 예정
			// "기존에 같은 mid가 있어?" => DAO(M) V값을 M에게 전달 == C의 작업(서블릿)
			// ${pageContext.request.contextPath} => 프로젝트 명도 기제됨 (실제 실무에서는 헷갈리지 않기위해 사용)
					
			data: {mid:mid}, // 무슨 data를 보낼래? post방식: json데이터로 mid라는 이름으로 mid값을 보냄 (json 복습 필요)
			success: function(result){ // 요청 보낸 곳(서블릿)에서 성공적으로 수행 시 실행
				// result 는 String
				// JS -1 : 모든 데이터가 객체
				// JS -2 : 동적타이핑(즉각적으로 자동 형변환)
					console.log("로그: ["+result+"]")
					if(result == 1){
						$("#result").text("사용이 가능한 아이디 입니다!");
						$("#result").css("color","blue");
					}
					else{
						$("#result").text("사용이 불가능한 아이디 입니다...");
						$("#result").css("color","red");
					}
			},
			error: function(request, status, error){ // 요청 보낸 곳(서블릿)에서 에러가 발생할 시 실행
				console.log("code: "+request.status);
				console.log("message: "+request.responseText);
				console.log("error: "+error);
			}
		});
	}
	
</script>

</body>
</html>