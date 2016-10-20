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
              prompt: _FtmGlobais.fvEmailFormato
            },
            {
              type: 'empty',
              prompt: _FtmGlobais.fvEmailObrigatorio
            }
          ]
        }
      },
      onSuccess: function(event,fields){
        var _botaoEnviar = _formulario.find('button');
        var _dados = _formulario.serialize();
        var errorMsgBox = _formulario.find('.ui.error.message');
        var successMsgBox = $('#contato-msg-sucesso');
        successMsgBox
          .removeClass('visible')
          .addClass('hidden');
        errorMsgBox
          .removeClass('visible')
          .addClass('hidden');
        _botaoEnviar.addClass('loading');
        $.ajax({
          type: 'POST',
          url: _FtmGlobais.urlPostContato,
          data: _dados,
          dataType: 'json',
          success: function(data, textStatus, jqXHR){
            _botaoEnviar.removeClass('loading');
            successMsgBox
              .removeClass('hidden')
              .addClass('visible');
          },
          error: function(jqXHR, textStatus, errorThrown){
            _botaoEnviar.removeClass('loading');
            if (jqXHR.status == 422){
              var stringErros = "";
              for (var key in jqXHR.responseJSON){
                if (jqXHR.responseJSON.hasOwnProperty(key)){
                  var erros = jqXHR.responseJSON[key];
                  for (var i = 0; i < erros.length; i++){
                    stringErros = stringErros + "<li>" + erros[i] + "</li>";
                  }
                }
              }
              errorMsgBox.find('ul')
                .empty()
                .append(stringErros);
              errorMsgBox
              .removeClass('hidden')
              .addClass('visible');
            } else {
              //algum outro tipo de erro
              $('#modals-ops-algo-deu-errado').modal('show');
            }
          }
        });
        //O retorno abaixo é para evitar que a página seja redirecionada
        return false;
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
