


<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $id_permintaan=$_POST['id_permintaan'];
	$split=$_POST['split'];

   require_once('koneksi.php');
   //Cek npm sudah terdaftar apa belum
   $sql = "SELECT * FROM detil_permintaan WHERE id_permintaan ='$id_permintaan'";
   $check = mysqli_fetch_array(mysqli_query($con,$sql));
   
  
	   
    $sql= "INSERT INTO detil_permintaan (id_permintaan,id_brg,jumlah_minta) VALUES ('$id_permintaan','2', '$split');";
	
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