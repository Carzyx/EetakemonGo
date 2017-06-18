(function($) {

    skel.breakpoints({
        wide: '(max-width: 1680px)',
        normal: '(max-width: 1280px)',
        narrow: '(max-width: 980px)',
        narrower: '(max-width: 840px)',
        mobile: '(max-width: 736px)',
        mobilep: '(max-width: 480px)'
    });

    $(function() {

        var	$window = $(window),
            $body = $('body'),
            $header = $('#header'),
            $banner = $('#banner');

        // Fix: Placeholder polyfill.
        $('form').placeholder();

        // Prioritize "important" elements on narrower.
        skel.on('+narrower -narrower', function() {
            $.prioritize(
                '.important\\28 narrower\\29',
                skel.breakpoint('narrower').active
            );
        });

        // Dropdowns.
        $('#nav > ul').dropotron({
            alignment: 'right'
        });

        // Off-Canvas Navigation.

        // Navigation Button.
        $(
            '<div id="navButton">' +
            '<a href="#navPanel" class="toggle"></a>' +
            '</div>'
        )
            .appendTo($body);

        // Navigation Panel.
        $(
            '<div id="navPanel">' +
            '<nav>' +
            $('#nav').navList() +
            '</nav>' +
            '</div>'
        )
            .appendTo($body)
            .panel({
                delay: 500,
                hideOnClick: true,
                hideOnSwipe: true,
                resetScroll: true,
                resetForms: true,
                side: 'left',
                target: $body,
                visibleClass: 'navPanel-visible'
            });

        // Fix: Remove navPanel transitions on WP<10 (poor/buggy performance).
        if (skel.vars.os == 'wp' && skel.vars.osVersion < 10)
            $('#navButton, #navPanel, #page-wrapper')
                .css('transition', 'none');

        // Header.
        // If the header is using "alt" styling and #banner is present, use scrollwatch
        // to revert it back to normal styling once the user scrolls past the banner.
        // Note: This is disabled on mobile devices.
        if (!skel.vars.mobile
            &&	$header.hasClass('alt')
            &&	$banner.length > 0) {

            $window.on('load', function() {

                $banner.scrollwatch({
                    delay:		0,
                    range:		0.5,
                    anchor:		'top',
                    on:			function() { $header.addClass('alt reveal'); },
                    off:		function() { $header.removeClass('alt'); }
                });

            });

        }

    });
    $('[id^=edit]').keypress(validateNumber);//PARA SOLO INTRODUCIR NUMEROS
    ////Hace que el boton de ataques este deshabilitado

    var $input = $('input:text'),
        $register = $('#atackButton');

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

    ////Acabo Prieba button disabled

})(jQuery);

function validateNumber(event) {
    var key = window.event ? event.keyCode : event.which;
    if (event.keyCode === 8 || event.keyCode === 46) {
        return true;
    } else if ( key < 48 || key > 57 ) {
        return false;
    } else {
        return true;
    }
}


class User {
    constructor() {
        this.name;
        this.surname;
        this.username;
        this.password;
        this.email;
        this.rol;
        this.image;
        this.authToken;
        this.eetakemons;
    }
};
var user;

function getUserFromCookie() {
    user = Cookies.get(user, User.class);
    if(user.charAt(0) === '"' && user.charAt(user.length-1) ==='"')
    {
        user = user.substr(1,user.length-1);
    }
    responseToUser(user);
    return user;
}
function redirectUrl(ulr) {
    if (ulr !== undefined && ulr) {
        window.location.href = ulr;
    }
}
function doAjaxRequest(method, url, data, callback, param1, param2) {
    $.ajax({
        type: method,
        url: url,
        contentType: "application/json",
        success: function (msg) {
            responseToUser(msg, this.success.arguments[2].getResponseHeader("Authoritzation"));
            callback(param1, param2);
        },
        error: function(){
            return false;
        },
        data: JSON.stringify(data)
    });
}
function responseToUser(response, authToken) {
    user = new User();
    user.name = response.name;
    user.surname = response.surname;
    user.username = response.username;
    user.password = response.password;
    user.email = response.email;
    user.rol = response.rol;
    user.image = response.image;
    user.authToken = authToken;

    Cookies.set('user', user, { expires: 1 });}


function RegisterUser() {

    var name = $("#name").val();
    var surname = $("#surname").val();
    var username = $("#username").val();
    var password = $("#password").val();
    var email = $("#email").val();

    var sendInfo = {
        name: name,
        surname: surname,
        username: username,
        password: password,
        email: email
    };

    $.ajax({
        type: "POST",
        url: " http://localhost:8080/myapp/web/createUser",
        contentType: "application/json",
        success: function (msg) {
            if (msg) {
                alert("Somebody" + name + " was added in list !");
                location.reload(true);
            } else {
                alert("Cannot add to list !");
            }
        },

        data: JSON.stringify(sendInfo)
    });
}

