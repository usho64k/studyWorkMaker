#!/usr/bin/perl
use strict;
use warnings;

#乱数生成
my @qaNum = (int(rand 13),int(rand 4));
my @otherSelector = (int(rand 13),int(rand 13),int(rand 13));

#ファイルを読み込む
my $fileName = 'abc.txt';
open(my $fh, '<', $fileName) or die("Can't open $fileName:$!");

#ファイルの内容をf_contに入れる
my $f_cont = do {local $/; <$fh> };

#各行に分けて処理
my @fileLine = split(/\n/,$f_cont);

#QA表を作成する
my @qalist = ("answer","question");
my @arrayS = \@qalist;			#この辺謎
my $arrayP = \@arrayS;			#この辺謎

for(my $count = 0; $count < $#fileLine+1; $count++)
{
	#count行目を参照する
	my $str = $fileLine[$count];

	#questionとanswerに分けてarrayS(=qalistのリファレンス)に
	#strElemのリファレンスを追加する
	my @strElem = split(/=/,$str);
	push(@arrayS,(\@strElem));
}

#問題を出す
print $arrayP->[$qaNum[0]][1]."\n";
my $ansprCnt = 0;

for(my $i = 0; $i < 4; $i++)
{
	if($qaNum[1] != $i)
	{
		print $i.":".$arrayP->[$otherSelector[$ansprCnt]][0]."\n";
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
print "a : $userAns \n";

if($userAns == $qaNum[1])
{
	print "正解";
}
else
{
	print "不正解";
}
