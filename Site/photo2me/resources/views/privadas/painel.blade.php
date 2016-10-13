@extends('layouts.layoutPrivadas')

@section('headPrivadas')
  {{-- Meta --}}
  <meta name="keywords" content="{{ trans('keywords.painel') }}">
  <title>{{ trans('titles.painel') }}</title>
  <meta name="Description" content="{{ trans('descriptions.painel') }}">
@endsection

@section('headerPrivadas')
  @include('elements.headers.dashboard')
@endsection

@section('conteudoPrivadas')
  <h1>{{ trans('messages.painel') }}</h1>
  <h2>{{ trans('messages.criar-evento') }}</h2>
  <button class="ui button">
    <i class="plus icon"></i>
    {{ trans('messages.criar-evento') }}
  </button>
  <h2>{{ trans('messages.seus-eventos') }}</h2>
  @if (true == true)
    <div class="ui info message">
      <i class="close icon"></i>
      <div class="header">
        {{ trans('messages.nenhum-evento-encontrado') }}
      </div>
      <p>{{ trans('messages.nao-preocupe-pode-criar-um') }} <a href="">{{ trans('messages.apenas-clique-aqui') }}</a></p>
    </div>
  @endif
@endsection

@section('footerPrivadas')
  @include('elements.footers.dashboard')
  {{-- JS --}}
  <script type="text/javascript">
    $(document).ready(function(){
      $(window).resize(function(){
      });
    });
  </script>
@endsection