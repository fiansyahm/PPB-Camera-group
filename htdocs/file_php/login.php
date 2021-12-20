<?php

	include 'db_config.php';

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);
	
	$encodedLogin = $_POST['EN_LOGIN'];
	$parts = [];
	$parts = explode(";", $encodedLogin);
	
// 	$sqlQuery = "SELECT `nama`,`password` FROM `register`where `nama`='$parts[0]' AND `password`='$parts[1]'";
    $sqlQuery = "SELECT `id`,`nama`,`posisi` FROM `register` where `email`='$parts[0]' AND `password`='$parts[1]'";
	$res = mysqli_query($con,$sqlQuery);
	$count = mysqli_num_rows($res);
    
    
    $row=$res->fetch_assoc();
    // while($row = $result->fetch_assoc()) {
    // }
	

	if(mysqli_query($con, $sqlQuery)&&$count>=1){

		$result["status"] = TRUE;
		$result["remarks"] = "Login Successfully";
		$result["id"] = $row["id"];
		$result["nama"] = $row["nama"];
		$result["posisi"] = $row["posisi"];
		

	}else{

		$result["status"] = FALSE;
		$result["remarks"] = "Login Failed";

	}

	mysqli_close($con);

	print(json_encode($result));

?>