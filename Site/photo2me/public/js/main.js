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
      var tamanhoMinimo = $('#baner-segment .container').height();
      var banerPadding = _banerSegment.outerHeight(true) - _banerSegment.innerHeight();
      if (_alturaWindow < tamanhoMinimo){
        _banerSegment.height(tamanhoMinimo);
      } else if ((_alturaWindow - _alturaHeader) > _alturaMaxBaner){
        _banerSegment.height(_alturaMaxBaner);
      } else {
        _banerSegment.outerHeight(_alturaWindow - _alturaHeader - banerPadding);
      }
    }
  }
  var _atualizarVariaveisDOM = function(){
    _banerSegment = $('#baner-segment');
    _alturaHeader = $('#header').height();
    _alturaWindow = $(window).height();
    _alturaDocument = $(document).height();
  };
  var _initResize = function(){
    _atualizarVariaveisDOM();
    _setarTamanhoBaner();
  };
  var resize = function(){
    _initResize();
  };
  var init = function(){
    _initResize();
  };

  return {
    init: init,
    resize: resize
  };
})();
