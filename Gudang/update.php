
<?php
require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='POST') {

  $response = array();
  //mendapatkan data
  $id_mitra=$_POST['id_mitra'];
  $daerah_mitra=$_POST['daerah_mitra'];
  $pic_mitra=$_POST['pic_mitra'];
  $tlp_mitra=$_POST['tlp_mitra'];
  $alamat_mitra=$_POST['alamat_mitra'];

  $sql = "UPDATE mitra SET daerah_mitra = '$daerah_mitra', pic_mitra = '$pic_mitra', tlp_mitra = '$tlp_mitra', alamat_mitra = '$alamat_mitra' WHERE id_mitra = '$id_mitra'";
  if(mysqli_query($con,$sql)) {
    $response["value"] = 1;
    $response["message"] = "Berhasil diperbarui";
    echo json_encode($response);
  } else {
    $response["value"] = 0;
    $response["message"] = "oops! Gagal merubah!";
    echo json_encode($response);
  }
  mysqli_close($con);
}
?>