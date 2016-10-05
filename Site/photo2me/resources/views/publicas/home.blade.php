@extends('layouts.layoutMaster')

@section('head')
  {{-- Meta --}}
  <meta name="keywords" content="{{ trans('keywords.home') }}">
  <title>{{ trans('titles.home') }}</title>
  <meta name="Description" content="{{ trans('descriptions.home') }}">
@endsection

@section('header')
  @include('elements.headers.header')
@endsection

@section('conteudo')
  <div id="home-banner" class="ui segment">
    <div class="ui text container">
      <h1>Título de impacto</h1>
    </div>
  </div>
  <h1>Home</h1>
  <p>
    Teste, teste, teste.
  </p>
@endsection

@section('footer')
  @include('elements.footers.footer')
  {{-- JS --}}
  <script type="text/javascript">
    _FtmHome.init();
  </script>
@endsection
