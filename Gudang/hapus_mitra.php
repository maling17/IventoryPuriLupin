<?php
require_once('koneksi.php');
if($_SERVER['REQUEST_METHOD']=='POST') {
  $response = array();
  //mendapatkan data
  $id_mitra=$_POST['id_mitra'];
  $sql = "DELETE FROM mitra WHERE id_mitra= '$id_mitra'";
  if(mysqli_query($con,$sql)) {
    $response["value"] = 1;
    $response["message"] = "Berhasil dihapus";
    echo json_encode($response);
  } else {
    $response["value"] = 0;
    $response["message"] = "oops! Gagal dihapus!";
    echo json_encode($response);
  }
  mysqli_close($con);
}