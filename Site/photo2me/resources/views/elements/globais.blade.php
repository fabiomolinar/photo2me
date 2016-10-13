<script type="text/javascript">
  var _FtmGlobais = (function(){
    var init = function(){
      //fv = form validation
      this.fvNomeObrigatorio = "{!! trans('form.fvNomeObrigatorio') !!}";
      this.fvEmailObrigatorio = "{!! trans('form.fvEmailObrigatorio') !!}";
      this.fvEmailFormato = "{!! trans('form.fvEmailFormato') !!}";
      this.fvPasswordObrigatorio = "{!! trans('form.fvPasswordObrigatorio') !!}";
    }
    return {
      init: init
    }
  })();
  _FtmGlobais.init();
</script>
