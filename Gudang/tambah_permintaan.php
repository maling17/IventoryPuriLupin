


<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $id_permintaan=$_POST['id_permintaan'];
	$tgl_permintaan = $_POST['tgl_permintaan'];
	$tujuan= $_POST['tujuan'];
$id_mitra=$_POST['id_mitra'];
   require_once('koneksi.php');
   //Cek npm sudah terdaftar apa belum
   $sql = "SELECT * FROM permintaan WHERE id_permintaan ='$id_permintaan'";
   $check = mysqli_fetch_array(mysqli_query($con,$sql));
   
   if(isset($check)){
     $response["value"] = 0;
     $response["message"] = "oops! sudah ada!";
     echo json_encode($response);
   } else {
	   
    $sql= "INSERT INTO permintaan (id_permintaan,tgl_permintaan,tujuan,id_mitra) VALUES ('$id_permintaan', '$tgl_permintaan', '$tujuan','$id_mitra');";
	
	if(mysqli_query($con,$sql)) {
       $response["value"] = 1;
       $response["message"] = "Sukses Menambahkan";
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