'use strict';

const $modifyBtn = document.getElementById('modifyBtn');  		
const $cancelBtn = document.getElementById('cancelBtn');  		
const $listBtn = document.getElementById('listBtn');

//수정
$modifyBtn.addEventListener("click", e=>{
	const bnum = e.target.dataset.bnum;
	location.href = `/bbs/${bnum}/edit`;
});

//취소
$cancelBtn.addEventListener("click", e=>{
	
});

//목록
$listBtn.addEventListener("click", e=>{
	location.href = "/bbs/list";
});