


<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $id_brg=$_POST['id_brg'];
$jenis_brg=$_POST['jenis_brg'];
   $stok=$_POST['stok'];
   $flake_entri=$_POST['flake_entri'];
   
	

   require_once('koneksi.php');

   $sql= "update barang set stok= stok + $flake_entri where id_brg='kac3' and jenis_brg='flake';";
 
	if(mysqli_query($con,$sql)) {
    $response["value"] = 1;
    $response["message"] = "Berhasil diperbarui";
    echo json_encode($response);
  } else {
    $response["value"] = 0;
    $response["message"] = "oops! Gagal merubah!";
    echo json_encode($response);
  }
  mysqli_close($con);
}
?>