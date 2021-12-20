<?php

	include 'db_config.php';

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);
	

	$encodedSch = $_POST['EN_SCH'];
	$parts = [];
	$parts = explode(";", $encodedSch);
	
// 	$sqlQuery = "SELECT `nama`,`password` FROM `register`where `nama`='$parts[0]' AND `password`='$parts[1]'";
    $sqlQuery = "SELECT `nama` FROM `schedule`";
	$res = mysqli_query($con,$sqlQuery);
	$count = mysqli_num_rows($res);
    
    
    // $directors = array( "a");
    $directors = array();
    // $row=$res->fetch_assoc();
    while ($row = $res->fetch_assoc()) {
        array_push($directors,$row["nama"]);
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