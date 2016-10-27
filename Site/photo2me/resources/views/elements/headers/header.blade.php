<div class="ui large top fixed menu">
  <div class="header item">
    <a href="{{ URL::route('home') }}" class="ui image"><img src="{{ asset('/img/logo.png') }}" id="header-logo" alt="" /></a>
  </div>
  <a href="{{ URL::route('comoFunciona') }}" class="item mobile-hide">{{ trans('elements.header-como-funciona') }}</a>
  <a href="{{ URL::route('contato') }}" class="item mobile-hide">{{ trans('elements.header-contato') }}</a>
  <a id="header-side-menu" class="item mobile-show"><i class="sidebar icon"></i>{{ trans('elements.header-menu') }}</a>
  <div class="right menu">
    @if (Auth::guest())
      <a href="{{ URL::route('cadastrar') }}" class="item">{{ trans('elements.header-cadastrar') }}</a>
      <a href="{{ URL::route('entrar') }}" class="item">{{ trans('elements.header-entrar') }}</a>
    @else
      <a href="{{ url('/logout') }}" class="item">{{ trans('elements.header-sair') }}</a>
    @endif
  </div>
</div>
