<?php

require 'koneksi.php';

$sql="SELECT p.tgl_pengolahan, b.stok_awal,d.jumlah_olah,b.stok FROM barang b,detil_pengolahan d,pengolahan p where b.id_brg=d.id_brg and b.id_brg='1' and d.id_brg='1' and p.id_pengolahan= d.id_pengolahan order by b.id_brg";
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
$response = ['status'=> $status, 'barang' => $response_data];
// tampilkan dan convert ke format json
echo json_encode($response);
?>