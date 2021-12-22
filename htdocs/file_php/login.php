<?php
	include 'db_config.php';
	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);
	$encodedLogin = $_POST['EN_LOGIN'];
	$parts = [];
	$parts = explode(";", $encodedLogin);
	$sqlQuery = "SELECT `id`,`nama`,`posisi`,`profil_picture` FROM `register` where `email`='$parts[0]' AND `password`='$parts[1]'";
	$res = mysqli_query($con,$sqlQuery);
	$count = mysqli_num_rows($res);
    $row=$res->fetch_assoc();
	$idcek=$row["id"];
	$date = date('Y-m-d');
	$sql = "SELECT * FROM `medcheck` where `id_akun`='$idcek' AND `currentDate`='$date'";
	$res1 = mysqli_query($con,$sql);
	$count1 = mysqli_num_rows($res1);
	if(mysqli_query($con, $sqlQuery)&&$count>=1){
		$result["status"] = TRUE;
		$result["remarks"] = "Login Successfully";
		$result["id"] = $row["id"];
		$result["nama"] = $row["nama"];
		$result["posisi"] = $row["posisi"];
		$result["myimage"] = $row["profil_picture"];
		$result["totalrow"] = $count1;
	}else{
		$result["status"] = FALSE;
		$result["remarks"] = "Login Failed";
	}
	mysqli_close($con);
	print(json_encode($result));
?>