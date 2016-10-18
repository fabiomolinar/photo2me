@extends('layouts.layoutMaster')

@section('head')
  {{-- Meta --}}
  <meta name="keywords" content="{{ trans('keywords.contato') }}">
  <title>{{ trans('titles.contato') }}</title>
  <meta name="Description" content="{{ trans('descriptions.contato') }}">
@endsection

@section('sideHeader')
  @include('elements.headers.sideHeader')
@endsection

@section('header')
  @include('elements.headers.header')
@endsection

@section('conteudo')
  <div class="ui segment">
    <div class="ui container">
      <h1 class="ui header">{{ trans('messages.entrar-em-contato') }}</h1>
      <p>{{ trans('messages.entrar-em-contato-descricao') }}
        <a href="mailto:{{ trans('messages.email-contato') }}" target="_blank">{{ trans('messages.email-contato') }}</a>.
      </p>
      <div class="ui basic segment">
        <div id="contato-form-grid" class="ui centered grid">
          <div class="center aligned column">
            <form class="ui large form" action="{{ URL::route('postContato') }}" method="post">
              {{ csrf_field() }}
              <div class="ui stacked segment">
                <div class="required field">
                  <div class="ui left icon input">
                    <i class="user icon"></i>
                    <input type="text" name="nome" placeholder="{{ trans('form.nos-diga-seu-nome') }}">
                  </div>
                </div>
                <div class="required field">
                  <div class="ui left icon input">
                    <i class="at icon"></i>
                    <input type="text" name="email" placeholder="{{ trans('form.qual-seu-email') }}">
                  </div>
                </div>
                <div class="field">
                  <div class="ui input">
                    <textarea name="mensagem" placeholder="{{ trans('form.deixe-seu-recado') }}"></textarea>
                  </div>
                </div>
                <button class="ui button" type="submit">{{ trans('elements.contato-enviar') }}</button>
              </div>
              <div class="ui error message">
                <div class="header">{{ trans('form.ops-temos-um-problema') }}</div>
                <ul class="list">
                </ul>
              </div>
            </form>
            <div id="contato-msg-sucesso" class="ui success message">
              <div class="header">{{ trans('form.mensagem-enviada') }}</div>
              <p>{{ trans('form.entraremos-em-contato') }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
@endsection

@section('footer')
  @include('elements.footers.footer')
  {{-- JS --}}
  <script type="text/javascript">
    $(document).ready(function(){
      _FtmContato.init();
      $(window).resize(function(){
        _FtmContato.resize();
      });
    });
  </script>
@endsection
