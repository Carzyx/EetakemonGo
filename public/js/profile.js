
$(function() {
    var $formIncial = $('#Perfil-Inicial');
    var $formEditar = $('#Editar-Perfil');
    var $divForms = $('#div-forms');
    var $modalAnimateTime = 300;
    var $msgAnimateTime = 150;
    var $msgShowTime = 2000;

    $('#boton_cancelar_perfil').click( function () { modalAnimate($formIncial, $formEditar); });
    $('#boton_editar_perfil').click( function () { modalAnimate($formEditar, $formIncial); });
    
    function modalAnimate ($oldForm, $newForm) {
        var $oldH = $oldForm.height();
        var $newH = $newForm.height();
        $divForms.css("height",$oldH);
        $oldForm.fadeToggle($modalAnimateTime, function(){
            $divForms.animate({height: $newH}, $modalAnimateTime, function(){
                $newForm.fadeToggle($modalAnimateTime);
            });
        });
    }

    var $input = $('input:text'),
        $register = $('#boton_editar_user');

    $register.attr('disabled', true);
    $input.keyup(function() {
        var trigger = false;
        $input.each(function() {
            if (!$(this).val()) {
                trigger = true;
            }
        });
        trigger ? $register.attr('disabled', true) : $register.removeAttr('disabled');
    });



});
function infoPerfilUsuario() {
    sessionStorage.clear();
    var usuarioabuscar = $("#usuarioabuscar").val();
    var url = "http://localhost:8080/myapp/web/getUserByUsername/"+usuarioabuscar;
    console.log(url);
    var jsonResult;

    $.ajax({
        type: "GET",
        url: url,
        dataType: 'application/json',
        complete: function (data) {
            jsonResult = JSON.parse(data.responseText);
            rellenarInfoPerfil(jsonResult);
            sessionStorage.setItem("infoUser",JSON.stringify(jsonResult));
        }
    });
}
function rellenarInfoPerfil(data) {
    document.getElementById("Pname").innerHTML=data.name;
    document.getElementById("Psurname").innerHTML=data.surname;
    document.getElementById("Pusername").innerHTML=data.username;
    document.getElementById("PsizeEtakemons").innerHTML='-666';
    document.getElementById("Pemail").innerHTML=data.email;
}

function rellenarInfoUpdate() {
    var jsonUser=JSON.parse(sessionStorage.getItem("infoUser"));
    $('#edditName').attr('placeholder',jsonUser.name);
    $('#edditSurname').attr('placeholder',jsonUser.surname);
    $('#edditUsername').attr('placeholder',jsonUser.username);
    $('#edditEmail').attr('placeholder',jsonUser.email);


}

function updateUser() {
    var jsonUser=JSON.parse(sessionStorage.getItem("infoUser"));

    var name = $("#edditName").val();
    var surname = $("#edditSurname").val();
    var username = $("#edditUsername").val();
    var email = $("#edditEmail").val();
    var pass= jsonUser.password;

    var sendInfo = {
        name: name,
        surname: surname,
        email: email,
        username: username,
        password: pass
    };

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/myapp/web/updateUser",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(sendInfo),
        statusCode: {
            201: function (msg) {
                if (msg) {
                    alert(msg.responseText.toString());
                    location.reload(true);

                } else {
                    alert("Error in the execution...");
                }
            },
            200: function (msg) {
                if (msg) {
                    alert(msg.responseText.toString());
                    location.reload(true);

                } else {
                    alert("Error in the execution...");
                }
            }
        }

    })
}