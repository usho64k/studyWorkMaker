#!/usr/bin/perl
use strict;
use warnings;

#標準入力
my $line = <STDIN>;
chomp($line);
print "a : $line \n";

#基本的にはこの使い方をする
my $targetstr = "卑弥呼邪馬台国の女王";
my $isContainsEqual = ($targetstr =~ /=/);

if($isContainsEqual != 0)
{
	print ("OK!");

	my @array = split(/=/,$targetstr);
	print ($array[0] . "is" . $array[1] . "\n");
}
else
{
	print ("NG");
}
