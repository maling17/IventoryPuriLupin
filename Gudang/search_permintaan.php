<?php

require 'koneksi.php';



if($_SERVER['REQUEST_METHOD']=='POST') {
$search = $_POST['search'];
$sql="SELECT id_sj,tgl_sj,tujuan,split_sj,flake_sj FROM surat_jalan where id_sj like '%search%' and tgl_sj like '%$search%' order by tgl_sj";
$query= $con->query ($sql)or die($con->error);
$res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){
    array_push($result, array('id_sj'=>$row[0], 'tgl_sj'=>$row[1], 'tujuan'=>$row[2], 'split_sj'=>$row[3],'flake_sj'=>$row[4]));
  }
  echo json_encode(array("value"=>1,"result"=>$result));
  mysqli_close($con);
}
?>