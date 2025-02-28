/*
 * 
 */
document.getElementById('btnGetList').addEventListener('click',getList)
document.getElementById('btnGetOne').addEventListener('click',getOne)
document.getElementById('userid').addEventListener('keyup',checkUserid)

let isCheck = false
function checkUserid(){
	const message = document.getElementById('message')
	const userid = document.getElementById('userid').value
	if(userid.length < 4) {
		message.innerHTML = '4글자 이상입니다.'
		message.className='red'
		isCheck=false
		return
	}	
	const url = `/userid/${userid}`
	fetch(url)
			.then(response => {     
				return response.json()	
			})
			.then(data => {     
				// data 는 response.json() 의 결과
				console.log("data : ", data)
				if(data === true) {
					message.innerHTML ='사용할 수 있습니다.'
					message.className = 'green'
					isCheck = true
				}else {
					message.innerHTML ='이미 존재하는 아이디 입니다.'
					message.className = 'red'
					isCheck= false
				}
				
			})
			.catch(error => console.error("에러 : ", error) )
		
}

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
	<input value="${data.userid}">
	<input value="${data.username}">
	<input type="password" value="${data.password}">
	<input type="date" value="${data.birth}">
	<input value="${data.gender}">
	<input type="email" value="${data.email}">
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
//			document.getElementById('result').innerHTML=JSON.stringify(data)
			rowsPrint(data)
		}) 
		.catch(error => console.error("에러 : ", error))
}

function rowsPrint(list){
	document.getElementById('result').innerHTML=''
	
	list.forEach((item) => {
		const box = document.createElement("div")
		box.className = 'row'	// 위에서 만든 div 태그의 class 속성값
		const result=`	
			<input value="${item.userid}">
			<input value="${item.username}">
			<input type="password" value="${item.password}">
			<input type="date" value="${item.birth}">
			<input value="${item.gender}">
			<input type="email" value="${item.email}">
			`
		box.innerHTML = result
		document.getElementById('result').appendChild(box)		// result 요소의 자식 요소로 box 추가
	})
}