function SignIn() {
    var username = $("#username").val();
    var password = $("#password").val();

    user = new User();
    user.username = username;
    user.password = password;

    var url = "http://localhost:8080/myapp/UserService/singIn";
    var method = "POST";

    var redirect = "http://localhost:8080/home.html";

    var successRequest = doAjaxRequest(method, url, user, redirectUrl, redirect);


}

function doAjaxRequestManageDtabase(method, url, authToken, callback, param1) {
    $.ajax({
        beforeSend: function(xhr){xhr.setRequestHeader('Authoritzation', authToken);},
        type: method,
        url: url,
        dataType: 'application/json',
        complete: function (data) {
            user.authToken = data.getResponseHeader("Authoritzation");
            var jsonResult = JSON.parse(data.responseText);
            callback(param1, jsonResult)
        }
    });
}


function GetAllUsers(selector) {
    var arr = [ "name", "surname", "username", "password", "email", "rol", "image" ];
    var obj  = { name:1, surname:2, username:3, password:4, email:5, rol:6, image:7 };


    var method = "GET";
    var url = "http://localhost:8080/myapp/UserService/getAllUsers";
    user =  JSON.parse(Cookies.get("user", User.class));
    doAjaxRequestManageDtabase(method, url, user.authToken, buildHtmlTable, selector);
}


function BotonElegirAtaques(selector) {//consulta+guardar en session storage+crear lista dropdown

    sessionStorage.removeItem("Ataques");

    user =  JSON.parse(Cookies.get("user", User.class));

    $.ajax({
        beforeSend: function(xhr){xhr.setRequestHeader('Authoritzation', user.authToken);},
        type: "GET",
        url: " http://localhost:8080/myapp/AtackService/getAllAtacks",
        dataType: 'application/json',
        complete: function (data) {
            var jsonResult = JSON.parse(data.responseText);
            sessionStorage.setItem("Ataques",JSON.stringify(jsonResult));//sirve para mostrar los atributos del ataque
            dropDownList(selector,jsonResult);
        }
    });
}
function dropDownList(selector,data) {//borra las options anteriores y añade nuevas
    $(selector).empty();//.append($('<option/>').attr("value", -666).text('Elije un ataque'));
    $.each(data, function(i, option) {
        $(selector).append($('<option/>').attr("value", option.name).text(option.name));
    });
}


function clickAtaque(name) {//obtener ataques de session sorage y mostrar atributos
    var data = JSON.parse(sessionStorage.getItem("Ataques"));
    $.each(data, function(i, option){
        if (option.name==name){
            document.getElementById("nombre").innerHTML=option.name;
            document.getElementById("tipo").innerHTML=option.type;
            document.getElementById("damage").innerHTML=option.damageBase;
            document.getElementById("descripcion").innerHTML=option.description;
        }
    });
}
function guardarAtaque() {
    sessionStorage.removeItem("Atack4");

    var select = document.getElementById('OptionAtack');

    var selectedValues = $(select).val();
    console.log(selectedValues);

    /*var optionSelected = select.options[select.selectedIndex].text;
     console.log(optionSelected);*/
    if (selectedValues.length>4||selectedValues.length<4){
        alert("Selecciona solo 4 ataques");
        location.reload(true);
    }

    var data = JSON.parse(sessionStorage.getItem("Ataques"));
    var Atack;
    var Atack4=[];
    for (var j = 0; j < selectedValues.length; j++){
        $.each(data, function(i, option){
            if (option.name==selectedValues[j]){
                Atack = {
                    name: option.name,
                    type: option.type,
                    damageBase: option.damageBase,
                    description: option.description
                };
                Atack4.push(Atack);
            }
        });
    }

    sessionStorage.setItem('Atack4',JSON.stringify(Atack4));
    console.log(sessionStorage.getItem('Atack4'));
}


function GetAllEetakemons(selector) {
    var arr = [ "name", "level", "ps", "type", "description",  "image" ];
    var obj  = { name:1, level:2, ps:3, type:4, description:5,  image:6 };

    var method = "GET";
    var url = "http://localhost:8080/myapp/EetakemonService/getAllEetakemons";
    user =  JSON.parse(Cookies.get("user", User.class));
    doAjaxRequestManageDtabase(method, url, user.authToken, buildHtmlTable, selector);

}


function GetAllAtacks(selector) {
    var arr = [ "name", "type", "damageBase", "description"];
    var obj  = { name:1, type:2, damageBase:3, description:4};


    var method = "GET";
    var url = "http://localhost:8080/myapp/AtackService/getAllAtacks";
    user =  JSON.parse(Cookies.get("user", User.class));
    doAjaxRequestManageDtabase(method, url, user.authToken, buildHtmlTable, selector);

}

