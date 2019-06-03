

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Entry Mahasiswa</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body>
  <form action="update_split_barang.php" method="post" enctype="multipart/form-data" name="FMHS">
    <table width="452" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#666666">
      <tr>
        <td height="40" align="center" bgcolor="#009999"><strong><font color="#FFFFFF">FORM INPUT DATA </font></strong></td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF"><table width="452" border="0" align="center" cellpadding="5" cellspacing="0">
            <tr>
              <td width="113">Split</td>
              <td width="11">:</td>
              <td width="237"><input name="split_entri" type="text" id="split_entri" size="12" maxlength="12"></td>
            </tr>
          
            <tr>
              <td colspan="3" align="center"><input name="fok" type="submit" id="fok" value="OK">
            </td>
            </tr>
        </table></td>
      </tr>
    </table>
  </form>
</body>
</html>
<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $split_entri=$_POST['split_entri'];
	

   require_once('koneksi.php');
  
	$sql= "update barang set stok= stok + '$split_entri' where id_brg ='kac2'and jenis_brg='split'";
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