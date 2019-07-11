


<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $id_brg=$_POST['id_brg'];
   $stok=$_POST['stok'];
   $split_sj=$_POST['split_sj'];
   
	

   require_once('koneksi.php');

   $sql2 = "update barang set stok=stok-'$split_sj' where id_brg='2'";
 
	
	if(mysqli_query($con,$sql2)) {
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