
//正解・不正解を判定した後、URL変数を変える(正解数：succ 不正解数：fail)
function OnRefreshURL(){
	var succ = document.getElementById('success');
	var succ_num = Number(succ.innerHTML);

	var fail = document.getElementById('failed');
	var fail_num = Number(fail.innerHTML);

	var url = document.getElementById('url_ref');
	url.href = "./index.html?succ="+succ_num+"&fail="+fail_num;
}

//問題とユーザ回答画面・正誤判定画面の切り替えを行う(更新すると問題画面に戻る)
function switchDisplay(){
	var formQ = document.getElementById('quesDisp');
	formQ.style.display = "none";
	var formA = document.getElementById('ansDisp');
	formA.style.display = "block";
}

//正解のボタンを押したとき
function successPointAdd(){
	switchDisplay();
	alert("正解");

	var suc = document.getElementById( 'success' );
	var num_s = Number(suc.innerHTML);
	num_s += 1;
	suc.innerHTML = String(num_s);

	//Reconfig
	OnRefreshURL();
}

//不正解のボタンを押したとき
function failurePointAdd(){
	switchDisplay();
	alert("不正解");

	var fai = document.getElementById( 'failed' );
	var num_f = Number(fai.innerHTML);
	num_f += 1;
	fai.innerHTML = String(num_f);

	//Reconfig
	OnRefreshURL();
}

//ロード時 1回だけ呼ばれる
function onLoad(){
	//URL更新
	OnRefreshURL();
}

