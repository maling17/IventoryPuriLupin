<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $qty_olah=$_POST['qty_olah'];
   
	

   require_once('koneksi.php');

   $sql2 = "update barang set stok=stok-'$qty_olah' where id_brg='kac1'";
 
	
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