<?php

require 'koneksi.php';

$sql="SELECT id_permintaan,tgl_permintaan,tujuan FROM permintaan where id_permintaan not In(select id_permintaan from surat_jalan) and id_permintaan not in (select id_permintaan from permintaan where id_permintaan='1') order by id_permintaan";
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