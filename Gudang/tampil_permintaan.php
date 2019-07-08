<?php

require 'koneksi.php';
  $id_permintaan = $_GET['id_permintaan'];
$sql="select distinct p.id_permintaan, p.tgl_permintaan,p.tujuan, (select jumlah_minta from detil_permintaan where id_brg=2 and id_permintaan='$id_permintaan') as split,(select jumlah_minta from detil_permintaan where id_brg=3 and id_permintaan = '$id_permintaan')as flake from permintaan p, detil_permintaan d where p.id_permintaan = d.id_permintaan and d.id_permintaan='$id_permintaan'";
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