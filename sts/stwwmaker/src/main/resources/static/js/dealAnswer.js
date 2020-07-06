
//正解・不正解判定後、URL変数を変える(正解数:succ 不正解数:failに入れて実行、url_refに反映)
function OnRefreshURL()
{
	//正解数・不正解数をHTMLから取得
	var succ = document.getElementById('succ');
	var fail = document.getElementById('fail');
	
	//数字に変換
	var succ_num = Number(succ.innerHTML);
	var fail_num = Number(fail.innerHTML);
	
	//URLの参照を取得
	var url = document.getElementById('url_ref');
	url.href = "/qanda/?succ="+succ_num+"&fail="+fail_num;
}

//画面切り替え
function switchDisplay()
{
	var formQ = document.getElementById('quesDisp');
	formQ.style.display = "none";
	var formA = document.getElementById('ansDisp');
	formA.style.display = "block";
}

//正解のボタンを押したとき(ボタン押下イベント関数)
function onButtonSuccess()
{
	switchDisplay();	//画面切り替え
	alert("正解！");
	
	var cnt = document.getElementById('succ');
	var num_s = Number(cnt.innerHTML);
	num_s += 1;
	cnt.innerHTML = String(num_s);
	
	//URL更新
	OnRefreshURL();
}

//不正解ノボタンを推したとき(ボタン押下イベント関数)
function onButtonFailed()
{
	switchDisplay();	//画面切り替え
	alert("不正解！");

	var cnt = document.getElementById('fail');
	var num_s = Number(cnt.innerHTML);
	num_s += 1;
	cnt.innerHTML = String(num_s);
	
	//URL更新
	OnRefreshURL();
}