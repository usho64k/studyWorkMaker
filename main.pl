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
	my @arrayLine = split(/\n/,$content);
	for(my $count = 0; $count < $#arrayLine+1; $count++)
	{
		#arrayLineを直接見れば要素数がもらえる
		my $str = $arrayLine[$count];
		#print ($str . "spell\n");

		my @array = split(/=/,$str);
		print ($array[0] . " is ". $array[1] . "\n");

	}
}
else
{
	print ("NG");
}
