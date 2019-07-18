<?php

require 'koneksi.php';

$sql="SELECT year(tgl_pengolahan)as tahun FROM pengolahan where year(tgl_pengolahan) not in(select year(tgl_pengolahan) from pengolahan where year(tgl_pengolahan)=0) group BY year(tgl_pengolahan)";
$query= $con->query ($sql);
$tahun=null;
while($data = $query->fetch_assoc()){
   
    $tahun[] = $data;
}
// Cek apakah datanya null ?
if (is_null($tahun)) {
 // jika ya, buat status untuk response jadi false
 $status = false;
} else {
 // jika tidak, buat status untuk response jadi true
 $status = true;
}
// Set type header response ke Json
header('Content-Type: application/json');
// Bungkus data dalam array
$response = ['status'=> $status, 'tahun' => $tahun];
// tampilkan dan convert ke format json
echo json_encode($response);
?>