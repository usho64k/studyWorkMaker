#!/usr/bin/perl

#DB用のハンドラライブラリを呼び出す
use DBI;
use DBD::mysql;

#HTMLのPOST命令を受け取るライブラリを呼び出す(ついでに文字コード指定)
use CGI;
use utf8;
use Encode;

#先にDBにアクセスする(Perlで試すときにこっちが先に検証される)
my $dbh = DBI->connect("DBI:mysql:test_t","root","ubuntu") or die "cant connect 2 mysql: $DBI::errstr";


#MySQLで取り扱うエンコーディングに直す
$dbh->do("set names utf8");

#create table(必要次第)
#my $create_query = 'create table test_table(question varchar(256),answer varchar(256));';
#$dbh->do($create_query);

#HTML表示開始
print("content-type: text/html\n\n");

#ヘッダ記述
print("<html>\n");
print("<head>\n");
print("<meta charset=\"UTF-8\">\n");
print("</head>\n");

#ボディ記述
print("<body>\n");


#アップロードされたファイルを展開する
#アップロードされたファイルを開く (= HTMLのPOST命令を受け取り、ファイルハンドラにする)
my ($query_cgi,$fh);					#HTMLファイルハンドラ準備
$query_cgi = new CGI;
$fh = $query_cgi->param('csvSel');	#HTMLのinput-file属性にあたるIDを指定する
my $fStrAll = "";						#展開内容保存変数準備
print("<p>state</p>");
#デバッグ用のファイルを開く
#open(OUT,">","./a.txt") or die("<p>ERROR Can't Open Debug File.</p>");

#展開処理
while(read($fh,$buffer,1024))
{
	#print OUT $buffer;		#デバッグ用処理
	$fStrAll .= $buffer;	#吐き出した文字列を全部バッファする
	print("<p>state</p>");
}
print("<p>state</p>");

#処理終了。双方のファイルを閉じる
#close(OUT);	#デバッグ用処理
close($fh);	#ハンドラも閉じる(全部fStrAllに移ったので不要)

#HTMLに結果表示
print("<p><b>DEBUG:::</b>");
#噂によると、UTF8で処理していても、デコードする必要があるらしい。
print(Encode::decode('utf8',$fStrAll));
print("</p>\n");

#内容を分解してデータベースに挿入
my @fileLine = split(/\n/,$fStrAll);
print($#fileLine+1);

for(my $count = 0; $count < $#fileLine+1; $count++)
{
	my $str = $fileLine[$count];
	my @strElem = split(/=/,$str);
	my $insert_query = "insert into papipu value(12,\'".$strElem[0]."\',\'".$strElem[1]."\');";
	$dbh->do($insert_query);
	print("<p>insertQ:".$strElem[0].":A:".$strElem[1]."</p>\n");		#デバッグ用処理
}
$dbh->disconnect();

print("</body>\n");
print("</html>\n");

