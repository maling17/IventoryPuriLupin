<?php
include_once "koneksi.php";

	 class usr{}
	if(isset($_POST['nm_user'])&&isset($_POST['password'])){
	 $nm_user = ($_POST['nm_user']);
	 $password =($_POST['password']);
	
	 if ((empty($nm_user)) || (empty($password))) { 
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom tidak boleh kosong"; 
	 	die(json_encode($response));
	 }
	
	 $query = mysqli_query($con, "SELECT * FROM user WHERE nm_user='$nm_user' AND password='$password'");
	
	 $row = mysqli_fetch_array($query);
	
	 if (!empty($row)){
	 	$response = new usr();
	 	$response->success = 1;
	 	$response->message = "Selamat datang ".$row['nm_user'];
	 	$response->id_user = $row['id_user'];
	 	$response->nm_user = $row['nm_user'];
		$response->jabatan=$row['jabatan'];
		$response->tlp_user=$row['tlp_user'];
	 	die(json_encode($response));
		
	 } else { 
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Username atau password salah";
	 	die(json_encode($response));
	 }
	
	 mysqli_close($con);
	}
?>