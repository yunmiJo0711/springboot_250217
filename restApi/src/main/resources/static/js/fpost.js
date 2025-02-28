/*
 * 
 */
document.getElementById('btnSave').addEventListener('click',sendPost)

function sendPost(){
	if(!isCheck){
		alert('사용할 수 없는 아이디 입니다.')
		return;
	}
	// 6개의 입력값 가져오기
	const userid = document.getElementById('userid').value
	const username = document.getElementById('username').value
	const birth = document.getElementById('birth').value
	const gender = document.getElementById('gender').value
	const email = document.getElementById('email').value
	const password = document.getElementById('password').value
	const url = '/account'
	// 저장할 데이터를 객체로 만들기 : 프로퍼티 이름과 값 저장 변수명이 같으면 줄여서 한 번만 작성 
	const obj ={userid,username,birth,gender,email,password}
/*	const obj = {
		userid : 'gilsoon',
		username : '홍길순',
		password : '7777',
		birth : '2000-01-01',
		gender : '여성',
		email : 'soon@naver.com'
	}
*/	
	const options = {
		method: 'POST',
		headers : {
			'Content-Type': 'application/json;charset=UTF-8'
		},
		body : JSON.stringify(obj) //jsonStr
	}
	
	fetch(url,options)
		.then(response => {
			console.log("response : ", response)
			if(response.status === 400){
				alert('userid 와 email 은 유일한 값으로 합니다.(무결성 위반)')
			}
			return response.json()
		})
		.then(data => {
			console.log("data : ", data)
			if(data===1){
				alert('계정 등록 완료했습니다.')
				clear()
			}
		})
		.catch(error => console.error("오류 : ", error))
}

function clear(){
	document.getElementById('userid').value=''
	document.getElementById('username').value=''
	document.getElementById('password').value=''
	document.getElementById('birth').value=''
	document.getElementById('gender').value=''
	document.getElementById('email').value=''
	document.getElementById('message').innerHTML=''
}


		
		
		
		
		
		
		