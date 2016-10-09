var _FtmMaster = (function(){
  var init = function(){
    $('.ui.sidebar').sidebar('attach events','.top.fixed.menu #header-side-menu','show');    
  };
  return {
    init: init
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
