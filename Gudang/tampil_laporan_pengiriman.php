<?php

require 'koneksi.php';


$bulan=$_GET['bulan'];
$tahun=$_GET['tahun'];

$sql="SELECT date_format(tgl_sj,'%Y-%m')as tgl_sj ,tujuan,split_sj,flake_sj FROM surat_jalan where month(tgl_sj)='$bulan' and year(tgl_sj)='$tahun' group by month(tgl_sj)";
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
$response = ['status'=> $status,'laporan' => $response_data];
// tampilkan dan convert ke format json
echo json_encode($response);

?>