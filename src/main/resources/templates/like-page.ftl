<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="css/style.css">
</head>
<body style="background-color: #f5f5f5;">
<div class="col-4 offset-4">
    <form action="" method="post">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-12 col-lg-12 col-md-12 text-center">
                        <img src="${picture}" class="mx-auto rounded-circle img-fluid"
                             style="height: 300px; width: 300px; ">
                        <h3 class="mb-0 text-truncated" style="font-size:3vw">${name}</h3>
                        <input type="hidden" name="id" value="${id}">
                        <br>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button type="submit" name="choice" value="NO" class="btn btn-outline-danger btn-block"><span
                                    class="fa fa-times"></span> Dislike
                        </button>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button type="submit" name="choice" value="YES" class="btn btn-outline-success btn-block"><span
                                    class="fa fa-heart"></span> Like
                        </button>
                    </div>
                </div>
                <!--/row-->
            </div>
        </div>
        <a href="/liked" class="btn btn-success btn-block">Go to chat room</a>
        <a href="/logout" class="btn btn-dark btn-block">Log out</a>
    </form>
</div>
</body>
</html>