@extends('layouts.layoutMaster')

@section('head')
  {{-- Meta --}}
  <meta name="keywords" content="{{ trans('keywords.entrar') }}">
  <title>{{ trans('titles.entrar') }}</title>
  <meta name="Description" content="{{ trans('descriptions.entrar') }}">
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
      <div id="entrar-form-grid" class="ui middle aligned center aligned grid">
        <div class="center aligned column">
          <h1 class="ui header">{{ trans('messages.bem-vindo-de-volta') }}</h1>
          <form class="ui large form">
            <div class="ui stacked segment">
              <div class="required field">
                <div class="ui left icon input">
                  <i class="at icon"></i>
                  <input type="text" name="email" placeholder="{{ trans('form.seu-email') }}">
                </div>
              </div>
              <div class="required field">
                <div class="ui left icon input">
                  <i class="hashtag icon"></i>
                  <input type="password" name="password" placeholder="{{ trans('form.sua-senha') }}">
                </div>
              </div>
              <p><a href="">{{ trans('messages.esqueci-minha-senha') }}</a></p>
              <div class="inline field">
                <div class="ui checkbox">
                  <input name="lembrar" type="checkbox" tabindex="0" class="hidden">
                  <label>{{ trans('messages.manter-conectado') }}</label>
                </div>
              </div>
              <button class="ui button" type="submit">{{ trans('elements.entrar-entrar') }}</button>
              <div class="ui error message">
                <div class="header">{{ trans('form.ops-temos-um-problema') }}</div>
                <ul class="list">
                </ul>
              </div>
            </div>
            <div class="ui segment">
              <p>{{ trans('messages.nao-e-usuario') }} <a href="{{ URL::route('cadastrar') }}">{{ trans('messages.clique-aqui-para-cadastrar') }}</a></p>
            </div>
          </form>
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
        _FtmEntrar.init();
      $(window).resize(function(){
          _FtmEntrar.resize();
      });
    });
  </script>
@endsection
