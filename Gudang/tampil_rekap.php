<?php

require 'koneksi.php';


$bulan=$_GET['bulan'];
$tahun=$_GET['tahun'];

$sql="SELECT date_format(tgl_permintaan,'%Y-%m')as tgl_permintaan FROM permintaan where month(tgl_permintaan)='$bulan' and year(tgl_permintaan)='$tahun' group by month(tgl_permintaan)";
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