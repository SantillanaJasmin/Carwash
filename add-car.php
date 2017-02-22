<?php
    $con=mysqli_connect("localhost:3306","root","password","carwash");
    if (mysqli_connect_errno($con))
    {
       echo '{"query_result":"ERROR"}';
    }

    // $car_model = $_GET['model'];
    // $car_type = $_GET['type'];
    // $car_plate = $_GET['plate'];
    $car_model = 'Honda';
    $car_type = 'A';
    $car_plate = 'ABC 123';
    $sql = "INSERT INTO cars(car_model, car_type, car_plate) VALUES ('$car_model', '$car_type', '$car_plate')";

    $res = mysqli_query($con,$sql);

    $res = mysqli_query($con,$sql);

    if($res == true) {
      echo '{"query_result":"SUCCESS"}';
    }
    else{
      echo '{"query_result":"FAILURE"}';
    }
    mysqli_close($con);
?>