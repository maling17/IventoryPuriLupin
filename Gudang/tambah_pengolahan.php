



<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $id_pengolahan=$_POST['id_pengolahan'];
	$tgl_pengolahan = $_POST['tgl_pengolahan'];
	$w_awal=$_POST['w_awal'];
	$s_awal=$_POST['s_awal'];
	$f_awal=$_POST['f_awal'];

   require_once('koneksi.php');
   
   $sql = "SELECT * FROM pengolahan WHERE id_pengolahan='$id_pengolahan'";
   $check = mysqli_fetch_array(mysqli_query($con,$sql));
   
   if(isset($check)){
     $response["value"] = 0;
     $response["message"] = "oops! sudah ada!";
     echo json_encode($response);
   } else {
	   
    $sql= "INSERT INTO pengolahan(id_pengolahan,tgl_pengolahan,w_awal,s_awal,f_awal) VALUES ('$id_pengolahan', '$tgl_pengolahan','$w_awal','$s_awal','$f_awal');";
	
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