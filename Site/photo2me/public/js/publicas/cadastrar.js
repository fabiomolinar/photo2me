var _FtmCadastrar = (function(){
  var _formulario;
  var _formGrid;

  var _inicializarVariaveis = function(){
    _formulario = $('.form');
    _formGrid = $('#cadastrar-form-grid');
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
        email: {
          identifier: 'email',
          rules: [
            {
              type: 'empty',
              prompt: _FtmGlobais.fvNomeObrigatorio
            },
            {
              type: 'email',
              prompt: _FtmGlobais.fvEmailFormato
            }
          ]
        },
        password: {
          identifier: 'password',
          rules: [
            {
              type: 'empty',
              prompt: _FtmGlobais.fvPasswordObrigatorio
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
