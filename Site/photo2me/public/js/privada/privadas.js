var _FtmPrivadas = (function(){
  var subMenu;
  
  var ajustarSubMenu = function(){
    var tamanhoTela = $(window).width();
    if (tamanhoTela > 767){
      subMenu.removeClass('vertical');
    } else {
      subMenu.addClass('vertical');
    }
  };
  var initEResize = function(){
    ajustarSubMenu();
  };
  var init = function(){
    subMenu = $('#sub-header-privadas')
    initEResize();
  };
  var resize = function(){
    initEResize();
  };
  return {
    init: init,
    resize: resize
  };
})();
