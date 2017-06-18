<%--
  Created by IntelliJ IDEA.
  User: Miguel Angel
  Date: 11/06/2017
  Time: 3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<html>
<head>
    <title>Etakemon Go</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="assets/css/main.css" />
    <!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
</head>
<body class="landing">
<div id="page-wrapper">

    <!-- Header -->
    <header id="header" class="alt">
        <h1><a href="index.html">Etakemon Go</a> </h1>
        <nav id="nav">
            <ul>
                <li><a href="index.html" class="button">Inicio</a></li>
                <li>
                    <a href="#" class="icon fa-angle-down">Menu</a>
                    <ul>
                        <li><a href="pokedex.html">Pokédex</a></li>
                        <li><a href="contact.html">Contact</a></li>
                        <li><a href="elements.html">Elements</a></li>
                        <li>
                            <a href="">DataBase</a>
                            <ul>
                                <li><a href="usersdatabase.html">Users</a></li>
                                <li><a href="eetakemonsdatabase">Eetakemons</a></li>
                                <li><a href="atacksdatabase">Atacks</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li><a href="signIn.html" class="button">Registrate!</a></li>
            </ul>
        </nav>
    </header>

    <!-- Banner -->
    <section id="banner">
        <h2> <div>
            <a> <img src="pokemons/Etakemon.png"></a></div></h2>

        <section id="cta2">
            <form>
                <div class="row uniform 50%">
                    <div class="12u 12u(mobilep)">
                        <input type="text" name="name" id="name" placeholder="User nick" />
                    </div>
                    <div class="12u 12u(mobilep)">
                        <input type="password" name="password" id="password" placeholder="User password" />
                    </div>
                    <!--<div class="12u 12u(mobilep)">
                        <input type="email" name="email" id="email" placeholder="Email Address" />
                    </div>-->
                </div>
            </form></section>
        <ul class="actions">
            <li><a href="register2.html" class="button">Aun sin cuenta?</a></li>
            <li><a href="home.html" class="button">Iniciar Sesion</a></li>
        </ul>
    </section>


    <!-- Main -->

    <section class="box special features">
        <div class="features-row">
            <section>
                <img src="images\icons\captura.png">
                <h3>Captura!</h3>
                <p>Hazte con todos los Etakemons del mundo, o seras un fracaso y tus amigos se reiran de ti LOOSER.<br><br>
                    GET ON MY LVL BITCH</p>
                <ul class="actions">
                    <li><a href="pokedex.html" class="button alt">Clicka aqui</a></li>
                </ul>
            </section>
            <section>
                <img src="images\icons\evoluciona.png">
                <h3>Evoluciona</h3>
                <p>Evoluciona algo, ya que como persona no lo haces, almenos en un juego, que mas te da..<br><br>
                    Pa que luego digan</p>
                <ul class="actions">
                    <li><a href="pokedex.html" class="button alt">Bueno.. pues aqui</a></li>
                </ul>
            </section>
        </div>
        <div class="features-row">
            <section>
                <img src="images\icons\lucha.png">
                <h3>Lucha contra los rivales</h3>
                <p>Reta al pringao de tu clase  aun duelo a muelte en la pista de baile, o dale una paliza en los increibles duelos<br><br>
                    procura no quedar ultimo</p>
                <ul class="actions">
                    <li><a href="pokedex.html" class="button alt">.. Aunque sea aqui</a></li>
                </ul>
            </section>
            <section>
                <img src="images\icons\mejor.png">
                <h3>Conviertete en el mejor </h3>
                <p>Ya que no has ganado nah en tu vida, que menos que almenos intentar ser el mejor aqui...
                    <br><br>
                    No hay huevos.. uuuh lo que te han dicho</p>
                <ul class="actions">
                    <li><a href="pokedex.html" class="button alt">Que mas te dara..</a></li>
                </ul>
            </section>
        </div>
    </section>
    </section>


    <!-- Footer -->
    <footer id="footer">
        <ul class="icons">
            <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
            <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
            <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
            <li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
            <li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
            <li><a href="#" class="icon fa-google-plus"><span class="label">Google+</span></a></li>
        </ul>
        <ul class="copyright">
            <li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://atenea.upc.edu" target="_blank">Grup Homogeni</a></li>
        </ul>
    </footer>

</div>

<!-- Scripts -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery.dropotron.min.js"></script>
<script src="assets/js/jquery.scrollgress.min.js"></script>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="assets/js/main.js"></script>

</body>
</html>
