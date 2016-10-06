<div class="ui sidebar left vertical menu">
  <div class="item">
    <a href="{{ URL::route('comoFunciona') }}" class="item">{{ trans('elements.header-como-funciona') }}</a>
    <a href="{{ URL::route('contato') }}" class="item">{{ trans('elements.header-contato') }}</a>
  </div>
  <div class="item">
    <a href="{{ URL::route('cadastrar') }}" class="item">{{ trans('elements.header-cadastrar') }}</a>
    <a href="{{ URL::route('entrar') }}" class="item">{{ trans('elements.header-entrar') }}</a>
  </div>
</div>
