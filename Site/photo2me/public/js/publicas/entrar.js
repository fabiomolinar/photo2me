var _FtmEntrar = (function(){
  var _formulario;

  var _inicializarVariaveis = function(){
    _formulario = $('.form');
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
