var _FtmMaster = (function(){
  var init = function(){
    $('.ui.sidebar').sidebar('attach events','.top.fixed.menu #header-side-menu','show');    
  };
  return {
    init: init
  };
})();
