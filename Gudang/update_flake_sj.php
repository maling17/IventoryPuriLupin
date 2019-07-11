


<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $id_brg=$_POST['id_brg'];
   $stok=$_POST['stok'];
   $flake_sj=$_POST['flake_sj'];
	

   require_once('koneksi.php');

   $sql3 = "update barang set stok=stok-'$flake_sj' where id_brg='3'";
  
	
if(mysqli_query($con,$sql3)) {
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