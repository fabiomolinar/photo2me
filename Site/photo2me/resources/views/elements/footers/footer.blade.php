<div class="ui inverted vertical footer segment">
  <div class="ui center aligned container">
    <div class="ui stackable centered inverted grid">
      <div class="three wide middle aligned center aligned column mobile-hide">
        <div class="ui inverted link list">
          <a href="{{ URL::route('comoFunciona') }}" class="item">{{ trans('elements.header-como-funciona') }}</a>
        </div>
      </div>
      <div class="three wide middle aligned center aligned column mobile-hide">
        <div class="ui inverted link list">
          <a href="{{ URL::route('contato') }}" class="item">{{ trans('elements.header-contato') }}</a>
        </div>
      </div>
      <div class="seven wide center aligned column">
        <p>{{ trans('elements.footer-criar-comecar') }}</p>
        <button type="button" name="button" class="ui large button">
          {{ trans('elements.footer-comece-ja') }}
        </button>
      </div>
    </div>
    <div class="ui inverted section divider"></div>
    <img class="ui centered mini image" src="img/logo.png" alt="" />
    <div class="ui inverted link list">
      <a href="{{ URL::route('home') }}" class="item">Photo2Me</a>
    </div>
  </div>
</div>
