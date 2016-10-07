var _FtmMaster = (function(){
  var init = function(){
    $('.ui.sidebar').sidebar('attach events','.top.fixed.menu #header-side-menu','show');    
  };
  return {
    init: init
  };
})();

var _FtmHome = (function(){
  var banerSegment;
  var alturaMaxBaner = 700;
  var alturaHeader;
  var alturaWindow;
  var alturaDocument;

  var setarTamanhoBaner = function(){
    if (banerSegment && alturaWindow){
      //Tamanho mínimo é o tamanho do próprio conteúdo
      var tamanhoMinimo = $('#baner-segment .container').height();
      var banerPadding = banerSegment.outerHeight(true) - banerSegment.innerHeight();
      if (alturaWindow < tamanhoMinimo){
        banerSegment.height(tamanhoMinimo);
      } else if ((alturaWindow - alturaHeader) > alturaMaxBaner){
        banerSegment.height(alturaMaxBaner);
      } else {
        banerSegment.outerHeight(alturaWindow - alturaHeader - banerPadding);
      }
    }
  }
  var atualizarVariaveisDOM = function(){
    banerSegment = $('#baner-segment');
    alturaHeader = $('#header').height();
    alturaWindow = $(window).height();
    alturaDocument = $(document).height();
  };
  var initResize = function(){
    atualizarVariaveisDOM();
    setarTamanhoBaner();
  };
  var resize = function(){
    initResize();
  };
  var init = function(){
    initResize();
  };

  return {
    init: init,
    resize: resize
  };
})();
