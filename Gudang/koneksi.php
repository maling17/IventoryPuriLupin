<?php

define('Host','localhost');
define('User','root');
define('Pass','');
define('Db','dbgudang');

$con= mysqli_connect(Host,User,Pass,Db) or die ('unable to connect');
?>