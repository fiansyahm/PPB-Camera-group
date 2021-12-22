<?php
	include 'db_config.php';
	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);
	$encodedSch = $_POST['EN_ATT'];
	$parts = [];
	$parts = explode(";", $encodedSch);
	$sqlQuery = "SELECT * FROM `attendance` where `id_akun`='$encodedSch'";
	$res = mysqli_query($con,$sqlQuery);
	$count = mysqli_num_rows($res);
    $currentDateTime = array();
    $workFromHome= array();
    $status= array();
    $photo= array();
    $signature= array();
    while ($row = $res->fetch_assoc()) {
        array_push($currentDateTime,$row["currentDateTime"]);
        array_push($workFromHome,$row["workFromHome"]);
        array_push($status,$row["status"]);
        array_push($photo,$row["photo"]);
        array_push($signature,$row["signature"]);
    }
	if(mysqli_query($con, $sqlQuery)&&$count>=1){

		$result["status"] = TRUE;
		$result["remarks"] = "Get Data Successfully";
		$result["currentdatetime"] =$currentDateTime;
		$result["workfromhome"] =$workFromHome;
		$result["attendancestatus"] =$status;
		$result["photo"] =$photo;
		$result["signature"] =$signature;
		$result["totalrow"] = $count;
	}else{
		$result["status"] = FALSE;
		$result["remarks"] = "Get Data Failed";
	}
	mysqli_close($con);
	print(json_encode($result));
?>