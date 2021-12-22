<?php
	include 'db_config.php';
	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);
	$encoded = $_POST['EN_MED'];
	$parts = [];
	$parts = explode(";", $encoded);
	$date = date('Y-m-d');
	$sql = "SELECT * FROM `medcheck` where `id_akun`='$parts[0]' AND `currentDate`='$date'";
	$res = mysqli_query($con,$sql);
	$count = mysqli_num_rows($res);
	if($count==0){
	    $sqlQuery = "INSERT INTO `medcheck`(`id_akun`, `Demam`, `Batuk_Pilek`, `Tenggorokan_Sakit`, `Hidung_Tersumbat`, `Nyeri_Kepala`, `Sesak_Nafas`, `Lemas`, `Hilang_Penciuman`, `Diare`, `Kontak_ODP`, `Kondisi_Tubuh`, `Kelayakan`) VALUES ('$parts[0]','$parts[1]', '$parts[2]','$parts[3]','$parts[4]','$parts[5]','$parts[6]','$parts[7]', '$parts[8]','$parts[9]','$parts[10]','$parts[11]', '$parts[12]')";
	   $res= mysqli_query($con, $sqlQuery);
	    $result["status"] = TRUE;
		$result["remarks"] = "Form Uploaded Successfully";
	}
	else{
	    $result["status"] = TRUE;
		$result["remarks"] = "Form have Uploaded before";
	}
	if($res){

	}else{
		$result["status"] = FALSE;
		$result["remarks"] = "Image Uploading Failed";
	}
	mysqli_close($con);
	print(json_encode($result));

?>