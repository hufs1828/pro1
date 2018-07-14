<?php
header('Content-Type:text/html; charset=UTF-8');

$id =urldecode($_POST['id']);
$pword = urldecode( $_POST['pword']);
$title = urldecode($_POST['title']);
$subject = urldecode($_POST['subject']);

#if ($id == "") $id = "no id";
#if ($pword == "") $pword = "no password";
#if ($title =="") $title ="no title";
#if($subject =="") $subject = "nosubject";

echo ("
  ID : $id \r\n
  PASS : $pword \r\n
  TITLE : $title \r\n
  SUBJECT : $subject \r\n
");

?>
