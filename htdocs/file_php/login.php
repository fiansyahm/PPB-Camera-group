<?php

	include 'db_config.php';

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);

	$encodedImage = $_POST['EN_LOGIN'];

	$sqlQuery = "INSERT INTO `id`(`id`) VALUES ('$encodedImage')";

	if(mysqli_query($con, $sqlQuery)){

		// file_put_contents($imageLocation, base64_decode($encodedImage));

		$result["status"] = TRUE;
		$result["remarks"] = "Image Uploaded Successfully";

	}else{

		$result["status"] = FALSE;
		$result["remarks"] = "Image Uploading Failed";

	}

	mysqli_close($con);

	print(json_encode($result));

?>