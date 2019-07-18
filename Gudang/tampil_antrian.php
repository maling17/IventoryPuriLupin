<?php

require 'koneksi.php';



$sql="SELECT sj.id_sj,p.tujuan,sj.split_sj,sj.flake_sj,m.tlp_mitra from surat_jalan sj,permintaan p,mitra m where sj.id_permintaan=p.id_permintaan and p.id_mitra=m.id_mitra order by id_sj";
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