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
  <h1>Como Funciona</h1>
  <p>
    Teste, teste, teste.
  </p>
@endsection

@section('footer')
  @include('elements.footers.footer')
  {{-- JS --}}
  <script type="text/javascript">
  </script>
@endsection
