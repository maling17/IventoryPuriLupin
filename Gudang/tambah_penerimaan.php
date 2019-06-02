


<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $id_penerimaan=$_POST['id_penerimaan'];
   $no_po=$_POST['no_po'];
	$tgl_penerimaan = $_POST['tgl_penerimaan'];
	$qty_penerimaan= $_POST['qty_penerimaan'];
	

   require_once('koneksi.php');
   
   $sql = "SELECT * FROM penerimaan WHERE id_penerimaan='$id_penerimaan'";
   $check = mysqli_fetch_array(mysqli_query($con,$sql));
   
   if(isset($check)){
     $response["value"] = 0;
     $response["message"] = "oops! sudah ada!";
     echo json_encode($response);
   } else {
	   
    $sql= "INSERT INTO penerimaan (id_penerimaan,tgl_penerimaan,no_po,qty_penerimaan) VALUES ('$id_penerimaan', '$tgl_penerimaan','$no_po', '$qty_penerimaan');";
	
	if(mysqli_query($con,$sql)) {
       $response["value"] = 1;
       $response["message"] = "Sukses Menambahkan data";
		echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "oops! Coba lagi!";
       echo json_encode($response);
	   
     }
   }
   // tutup database
   mysqli_close($con);
   
} else {
  $response["value"] = 0;
  $response["message"] = "oops! Data gagal dimasukan!";
  echo json_encode($response);
}
?>	