function buildHtmlTable(selector, myList) {
    var columns = addAllColumnHeaders(myList, selector);

    for (var i = 0; i < myList.length; i++) {
        var row$ = $('<tr/>');
        for (var colIndex = 0; colIndex < columns.length; colIndex++) {
            var cellValue = myList[i][columns[colIndex]];
            if (cellValue == null) cellValue = "";
            row$.append($('<td/>').html(cellValue));
        }
        $(selector).append(row$);
    }
}

function addAllColumnHeaders(myList, selector) {
    var columnSet = [];
    var headerTr$ = $('<tr/>');

    for (var i = 0; i < myList.length; i++) {
        var rowHash = myList[i];
        for (var key in rowHash) {
            if ($.inArray(key, columnSet) == -1) {
                columnSet.push(key);
                headerTr$.append($('<th/>').html(key));
            }
        }
    }
    $(selector).append(headerTr$);

    return columnSet;
}

function showDiv(elem){
    if(elem.value == 1)
    {
        document.getElementById('databaseForm').style.display = "block";

    }
    else
    {
        document.getElementById('databaseForm').style.display = "none";
    }
}


function doActionDatabaseUser() {
    var option = document.getElementById('selectOptionDatabase');
    var optionSelected = option.options[option.selectedIndex].text;
    var urlAction;

    switch(optionSelected)
    {
        case "Add":
            urlAction = "http://localhost:8080/myapp/UserService/createUser";
            break;

        case "Remove":
            urlAction = "http://localhost:8080/myapp/UserService/removeUserByUsernameAndPassword";
            break;

        case "Update":
            urlAction = "http://localhost:8080/myapp/UserService/updateByUsernameAndPassword";
            break;
    }

    var name = $("#name").val();
    var surname = $("#surname").val();
    var username = $("#username").val();
    var password = $("#password").val();
    var email = $("#email").val();

    var sendInfo = {
        name: name,
        surname: surname,
        username: username,
        password: password,
        email: email
    };

    user =  JSON.parse(Cookies.get("user", User.class));
    console.log(user);

    $.ajax({
        beforeSend: function(xhr){xhr.setRequestHeader('Authoritzation', user.authToken);},
        type: "POST",
        url: urlAction,
        contentType: "application/json",
        success: function (msg) {
            if (msg) {
                alert(msg);
                location.reload(true);
                var table = document.getElementById('jsonTableUsersResult');
                table.parentNode.removeChild(table);
                GetAllUsers('#jsonTableUsersResult');

            } else {
                alert("Error in the execution...");
            }
        },

        data: JSON.stringify(sendInfo)
    });

}

function doActionDatabaseEetakemon() {
    var option = document.getElementById('selectOptionDatabase');
    var optionSelected = option.options[option.selectedIndex].text;
    var urlAction;

    switch(optionSelected)
    {
        case "Add":
            urlAction = "http://localhost:8080/myapp/EetakemonService/createEetakemon";
            break;

        case "Remove":
            urlAction = "http://localhost:8080/myapp/EetakemonService/removeEetakemon";
            break;

        case "Update":
            urlAction = "http://localhost:8080/myapp/EetakemonService/updateEetakemon";
            break;
    }

    var name = $("#name").val();
    var level = $("#editLevel").val();
    var ps = $("#editPS").val();

    var atackType = document.getElementById('OptionAtackType');
    var atackTypeSelected = atackType.options[atackType.selectedIndex].text;

    var image="pokemons/"+name+".png";
    var description = $("#description").val();

    var atackList=JSON.parse(sessionStorage.getItem('Atack4'));


    var sendInfo = {
        name: name,
        ps: ps,
        type: atackTypeSelected,
        image: image,
        description: description,
        eetakemonAtack: atackList
    };
    console.log(sendInfo);

    user =  JSON.parse(Cookies.get("user", User.class));
    console.log(user);

    $.ajax({
        beforeSend: function(xhr){xhr.setRequestHeader('Authoritzation', user.authToken);},
        type: "POST",
        url: urlAction,
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(sendInfo),

        success: function (msg) {
            if (msg) {
                alert(msg);
                location.reload(true);
                var table = document.getElementById('jsonTableUsersResult');
                table.parentNode.removeChild(table);
                GetAllUsers('#jsonTableUsersResult');

            } else {
                alert("Error in the execution...");
            }
        }

    });

}


