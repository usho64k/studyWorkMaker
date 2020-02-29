#!/usr/bin/perl
use strict;
use warnings;

my @strary;
foreach my $a(<test.txt>){
@strary=(@strary,$a);
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
	print ("OK!");
	#fileからデータを読み取る
	my $file ='abc.txt';
	open(my $fh, '<', $file) or die("Can't open $file:$!");
	my $content = do {local $/; <$fh> };

	#各行に分けて処理
	my @fileLine = split(/\n/,$content);
	my @title = ("question","answer");
	my @arrayS = \@title;
	my $sepatacrow = \@arrayS;

	for(my $count = 0; $count < $#fileLine+1; $count++)
	{
		#(@#fileLine+1)を見れば要素数がもらえる
		my $str = $fileLine[$count];
		#print ($str . "spell\n");

		my @arrayK = split(/=/,$str);
		print ($arrayK[0] . " is ". $arrayK[1] . "\n");
		push(@arrayS,(\@arrayK));
	}

	#全要素を展開
	for my $datas (@$sepatacrow) 
	{
		for my $element (@$datas)
		{
			print "$element\n";
		}
	}
}
else
{
	print ("NG");
}
