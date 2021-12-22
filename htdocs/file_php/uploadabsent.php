<?php

	include 'db_config.php';

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);
	$encodedAtt = $_POST['EN_ABSENT'];
	$parts = [];
	$parts = explode(";", $encodedAtt);
	$sql = "SELECT * FROM `attendance`";
	$res = mysqli_query($con,$sql);
	$count = mysqli_num_rows($res)+1;
	// $count=1;
	$imageTitle = "myImage$count";
	$imageLocation = "photoAbsent/$imageTitle.jpg";
    $kondisi=substr($parts[4],0,6);
    date_default_timezone_set('Asia/Jakarta');
    $inddate=date('Y-m-d H:i:s');
    $inddateonly=date('Y-m-d');
    $sql1 = "SELECT * FROM `absent`  where `id_akun`='$parts[0]' AND `currentDate`='$inddateonly'";
	$res1 = mysqli_query($con,$sql1);
	$count1 = mysqli_num_rows($res1);
    if($count1!=1){
    	$sqlQuery = "INSERT INTO `absent`(`id_akun`,`namaUser`,`jabatanUser`,`tipe_ijin`,`jenis_ijin`,`alasan`,`ijin_mulai`,`ijin_selesai`,`bukti_ijin`,`currentDate`) VALUES ('$parts[0]','$parts[1]', '$parts[2]','$parts[3]','$parts[4]','$parts[5]','$parts[6]','$parts[7]','$imageTitle','$inddateonly')";
    	if(mysqli_query($con, $sqlQuery)){
    		file_put_contents($imageLocation, base64_decode($parts[8]));
    		$result["status"] = TRUE;
    		$result["remarks"] = "Absent Form Uploaded Successfully";
    	}else{
    		$result["status"] = FALSE;
    		$result["remarks"] = "Absent Form Uploaded Failed";
    	}
    }
    else{
    		$result["status"] = FALSE;
    		$result["remarks"] = "Absent Form Uploaded Failed";
    
    }
    
	mysqli_close($con);

	print(json_encode($result));

?>