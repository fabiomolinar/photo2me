@extends('layouts.layoutMaster')

@section('head')
  {{-- Meta --}}
  <meta name="keywords" content="{{ trans('keywords.comoFunciona') }}">
  <title>{{ trans('titles.comoFunciona') }}</title>
  <meta name="Description" content="{{ trans('descriptions.comoFunciona') }}">
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
      <h1 class="ui header">{{ trans('messages.preparando-tudo') }}</h1>
      <p>{{ trans('messages.passo-a-passo') }}</p>
      <div class="ui stacked segment">
        <div class="ui center aligned grid">
          <div class="row">
            <div class="column"><h2>{{ trans('messages.o-que-voce-precisa-fazer') }}</h2></div>
          </div>
          <div class="row">
            <div class="column">
              <div class="ui two steps">
                <div class="step">
                  <i class="edit icon"></i>
                  <div class="content">
                    <div class="title">{{ trans('messages.criar-festa-titulo') }}</div>
                    <div class="description">{{ trans('messages.criar-festa-descricao') }}</div>
                  </div>
                </div>
                  <div class="active step">
                  <i class="announcement icon"></i>
                  <div class="content">
                    <div class="title">{{ trans('messages.divulgar-codigo-titulo') }}</div>
                    <div class="description">{{ trans('messages.divulgar-codigo-descricao') }}</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="ui divider"></div>
          <div class="row">
            <div class="column"><h2>{{ trans('messages.o-que-convidados-precisam-fazer') }}</h2></div>
          </div>
          <div class="row">
            <div class="column">
              <div class="ui three steps">
                <div class="step">
                  <i class="download icon"></i>
                  <div class="content">
                    <div class="title">{{ trans('messages.baixar-app-titulo') }}</div>
                    <div class="description">{{ trans('messages.baixar-app-descricao') }}</div>
                  </div>
                </div>
                <div class="active step">
                  <i class="write icon"></i>
                  <div class="content">
                    <div class="title">{{ trans('messages.digitar-codigo-titulo') }}</div>
                    <div class="description">{{ trans('messages.digitar-codigo-descricao') }}</div>
                  </div>
                </div>
                <div class="step">
                  <i class="photo icon"></i>
                  <div class="content">
                    <div class="title">{{ trans('messages.fazer-fotos-titulo') }}</div>
                    <div class="description">{{ trans('messages.fazer-fotos-descricao') }}</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <button class="ui large fluid button" type="button" name="button">{{ trans('messages.comecar-agora') }}</button>
    </div>
  </div>
@endsection

@section('footer')
  @include('elements.footers.footer')
  {{-- JS --}}
  <script type="text/javascript">
    $(document).ready(function(){
      $(window).resize(function(){
      });
    });
  </script>
@endsection