function doActionDatabaseAtack() {//demage base siempre es 0, NaN, undefined
    var option = document.getElementById('selectOptionDatabase');
    var optionSelected = option.options[option.selectedIndex].text;
    var urlAction;

    switch(optionSelected)
    {
        case "Add":
            urlAction = "http://localhost:8080/myapp/AtackService/createAtack";
            break;

        case "Remove":
            urlAction = "http://localhost:8080/myapp/AtackService/removeAtack";
            break;

        case "Update":
            urlAction = "http://localhost:8080/myapp/AtackService/updateAtack";
            break;
    }

    var name = $("#name").val();

    var atackType = document.getElementById('OptionAtackType');
    var type = atackType.options[atackType.selectedIndex].text;

    //var type = $("#type").val();
    var description = $("#description").val();
    var damageBase = parseInt($("#edit1").val());

    var sendInfo = {
        name: name,
        type: type,
        dagameBase: damageBase,
        description: description
    };
    console.log(sendInfo);

    user =  JSON.parse(Cookies.get("user", User.class));
    console.log(user);

    $.ajax({
        beforeSend: function(xhr){xhr.setRequestHeader('Authoritzation', user.authToken);},
        type: "POST",
        url: urlAction,
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(sendInfo),

        success: function (msg) {
            if (msg) {
                alert(msg);
                location.reload(true);
                var table = document.getElementById('jsonTableUsersResult');
                table.parentNode.removeChild(table);
                GetAllUsers('#jsonTableUsersResult');

            } else {
                alert("Error in the execution...");
            }
        }
    });
}


function NormalUserTableConstructor(selector, data) {//falta poner headers
    var tr;
    for (var i = 0; i < data.length; i++) {
        tr = $('<tr/>');
        tr.append("<td>" + data[i].username + "</td>");
        tr.append("<td>" + data[i].name + "</td>");
        tr.append("<td>" + data[i].surname + "</td>");
        tr.append("<td>" + data[i].email + "</td>");
        $(selector).append(tr);
    }
}
////////////////////////////////////////////////////////////////////pruebas gurki etakedex


function EetakedexConstructor(selector,json) {
    var tr=[];
    for (var i = 0; i < json.length; i++) {


        tr.push('<div class="6u 12u(mobilep)"><div class="flip-container"><div class="flipper">');
        tr.push('<div class="front" style="');
        tr.push('background: url('+json[i].image+') 0 0 no-repeat;">');//style front

        tr.push('<span class="name">'+json[i].name+'</span></div>');//nombre front

        tr.push('<div class="back" style="');//style back
        tr.push('background:#f8f8f8;">');//style back

        tr.push('<p>Name: '+json[i].name+'</p><p>Type: '+json[i].type+'</p><p>Level: '+json[i].level+'</p><p>PS: '+json[i].ps+'</p><p>Descripción: '+json[i].description+'</p>');

        tr.push('</div> </div> </div> </div>');

        //tr.push("<td>" + json[i].User_Name + "</td>");
        //tr.push("<td>" + json[i].score + "</td>");
        //tr.push("<td>" + json[i].team + "</td>");
        //tr.push('</tr>');
    }
    $(selector).append($(tr.join('')));
}

function GetAllEetakemonsFlipCards(selector) {
    var arr = [ "name", "level", "ps", "type", "description",  "image" ];
    var obj  = { name:1, level:2, ps:3, type:4, description:5,  image:6 };

    var jsonResult;

    var method = "GET";
    var url = "http://localhost:8080/myapp/EetakemonService/getAllEetakemons";
    user =  JSON.parse(Cookies.get("user", User.class));
    doAjaxRequestManageDtabase(method, url, user.authToken, EetakedexConstructor, selector);

}
function GetTusEetakemonsFlipCards(selector) {
    user =  JSON.parse(Cookies.get("user", User.class));
    var method = "GET";
    var url = "http://localhost:8080/myapp/UserService/getCompleteUserByNme/"+user.username;
    console.log(url);

    doAjaxRequestManageDtabase(method, url, user.authToken, SacarListaDeEtakemonsDeUnUsuario, selector);
}

function SacarListaDeEtakemonsDeUnUsuario(selector,jsonResult) {
    var eetakemons = jsonResult.eetakemons;
    EetakedexConstructor(selector,eetakemons);
}


///////////////////////////////////////////////////////////////////////////

function GetTablaVisibleUsersRegistrados(selector) {
    var arr = [ "name", "surname", "username", "password", "email", "rol", "image" ];
    var obj  = { name:1, surname:2, username:3, password:4, email:5, rol:6, image:7 };

    var jsonResult;

    var method = "GET";
    var url = "http://localhost:8080/myapp/UserService/getAllUsers";
    user =  JSON.parse(Cookies.get("user", User.class));
    doAjaxRequestManageDtabase(method, url, user.authToken, NormalUserTableConstructor, selector);


}



function TestAlert() {
    alert("hello world");
}
