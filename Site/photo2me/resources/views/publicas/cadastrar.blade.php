@extends('layouts.layoutMaster')

@section('head')
  {{-- Meta --}}
  <meta name="keywords" content="{{ trans('keywords.cadastrar') }}">
  <title>{{ trans('titles.cadastrar') }}</title>
  <meta name="Description" content="{{ trans('descriptions.cadastrar') }}">
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
      <div class="ui middle aligned center aligned grid">
        <div class="center aligned column">
          <h1 class="ui header">{{ trans('messages.bem-vindo') }}</h1>
          <form class="ui large form">
            <div class="ui stacked segment">
              <div class="required field">
                <div class="ui left icon input">
                  <i class="at icon"></i>
                  <input type="text" name="email" placeholder="{{ trans('form.qual-seu-email') }}">
                </div>
              </div>
              <div class="required field">
                <div class="ui left icon input">
                  <i class="hashtag icon"></i>
                  <input type="text" name="email" placeholder="{{ trans('form.crie-uma-senha') }}">
                </div>
              </div>
              <div class="inline field">
                <div class="ui checkbox">
                  <input name="lembrar" type="checkbox" tabindex="0" class="hidden">
                  <label>{{ trans('messages.manter-conectado') }}</label>
                </div>
              </div>
              <button class="ui button" type="submit">{{ trans('elements.cadastrar-cadastrar') }}</button>
              <div class="ui error message">
                <div class="header">{{ trans('form.ops-temos-um-problema') }}</div>
                <ul class="list">
                </ul>
              </div>
            </div>
          </form>
          <div class="ui segment">
            <p>{{ trans('messages.ja-e-usuario') }} <a href="{{ URL::route('entrar') }}">{{ trans('messages.clique-aqui-para-entrar') }}</a></p>
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
      _FtmCadastrar.init();
      $(window).resize(function(){
        _FtmCadastrar.resize();
      });
    });
  </script>
@endsection
