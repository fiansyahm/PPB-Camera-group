<?php

	include 'db_config.php';

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);

	$encodedAtt = $_POST['EN_PROF'];
	$parts = [];
	$parts = explode(";", $encodedAtt);

	$sql = "SELECT `profil_picture` FROM `register` where `id`='$parts[0]'";
	$res = mysqli_query($con,$sql);
	$count = mysqli_num_rows($res);
	// $count=1;
	$imageTitle = "myProfilImage$parts[0]";
	$imageLocation = "profileimages/$imageTitle.jpg";

    if($count==1){
            if (file_exists($imageLocation)) {
                unlink($imageLocation);
                // echo 'File '.$filename.' has been deleted';
            } else {
                // echo 'Could not delete '.$filename.', file does not exist';
            }
    }

    
	$sqlQuery = "UPDATE `register` SET `profil_picture`='$imageTitle' where `id`='$parts[0]'";
	
	
	if(mysqli_query($con, $sqlQuery)){
		file_put_contents($imageLocation, base64_decode($parts[1]));
		$result["status"] = TRUE;
		$result["nama"] =	$imageTitle;
		$result["remarks"] = "Update Profile Successfully";

	}else{

		$result["status"] = FALSE;
		$result["remarks"] = "Update Profile  Failed";

	}

	mysqli_close($con);

	print(json_encode($result));

?>