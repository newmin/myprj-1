'use strict';
const $dropbox = document.getElementById('dropbox');
const $imageFile = document.getElementById('imageFile');
const $profileImg   = document.getElementById('profileImg');

const dragover_h = e => {
	e.preventDefault();//필수: 생략하면 drop이벤트가 발생하지 않는다.
}
const dragenter_h = e=>{
  e.target.style.borderColor="red";
  //console.log(e);	
}
const dragleave_h = e=> {
  e.target.style.borderColor="gray";
  $profileImg.src='';
  $imageFile.value='';
  //console.log(e);
}
const drop_h = e=>{
  e.preventDefault();
  e.target.style.borderColor="gray";
  const files = e.dataTransfer.files;
  console.log("drop:",files); 
  
  if(files.length == 1){
	  $imageFile.files = files;	  
	
    // 파일을 읽는 작업 시작
	  //readAsDataURL 메서드는 파일을 data URL 형식으로 만들어 준다. 
	  //파일을 서버에 업로드하지 않고도 조작할 수 있음을 의미한다. 
	  //포멧을 변환하거나, 데이터를 분석하여 변조하는 일이 가능해 진다.	
	  const reader = new FileReader();
	  reader.readAsDataURL(files[0]);
	  reader.addEventListener("load",e=>{ 
			$profileImg.src =  reader.result; 
		});
	}else{
		alert('파일을 하나만 선택하세요!');
	}
}
const click_h =  e=>{
  const ev = new MouseEvent('click',{bubbles:true, cancelable:false});
  $imageFile.dispatchEvent(ev);	
}
const change_h = e=>{
  const files = e.target.files;
  console.log("change",files);   
  
  if(files.length == 1){
  	const reader = new FileReader();
  	reader.readAsDataURL(files[0]);
  	reader.addEventListener("load",e=>{
    	$profileImg.src =  reader.result;
 	 	});
 	}else{
		alert('파일을 하나만 선택하세요!');
	}
}
  
$dropbox.addEventListener('dragover',dragover_h); 
$dropbox.addEventListener('dragenter',dragenter_h);
$dropbox.addEventListener('dragleave',dragleave_h);
$dropbox.addEventListener('drop', drop_h);
$dropbox.addEventListener('click',click_h);
$imageFile.addEventListener("change",change_h);

