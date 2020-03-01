#!/usr/bin/perl
use strict;
use warnings;

my @strary;
foreach my $a(<test.txt>){
@strary=(@strary,$a);
}

#乱数生成
my @rndArr = (int(rand 13),int(rand 10),int(rand 10));

print "(1) 本番\n";
for my $i(0 .. 2)
{
	print "$i番目: $rndArr[$i] \n";
}


#標準入力
my $line = <STDIN>;
chomp($line);
print "a : $line \n";

#基本的にはこの使い方をする
my $targetstr = "卑弥=呼邪馬台国の女王";
my $isContainsEqual = ($targetstr =~ /=/);

if($isContainsEqual != 0)
{
	#print ("OK!");

	#fileからデータを読み取る
	my $file ='abc.txt';
	open(my $fh, '<', $file) or die("Can't open $file:$!");
	#ファイルの内容をcontentに入れる
	my $content = do {local $/; <$fh> };

	#各行に分けて処理
	my @fileLine = split(/\n/,$content);
	my @title = ("question","answer");
	my @arrayS = \@title;
	my $arrayP = \@arrayS;

	for(my $count = 0; $count < $#fileLine+1; $count++)
	{
		#(@#fileLine+1)を見れば要素数がもらえる
		my $str = $fileLine[$count];

		#questionとanswerに分ける
		my @arrayK = split(/=/,$str);
		print ($arrayK[0] . " is ". $arrayK[1] . "\n");
		push(@arrayS,(\@arrayK));
	}
	print "\n\n";

	#全要素を展開
	my $cnt = 0;
	my $anscnt = 0;

	for my $datas (@$arrayP) 
	{
		for my $element (@$datas)
		{
			if(($cnt % 2 == 0) && ($cnt / 2 == $rndArr[0]))
			{
				$anscnt = $cnt;
				print "$element\n";
			}
			$cnt++;
		}
	}
	
	my $ansover = 10;
	while($ansover > 0)
	{
		
	}

}
else
{
	print ("NG");
}


