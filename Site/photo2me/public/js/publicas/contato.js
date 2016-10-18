var _FtmContato = (function(){
  var _formGrid;
  var _formulario;

  var _inicializarVariaveis = function(){
    _formGrid = $('#contato-form-grid');
    _formulario = $('.form');
  };
  var _setarTamanhoForm = function(){
    var larguraTela = $(window).width();
    if (larguraTela > 767){
      _formGrid.addClass('two column');
    } else {
      _formGrid.removeClass('two column');
    }
  };
  var _setValidation = function(){
    _formulario.form({
      fields: {
        nome: {
          identifier: 'nome',
          rules: [
            {
              type: 'empty',
              prompt: _FtmGlobais.fvNomeObrigatorio
            }
          ]
        },
        email: {
          identifier: 'email',
          rules: [
            {
              type: 'email',
              prompt: _FtmGlobais.fvEmailFormato
            },
            {
              type: 'empty',
              prompt: _FtmGlobais.fvEmailObrigatorio
            }
          ]
        }
      }
    });
  };
  var _initEResize = function(){
    _setarTamanhoForm();
  };
  var resize = function(){
    _initEResize();
  };
  var init = function(){
    _inicializarVariaveis();
    _initEResize();
    _setValidation();
  };

  return {
    init: init,
    resize: resize,
  };
})();
