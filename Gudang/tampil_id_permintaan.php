<?php

require 'koneksi.php';

$sql="select id_permintaan from permintaan order by id_permintaan desc limit 1;";
$query= $con->query ($sql);
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
$response = ['status'=> $status, 'permintaan' => $response_data];
// tampilkan dan convert ke format json
echo json_encode($response);
?>