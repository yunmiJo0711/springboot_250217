/*
 * 
 */
document.getElementById('btnGetList').addEventListener('click',getList)
document.getElementById('btnGetOne').addEventListener('click',getOne)

function getOne(){
	const userid = document.getElementById('search').value
	const url = `/account/${userid}`
	
	fetch(url)
		.then(response => {		// 화살표함수 : response 는 함수의 인자 
			console.log("response : ", response)
			return response.json()	// 그 중에서 컨트롤러(서버)에 있는 body만 객체화해서 넘겨준다.
			// 응답의 body를 역직렬화(객체화) 하여 다음 then 의 콜백함수 인자 data에 전달 
		})
		.then(data => {		// 화살표함수 : data 는 함수의 인자 // function ooo (data) 대신에 화살표 함수 사용.
			// data 는 response.json() 의 결과
			console.log("data : ", data)
			dataPrint(data)
		}) 
		.catch(error => console.error("에러 : ", error))
}

function dataPrint(data){
//	document.getElementById('userid').value=data.userid
	
	const result=`	
	<input id="userid" value="${data.userid}">
	<input id="username" value="${data.username}">
	<input id="password" type="password" value="${data.password}">
	<input id="birth" type="date" value="${data.birth}">
	<input id="gender" value="${data.gender}">
	<input id="email" type="email" value="${data.email}">
	`
	document.getElementById('result').innerHTML=result
}

function getList(){
	const url = '/account'
	
	fetch(url)
		.then(response => {
			console.log("response : ", response)
			return response.json()	// 응답의 body를 역직렬화(객체화) 하여 다음 then 에 전달 
		})
		.then(data => {
			console.log("data : ", data)
			dataPrint(data)
		}) 
		.catch(error => console.error("에러 : ", error))
}