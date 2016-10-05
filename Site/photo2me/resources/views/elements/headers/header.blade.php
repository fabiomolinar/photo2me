<div class="ui large secondary pointing menu">
  <div class="header item">
    <a href="{{ URL::route('home') }}" class="ui image"><img src="img/logo.png" id="header-logo" alt="" /></a>
  </div>
  <a href="{{ URL::route('comoFunciona') }}" class="item">{{ trans('elements.header-como-funciona') }}</a>
  <a href="{{ URL::route('contato') }}" class="item">{{ trans('elements.header-contato') }}</a>
  <div class="right menu">
    <a href="{{ URL::route('cadastrar') }}" class="item">{{ trans('elements.header-cadastrar') }}</a>
    <a href="{{ URL::route('entrar') }}" class="item">{{ trans('elements.header-entrar') }}</a>
  </div>
</div>
