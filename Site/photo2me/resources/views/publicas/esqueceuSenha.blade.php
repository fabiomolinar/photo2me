@extends('layouts.layoutMaster')

@section('head')
  {{-- Meta --}}
  <meta name="keywords" content="{{ trans('keywords.esqueci-senha') }}">
  <title>{{ trans('titles.esqueci-senha') }}</title>
  <meta name="Description" content="{{ trans('descriptions.esqueci-senha') }}">
@endsection

@section('sideHeader')
  @include('elements.headers.sideHeader')
@endsection

@section('header')
  @include('elements.headers.header')
@endsection

@section('conteudo')
  <div class="ui basic segment">
    <div class="ui container">
      <div id="esqueceu-senha-form-grid" class="ui middle aligned center aligned grid">
        <div class="center aligned column">
          <h1 class="ui header">{{ trans('messages.recuperar-senha') }}</h1>
          <form class="ui large form">
            <div class="ui stacked segment">
              <div class="required field">
                <div class="ui left icon input">
                  <i class="at icon"></i>
                  <input type="text" name="email" placeholder="{{ trans('form.qual-seu-email') }}">
                </div>
              </div>
              <button class="ui button" type="submit">{{ trans('elements.recuperar-recuperar') }}</button>
              <div class="ui error message">
                <div class="header">{{ trans('form.ops-temos-um-problema') }}</div>
                <ul class="list">
                </ul>
              </div>
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
      _FtmEsqueceuSenha.init();
      $(window).resize(function(){
        _FtmEsqueceuSenha.resize();
      });
    });
  </script>
@endsection
