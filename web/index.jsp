<%--
  Created by IntelliJ IDEA.
  User: Tanya
  Date: 16.10.2016
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
    <link type="text/css" rel="stylesheet" href="index.css">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
    <script>
        $(document).ready(function () {

            $('.form').find('input, textarea').on('keyup blur focus', function (e) {

                var $this = $(this),
                        label = $this.prev('label');


                if (e.type === 'keyup') {
                    if ($this.val() === '') {
                        label.removeClass('active highlight');
                        $('#error_sign').text('');
                        $('#error_login').text('');

                    } else {
                        label.addClass('active highlight');
                        $('#error_sign').text('');
                        $('#error_login').text('');
                    }
                } else if (e.type === 'blur') {
                    if ($this.val() === '') {
                        label.removeClass('active highlight');
                        $('#error_sign').text('');
                        $('#error_login').text('');
                    } else {
                        label.removeClass('highlight');
                        $('#error_sign').text('');
                        $('#error_login').text('');
                    }
                } else if (e.type === 'focus') {

                    if ($this.val() === '') {
                        label.removeClass('highlight');

                    }
                    else if ($this.val() !== '') {
                        label.addClass('highlight');

                    }
                }


            });

            $('.tab a').on('click', function (e) {

                e.preventDefault();

                $(this).parent().addClass('active');
                $(this).parent().siblings().removeClass('active');

                target = $(this).attr('href');

                $('.tab-content > div').not(target).hide();
                $(target).fadeIn(600);


            });

            if ($('#error_login').text().indexOf('пароль') != -1) {
                $('#alogin').addClass('active');
                $('#asignup').removeClass('active');
                target = $('#login');

                $('.tab-content > div').not(target).hide();
                $(target).fadeIn(600);


            }
        });
    </script>
</head>
<body>
<div class="form">

    <ul class="tab-group">
        <li class="tab active" id="asignup"><a href="#signup">Sign Up</a></li>
        <li class="tab" id="alogin"><a href="#login">Log In</a></li>
    </ul>

    <div class="tab-content">
        <div id="signup">
            <form action="/authorization" method="post">
                <input type="hidden" name="method" value="sign">
                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            First Name<span class="req">*</span>
                        </label>
                        <input type="text" required autocomplete="off" name="first_name"/>
                    </div>

                    <div class="field-wrap">
                        <label>
                            Last Name<span class="req">*</span>
                        </label>
                        <input type="text" required autocomplete="off" name="last_name"/>
                    </div>
                </div>

                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="email" required autocomplete="off" name="email"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Set A Password<span class="req">*</span>
                    </label>
                    <input type="password" required autocomplete="off" name="password"/>
                </div>

                <button type="submit" class="button button-block"/>
                Get Started</button>
                <div class="field-wrap">
                    <label id="error_sign">
                        ${error_sign}
                    </label>

                </div>
            </form>

        </div>

        <div id="login">
            <form action="/authorization" method="post">
                <input type="hidden" name="method" value="login">
                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="email" required autocomplete="off" name="email"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Password<span class="req">*</span>
                    </label>
                    <input type="password" required autocomplete="off" name="password"/>
                </div>

                <button class="button button-block"/>
                Log In</button>
                <div class="field-wrap">
                    <label id="error_login">
                        ${error_login}
                    </label>

                </div>
            </form>

        </div>

    </div><!-- tab-content -->

</div> <!-- /form -->
</body>
</html>
