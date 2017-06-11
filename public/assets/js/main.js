/*
	Alpha by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
*/

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

})(jQuery);


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
};



function GetAllUsers(selector) {
    var arr = [ "name", "surname", "username", "password", "email", "rol", "image" ];
    var obj  = { name:1, surname:2, username:3, password:4, email:5, rol:6, image:7 };

    var jsonResult;
    $.ajax({
        type: "GET",
        url: " http://localhost:8080/myapp/web/getAllUsers",
        dataType: 'application/json',
        complete: function (data) {
            jsonResult = JSON.parse(data.responseText);
            buildHtmlTable(selector, jsonResult)

        }
    });

};

function GetAllEetakemons(selector) {
    var arr = [ "name", "level", "ps", "type", "description",  "image" ];
    var obj  = { name:1, level:2, ps:3, type:4, description:5,  image:6 };

    var jsonResult;
    $.ajax({
        type: "GET",
        url: " http://localhost:8080/myapp/web/getAllEetakemons",
        dataType: 'application/json',
        complete: function (data) {
            jsonResult = JSON.parse(data.responseText);
            buildHtmlTable(selector, jsonResult)

        }
    });

}

function GetAllAtacks(selector) {
    var arr = [ "name", "type", "damageBase", "description"];
    var obj  = { name:1, type:2, damageBase:3, description:4};

    var jsonResult;
    $.ajax({
        type: "GET",
        url: " http://localhost:8080/myapp/web/getAllAtacks",
        dataType: 'application/json',
        complete: function (data) {
            jsonResult = JSON.parse(data.responseText);
            buildHtmlTable(selector, jsonResult)

        }
    });

};

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
            urlAction = "http://localhost:8080/myapp/web/createUser";
            break;

        case "Remove":
            urlAction = "http://localhost:8080/myapp/web/removeUserByUsernameAndPassword";
            break;

        case "Update":
            urlAction = "http://localhost:8080/myapp/web/updateByUsernameAndPassword";
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

    $.ajax({
        type: "POST",
        url: urlAction,
        contentType: "application/json",
        success: function (msg) {
            if (msg) {
                alert(msg);
                location.reload(true);
                var table = document.getElementById('jsonTableUsersResult')
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
            urlAction = "http://localhost:8080/myapp/web/createEetakemon";
            break;

        case "Remove":
            urlAction = "http://localhost:8080/myapp/web/removeEetakemon";
            break;

        case "Update":
            urlAction = "http://localhost:8080/myapp/web/updateEetakemon";
            break;
    }

    var name = $("#name").val();

    var atackType = document.getElementById('OptionAtackType');
    var atackTypeSelected = atackType.options[atackType.selectedIndex].text;

    var type = $("#type").val();
    var description = $("#description").val();

    var sendInfo = {
        name: name,
        type: atackTypeSelected,
        dagameBase: damage,
        description: description,
    };

    $.ajax({
        type: "POST",
        url: urlAction,
        contentType: "application/json",
        success: function (msg) {
            if (msg) {
                alert(msg);
                location.reload(true);
                var table = document.getElementById('jsonTableAtacksResult')
                table.parentNode.removeChild(table);
                GetAllUsers('#jsonTableAtacksResult');

            } else {
                alert("Error in the execution...");
            }
        },

        data: JSON.stringify(sendInfo)
    });

}


function doActionDatabaseAtack() {
    var option = document.getElementById('selectOptionDatabase');
    var optionSelected = option.options[option.selectedIndex].text;
    var urlAction;

    switch(optionSelected)
    {
        case "Add":
            urlAction = "http://localhost:8080/myapp/web/createAtack";
            break;

        case "Remove":
            urlAction = "http://localhost:8080/myapp/web/removeAtack";
            break;

        case "Update":
            urlAction = "http://localhost:8080/myapp/web/updateAtack";
            break;
    }

    var name = $("#name").val();

    var atackType = document.getElementById('OptionAtackType');
    var atackTypeSelected = atackType.options[atackType.selectedIndex].text;

    var type = $("#type").val();
    var description = $("#description").val();

    var sendInfo = {
        name: name,
        type: atackTypeSelected,
        dagameBase: damage,
        description: description,
    };

    $.ajax({
        type: "POST",
        url: urlAction,
        contentType: "application/json",
        success: function (msg) {
            if (msg) {
                alert(msg);
                location.reload(true);
                var table = document.getElementById('jsonTableAtacksResult')
                table.parentNode.removeChild(table);
                GetAllUsers('#jsonTableAtacksResult');

            } else {
                alert("Error in the execution...");
            }
        },

        data: JSON.stringify(sendInfo)
    });

}


function TablaVisibleParaOtros(selector, data) {//falta poner headers
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
        tr.push('background: url(pokemons/'+json[i].image+'.gif) 0 0 no-repeat;">');//style front

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

function GetAllEetakemonsPrueba(selector) {
    var arr = [ "name", "level", "ps", "type", "description",  "image" ];
    var obj  = { name:1, level:2, ps:3, type:4, description:5,  image:6 };

    var jsonResult;
    $.ajax({
        type: "GET",
        url: " http://localhost:8080/myapp/web/getAllEetakemons",
        dataType: 'application/json',
        complete: function (data) {
            jsonResult = JSON.parse(data.responseText);
            console.log(jsonResult);
            EetakedexConstructor(selector, jsonResult);

        }
    });

}

///////////////////////////////////////////////////////////////////////////

function GetTablaVisibleUsersRegistrados(selector) {
    var arr = [ "name", "surname", "username", "password", "email", "rol", "image" ];
    var obj  = { name:1, surname:2, username:3, password:4, email:5, rol:6, image:7 };

    var jsonResult;
    $.ajax({
        type: "GET",
        url: " http://localhost:8080/myapp/web/getAllUsers",
        dataType: 'application/json',
        complete: function (data) {
            jsonResult = JSON.parse(data.responseText);
            TablaVisibleParaOtros(selector, jsonResult)

        }
    });

}



function TestAlert() {
    alert("hello world");
}
