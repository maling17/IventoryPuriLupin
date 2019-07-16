<?php

require 'koneksi.php';


$tgl_pengolahan=$_GET['tgl_pengolahan'];

$sql="select p.tgl_pengolahan,w_awal as Whole_Awal,d.jumlah_olah as Whole_Olah,w_awal-jumlah_olah as Whole_akhir from pengolahan p,detil_pengolahan d where d.id_brg='1' and d.tgl_pengolahan=p.tgl_pengolahan and d.id_pengolahan=p.id_pengolahan and date_format(p.tgl_pengolahan,'%Y-%m')='$tgl_pengolahan' order by p.tgl_pengolahan";
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