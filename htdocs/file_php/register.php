<?php

	include 'db_config.php';

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);

	$encoded = $_POST['EN_REGIS'];
	$parts = [];
	$parts = explode(";", $encoded);

	$sqlQuery = "INSERT INTO `register`(`nama`, `posisi`, `email`, `password`) 
	VALUES ('$parts[0]','$parts[1]', '$parts[2]','$parts[3]')";

	if(mysqli_query($con, $sqlQuery)){  
	    $encoded="Register Successfully";
		$result["status"] = TRUE;
		$result["remarks"] = $encoded;
	}else{
		$result["status"] = FALSE;
		$result["remarks"] = "Register Failed";

	}
	
	mysqli_close($con);
	print(json_encode($result));

?>