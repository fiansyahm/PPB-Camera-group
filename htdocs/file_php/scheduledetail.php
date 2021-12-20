<?php

	include 'db_config.php';

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);
	

	$encodedSch = $_POST['EN_SCHDET'];
	$parts = [];
	$parts = explode(";", $encodedSch);
	
	$sqlQuery = "SELECT `nama`,`datang_awal`,`datang`,`pulang`,`pulang_akhir` FROM `schedule` where `id`='$encodedSch'";
	$res = mysqli_query($con,$sqlQuery);
	$count = mysqli_num_rows($res);
    
    
    // $directors = array( "a");
    $directors = array();
    // $row=$res->fetch_assoc();
    while ($row = $res->fetch_assoc()) {
        array_push($directors,$row["datang_awal"]);
        array_push($directors,$row["datang"]);
        array_push($directors,$row["pulang"]);
        array_push($directors,$row["pulang_akhir"]);
    }
	
	

	if(mysqli_query($con, $sqlQuery)&&$count>=1){

		$result["status"] = TRUE;
		$result["remarks"] = "Get Data Successfully";
		$result["schedule"] = $directors;
		$result["totalrow"] = $count;
		
		

	}else{

		$result["status"] = FALSE;
		$result["remarks"] = "Get Data Failed";

	}

	mysqli_close($con);

	print(json_encode($result));

?>