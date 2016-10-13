var _FtmMaster = (function(){
  var init = function(){
    $('.ui.sidebar').sidebar('attach events','.top.fixed.menu #header-side-menu','show');    
  };
  return {
    init: init
  };
})();

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

var _FtmHome = (function(){
  var _banerSegment;
  var _alturaMaxBaner = 700;
  var _alturaHeader;
  var _alturaWindow;
  var _alturaDocument;

  var _setarTamanhoBaner = function(){
    if (_banerSegment && _alturaWindow){
      //Tamanho mínimo é o tamanho do próprio conteúdo
      var _conteudo = $('#baner-segment .container');
      var _tamanhoMinimo = _conteudo.height();
      var _banerPadding = _banerSegment.outerHeight(true) - _banerSegment.innerHeight();
      if (_alturaWindow < _tamanhoMinimo){
        _banerSegment.height(_tamanhoMinimo);
      } else if ((_alturaWindow - _alturaHeader) > _alturaMaxBaner){
        _banerSegment.height(_alturaMaxBaner);
      } else {
        _banerSegment.outerHeight(_alturaWindow - _alturaHeader - _banerPadding);
      }
      //Adicionar padding para centralizar conteúdo
      var _paddingTop = (_banerSegment.innerHeight() - _conteudo.innerHeight())/2;
      _conteudo.css('padding-top',_paddingTop);
    }
  }
  var _atualizarVariaveisDOM = function(){
    _banerSegment = $('#baner-segment');
    _alturaHeader = $('#header').height();
    _alturaWindow = $(window).height();
    _alturaDocument = $(document).height();
  };
  var _initEResize = function(){
    _atualizarVariaveisDOM();
    _setarTamanhoBaner();
  };
  var resize = function(){
    _initEResize();
  };
  var init = function(){
    this.baner = $('#baner-segment');
    _initEResize();
  };

  return {
    init: init,
    resize: resize
  };
})();
