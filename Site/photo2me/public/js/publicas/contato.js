var _FtmContato = (function(){
  var formGrid;

  var _inicializarVariaveis = function(){
    formGrid = $('#contato-form-grid');
  };
  var _setarTamanhoForm = function(){
    var larguraTela = $(window).width();
    if (larguraTela > 767){
      formGrid.addClass('two column');
    } else {
      formGrid.removeClass('two column');
    }
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
  };

  return {
    init: init,
    resize: resize,
  };
})();
