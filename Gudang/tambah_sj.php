


<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data

$id_sj=$_POST['id_sj'];
	
   $tgl_sj = $_POST['tgl_sj'];
	$tujuan= $_POST['tujuan'];
	$split_sj= $_POST['split_sj'];
	$flake_sj=$_POST['flake_sj'];
	$id_permintaan=$_POST['id_permintaan'];
	

   require_once('koneksi.php');
   
   $sql = "SELECT * FROM surat_jalan WHERE id_sj ='$id_sj'";
   $check = mysqli_fetch_array(mysqli_query($con,$sql));
   
   if(isset($check)){
     $response["value"] = 0;
     $response["message"] = "oops! sudah ada!";
     echo json_encode($response);
   } else {
	   
    $sql= "INSERT INTO surat_jalan(id_sj,tgl_sj,tujuan,id_permintaan,split_sj,flake_sj) VALUES ('$id_sj','$tgl_sj', '$tujuan','$id_permintaan','$split_sj','$flake_sj');";
	
	if(mysqli_query($con,$sql)) {
       $response["value"] = 1;
       $response["message"] = "Sukses Menambahkan Surat Jalan";
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