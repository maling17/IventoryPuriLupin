<?php

require 'koneksi.php';

$sql_tampil_mitra="SELECT * FROM mitra order by id_mitra";
$query= $con->query ($sql_tampil_mitra);
$response_data=null;
while ($data = $query->fetch_assoc()) {
 // tambahkan data yg di seleksi ke dalam array
 $response_data[] = $data;
}
// Cek apakah datanya null ?
if (is_null($response_data)) {
 // jika ya, buat status untuk response jadi false
 $status = false;
} else {
 // jika tidak, buat status untuk response jadi true
 $status = true;
}
// Set type header response ke Json
header('Content-Type: application/json');
// Bungkus data dalam array
$response = ['status'=> $status, 'mitra' => $response_data];
// tampilkan dan convert ke format json
echo json_encode($response);
?>