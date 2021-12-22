<?php

	include 'db_config.php';

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);

	$encodedAtt = $_POST['EN_ATTENDANCE'];
	$parts = [];
	$parts = explode(";", $encodedAtt);

	$sql = "SELECT * FROM `attendance`";
	$res = mysqli_query($con,$sql);
	$count = mysqli_num_rows($res)+1;
	// $count=1;
	$imageTitle = "myImage$count";
	$imageLocation = "photoAttendance/$imageTitle.jpg";

	$signatureTitle = "mySignature$count";
	$signatureLocation = "signatureAttendance/$signatureTitle.jpg";

    $kondisi=substr($parts[4],0,6);
    date_default_timezone_set('Asia/Jakarta');
    $inddate=date('Y-m-d H:i:s');
    $inddateonly=date('Y-m-d');
    
    $sql1 = "SELECT * FROM `attendance`  where `id_akun`='$parts[0]' AND `kondisi`='$kondisi' AND `currentDate`='$inddateonly'";
	$res1 = mysqli_query($con,$sql1);
	$count1 = mysqli_num_rows($res1);

    if($count1!=1){
    	$sqlQuery = "INSERT INTO `attendance`(`id_akun`,`namaUser`,`jabatanUser`,`workFromHome`,`status`,`photo`,`signature`,`kondisi`,`currentDateTime`,`currentDate`) VALUES ('$parts[0]','$parts[1]', '$parts[2]','$parts[3]','$parts[4]','$imageTitle','$signatureTitle','$kondisi','$inddate','$inddateonly')";
    	
    	if(mysqli_query($con, $sqlQuery)){
    		file_put_contents($imageLocation, base64_decode($parts[5]));
    		file_put_contents($signatureLocation, base64_decode($parts[6]));
    		$result["status"] = TRUE;
    		$result["remarks"] = "Attendance Form Uploaded Successfully";
    
    	}else{
    
    		$result["status"] = FALSE;
    		$result["remarks"] = "Attendance Form Uploaded Failed";
    
    	}
    }
    else{
    
    		$result["status"] = FALSE;
    		$result["remarks"] = "Attendance Form Uploaded Failed";
    
    }
    
	mysqli_close($con);

	print(json_encode($result));

?>