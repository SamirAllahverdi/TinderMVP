<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container" style="max-height: 100%">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list likedProfiles as profile>
                                <#assign x="${profile.daysAgo}">
                                <tr>
                                    <td width="10">
                                        <div class="avatar-img">
                                            <img class="img-circle rounded-circle" src="${profile.picture}"
                                                 style="width: 70px; height: 70px; object-fit: cover"/>  
                                        </div>
                                    </td>
                                    <td class="align-middle">
                                        ${profile.name} ${profile.surname} <br>
                                        <a href="/messages/${profile.id}" target="_blank" type="button"
                                           class="btn btn-outline-primary btn-sm">send message</a>
                                    </td>
                                    <td class="align-middle">
                                        ${profile.job}
                                    </td>
                                    <td class="align-middle">
                                        Last Login: ${profile.time} <br><small class="text-muted">
                                            <#if x="0">Today<#else>${profile.daysAgo} days ago</#if>
                                        </small>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <a href="/users" class="btn btn-success btn-block">Find Love</a>
            <a href="/logout" class="btn btn-dark btn-block">Log out</a>
        </div>
    </div>
</div>
</body>
</html>