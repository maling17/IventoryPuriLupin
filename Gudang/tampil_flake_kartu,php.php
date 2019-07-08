<?php

require 'koneksi.php';

$sql_tampil_mitra="select f_awal as Flake_Awal,jumlah_olah as Flake_Olah,f_awal+jumlah_olah as Flake_akhir from pengolahan p,detil_pengolahan d where d.id_brg='3' and d.tgl_pengolahan=p.tgl_pengolahan and d.id_pengolahan=p.id_pengolahan order by p.tgl_pengolahan;";
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
$response = ['status'=> $status, 'barang' => $response_data];
// tampilkan dan convert ke format json
echo json_encode($response);
?>