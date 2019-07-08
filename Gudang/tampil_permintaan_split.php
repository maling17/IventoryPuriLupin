<?php

require 'koneksi.php';

$sql="select p.id_permintaan,p.tgl_permintaan,p.tujuan,d.jumlah_minta as split from permintaan p,detil_permintaan d where p.id_permintaan=d.id_permintaan and d.id_brg=2 order by p.id_permintaan";
$query= $con->query ($sql)or die($con->error);
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