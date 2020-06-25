<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="text-center">
<form class="form-signin" method="post">
    <img class="mx-auto rounded-circle img-fluid"
         style="height: 100%; width: 100%; max-height: 100px; max-width: 100px; object-fit: cover"
         src="https://icon2.cleanpng.com/20180622/pwy/kisspng-cercanas-bilbao-zorrotza-renfe-operadora-cercan-tinder-5b2d1716c16048.0838255415296816867921.jpg">
    <h1 class="h3 mb-3 font-weight-normal">Please sign up</h1>
    <input type="text" name="name" id="inputEmail" class="form-control" placeholder="Name" required autofocus>
    <input type="text" name="surname" id="inputEmail" class="form-control" placeholder="Surname" required>
    <input type="url" name="url_photo" id="inputEmail" class="form-control"
           placeholder="photo URL (not a mandatory field)">
    <input type="text" name="job" id="inputPassword" class="form-control" placeholder="Job" required>
    <input type="text" name="username" id="inputEmail" class="form-control" placeholder="Username" required>
    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
    or <br>
    <a href="/login">Back to login</a>
    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2018</p>
</form>
</body>
</html>