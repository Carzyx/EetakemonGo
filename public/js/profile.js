
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
    
});
