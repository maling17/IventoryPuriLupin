


<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $id_mitra=$_POST['id_mitra'];
	$daerah_mitra = $_POST['daerah_mitra'];
	$pic_mitra = $_POST['pic_mitra'];
	$tlp_mitra = $_POST['tlp_mitra'];
	$alamat_mitra = $_POST['alamat_mitra'];

   require_once('koneksi.php');
   //Cek npm sudah terdaftar apa belum
   $sql = "SELECT * FROM mitra WHERE id_mitra ='$id_mitra'";
   $check = mysqli_fetch_array(mysqli_query($con,$sql));
   
   if(isset($check)){
     $response["value"] = 0;
     $response["message"] = "oops! sudah ada!";
     echo json_encode($response);
   } else {
	   
    $sql= "INSERT INTO mitra (id_mitra,daerah_mitra,pic_mitra,tlp_mitra,alamat_mitra) VALUES ('$id_mitra', '$daerah_mitra', '$pic_mitra', '$tlp_mitra', '$alamat_mitra');";
	
	if(mysqli_query($con,$sql)) {
       $response["value"] = 1;
       $response["message"] = "Sukses mendaftar";
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