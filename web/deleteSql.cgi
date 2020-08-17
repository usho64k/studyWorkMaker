#!/usr/bin/perl

#DByounohandora library wo calling
use DBI;
use DBD::mysql;

use CGI;
use utf8;
use Encode;

my $dbh = DBI->connect("DBI:mysql:test_t","root","ubuntu") or die "cant connect to mysql: $DBI::errstr";

$dbh->do("set names utf8");

my $delete_query = 'delete from papipu;';
$dbh->do($delete_query);
$dbh->disconnect();

print("content-type: text/html\n\n");
print("<html>\n");
print("<head>\n");
print("<meta charset=\"UTF-8\">\n");
print("</head>\n");

print("<body>\n");
print("deleted data on table of (papipu)\n");
print("</body>\n");
print("</html>\n");
