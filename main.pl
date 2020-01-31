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

	open(DATAFILE,	"<abc.txt") or die("Error:$!");
	my $line = <DATAFILE>;
	
	my @array = split(/=/,$line);
	print ($array[0] . "is" . $array[1] . "\n");
}
else
{
	print ("NG");
}
