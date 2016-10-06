var _FtmMaster = (function(){
  var init = function(){
    //Fazer inicialização apenas depois que a página terminar de carregar
    $(document).ready(function(){
      $('.ui.sidebar').sidebar('attach events','.top.fixed.menu #header-side-menu','show');
    });
  };
  return {
    init: init
  };
})();
