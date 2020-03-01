#!/usr/bin/perl
use strict;
use warnings;

#ファイル名を与える
my $fileName = 'abc.txt';

#読み込み処理
my $fh2 = readfile($fileName);				#ファイル読み込み
my $f_cont = do {local $/; <$fh2> };		#ファイル内容をf_contに代入 
my @fileLine = split(/\n/,$f_cont);			#各行に分けて処理

#QA表を作成する
my @qalist = ("answer","question");
my @arrayS = \@qalist;
my $arrayP = \@arrayS;

for(my $count = 0; $count < $#fileLine+1; $count++)
{
	#count行目を参照する
	my $str = $fileLine[$count];

	#questionとanswerに分けてarrayS(=qalistのリファレンス)に
	#strElemのリファレンスを追加する
	my @strElem = split(/=/,$str);
	push(@arrayS,(\@strElem));
}

#ゲームフェーズ
my $continue = 1;
while($continue == 1)
{

	#問題・正解が4択のうちどれかを取得
	my @qaNum = (int(rand $#fileLine+1),int(rand 4));
	#他の選択肢を取得
	my @ans_otherSel = ($qaNum[1]);
	for my $num (0 .. 2)
	{
		push(@ans_otherSel,makerand($#fileLine+1,$qaNum[0],@ans_otherSel));
	}
	print($qaNum[0]."\n");
	print($ans_otherSel[0]."\n");
	print($ans_otherSel[1]."\n");
	print($ans_otherSel[2]."\n");
	print($ans_otherSel[3]."\n");

	#問題を出す
	print $arrayP->[$qaNum[0]][1]."\n";

	#選択肢を出す
	my $ansprCnt = 0;
	for(my $i = 0; $i < 4; $i++)
	{
		if($qaNum[1] != $i)
		{
			print $i.":".$arrayP->[$ans_otherSel[$ansprCnt]][0]."\n";
			$ansprCnt++;
		}
		else
		{
			print $i.":".$arrayP->[$qaNum[0]][0]."\n";
		}
	}

	#答えを入力
	my $userAns = <STDIN>;
	chomp($userAns);
	print "ans : $userAns \n";

	#正解or不正解
	if($userAns == $qaNum[1])
	{
		print "正解\n";
	}
	else
	{
		print "不正解\n";
	}

	#煽る
	print "again?\n please tap y to continue solving questions\n";
	my $userAns2 = <STDIN>;
	chomp($userAns2);
	print " <<<$userAns2>>>  \n";
	if($userAns2 ne "y")
	{
		$continue = 0;
	}
}

#以下サブルーチン

#ファイルを読み込む処理
sub readfile
{
	my $fname = shift;
	open(my $fh,'<',$fname) or die("Can't open $fname:$!");

	return $fh;
}

#乱数処理
sub makerand
{
	my ($lines, $oksel, @search_list) = @_;

	#繰り返し変数と返り値変数
	my $continue = 1;
	my $ret = 0;

	#繰り返しフラグが立っていれば繰り返す
	while($continue == 1)
	{
		#乱数を一つ取得
		$ret = int(rand $lines);

		#重複を検出するとdupleが1になり、continueが変わらず続行
		#重複しなければdupleが0のまま、continueが0になって終了
		#my @matches = grep {$_ == $ret} @search_list;
		#my $duple = $matches;
		
		my $duple = 0;
		for my $num (@search_list)
		{
			if($num == $ret)
			{
				$duple = 1;
			}
			if($oksel == $ret)
			{
				$duple = 1;
			}
		}
		if($duple == 0)
		{
			$continue = 0;
		}
	}
	return $ret;
}

