<?php

require 'koneksi.php';

$sql="select id_permintaan from permintaan order by id_permintaan desc limit 1;";

$check = mysqli_fetch_array(mysqli_query($con,$sql));



$query= $con->query ($sql);
$response_data=null;
while ($data = $query->fetch_assoc()) {
 // tambahkan data yg di seleksi ke dalam array
 $response_data[] = $data;
}
// Cek apakah datanya null ?
if (is_null($response_data)) {
 // jika ya, buat status untuk response jadi false
 $sql= "INSERT INTO permintaan(id_permintaan) VALUES (0);";
	if(mysqli_query($con,$sql)) {
       $response["value"] = 1;
       $response["message"] = "Sukses Menambahkan Permintaan";
		echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "oops! Coba lagi!";
       echo json_encode($response);
	   
     }
 $status = false;
} else {
 // jika tidak, buat status untuk response jadi true
 $status = true;
}
// Set type header response ke Json
header('Content-Type: application/json');
// Bungkus data dalam array
$response = ['status'=> $status, 'permintaan' => $response_data];
// tampilkan dan convert ke format json
echo json_encode($response);
?>