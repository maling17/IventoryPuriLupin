<?php

require 'koneksi.php';


$tgl_permintaan=$_GET['tgl_permintaan'];

$sql="SELECT m.daerah_mitra, p.tgl_permintaan, sum(dp.jumlah_minta) as split  FROM mitra m, permintaan p, detil_permintaan dp, barang b WHERE m.id_mitra=p.id_mitra AND p.id_permintaan=dp.id_permintaan AND dp.id_brg=b.id_brg AND date_format(p.tgl_permintaan,'%Y-%m')='$tgl_permintaan' AND b.id_brg=2 group by m.daerah_mitra";
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