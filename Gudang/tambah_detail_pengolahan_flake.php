<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $id_pengolahan=$_POST['id_pengolahan'];
	$flake=$_POST['flake'];
	$tgl_pengolahan=$_POST['tgl_pengolahan'];

   require_once('koneksi.php');
   //Cek npm sudah terdaftar apa belum
   $sql = "SELECT * FROM detil_pengolahan WHERE id_pengolahan ='$id_pengolahan'";
   $check = mysqli_fetch_array(mysqli_query($con,$sql));
   
  
	   
    $sql= "INSERT INTO detil_pengolahan (id_brg,id_pengolahan,jumlah_olah,tgl_pengolahan) VALUES ('3','$id_pengolahan', '$flake','$tgl_pengolahan');";
	
	if(mysqli_query($con,$sql)) {
       $response["value"] = 1;
       $response["message"] = "Sukses Menambahkan";
		echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "oops! Coba lagi!";
       echo json_encode($response);
	   
     }
   // tutup database
   mysqli_close($con);
   
} else {
  $response["value"] = 0;
  $response["message"] = "oops! Data gagal dimasukan!";
  echo json_encode($response);
}
?>